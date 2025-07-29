import {
  apiGetListSeatLanhNM,
  apiUpdateTypeSeatNormal,
  apiUpdateTypeSeatVip,
} from "../../service/SeatService";

import React, { useEffect, useState } from "react";
import "./show-room.css";
import { useParams } from "react-router";
import { getShowRoom } from "../../service/ShowRoomService";
import { Link } from "react-router-dom";
import Header from "../common/header/Header";
import Footer from "../common/footer/Footer";

const positionStatus = {
  1: "normal",
  2: "vip",
};

const seatRows = ["G", "F", "E", "D", "C", "B", "A"];

const DetailShowRoom = () => {
  const [showRoom, setShowRoom] = useState(null);
  const [allSeatByRow, setAllSeatByRow] = useState([]);
  const [listSelecting, setListSelecting] = useState([]);
  const param = useParams();
  const token = localStorage.getItem("token");
  const fetchListPosition = async () => {
    const res = await apiGetListSeatLanhNM(param.id, token);
    const resShowRoom = await getShowRoom(param.id, token);
    setShowRoom(resShowRoom);
    const positionsByRow = orderSeatByRow(res);
    setAllSeatByRow(positionsByRow);
  };

  const handlerSelecting = async (seatId, seatType) => {
    if (seatType === 2) {
      await apiUpdateTypeSeatVip(seatId, token);
      const res = await apiGetListSeatLanhNM(param.id, token);
      const positionsByRow = orderSeatByRow(res);
      setAllSeatByRow(positionsByRow);
    } else {
      await apiUpdateTypeSeatNormal(seatId, token);
      const res = await apiGetListSeatLanhNM(param.id, token);
      const positionsByRow = orderSeatByRow(res);
      setAllSeatByRow(positionsByRow);
    }
  };

  useEffect(() => {
    fetchListPosition();
  }, []);

  const orderSeatByRow = (data) => {
    const seats = seatRows
      .map((r) => ({
        rowLabel: r,
        positions: data
          .filter((item) => item.nameSeat.includes(r))
          .filter((item1, index, self) => {
            // Lọc các phần tử có tên trùng nhau
            return (
              index === self.findIndex((t) => t.nameSeat === item1.nameSeat)
            );
          })

          .map((seatItem) => ({
            seatId: seatItem.idSeat,
            name: seatItem.nameSeat,
            type: seatItem.typeSeat.idTypeSeat,
          })),
      }))
      .filter((row) => row.positions.length > 0);
    return seats;
  };

  return (
    <>
      <Header />
      allSeatByRow && (
      <>
        <div className="container-lg" style={{ marginTop: "150px" }}>
          <div className="row mt-4">
            <div className=" row col-12 col-md-12">
              <div className="select-position-wrapper">
                <h2 style={{ fontSize: 20, color: "#fff", fontWeight: 500 }}>
                  Phòng chiếu:{" "}
                  <span
                    style={{ fontSize: 20, color: "#fff", fontWeight: 700 }}
                  >
                    {showRoom && showRoom.nameShowRoom}
                  </span>
                </h2>
                <div className="position-pick">
                  <div className="situations">
                    {allSeatByRow.map((row) => (
                      <div
                        key={row.rowLabel}
                        className="d-flex justify-content-around"
                      >
                        <div className="row-label">{row.rowLabel}</div>
                        <div className="row-positions d-flex">
                          {row.positions.map((p, index) => (
                            <div
                              key={p.seatId}
                              className={`position-item ${
                                positionStatus[p.type]
                              } ${
                                listSelecting.includes(p.seatId)
                                  ? "selecting"
                                  : ""
                              }`}
                              onClick={() => handlerSelecting(p.seatId, p.type)}
                            >
                              {p.name.slice(1)}
                            </div>
                          ))}
                        </div>
                        <div className="row-label">{row.rowLabel}</div>
                      </div>
                    ))}
                  </div>
                  <div className="col-4 m-auto text-center screen">
                    Màn hình
                  </div>
                  <div className="position-info row d-flex justify-content-center">
                    <div className="col-8 col-md-12 col-sm-12">
                      <div className="row">
                        <div className="col-12 col-md-6 col-sm-6 d-flex align-items-center justify-content-center">
                          <div className="selecting label"></div>
                          <span>Ghế Vip</span>
                        </div>
                        <div className="col-12 col-md-6 col-sm-6 d-flex align-items-center justify-content-center">
                          <div className="sell label"></div>
                          <span>Ghế thường</span>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div className="d-flex justify-content-center mt-4 mb-4 gap-2">
          <button style={{ borderRadius: 10, height: 40 }} type="button">
            <Link
              style={{
                textDecoration: "none",
                color: "#fff",
                fontSize: 20,
                fontWeight: 600,
                lineHeight: "50%",
              }}
              to={`/admin/showroom/list`}
            >
              Quay lại
            </Link>
          </button>
        </div>
      </>
      )
      <Footer />
    </>
  );
};
export default DetailShowRoom;
