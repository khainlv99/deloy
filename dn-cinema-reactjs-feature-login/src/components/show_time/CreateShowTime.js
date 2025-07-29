import {useNavigate} from "react-router";
import {useEffect, useState} from "react";
import * as TypeFilmService from "../../service/TypeFilmService";
import * as Yup from "yup";
import * as FilmService from "../../service/FilmService";
import {ErrorMessage, Field, Form, Formik} from "formik";
import {ColorRing} from "react-loader-spinner";
import {ref, getDownloadURL, uploadBytesResumable} from 'firebase/storage'
import {toast} from "react-toastify";
import {storage} from "../../firebase";
import {getAll} from "../../service/ShowRoomService";



export function CreateShowTime() {
    const navigate = useNavigate();
    const [listFilm, setListFilm] = useState([]);
    const [listShowRoom, setListShowRoom] = useState([]);
    useEffect(() => {
        const listFilm = async () => {
            const result = await FilmService.apiGetAllFilms();
            setListFilm(result)
            console.log(result)
        }
        listFilm();
    }, [])
    useEffect(() => {
        const listShowRoom = async () => {
            const result = await getAll();
            setListShowRoom(result)
            console.log(result)
        }
        listShowRoom();
    }, [])


    const [selectedFile, setSelectedFile] = useState(null);
    const [firebaseImg, setImg] = useState(null);
    const [progress, setProgress] = useState(0);

    const handleFileSelect = (event) => {
        const file = event.target.files[0];
        console.log(file)
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
        document.title = "Thêm mới phim"
    }, [])

    return (
        <Formik
            initialValues={{
                imgFilm: "",
                nameFilm: "",
                nation: "",
                dateStartFilm: "",
                dateEndFilm: "",
                actor: "",
                studioFilm: "",
                director: "",
                timeFilm: "",
                movieLabel: "",
                trailer: "",
                normalSeatPrice: "",
                vipSeatPrice: "",
                describeFilm: "",
                typeFilm:{
                    idTypeFilm:0
                }

            }}
            validationSchema={Yup.object({
                nameFilm: Yup.string()
                    .required("Nhập tên phim"),
                nation: Yup.string()
                    .required("Nhập quốc gia"),
                dateStartFilm: Yup.date()
                    .required("Nhập ngày khởi chiếu"),
                dateEndFilm: Yup.date()
                    .required("Nhập ngày kết thúc"),
                actor: Yup.string()
                    .required("Nhập diễn viên"),
                studioFilm: Yup.string()
                    .required("Nhập hãng phim"),
                director: Yup.string()
                    .required("Nhập đạo diễn"),
                timeFilm: Yup.number()
                    .required("Nhập thời lượng phim")
                    .min(30 , "Thời lượng phim không được nhỏ hơn 30")
                    .max(200, "Thời lượng phim không được lớn hơn 200"),
                movieLabel: Yup.string()
                    .required("Nhập nhãn phim"),
                trailer: Yup.string()
                    .required("Nhập trailer phim")
                    .matches("https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&=]*)"),
                normalSeatPrice: Yup.number()
                    .required("Nhập giá ghế thường")
                    .min(10000,"Giá ghế thường không được nhỏ hơn 10000")
                    .max(1000000000, "Giá ghế thường không được quá 1 tỷ"),
                vipSeatPrice: Yup.number()
                    .required("Nhập giá ghế vip")
                    .min(10000, "Giá ghế vip không được nhỏ hơn 10000")
                    .max(1000000000, "Giá ghế vip không được quá 1 tỷ"),
                describeFilm: Yup.string()
                    .required("Nhập nội dung phim")
            })}
            onSubmit={(values, {resetForm}) => {
                const create = async () => {
                    const newValue = {
                        ...values,
                        imgFilm: firebaseImg,
                    };
                    newValue.imgFilm = await handleSubmitAsync();
                    newValue.typeFilm.idTypeFilm = parseInt(values.idTypeFilm);
                    delete values.idTypeFilm;
                    await FilmService.createFilm(newValue);
                    toast("Thêm mới phim " + values.nameFilm + " thành công")
                    navigate('/admin/film/list')
                    console.log(newValue)
                    resetForm();
                }
                create();
            }}>
            {
                ({
                     values,
                     touched,
                     errors,
                     isSubmitting,
                     handleChange,
                     handleBlur,
                     handleSubmit
                 }) => (
                    <div className="row mx-0" style={{marginTop: "30%"}}>
                        <div className="container mx-auto my-5 col-8">
                            <div className="form-edit-movie">
                                <h1 style={{textAlign: "center", marginBottom: "5%"}}>
                                    Thêm mới phim
                                </h1>
                                <Form>
                                    <div className="row" style={{marginBottom: "2%", display:"flex"}}>
                                        <div className="col-3" style={{textAlign: "right"}}>
                                            <label className="fw-bold" style={{marginRight: "2%", display: "flex" , float: "right", marginTop: "3%"}}>
                                                Ảnh <span style={{color: "red"}}>(*)</span>
                                            </label>
                                        </div>
                                        <div className="col-3" style={{width:"67%"}}>
                                            <Field
                                                type="file"
                                                onChange={(e) => handleFileSelect(e)}
                                                id="imgFilm"
                                                name={"imgFilm"}
                                                className="form-control-plaintext d-none "
                                            />
                                            <p>
                                                <label htmlFor="imgFilm" style={{
                                                    display: "flex",
                                                    padding: "6px 12px",
                                                    border: "1px solid",
                                                    borderRadius: "4px",
                                                }}>
                                                    Chọn hình ảnh
                                                </label></p>
                                            {!selectedFile && (
                                                <span className={"mt-2 text-danger"}>Chưa có hình ảnh được chọn</span>
                                            )}
                                            {selectedFile && (
                                                <img
                                                    className={"mt-2"}
                                                    src={URL.createObjectURL(selectedFile)}
                                                    style={{width: "100%"}}
                                                />
                                            )}

                                        </div>
                                        <ErrorMessage name="imgFilm" component='span'
                                                      className='form-err text-center' style={{color: "red"}}/>
                                    </div>
                                    <div className="row" style={{marginBottom: "2%"}}>
                                        <div className="col-3" style={{textAlign: "right"}}>
                                            <label className="fw-bold" style={{marginRight: "2%", display: "flex" , float: "right", marginTop: "3%"}}>
                                                Tên phim <span className="warning">(*)</span>
                                            </label>
                                        </div>
                                        <div className="col-8">
                                            <Field
                                                type="text"
                                                style={{width: "100%"}}
                                                name="nameFilm"

                                            />
                                            <ErrorMessage name="nameFilm" component={"p"} style={{color: "red"}}/>
                                        </div>
                                    </div>
                                    <div className="row" style={{marginBottom: "2%"}}>
                                        <div className="col-3" style={{textAlign: "right"}}>
                                            <label className="fw-bold" style={{marginRight: "2%", display: "flex" , float: "right", marginTop: "3%"}}>
                                                Quốc gia <span className="warning">(*)</span>
                                            </label>
                                        </div>
                                        <div className="col-8">
                                            <Field
                                                type="text"
                                                style={{width: "100%"}}
                                                name="nation"
                                            />
                                            <ErrorMessage name="nation" component={"p"} style={{color: "red"}}/>
                                        </div>
                                    </div>
                                    <div className="row" style={{marginBottom: "2%"}}>
                                        <div className="col-3" style={{textAlign: "right"}}>
                                            <label className="fw-bold" style={{marginRight: "2%", display: "flex" , float: "right", marginTop: "3%"}}>
                                                Từ ngày <span className="warning">(*)</span>
                                            </label>
                                        </div>
                                        <div className="col-8">
                                            <Field
                                                type="date"
                                                style={{width: "100%"}}
                                                name="dateStartFilm"
                                            />
                                            <ErrorMessage name="dateStartFilm" component={"p"}
                                                          style={{color: "red"}}/>

                                        </div>
                                    </div>
                                    <div className="row" style={{marginBottom: "2%"}}>
                                        <div className="col-3" style={{textAlign: "right"}}>
                                            <label className="fw-bold" style={{marginRight: "2%", display: "flex" , float: "right", marginTop: "3%"}}>
                                                Đến ngày <span className="warning">(*)</span>
                                            </label>
                                        </div>
                                        <div className="col-8">
                                            <Field
                                                type="date"
                                                style={{width: "100%"}}
                                                name="dateEndFilm"
                                            />
                                            <ErrorMessage name="dateEndFilm" component={"p"}
                                                          style={{color: "red"}}/>
                                        </div>
                                    </div>
                                    <div className="row" style={{marginBottom: "2%"}}>
                                        <div className="col-3" style={{textAlign: "right"}}>
                                            <label className="fw-bold" style={{marginRight: "2%", display: "flex" , float: "right", marginTop: "3%"}}>
                                                Diễn viên <span className="warning">(*)</span>
                                            </label>
                                        </div>
                                        <div className="col-8">
                                            <Field
                                                type="text"
                                                style={{width: "100%"}}
                                                name="actor"
                                            />
                                            <ErrorMessage name="actor" component={"p"} style={{color: "red"}}/>
                                        </div>
                                    </div>
                                    <div className="row" style={{marginBottom: "2%"}}>
                                        <div className="col-3" style={{textAlign: "right"}}>
                                            <label className="fw-bold" style={{marginRight: "2%", display: "flex" , float: "right", marginTop: "3%"}}>
                                                Hãng phim <span className="warning">(*)</span>
                                            </label>
                                        </div>
                                        <div className="col-8">
                                            <Field
                                                type="text"
                                                style={{width: "100%"}}
                                                name="studioFilm"
                                            />
                                            <ErrorMessage name="studioFilm" component={"p"} style={{color: "red"}}/>
                                        </div>
                                    </div>
                                    <div className="row" style={{marginBottom: "2%"}}>
                                        <div className="col-3" style={{textAlign: "right"}}>
                                            <label className="fw-bold" style={{marginRight: "2%", display: "flex" , float: "right", marginTop: "3%"}}>
                                                Đạo diễn <span className="warning">(*)</span>
                                            </label>
                                        </div>
                                        <div className="col-8">
                                            <Field
                                                type="text"
                                                style={{width: "100%"}}
                                                name="director"
                                            />
                                            <ErrorMessage name="director" component={"p"} style={{color: "red"}}/>
                                        </div>
                                    </div>
                                    <div className="row" style={{marginBottom: "2%"}}>
                                        <div className="col-3" style={{textAlign: "right"}}>
                                            <label className="fw-bold" style={{marginRight: "2%", display: "flex" , float: "right", marginTop: "3%"}}>
                                                Thời lượng <span className="warning">(*)</span>
                                            </label>
                                        </div>
                                        <div className="col-8">
                                            <Field
                                                type="number"
                                                style={{width: "100%"}}
                                                name="timeFilm"
                                            />
                                            <ErrorMessage name="timeFilm" component={"p"} style={{color: "red"}}/>
                                        </div>
                                    </div>
                                    <div className="row" style={{marginBottom: "2%"}}>
                                        <div className="col-3" style={{textAlign: "right"}}>
                                            <label className="fw-bold" style={{marginRight: "2%", display: "flex" , float: "right", marginTop: "3%"}}>
                                                Nhãn phim <span className="warning">(*)</span>
                                            </label>
                                        </div>
                                        <div className="col-8">
                                            <Field
                                                type="text"
                                                style={{width: "100%"}}
                                                name="movieLabel"
                                            />
                                            <ErrorMessage name="movieLabel" component={"p"} style={{color: "red"}}/>
                                        </div>
                                    </div>
                                    <div className="row" style={{marginBottom: "2%"}}>
                                        <div className="col-3" style={{textAlign: "right"}}>
                                            <label className="fw-bold" style={{marginRight: "2%", display: "flex" , float: "right", marginTop: "3%"}}>
                                                Trailer <span className="warning">(*)</span>
                                            </label>
                                        </div>
                                        <div className="col-8">
                                            <Field
                                                type="text"
                                                style={{width: "100%"}}
                                                name="trailer"
                                            />
                                            <ErrorMessage name="trailer" component={"p"} style={{color: "red"}}/>
                                        </div>
                                    </div>
                                    <div className="row kind-movie" style={{marginBottom: "2%"}}>
                                        <div className="title-kind col-3" style={{textAlign: "right"}}>
                                            <label className="fw-bold" style={{marginRight: "2%", display: "flex" , float: "right", marginTop: "3%"}}>
                                                Thể loại <span className="warning">(*)</span>
                                            </label>
                                        </div>
                                        <div className="col-8">
                                            <Field as='select' name="idTypeFilm" style={{width:"100%", border:"1px solid #ced0da"}}>
                                                {listTypeFilm.map((listType, index) => (
                                                    <option
                                                        value={listType.idTypeFilm}>{listType.nameTypeFilm}</option>
                                                ))}
                                            </Field>
                                        </div>
                                    </div>
                                    <div className="row" style={{marginBottom: "2%"}}>
                                        <div className="col-3" style={{textAlign: "right"}}>
                                            <label className="fw-bold" style={{marginRight: "2%", display: "flex" , float: "right", marginTop: "3%"}}>
                                                Giá ghế thường <span className="warning">(*)</span>
                                            </label>
                                        </div>
                                        <div className="col-8">
                                            <Field
                                                type="number"
                                                style={{width: "100%"}}
                                                name="normalSeatPrice"
                                            />
                                            <ErrorMessage name="normalSeatPrice" component={"p"}
                                                          style={{color: "red"}}/>
                                        </div>
                                    </div>
                                    <div className="row" style={{marginBottom: "2%"}}>
                                        <div className="col-3" style={{textAlign: "right"}}>
                                            <label className="fw-bold" style={{marginRight: "2%", display: "flex" , float: "right", marginTop: "3%"}}>
                                                Giá ghế vip <span className="warning">(*)</span>
                                            </label>
                                        </div>
                                        <div className="col-8">
                                            <Field
                                                type="number"
                                                style={{width: "100%"}}
                                                name="vipSeatPrice"
                                            />
                                            <ErrorMessage name="vipSeatPrice" component={"p"} style={{color: "red"}}/>
                                        </div>
                                    </div>
                                    <div className="row" style={{marginBottom: "2%"}}>
                                        <div className="col-3" style={{textAlign: "right"}}>
                                            <label className="fw-bold" style={{marginRight: "2%", display: "flex" , float: "right", marginTop: "3%"}}>
                                                Nội dung <span className="warning">(*)</span>
                                            </label>
                                        </div>
                                        <div className="col-8">
                                            <Field
                                                as="textarea"
                                                className="form-control form-control-md"
                                                type="text"
                                                row={4}
                                                col={86}
                                                style={{maxWidth:"100%"}}
                                                name="describeFilm"
                                            />
                                            <ErrorMessage name="describeFilm" component={"p"} style={{color: "red"}}/>
                                        </div>
                                    </div>
                                    <div className="row" style={{marginBottom: "2%"}}>
                                        <div className="col-3" style={{textAlign: "right"}}>
                                            <Field type="hidden"/>
                                        </div>
                                        <div className="col-8">
                                            {
                                                isSubmitting ?
                                                    <ColorRing
                                                        visible={true}
                                                        height="80"
                                                        width="80"
                                                        ariaLabel="blocks-loading"
                                                        wrapperStyle={{}}
                                                        wrapperClass="blocks-wrapper"
                                                        colors={['#e15b64', '#f47e60', '#f8b26a', '#abbd81', '#849b87']}
                                                    />
                                                    :
                                                    <button
                                                        type="submit"
                                                        className="btn btn-primary"
                                                        style={{background: "#f26b38"}}
                                                    >
                                                        Thêm mới
                                                    </button>
                                            }
                                            {/*<button*/}
                                            {/*    type="reset"*/}
                                            {/*    className="btn btn-primary"*/}
                                            {/*    style={{background: "black", color: "white", marginLeft: "0%"}}*/}
                                            {/*>*/}
                                            {/*    Quay lại*/}
                                            {/*</button>*/}
                                        </div>
                                    </div>
                                </Form>
                            </div>
                        </div>
                    </div>
                )
            }
        < /Formik>
    )
}
