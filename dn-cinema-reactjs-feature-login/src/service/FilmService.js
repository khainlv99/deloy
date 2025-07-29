import axios from "axios";

export const apiGetShowTimesByFilm = async (id) => {
  try {
    const result = await axios.get(
      `http://localhost:8080/api/public/showtime/${id}`
    );
    return result.data;
  } catch (e) {
    console.log(e);
  }
};
export const apiGetShowTimesByDate = async (filmId, date) => {
  try {
    const result = await axios.get(
      `http://localhost:8080/api/public/showtime/${filmId}/${date}`
    );
    return result.data;
  } catch (e) {
    console.log(e);
  }
};
export const apiGetAllFilmToBooking = async () => {
  try {
    const result = await axios.get(
      `http://localhost:8080/api/public/movie/list`
    );
    return result.data;
  } catch (e) {
    console.log(e);
  }
};
export const apiGetAllFilms = async (pageAndSearch) => {
  try {
    const result = await axios.get(
      "http://localhost:8080/api/public/movie?page=" +
        pageAndSearch.page +
        "&search=" +
        pageAndSearch.search +
        "&sort=" +
        pageAndSearch.sort +
        "&type_film=" +
        pageAndSearch.type_film
    );
    console.log(
      "http://localhost:8080/api/public/movie?page=" +
        pageAndSearch.page +
        "&search=" +
        pageAndSearch.search +
        "&sort=" +
        pageAndSearch.sort +
        "&type_film=" +
        pageAndSearch.type_film
    );
    return result.data;
  } catch (e) {
    console.log(e);
  }
};

export const findFilmById = async (idFilm) => {
  try {
    const result = await axios.get(
      "http://localhost:8080/api/public/movie/detail/" + idFilm
    );
    console.log(result);
    return result.data;
  } catch (e) {
    console.log(e);
  }
};
export const findFilmsUpcoming = async () => {
  try {
    const result = await axios.get(
      "http://localhost:8080/api/public/movie/list-upcoming"
    );
    console.log(result.data);
    return result.data;
  } catch (e) {
    console.log(e);
  }
};
export const findFilmsPlaying = async () => {
  try {
    const result = await axios.get(
      "http://localhost:8080/api/public/movie/list-playing"
    );
    console.log(result.data);
    return result.data;
  } catch (e) {
    console.log(e);
  }
};
export const updateFilm = async (film) => {
  console.log(film);
  try {
    await axios.put("http://localhost:8080/api/public/movie/" + film.idFilm, {
      ...film,
    });
  } catch (e) {
    console.log(e);
  }
};
export const createFilm = async (film) => {
  try {
    await axios.post("http://localhost:8080/api/public/movie/create", {
      ...film,
    });
  } catch (e) {
    console.log(e);
  }
};
export const listFilm = async ({ page, search }) => {
  try {
    const result =
      await axios.get(`http://localhost:8080/api/public/movie?page=${
        page ? page : 0
      }
        &search=${search}`);
    return result.data;
  } catch (e) {
    console.log(e);
  }
};
export const deleteFilm = async (idFilm) => {
  try {
    await axios.delete(`http://localhost:8080/api/public/movie/${idFilm}`);
  } catch (e) {
    console.log(e);
  }
};
export const getFilm = async (id) => {
  try {
    const result = await axios.get(
      "http://localhost:8080/api/public/movie/detail/" + id
    );
    return result.data;
  } catch (err) {
    console.log(err);
  }
};
export const detail = async (id) => {
  try {
    return (
      await axios.get("http://localhost:8080/api/public/movie/detail/" + id)
    ).data;
  } catch (error) {
    console.log(error);
  }
};
