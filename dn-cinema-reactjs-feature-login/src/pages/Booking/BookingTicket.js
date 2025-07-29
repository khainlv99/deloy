import React, { useState } from "react";
import "./BookingTicket.css";
import SelectPosition from "../../components/booking-ticket/SelectPosition";
import SelectShowTime from "../../components/booking-ticket/SelectShowTime";
import { apiBookingTicket } from "../../service/BookingTicketService";
import { ConfirmTicket } from "../../components/confirm-ticket/ConfirmTicket";
import Payment from "../../components/booking-ticket/Payment";
import Header from "../../components/common/header/Header";
import Footer from "../../components/common/footer/Footer";
import { useEffect } from "react";

const BookingTicket = () => {
  const [data, setData] = useState();
  const [step, setStep] = useState(1);
  const [listSelecting, setListSelecting] = useState([]);
  useEffect(() => {
    document.title = "Đặt vé phim";
  }, []);
  const handleDataShowTimeData = (film, showTime) => {
    setData({ film, showTime });
    setStep(2);
  };

  const handleBuyTicket = async (listSeat) => {
    setListSelecting(listSeat);
    const res = await apiBookingTicket(listSeat);
    if (res.status === 200) {
      setStep(3);
    }
  };

  return (
    <>
      <Header />
      <div className="booking-ticket">
        {step === 1 && <SelectShowTime onFinish={handleDataShowTimeData} />}
        {step === 2 && (
          <SelectPosition
            filmData={data}
            onFinish={handleBuyTicket}
            onBack={() => setStep(1)}
          />
        )}
        {step === 3 && (
          <ConfirmTicket filmData={data} listSelectingData={listSelecting} />
        )}
      </div>
      <Footer />
    </>
  );
};
export default BookingTicket;
