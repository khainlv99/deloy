import "./Login.css";
import { LoginSocialFacebook } from "reactjs-social-login";
import { FacebookLoginButton } from "react-social-login-buttons";
import { ErrorMessage, Field, Form, Formik } from "formik";
import * as Yup from "yup";
import {
  handleCallApiLogin,
  handleCallApiToResetPassword,
} from "../../service/LoginService";
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router";
import { Link } from "react-router-dom";
import { useSelector } from "react-redux";
import { Circles } from "react-loader-spinner";
import { toast } from "react-toastify";
import Header from "../../components/common/header/Header";
import Footer from "../../components/common/footer/Footer";
/**
 * @author ChinhLV
 * @returns ResetPassword
 * @since 27/05/2023
 * ResetPassword component được sử dụng để đổi mật khẩu. Nếu mật khẩu trùng với xác nhận mật khẩu
 * và mã code đúng với mã đã được gửi qua email
 * thì cho reset password. Còn không thông báo lỗi cho người dùng.
 */
function ResetPassword() {
  const data = useSelector((state) => state.api.data);
  const location = useLocation();
  const [failedCode, setFailedCode] = useState(null);
  const [failedConfirmPassword, setFailedConfirmPassword] = useState(null);
  const navigate = useNavigate();
  useEffect(() => {
    document.title = "Đổi mật khẩu";
  }, []);
  return (
    <>
      <Header />
      <div className="d-flex justify-content-center">
        <div className="login-container container d-flex justify-content-center align-items-center flex-column">
          <Formik
            initialValues={{
              code: "",
              newPassword: "",
              confirmNewPassword: "",
            }}
            validationSchema={Yup.object({
              code: Yup.number()
                .required("Mã code bắt buộc phải nhập.")
                .test(
                  "Mã code bắt buộc phải là 6 chữ số.",
                  "Mã code bắt buộc phải là 6 chữ số.",
                  function (value) {
                    return value >= 100000 && value <= 999999;
                  }
                ),
              newPassword: Yup.string()
                .required("Mật khẩu bắt buộc phải nhập.")
                .test(
                  "Mật khẩu bắt buộc từ 6-30 ký tự.",
                  "Mật khẩu bắt buộc từ 6-30 ký tự.",
                  function (value) {
                    return value.length >= 6 && value.length <= 30;
                  }
                ),
              confirmNewPassword: Yup.string()
                .required("Mật khẩu bắt buộc phải nhập.")
                .oneOf(
                  [Yup.ref("newPassword")],
                  "Xác nhận mật khẩu không đúng"
                ),
            })}
            onSubmit={(values, { setSubmitting }) => {
              if (parseInt(data.code) !== values.code) {
                setFailedCode("Mã code không đúng, vui lòng thử lại.");
                setSubmitting(false);
              }
              if (parseInt(data.code) === values.code) {
                setTimeout(() => {
                  setSubmitting(false);
                  handleCallApiToResetPassword({
                    email: location.state.email,
                    password: values.newPassword,
                  }).then((e) => {
                    toast("Đổi mật khẩu thành công, đăng nhập để tiếp tục.");
                  });
                  navigate("/login");
                }, 2000);
              }
            }}
          >
            {({
              handleSubmit,
              handleChange,
              setSubmitting,
              isSubmitting,
              values,
            }) => (
              <Form>
                <p className="title-login text-center mb-3">Đổi mật khẩu</p>
                <table>
                  <tbody>
                    <tr>
                      <td colSpan={2} className="modify-title">
                        Vui lòng nhập mã code đã được gửi qua email{" "}
                        <span className="fw-bold">{location.state.email}</span>{" "}
                        để đổi mật khẩu mới.
                      </td>
                    </tr>
                    <tr>
                      <td>
                        <label htmlFor="code">Mã code</label>
                      </td>
                      <td>
                        <Field
                          type="number"
                          name="code"
                          id="username"
                          placeholder="Nhập mã code từ email"
                        />
                      </td>
                    </tr>
                    <tr>
                      <th></th>
                      <td>
                        <ErrorMessage
                          name="code"
                          className="error-mess m-0"
                          component={"p"}
                        />
                        {failedCode && (
                          <p className="error-mess m-0">{failedCode}</p>
                        )}
                      </td>
                    </tr>
                    <tr>
                      <td>
                        <label htmlFor="newPassword">Mật khẩu mới</label>
                      </td>
                      <td>
                        <Field
                          type="password"
                          autoComplete="on"
                          name="newPassword"
                          id="password"
                          placeholder="Mật khẩu mới"
                        />
                      </td>
                    </tr>
                    <tr>
                      <th></th>
                      <td>
                        <ErrorMessage
                          name="newPassword"
                          className="error-mess m-0"
                          component={"p"}
                        />
                      </td>
                    </tr>
                    <tr>
                      <td>
                        <label htmlFor="confirmNewPassword">
                          Xác nhận mật khẩu
                        </label>
                      </td>
                      <td>
                        <Field
                          type="password"
                          autoComplete="on"
                          name="confirmNewPassword"
                          id="password"
                          placeholder="Nhập lại mật khẩu"
                        />
                      </td>
                    </tr>
                    <tr>
                      <th></th>
                      <td>
                        <ErrorMessage
                          name="confirmNewPassword"
                          className="error-mess m-0"
                          component={"p"}
                        />
                        {failedConfirmPassword && (
                          <p className="error-mess m-0">
                            {failedConfirmPassword}
                          </p>
                        )}
                      </td>
                    </tr>
                    <tr>
                      <td></td>
                      <td className="other-login">
                        <Link
                          to={"/login"}
                          className="login-facebook text-decoration-none"
                        >
                          Đăng nhập tài khoản khác
                        </Link>
                        <Link
                          to="/register"
                          className="float-end text-decoration-none"
                        >
                          Đăng ký tài khoản
                        </Link>
                      </td>
                    </tr>
                    <tr>
                      <th></th>
                      <td className="other-login">
                        {isSubmitting ? (
                          <div className="d-flex justify-content-center w-100 align-items-center">
                            <Circles
                              height="50"
                              width="50"
                              color="#f26b38"
                              ariaLabel="circles-loading"
                              wrapperStyle={{}}
                              wrapperClass=""
                              visible={true}
                            />
                          </div>
                        ) : (
                          <button
                            type="submit"
                            className="login mt-3 w-100 text-center fw-bold"
                          >
                            Đổi mật khẩu
                          </button>
                        )}
                      </td>
                    </tr>
                    <tr>
                      <th></th>
                      <td>
                        <LoginSocialFacebook
                          appId="257872636750784"
                          onResolve={(resolve) => {
                            console.log(resolve);
                          }}
                          onReject={(reject) => console.log(reject)}
                        >
                          <FacebookLoginButton className="login login-facebook w-100 text-center fw-bold d-flex justify-content-center mt-1" />
                        </LoginSocialFacebook>
                      </td>
                    </tr>
                  </tbody>
                </table>
              </Form>
            )}
          </Formik>
        </div>
      </div>
      <Footer />
    </>
  );
}

export default ResetPassword;
