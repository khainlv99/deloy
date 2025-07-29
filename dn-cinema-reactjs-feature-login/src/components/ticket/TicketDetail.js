import React, { useEffect, useRef, useState } from "react";
import { useNavigate, useParams } from "react-router";
import * as ticketService from "../../service/InformationTicketService";
import "../film/detailFilm.css";
import { Form, Formik } from "formik";
import { useReactToPrint } from "react-to-print";
import { TicketPrint } from "./TicketPrint";
import { format } from "date-fns";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import Header from "../common/header/Header";
import Footer from "../common/footer/Footer";

export default function TicketDetail() {
  const auth = localStorage.getItem("token");
  const param = useParams();
  const navigate = useNavigate();
  const [destroyceTicket, setDestroyceTicket] = useState();
  const componentBRef = useRef(null);
  const [ticketDetail, setTicketDetail] = useState();
  const dateTicket = new Date(
    ticketDetail?.ticketSet[0].seat.showTime.showDate
  );
  const currentDate = new Date();
  const timeTicket = ticketDetail?.ticketSet[0].seat.showTime.showTime;
  const [valueTicket, setValueTicket] = useState({
    idTicket: "",
    idCustomer: "",
    idSeat: "",
    nameTypeSeat: "",
  });
  const [showButtonReturn, setShowButtonReturn] = useState(false);
  const [selectedOption, setSelectedOption] = useState("");
  const handlePrint = useReactToPrint({
    content: () => componentBRef.current,
    pageStyle: "@page { size: A4; margin: 0px; }",
    onAfterPrint: () => {
      const confirmPrintTicket = async () => {
        try {
          await ticketService.confirmPrintTicket(valueTicket.idTicket, auth);
          toast.success("In vé thành công");
          navigate("/employee/ticket/list");
        } catch (error) {
          console.log(error);
        }
      };
      confirmPrintTicket();
    },
  });
  useEffect(() => {
    const detail = async () => {
      try {
        const res = await ticketService.detail(param.id, auth);
        console.log(res);
        setTicketDetail(res);
      } catch (err) {
        console.log(err);
      }
    };

    detail();
  }, [param.id]);
  useEffect(() => {
    if (currentDate.getDate() === dateTicket.getDate()) {
      dateTicket.setHours(timeTicket.slice(0, 2), timeTicket.slice(3), 0, 0);
      const timeDiff = dateTicket.getTime() - currentDate.getTime();
      const minutesDiff = Math.floor(timeDiff / (1000 * 60));
      setDestroyceTicket(minutesDiff);
      console.log(minutesDiff);
    }
  });
  if (!ticketDetail) {
    return null;
  }
  const handleReturnTicket = (idTicket, idSeat, nameTypeSeat) => {
    setValueTicket({
      idTicket: idTicket,
      idCustomer: ticketDetail?.idCustomer,
      idSeat: idSeat,
      nameTypeSeat: nameTypeSeat,
    });

    if (selectedOption === idSeat) {
      setShowButtonReturn(false);
      setSelectedOption("");
    } else {
      setShowButtonReturn(true);
      setSelectedOption(idSeat);
    }
  };
  console.log(ticketDetail);
  console.log(selectedOption);
  return (
    <>
      <Header />
      <div
        className="container mt-5"
        style={{ marginTop: 100, marginBottom: 200 }}
      >
        <div>
          <i type="button" className="bi bi-house-fill text-secondary">
            <span className="fst-normal">TRANG CHỦ</span>
          </i>
        </div>
      </div>
      <div className="container shadow-movie bg-white mt-3">
        <div>
          <div className="row mx-0 pt-5 pb-5">
            <div className="col-3 ">
              <div className="detail-feat-img">
                <img
                  src={ticketDetail?.ticketSet[0].seat.showTime.film.imgFilm}
                  className="loaded img-hover"
                  data-was-processed="true"
                />
              </div>
            </div>
            <div className="col-5  text-secondary">
              <div>
                <h3 className="text-white bg-movie py-2 text-center">
                  XÁC NHẬN ĐẶT VÉ
                </h3>
              </div>
              <hr className="text-movie" />
              <div>
                <div className="detail-rating border border-2 p-3">
                  <div className="mt-2">
                    <Formik
                      initialValues={{
                        idTicket: "",
                        idCustomer: "",
                        idSeat: "",
                        nameTypeSeat: "",
                      }}
                      onSubmit={() => {
                        const deleteTicket = async () => {
                          try {
                            await ticketService.deleteTicket(valueTicket, auth);
                          } catch (e) {
                            console.log(e);
                          }
                        };
                        deleteTicket();
                        setTimeout(async () => {
                          toast("Hủy vé thành công");
                        }, 2000);
                        navigate("/employee/ticket/list");
                      }}
                    >
                      <Form>
                        <table>
                          <thead>
                            <tr style={{ height: 39 }}>
                              <th className="text-secondary">Tên phim:</th>
                              <td>
                                {
                                  ticketDetail?.ticketSet[0].seat.showTime.film
                                    .nameFilm
                                }
                              </td>
                            </tr>
                          </thead>
                          <tbody>
                            <tr style={{ height: 39 }}>
                              <th className="text-secondary">Rạp:</th>
                              <td>
                                {
                                  ticketDetail?.ticketSet[0].seat.showRoom
                                    .nameShowRoom
                                }
                              </td>
                            </tr>
                            <tr style={{ height: 39 }}>
                              <th className="text-secondary">Ngày chiếu :</th>
                              <td style={{ fontFamily: "Roboto" }}>
                                {" "}
                                {format(
                                  new Date(
                                    ticketDetail?.ticketSet[0].seat.showTime.showDate
                                  ),
                                  "dd/MM/yyyy"
                                )}
                              </td>
                            </tr>
                            <tr style={{ height: 39 }}>
                              <th className="text-secondary">Giờ chiếu:</th>
                              <td>
                                {
                                  ticketDetail?.ticketSet[0].seat.showTime
                                    .showTime
                                }
                              </td>
                            </tr>
                            <tr style={{ height: 39 }}>
                              <th className="text-secondary">Số lượng vé:</th>
                              <td>{ticketDetail?.ticketSet.length}</td>
                            </tr>
                            <tr style={{ height: 39 }}>
                              <th className="text-secondary align-top">Ghế:</th>
                              <td>
                                {ticketDetail?.ticketSet
                                  .filter(
                                    (element) =>
                                      element.seat.seat.idStatusSeat === 1 &&
                                      element.delete === false
                                  )
                                  .map((element, index) => (
                                    <div className="mb-3" key={index}>
                                      <input
                                        type="radio"
                                        checked={
                                          +selectedOption ===
                                          element.seat.idSeat
                                        }
                                        value={element.seat.idSeat}
                                        onClick={() =>
                                          handleReturnTicket(
                                            element.idTicket,
                                            element.seat.idSeat,
                                            element.seat.typeSeat.nameTypeSeat
                                          )
                                        }
                                        class="form-check-input"
                                        name="inlineRadioOptions"
                                        id={element.seat.idSeat}
                                      />
                                      <label
                                        class="form-check-label ms-2"
                                        for={element.seat.idSeat}
                                      >
                                        {element.seat.nameSeat}
                                        {element.seat.typeSeat.nameTypeSeat ===
                                        "VIP"
                                          ? " (Ghế VIP)"
                                          : " (Ghế thường)"}
                                        <span
                                          className="ms-3"
                                          style={{ color: "lightcoral" }}
                                        >
                                          {element.priceAfterDiscount.toLocaleString(
                                            "vi-VN",
                                            {
                                              style: "currency",
                                              currency: "VND",
                                            }
                                          )}
                                        </span>
                                      </label>
                                    </div>
                                  ))}
                              </td>
                            </tr>
                            <tr className="" style={{ height: 39 }}>
                              <th className="text-secondary">Tổng tiền:</th>
                              <td>
                                <span style={{ color: "lightcoral" }}>
                                  {ticketDetail?.ticketSet
                                    .filter(
                                      (element) =>
                                        element.seat.seat.idStatusSeat === 1 &&
                                        element.delete === false
                                    )
                                    .map(
                                      (element, index) =>
                                        element.priceAfterDiscount
                                    )
                                    .reduce((accumulator, currentValue) => {
                                      return accumulator + currentValue;
                                    }, 0)
                                    .toLocaleString("vi-VN", {
                                      style: "currency",
                                      currency: "VND",
                                    })}
                                </span>
                              </td>
                              <td></td>
                            </tr>
                            <tr style={{ height: 39 }}>
                              {showButtonReturn ? (
                                destroyceTicket < 60 ||
                                destroyceTicket === undefined ? (
                                  <td>
                                    <button
                                      type="button"
                                      onClick={() => {
                                        toast("Đã quá thời gian hủy vé");
                                      }}
                                      className="btn button-movie h-100 border border-0"
                                      style={{ width: 100 }}
                                    >
                                      Hủy vé
                                    </button>
                                  </td>
                                ) : (
                                  <td>
                                    <button
                                      type="submit"
                                      className="btn button-movie h-100 border border-0"
                                      style={{ width: 100 }}
                                    >
                                      Hủy vé
                                    </button>
                                  </td>
                                )
                              ) : (
                                <td>
                                  <span
                                    type="button"
                                    className="btn btn-secondary h-100 border border-0"
                                    style={{ width: 100 }}
                                  >
                                    Hủy vé
                                  </span>
                                </td>
                              )}
                              {showButtonReturn ? (
                                <td>
                                  <button
                                    type="button"
                                    className="button-movie btn h-100 border border-0"
                                    style={{ width: 120 }}
                                    data-bs-toggle="modal"
                                    data-bs-target="#staticBackdrop"
                                  >
                                    Xác nhận
                                  </button>
                                </td>
                              ) : (
                                <td>
                                  <span
                                    type="button"
                                    className="btn btn-secondary h-100 border border-0"
                                    style={{ width: 100 }}
                                  >
                                    Xác nhận
                                  </span>
                                </td>
                              )}
                            </tr>
                          </tbody>
                        </table>
                      </Form>
                    </Formik>
                  </div>
                </div>
              </div>
            </div>
            <div className="col-4  text-secondary">
              <div>
                <h3 className="text-white bg-movie py-2  text-center">
                  THÔNG TIN THÀNH VIÊN
                </h3>
              </div>
              <hr className="text-movie" />
              <div className="detail-rating border border-2 p-3 ">
                <div className={"mt-2"}>
                  <table>
                    <thead>
                      <tr style={{ height: 39 }}>
                        <th className="text-secondary">Mã thành viên:</th>
                        <td>{ticketDetail?.idCustomer}</td>
                      </tr>
                    </thead>
                    <tbody>
                      <tr style={{ height: 39 }}>
                        <th className="text-secondary">Họ và tên:</th>
                        <td>{ticketDetail?.nameCustomer}</td>
                      </tr>
                      <tr style={{ height: 39 }}>
                        <th className="text-secondary">CMND:</th>
                        <td>{ticketDetail?.identityCard}</td>
                      </tr>
                      <tr style={{ height: 39 }}>
                        <th className="text-secondary" style={{ width: 150 }}>
                          Điểm thành viên:{" "}
                        </th>
                        <td>{ticketDetail?.pointCustomer}</td>
                      </tr>
                      <tr>
                        <th className="text-secondary" style={{ height: 39 }}>
                          Số điện thoại:
                        </th>
                        <td>{ticketDetail?.phone}</td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      {/*modal in vé*/}
      <div
        className="modal fade"
        id="staticBackdrop"
        data-bs-backdrop="static"
        data-bs-keyboard="false"
        tabIndex="-1"
        aria-labelledby="staticBackdropLabel"
        aria-hidden="true"
      >
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h5 className="modal-title" id="staticBackdropLabel">
                In vé xem phim
              </h5>
              <button
                type="button"
                className="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div className="modal-body">
              <div ref={componentBRef}>
                <TicketPrint
                  ticketDetail={ticketDetail}
                  idTicket={valueTicket.idTicket}
                />
              </div>
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
                type="button"
                className="button-movie btn h-100 border border-0"
                onClick={() => {
                  handlePrint();
                }}
                data-bs-dismiss="modal"
              >
                In vé
              </button>
            </div>
          </div>
        </div>
      </div>
      <ToastContainer />
      <Footer />
    </>
  );
}
