import { useEffect, useState } from "react";
import * as FilmService from "../../service/FilmService";
import ReactPaginate from "react-paginate";
import { Field, Form, Formik } from "formik";
import axios from "axios";
import { Link } from "react-router-dom";
import { toast } from "react-toastify";
import Header from "../common/header/Header";
import Footer from "../common/footer/Footer";

export function ListFilm() {
  const [filmList, setFilmList] = useState([]);
  const [films, setFilms] = useState([]);

  const [searchAndPage, setSearchAndPage] = useState({
    page: 0,
    search: "",
  });
  const [value, setValue] = useState("");
  const [pageCount, setPageCount] = useState(0);

  useEffect(() => {
    const listFilm = async () => {
      const result = await FilmService.listFilm(searchAndPage);
      console.log(result);
      try {
        setFilmList(result.content);
        setPageCount(result.totalPages);
      } catch {
        setFilmList([]);
      }
    };
    listFilm();
  }, [searchAndPage]);

  const handleDelete = async () => {
    await FilmService.deleteFilm(films.idFilm);
    // const result = await FilmService.listFilm(searchAndPage);
    setFilmList(filmList.filter((prev) => prev.idFilm != films.idFilm));
    toast("Xoá " + films.nameFilm + " thành công");
  };

  const getData = async (id) => {
    const data = await FilmService.getFilm(id);
    setFilms(data);
  };

  const handlePageClick = (event) => {
    console.log(event.selected);
    setSearchAndPage((prev) => ({ ...prev, page: event.selected }));
  };

  const handleSearch = async (e) => {
    e.preventDefault();
    return await axios
      .get(`http://localhost:8080/api/public/movie?q=${value}`)
      .then((res) => {
        setFilmList(res.data);
      })
      .catch((err) => console.log(err));
  };

  useEffect(() => {
    document.title = "Danh sách phim";
  }, []);

  return (
    <>
      <Header />
      <div className="d-flex justify-content-center">
        <div className="container" style={{ margin: "150px 0" }}>
          <div style={{ boxShadow: "1px 3px 10px 5px rgba(0, 0, 0, 0.2)" }}>
            <div style={{ marginBottom: 20 }}>
              <h2
                className="d-flex justify-content-center"
                style={{
                  padding: 16,
                  backgroundColor: "#f26b38",
                  color: "#fff",
                  fontSize: 24,
                }}
              >
                DANH SÁCH PHIM
              </h2>
            </div>
            <div className="row ">
              <div className="col-md-4">
                <div>
                  <Link
                    to={`/admin/film/create`}
                    type="button"
                    className="btn btn-outline-primary"
                    style={{
                      width: 150,
                      background: "rgb(242, 107, 56)",
                      color: "white",
                      marginLeft: "10px",
                      border: "none",
                    }}
                  >
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      width="16"
                      height="16"
                      fill="currentColor"
                      class="bi bi-plus-circle"
                      viewBox="0 0 16 16"
                    >
                      <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
                      <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z" />
                    </svg>{" "}
                    Thêm mới
                  </Link>
                </div>
              </div>
              <div className="row col-md-8">
                <Formik
                  initialValues={{ search: "" }}
                  onSubmit={(values) => {
                    setSearchAndPage((prev) => {
                      return { ...prev, ...values, page: 0 };
                    });
                  }}
                >
                  <Form
                    className=""
                    style={{ boxShadow: "none", padding: "0px" }}
                    onSubmit={handleSearch}
                  >
                    <Field
                      type="text"
                      className="form-control float-start me-3"
                      style={{
                        width: "90%",
                        paddingLeft: 35,
                        height: "38px",
                      }}
                      name="name"
                      aria-describedby="helpId"
                      placeholder="Tìm kiếm..."
                    />
                    <button
                      className="btn btn-outline-success"
                      style={{
                        height: "38px",
                        background: "rgb(242, 107, 56)",
                        color: "white",
                        border: "none",
                        width: "50px",
                      }}
                    >
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="16"
                        height="16"
                        fill="currentColor"
                        className="bi bi-search"
                        viewBox="0 0 16 16"
                      >
                        <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" />
                      </svg>
                    </button>
                  </Form>
                </Formik>
              </div>
            </div>
            <div style={{ marginTop: 20 }}>
              <div className="row ">
                <div className="col-md-12">
                  <div className="d-flex justify-content-center">
                    {filmList.length === 0 && searchAndPage.search != "" ? (
                      <h1 style={{ color: "red" }}>Không tìm thấy</h1>
                    ) : (
                      <div
                        className="table-responsive"
                        style={{ margin: "auto", width: "100%" }}
                      >
                        <table className="table" style={{ width: "100%" }}>
                          <thead>
                            <tr className="text-center">
                              <th>STT</th>
                              <th>Tên phim</th>
                              <th>Ngày khởi chiếu</th>
                              <th>Hãng phim</th>
                              <th>Thời lượng</th>
                              <th>Thể loại</th>
                              <th>Tác vụ</th>
                            </tr>
                          </thead>
                          <tbody>
                            {filmList.map((film, index) => (
                              <tr key={index} className="text-center">
                                <td>{index + 1}</td>
                                <td>{film.nameFilm}</td>
                                <td>{film.dateStartFilm}</td>
                                <td>{film.studioFilm}</td>
                                <td>{film.timeFilm}</td>
                                <td>{film.typeFilm.nameTypeFilm}</td>
                                <td>
                                  <Link
                                    to={`/admin/film/edit/${film.idFilm}`}
                                    style={{ display: "inline-block" }}
                                  >
                                    <button
                                      type="button"
                                      className="btn btn-outline-warning me-3"
                                    >
                                      <svg
                                        xmlns="http://www.w3.org/2000/svg"
                                        width="16"
                                        height="16"
                                        fill="currentColor"
                                        className="bi bi-pencil"
                                        viewBox="0 0 16 16"
                                      >
                                        <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z" />
                                      </svg>
                                    </button>
                                  </Link>
                                  <button
                                    type="submit"
                                    className="btn btn-danger"
                                    data-bs-toggle="modal"
                                    data-bs-target="#exampleModal"
                                    onClick={() => getData(film.idFilm)}
                                  >
                                    <svg
                                      xmlns="http://www.w3.org/2000/svg"
                                      width="16"
                                      height="16"
                                      fill="currentColor"
                                      className="bi bi-trash"
                                      viewBox="0 0 16 16"
                                    >
                                      <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6Z" />
                                      <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1ZM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118ZM2.5 3h11V2h-11v1Z" />
                                    </svg>
                                  </button>
                                </td>
                              </tr>
                            ))}
                          </tbody>
                        </table>
                        <div className="d-flex justify-content-center">
                          <ReactPaginate
                            breakLabel="..."
                            nextLabel=">"
                            onPageChange={handlePageClick}
                            pageCount={pageCount}
                            previousLabel="< "
                            containerClassName="pagination"
                            pageLinkClassName="page-num"
                            nextLinkClassName="page-next"
                            previousLinkClassName="page-previous"
                            activeClassName="active"
                            disabledClassName="d-none"
                          />
                        </div>
                      </div>
                    )}
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div
        className="modal fade"
        id="exampleModal"
        tabIndex="-1"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
      >
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h5 className="modal-title" id="exampleModalLabel">
                Delete
              </h5>
            </div>
            <div className="modal-body">
              <span>Bạn có muốn xóa:</span>{" "}
              <span style={{ color: "red" }} className="font-monospace">
                {films?.nameFilm} ?
              </span>
            </div>
            <div className="modal-footer">
              <button
                type="button"
                className="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                Cancel
              </button>
              <button
                onClick={() => handleDelete()}
                type="button"
                className="btn btn-danger"
                data-bs-dismiss="modal"
              >
                OK
              </button>
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
}
