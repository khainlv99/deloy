import React, { useEffect, useState } from "react";
import { Field, Form, Formik } from "formik";
import { customerService } from "../../service/CustomerService";
import { customerTypeService } from "../../service/CustomerTypeService";
import { useNavigate } from "react-router-dom";
import Header from "../common/header/Header";
import Footer from "../common/footer/Footer";
import ReactPaginate from "react-paginate";

function List() {
  const [customer, setCustomer] = useState([]);
  const [customerType, setCustomerType] = useState([]);
  const auth = localStorage.getItem("token");
  let navigate = useNavigate();

  useEffect(() => {
    const customerList = async () => {
      const customerApi = await customerService.findAll(auth);
      const customerTypeApi = await customerTypeService.findAllCustomerType();

      setCustomer(customerApi);
      setCustomerType(customerTypeApi);
    };
    customerList();
  }, []);

  const handleEdit = (id) => {
    navigate(`/admin/customer/edit/${id}`);
  };

  return (
    <>
      <Header />
      <div className="container my-5" style={{ marginBottom: "150px" }}>
        <div className="row" style={{ marginTop: "150px" }}>
          <div className="col-12">
            <div style={{ marginBottom: "20px" }}>
              <h2
                className="text-center p-3"
                style={{
                  padding: "16px",
                  backgroundColor: "rgb(242, 107, 56)",
                  color: "#fff",
                  fontSize: "24px",
                }}
              >
                DANH SÁCH THÔNG TIN THÀNH VIÊN
              </h2>
            </div>
            <div className="row">
              <div className="col-md-6"></div>

              <div className="col-md-6">
                <Formik
                  initialValues={{ nameSearch: "" }}
                  onSubmit={async (values) => {
                    const result = await customerService.findAllAndSearch(
                      values.nameSearch
                    );
                    if (result.length === 0) {
                      alert("không tìm thấy");
                    } else {
                      setCustomer(result);
                    }
                  }}
                >
                  <Form className="d-flex">
                    <Field
                      style={{ width: "90%", height: "40px" }}
                      className="form-control me-2 flex-grow-1"
                      type="text"
                      name="nameSearch"
                      placeholder="Tìm kiếm theo tên thành viên..."
                    />
                    <button
                      className="btn btn-outline-success"
                      style={{
                        background: "rgb(242, 107, 56)",
                        color: "white",
                        border: "none",
                        width: "50px",
                      }}
                    >
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="16"
                        height="16"
                        fill="currentColor"
                        className="bi bi-search"
                        viewBox="0 0 16 16"
                      >
                        <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" />
                      </svg>
                    </button>
                  </Form>
                </Formik>
              </div>
            </div>
            <div className="table-responsive mt-4">
              <table className="table table-striped">
                <thead>
                  <tr style={{ textAlign: "center" }}>
                    <th>Stt</th>
                    {/*<th>Mã thành viên</th>*/}
                    <th>Tên thành viên</th>
                    <th>Số điện thoại</th>
                    <th>Giới tính</th>
                    <th>Tích điểm</th>
                    <th>Loại Thành Viên</th>
                    <th>Email</th>
                    <th>Địa chỉ</th>
                    <th>Số CMND</th>
                    <th>Chỉnh sửa</th>
                  </tr>
                </thead>
                <tbody>
                  {customer &&
                    customer.map((customers, index) => (
                      <tr key={index} style={{ textAlign: "center" }}>
                        <td>{index + 1}</td>
                        <td>{customers.nameCustomer}</td>
                        <td>{customers.phone}</td>
                        <td>{customers.gender}</td>
                        <td>{customers.pointCustomer}</td>
                        <td>{customers.typeCustomer.nameTypeCustomer}</td>
                        <td>{customers.email}</td>
                        <td>{customers.address}</td>
                        <td>{customers.identityCard}</td>
                        <td>
                          <button
                            onClick={() => handleEdit(customers.idCustomer)}
                            className="btn btn-outline-warning"
                          >
                            <svg
                              xmlns="http://www.w3.org/2000/svg"
                              width="16"
                              height="16"
                              fill="currentColor"
                              className="bi bi-pencil"
                              viewBox="0 0 16 16"
                            >
                              <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z" />
                            </svg>
                          </button>
                        </td>
                      </tr>
                    ))}
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
}

export default List;
