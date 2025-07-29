import "./detail-discount.css";
import React, { useEffect, useState } from "react";
import {
  findAllDiscount,
  findByIdDiscount,
} from "../../service/discount/DiscountService";
import { useNavigate, useParams } from "react-router";
import { Link } from "react-router-dom";
import Footer from "../common/footer/Footer";
import Header from "../common/header/Header";
import { border } from "@mui/system";

export function DetailDiscount() {
  const [discount, setDiscount] = useState({});
  const [listDiscount, setListDiscount] = useState(null);
  const param = useParams();
  const navigate = useNavigate();
  useEffect(() => {
    const fetchApi = async () => {
      const result = await findAllDiscount();
      const result1 = await findByIdDiscount(param.id);
      setDiscount(result1);
      console.log(result1);
      document.title = result1.nameDiscount;
      setListDiscount(result);
    };
    fetchApi();
  }, []);
  return (
    <>
      <Header />
      {discount && listDiscount && (
        <div
          className="d-flex justify-content-center"
          style={{ margin: "150px 0" }}
        >
          <div className="container">
            <h1 style={{ fontSize: 28, marginBottom: 20 }}>
              {discount.nameDiscount}
            </h1>
            <div className="row mx-0 ">
              <div className="col-8">
                <b>Nội dung:</b> {discount.describeDiscount}
                <br />
                <br />
                <div className="col-6" style={{ display: "inline" }}>
                  <div>
                    <p>
                      <strong>Ngày bắt đầu:</strong> {discount.dateStart}
                    </p>
                    <p>
                      <strong>Ngày kết thúc:</strong> {discount.dateEnd}
                    </p>
                    <p>
                      <strong>Giảm giá vé: </strong>
                      {discount.percentDiscount}%
                    </p>
                  </div>
                  <div
                    style={{
                      width: "50%",
                      marginLeft: "20%",
                      marginBottom: "10px",
                    }}
                  >
                    <img
                      className="box-shadow-card"
                      style={{ width: "100%", alignContent: "center" }}
                      src={discount.imageDiscount}
                      alt=""
                    />
                  </div>
                  <div>
                    <h4>Điều kiện áp dụng</h4>
                    <ul>
                      <li>Dành cho khách hàng thành viên</li>
                      <li>
                        Mỗi khách hàng mua tối đa 01 Combo 1/ Combo 2 U22 mỗi
                        lần
                      </li>
                      <li>
                        Trong mọi trường hợp ,quyết định của DN Cinema là quyết
                        định cuối cùng
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
              <div className="col-4">
                <div
                  style={{
                    marginRight: 80,
                    backgroundColor: "#f7f8f9",
                    paddingTop: 9,
                  }}
                >
                  <span
                    style={{
                      backgroundColor: "#f26b38",
                      color: "aliceblue",
                      fontSize: "20p",
                      padding: 10,
                    }}
                  >
                    <b>MUA VÉ NHANH</b>
                  </span>
                  <br />
                  <br />
                  <br />
                  <div style={{ padding: 16 }}>
                    <div>
                      <select className="form-select">
                        <option selected="">Chọn phim</option>
                        <option value="">Fast And Furious</option>
                        <option value="">Lật mặt 6</option>
                        <option value="">Bố già</option>
                      </select>
                    </div>
                    <br />
                    <div>
                      <select className="form-select">
                        <option selected="">Chọn rạp</option>
                        <option value="">ĐN Tân Bình</option>
                        <option value="">ĐN Đà Nẵng</option>
                        <option value="">ĐN Bình Thạnh</option>
                        <option value="">ĐN Sơn La</option>
                        <option value="">ĐN Bến Tre</option>
                        <option value="">ĐN Cà Mau</option>
                      </select>
                    </div>
                    <br />
                    <div>
                      <select className="form-select">
                        <option selected="">Chọn ngày</option>
                        <option value="">Thứ hai,25/5/2023</option>
                        <option value="">Thứ ba,26/5/2023</option>
                        <option value="">Thứ tư,27/5/2023</option>
                      </select>
                    </div>
                    <br />
                    <div>
                      <select className="form-select">
                        <option selected="">Chọn suất</option>
                        <option value="">10:00, 2D - Phụ đề</option>
                        <option value="">12:00, 2D - Phụ đề</option>
                        <option value="">1:00, 2D - Phụ đề</option>
                      </select>
                    </div>
                  </div>
                  <button className=" btn-secondary">Mua vé</button>
                </div>
                <br />
                <h5>PHIM HOT THÁNG 5</h5>
                <div
                  id="carouselExampleControls"
                  className="carousel slide"
                  data-bs-ride="carousel"
                >
                  <div className="carousel-inner">
                    <div className="carousel-item active">
                      <img
                        src="https://cdn.galaxycine.vn/media/2023/5/9/450x300_1683602206164.jpg"
                        className="d-block w-100"
                        alt="..."
                      />
                    </div>
                  </div>
                  <button
                    className="carousel-control-prev"
                    type="button"
                    data-bs-target="#carouselExampleControls"
                    data-bs-slide="prev"
                  >
                    <span
                      className="carousel-control-prev-icon"
                      aria-hidden="true"
                    />
                    <span className="visually-hidden">Previous</span>
                  </button>
                  <button
                    className="carousel-control-next"
                    type="button"
                    data-bs-target="#carouselExampleControls"
                    data-bs-slide="next"
                  >
                    <span
                      className="carousel-control-next-icon"
                      aria-hidden="true"
                    />
                    <span className="visually-hidden">Next</span>
                  </button>
                </div>
              </div>
            </div>
            <div className="event">
              <div className="row mx-0" style={{ marginTop: 100 }}>
                <div style={{ backgroundColor: "#f26b38" }}>
                  <h3 style={{ color: "white" }}>Các khuyến mãi khác </h3>
                </div>
              </div>
              <div className="row mx-0">
                {listDiscount &&
                  listDiscount.map((isDiscount, index) => {
                    if (index < 3) {
                      return (
                        <div className="col-md-4" style={{ paddingTop: 20 }}>
                          <div
                            className="card"
                            style={{
                              width: 400,
                              backgroundColor: "rgb(0 0 0)",
                            }}
                          >
                            <Link
                              to={"/detail-discount/" + isDiscount.idDiscount}
                            >
                              <img
                                style={{ height: 500 }}
                                src={isDiscount.imageDiscount}
                                className="image"
                              />
                              <div className="readmore">
                                <p style={{ color: "white" }}>
                                  <b>{isDiscount.nameDiscount}</b>
                                  <p>"Ngày bắt đầu:"{isDiscount.dateStart}</p>
                                  <p>Ngày kết thúc:"{isDiscount.dateEnd}</p>
                                  <p>Giảm giá:{isDiscount.percentDiscount}</p>
                                </p>
                                <div
                                  className="text"
                                  style={{ marginTop: 200 }}
                                >
                                  Chi tiết
                                </div>
                              </div>
                            </Link>
                          </div>
                        </div>
                      );
                    }
                  })}
              </div>
            </div>
          </div>
        </div>
      )}
      <Footer />
    </>
  );
}
