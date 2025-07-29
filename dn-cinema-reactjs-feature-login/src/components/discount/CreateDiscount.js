import * as DiscountService from "../../service/discount/DiscountService";
import * as Yup from "yup";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router";
import { ErrorMessage, Field, Form, Formik } from "formik";
import { getDownloadURL, ref, uploadBytesResumable } from "firebase/storage";

import "react-toastify/dist/ReactToastify.css";
import { ToastContainer, toast } from "react-toastify";
import discountStyle from "./DiscountFormCss.module.css";
import { storage } from "../../firebase";
import Header from "../common/header/Header";
import Footer from "../common/footer/Footer";

export default function DiscountCreate() {
  const auth = localStorage.getItem("token");
  useEffect(() => {
    document.title = "Thêm mới khuyến mãi";
  }, []);
  const [img, setImg] = useState(null);
  const [selectedFile, setSelectedFile] = useState(null);
  const [progress, setProgress] = useState(0);
  const navigate = useNavigate();
  const handleSelectFile = (event) => {
    const file = event.target.files[0];
    setImgErr("");
    if (file) {
      setSelectedFile(file);
    }
  };
  const [imgErr, setImgErr] = useState("");
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
            setImg(downloadUrl);
            resolve(downloadUrl);
          } catch (e) {
            console.log(e);
          }
        })
      );
    });
  };

  return (
    <>
      <Header />
      <Formik
        initialValues={{
          nameDiscount: "",
          dateStart: "",
          dateEnd: "",
          describeDiscount: "",
          percentDiscount: "",
          imageDiscount: "",
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
            .max(1000, "Chi tiết khuyến mãi không được quá 1000 từ"),
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
            newValue.imageDiscount = await handleSubmitImg();
            await DiscountService.createDiscount(newValue, auth);
            toast(`Thêm khuyến mãi thành công! `);
            navigate("/admin/discount/list");
            setSubmitting(false);
          } catch (e) {
            setImgErr(e.response.data[0].defaultMessage);
          }
        }}
      >
        <Form>
          <div className="container" style={{ marginTop: "80px" }}>
            <div className="row mx-0">
              <div className="col-md-7 m-auto mt-5 px-0 col-12">
                <div className="p-5">
                  <table className="w-100">
                    <thead>
                      <tr>
                        <th colSpan={2}>
                          <h1
                            style={{
                              textAlign: "center",
                              marginBottom: "5%",
                              fontSize: "2.5rem",
                            }}
                          >
                            THÊM MỚI KHUYẾN MÃI
                          </h1>
                        </th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr className="">
                        <td className="row" style={{ alignItems: "end" }}>
                          <label
                            htmlFor="account"
                            className="fw-bold"
                            style={{ marginRight: "2%" }}
                          >
                            Tiêu đề
                            <span style={{ color: "red" }}>(*)</span>
                          </label>
                        </td>
                        <td style={{ width: "60%" }}>
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
                            htmlFor="account"
                            className="fw-bold"
                            style={{ marginRight: "2%" }}
                          >
                            Thời gian bắt đầu
                            <span style={{ color: "red" }}>(*)</span>
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
                            htmlFor="account"
                            className="fw-bold"
                            style={{ marginRight: "2%" }}
                          >
                            Thời gian kết thúc
                            <span style={{ color: "red" }}>(*)</span>
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
                            htmlFor="account"
                            className="fw-bold"
                            style={{ marginRight: "2%" }}
                          >
                            Mức giảm giá
                            <span style={{ color: "red" }}>(*)</span>
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
                            htmlFor="account"
                            className="fw-bold"
                            style={{ marginRight: "2%" }}
                          >
                            Hình ảnh
                            <span style={{ color: "red" }}>(*)</span>
                          </label>
                        </td>
                        <td className="">
                          <Field
                            type="file"
                            onChange={handleSelectFile}
                            name="imageDiscount"
                            className="form-control-plaintext d-none "
                            id="img"
                          />
                          <ErrorMessage
                            name="imageDiscount"
                            component="span"
                            className="text-danger"
                          />
                          <p>
                            <label
                              htmlFor="img"
                              style={{
                                display: "inline-block",
                                padding: "6px 12px",
                                marginTop: "12px",
                                border: "1px solid",
                                borderRadius: "4px",
                              }}
                            >
                              Chọn hình ảnh
                            </label>
                          </p>
                          {!selectedFile && (
                            <span className={"mt-2"}>
                              Chưa có hình ảnh được chọn
                            </span>
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
                          <div className="">
                            <label
                              htmlFor="account"
                              className="fw-bold"
                              style={{ marginRight: "2%" }}
                            >
                              Chi tiết
                              <span style={{ color: "red" }}>(*)</span>
                            </label>
                          </div>
                        </td>
                        <td className="">
                          <Field
                            style={{ height: 130, marginTop: 10 }}
                            className="form-control"
                            id="detail"
                            placeholder=""
                            type="text"
                            name="describeDiscount"
                          />
                          <ErrorMessage
                            name="describeDiscount"
                            component="span"
                            className="text-danger"
                          />
                        </td>
                      </tr>
                      <tr style={{ height: 120 }}>
                        <td></td>
                        <td style={{ textAlign: "center" }}>
                          <button
                            onClick={(event) => {
                              navigate("/discount-list");
                            }}
                            className="btn btn-secondary"
                            style={{
                              width: "120px",
                              background: "rgb(242, 107, 56)",
                              color: "white",
                              marginRight: "10px",
                              border: "none",
                            }}
                          >
                            Quay lại
                          </button>
                          <button
                            type="submit"
                            className="btn btn-primary"
                            style={{ background: "rgb(242, 107, 56)" }}
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
