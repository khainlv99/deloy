import axios from "axios";
/**
 * @author ChinhLV
 * @param {*} value
 * @since 28/05/2023
 * @returns giá trị của đối tượng bao gồm các thông tin: token, username, roles
 * hàm được dùng để đăng nhập với giá trị đầu vào là username và password
 */
export const handleCallApiLogin = async (value) => {
  try {
    const result = await axios.post(
      "http://localhost:8080/api/public/signin",
      value
    );
    return result.data;
  } catch (e) {
    console.log(e);
  }
};
/**
 * @author ChinhLV
 * @param {*} value
 * @since 28/05/2023
 * @returns giá trị của đối tượng bao gồm các thông tin: mã code
 * hàm được sử dụng để kiểm tra email và gửi mã code thông qua email đó nếu hợp lệ
 */
export const handleCallApiToConfirmEmail = async (value) => {
  try {
    const result = await axios.post(
      "http://localhost:8080/api/public/confirm-email",
      value
    );
    return result;
  } catch (e) {
    console.log(e);
  }
};
/**
 * @author ChinhLV
 * @param {*} value
 * @since 28/05/2023
 * @returns giá trị của đối tượng bao gồm các thông tin: message
 * hàm được sử dụng để đổi mật khẩu nếu mã code đúng với mã code trong email và mật khẩu trùng với xác nhận mật khẩu
 */
export const handleCallApiToResetPassword = async (value) => {
  try {
    await axios.post("http://localhost:8080/api/public/reset-password", value);
  } catch (e) {
    console.log(e);
  }
};
/**
 * @author ChinhLV
 * @param {*} value
 * @since 28/05/2023
 * @returns giá trị của đối tượng bao gồm các thông tin: message đã tạo thành công hay chưa
 * hàm được sử dụng để tạo tài khoản thông qua các thông tin từ tài khoản của facebook
 */
export const handleCallApiToCreateAccountFb = async (value) => {
  try {
    const result = await axios.post(
      "http://localhost:8080/api/public/login-facebook",
      value
    );
    return result;
  } catch (e) {
    return { error: e };
  }
};
