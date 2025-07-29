import * as DiscountService from "../../service/discount/DiscountService";
import * as Yup from "yup";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router";
import { ErrorMessage, Field, Form, Formik } from "formik";
import { getDownloadURL, ref, uploadBytesResumable } from "firebase/storage";

import "react-toastify/dist/ReactToastify.css";
import { ToastContainer, toast } from "react-toastify";
import { storage } from "../../firebase";
import Header from "../common/header/Header";
import Footer from "../common/footer/Footer";
export default function DiscountUpdate() {
  useEffect(() => {
    document.title = "Chỉnh sửa khuyến mãi";
  }, []);
  const [flag, setFlag] = useState(false);
  const [discount, setDiscount] = useState(null);
  const [img, setImg] = useState("");
  const [selectedFile, setSelectedFile] = useState(null);
  const [progress, setProgress] = useState(0);
  const [imgErr, setImgErr] = useState("");
  const auth = localStorage.getItem("token");
  const handleSelectFile = (event) => {
    const file = event.target.files[0];
    setFlag(true);
    setImgErr("");
    if (file) {
      setSelectedFile(file);
    }
  };
  const handleSubmitImg = async () => {
    return new Promise((resolve, reject) => {
      const file = selectedFile;
      if (!file) {
        return reject("Chưa có file ảnh được chọn");
      }
      const storageRef = ref(storage, `files/${file.name}`);
      const uploadTask = uploadBytesResumable(storageRef, file);
      uploadTask.on(
        "state_changed",
        ((snapshot) => {
          const progress = Math.round(
            (snapshot.bytesTransferred / snapshot.totalBytes) * 100
          );
          setProgress(progress);
        },
        (error) => {
          reject(error);
        },
        async () => {
          try {
            const downloadUrl = await getDownloadURL(uploadTask.snapshot.ref);
            console.log(downloadUrl);
            setImg(downloadUrl);
            resolve(downloadUrl);
          } catch (e) {
            setImgErr(e.response.data[0].defaultMessage);
          }
        })
      );
    });
  };
  const param = useParams();

  const findDiscount = async () => {
    const rs = await DiscountService.findDiscountById(param.id, auth);
    setDiscount(rs);
  };

  useEffect(() => {
    findDiscount();
  }, [param.id]);

  const navigate = useNavigate();

  if (!discount) {
    return null;
  }

  return (
    <>
      <Header />{" "}
      <Formik
        enableReinitialize={true}
        initialValues={{
          nameDiscount: discount?.nameDiscount,
          dateStart: discount?.dateStart,
          dateEnd: discount?.dateEnd,
          describeDiscount: discount?.describeDiscount,
          percentDiscount: discount?.percentDiscount,
          idDiscount: param.id,
          imageDiscount: discount?.imageDiscount,
        }}
        validationSchema={Yup.object({
          nameDiscount: Yup.string()
            .trim()
            .required("Tên khuyến mãi không được để trống")
            .max(255, "Tên khuyến mãi không được quá 255 từ"),
          dateStart: Yup.date()
            .required("Ngày bắt đầu không được để trống")
            .min(Yup.ref("dateEnd"), "Ngày bắt đầu phải nhỏ hơn ngày kết thúc")
            .min(
              new Date(Date.now() + 7 * 24 * 60 * 60 * 1000),
              "Ngày bắt đầu phải lớn hơn ngày hiện tại 7 ngày"
            ),
          dateEnd: Yup.date()
            .required("Ngày kết thúc không được để trống")
            .min(
              Yup.ref("dateStart"),
              "Ngày kết thúc phải lớn hơn ngày bắt đầu"
            ),

          describeDiscount: Yup.string()
            .trim()
            .required("Chi tiết khuyến mãi không được để trống")
            .max(255, "Chi tiết khuyến mãi không được quá 255 từ"),
          percentDiscount: Yup.number()
            .required("Phần trăm giảm giá không được để trống")
            .min(0.01, "Phần trăm giảm giá không được nhỏ hơn hoặc bằng 0")
            .max(100, "Phần trăm giảm giá không được lớn hơn 100"),
        })}
        onSubmit={async (values, { setSubmitting }) => {
          const newValue = {
            ...values,
            imageDiscount: img,
          };
          try {
            if (flag) {
              newValue.imageDiscount = await handleSubmitImg();
              await DiscountService.updateDiscount(newValue, auth);
            } else {
              await DiscountService.updateDiscount(values, auth);
              console.log(values);
            }
            toast(`Chỉnh sửa khuyến mãi thành công! `);
            navigate(`/admin/discount/list`);
            setSubmitting(false);
          } catch (e) {
            setImgErr(e.response.data[0].defaultMessage);
          }
        }}
      >
        <Form className="pt-5 mb-5">
          <div className="container">
            <div className="row mx-0">
              <div className="col-md-7 m-auto shadow border border-1 mt-5 px-0 col-12">
                <div className="p-5">
                  <table className="w-100">
                    <thead>
                      <tr
                        style={{
                          background: "#f26b38",
                          paddingRight: 0,
                          marginLeft: 0,
                        }}
                      >
                        <th
                          className="title-font"
                          style={{
                            fontSize: 24,
                            fontWeight: 600,
                            color: "#fff",
                            textAlign: "center",
                          }}
                          colSpan={2}
                        >
                          Chỉnh sửa khuyến mãi
                        </th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr className="">
                        <td className="" style={{ alignItems: "end" }}>
                          <label
                            className="normal-font float-end"
                            htmlFor="name"
                            style={{ marginRight: 15 }}
                          >
                            Tiêu đề{" "}
                            <span style={{ color: "red", fontSize: 20 }}>
                              *
                            </span>
                          </label>
                        </td>
                        <td className="">
                          <Field name="idDiscount" type="hidden" />
                          <Field
                            name="nameDiscount"
                            className="form-control"
                            id="name"
                            placeholder=""
                            type="text"
                          />
                          <ErrorMessage
                            name="nameDiscount"
                            component="span"
                            className="text-danger"
                          />
                        </td>
                      </tr>
                      <tr className="">
                        <td className="">
                          <label
                            className="normal-font float-end"
                            htmlFor="startTime"
                            style={{ marginRight: 15 }}
                          >
                            Thời gian bắt đầu{" "}
                            <span style={{ color: "red" }}>*</span>
                          </label>
                        </td>
                        <td className="">
                          <Field
                            name="dateStart"
                            className="form-control"
                            id="startTime"
                            type="date"
                          />
                          <ErrorMessage
                            name="dateStart"
                            component="span"
                            className="text-danger"
                          />
                        </td>
                      </tr>
                      <tr className="">
                        <td className="">
                          <label
                            className="normal-font float-end"
                            htmlFor="endTime"
                            style={{ marginRight: 15 }}
                          >
                            Thời gian kết thúc{" "}
                            <span style={{ color: "red" }}>*</span>
                          </label>
                        </td>
                        <td className="">
                          <Field
                            name="dateEnd"
                            className="form-control"
                            id="endTime"
                            placeholder=""
                            type="date"
                          />
                          <ErrorMessage
                            name="dateEnd"
                            component="span"
                            className="text-danger"
                          />
                        </td>
                      </tr>
                      <tr className="">
                        <td className="">
                          <label
                            className="normal-font float-end"
                            style={{ marginRight: 15 }}
                          >
                            Mức giảm giá (%)
                            <span style={{ color: "red" }}>*</span>
                          </label>
                        </td>
                        <td className="">
                          <Field
                            name="percentDiscount"
                            className="form-control float-start"
                            placeholder=""
                            style={{ width: "100%" }}
                            type="number"
                          />
                          <ErrorMessage
                            name="percentDiscount"
                            component="span"
                            className="text-danger"
                          />
                        </td>
                      </tr>
                      <tr className="">
                        <td className="">
                          <label
                            className="normal-font float-end"
                            htmlFor="img"
                            style={{ marginRight: 15 }}
                          >
                            Hình ảnh <span style={{ color: "red" }}>*</span>
                          </label>
                        </td>
                        <td className="">
                          <Field
                            type="file"
                            onChange={(e) => handleSelectFile(e)}
                            className="form-control-plaintext d-none "
                            id="img"
                            name={"img"}
                          />
                          <ErrorMessage
                            name="img"
                            component="span"
                            className="text-danger"
                          />
                          <label
                            htmlFor="img"
                            style={{
                              padding: "6px 12px",
                              marginTop: "12px",
                              border: "1px solid",
                              borderRadius: "4px",
                            }}
                          >
                            Chọn hình ảnh
                          </label>
                          <br />

                          {!selectedFile && (
                            <img
                              className={"mt-2"}
                              src={discount?.imageDiscount}
                              style={{ width: "50%" }}
                            />
                          )}
                          {selectedFile && (
                            <img
                              className={"mt-2"}
                              src={URL.createObjectURL(selectedFile)}
                              style={{ width: "50%" }}
                            />
                          )}
                          <span className={"text-danger"}>{imgErr}</span>
                        </td>
                      </tr>
                      <tr className="">
                        <td className="align-top">
                          <div className="float-end">
                            <label
                              className="normal-font align-top"
                              htmlFor="detail"
                              style={{ marginRight: 15, marginTop: 10 }}
                            >
                              Chi tiết <span style={{ color: "red" }}>*</span>
                            </label>
                          </div>
                        </td>
                        <td className="">
                          <Field
                            style={{ height: 130, marginTop: 10 }}
                            className="form-control"
                            id="detail"
                            placeholder=""
                            type="textarea"
                            name={"describeDiscount"}
                          />
                          <ErrorMessage
                            name="describeDiscount"
                            component="span"
                            className="text-danger"
                          />
                        </td>
                      </tr>
                      <tr style={{ height: 120 }}>
                        <td colSpan={2}>
                          <button
                            onClick={(event) => {
                              navigate("/discount-list");
                            }}
                            className="btn btn-secondary float-end"
                            style={{
                              width: "20%",
                              background: "black",
                              color: "white",
                              marginLeft: "3%",
                            }}
                          >
                            Quay lại
                          </button>
                          <button
                            type="submit"
                            className="btn btn-primary float-end"
                            style={{ background: "#f26b38" }}
                          >
                            Xác nhận
                          </button>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </Form>
      </Formik>
      <Footer />
    </>
  );
}
