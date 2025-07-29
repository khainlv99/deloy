import "./UpdateFilm.css";
import { useNavigate, useParams } from "react-router";
import { useEffect, useState } from "react";
import * as FilmService from "../../service/FilmService";
import * as TypeFilmService from "../../service/TypeFilmService";
import { ErrorMessage, Field, Form, Formik } from "formik";
import * as Yup from "yup";
import { ColorRing } from "react-loader-spinner";

import { ref, getDownloadURL, uploadBytesResumable } from "firebase/storage";

import { toast } from "react-toastify";
import { storage } from "../../firebase";
import { updateFilm } from "../../service/FilmService";
import Footer from "../common/footer/Footer";
import Header from "../common/header/Header";

export function UpdateFilm() {
  const navigate = useNavigate();
  const [films, setFilms] = useState();
  const [listTypeFilm, setListTypeFilm] = useState([]);
  const params = useParams();
  const [selectedFile, setSelectedFile] = useState(null);
  const [firebaseImg, setImg] = useState(null);
  const [progress, setProgress] = useState(0);

  useEffect(() => {
    const search = async () => {
      console.log(params.id);
      const film = await FilmService.findFilmById(params.id);
      setFilms(film);
      console.log(film);
    };
    search();
  }, [params.id]);

  useEffect(() => {
    const listTypeFilm = async () => {
      const result = await TypeFilmService.listTypeFilm();
      setListTypeFilm(result);
      console.log(result);
    };
    listTypeFilm();
  }, []);

  const handleFileSelect = (event) => {
    const file = event.target.files[0];
    console.log(file);
    if (file) {
      setSelectedFile(file);
    }
  };
  const handleSubmitAsync = async () => {
    return new Promise((resolve, reject) => {
      const file = selectedFile;
      if (!file) return reject("No file selected");
      const storageRef = ref(storage, `files/${file.name}`);
      const uploadTask = uploadBytesResumable(storageRef, file);

      uploadTask.on(
        "state_changed",
        (snapshot) => {
          const progress = Math.round(
            (snapshot.bytesTransferred / snapshot.totalBytes) * 100
          );
          setProgress(progress);
        },
        (error) => {
          reject(error);
        },
        async () => {
          const downloadURL = await getDownloadURL(uploadTask.snapshot.ref);
          setImg(downloadURL);
          resolve(downloadURL);
        }
      );
    });
  };

  useEffect(() => {
    document.title = "Chỉnh sửa phim";
  }, []);

  if (!films) {
    return null;
  }
  return (
    <>
      <Header />
      {films && (
        <Formik
          initialValues={{
            idFilm: films?.idFilm,
            imgFilm: films?.imgFilm,
            nameFilm: films?.nameFilm,
            nation: films?.nation,
            dateStartFilm: films?.dateStartFilm,
            dateEndFilm: films?.dateEndFilm,
            actor: films?.actor,
            studioFilm: films?.studioFilm,
            director: films?.director,
            timeFilm: films?.timeFilm,
            movieLabel: films?.movieLabel,
            trailer: films?.trailer,
            typeFilm: {
              idTypeFilm: films?.typeFilm.idTypeFilm,
            },
            normalSeatPrice: films?.normalSeatPrice,
            vipSeatPrice: films?.vipSeatPrice,
            describeFilm: films?.describeFilm,
            nameTypeFilm: films?.typeFilm.nameTypeFilm,
          }}
          validationSchema={Yup.object({
            nameFilm: Yup.string().required("Nhập tên phim"),
            nation: Yup.string().required("Nhập quốc gia"),
            dateStartFilm: Yup.date().required(
              "Ngày bắt đầu không được để trống"
            ),
            dateEndFilm: Yup.date()
              .required("Ngày kết thúc không được để trống")
              .min(
                Yup.ref("dateStartFilm"),
                "Ngày kết thúc phải lớn hơn ngày bắt đầu"
              ),
            actor: Yup.string().required("Nhập diễn viên"),
            studioFilm: Yup.string().required("Nhập hãng phim"),
            director: Yup.string().required("Nhập đạo diễn"),
            timeFilm: Yup.number()
              .required("Nhập thời lượng phim")
              .min(30, "Thời lượng phim không được nhỏ hơn 30")
              .max(200, "Thời lượng phim không được lớn hơn 200"),
            movieLabel: Yup.string().required("Nhập nhãn phim"),
            trailer: Yup.string()
              .required("Nhập trailer phim")
              .matches(
                "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&=]*)"
              ),
            normalSeatPrice: Yup.number()
              .required("Nhập giá ghế thường")
              .min(10000, "Giá ghế thường không được nhỏ hơn 10000")
              .max(1000000000, "Giá ghế thường không được quá 1 tỷ"),
            vipSeatPrice: Yup.number()
              .required("Nhập giá ghế vip")
              .min(10000, "Giá ghế vip không được nhỏ hơn 10000")
              .max(1000000000, "Giá ghế vip không được quá 1 tỷ"),
            describeFilm: Yup.string().required("Nhập nội dung phim"),
          })}
          onSubmit={(values, { setSubmitting }) => {
            console.log(values);
            const edit = async () => {
              // const newValue = {
              //     ...values,
              //     imgFilm: firebaseImg,
              // };
              // newValue.imgFilm = await handleSubmitAsync();
              values.typeFilm.idTypeFilm = parseInt(values.typeFilm.idTypeFilm);
              delete values.idTypeFilm;
              console.log(values);
              await FilmService.updateFilm(values);
              setSubmitting(false);
              toast("Chỉnh sửa phim " + values.nameFilm + " thành công");
              navigate("/admin/film/list");
              console.log(values);
            };
            edit();
          }}
        >
          {({ isSubmitting }) => (
            <Form style={{ margin: "150px 0" }}>
              <div className="container py-5 h-100">
                <div className="row justify-content-center align-items-center h-100">
                  <div className="col-12 col-lg-9 col-xl-7">
                    <div
                      className="card shadow-2-strong card-registration"
                      style={{ borderRadius: 15 }}
                    >
                      <div className="card-body p-4 p-md-5">
                        <h3 className="mb-4 pb-2 pb-md-0 mb-md-5">
                          Thêm mới phim
                        </h3>
                        <div className="row">
                          <div className="col-md-6 mb-4">
                            {/*<div className="form-outline">*/}
                            {/*    <Field*/}
                            {/*        type="file"*/}
                            {/*        onChange={(e) => handleFileSelect(e)}*/}
                            {/*        id="imgFilm"*/}
                            {/*        name={"imgFilm"}*/}
                            {/*        className="form-control-plaintext d-none "*/}
                            {/*    />*/}
                            {/*    <p>*/}
                            {/*        <label htmlFor="imgFilm" style={{*/}
                            {/*            display: "flex",*/}
                            {/*            padding: "6px 12px",*/}
                            {/*            border: "1px solid",*/}
                            {/*            borderRadius: "4px",*/}
                            {/*        }}>*/}
                            {/*            Chọn hình ảnh*/}
                            {/*        </label></p>*/}
                            {/*    {!selectedFile && (*/}
                            {/*        <span className={"mt-2 text-danger"}>Chưa có hình ảnh được chọn</span>*/}
                            {/*    )}*/}
                            {/*</div>*/}
                            <div className="inputBox">
                              <Field
                                type="text"
                                className="input"
                                style={{ width: "100%", height: "90%" }}
                                name="imgFilm"
                              />
                              <ErrorMessage
                                name="imgFilm"
                                component={"p"}
                                style={{ color: "red" }}
                              />
                              <label
                                className="labelInput"
                                style={{
                                  marginLeft: "2%",
                                  backgroundColor: "white",
                                  color: "black",
                                }}
                              >
                                Ảnh phim
                              </label>
                            </div>
                          </div>
                          <div className="col-md-6 mb-4">
                            <div className="inputBox">
                              <Field
                                type="text"
                                className="input"
                                style={{ width: "100%", height: "90%" }}
                                name="nameFilm"
                              />
                              <ErrorMessage
                                name="nameFilm"
                                component={"p"}
                                style={{ color: "red" }}
                              />
                              <label
                                className="labelInput"
                                style={{
                                  marginLeft: "2%",
                                  backgroundColor: "white",
                                  color: "black",
                                }}
                              >
                                Tên phim
                              </label>
                            </div>
                          </div>
                        </div>
                        <div className="row">
                          <div className="col-md-6 mb-4 d-flex align-items-center">
                            <div className="form-outline datepicker w-100">
                              <div className="inputBox">
                                <Field
                                  type="text"
                                  className="input"
                                  style={{ width: "100%", height: "90%" }}
                                  name="nation"
                                />
                                <ErrorMessage
                                  name="nation"
                                  component={"p"}
                                  style={{ color: "red" }}
                                />
                                <label
                                  className="labelInput"
                                  style={{
                                    marginLeft: "2%",
                                    backgroundColor: "white",
                                    color: "black",
                                  }}
                                >
                                  Quốc gia
                                </label>
                              </div>
                            </div>
                          </div>
                          <div className="col-md-6 mb-4">
                            <div className="inputBox">
                              <Field
                                type="text"
                                className="input"
                                style={{ width: "100%", height: "90%" }}
                                name="studioFilm"
                              />
                              <ErrorMessage
                                name="studioFilm"
                                component={"p"}
                                style={{ color: "red" }}
                              />
                              <label
                                className="labelInput"
                                style={{
                                  marginLeft: "2%",
                                  backgroundColor: "white",
                                  color: "black",
                                }}
                              >
                                Hãng phim
                              </label>
                            </div>
                          </div>
                        </div>
                        <div className="row">
                          <div className="col-md-6 mb-4">
                            <div className="inputBox">
                              <Field
                                type="date"
                                className="input"
                                style={{ width: "100%", height: "90%" }}
                                name="dateStartFilm"
                              />
                              <ErrorMessage
                                name="dateStartFilm"
                                component={"p"}
                                style={{ color: "red" }}
                              />
                              <label
                                className="labelInput"
                                style={{
                                  marginLeft: "2%",
                                  backgroundColor: "white",
                                  color: "black",
                                }}
                              >
                                Từ ngày
                              </label>
                            </div>
                          </div>
                          <div className="col-md-6 mb-4">
                            <div className="inputBox">
                              <Field
                                type="date"
                                className="input"
                                style={{ width: "100%", height: "90%" }}
                                name="dateEndFilm"
                              />
                              <ErrorMessage
                                name="dateEndFilm"
                                component={"p"}
                                style={{ color: "red" }}
                              />
                              <label
                                className="labelInput"
                                style={{
                                  marginLeft: "2%",
                                  backgroundColor: "white",
                                  color: "black",
                                }}
                              >
                                Đến ngày
                              </label>
                            </div>
                          </div>
                        </div>
                        <div className="row">
                          <div className="col-md-6 mb-4">
                            <div className="inputBox">
                              <Field
                                type="text"
                                className="input"
                                style={{ width: "100%", height: "90%" }}
                                name="director"
                              />
                              <ErrorMessage
                                name="director"
                                component={"p"}
                                style={{ color: "red" }}
                              />
                              <label
                                className="labelInput"
                                style={{
                                  marginLeft: "2%",
                                  backgroundColor: "white",
                                  color: "black",
                                }}
                              >
                                Đạo diễn
                              </label>
                            </div>
                          </div>
                          <div className="col-md-6 mb-4">
                            <div className="inputBox">
                              <Field
                                type="text"
                                className="input"
                                style={{ width: "100%", height: "90%" }}
                                name="actor"
                              />
                              <ErrorMessage
                                name="actor"
                                component={"p"}
                                style={{ color: "red" }}
                              />
                              <label
                                className="labelInput"
                                style={{
                                  marginLeft: "2%",
                                  backgroundColor: "white",
                                  color: "black",
                                }}
                              >
                                Diễn viên
                              </label>
                            </div>
                          </div>
                        </div>
                        <div className="row">
                          <div className="col-md-6 mb-4">
                            <div className="inputBox">
                              <Field
                                type="number"
                                className="input"
                                style={{ width: "100%", height: "90%" }}
                                name="timeFilm"
                              />
                              <ErrorMessage
                                name="timeFilm"
                                component={"p"}
                                style={{ color: "red" }}
                              />
                              <label
                                className="labelInput"
                                style={{
                                  marginLeft: "2%",
                                  backgroundColor: "white",
                                  color: "black",
                                }}
                              >
                                Thời lượng
                              </label>
                            </div>
                          </div>
                          <div className="col-md-6 mb-4">
                            <div className="inputBox">
                              <Field
                                type="text"
                                className="input"
                                style={{ width: "100%", height: "90%" }}
                                name="movieLabel"
                              />
                              <ErrorMessage
                                name="movieLabel"
                                component={"p"}
                                style={{ color: "red" }}
                              />
                              <label
                                className="labelInput"
                                style={{
                                  marginLeft: "2%",
                                  backgroundColor: "white",
                                  color: "black",
                                }}
                              >
                                Nhãn phim
                              </label>
                            </div>
                          </div>
                        </div>
                        <div className="row">
                          <div className="col-md-6 mb-4">
                            <div className="inputBox">
                              <Field
                                type="number"
                                className="input"
                                style={{ width: "100%", height: "90%" }}
                                name="normalSeatPrice"
                              />
                              <ErrorMessage
                                name="normalSeatPrice"
                                component={"p"}
                                style={{ color: "red" }}
                              />
                              <label
                                className="labelInput"
                                style={{
                                  marginLeft: "2%",
                                  backgroundColor: "white",
                                  color: "black",
                                }}
                              >
                                Giá ghế thường
                              </label>
                            </div>
                          </div>
                          <div className="col-md-6 mb-4">
                            <div className="inputBox">
                              <Field
                                type="number"
                                className="input"
                                style={{ width: "100%", height: "90%" }}
                                name="vipSeatPrice"
                              />
                              <ErrorMessage
                                name="vipSeatPrice"
                                component={"p"}
                                style={{ color: "red" }}
                              />
                              <label
                                className="labelInput"
                                style={{
                                  marginLeft: "2%",
                                  backgroundColor: "white",
                                  color: "black",
                                }}
                              >
                                Giá ghế vip
                              </label>
                            </div>
                          </div>
                        </div>
                        <div className="row">
                          <div className="col-md-6 mb-4">
                            <div className="inputBox">
                              <Field
                                type="text"
                                className="input"
                                style={{ width: "100%", height: "90%" }}
                                name="trailer"
                              />
                              <ErrorMessage
                                name="trailer"
                                component={"p"}
                                style={{ color: "red" }}
                              />
                              <label
                                className="labelInput"
                                style={{
                                  marginLeft: "2%",
                                  backgroundColor: "white",
                                  color: "black",
                                }}
                              >
                                Trailer
                              </label>
                            </div>
                          </div>
                          <div className="col-md-6 mb-4">
                            <div className="inputBox">
                              <Field
                                as="select"
                                name="typeFilm.idTypeFilm"
                                style={{
                                  width: "100%",
                                  border: "1px solid #000",
                                }}
                              >
                                {listTypeFilm.map((listType, index) => (
                                  <option value={listType.idTypeFilm}>
                                    {listType.nameTypeFilm}
                                  </option>
                                ))}
                              </Field>
                              <ErrorMessage
                                name="idTypeFilm"
                                component={"p"}
                                style={{ color: "red" }}
                              />
                            </div>
                          </div>
                        </div>

                        <div className="row">
                          <div className="inputBox">
                            <Field
                              placeholder="Nội dung phim"
                              as="textarea"
                              row={4}
                              col={86}
                              type="text"
                              className="inputContent"
                              style={{ width: "100%", height: "90%" }}
                              name="describeFilm"
                            />
                            <ErrorMessage
                              name="describeFilm"
                              component={"p"}
                              style={{ color: "red" }}
                            />
                            {/*<label className="labelInput" style={{marginLeft:"2%", backgroundColor:"white", color:"black"}}>*/}
                            {/*    Nội dung phim*/}
                            {/*</label>*/}
                          </div>
                        </div>

                        <div className="mt-4 pt-2 d-flex justify-content-around">
                          {isSubmitting ? (
                            <ColorRing
                              visible={true}
                              height="80"
                              width="80"
                              ariaLabel="blocks-loading"
                              wrapperStyle={{}}
                              wrapperClass="blocks-wrapper"
                              colors={[
                                "#e15b64",
                                "#f47e60",
                                "#f8b26a",
                                "#abbd81",
                                "#849b87",
                              ]}
                            />
                          ) : (
                            <button
                              type="submit"
                              className="btn btn-primary"
                              style={{ background: "#f26b38" }}
                            >
                              Chỉnh sửa
                            </button>
                          )}
                          <button
                            type="reset"
                            className="btn btn-dark"
                            style={{
                              background: "black",
                              color: "white",
                              marginLeft: "0%",
                            }}
                          >
                            Quay lại
                          </button>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </Form>
          )}
        </Formik>
      )}
      <Footer />
    </>
  );
}
