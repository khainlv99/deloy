import React, { useEffect, useState } from "react";
import { useParams } from "react-router";
import { storage } from "../../firebase";
import { getDownloadURL, ref, uploadBytesResumable } from "@firebase/storage";
import * as Yup from "yup";
import { ErrorMessage, Field, Form, Formik } from "formik";
import { Link } from "react-router-dom";
import { ColorRing } from "react-loader-spinner";
import { toast } from "react-toastify";
import {
  editCustomerAccount,
  findByIdAccount,
  findCustomerByNameAccount,
} from "../../service/CustomerServiceTruongNN";
import Header from "../common/header/Header";
import Footer from "../common/footer/Footer";

export function UpdateCustomerAccount() {
  const [customer, setCustomer] = useState(null);
  const param = useParams();
  const [user, setUser] = useState(null);
  const username = localStorage.getItem("username");
  const token = localStorage.getItem("token");
  useEffect(() => {
    document.title = "Quản lý tài khoản";
    const fetchApi = async () => {
      const result = await findByIdAccount(param.id, token);
      console.log(result);
      setCustomer(result);
    };
    fetchApi();
    const findCustomerByUsername = async () => {
      const result = await findCustomerByNameAccount(username);
      setUser(result);
    };
    findCustomerByUsername();
  }, []);

  if (!customer) {
    return null;
  }
  return (
    <>
      <Header />
      <Formik
        initialValues={{
          idCustomer: customer?.idCustomer,
          accountUser: {
            id: customer?.accountUser?.id,
            nameAccount: customer?.accountUser?.nameAccount,
            passwordAccount: customer?.accountUser?.passwordAccount,
          },
          nameCustomer: customer?.nameCustomer,
          dateOfBirth: customer?.dateOfBirth,
          pointCustomer: customer?.pointCustomer,
          gender: customer?.gender,
          email: customer?.email,
          identityCard: customer?.identityCard,
          address: customer?.address,
          phone: customer?.phone,
          imgCustomer: customer?.imgCustomer,
          typeCustomer: customer?.typeCustomer,
        }}
        validationSchema={Yup.object({
          nameCustomer: Yup.string()
            .trim()
            .required("Vui lòng  nhập họ tên")
            .min(4, "Tên tài khoản quá ngắn, phải từ 4 ký tự")
            .max(100, "Tên tài khoản quá dài")
            .matches(
              /^(?=.*[a-zA-Z\s])[^!@#$%^&*(),.?":{}|<>]{4,100}$/,
              "Tên phải có độ dài từ 4 ký tự, không chứa ký tự đặc biệt"
            ),
          dateOfBirth: Yup.date()
            .required("Vui lòng nhập ngày sinh")
            .test("is-over-16", "Bạn chưa đủ 16 tuổi", function (value) {
              const currentDate = new Date();
              const selectedDate = new Date(value);
              const ageDiff =
                currentDate.getFullYear() - selectedDate.getFullYear();
              if (ageDiff < 16) {
                return false;
              }
              return true;
            }),
          email: Yup.string()
            .min(12, "Email ít nhất 12 ký tự")
            .max(32, "Email tối đa 32 ký tự")
            .matches(
              /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/,
              "Email phải đúng định dạng xxx@gmail.com"
            )
            .required("Vui lòng nhập email"),
          identityCard: Yup.string()
            .required("Vui lòng nhập số CCCD")
            .matches(/^[0-9]{12}$/, "CCCD phải là 12 ký tự số"),
          address: Yup.string().required("Vui lòng nhập địa chỉ"),
          phone: Yup.string()
            .required("Vui lòng nhập số điện thoại")
            .matches(
              /^(\+?84|0)(3[2-9]|5[2689]|7[06-9]|8[1-9]|9[0-9])[0-9]{7}$/,
              "Số điện thoại không hợp lệ"
            ),
        })}
        onSubmit={(values, { resetForm }) => {
          const updateCustomer = async () => {
            const newValue = {
              ...values,
              customerType: parseInt(values.customerType),
            };
            editCustomerAccount(newValue, token);
            toast("Chỉnh sửa thông tin tài khoản thành công");
            resetForm(false);
          };
          updateCustomer();
        }}
      >
        <div className="container-fluid" style={{ margin: "150px 0" }}>
          <div className="row">
            <div className="col-3 side-bar">
              <h2 style={{ textAlign: "center", fontSize: "30px" }}>
                Quản lý tài khoản
              </h2>
              <p className="text-center">
                <img
                  src={
                    "https://static2.yan.vn/YanNews/2167221/202102/facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg"
                  }
                  className="rounded-circle avatar"
                  height="200px"
                  style={{
                    height: "100px",
                    width: "100px",
                    margin: "0 auto",
                    border: "1px solid",
                  }}
                />
              </p>
              <p style={{ textAlign: "center", fontSize: 25 }}>
                {customer?.accountUser?.nameAccount}
              </p>
              <div className="mt-3" style={{ textAlign: "center" }}>
                <i className="bi bi-bookmark-star-fill" />
                Điểm tích lũy : 120
              </div>
              <div className="mt-3" style={{ textAlign: "center" }}>
                <Link to="/login" className="log-out btn btn-outline-danger">
                  Đăng xuất
                </Link>
              </div>
              <hr />
              {user && (
                <Link
                  to={"/customer/change-information/" + user.idCustomer}
                  className="mt-2"
                  style={{ color: "black" }}
                >
                  <link href="" style={{ fontSize: 14 }} />
                  <i className="bi bi-person-bounding-box" />
                  Thông tin tài khoản
                </Link>
              )}
              <hr />
              <Link
                to={"/ticket-customer/history"}
                className="mt-2"
                style={{ color: "black" }}
              >
                <link style={{ fontSize: 14 }} />
                <i className="bi bi-calculator" />
                Lịch sử
              </Link>
              <hr />
              <Link
                to={"/ticket-customer"}
                className="mt-2"
                style={{ color: "black" }}
              >
                <link href="" style={{ fontSize: 14 }} />
                <i className="bi bi-ticket-detailed" />
                Vé đã đặt
              </Link>
            </div>
            <div className=" container mx-auto my-5 col-md-7">
              <div
                className="col-12 col-lg-9 col-xl-7"
                style={{ width: "100%" }}
              >
                <div className="card shadow-2-strong card-registration">
                  <Form>
                    <div
                      className="card-body p-4 p-md-6"
                      style={{ marginTop: "-3%" }}
                    >
                      <h1
                        className="mb-4 pb-2 pb-md-0 mb-md-5"
                        style={{ textAlign: "center" }}
                      >
                        Chỉnh sửa thông tin tài khoản
                      </h1>
                      <div className="row">
                        <div className="col-md-6 mb-4">
                          <div className="inputBox">
                            <Field
                              type="text"
                              className="input"
                              style={{ width: "100%", height: "90%" }}
                              name="nameCustomer"
                            />
                            <div>
                              <ErrorMessage
                                name="nameCustomer"
                                component={"p"}
                                style={{ color: "red" }}
                              />
                            </div>

                            <label
                              className="labelInput"
                              style={{
                                marginLeft: "2%",
                                marginTop: "-6%",
                                backgroundColor: "white",
                                color: "black",
                              }}
                            >
                              Họ tên
                            </label>
                          </div>
                        </div>
                        <div className="col-md-6 mb-4">
                          <div className="inputBox">
                            <Field
                              type="date"
                              className="input"
                              style={{ width: "100%", height: "90%" }}
                              name="dateOfBirth"
                            />
                            <div>
                              <ErrorMessage
                                name="dateOfBirth"
                                component={"p"}
                                style={{ color: "red" }}
                              />
                            </div>
                            <label
                              className="labelInput"
                              style={{
                                marginLeft: "2%",
                                marginTop: "-6%",
                                backgroundColor: "white",
                                color: "black",
                              }}
                            >
                              Ngày sinh
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
                              name="identityCard"
                            />
                            <div>
                              <ErrorMessage
                                name="identityCard"
                                component={"p"}
                                style={{ color: "red" }}
                              />
                            </div>
                            <label
                              className="labelInput"
                              style={{
                                marginLeft: "2%",
                                marginTop: "-6%",
                                backgroundColor: "white",
                                color: "black",
                              }}
                            >
                              CCCD
                            </label>
                          </div>
                        </div>
                        <div className="col-md-6 mb-4">
                          <div className="inputBox">
                            <Field
                              type="text"
                              className="input"
                              style={{ width: "100%", height: "90%" }}
                              name="email"
                            />
                            <div>
                              <ErrorMessage
                                name="email"
                                component={"p"}
                                style={{ color: "red" }}
                              />
                            </div>
                            <label
                              className="labelInput"
                              style={{
                                marginLeft: "2%",
                                marginTop: "-6%",
                                backgroundColor: "white",
                                color: "black",
                              }}
                            >
                              Email
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
                              name="phone"
                            />
                            <div>
                              <ErrorMessage
                                name="phone"
                                component={"p"}
                                style={{ color: "red" }}
                              />
                            </div>
                            <label
                              className="labelInput"
                              style={{
                                marginLeft: "2%",
                                marginTop: "-6%",
                                backgroundColor: "white",
                                color: "black",
                              }}
                            >
                              Số điện thoại
                            </label>
                          </div>
                        </div>
                        <div
                          className="col-md-6 mb-4"
                          style={{ display: "flex", marginLeft: "-12%" }}
                        >
                          <Field
                            type="radio"
                            value="Nam"
                            name="gender"
                            style={{
                              height: "35%",
                              marginTop: "4%",
                              marginRight: "-17%",
                              marginLeft: "2%",
                            }}
                          />{" "}
                          <span
                            style={{ marginTop: "1%", marginRight: "-10%" }}
                          >
                            Nam
                          </span>
                          <Field
                            type="radio"
                            value="N?"
                            name="gender"
                            style={{
                              height: "35%",
                              marginTop: "4%",
                              marginRight: "-17%",
                              marginLeft: "-10%",
                            }}
                          />{" "}
                          <span style={{ marginTop: "1%" }}>Nữ</span>
                          <ErrorMessage
                            name="gender"
                            component={"p"}
                            style={{ color: "red" }}
                          />
                        </div>
                      </div>

                      <div className="row">
                        <div className="inputBox">
                          <Field
                            type="text"
                            className="input"
                            style={{ width: "100%", height: "39px" }}
                            name="address"
                          />
                          <div>
                            <ErrorMessage
                              name="address"
                              component={"p"}
                              style={{ color: "red" }}
                            />
                          </div>
                        </div>
                      </div>

                      <div className="row" style={{ marginBottom: "2%" }}>
                        <div
                          className="col-12"
                          style={{ marginTop: "4%", textAlign: "center" }}
                        >
                          <button
                            type="submit"
                            className="btn btn-primary"
                            style={{ background: "#f26b38", marginLeft: "7%" }}
                          >
                            Lưu
                          </button>
                          <button
                            type="reset"
                            className="btn btn-primary"
                            style={{
                              background: "black",
                              color: "white",
                              marginLeft: "10px",
                            }}
                          >
                            Hủy
                          </button>
                        </div>
                      </div>
                    </div>
                  </Form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </Formik>
      <Footer />
    </>
  );
}
