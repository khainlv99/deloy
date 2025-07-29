import axios from "axios";

export const apiGetListSeatLanhNM = async (id, auth) => {
  const headers = {
    Authorization: "Bearer " + auth,
  };
  try {
    const result = await axios.get(
      `http://localhost:8080/api/admin/seat/list/${id}`,
      { headers }
    );
    return result.data;
  } catch (e) {
    console.log(e);
  }
};

export const apiGetListSeat = async (id) => {
  try {
    const result = await axios.get(
      `http://localhost:8080/api/public/seat/${id}`
    );
    return result.data;
  } catch (e) {
    console.log(e);
  }
};

export const apiUpdateTypeSeatVip = async (seat, auth) => {
  const headers = {
    Authorization: "Bearer " + auth,
  };
  try {
    const result = await axios.put(
      "http://localhost:8080/api/admin/seat/update_type_vip/" + seat,
      null,
      {
        headers,
      }
    );
    return result.data;
  } catch (e) {
    console.log(e);
  }
};

export const apiUpdateTypeSeatNormal = async (seat, auth) => {
  const headers = {
    Authorization: "Bearer " + auth,
  };
  try {
    const result = await axios.put(
      "http://localhost:8080/api/admin/seat/update_type_normal/" + seat,
      null,
      {
        headers,
      }
    );
    return result.data;
  } catch (e) {
    console.log(e);
  }
};
