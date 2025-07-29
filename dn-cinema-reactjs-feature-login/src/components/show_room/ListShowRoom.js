import React, { useEffect, useState } from "react";
import { findAll } from "../../service/ShowRoomService";
import { Link } from "react-router-dom";
import ReactPaginate from "react-paginate";
import { Field, Form, Formik } from "formik";
import Header from "../common/header/Header";
import Footer from "../common/footer/Footer";
import { color } from "chart.js/helpers";

export function ListShowRoom() {
  const [showRoomList, setShowRoomList] = useState([]);
  const [searchAndPage, setSearchAndPage] = useState({
    page: 0,
    search: "",
  });
  const [pageCount, setPageCount] = useState(0);
  const auth = localStorage.getItem("token");
  useEffect(() => {
    const list = async () => {
      const result = await findAll(searchAndPage, auth);
      console.log(result);
      try {
        setShowRoomList(result.content);
        setPageCount(result.totalPages);
      } catch {
        setShowRoomList([]);
      }
    };
    list();
  }, [searchAndPage]);

  const handlePageClick = (event) => {
    console.log(event.selected);
    setSearchAndPage((prev) => ({ ...prev, page: event.selected }));
  };
  return (
    <>
      <Header />
      <div className="row mx-0 list-cinema-room" style={{ margin: "150px 0" }}>
        <div className="container mx-auto my-5 col-8">
          <div
            style={{
              boxShadow:
                "rgba(67, 71, 85, 0.27) 0px 0px 0.25em, rgba(90, 125, 188, 0.05) 0px 0.25em 1em",
            }}
          >
            <div
              style={{
                marginBottom: 20,
                background: "rgb(242, 107, 56)",
                color: "white",
                height: "60px",
              }}
            >
              <h2
                className="d-flex justify-content-center"
                style={{
                  fontSize: "24px",
                  fontWeight: 600,
                  height: "60px",
                  alignItems: "center",
                }}
              >
                DANH SÁCH PHÒNG CHIẾU
              </h2>
            </div>
            <div style={{ marginTop: 20 }}>
              <div className="row">
                <div className="col-md-12">
                  <div className="d-flex justify-content-center">
                    {showRoomList.length === 0 ? (
                      <p style={{ color: "black" }}>
                        Không tìm thấy kết quả cho từ khóa{" "}
                        <span style={{ color: "red" }}>
                          "{searchAndPage.search}"
                        </span>
                      </p>
                    ) : (
                      <div className="row container-fluid">
                        <div className="col-2 col-md-6" />
                        <div className="col-10 col-md-6 p-0 d-flex justify-content-center gap-2">
                          <Formik
                            initialValues={{ search: "" }}
                            onSubmit={(values) => {
                              setSearchAndPage((prev) => {
                                return { ...prev, ...values, page: 0 };
                              });
                            }}
                          >
                            <Form
                              className="form d-flex align-items-center"
                              style={{ width: "100%" }}
                            >
                              <Field
                                style={{ width: "90%", height: "40px" }}
                                name="search"
                                className="form-control mx-2"
                                type="text"
                                placeholder="Tìm kiếm theo mã vé, tên phim..."
                              />
                              <button
                                style={{
                                  background: "rgb(242, 107, 56)",
                                  color: "white",
                                  border: "none",
                                  height: "40px",
                                }}
                                type="submit"
                                className="btn btn-outline-primary"
                                title="Tìm kiếm"
                              >
                                <svg
                                  xmlns="http://www.w3.org/2000/svg"
                                  width={16}
                                  height={16}
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
                        <table className="table table-striped table-hover">
                          <thead>
                            <tr style={{ textAlign: "center" }}>
                              <th>STT</th>
                              <th>Mã phòng chiếu</th>
                              <th>Tên phòng chiếu</th>
                              <th>Số lượng ghế</th>
                              <th>Chi tiết phòng chiếu</th>
                            </tr>
                          </thead>
                          <tbody>
                            {showRoomList.map((showRoom, index) => (
                              <tr key={index} style={{ textAlign: "center" }}>
                                <td>{index + 1}</td>
                                <td>{showRoom.idShowRoom}</td>
                                <td>{showRoom.nameShowRoom}</td>
                                <td>{showRoom.quantitySeat}</td>
                                <td>
                                  <Link
                                    to={
                                      "/admin/showroom/detail/" +
                                      showRoom.idShowRoom
                                    }
                                    style={{ display: "inline-block" }}
                                  >
                                    <button
                                      type="button"
                                      className="btn btn-outline-warning me-3"
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
                                  </Link>
                                </td>
                              </tr>
                            ))}
                          </tbody>
                        </table>
                        <div className="d-grid">
                          <ReactPaginate
                            breakLabel="..."
                            nextLabel=">"
                            onPageChange={handlePageClick}
                            pageCount={pageCount}
                            previousLabel="< "
                            containerClassName="pagination"
                            pageLinkClassName="page-num"
                            nextLinkClassName="page-next"
                            previousLinkClassName="page-previous"
                            activeClassName="active"
                            disabledClassName="d-none"
                          />
                        </div>
                      </div>
                    )}
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
}
