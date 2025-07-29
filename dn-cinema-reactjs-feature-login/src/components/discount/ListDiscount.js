import { Link, NavLink, useNavigate } from "react-router-dom";
import * as discountService from "../../service/discount/DiscountService";
import DiscountModalDelete from "./DeleteDiscount";
import ReactPaginate from "react-paginate";
import { Field, Form, Formik } from "formik";
import React, { useEffect, useState } from "react";
import Header from "../common/header/Header";
import Footer from "../common/footer/Footer";
import "react-toastify/dist/ReactToastify.css";
import { toast, ToastContainer } from "react-toastify";
import { color } from "chart.js/helpers";

function DiscountList() {
  const [discountList, setDiscountList] = useState([]);
  const [deleteId, setDeleteId] = useState(0);
  const [deleteName, setDeleteName] = useState("");
  const navigate = useNavigate();
  const [pageCount, setPageCount] = useState(0);
  const [page, setPage] = useState(0);
  const [size, setSize] = useState(0);
  let stt = page * size + 1;
  const [showMessage, setShowMessage] = useState(false);
  const [firstRecord, setFirstRecord] = useState(1);
  const [lastRecord, setLastRecord] = useState(0);
  const [totalRecords, setTotalRecords] = useState(0);
  const [showRecords, setShowRecords] = useState(true);
  const auth = localStorage.getItem("token");
  const getPropsDeleteDiscount = (id, name) => {
    setDeleteId(id);
    setDeleteName(name);
  };

  function handleUpdate(id) {
    navigate(`/admin/discount/update/${id}`);
  }

  const handlePageClick = (data) => {
    setPage(data.selected);
  };
  useEffect(() => {
    document.title = "Khuyến mãi";
  }, []);
  const findAll = async () => {
    const rs = await discountService.findByName("", page, auth);
    console.log(rs);
    setDiscountList(rs.data.content);
    setPageCount(rs.data.totalPages);
    setSize(rs.data.size);
    setTotalRecords(rs.data.totalElements);
  };

  useEffect(() => {
    findAll();
    const first = page * size + 1;
    const last = Math.min((page + 1) * size, totalRecords);
    setFirstRecord(first);
    setLastRecord(last);
  }, [page, size, totalRecords]);

  return (
    discountList && (
      <>
        <Header />
        <div className="row mx-0" style={{ margin: "150px 0" }}>
          <div
            className="container-fluid mx-auto my-5 col-10"
            style={{
              boxShadow: "rgba(0, 0, 0, 0.2) 1px 3px 10px 5px",
              padding: 0,
            }}
          >
            <div>
              <div style={{ marginBottom: 20 }}>
                <h2
                  className="d-flex justify-content-center"
                  style={{
                    padding: 16,
                    backgroundColor: "#f26b38",
                    color: "#fff",
                    fontSize: 24,
                    fontWeight: 600,
                  }}
                >
                  DANH SÁCH KHUYẾN MÃI
                </h2>
              </div>
              <div className="row">
                <div className="col-md-4">
                  <NavLink to="/admin/discount/create">
                    <button
                      className="btn btn-outline-primary text-dark"
                      style={{
                        background: "rgb(242, 107, 56)",
                        border: "none",
                        marginLeft: "10px",
                      }}
                    >
                      <i
                        className="bi bi-plus-circle"
                        style={{ color: "white" }}
                      />{" "}
                      <span style={{ color: "white" }}>
                        Thêm mới khuyến mãi
                      </span>
                    </button>
                  </NavLink>
                </div>
                <div className="row col-md-8">
                  <Formik
                    initialValues={{
                      name: "",
                    }}
                    onSubmit={(value) => {
                      const search = async () => {
                        const rs = await discountService.findByName(
                          value.name,
                          0,
                          auth
                        );
                        if (rs.data.content.length === 0) {
                          setShowMessage(true);
                        } else {
                          setShowMessage(false);
                          setDiscountList(rs.data.content);
                          setPageCount(rs.data.totalPages);
                        }
                      };
                      search();
                    }}
                  >
                    <Form className="d-flex justify-content-end">
                      <div
                        className="form-group d-flex justify-content-end w-75"
                        style={{
                          paddingLeft: 80,
                        }}
                      >
                        <i className="ti-search ti-search1" />
                        <Field
                          type="text"
                          className="form-control float-start me-3"
                          style={{
                            width: "90%",
                            paddingLeft: 35,
                            height: "38px",
                          }}
                          name="name"
                          aria-describedby="helpId"
                          placeholder="Tìm kiếm..."
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
                      </div>
                    </Form>
                  </Formik>
                </div>
              </div>
              <div style={{ marginTop: 20 }}>
                <div className="row">
                  <div className="col-md-12">
                    <div>
                      {showMessage && (
                        <div className="d-flex justify-content-center">
                          <span id="empty">Không tìm thấy tên khuyến mãi</span>
                          <div>
                            <button
                              className="btn btn-outline-primary ms-3"
                              onClick={() => setShowMessage(false)}
                            >
                              Trở về danh sách khuyến mãi
                            </button>
                          </div>
                        </div>
                      )}
                    </div>
                    <div className="d-flex justify-content-center">
                      {!showMessage && discountList.length > 0 && (
                        <table className="table table-striped table-hover">
                          <thead>
                            <tr style={{ textAlign: "center" }}>
                              <th
                                style={{ width: "2%" }}
                                className="text-center"
                              >
                                STT
                              </th>
                              <th
                                style={{ width: "15%" }}
                                className="text-center"
                              >
                                Khuyến mãi
                              </th>
                              <th
                                style={{ width: "10%" }}
                                className="text-center"
                              >
                                Hình ảnh
                              </th>
                              <th
                                style={{ width: "10%" }}
                                className="text-center"
                              >
                                Ngày bắt đầu
                              </th>
                              <th
                                style={{ width: "10%" }}
                                className="text-center"
                              >
                                Ngày kết thúc
                              </th>
                              <th
                                style={{ width: "25%" }}
                                className="text-center"
                              >
                                Nội dung
                              </th>
                              <th className="text-center">Mức ưu đãi (%)</th>
                              <th className="text-center">Tác vụ</th>
                            </tr>
                          </thead>
                          <tbody>
                            {discountList.map((discount, index) => {
                              return (
                                <tr key={index} style={{ textAlign: "center" }}>
                                  <td
                                    scope="row"
                                    className="text-center align-middle "
                                  >
                                    {" "}
                                    <strong>{stt++}</strong>
                                  </td>
                                  <td
                                    style={{ width: "15%" }}
                                    className="align-middle text-start"
                                  >
                                    {discount.nameDiscount}
                                  </td>
                                  <td className="text-center align-middle">
                                    <div className="d-flex justify-content-center">
                                      <a
                                        href={discount.imageDiscount}
                                        target="_blank"
                                        rel="noopener noreferrer"
                                      >
                                        <img
                                          src={discount.imageDiscount}
                                          alt="Hình ảnh khuyến mãi"
                                          style={{ width: 70, height: 100 }}
                                        />
                                      </a>
                                    </div>
                                  </td>
                                  <td className="text-center align-middle">
                                    {discount.dateStart}
                                  </td>
                                  <td className="text-center align-middle">
                                    {discount.dateEnd}
                                  </td>
                                  <td className="align-middle text-start">
                                    {discount.describeDiscount}
                                  </td>
                                  <td className="text-center align-middle">
                                    <strong>{discount.percentDiscount}</strong>
                                  </td>
                                  <td className="text-center align-middle">
                                    <button
                                      type="button"
                                      className="btn btn-outline-warning"
                                      onClick={() =>
                                        handleUpdate(discount.idDiscount)
                                      }
                                    >
                                      <i className="bi bi-pencil" />
                                    </button>
                                    <button
                                      style={{ marginLeft: "5px" }}
                                      type="button"
                                      data-bs-toggle="modal"
                                      className="btn btn-outline-danger"
                                      data-bs-target="#exampleModal"
                                      onClick={() =>
                                        getPropsDeleteDiscount(
                                          discount.idDiscount,
                                          discount.nameDiscount
                                        )
                                      }
                                    >
                                      <i className="bi bi-trash" />
                                    </button>
                                  </td>
                                </tr>
                              );
                            })}
                          </tbody>
                        </table>
                      )}
                    </div>
                    <div className="d-flex justify-content-center">
                      <p>
                        Hiển thị{" "}
                        <strong>
                          {firstRecord} - {lastRecord}{" "}
                        </strong>{" "}
                        trong tổng số <strong>{totalRecords}</strong> bản ghi
                      </p>
                    </div>
                  </div>
                </div>
              </div>
              <div>
                <ReactPaginate
                  previousLabel={"Trước"}
                  nextLabel={"Sau"}
                  pageCount={pageCount}
                  onPageChange={handlePageClick}
                  containerClassName="pagination"
                  pageClassName="page-item"
                  pageLinkClassName="page-link"
                  previousClassName="page-item"
                  previousLinkClassName="page-link"
                  nextClassName="page-item"
                  nextLinkClassName="page-link"
                  activeClassName="active"
                />
              </div>
            </div>
          </div>
        </div>
        <ToastContainer
          position="top-right"
          autoClose={1000}
          hideProgressBar={false}
          newestOnTop={false}
          closeOnClick
          rtl={false}
          pauseOnFocusLoss
          draggable
          pauseOnHover
          theme="dark"
        />

        <DiscountModalDelete
          id={deleteId}
          name={deleteName}
          getShowList={() => {
            findAll();
          }}
        />
        <Footer />
      </>
    )
  );
}

export default DiscountList;
