import React, { useEffect, useState } from "react";
import "../customer/customer.css";
import { getDownloadURL, ref, uploadBytesResumable } from "firebase/storage";
import { storage } from "../../firebase";
import { ErrorMessage, Field, Form, Formik } from "formik";
import * as Yup from "yup";
import { useNavigate } from "react-router";
import { toast } from "react-toastify";
import { ColorRing } from "react-loader-spinner";
import {
  checkEmailExists,
  checkIdentityCardExists,
  checkPhoneExists,
  checkUsernameExists,
  findAllCustomerType,
  saveCustomer,
} from "../../service/CustomerServiceTruongNN";

export function CreateCustomerAccount() {
  const [selectedFile, setSelectedFile] = useState(null);
  const [firebaseImg, setImg] = useState(null);
  const [progress, setProgress] = useState(0);
  const [typeCustomer, setTypeCustomer] = useState([]);
  const [imgErr, setImgErr] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    const list = async () => {
      setTypeCustomer(await findAllCustomerType());
    };
    list();
  }, []);

  const handleFileSelect = (event) => {
    const file = event.target.files[0];
    setImgErr("");
    if (file) {
      setSelectedFile(file);
    }
  };

  const handleSubmitAsync = async () => {
    return new Promise((resolve, reject) => {
      const file = selectedFile;
      if (!file) return reject("Ch?a có file ?nh nào ???c ch?n");
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

  return (
    <>
      <Formik
        initialValues={{
          accountUser: { nameAccount: "", passwordAccount: "" },
          nameCustomer: "",
          dateOfBirth: "",
          pointCustomer: "0.0",
          gender: "",
          phone: "",
          address: "",
          email: "",
          identityCard: "",
          imgCustomer: "",
          typeCustomer: {
            idTypeCustomer: 1,
          },
        }}
        validationSchema={Yup.object({
          accountUser: Yup.object().shape({
            nameAccount: Yup.string()
              .required("Vui lòng nhập tên tài khoản")
              .min(4, "Tên tài khoản quá ngắn, phải từ 4 ký tự")
              .max(100, "Tên tài khoản quá dài")
              .trim()
              .matches(
                /^[a-zA-Z0-9]{4,100}$/,
                "Tên tài khoản không chứa dấu, kí tự đặc biệt và khoảng cách"
              )
              .test(
                "check-username",
                "Tài khoản đã tồn tại",
                async function (value) {
                  try {
                    if (!value) {
                      return true;
                    }
                    const isUsernameExists = await checkUsernameExists(value);
                    console.log(isUsernameExists);
                    return !isUsernameExists;
                  } catch (error) {
                    console.log(error);
                  }
                }
              ),
            passwordAccount: Yup.string()
              .trim()
              .min(8, "Mật khẩu ít nhất 8 ký tự")
              .max(28, "Mật khẩu tối đa 28 ký tự")
              .matches(
                /^[^\s!#$%^&*()]+$/,
                "Mật khẩu không chứa khoảng cách , dấu và các kí tự đặc biệt trừ @"
              )
              .required("Vui lòng nhập mật khẩu tài khoản"),
            againPasswordAccount: Yup.string()
              .required("Vui lòng nhập lại mật khẩu")
              .oneOf([Yup.ref("passwordAccount")], "Mật khẩu không trùng khớp"),
          }),

          nameCustomer: Yup.string()
            .trim()
            .required("Vui lòng nhập họ tên")
            .min(4, "Tên tài khoản quá ngắn, phải từ 4 ký tự")
            .max(100, "Tên tài khoản quá dài")
            .matches(
              /^(?=.*[a-zA-Z\s])[^!@#$%^&*(),.?":{}|<>]{4,100}$/,
              "Tên phải có độ dài từ 4 ký tự, không chứa ký tự đặc biệt"
            ),
          dateOfBirth: Yup.date()
            .required("Vui lòng chọn ngày sinh")
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
          phone: Yup.string()
            .required("Vui lòng nhập số điện thoại")
            .matches(
              /^(\+?84|0)(3[2-9]|5[2689]|7[06-9]|8[1-9]|9[0-9])[0-9]{7}$/,
              "Số điện thoại không hợp lệ, phải từ 10 hoặc 11 số"
            )
            .test(
              "check-phone",
              "Số điện thoại đã tồn tại",
              async function (value) {
                if (!value) {
                  return true; // Không ki?m tra n?u không có giá tr?
                }
                const isUsernameExists = await checkPhoneExists(value);
                return !isUsernameExists;
              }
            ),
          address: Yup.string()
            .required("Vui lòng nhập địa chỉ")
            .matches(
              /^[^!@#$%^&*()+=\[\]{};':"\\|.<>?`~]+$/,
              "Địa chỉ không chứa ký tự đặc biệt trừ /"
            )
            .min(4, "Địa chỉ quá ngắn")
            .max(100, "Địa chỉ quá dài"),
          email: Yup.string()
            .min(12, "Email ít nhất 12 ký tự")
            .max(32, "Email tối đa 32 ký tự")
            .matches(
              /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/,
              "Email phải đúng định dạng xxx@gmail.com"
            )
            .required("Vui lòng nhập email")
            .test("check-email", "Email đã tồn tại", async function (value) {
              if (!value) {
                return true; // Không ki?m tra n?u không có giá tr?
              }

              const isUsernameExists = await checkEmailExists(value);
              return !isUsernameExists;
            }),
          identityCard: Yup.string()
            .required("Vui lòng nhập CCCD")
            .matches(/^[0-9]{12}$/, "CCCD phải là 12 ký tự số")
            .test(
              "check-identityCard",
              "CCCD đã tồn tại",
              async function (value) {
                if (!value) {
                  return true; // Không ki?m tra n?u không có giá tr?
                }
                const isUsernameExists = await checkIdentityCardExists(value);
                return !isUsernameExists;
              }
            ),
        })}
        onSubmit={(values, { resetForm }) => {
          const create = async () => {
            const newValue = {
              ...values,
              imgCustomer: firebaseImg,
              customerType: parseInt(values.customerType),
            };
            try {
              newValue.imgCustomer = await handleSubmitAsync();
              await saveCustomer(newValue);
              toast(`Đăng kí tài khoản thành công, đăng nhập để tiếp tục`);
              navigate("/login");
              resetForm(false);
            } catch (e) {
              setImgErr(e.response.data[0].defaultMessage);
              toast(`Đăng kí thất bại`);
              resetForm(true);
            }
          };
          create();
        }}
      >
        <Form>
          <div className="container py-5 h-100">
            <div className="row justify-content-center align-items-center h-100">
              <div
                className="column-gap-lg-3"
                style={{ width: "20%", marginBottom: "21%" }}
              >
                {selectedFile && (
                  <img
                    className={"mt-2"}
                    src={URL.createObjectURL(selectedFile)}
                    style={{ width: "100%" }}
                  />
                )}
              </div>
              <div className="col-12 col-lg-9 col-xl-7">
                <div
                  className="card shadow-2-strong card-registration"
                  style={{ borderRadius: 15 }}
                >
                  <div className="card-body p-4 p-md-5">
                    <h3 className="mb-4 pb-2 pb-md-0 mb-md-5">
                      Đăng ký tài khoản
                    </h3>
                    <div className="row">
                      <div className="col-md-6 mb-4">
                        <div className="form-outline">
                          <Field
                            type="file"
                            onChange={(e) => handleFileSelect(e)}
                            id="imgCustomer"
                            name={"imgCustomer"}
                            className="form-control-plaintext d-none "
                          />
                          <p>
                            <label
                              htmlFor="imgCustomer"
                              style={{
                                display: "flex",
                                padding: "6px 12px",
                                border: "1px solid",
                                borderRadius: "4px",
                              }}
                            >
                              Chọn hình ảnh
                            </label>
                          </p>
                          {!selectedFile && (
                            <span className={"mt-2 text-danger"}>
                              Chưa có hình ảnh được chọn
                            </span>
                          )}
                        </div>
                      </div>
                      <div className="col-md-6 mb-4">
                        <div className="inputBox">
                          <Field
                            type="text"
                            className="input"
                            style={{ width: "100%", height: "90%" }}
                            name="accountUser.nameAccount"
                          />
                          <div>
                            <ErrorMessage
                              name="accountUser.nameAccount"
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
                            Tài khoản
                          </label>
                        </div>
                      </div>
                    </div>

                    <div className="row">
                      <div className="col-md-6 mb-4 d-flex align-items-center">
                        <div className="form-outline datepicker w-100">
                          <div className="inputBox">
                            <Field
                              type="password"
                              className="input"
                              style={{ width: "100%", height: "90%" }}
                              name="accountUser.passwordAccount"
                            />
                            <div>
                              <ErrorMessage
                                name="accountUser.passwordAccount"
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
                              Mật khẩu
                            </label>
                          </div>
                        </div>
                      </div>
                      <div className="col-md-6 mb-4">
                        <div className="inputBox">
                          <Field
                            type="password"
                            className="input"
                            style={{ width: "100%", height: "90%" }}
                            name="accountUser.againPasswordAccount"
                          />
                          <div>
                            <ErrorMessage
                              name="accountUser.againPasswordAccount"
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
                            Xác nhận mật khẩu
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
                        <span style={{ marginTop: "1%", marginRight: "-10%" }}>
                          Nam
                        </span>
                        <Field
                          type="radio"
                          value="Nữ"
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
                        <label
                          className="labelInput1"
                          style={{
                            marginLeft: "2%",
                            marginTop: "-5%",
                            backgroundColor: "white",
                            color: "black",
                            top: "15%",
                          }}
                        >
                          Địa chỉ
                        </label>
                      </div>
                    </div>

                    <div className="row" style={{ marginBottom: "2%" }}>
                      <div className="col-3" style={{ textAlign: "right" }}>
                        <Field type="hidden" />
                      </div>
                      <div className="col-8" style={{ marginTop: "7%" }}>
                        <button
                          type="submit"
                          className="btn btn-primary"
                          style={{ background: "#f26b38" }}
                        >
                          Đăng ký
                        </button>
                        <button
                          type="reset"
                          className="btn btn-primary"
                          style={{
                            background: "black",
                            color: "white",
                            marginLeft: "3%",
                          }}
                        >
                          Hủy
                        </button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </Form>
      </Formik>
    </>
  );
}
