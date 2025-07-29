import { useState } from "react";
import "./ListAllFilm.css";
import { useEffect } from "react";
import { apiGetAllFilms } from "../../service/FilmService";
import { Link } from "react-router-dom";
import ReactPaginate from "react-paginate";
import { listTypeFilm } from "../../service/TypeFilmService";
import Header from "../common/header/Header";
import Footer from "../common/footer/Footer";
function ListAllFilm() {
  const [listFilm, setListFilm] = useState(null);
  const [typeFilm, setTypeFilm] = useState(null);
  const [searchAndPage, setSearchAndPage] = useState({
    page: 0,
    search: "",
    sort: "idFilm",
    type_film: 0,
  });
  const handlePageClick = (event) => {
    console.log(event.selected);
    setSearchAndPage((prev) => ({ ...prev, page: event.selected }));
  };
  const handleSortChange = (event) => {
    setSearchAndPage((prev) => ({ ...prev, sort: event.target.value }));
  };
  const handleTypeFilmChange = (event) => {
    setSearchAndPage((prev) => ({ ...prev, type_film: +event.target.value }));
  };
  useEffect(() => {
    document.title = "Danh sách phim";
    const fetchApiToCallTypeFilm = async () => {
      const result = await listTypeFilm();
      console.log(result);
      setTypeFilm(result);
    };
    fetchApiToCallTypeFilm();
  }, []);
  useEffect(() => {
    const fetchApiToCallAllFilm = async () => {
      const result = await apiGetAllFilms(searchAndPage);
      console.log(result);
      console.log(searchAndPage);
      setListFilm(result);
    };
    fetchApiToCallAllFilm();
  }, [searchAndPage]);
  return (
    <>
      <Header />
      <div className="d-flex justify-content-center">
        <div className="list-film container">
          <div className="sort-and-search d-flex justify-content-end align-items-center mb-3">
            <select onChange={handleSortChange} className="filter">
              <option value="idFilm">Sắp xếp theo</option>
              <option value="nameFilm">Tên phim</option>
              <option value="dateStartFilm">Ngày khởi chiếu</option>
              <option value="dateEndFilm">Ngày kết thúc</option>
              <option value="nation">Quốc gia</option>
              <option value="timeFilm">Thời gian chiếu</option>
            </select>
            <select className="filter ms-2" onChange={handleTypeFilmChange}>
              <option value="0">Loại phim</option>
              {typeFilm &&
                typeFilm.map((type) => (
                  <option key={type.idTypeFilm} value={type.idTypeFilm}>
                    {type.nameTypeFilm}
                  </option>
                ))}
            </select>
          </div>
          <div className="row d-flex">
            {listFilm &&
              listFilm.content.map((film) => {
                return (
                  <div className="movie-card col-3 p-3">
                    <Link to={"detail/" + film.idFilm}>
                      <figure className="card-banner">
                        <img src={film.imgFilm} alt={film.nameFilm} />
                      </figure>
                    </Link>
                    <div className="title-wrapper">
                      <Link to={"/film/detail/" + film.idFilm}>
                        <h3 className="card-title">{film.nameFilm}</h3>
                      </Link>
                    </div>
                    <div className="card-meta pt-2">
                      <div className="badge badge-outline">
                        {film.movieLabel}
                      </div>
                      <div className="duration">
                        <ion-icon name="time-outline" />
                        <time dateTime="PT137M">{film.timeFilm} phút</time>
                      </div>
                      <div className="rating">
                        <ion-icon name="star" />
                        <data>{film.nation}</data>
                      </div>
                    </div>
                  </div>
                );
              })}
          </div>
          {listFilm && (
            <div className="d-grid">
              <ReactPaginate
                breakLabel="..."
                nextLabel=">"
                onPageChange={handlePageClick}
                pageCount={listFilm.totalPages}
                previousLabel="< "
                containerClassName="pagination"
                pageLinkClassName="page-num"
                nextLinkClassName="page-num"
                previousLinkClassName="page-num"
                activeClassName="active"
                disabledClassName="d-none"
              />
            </div>
          )}
        </div>
      </div>
      <Footer />
    </>
  );
}

export default ListAllFilm;
