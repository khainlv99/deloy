import axios from "axios";

export const saveEmployee = async (employeeDTO, auth) => {
  const headers = { Authorization: "Bearer " + auth };
  try {
    await axios.post(
      `http://localhost:8080/api/admin/employee/create`,
      { ...employeeDTO },
      { headers }
    );
  } catch (e) {
    console.log(e);
  }
};
export const editEmployee = async (employeeDTO, auth) => {
  const headers = { Authorization: "Bearer " + auth };
  try {
    await axios.put(
      `http://localhost:8080/api/admin/employee/update/${employeeDTO.idEmployee}`,
      { ...employeeDTO },
      { headers }
    );
  } catch (e) {
    console.log(e);
  }
};
export const findById = async (id, auth) => {
  const headers = { Authorization: "Bearer " + auth };
  try {
    return (
      await axios.get(`http://localhost:8080/api/admin/employee/${id}`, {
        headers,
      })
    ).data;
  } catch (e) {
    console.log(e);
  }
};
export const checkUsernameExists = async (nameAccount, auth) => {
  const headers = { Authorization: "Bearer " + auth };
  try {
    return (
      await axios.get(
        `http://localhost:8080/api/admin/employee/check-account/${nameAccount}`,
        { headers }
      )
    ).data;
  } catch (error) {
    console.error(error);
    throw new Error("Đã xảy ra lỗi khi kiểm tra tài khoản");
  }
};
export const checkEmailExists = async (email, auth) => {
  const headers = { Authorization: "Bearer " + auth };
  try {
    return (
      await axios.get(
        `http://localhost:8080/api/admin/employee/check-email/${email}`,
        { headers }
      )
    ).data;
  } catch (error) {
    console.error(error);
    // Xử lý lỗi nếu cần thiết
    throw new Error("Đã xảy ra lỗi khi kiểm tra Email");
  }
};
export const checkPhoneExists = async (phone, auth) => {
  const headers = { Authorization: "Bearer " + auth };
  try {
    return (
      await axios.get(
        `http://localhost:8080/api/admin/employee/check-phone/${phone}`,
        { headers }
      )
    ).data;
  } catch (error) {
    console.error(error);
    // Xử lý lỗi nếu cần thiết
    throw new Error("Đã xảy ra lỗi khi kiểm tra số điện thoại");
  }
};
export const checkIdentityCardExists = async (identityCard, auth) => {
  const headers = { Authorization: "Bearer " + auth };
  try {
    return (
      await axios.get(
        `http://localhost:8080/api/admin/employee/check-identityCard/${identityCard}`,
        { headers }
      )
    ).data;
  } catch (error) {
    console.error(error);
    // Xử lý lỗi nếu cần thiết
    throw new Error("Đã xảy ra lỗi khi kiểm tra CCCD");
  }
};
export const search = async (name, page, auth) => {
  const headers = { Authorization: "Bearer " + auth };
  try {
    const res = await axios.get(
      `http://localhost:8080/api/admin/employee?search=${name}&page=${
        page ? page : "0"
      }`,
      { headers }
    );
    return res.data;
  } catch (err) {
    console.log(err);
  }
};

export const deleteEmployee = async (id, auth) => {
  const headers = { Authorization: "Bearer " + auth };
  try {
    await axios.delete(
      `http://localhost:8080/api/admin/employee/delete/${id}`,
      { headers }
    );
  } catch (err) {
    console.log(err);
  }
};
