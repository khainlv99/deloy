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
    const result = await axios.get(`http://localhost:8080/api/user/type`);
    return result.data;
  } catch (error) {
    console.log(error);
  }
};

export const editCustomerAccount = async (customer) => {
  try {
    await axios.patch(
      `http://localhost:8080/api/user/update/${customer.idCustomer}`,
      { ...customer }
    );
  } catch (e) {
    console.log(e);
  }
};
export const findByIdAccount = async (id) => {
  try {
    return (await axios.get(`http://localhost:8080/api/${id}`)).data;
  } catch (e) {
    console.log(e);
  }
};

export const checkUsernameExists = async (nameAccount) => {
  try {
    return (
      await axios.get(`http://localhost:8080/api/check-account/${nameAccount}`)
    ).data;
  } catch (error) {
    console.error(error);
    // Xử lý lỗi nếu cần thiết
    throw new Error("Đã xảy ra lỗi khi kiểm tra tài khoản");
  }
};

export const checkEmailExists = async (email) => {
  try {
    return (await axios.get(`http://localhost:8080/api/check-email/${email}`))
      .data;
  } catch (error) {
    console.error(error);
    // Xử lý lỗi nếu cần thiết
    throw new Error("Đã xảy ra lỗi khi kiểm tra email");
  }
};

export const checkIdentityCardExists = async (identity) => {
  try {
    return (
      await axios.get(`http://localhost:8080/api/check-identity/${identity}`)
    ).data;
  } catch (error) {
    console.error(error);
    // Xử lý lỗi nếu cần thiết
    throw new Error("Đã xảy ra lỗi khi kiểm tra CCCD");
  }
};
export const checkPhoneExists = async (phone) => {
  try {
    return (await axios.get(`http://localhost:8080/api/check-phone/${phone}`))
      .data;
  } catch (error) {
    console.error(error);
    // Xử lý lỗi nếu cần thiết
    throw new Error("Đã xảy ra lỗi khi kiểm tra số điên thoại");
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

export const findAll = async (auth) => {
  const headers = {
    Authorization: "Bearer " + auth,
  };
  try {
    const result = await axios.get(
      `http://localhost:8080/api/employee/customer`,
      { headers }
    );
    return result.data;
  } catch (error) {
    console.log(error);
  }
};
export const editCustomer = async (customer, auth) => {
  const headers = {
    Authorization: "Bearer " + auth,
  };
  try {
    await axios.patch(
      `http://localhost:8080/api/employee/customer/update`,
      { ...customer },
      { headers }
    );

    alert("Chỉnh sửa thành công !");
  } catch (error) {
    console.log(error);
  }
};
export const findById = async (id, auth) => {
  const headers = {
    Authorization: "Bearer " + auth,
  };
  try {
    const book = await axios.get(
      `http://localhost:8080/api/employee/customer/${id}`,
      { headers }
    );
    return book.data;
  } catch (error) {
    console.log(error);
    return error;
  }
};
export const customerService = {
  findAll,
  editCustomer,
  findById,
  findAllAndSearch,
};
