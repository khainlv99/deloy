import axios from "axios";

export const findAll = async () => {
  try {
    const result = await axios.get(
      "http://localhost:8080/api/public/discount-list"
    );
    return result.data;
  } catch (error) {
    console.log(error);
  }
};
export const findByName = async (value, currentPage, auth) => {
  const headers = { Authorization: "Bearer " + auth };
  const result = await axios.get(
    `http://localhost:8080/api/admin/discount/list?name=${value}&page=${currentPage}`,
    { headers }
  );
  return result;
};

export const findAllDiscount = async () => {
  try {
    const result = await axios.get(
      `http://localhost:8080/api/public/discount-list`
    );
    return result.data;
  } catch (e) {
    console.log(e);
  }
};

export const save = async (discount) => {
  try {
    await axios.post(`http://localhost:8080/api/discount/`, { ...discount });
  } catch (e) {
    console.log(e);
  }
};

export const remove = async (id, auth) => {
  const headers = { Authorization: "Bearer " + auth };
  try {
    await axios.delete(`http://localhost:8080/api/admin/discount/${id}`, {
      headers,
    });
  } catch (e) {
    console.log(e);
  }
};
export const createDiscount = async (discount, auth) => {
  const headers = { Authorization: "Bearer " + auth };
  return await axios.post(
    "http://localhost:8080/api/admin/discount/create",
    {
      ...discount,
    },
    { headers }
  );
};
export const findDiscountById = async (id, auth) => {
  const headers = { Authorization: "Bearer " + auth };
  return (
    await axios.get(`http://localhost:8080/api/admin/discount/${id}`, {
      headers,
    })
  ).data;
};
export const updateDiscount = async (discount, auth) => {
  const headers = { Authorization: "Bearer " + auth };
  return await axios.put(
    `http://localhost:8080/api/admin/discount/update/${discount.idDiscount}`,
    { ...discount },
    { headers }
  );
};
export const findByIdDiscount = async (id) => {
  try {
    const result = await axios.get(
      `http://localhost:8080/api/public/discount/` + id
    );
    return result.data;
  } catch (e) {
    console.log(e);
  }
};
