import axios from "axios";

/**
 * @Author QuynhHTN
 * Date create: 02/06/2023
 * @Param id
 * @Returns {Promise<any>}
 */
export const detail = async (id, auth) => {
  const headers = {
    Authorization: "Bearer " + auth,
  };
  try {
    return (
      await axios.get(
        `http://localhost:8080/api/employee/customer/detail/${id}`,
        { headers }
      )
    ).data;
  } catch (error) {
    console.log(error);
  }
};
/**
 * @Author: QuynhHTN
 * Date create: 02/06/2023
 * @Param value
 * @Returns {Promise<AxiosResponse<any>>}
 */
export const deleteTicket = async (value, auth) => {
  const headers = {
    Authorization: "Bearer " + auth,
  };
  try {
    return await axios.put(
      `http://localhost:8080/api/employee/ticket/update`,
      {
        ...value,
      },
      { headers }
    );
  } catch (error) {
    console.log(error);
  }
};
/**
 * @Author: QuynhHTN
 * Date create: 02/06/2023
 * @Param id
 * @Returns {Promise<AxiosResponse<any>>}
 */
export const confirmPrintTicket = async (id, auth) => {
  const headers = {
    Authorization: "Bearer " + auth,
  };
  try {
    return await axios.put(
      `http://localhost:8080/api/employee/ticket/confirm-print/${id}`,
      null,
      { headers }
    );
  } catch (error) {
    console.log(error);
  }
};
