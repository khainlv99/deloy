import { useEffect } from "react";
import { saveTicket } from "../../service/TicketService";
import { useNavigate, useParams } from "react-router";
import { useSearchParams } from "react-router-dom";
import { toast } from "react-toastify";

export function FetchApi() {
  const [queryParameters] = useSearchParams();
  const navigate = useNavigate();
  const token = localStorage.getItem("token");
  useEffect(() => {
    const callApi = async () => {
      await saveTicket(
        queryParameters.get("idCus"),
        queryParameters.get("idFilm"),
        queryParameters.get("idDiscount"),
        queryParameters.get("seat"),
        queryParameters.get("price"),
        queryParameters.get("vnp_ResponseCode"),
        token
      );
    };
    callApi();
    navigate("/booking-ticket");
    toast("Thanh toán thành công");
  }, []);
}
