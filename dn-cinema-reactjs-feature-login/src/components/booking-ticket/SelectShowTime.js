import {
  apiGetAllFilmToBooking,
  apiGetAllFilms,
  findFilmById,
} from "../../service/FilmService";
import "./booking-ticket.css";
import React, { useEffect, useState } from "react";
import {
  apiGetShowTimesByDate,
  apiGetShowTimesByFilm,
} from "../../service/ShowTimeService";
import { useNavigate, useParams } from "react-router";

const SelectShowTime = (props) => {
  const { onFinish } = props;
  const [allFilms, setAllFilms] = useState([]);
  const [allDates, setAllDates] = useState([]);
  const [showTimesFilters, setShowTimesFilters] = useState([]);
  const [selectedFilm, setSelectedFilm] = useState();
  const [selectedDate, setSelectedDate] = useState(0);
  const [selectedTime, setSelectedTime] = useState();
  const username = localStorage.getItem("username");
  const navigate = useNavigate();
  const param = useParams();

  const fetchListFilm = async () => {
    const data = await apiGetAllFilmToBooking();
    console.log(data);
    setAllFilms(data);
    setAllDates([]);
  };

  const onSelectFilm = (film) => {
    setSelectedFilm(film);
    fetchShowTimesByFilm(film.idFilm);
  };
  const fetchShowTimesByFilm = async (id) => {
    const res = await apiGetShowTimesByFilm(id);
    // const datesByFilm =  [...new Set(res.map(item => format(new Date(item.showDate),"dd/MM/yyyy")))];
    const datesByFilm = [...new Set(res.map((item) => item.showDate))];
    setAllDates(datesByFilm);
  };
  const onSelectDate = (date) => {
    // setSelectedDate(format(new Date(date),'yyyy-dd-MM'));
    setSelectedDate(date);
    console.log(date);
  };

  const fetchShowTimeByDate = async () => {
    const showTimeByDate = await apiGetShowTimesByDate(
      selectedFilm.idFilm,
      selectedDate
    );
    console.log(showTimeByDate);
    setShowTimesFilters(showTimeByDate);
  };
  const onSelectTime = (showTime) => {
    setSelectedTime(showTime);
  };
  const handleCLickSetTicket = () => {
    if (username == null) {
      navigate("/login");
    }
    onFinish(selectedFilm, selectedTime);
  };
  const fetchFilmById = async () => {
    if (param.id) {
      const filmById = await findFilmById(param.id);
      setSelectedFilm(filmById);
    }
  };
  useEffect(() => {
    fetchListFilm();
    fetchFilmById();
    if (param.id) {
      fetchShowTimesByFilm(param.id);
    }
  }, []);

  useEffect(() => {
    setSelectedDate(0);
    setSelectedTime(0);
  }, [selectedFilm]);

  useEffect(() => {
    if (selectedDate) {
      fetchShowTimeByDate();
      setSelectedTime(0);
    }
    // } else {
    //     setShowTimesFilters([])
    // }
  }, [selectedDate]);
  return (
    <div className="container-lg">
      <div className="select-film-wrapper">
        <h3 className="title">Đặt vé</h3>
        <div className="row">
          <div className="col-12 col-md-4 select-card">
            <div className="content">
              <div className="title">CHỌN PHIM</div>
              <div className="option-items" id="films-combox">
                {allFilms.map((it) => (
                  <div
                    key={it.idFilm}
                    className={`item ${
                      selectedFilm?.idFilm === it.idFilm ? "selected" : ""
                    }`}
                    onClick={() => onSelectFilm(it)}
                  >
                    {it.nameFilm}
                  </div>
                ))}
              </div>
            </div>
          </div>
          <div className="col-12 col-md-4 select-card">
            <div className="content">
              <div className="title">CHỌN NGÀY CHIẾU</div>
              <div className="option-items" id="dates-combox">
                {allDates.map((it) => (
                  <div
                    key={it}
                    // className={`item ${format(new Date(selectedDate),'dd/MM/yyyy') === it ? 'selected' : ''}`}
                    className={`item ${selectedDate === it ? "selected" : ""}`}
                    onClick={() => onSelectDate(it)}
                  >
                    {it}
                  </div>
                ))}
              </div>
            </div>
          </div>
          <div className="col-12 col-md-4 select-card">
            <div className="content">
              <div className="title">CHỌN SUẤT CHIẾU</div>
              <div className="option-items" id="times-combox">
                {selectedFilm && selectedDate ? (
                  showTimesFilters.length > 0 ? (
                    showTimesFilters.map((it) => (
                      <div
                        key={it.idShowTime}
                        className={`time-item ${
                          selectedTime?.idShowTime === it.idShowTime
                            ? "selected"
                            : ""
                        }`}
                        onClick={() => onSelectTime(it)}
                      >
                        {it.showTime}
                      </div>
                    ))
                  ) : (
                    <div className="no-times">Không có suất chiếu</div>
                  )
                ) : (
                  ""
                )}
              </div>
            </div>
          </div>
        </div>
      </div>
      <button
        disabled={!selectedTime}
        onClick={handleCLickSetTicket}
        className="d-flex m-auto mt-4 mb-4 btn btn-primary"
        style={{ borderRadius: 10, height: 40 }}
      >
        Đặt vé
      </button>
    </div>
  );
};
export default SelectShowTime;
