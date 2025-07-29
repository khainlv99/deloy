import { useEffect, useState } from "react";
import { useParams } from "react-router";
import * as filmService from "../../service/FilmService";
import "./detailFilm.css";
import { format } from "date-fns";
import { Link } from "react-router-dom";
import Header from "../common/header/Header";
import Footer from "../common/footer/Footer";
import { async } from "q";
export default function DetailFilm() {
  const param = useParams();
  const [movieDetail, setMovieDetail] = useState(null);
  const [listMoviePlaying, setListMoviePlaying] = useState(null);
  useEffect(() => {
    const detail = async () => {
      try {
        const res = await filmService.detail(param.id);
        document.title = res.nameFilm;
        setMovieDetail(res);
      } catch (err) {
        console.log(err);
      }
    };
    const fetchApiListMoviePlaying = async () => {
      const res = await filmService.findFilmsPlaying();
      setListMoviePlaying(res);
    };
    detail();
    fetchApiListMoviePlaying();
  }, [param.id]);
  if (!movieDetail) {
    return null;
  }
  return (
    <>
      <Header />
      {movieDetail && (
        <div style={{ margin: "150px 0" }}>
          <div className="container">
            <div className="row mt-5 mx-2">
              <div>
                <Link to={"/film"} className="text-decoration-none">
                  <i type="button" className="bi bi-house-fill text-secondary">
                    <span className="fst-normal">TRANG CHỦ</span>
                  </i>
                </Link>
              </div>
            </div>
            <div className="row mx-0">
              <div className="col-3 mt-2">
                {/*<div className="col-lg-3 col-12">*/}
                <div className="detail-feat-img">
                  <img
                    src={movieDetail?.imgFilm}
                    className="loaded img-hover"
                    data-was-processed="true"
                  />
                  <Link
                    to={movieDetail?.trailer}
                    target="_blank"
                    className="play-button"
                  >
                    <i className="bi bi-play-circle" />
                  </Link>
                </div>
                <section
                  className="mt-5 text-secondary border border-2 p-3 section-movie"
                  style={{
                    fontFamily:
                      '"Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif',
                  }}
                >
                  <h3>NHẬN KHUYẾN MÃI</h3>
                  <div className="box-promotion mt-3">
                    <form style={{ fontFamily: '"Arial"' }}>
                      <input
                        placeholder="Nhập Email Của Bạn"
                        className=" input-movie w-100 form-control"
                      />
                      <button
                        className="button-movie-1 mt-2"
                        style={{ lineHeight: "50%" }}
                      >
                        Đăng ký
                      </button>
                    </form>
                  </div>
                </section>
              </div>
              <div
                className="col-6"
                style={{
                  fontFamily:
                    '"Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif',
                }}
              >
                {/*<div*/}
                {/*    className="col-lg-6 col-12"*/}
                {/*    style={{*/}
                {/*        fontFamily: '"Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif'*/}
                {/*    }}*/}
                {/*>*/}
                <div>
                  <h3 className="text-secondary">{movieDetail?.nameFilm}</h3>
                </div>
                <hr className="text-movie" />
                <div className="detail-rating">
                  <span className="button-age">{movieDetail?.movieLabel}</span>
                  <span className="fs-4 ms-4 text-movie">
                    <i className="bi bi-clock-history me-2 ms-1" />
                    {movieDetail?.timeFilm} phút
                  </span>
                  <div className="mt-2">
                    <hr className="text-movie" />
                    <table>
                      <thead>
                        <tr style={{ height: 45 }}>
                          <th className="text-secondary">Thể loại :</th>
                          <td>{movieDetail?.typeFilm.nameTypeFilm}</td>
                        </tr>
                      </thead>
                      <tbody>
                        <tr style={{ height: 45 }}>
                          <th className="text-secondary">Quốc gia :</th>
                          <td>{movieDetail?.nation}</td>
                        </tr>
                        <tr style={{ height: 45 }}>
                          <th className="text-secondary">Đạo diễn :</th>
                          <td>{movieDetail?.director}</td>
                        </tr>
                        <tr style={{ height: 45 }}>
                          <th className="text-secondary">Diễn viên :</th>
                          <td>{movieDetail?.actor}</td>
                        </tr>
                        <tr style={{ height: 45 }}>
                          <th className="text-secondary">Nhà sản xuất :</th>
                          <td>{movieDetail?.studioFilm}</td>
                        </tr>
                        <tr style={{ height: 46, width: 300 }}>
                          <th className="text-secondary">Ngày khởi chiếu :</th>
                          {/*<td>{movieDetail?.dateStartFilm}</td>*/}
                          <td>
                            {format(
                              new Date(movieDetail?.dateStartFilm),
                              "dd/MM/yyyy"
                            )}
                          </td>
                        </tr>
                        <tr style={{ height: 45 }}>
                          <th className="">
                            <Link
                              to={"/booking-ticket/" + param.id}
                              type="button"
                              className="btn button-movie"
                              style={{ lineHeight: "50%" }}
                            >
                              Đặt vé
                            </Link>
                          </th>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                  <hr className="text-movie" />
                  <div
                    className="mt-4 text-secondary"
                    style={{
                      fontFamily:
                        '"Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif',
                    }}
                  >
                    <div>
                      <h3>NỘI DUNG PHIM</h3>
                    </div>
                    <div>
                      <p>{movieDetail?.describeFilm}</p>
                    </div>
                  </div>
                </div>
              </div>
              <div
                className="col-3 text-secondary"
                style={{
                  fontFamily:
                    '"Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif',
                }}
              >
                {/*<div*/}
                {/*    className="col-lg-3 col-12 text-secondary"*/}
                {/*    style={{*/}
                {/*        fontFamily:*/}
                {/*            '"Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif'*/}
                {/*    }}*/}
                {/*>*/}
                <div>
                  <h3>PHIM ĐANG CHIẾU</h3>
                </div>
                {listMoviePlaying &&
                  listMoviePlaying.map((movie, index) => {
                    if (index < 3) {
                      return (
                        <div className="article-movie-home">
                          <Link to={"/film/detail/" + movie.idFilm}>
                            <img
                              src={movie.imgFilm}
                              style={{ height: 200, width: 300 }}
                              className="loaded"
                              data-was-processed="true"
                            />
                          </Link>
                          <a href="/dat-ve/lat-mat-6-tam-ve-dinh-menh">
                            <div className="decription-hover overlay">
                              <div className="movies-content">
                                <div className="group">
                                  <div className=" secondary-white">
                                    Chi tiết
                                  </div>
                                </div>
                              </div>
                            </div>
                          </a>
                          <div className="mt-2">
                            <div>
                              <h6 className="text-secondary text-comingMovie">
                                {movie.nameFilm}
                              </h6>
                            </div>
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
