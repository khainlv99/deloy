import axios from "axios";

export const apiBookingTicket = async (listSeat) => {
    try {
        const result = await axios.put(`http://localhost:8080/api/public/seat/update_status`, listSeat);
        return result;
    } catch (e) {
        console.log(e);
    }
};

export const apiResetBookingTicket = async (listSeat) => {
    try {

        const result = await axios.put(`http://localhost:8080/api/public/seat/reset_status`, listSeat);
        return result;
    } catch (e) {
        console.log(e);
    }
};