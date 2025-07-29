import { Link, useLocation } from "react-router-dom";
import { FaSearch } from "react-icons/fa";
import { Avatar } from "@mui/material";
import "./index.css";
import Dropdown from "react-bootstrap/Dropdown";
import { useEffect } from "react";
import { useState } from "react";
import { useSelector } from "react-redux";
import React from "react";
import { findCustomerByNameAccount } from "../../../service/CustomerServiceTruongNN";

const Header = () => {
  const username = localStorage.getItem("username");
  const account = JSON.parse(localStorage.getItem("account"));
  const [user, setUser] = useState(null);
  const roles = [];
  if (account != null) {
    for (let i = 0; i < account.roles.length; i++) {
      roles.push(account.roles[i].authority);
    }
  }
  const handleLogout = () => {
    localStorage.clear();
    window.location.href = "/";
  };
  useEffect(() => {
    document.title = "Lịch sử cộng điểm";
    console.log(username);
    const findCustomerByUsername = async () => {
      const result = await findCustomerByNameAccount(username);
      setUser(result);
      console.log(result);
    };
    findCustomerByUsername();
  }, []);
  return (
    <>
      <header className="header" data-header="">
        <div className="container">
          <div data-overlay="" />
          <Link to={"/"} className="logo">
            <img
              src={`${process.env.PUBLIC_URL}/assets/img/home/logo.png`}
              alt="Filmlane logo"
              height="80"
              width="160"
            />
          </Link>
          <div className="header-actions">
            <div className="search-box">
              <button className="btn-search d-flex">
                <FaSearch />
              </button>
              <input
                type="text"
                className="input-search"
                placeholder="Tìm kiếm phim..."
              />
            </div>
            {username ? (
              <Dropdown>
                <Dropdown.Toggle
                  variant="transparent"
                  className="d-flex justify-content-center align-items-center border-0"
                >
                  <Avatar>{username[0].toUpperCase()}</Avatar>
                </Dropdown.Toggle>

                <Dropdown.Menu>
                  {roles.includes("ADMIN") ? (
                    <Dropdown.Item
                      to="/admin/employee/list"
                      className="text-decoration-none"
                    >
                      <Link
                        to="/admin/employee/list"
                        className="text-dark text-decoration-none"
                      >
                        Quản lý nhân viên
                      </Link>
                    </Dropdown.Item>
                  ) : (
                    ""
                  )}
                  {roles.includes("ADMIN") ? (
                    <Dropdown.Item className="text-decoration-none">
                      <Link
                        to={"/admin/film/list"}
                        className="text-dark text-decoration-none"
                      >
                        Quản lý phim
                      </Link>
                    </Dropdown.Item>
                  ) : (
                    ""
                  )}
                  {roles.includes("ADMIN") || roles.includes("EMPLOYEE") ? (
                    <Dropdown.Item className="text-decoration-none">
                      <Link
                        className="text-dark text-decoration-none"
                        to={"/admin/customer/list"}
                      >
                        Danh sách khách hàng
                      </Link>
                    </Dropdown.Item>
                  ) : (
                    ""
                  )}
                  {roles.includes("EMPLOYEE") ? (
                    <Dropdown.Item className="text-decoration-none">
                      <Link
                        to={"/employee/ticket/list"}
                        className="text-dark text-decoration-none"
                      >
                        Quản lý vé
                      </Link>
                    </Dropdown.Item>
                  ) : (
                    ""
                  )}
                  {roles.includes("ADMIN") ? (
                    <Dropdown.Item className="text-decoration-none">
                      <Link
                        to={"/admin/showroom/list"}
                        className="text-dark text-decoration-none"
                      >
                        Quản lý phòng chiếu
                      </Link>
                    </Dropdown.Item>
                  ) : (
                    ""
                  )}
                  {roles.includes("ADMIN") ? (
                    <Dropdown.Item className="text-decoration-none">
                      <Link
                        className="text-dark text-decoration-none"
                        to="/admin/discount/list"
                      >
                        Quản lý khuyến mãi
                      </Link>
                    </Dropdown.Item>
                  ) : (
                    ""
                  )}
                  {roles.includes("ADMIN") ? (
                    <Dropdown.Item className="text-decoration-none">
                      <Link
                        to={"/admin/statistic-film"}
                        className="text-dark text-decoration-none"
                      >
                        Thống kê phim
                      </Link>
                    </Dropdown.Item>
                  ) : (
                    ""
                  )}
                  {roles.includes("ADMIN") ? (
                    <Dropdown.Item className="text-decoration-none">
                      <Link
                        to={"/admin/statistic-customer"}
                        className="text-dark text-decoration-none"
                      >
                        Thống kê khách hàng
                      </Link>
                    </Dropdown.Item>
                  ) : (
                    ""
                  )}
                  {roles.includes("ADMIN") || roles.includes("EMPLOYEE") ? (
                    <Dropdown.Divider />
                  ) : (
                    ""
                  )}
                  <Dropdown.Item className="text-decoration-none">
                    <Link
                      to={"/ticket-customer/history"}
                      className="text-dark text-decoration-none"
                    >
                      Lịch sử đặt vé
                    </Link>
                  </Dropdown.Item>
                  {user && (
                    <Dropdown.Item className="text-decoration-none">
                      <Link
                        to={"/customer/change-information/" + user.idCustomer}
                        className="text-dark text-decoration-none"
                      >
                        Quản lý tài khoản
                      </Link>
                    </Dropdown.Item>
                  )}
                  <Dropdown.Item
                    onClick={handleLogout}
                    className="text-decoration-none"
                  >
                    Đăng xuất
                  </Dropdown.Item>
                </Dropdown.Menu>
              </Dropdown>
            ) : (
              <Link to="/login" className="login-btn text-decoration-none">
                ĐĂNG NHẬP
              </Link>
            )}
          </div>
          <button className="menu-open-btn" data-menu-open-btn="">
            <ion-icon name="reorder-two" />
          </button>
          <nav className="navbar" data-navbar="">
            <div className="navbar-top">
              <a href="./index.html" className="logo">
                <img src="assets\img\home\logo.png" alt="Filmlane logo" />
              </a>
              <button className="menu-close-btn" data-menu-close-btn="">
                <ion-icon name="close-outline" />
              </button>
            </div>
            <ul className="navbar-list">
              <li>
                <Link to="/" className="navbar-link text-decoration-none">
                  Trang chủ
                </Link>
              </li>
              <li>
                <Link to="/film" className="navbar-link text-decoration-none">
                  Phim
                </Link>
              </li>
              <li>
                <Link
                  to="/booking-ticket"
                  className="navbar-link text-decoration-none"
                >
                  Đặt vé
                </Link>
              </li>
              <li>
                <Link
                  to="/discount"
                  className="navbar-link text-decoration-none"
                >
                  Khuyến mãi
                </Link>
              </li>
              <li>
                <Link
                  to="/show-time"
                  className="navbar-link  text-decoration-none"
                >
                  Lịch chiếu
                </Link>
              </li>
            </ul>
          </nav>
        </div>
      </header>
    </>
  );
};
export default Header;
