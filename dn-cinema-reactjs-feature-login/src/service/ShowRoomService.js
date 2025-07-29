import axios from "axios";

export const findAllStatusSeat = async () => {
  try {
    const data = await axios.get(
      "http://localhost:8080/api/admin/status-seat/list"
    );
    return data.data;
  } catch (e) {
    console.log(e);
  }
};
export const findAllTypeSeat = async () => {
  try {
    const data = await axios.get(
      "http://localhost:8080/api/admin/type-seat/list"
    );
    return data.data;
  } catch (e) {
    console.log(e);
  }
};

export const findAll = async ({ page, search }, auth) => {
  const headers = {
    Authorization: "Bearer " + auth,
  };
  try {
    const result = await axios.get(
      `http://localhost:8080/api/admin/showroom/list?page=${page ? page : 0}
        &search=${search}`,
      { headers }
    );
    return result.data;
  } catch (e) {
    console.log(e);
  }
};

// Lấy dữ liệu của 1 phần tử trong list theo id
export const getShowRoom = async (id, auth) => {
  const headers = {
    Authorization: "Bearer " + auth,
  };
  try {
    const result = await axios.get(
      `http://localhost:8080/api/admin/showroom/list/${id}`,
      { headers }
    );
    return result.data;
  } catch (e) {
    console.log(e);
  }
};

export const getAll = async () => {
  try {
    const result = await axios.get(
      "http://localhost:8080/api/admin/showroom/getAll"
    );
    return result.data;
  } catch (e) {
    console.log(e);
  }
};
