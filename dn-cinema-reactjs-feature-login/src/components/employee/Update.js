import * as employeeService from "../../service/employee/employeeService";
import { ErrorMessage, Field, Form, Formik } from "formik";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { getDownloadURL, ref, uploadBytesResumable } from "firebase/storage";
import { storage } from "../../firebase";
import "react-toastify/dist/ReactToastify.css";
import { ToastContainer, toast } from "react-toastify";
import * as Yup from "yup";
import Header from "../common/header/Header";
import Footer from "../common/footer/Footer";

export function UpdateEmployee() {
  const navigate = useNavigate();
  const [employee, setEmployee] = useState();
  const param = useParams();
  const [selectedFile, setSelectedFile] = useState(null);
  const [firebaseImg, setImg] = useState("");
  const [flag, setFlag] = useState(false);
  const [progress, setProgress] = useState(0);
  const [imgErr, setImgErr] = useState("");
  const token = localStorage.getItem("token");

  useEffect(() => {
    const fetchApi = async () => {
      const result = await employeeService.findById(param.id, token);
      setEmployee(result);
    };
    fetchApi();
  }, []);

  const handleFileSelect = (event) => {
    const file = event.target.files[0];
    setFlag(true);
    if (file) {
      setSelectedFile(file);
    }
  };

  const handleSubmitAsync = async () => {
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
            setImgErr(e.response.data[0].defaultMessage);
          }
        })
      );
    });
  };

  if (!employee) {
    return null;
  }
  return (
    <>
      <Header />
      <Formik
        initialValues={{
          idEmployee: employee?.idEmployee,
          imgEmployee: employee?.imgEmployee,
          accountUser: {
            id: employee?.accountUser?.id,
            nameAccount: employee?.accountUser?.nameAccount,
            passwordAccount: "",
          },
          nameEmployee: employee?.nameEmployee,
          dateOfBirth: employee?.dateOfBirth,
          gender: employee?.gender,
          email: employee?.email,
          identityCard: employee?.identityCard,
          phone: employee?.phone,
          address: employee?.address,
        }}
        validationSchema={Yup.object({
          accountUser: Yup.object().shape({
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
              .oneOf([Yup.ref("passwordAccount"), null], "Mật khẩu không khớp")
              .required("Vui lòng nhập lại mật khẩu"),
          }),
          nameEmployee: Yup.string()
            .trim()
            .matches(
              /^[^!@#$%^&*()+=\[\]{};':"\\|.<>?`~/]+$/,
              "Tên không bao gồm kí tự đặc biệt"
            )
            .max(100, "Họ tên tối đa 100 ký tự")
            .required("Vui lòng nhập tên nhân viên"),
          dateOfBirth: Yup.date()
            .required("Vui lòng chọn ngày sinh")
            .test("is-over-18", "Bạn phải trên 18 tuổi", function (value) {
              const currentDate = new Date();
              const selectedDate = new Date(value);
              const ageDiff =
                currentDate.getFullYear() - selectedDate.getFullYear();
              if (ageDiff < 18) {
                return false;
              }
              return true;
            }),
          gender: Yup.string().required("Vui lòng chọn giới tính"),
          email: Yup.string()
            .trim()
            .min(12, "Email ít nhất 12 ký tự")
            .max(32, "Email tối đa 32 ký tự")
            .matches(
              /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/,
              "Email phải đúng định dạng xxx@gmail.com"
            )
            .required("Vui lòng nhập địa chỉ email")
            .test("check-email", "Email đã tồn tại", async function (value) {
              if (value == employee?.email) {
                return true;
              }
              if (!value) {
                return true;
              }
              const isUsernameExists = await employeeService.checkEmailExists(
                value
              );
              return !isUsernameExists;
            }),
          identityCard: Yup.string()
            .trim()
            .matches(/^[0-9]{12}$/, "CCCD phải là 12 kí tự số")
            .required("Vui lòng nhập số CMND")
            .test(
              "check-identityCard",
              "CCCD đã tồn tại",
              async function (value) {
                if (value == employee?.identityCard) {
                  return true;
                }
                if (!value) {
                  return true;
                }

                const isUsernameExists =
                  await employeeService.checkIdentityCardExists(value);
                return !isUsernameExists;
              }
            ),
          phone: Yup.string()
            .trim()
            .matches(
              /^0[0-9]{9,10}$/,
              "Số điện thoại phải là kí tự số và chỉ có 10 hoặc 11 số"
            )
            .min(10, "Số điện thoại phải từ 10 đến 11 kí tự số")
            .max(11, "Số điện thoại phải từ 10 đến 11 kí tự số")
            .required("Vui lòng nhập số điện thoại")
            .test(
              "check-phone",
              "Số điện thoại đã tồn tại",
              async function (value) {
                if (value == employee?.phone) {
                  return true;
                }
                if (!value) {
                  return true;
                }
                const isUsernameExists = await employeeService.checkPhoneExists(
                  value
                );
                return !isUsernameExists;
              }
            ),
          address: Yup.string()
            .trim()
            .max(100, "Địa chỉ tối đa 100 ký tự")
            .matches(
              /^[^!@#$%^&*()+=\[\]{};':"\\|.<>?`~]+$/,
              "Địa chỉ không chứa kí tự đặc biệt trừ /"
            )
            .required("Vui lòng nhập địa chỉ"),
        })}
        onSubmit={(values, { setSubmitting }) => {
          const edit = async () => {
            const newValue = {
              ...values,
              imgEmployee: firebaseImg,
            };
            try {
              if (flag) {
                newValue.imgEmployee = await handleSubmitAsync();
                await employeeService.editEmployee(newValue, token);
              } else {
                await employeeService.editEmployee(values, token);
                console.log(values);
              }
              toast(`Cập nhật nhân viên thành công! `);
              navigate(`/admin/employee/list`);
              setSubmitting(false);
            } catch (error) {
              console.log(error);
            }
          };
          edit();
        }}
      >
        <div style={{ margin: "150px 0" }}>
          <div className="row mx-0">
            <div className="container mx-auto my-5 col-8">
              <div className="form-employee">
                <h1 style={{ textAlign: "center", marginBottom: "5%" }}>
                  CẬP NHẬT NHÂN VIÊN
                </h1>
                <Form>
                  <div>
                    <Field type="hidden" name="idEmployee" />
                  </div>
                  <div>
                    <Field type="hidden" name="accountUser.id" />
                  </div>
                  <div className="row" style={{ marginBottom: "2%" }}>
                    <div className="col-3" style={{ textAlign: "right" }}>
                      <label className="fw-bold" style={{ marginRight: "2%" }}>
                        Ảnh <span style={{ color: "red" }}>(*)</span>
                      </label>
                    </div>
                    <div className="col-md-3 col-xs-12 col-sm-12 col-lg-3">
                      <Field
                        type="file"
                        onChange={(e) => handleFileSelect(e)}
                        id="imgEmployee"
                        name={"firebaseImg"}
                        className="form-control-plaintext d-none "
                      />
                      <p>
                        <label
                          htmlFor="imgEmployee"
                          style={{
                            display: "inline-block",
                            padding: "6px 12px",
                            border: "1px solid",
                            borderRadius: "4px",
                          }}
                        >
                          Chọn hình ảnh
                        </label>
                      </p>
                      {!selectedFile && (
                        <img
                          className={"mt-2"}
                          src={employee?.imgEmployee}
                          style={{ width: "100%" }}
                        />
                      )}
                      {selectedFile && (
                        <img
                          className={"mt-2"}
                          src={URL.createObjectURL(selectedFile)}
                          style={{ width: "50%" }}
                        />
                      )}
                    </div>
                    <ErrorMessage
                      name="imgEmployee"
                      component="span"
                      className="form-err text-center"
                      style={{ color: "red" }}
                    />
                  </div>
                  <div className="row" style={{ marginBottom: "2%" }}>
                    <div className="col-3" style={{ textAlign: "right" }}>
                      <label
                        htmlFor="account"
                        className="fw-bold"
                        style={{ marginRight: "2%" }}
                      >
                        Tài khoản
                        <span style={{ color: "red" }}>(*)</span>
                      </label>
                    </div>
                    <div className="col-8">
                      <Field
                        type="text"
                        style={{ width: "100%" }}
                        name="accountUser.nameAccount"
                        disabled={true}
                      />
                    </div>
                  </div>
                  <div className="row" style={{ marginBottom: "2%" }}>
                    <div className="col-3" style={{ textAlign: "right" }}>
                      <label
                        htmlFor="password"
                        className="fw-bold"
                        style={{ marginRight: "2%" }}
                      >
                        Mật khẩu
                        <span style={{ color: "red" }}>(*)</span>
                      </label>
                    </div>
                    <div className="col-8">
                      <Field
                        type="password"
                        style={{ width: "100%" }}
                        name="accountUser.passwordAccount"
                      />
                    </div>
                    <ErrorMessage
                      name="accountUser.passwordAccount"
                      component="span"
                      className="form-err text-center"
                      style={{ color: "red" }}
                    />
                  </div>
                  <div className="row" style={{ marginBottom: "2%" }}>
                    <div className="col-3" style={{ textAlign: "right" }}>
                      <label
                        htmlFor="accountUser.againPasswordAccount"
                        className="fw-bold"
                        style={{ marginRight: "2%" }}
                      >
                        Nhập lại mật khẩu{" "}
                        <span style={{ color: "red" }}>(*)</span>
                      </label>
                    </div>
                    <div className="col-8">
                      <Field
                        type="password"
                        style={{ width: "100%" }}
                        name="accountUser.againPasswordAccount"
                        id="accountUser.againPasswordAccount"
                      />
                    </div>
                    <ErrorMessage
                      name="accountUser.againPasswordAccount"
                      component="span"
                      className="form-err text-center"
                      style={{ color: "red" }}
                    />
                  </div>
                  <div className="row" style={{ marginBottom: "2%" }}>
                    <div className="col-3" style={{ textAlign: "right" }}>
                      <label
                        htmlFor="nameEmployee"
                        className="fw-bold"
                        style={{ marginRight: "2%" }}
                      >
                        Họ tên <span style={{ color: "red" }}>(*)</span>
                      </label>
                    </div>
                    <div className="col-8">
                      <Field
                        type="text"
                        style={{ width: "100%" }}
                        name="nameEmployee"
                        id="nameEmployee"
                      />
                    </div>
                    <ErrorMessage
                      name="nameEmployee"
                      component="span"
                      className="form-err text-center"
                      style={{ color: "red" }}
                    />
                  </div>
                  <div className="row" style={{ marginBottom: "2%" }}>
                    <div className="col-3" style={{ textAlign: "right" }}>
                      <label
                        htmlFor="dateOfBirth"
                        className="fw-bold"
                        style={{ marginRight: "2%" }}
                      >
                        Ngày sinh <span style={{ color: "red" }}>(*)</span>
                      </label>
                    </div>
                    <div className="col-8">
                      <Field
                        type="date"
                        style={{ width: "100%" }}
                        name="dateOfBirth"
                        id="dateOfBirth"
                      />
                    </div>
                    <ErrorMessage
                      name="dateOfBirth"
                      component="span"
                      className="form-err text-center"
                      style={{ color: "red" }}
                    />
                  </div>
                  <div className="row" style={{ marginBottom: "2%" }}>
                    <div className="col-3" style={{ textAlign: "right" }}>
                      <label className="fw-bold" style={{ marginRight: "2%" }}>
                        Giới tính <span style={{ color: "red" }}>(*)</span>
                      </label>
                    </div>
                    <div className="col-8 row">
                      <div className="col-3">
                        <Field
                          type="radio"
                          name="gender"
                          value="Nam"
                          id="inlineRadio1"
                          style={{
                            width: 17,
                            height: 17,
                            display: "inline-block",
                            marginRight: 10,
                          }}
                        />
                        <label
                          htmlFor="inlineRadio1"
                          style={{ marginRight: "5%" }}
                        >
                          Nam
                        </label>
                      </div>
                      <div className="col-3">
                        <Field
                          type="radio"
                          name="gender"
                          value="Nữ"
                          id="inlineRadio2"
                          style={{
                            width: 17,
                            height: 17,
                            display: "inline-block",
                            marginRight: 10,
                          }}
                        />
                        <label
                          htmlFor="inlineRadio2"
                          style={{ marginRight: "5%" }}
                        >
                          Nữ
                        </label>
                      </div>
                    </div>
                    <ErrorMessage
                      name="gender"
                      component="span"
                      className="form-err text-center"
                      style={{ color: "red" }}
                    />
                  </div>
                  <div className="row" style={{ marginBottom: "2%" }}>
                    <div className="col-3" style={{ textAlign: "right" }}>
                      <label
                        htmlFor="email"
                        className="fw-bold"
                        style={{ marginRight: "2%" }}
                      >
                        Email <span style={{ color: "red" }}>(*)</span>
                      </label>
                    </div>
                    <div className="col-8">
                      <Field
                        type="email"
                        style={{ width: "100%" }}
                        name="email"
                        id="email"
                      />
                    </div>
                    <ErrorMessage
                      name="email"
                      component="span"
                      className="form-err text-center"
                      style={{ color: "red" }}
                    />
                  </div>
                  <div className="row" style={{ marginBottom: "2%" }}>
                    <div className="col-3" style={{ textAlign: "right" }}>
                      <label
                        htmlFor="identityCard"
                        className="fw-bold"
                        style={{ marginRight: "2%" }}
                      >
                        CCCD <span style={{ color: "red" }}>(*)</span>
                      </label>
                    </div>
                    <div className="col-8">
                      <Field
                        type="text"
                        style={{ width: "100%" }}
                        name="identityCard"
                        id="identityCard"
                      />
                    </div>
                    <ErrorMessage
                      name="identityCard"
                      component="span"
                      className="form-err text-center"
                      style={{ color: "red" }}
                    />
                  </div>
                  <div className="row" style={{ marginBottom: "2%" }}>
                    <div className="col-3" style={{ textAlign: "right" }}>
                      <label
                        htmlFor="phone"
                        className="fw-bold"
                        style={{ marginRight: "2%" }}
                      >
                        Số điện thoại <span style={{ color: "red" }}>(*)</span>
                      </label>
                    </div>
                    <div className="col-8">
                      <Field
                        type="text"
                        style={{ width: "100%" }}
                        name="phone"
                        id="phone"
                      />
                    </div>
                    <ErrorMessage
                      name="phone"
                      component="span"
                      className="form-err text-center"
                      style={{ color: "red" }}
                    />
                  </div>
                  <div className="row" style={{ marginBottom: "2%" }}>
                    <div className="col-3" style={{ textAlign: "right" }}>
                      <label
                        htmlFor="address"
                        className="fw-bold"
                        style={{ marginRight: "2%" }}
                      >
                        Địa chỉ <span style={{ color: "red" }}>(*)</span>
                      </label>
                    </div>
                    <div className="col-8">
                      <Field
                        name="address"
                        id="address"
                        style={{ width: "100%" }}
                      />
                    </div>
                    <ErrorMessage
                      name="address"
                      component="span"
                      className="form-err text-center"
                      style={{ color: "red" }}
                    />
                  </div>
                  <div className="row" style={{ marginBottom: "2%" }}>
                    <div className="col-3" style={{ textAlign: "right" }}>
                      <span style={{ color: "red" }}>(*)</span>
                      <span>: Bắt buộc nhập</span>
                    </div>
                  </div>
                  <div className="row" style={{ marginBottom: "2%" }}>
                    <div className="col-3" style={{ textAlign: "right" }}>
                      <Field type="hidden" />
                    </div>
                    <div className="col-8" style={{ textAlign: "center" }}>
                      <button
                        type="submit"
                        className="btn btn-primary"
                        style={{ background: "#f26b38", marginRight: "10px" }}
                      >
                        Cập nhật
                      </button>
                      <button
                        type="reset"
                        className="btn btn-primary"
                        style={{ background: "black", color: "white" }}
                      >
                        Quay lại
                      </button>
                    </div>
                  </div>
                </Form>
              </div>
            </div>
          </div>
        </div>
      </Formik>
      <Footer />
    </>
  );
}
