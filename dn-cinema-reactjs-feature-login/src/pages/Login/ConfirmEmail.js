import "./Login.css";
import { LoginSocialFacebook } from "reactjs-social-login";
import { FacebookLoginButton } from "react-social-login-buttons";
import { ErrorMessage, Field, Form, Formik } from "formik";
import * as Yup from "yup";
import { useState } from "react";
import { useNavigate } from "react-router";
import { Link } from "react-router-dom";
import { Circles } from "react-loader-spinner";
import { handleCallApiToConfirmEmail } from "../../service/LoginService";
import { useDispatch } from "react-redux";
import { receiveData } from "../../redux/action";
import Header from "../../components/common/header/Header";
import Footer from "../../components/common/footer/Footer";
import { useEffect } from "react";
/**
 * @author ChinhLV
 * @returns ComfirmEmail
 * @since 27/05/2023
 * ConfirmEmail component được sử dụng để xác minh tài khoản trước khi đổi mật khẩu thông qua mã code được gửi qua email.
 * nếu email đã tồn tại thì gửi mã code, nếu không thì báo lỗi email không tồn tại.
 */
function ConfirmEmail() {
  const [failedEmail, setFailedEmail] = useState(null);
  const dispatch = useDispatch();
  const navigate = useNavigate();
  useEffect(() => {
    document.title = "Xác minh email";
  }, []);
  return (
    <>
      <Header />
      <div className="d-flex justify-content-center">
        <div className="login-container container d-flex justify-content-center align-items-center flex-column">
          <Formik
            initialValues={{
              email: "",
            }}
            validationSchema={Yup.object({
              email: Yup.string()
                .required("Email bắt buộc phải nhập.")
                .matches(
                  /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/,
                  "Email không đúng định dạng."
                ),
            })}
            onSubmit={(values, { setSubmitting }) => {
              setFailedEmail(null);
              setTimeout(() => {
                setSubmitting(false);
                handleCallApiToConfirmEmail(values)
                  .then((e) => {
                    dispatch(
                      receiveData({
                        email: values.email,
                        code: e.data.code,
                      })
                    );
                  })
                  .catch((e) => {
                    console.log(e);
                    setFailedEmail(
                      "Email này chưa được đăng ký, vui lòng thử lại."
                    );
                    return;
                  });
                navigate("/reset-password", {
                  state: { email: values.email },
                });
              }, 5000);
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
                <p className="title-login text-center mb-3">Xác minh email</p>
                <table>
                  <tbody>
                    <tr>
                      <td colSpan={2} className="modify-title">
                        Vui lòng nhập email mà bạn đã đăng ký tài khoản để nhận
                        mã code qua email xác minh đây là tài khoản của bạn
                        trước khi đổi mật khẩu.
                      </td>
                    </tr>
                    <tr>
                      <td>
                        <label htmlFor="email">Email đã đăng ký</label>
                      </td>
                      <td>
                        <Field
                          type="text"
                          name="email"
                          id="username"
                          placeholder="Nhập email đã đăng ký tài khoản"
                        />
                      </td>
                    </tr>
                    <tr>
                      <th></th>
                      <td>
                        <ErrorMessage
                          name="email"
                          className="error-mess m-0"
                          component={"p"}
                        />
                        {failedEmail && (
                          <p className="error-mess m-0">{failedEmail}</p>
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
                          <div className="d-flex justify-content-center align-items-center w-100">
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
                            Xác minh email
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

export default ConfirmEmail;
