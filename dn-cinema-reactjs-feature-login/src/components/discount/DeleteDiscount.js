import * as discountService from "../../service/discount/DiscountService";
import { toast } from "react-toastify";
import { useNavigate } from "react-router";

function DiscountModalDelete(props) {
  const navigate = useNavigate();
  const auth = localStorage.getItem("token");
  const handleDelete = async (id) => {
    await discountService.remove(id, auth);
    // debugger
    toast.success("Xóa mã khuyến mãi thành công!");
    props.getShowList();
  };

  return (
    <>
      <div
        className="modal fade"
        id="exampleModal"
        tabIndex={-1}
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
      >
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h1 className="modal-title fs-5" id="exampleModalLabel">
                Xóa khuyến mãi
              </h1>
              <button
                type="button"
                className="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              />
            </div>
            <div className="modal-body">
              Bạn có chắc chắn muốn xóa khuyến mãi{" "}
              <span className="text-danger fw-bold">{props.name}</span> không?
            </div>
            <div className="modal-footer">
              <button
                type="button"
                className="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                Quay lại
              </button>
              <button
                onClick={() => handleDelete(props.id)}
                type="button"
                className="btn btn-danger"
                data-bs-dismiss="modal"
              >
                Xóa khuyến mãi
              </button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
export default DiscountModalDelete;
