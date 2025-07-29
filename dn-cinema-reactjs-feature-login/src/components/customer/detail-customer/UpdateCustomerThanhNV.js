import React, { useEffect, useState } from "react";
import { Form, Field, Formik, ErrorMessage } from "formik";
import { useNavigate, useParams } from "react-router";
import { NavLink } from "react-router-dom";
import * as Yup from "yup";
import { editCustomer, findById } from "../../../service/CustomerService";
import Header from "../../common/header/Header";
import Footer from "../../common/footer/Footer";

function Update() {
  let param = useParams();

  let navigate = useNavigate();

  const [customers, setCustomers] = useState();
  const auth = localStorage.getItem("token");
  useEffect(() => {
    const fetchApi = async () => {
      const res = await findById(param.id, auth);
      setCustomers(res);
    };
    fetchApi();
  }, []);

  if (!customers) {
    return null;
  }
  return (
    <>
      <Header />
      <Formik
        initialValues={{
          idCustomer: customers?.idCustomer,
          nameCustomer: customers?.nameCustomer,
          dateOfBirth: customers?.dateOfBirth,
          phone: customers?.phone,
          address: customers?.address,
          email: customers?.email,
          identityCard: customers?.identityCard,
        }}
        validationSchema={Yup.object({
          nameCustomer: Yup.string().required("không để trống."),
          phone: Yup.string().required("không để trống."),
          address: Yup.string().required("không để trống."),

          email: Yup.string()
            .required("Không để trống.")
            .matches(
              /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/,
              "Email không hợp lệ."
            ),
        })}
        onSubmit={(values) => {
          const edit = async () => {
            console.log(values);
            await editCustomer(values, auth);
            navigate("/customer");
          };
          edit();
        }}
      >
        <>
          <div
            className="container"
            style={{ marginTop: 150, marginBottom: 150 }}
          >
            <div className="fw-center">
              <div>
                <Form>
                  <Field type="hidden" name="idCustomer" />
                  <div className="container mt-5">
                    <div>
                      <h1
                        className="d-flex justify-content-center"
                        style={{ fontSize: "2.5rem" }}
                      >
                        THÔNG TIN THÀNH VIÊN
                      </h1>

                      <div className="mb-3 align-items-center">
                        <label htmlFor="name" className="col-form-label">
                          Họ &amp; Tên:
                        </label>
                        <Field
                          type="text"
                          name="nameCustomer"
                          id="name"
                          className="form-control"
                        />
                        <ErrorMessage
                          name="nameCustomer"
                          component="div"
                          className="text-danger"
                        />
                      </div>
                      <div className="row">
                        <div className="col-md-6">
                          <div className="mb-3 align-items-center">
                            <label
                              htmlFor="birthday"
                              className="col-form-label"
                            >
                              Ngày sinh:
                            </label>
                            <Field
                              type="date"
                              name="dateOfBirth"
                              id="birthday"
                              className="form-control"
                            />
                          </div>
                        </div>

                        <div className="col-md-6">
                          <div className="mb-3 align-items-center">
                            <label htmlFor="phone" className="col-form-label">
                              Số điện thoại:
                            </label>
                            <Field
                              type="text"
                              id="phone"
                              name="phone"
                              className="form-control"
                              placeholder="Số điện thoại"
                            />
                            <ErrorMessage
                              name="phone"
                              component="div"
                              className="text-danger"
                            />
                          </div>
                        </div>
                      </div>
                      <div className="row">
                        <div className="col-md-6">
                          <div className="mb-3 align-items-center">
                            <label htmlFor="email" className="col-form-label">
                              Email:
                            </label>
                            <Field
                              type="text"
                              id="email"
                              name="email"
                              className="form-control"
                              placeholder="Email"
                            />
                            <ErrorMessage
                              name="email"
                              component="div"
                              className="text-danger"
                            />
                          </div>
                        </div>
                        <div className="col-md-6">
                          <div className="mb-3 align-items-center">
                            <label htmlFor="cmnd" className="col-form-label">
                              Số CMND:
                            </label>
                            <Field
                              type="text"
                              id="cmnd"
                              name="identityCard"
                              className="form-control"
                              placeholder="Chứng minh nhân dân"
                            />
                          </div>
                        </div>
                      </div>
                      <div className="mb-3 align-items-center">
                        <label htmlFor="address" className="col-form-label">
                          Địa chỉ:
                        </label>
                        <Field
                          type="text"
                          id="address"
                          name="address"
                          className="form-control"
                          placeholder="Địa chỉ"
                        />
                        <ErrorMessage
                          name="address"
                          component="div"
                          className="text-danger"
                        />
                        <div className="mt-3" style={{ textAlign: "center" }}>
                          <button
                            type="submit"
                            className="btn btn-outline-light"
                            style={{ background: "orangered" }}
                          >
                            Lưu lại
                          </button>

                          {/*<NavLink to={'/customer'}>*/}
                          {/*    <button*/}
                          {/*        type="button"*/}
                          {/*        className="btn btn-outline-secondary"*/}
                          {/*        data-bs-dismiss="modal">*/}
                          {/*        Quay lại*/}
                          {/*    </button>*/}
                          {/*</NavLink>*/}
                        </div>
                      </div>
                    </div>
                  </div>
                </Form>
              </div>
            </div>
          </div>
        </>
      </Formik>
      <Footer />
    </>
  );
}

export default Update;
