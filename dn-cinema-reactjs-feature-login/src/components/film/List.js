import React from "react";
import { useState } from "react";
import { useEffect } from "react";
import { Link } from "react-router-dom";
import { findFilmsPlaying, findFilmsUpcoming } from "../../service/FilmService";
import Slider from "react-slick";
const settings = {
  dots: true,
  infinite: true,
  speed: 500,
  slidesToShow: 4, // Số lượng item hiển thị trên mỗi slide
  slidesToScroll: 4,
};
export function ListFilm() {
  const [movieUpcoming, setMovieUpcoming] = useState([]);
  const [moviePlaying, setMoviePlaying] = useState([]);
  useEffect(() => {
    const fetchApiToGetData = async () => {
      const resultMovieUpcoming = await findFilmsUpcoming();
      setMovieUpcoming(resultMovieUpcoming);
      const resultMoviePlaying = await findFilmsPlaying();
      setMoviePlaying(resultMoviePlaying);
    };
    fetchApiToGetData();
  }, []);
  return (
    <div>
      <section className="upcoming">
        <div className="container">
          <div className="flex-wrapper">
            <div className="title-wrapper">
              <p className="section-subtitle fw-bold">Phim đang chiếu</p>
            </div>
            <ul className="filter-list">
              <li>
                <button className="filter-btn">Hành động</button>
              </li>
              <li>
                <button className="filter-btn">Hài kịch</button>
              </li>
              <li>
                <button className="filter-btn">Cuộc sống</button>
              </li>
            </ul>
          </div>
          <div>
            <Slider {...settings}>
              {moviePlaying.map((movie) => (
                <div className="movie-card p-3">
                  <Link to={"/film/detail/" + movie.idFilm}>
                    <figure className="card-banner">
                      <img src={movie.imgFilm} alt={movie.nameFilm} />
                    </figure>
                  </Link>
                  <div className="title-wrapper">
                    <Link to={"/film/detail/" + movie.idFilm}>
                      <h3 className="card-title">{movie.nameFilm}</h3>
                    </Link>
                  </div>
                  <div className="card-meta pt-2">
                    <div className="badge badge-outline">
                      {movie.movieLabel}
                    </div>
                    <div className="duration">
                      <ion-icon name="time-outline" />
                      <time dateTime="PT137M">{movie.timeFilm} phút</time>
                    </div>
                    <div className="rating">
                      <ion-icon name="star" />
                      <data>{movie.nation}</data>
                    </div>
                  </div>
                </div>
              ))}
            </Slider>
          </div>
        </div>
        <div className="container" style={{ marginTop: 100 }}>
          <div className="flex-wrapper">
            <div className="title-wrapper">
              <p className="section-subtitle fw-bold">Phim sắp khởi chiếu</p>
            </div>
            <ul className="filter-list">
              <li>
                <button className="filter-btn">Hành động</button>
              </li>
              <li>
                <button className="filter-btn">Hài kịch</button>
              </li>
              <li>
                <button className="filter-btn">Cuộc sống</button>
              </li>
            </ul>
          </div>
          <div>
            <Slider {...settings}>
              {movieUpcoming.map((movie) => (
                <div className="movie-card p-3">
                  <Link to={"/film/detail/" + movie.idFilm}>
                    <figure className="card-banner">
                      <img src={movie.imgFilm} alt={movie.nameFilm} />
                    </figure>
                  </Link>
                  <div className="title-wrapper">
                    <Link to={"/film/detail/" + movie.idFilm}>
                      <h3 className="card-title">{movie.nameFilm}</h3>
                    </Link>
                  </div>
                  <div className="card-meta pt-2">
                    <div className="badge badge-outline">
                      {movie.movieLabel}
                    </div>
                    <div className="duration">
                      <ion-icon name="time-outline" />
                      <time dateTime="PT137M">{movie.timeFilm} phút</time>
                    </div>
                    <div className="rating">
                      <ion-icon name="star" />
                      <data>{movie.nation}</data>
                    </div>
                  </div>
                </div>
              ))}
            </Slider>
          </div>
        </div>
      </section>
    </div>
  );
}

export default ListFilm;
