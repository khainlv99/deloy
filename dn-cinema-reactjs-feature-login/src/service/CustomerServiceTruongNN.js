import axios from "axios";

export const saveCustomer = async (customer) => {
  try {
    await axios.post(`http://localhost:8080/api/public/create`, {
      ...customer,
    });
  } catch (e) {
    console.log(e);
  }
};
export const findAllCustomerType = async () => {
  try {
    const result = await axios.get(`http://localhost:8080/api/public/type`);
    return result.data;
  } catch (error) {
    console.log(error);
  }
};

export const editCustomerAccount = async (customer, auth) => {
  const headers = { Authorization: "Bearer " + auth };
  try {
    await axios.patch(
      `http://localhost:8080/api/user/update/${customer.idCustomer}`,
      { ...customer },
      { headers }
    );
  } catch (error) {
    console.log(error);
  }
};
export const findByIdAccount = async (id, auth) => {
  const headers = { Authorization: "Bearer " + auth };
  try {
    return (
      await axios.get(`http://localhost:8080/api/user/find-account/${id}`, {
        headers,
      })
    ).data;
  } catch (e) {
    console.log(e);
  }
};

export const checkUsernameExists = async (nameAccount) => {
  try {
    return (
      await axios.get(
        `http://localhost:8080/api/public/check-account/${nameAccount}`
      )
    ).data;
  } catch (error) {
    console.error(error);
    // X? lý l?i n?u c?n thi?t
    throw new Error("?ã x?y ra l?i khi ki?m tra tài kho?n");
  }
};

export const checkEmailExists = async (email) => {
  try {
    return (
      await axios.get(`http://localhost:8080/api/public/check-email/${email}`)
    ).data;
  } catch (error) {
    console.error(error);
    // X? lý l?i n?u c?n thi?t
    throw new Error("?ã x?y ra l?i khi ki?m tra email");
  }
};

export const checkIdentityCardExists = async (identity) => {
  try {
    return (
      await axios.get(
        `http://localhost:8080/api/public/check-identity/${identity}`
      )
    ).data;
  } catch (error) {
    console.error(error);
    // X? lý l?i n?u c?n thi?t
    throw new Error("?ã x?y ra l?i khi ki?m tra CCCD");
  }
};
export const checkPhoneExists = async (phone) => {
  try {
    return (
      await axios.get(`http://localhost:8080/api/public/check-phone/${phone}`)
    ).data;
  } catch (error) {
    console.error(error);
    // X? lý l?i n?u c?n thi?t
    throw new Error("?ã x?y ra l?i khi ki?m tra s? ?iên tho?i");
  }
};

export const findAllAndSearch = async (nameSearch) => {
  try {
    const result = await axios.get(
      `http://localhost:8080/api/employee/customer?nameSearch=${nameSearch}`
    );
    console.log(result.data);
    return result.data;
  } catch (error) {
    console.log(error);
  }
};

export const findAll = async () => {
  try {
    const result = await axios.get(
      `http://localhost:8080/api/employee/customer`
    );
    console.log(result.data);
    return result.data;
  } catch (error) {
    console.log(error);
  }
};

export const editCustomer = async (customer) => {
  try {
    await axios.patch(`http://localhost:8080/customer/update`, { ...customer });

    alert("Ch?nh s?a thành công !");
  } catch (error) {
    console.log(error);
  }
};
export const findById = async (id) => {
  try {
    const book = await axios.get(`http://localhost:8080/customer/${id}`);
    return book.data;
  } catch (error) {
    console.log(error);
    return error;
  }
};

export const checkMatchPassword = async () => {
  try {
    const result = await axios.post(
      `http://localhost:8080/api/user/update-pass`
    );
    console.log(result);
  } catch (error) {
    console.error(error);
    // X? lý l?i n?u c?n thi?t
    throw new Error("?ã x?y ra l?i khi check trùng m?t kh?u");
  }
};
export const customerService = {
  findAll,
  editCustomer,
  findById,
  findAllAndSearch,
};
export const findCustomerByNameAccount = async (name) => {
  try {
    const result = await axios.get(
      "http://localhost:8080/api/public/find-customer?name=" + name
    );
    return result.data;
  } catch (error) {
    console.error(error);
  }
};
