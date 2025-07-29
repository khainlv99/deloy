import { useState, useEffect } from "react";
import "../ticket/ticket.css";
import { Field, Form, Formik } from "formik";
import ReactPaginate from "react-paginate";
import { cancelTicket, findAllTicket } from "../../service/TicketService";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { Link } from "react-router-dom";
import Header from "../common/header/Header";
import Footer from "../common/footer/Footer";

export function ListTicket() {
  const [listTicket, setListTicket] = useState([]);
  const [pageCount, setPageCount] = useState(0);
  const [ticketData, setTicketData] = useState({ id: 0, nameFilm: "" });
  const [time, setTime] = useState(0);
  const [searchAndPage, setSearchAndPage] = useState({
    page: 0,
    search: "",
  });
  const auth = localStorage.getItem("token");
  useEffect(() => {
    document.title = "Quản lý vé";
  }, []);
  useEffect(() => {
    const list = async () => {
      const result = await findAllTicket(searchAndPage, auth);
      try {
        setListTicket(result.content);
        setPageCount(result.totalPages);
      } catch {
        setListTicket([]);
      }
    };

    list();
  }, [searchAndPage, auth]);

  const handleCancleTicket = () => {
    const cancel = async () => {
      await cancelTicket(ticketData.id, auth);
      setListTicket((state) =>
        state.filter((ticket) => ticket.idTicket != ticketData.id)
      );
      toast.success("Huỷ vé thành công");
    };
    cancel();
  };
  const handlePageClick = (event) => {
    setSearchAndPage((prev) => ({ ...prev, page: event.selected }));
  };

  return (
    <>
      <Header />
      <div className="row mx-0" style={{ margin: "150px 0" }}>
        <div
          className="container-fluid mx-auto my-5 col-8"
          style={{ width: "80%" }}
        >
          <div style={{ boxShadow: "1px 3px 10px 5px rgba(0, 0, 0, 0.2)" }}>
            <div style={{ marginBottom: 20 }}>
              <h2
                className="d-flex justify-content-center"
                style={{
                  padding: 16,
                  backgroundColor: "rgb(242, 107, 56)",
                  fontSize: "24px",
                  color: "#fff",
                }}
              >
                DANH SÁCH VÉ ĐẶT
              </h2>
            </div>
            <div className="row ">
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
                    style={{ width: "90%" }}
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
            </div>
            <div style={{ marginTop: 20 }}>
              <div className="row">
                <div className="col-md-12">
                  <div className="d-flex justify-content-center">
                    {listTicket.length === 0 && searchAndPage.search !== "" ? (
                      <h1 className={"text-danger text-center my-3"}>
                        Không tìm thấy kết quả
                      </h1>
                    ) : (
                      <div className="table-responsive">
                        <table className="table table-striped table-hover align-middle">
                          <thead>
                            <tr style={{ textAlign: "center" }}>
                              <th>STT</th>
                              <th>Mã vé đặt</th>
                              <th>Mã thành viên</th>
                              <th>Họ tên</th>
                              <th>CMND</th>
                              <th>Số điện thoại</th>
                              <th>Phim</th>
                              <th>Ngày chiếu</th>
                              <th>Giờ chiếu</th>
                              <th>Tình trạng</th>
                              <th>Tác vụ</th>
                            </tr>
                          </thead>
                          <tbody>
                            {listTicket.map((ticket, index) => (
                              <tr key={index} style={{ textAlign: "center" }}>
                                <td>{index + 1}</td>
                                <td>{ticket.idTicket}</td>
                                <td>{ticket.idCustomer}</td>
                                <td>{ticket.nameCustomer}</td>
                                <td>{ticket.identityCard}</td>
                                <td>{ticket.phone}</td>

                                <td>{ticket.nameFilm}</td>
                                <td>{ticket.showDate}</td>
                                <td>{ticket.showTime}</td>
                                <td>
                                  {ticket.statusTicket
                                    ? "Đã nhận"
                                    : "Chưa nhận"}
                                </td>
                                <td>
                                  <Link
                                    to={`/employee/ticket/detail/${ticket.idTicket}`}
                                    className="btn btn-outline-success"
                                    title="Nhận vé"
                                  >
                                    <svg
                                      xmlns="http://www.w3.org/2000/svg"
                                      width={16}
                                      height={16}
                                      fill="currentColor"
                                      className="bi bi-check2-square"
                                      viewBox="0 0 16 16"
                                    >
                                      <path d="M3 14.5A1.5 1.5 0 0 1 1.5 13V3A1.5 1.5 0 0 1 3 1.5h8a.5.5 0 0 1 0 1H3a.5.5 0 0 0-.5.5v10a.5.5 0 0 0 .5.5h10a.5.5 0 0 0 .5-.5V8a.5.5 0 0 1 1 0v5a1.5 1.5 0 0 1-1.5 1.5H3z" />
                                      <path d="m8.354 10.354 7-7a.5.5 0 0 0-.708-.708L8 9.293 5.354 6.646a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0z" />
                                    </svg>
                                  </Link>
                                  <button
                                    style={{ marginLeft: "5px" }}
                                    key={ticket.idTicket}
                                    type="button"
                                    title="Huỷ vé"
                                    className="btn btn-outline-danger"
                                    data-bs-toggle="modal"
                                    onClick={() => {
                                      const currentDate = new Date();
                                      const dateString =
                                        ticket.showDate + " " + ticket.showTime;
                                      var parts = dateString.split(/[- :]/);
                                      var year = parseInt(parts[0]);
                                      var month = parseInt(parts[1]) - 1;
                                      var day = parseInt(parts[2]);
                                      var hour = parseInt(parts[3]);
                                      var minute = parseInt(parts[4]);

                                      var date = new Date(
                                        year,
                                        month,
                                        day,
                                        hour,
                                        minute
                                      );
                                      var timeDiff = date - currentDate;
                                      var hoursDiff =
                                        timeDiff / (1000 * 60 * 60);
                                      setTime(hoursDiff);
                                      setTicketData({
                                        id: ticket.idTicket,
                                        nameFilm: ticket.nameFilm,
                                      });
                                    }}
                                    data-bs-target="#cancelTicket"
                                  >
                                    <svg
                                      xmlns="http://www.w3.org/2000/svg"
                                      width={16}
                                      height={16}
                                      fill="currentColor"
                                      className="bi bi-trash3"
                                      viewBox="0 0 16 16"
                                      title="Huỷ vé"
                                    >
                                      <path d="M6.5 1h3a.5.5 0 0 1 .5.5v1H6v-1a.5.5 0 0 1 .5-.5ZM11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3A1.5 1.5 0 0 0 5 1.5v1H2.506a.58.58 0 0 0-.01 0H1.5a.5.5 0 0 0 0 1h.538l.853 10.66A2 2 0 0 0 4.885 16h6.23a2 2 0 0 0 1.994-1.84l.853-10.66h.538a.5.5 0 0 0 0-1h-.995a.59.59 0 0 0-.01 0H11Zm1.958 1-.846 10.58a1 1 0 0 1-.997.92h-6.23a1 1 0 0 1-.997-.92L3.042 3.5h9.916Zm-7.487 1a.5.5 0 0 1 .528.47l.5 8.5a.5.5 0 0 1-.998.06L5 5.03a.5.5 0 0 1 .47-.53Zm5.058 0a.5.5 0 0 1 .47.53l-.5 8.5a.5.5 0 1 1-.998-.06l.5-8.5a.5.5 0 0 1 .528-.47ZM8 4.5a.5.5 0 0 1 .5.5v8.5a.5.5 0 0 1-1 0V5a.5.5 0 0 1 .5-.5Z" />
                                    </svg>
                                  </button>
                                  <ToastContainer />
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
      {/*Modal xoá*/}
      <div
        className="modal fade"
        id="cancelTicket"
        tabIndex="{-1}"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
      >
        {time > 1 ? (
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <h1 className="modal-title fs-5" id="exampleModalLabel">
                  Huỷ vé
                </h1>
                <button
                  type="button"
                  className="btn-close"
                  data-bs-dismiss="modal"
                  aria-label="Close"
                />
              </div>
              <div className="modal-body">
                <span className="float-start mx-1 ">
                  Bạn có chắc chắn muốn huỷ vé phim
                </span>{" "}
                <span className="text-danger fw-bold f-flex float-start mx-1">
                  {ticketData.nameFilm}
                </span>
                không?
              </div>
              <div className="modal-footer">
                <button
                  type="button"
                  className="btn btn-secondary"
                  data-bs-dismiss="modal"
                >
                  Hủy
                </button>
                <button
                  className="btn btn-danger"
                  data-bs-dismiss="modal"
                  onClick={() => handleCancleTicket()}
                >
                  Huỷ vé
                </button>
              </div>
            </div>
          </div>
        ) : (
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <h5 className="modal-title" id="exampleModalLabel">
                  Huỷ vé
                </h5>
                <button
                  type="button"
                  className="btn-close"
                  data-bs-dismiss="modal"
                  aria-label="Close"
                />
              </div>
              <div className="modal-body">
                Vé chỉ có thể huỷ trước 1 giờ phim chiếu
              </div>
              <div className="modal-footer">
                <button
                  type="button"
                  className="btn btn-secondary"
                  data-bs-dismiss="modal"
                >
                  Huỷ
                </button>
              </div>
            </div>
          </div>
        )}
      </div>
      <Footer />
    </>
  );
}
