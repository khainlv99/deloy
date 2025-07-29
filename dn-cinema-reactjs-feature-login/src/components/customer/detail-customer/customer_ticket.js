import * as customerService from "../../../service/TicketManagementService";
import React, { useEffect, useState } from "react";
import "../detail-customer/style.css";
import ReactPaginate from "react-paginate";
import { Link } from "react-router-dom";
import { toast, ToastContainer } from "react-toastify";
import Header from "../../common/header/Header";
import Footer from "../../common/footer/Footer";
import { findCustomerByNameAccount } from "../../../service/CustomerServiceTruongNN";

export function TickBookingList(effect, deps) {
  const [ticketBooking, setTicketBooking] = useState(null);
  const [pageCount, setPageCount] = useState(0);
  const [page, setPage] = useState(0);
  const [size, setSize] = useState(0);
  const [deleteTicket, setDeleteTicket] = useState();
  const [user, setUser] = useState(null);
  const token = localStorage.getItem("token");
  const username = localStorage.getItem("username");

  let stt = page * size + 1;
  const handleLogout = () => {
    localStorage.clear();
    window.location.href = "/";
  };
  const handlePageClick = (event) => {
    setPage(+event.selected);
  };

  const handleDeleteTicket = async (id) => {
    setDeleteTicket(id);
    console.log(deleteTicket);
  };
  const handleDelete = async () => {
    console.log(deleteTicket);
    await customerService.deleteTicket(deleteTicket);
    setTicketBooking(ticketBooking.filter((e) => e.idTicket != deleteTicket));
    toast("Xóa thành công !");
  };
  useEffect(() => {
    document.title = "Vé đã đặt";
    const findCustomerByUsername = async () => {
      const result = await findCustomerByNameAccount(username);
      setUser(result);
    };
    findCustomerByUsername();
  }, []);
  useEffect(() => {
    const fetchApi = async () => {
      try {
        const result = await customerService.findAllTicketBooking(page, token);
        setTicketBooking(result.data.content);
        setPageCount(result.data.totalPages);
        setSize(result.data.size);
        console.log(result);
      } catch (error) {
        console.log(error);
      }
    };
    fetchApi();
  }, [page, deleteTicket]);

  return (
    <>
      <Header />
      <div id="mySidebar" className="sidebar" style={{ margin: "150px 0" }}>
        <a href="javascript:void(0)" className="closebtn" onClick="closeNav()">
          ×
        </a>
        <a href="#">About</a>
        <a href="#">Services</a>
        <a href="#">Clients</a>
        <a href="#">Contact</a>
      </div>
      <div className="container" style={{ marginTop: 150 }}>
        <div className="row">
          <i className="bi bi-list menu d-none" onClick="openNav()" />
          <div className="col-3 side-bar">
            <h2 style={{ fontSize: 24 }} className="text-center mt-3">
              Quản lý tài khoản
            </h2>
            <p className="text-center flex-column">
              <img
                src={
                  ticketBooking?.imgCustomer ||
                  "https://static2.yan.vn/YanNews/2167221/202102/facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg"
                }
                className="rounded-circle"
                style={{ width: 100, margin: `0 auto`, border: "1px solid" }}
                height="100px"
              />
            </p>
            <p style={{ fontSize: 25 }} className="text-center mt-3">
              {localStorage.getItem("username")}
            </p>
            <div className="mt-3 text-center">
              <i className="bi bi-bookmark-star-fill" />
              Điểm tích lũy : 120
            </div>
            <div className="mt-3">
              <button
                onClick={handleLogout}
                type="button"
                className="log-out btn btn-outline-danger"
                style={{ display: "block" }}
              >
                <i className="bi bi-arrow-right-circle" />
                Đăng xuất
              </button>
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
          <div className=" container mx-auto my-5 col-9">
            <div style={{ boxShadow: "1px 3px 10px 5px rgba(0, 0, 0, 0.2)" }}>
              <div style={{ marginBottom: 20 }}>
                <h2
                  className="d-flex justify-content-center"
                  style={{ padding: 16 }}
                >
                  Vé đã đặt
                </h2>
              </div>
              <div style={{ marginTop: 20 }}>
                <div className="row">
                  <div className="col-md-12">
                    <div>
                      <div className="table-responsive px-5 py-3 d-flex justify-content-center flex-column">
                        <table className="table table-striped table-hover">
                          <thead>
                            <tr>
                              <th>STT</th>
                              <th>Tên Phim</th>
                              <th>Ngày Tạo</th>
                              <th>Tổng Tiền (Vnd)</th>
                              <th>Trạng Thái</th>
                              <th>Xoá</th>
                            </tr>
                          </thead>
                          <tbody>
                            {ticketBooking &&
                              ticketBooking.map((ticketBookings, index) => (
                                <tr key={index}>
                                  <td>{stt++}</td>
                                  <td scope={"row"}>
                                    {ticketBookings?.nameFilm}
                                  </td>
                                  <td>{ticketBookings?.dateBooking}</td>
                                  <td>{ticketBookings?.priceAfterDiscount}</td>
                                  <td>{ticketBookings?.statusTicket}</td>
                                  <td>
                                    <button
                                      type="button"
                                      className="btn btn-danger"
                                      data-bs-toggle="modal"
                                      data-bs-target="#exampleModal"
                                      onClick={() =>
                                        handleDeleteTicket(
                                          ticketBookings.idTicket
                                        )
                                      }
                                    >
                                      <svg
                                        xmlns="http://www.w3.org/2000/svg"
                                        width={16}
                                        height={16}
                                        fill="currentColor"
                                        className="bi bi-trash"
                                        viewBox="0 0 16 16"
                                      >
                                        <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6Z" />
                                        <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1ZM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118ZM2.5 3h11V2h-11v1Z" />
                                      </svg>
                                    </button>
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
                            pageRangeDisplayed={2}
                            marginPagesDisplayed={1}
                            previousLabel="<"
                            containerClassName="pagination"
                            pageClassName="page-item"
                            pageLinkClassName="page-link"
                            nextClassName="page-item"
                            nextLinkClassName="page-link"
                            previousClassName="page-item"
                            previousLinkClassName="page-link"
                            activeClassName="active"
                            disabledClassName="d-none"
                          />
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <ToastContainer />
      {/*Modal xoá*/}
      <div
        className="modal fade"
        id="exampleModal"
        tabIndex="-1"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
      >
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h1 className="modal-title fs-5" id="exampleModalLabel">
                Hủy Đặt Vé
              </h1>
              <button
                type="button"
                className="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div className="modal-body">Bạn Có Muốn Hủy Vé Này Không ?</div>
            <div className="modal-footer">
              <button
                type="button"
                className="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                Hủy Bỏ
              </button>
              <button
                onClick={() => handleDelete()}
                type="button"
                className="btn btn-primary"
                data-bs-dismiss="modal"
              >
                Xóa
              </button>
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
}
