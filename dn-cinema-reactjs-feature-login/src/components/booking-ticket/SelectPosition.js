import { toast } from "react-toastify";
import { apiGetListSeat } from "../../service/SeatService";
import "./booking-ticket.css";
import React, { useEffect, useState } from "react";
import { format } from "date-fns";
const positionStatus = {
  1: "sell",
  2: "available",
  3: "unavailable",
};

const seatRows = ["I", "H", "G", "F", "E", "D", "C", "B", "A"];

const SelectPosition = (props) => {
  const { onFinish, onBack, filmData } = props;
  const [allSeat, setAllSeat] = useState([]);
  const [allSeatByRow, setAllSeatByRow] = useState([]);
  const [listSelecting, setListSelecting] = useState([]);
  const [numberOfSeat, setNumberOfSeat] = useState(1);

  const fetchListPosition = async () => {
    const res = await apiGetListSeat(filmData.showTime.idShowTime);
    setAllSeat(res);
    const positionsByRow = orderSeatByRow(res);
    setAllSeatByRow(positionsByRow);
  };

  const orderSeatByRow = (data) => {
    const seats = seatRows
      .map((r) => ({
        rowLabel: r,
        positions: data
          .filter((item) => item.nameSeat.includes(r))
          .map((seatItem) => ({
            seatId: seatItem.idSeat,
            name: seatItem.nameSeat,
            status: seatItem.seat.idStatusSeat,
          })),
      }))
      .filter((row) => row.positions.length > 0);
    return seats;
  };

  const handlerSelecting = async (seatId, seatStatus) => {
    if (seatStatus === 2) {
      if (!listSelecting.includes(seatId)) {
        setListSelecting([...listSelecting, seatId]);
      } else {
        const newList = [...listSelecting].filter((item) => item !== seatId);
        setListSelecting(newList);
      }
    }
  };

  const handleContinue = () => {
    if (listSelecting.length !== numberOfSeat) {
      toast.error("Vui lòng chọn đúng số lượng ghế", { autoClose: 3000 });
    } else {
      onFinish(listSelecting);
    }
  };
  const calTotalPrice = () => {
    const selectectSeat = allSeat.filter((seat) =>
      listSelecting.includes(seat.idSeat)
    );
    const priceArray = selectectSeat.map((seat) =>
      seat.typeSeat.idTypeSeat === 1
        ? filmData.film.normalSeatPrice
        : filmData.film.vipSeatPrice
    );
    return priceArray.reduce((a, b) => a + b, 0);
  };

  useEffect(() => {
    fetchListPosition();
  }, []);
  const increaseQuantity = () => {
    if (numberOfSeat < 8) {
      setNumberOfSeat(numberOfSeat + 1);
    }
  };
  const decreaseQuantity = () => {
    if (numberOfSeat > 1) {
      setNumberOfSeat(numberOfSeat - 1);
    }
  };

  const maxValue = 8;
  const handleChange = (e) => {
    const enterValue = +e.target.value;
    if (enterValue <= maxValue) {
      setNumberOfSeat(enterValue);
    }
  };

  return (
    <div className="container-lg">
      <div className="row mt-4">
        <div className="col-12 col-md-8">
          <div className="select-position-wrapper">
            <h3 className="title">Chọn ghế</h3>
            <div className="number-of-position mb-3">
              <span>Số lượng ghế</span>
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="20"
                height="20"
                fill="currentColor"
                className="bi bi-dash-circle-fill"
                viewBox="0 0 16 16"
                onClick={decreaseQuantity}
              >
                <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM4.5 7.5a.5.5 0 0 0 0 1h7a.5.5 0 0 0 0-1h-7z" />
              </svg>
              <input
                type="number"
                value={numberOfSeat}
                onChange={handleChange}
                className="hideNumberArrows"
              />
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="20"
                height="20"
                fill="currentColor"
                className="bi bi-plus-circle-fill"
                viewBox="0 0 16 16"
                onClick={() => {
                  increaseQuantity();
                }}
              >
                <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM8.5 4.5a.5.5 0 0 0-1 0v3h-3a.5.5 0 0 0 0 1h3v3a.5.5 0 0 0 1 0v-3h3a.5.5 0 0 0 0-1h-3v-3z" />
              </svg>
            </div>

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
                            positionStatus[p.status]
                          } ${
                            listSelecting.includes(p.seatId) ? "selecting" : ""
                          }`}
                          onClick={() => handlerSelecting(p.seatId, p.status)}
                        >
                          {p.name.slice(1)}
                        </div>
                      ))}
                    </div>
                    <div className="row-label">{row.rowLabel}</div>
                  </div>
                ))}
              </div>
              <div className="col-4 m-auto text-center screen">Màn hình</div>
              <div className="position-info row d-flex justify-content-center">
                <div className="col-8 col-md-12 col-sm-12">
                  <div className="row">
                    <div className="col-12 col-md-3 col-sm-6 d-flex align-items-center justify-content-center">
                      <div className="selecting label"></div>
                      <span>Ghế đang chọn</span>
                    </div>
                    <div className="col-12 col-md-3 col-sm-6 d-flex align-items-center justify-content-center">
                      <div className="sell label"></div>
                      <span>Ghế đã bán</span>
                    </div>
                    <div className="col-12 col-md-3 col-sm-6 d-flex align-items-center justify-content-center">
                      <div className="available label"></div>
                      <span>Có thể chọn</span>
                    </div>
                    <div className="col-12 col-md-3 col-sm-6 d-flex align-items-center justify-content-center">
                      <div className="unavailable label"></div>
                      <span>Không thể chọn</span>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div className="col-12 col-md-4">
          <div className="film-info">
            {/*<h4 className="title text-center">Phim đã chọn</h4>*/}
            <img
              className="w-100 text-center px-4"
              src={filmData?.film?.imgFilm}
              style={{ height: 300 }}
            />
            <h5 className="film-title text-center mt-2">
              {filmData?.film?.nameFilm}
            </h5>
            <span className="age-rating">T16</span>
            <span className="notice d-inline-flex" style={{ fontSize: 15 }}>
              (*) Phim chỉ dành cho khán giả từ 16 tuổi trở lên
            </span>
            <div className="font-weight-normal mb-1">
              <b style={{ fontWeight: "bold" }}> Ngày chiếu: </b>{" "}
              {format(new Date(filmData?.showTime.showDate), "dd/MM/yyyy")}
            </div>
            <div className="font-weight-normal mb-1">
              <b style={{ fontWeight: "bold" }}>Lịch chiếu phim:</b>{" "}
              {filmData.showTime.showTime}
            </div>
            <div className="font-weight-normal mb-1">
              <b style={{ fontWeight: "bold" }}>Thời lượng:</b>{" "}
              {filmData.film.timeFilm} phút
            </div>
            <div className="font-weight-normal mb-1 d-flex">
              <b style={{ fontWeight: "bold" }}>Ghế chọn :</b> &nbsp;
              <div className="d-flex gap-2 flex-wrap">
                {allSeat
                  .filter((seat) => listSelecting.includes(seat.idSeat))
                  .map((item) => (
                    <span
                      key={item.idSeat}
                      className="position-item available selecting"
                    >
                      {item.nameSeat}
                    </span>
                  ))}
              </div>
            </div>
            <div className="font-weight-normal mb-1 d-flex align-items-baseline">
              <span>
                <b style={{ fontWeight: "bold" }}>Tổng:</b>
              </span>{" "}
              &nbsp;
              <span className="total-price">{calTotalPrice()}</span>&nbsp;
              <span>VND</span>
            </div>
          </div>
        </div>
      </div>
      <div className="d-flex justify-content-center mt-4 mb-4 gap-2">
        <button
          onClick={() => onBack()}
          className="d-flex btn btn-secondary"
          type="button"
          style={{ borderRadius: 10, height: 40 }}
        >
          Quay lại
        </button>
        <button
          disabled={!listSelecting.length}
          onClick={handleContinue}
          className="btn btn-primary d-flex"
          style={{ borderRadius: 10, height: 40 }}
          type="button"
        >
          Tiếp tục
        </button>
      </div>
    </div>
  );
};
export default SelectPosition;
