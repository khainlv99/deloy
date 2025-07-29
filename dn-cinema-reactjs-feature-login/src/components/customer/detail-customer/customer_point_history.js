import * as customerService from "../../../service/TicketManagementService";
import React, { useEffect, useState } from "react";
import "../detail-customer/style.css";
import ReactPaginate from "react-paginate";
import { Link } from "react-router-dom";
import { findAllPlusPoint } from "../../../service/TicketManagementService";
import { useNavigate } from "react-router";
import Header from "../../common/header/Header";
import Footer from "../../common/footer/Footer";
import { findCustomerByNameAccount } from "../../../service/CustomerServiceTruongNN";

export function CustomerPointHistory() {
  const navigate = useNavigate();
  const [pointHistory, setPointHistory] = useState([]);
  const [pageCount, setPageCount] = useState(0);
  const [page, setPage] = useState(0);
  const [size, setSize] = useState(0);
  const [user, setUser] = useState(null);
  const username = localStorage.getItem("username");
  const token = localStorage.getItem("token");
  let stt = page * size + 1;

  const handlePageClick = (event) => {
    setPage(+event.selected);
  };
  const handleLogout = () => {
    localStorage.clear();
    navigate("/");
  };
  useEffect(() => {
    document.title = "Lịch sử cộng điểm";
    const findCustomerByUsername = async () => {
      const result = await findCustomerByNameAccount(username);
      setUser(result);
    };
    findCustomerByUsername();
  }, []);
  useEffect(() => {
    const fetchApi = async () => {
      try {
        const result = await customerService.findAllTicketBookingPoint(
          page,
          token
        );
        setPointHistory(result.data.content);
        setPageCount(result.data.totalPages);
        setSize(result.data.size);
        console.log(result);
      } catch (error) {
        console.log(error);
      }
    };
    fetchApi();
  }, [page]);
  const handlePlusPoint = async () => {
    const dateStart = document.getElementById("start").value;
    const dateEnd = document.getElementById("end").value;
    const result = await findAllPlusPoint(0, dateStart, dateEnd, token);
    try {
      setPointHistory(result.data.content);
      console.log(result);
    } catch (error) {
      console.log(error);
      setPointHistory([]);
    }
  };
  return (
    <>
      <Header />
      <div className="container" style={{ margin: "150px auto" }}>
        <div className="row">
          <i className="bi bi-list menu d-none" onClick="openNav()" />
          <div className="col-3 side-bar">
            <h2 style={{ fontSize: 24 }} className="text-center mt-3">
              Quản lý tài khoản
            </h2>
            <p className="text-center flex-column">
              <img
                src={
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
                type="button"
                className="log-out btn btn-outline-danger"
                style={{ display: "block" }}
                onClick={handleLogout}
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
            <div style={{ marginBottom: 20 }}>
              <h2
                className="d-flex justify-content-center"
                style={{ padding: 16 }}
              >
                Lịch Sử Cộng Điểm
              </h2>
            </div>
            <div style={{ marginTop: 20 }}>
              <div className="row">
                <div className="col-md-12">
                  <div className="d-flex justify-content-center">
                    <form>
                      <table>
                        <tbody>
                          <tr>
                            <th style={{ float: "right", background: "white" }}>
                              <p style={{ fontSize: 14 }}>
                                Từ Ngày :{" "}
                                <span style={{ color: "red" }}>(*)</span>
                              </p>
                            </th>
                            <th style={{ background: "white" }}>
                              <input
                                id="start"
                                style={{ width: 300 }}
                                type="date"
                              />
                            </th>
                          </tr>
                          <tr>
                            <th style={{ float: "right", background: "white" }}>
                              <p style={{ fontSize: 14 }}>
                                Đến Ngày :{" "}
                                <span style={{ color: "red" }}>(*)</span>
                              </p>
                            </th>
                            <th style={{ background: "white" }}>
                              <input
                                id="end"
                                style={{ width: 300 }}
                                type="date"
                              />
                            </th>
                          </tr>
                        </tbody>
                      </table>
                      <div className="mt-3 text-center">
                        <button
                          onClick={() => handlePlusPoint()}
                          type="button"
                          className="log-out btn btn-outline-danger"
                        >
                          Xem điểm
                        </button>
                      </div>
                    </form>
                  </div>
                  {pointHistory.length === 0 ? (
                    <h3 className={"text-danger text-center my-3"}>
                      Không tìm thấy kết quả{" "}
                    </h3>
                  ) : (
                    <div className="col-md-12">
                      <div className="mt-3" style={{ width: "100%" }}>
                        <div className=" table-responsive px-5 py-3 d-flex justify-content-center flex-column">
                          <table className="table table-striped table-hover">
                            <thead>
                              <tr>
                                <th>STT</th>
                                <th>Ngày Tạo</th>
                                <th>Tên Phim</th>
                                <th>Tổng Điểm</th>
                              </tr>
                            </thead>
                            <tbody>
                              {pointHistory.map((pointHistorys, index) => (
                                <tr key={index}>
                                  <td>{stt++}</td>
                                  <td>{pointHistorys?.dateBooking}</td>
                                  <td>{pointHistorys?.nameFilm}</td>
                                  <td>{pointHistorys?.pointCustomer}</td>
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
                  )}
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
