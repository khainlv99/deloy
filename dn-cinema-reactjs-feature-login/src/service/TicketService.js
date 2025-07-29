import axios from "axios";

export const findByIdSeat = async (list, idFilm, auth) => {
  const headers = { Authorization: "Bearer " + auth };
  let string = list.join(",");
  try {
    const result = await axios.get(
      `http://localhost:8080/api/user/ticket/find-by-id?list=${string}&idFilm=${idFilm}`,
      { headers }
    );
    return result.data;
  } catch (e) {
    console.log(e);
  }
};

export const checkDiscount = async (nameDiscount, auth) => {
  const headers = { Authorization: "Bearer " + auth };
  try {
    const result = await axios.get(
      `http://localhost:8080/api/user/ticket/check-discount?nameDiscount=${nameDiscount}`,
      { headers }
    );
    return result.data;
  } catch (e) {
    console.log(e);
  }
};
export const pay = async (ticketDTO, auth) => {
  console.log(ticketDTO);
  const headers = { Authorization: "Bearer " + auth };
  try {
    const result = await axios.post(
      `http://localhost:8080/api/user/ticket/pay`,
      ticketDTO,
      { headers }
    );
    return result.data;
  } catch (e) {
    console.log(e);
  }
};
export const getCustomer = async (nameAcc, auth) => {
  const headers = { Authorization: "Bearer " + auth };
  try {
    const result = await axios.get(
      `http://localhost:8080/api/user/ticket/get-customer?username=${nameAcc}`,
      { headers }
    );
    return result.data;
  } catch (e) {
    console.log(e);
  }
};
export const cancelSeat = async (listId) => {
  try {
    await axios.put(
      `http://localhost:8080/api/public/seat/reset_status`,
      listId
    );
  } catch (e) {
    console.log(e);
  }
};
/**
 * @Param page
 * @Param search
 * Phương thức sử dụng để tìm kiếm kết hợp danh sách vé đặt
 * @author DatLVP
 */
export const findAllTicket = async ({ page, search }, auth) => {
  const headers = {
    Authorization: "Bearer " + auth,
  };
  try {
    const result = await axios.get(
      `http://localhost:8080/api/employee/ticket/list?page=${
        page ? page : 0
      }&search=${search}`,
      { headers }
    );
    return result.data;
  } catch (error) {
    console.log(error);
  }
};
/**
 * @Param id
 * Phương thức sử dụng để tìm huỷ vé đã đặt
 * @author DatLVP
 */

export const cancelTicket = async (id, auth) => {
  const headers = {
    Authorization: "Bearer " + auth,
  };

  try {
    await axios.put(
      `http://localhost:8080/api/employee/ticket/cancelTicket/${id}`,
      null,
      { headers: headers }
    );
  } catch (error) {
    console.log(error);
  }
};
export const checkSeat = async (idSeat) => {
  try {
    const result = await axios.get(
      `http://localhost:8080/api/public/seat/check-seat/` + idSeat
    );
    return result.data;
  } catch (e) {
    console.log(e);
  }
};
export const saveTicket = async (
  idCus,
  idFilm,
  idDiscount,
  seat,
  price,
  vnp_ResponseCode,
  auth
) => {
  const headers = { Authorization: "Bearer " + auth };
  try {
    await axios.get(
      `http://localhost:8080/api/user/ticket/create?idCus=${idCus}&idFilm=${idFilm}&idDiscount=${idDiscount}&seat=${seat}&price=${price}&vnp_ResponseCode=${vnp_ResponseCode}`,
      headers
    );
  } catch (e) {
    console.log(e);
  }
};
