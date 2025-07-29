import React, { useEffect, useState } from "react";
import { Field, Form, Formik } from "formik";
import ReactPaginate from "react-paginate";
import { NavLink, useNavigate } from "react-router-dom";
import employeeService from "../../service/employee/employeeService";
import EmployeeDelete from "./Delete";
import "../employee/Employee.css"

export default function EmployeeList() {
    const [employeeList, setEmployeeList] = useState([]);
    const [pageCount, setPageCount] = useState(0);
    let [count, setCount] = useState(1);
    const [currentPage, setCurrentPage] = useState(0);
    const [name, setName] = useState("");
    const [deleteId, setDeleteId] = useState(0);
    const [deleteName, setDeleteName] = useState("");
    const navigate = useNavigate();

    useEffect(() => {
        document.title = "Danh sách phim"; // Thay đổi title
    }, []);

    const search = async (value) => {
        let showTable = document.getElementById("showTable");
        let errMsg = document.getElementById("error");
        try {
            const res = await employeeService.search(value.name, value.page);
            setCurrentPage(res.content.number);
            setName(value.name);
            const totalPages = res.totalPages;
            setPageCount(totalPages);
            setEmployeeList(res.content);
            setCount(Math.ceil(res.content.size * res.content.number + 1));
            showTable.style.display = "block";
            errMsg.style.display = "none";
        } catch (e) {
            console.log(e);
            showTable.style.display = "none";
            errMsg.innerHTML = "Không tìm thấy dữ liệu!";
            errMsg.style.display = "block";
        }
    };



    const showList = async () => {
        try {
            const res = await employeeService.search(name, currentPage);
            console.log(res);
            setEmployeeList(res.content);
            const totalPages = res.totalPages;
            setPageCount(totalPages);
        } catch (error) {
            console.log(error);
            setCurrentPage(currentPage - 1);
        }
    };

    useEffect(() => {
        showList();
    }, [currentPage]);

    const handlePageClick = async (page) => {
        setCurrentPage(+page.selected);

        const res = await employeeService.search(name, page.selected);
        console.log(res.data);
        setEmployeeList(res.content);
        setCount(Math.ceil(res.size * page.selected + 1));
    };

    const getPropsDeleteEmployee = (id, name) => {
        setDeleteId(id);
        setDeleteName(name);
    };

    return (
        <>
            <div className="row mx-0">
                <div className="container mx-auto my-5 col-8">
                    <div style={{ boxShadow: "1px 3px 10px 5px rgba(0, 0, 0, 0.2)" }}>
                        {employeeList.data !== "" && (
                            <>
                                <div style={{ marginBottom: 20 }}>
                                    <h2
                                        className="d-flex justify-content-center"
                                        style={{ padding: 16, backgroundColor: "#f26b38", color: "#fff" }}
                                    >
                                        DANH SÁCH THÔNG TIN NHÂN VIÊN
                                    </h2>
                                </div>
                                <div className="row">
                                    <div className="col-md-4">
                                        <div>
                                            <NavLink
                                                to="/employee/create"
                                                type="button"
                                                className="btn btn-outline-primary"
                                                style={{ width: 150 }}
                                            >
                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-circle" viewBox="0 0 16 16">
                                                    <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
                                                    <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z" />
                                                </svg> Thêm mới
                                            </NavLink>
                                        </div>
                                    </div>
                                    <div className="row col-md-8">
                                        <Formik
                                            initialValues={{
                                                name: "",
                                                page: currentPage,
                                            }}
                                            onSubmit={(value) => {
                                                search(value);
                                            }}
                                        >
                                            <Form
                                                className="d-flex justify-content-center"
                                                role="search"
                                                style={{boxShadow: "none",padding: 0}}

                                            >

                                                <Field

                                                    type="search"
                                                    className="form-control float-start"
                                                    name="name"
                                                    placeholder="Tìm kiếm theo mã nhân viên, tên nhân viên hoặc số điện thoại"
                                                />
                                                <button
                                                    className="btn btn-outline-secondary"
                                                    type="submit"
                                                >
                                                    <svg
                                                        xmlns="http://www.w3.org/2000/svg"
                                                        width={16}
                                                        height={16}
                                                        fill="currentColor"
                                                        className="bi bi-search"
                                                        viewBox="0 0 16 16"
                                                    >
                                                        <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" />
                                                        //                                             </svg>
                                                </button>
                                            </Form>
                                        </Formik>
                                    </div>
                                </div>
                                <h2
                                    id="error"
                                    className={"text-center text-danger"}
                                    style={{ display: "none" }}
                                ></h2>
                                <div id="showTable">
                                    <div className="row">
                                        <div className="col-md-12">
                                            <div className="d-flex justify-content-center">
                                                <table
                                                    className="table table-striped table-hover"
                                                    style={{ width: "85%" }}
                                                >
                                                    <thead>
                                                    <tr>
                                                        <th>STT</th>
                                                        <th>Mã NV</th>
                                                        <th>Tên NV</th>
                                                        <th>Số CMND</th>
                                                        <th>Email</th>
                                                        <th>SĐT</th>
                                                        <th>Địa Chỉ</th>
                                                        <th>Chỉnh Sửa</th>
                                                        <th>Xoá</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    {employeeList.map((employee, index) => (
                                                        <tr key={index}>
                                                            <td>{count++}</td>
                                                            <td>
                                                                {employee.accountUser.nameAccount}
                                                            </td>
                                                            <td

                                                                className="text-cut"
                                                            >
                                                                {employee.nameEmployee}
                                                            </td>
                                                            <td

                                                                className="text-cut"
                                                            >
                                                                {employee.identityCard}
                                                            </td>
                                                            <td>
                                                                {employee.email}
                                                            </td>
                                                            <td>
                                                                {employee.phone}
                                                            </td>
                                                            <td>
                                                                {employee.address}
                                                            </td>
                                                            <td>
                                                                <NavLink
                                                                    to={`/employee/edit/${employee.id}`}
                                                                    className="btn btn-outline-primary"
                                                                >
                                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                                                                        <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z" />
                                                                        <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z" />
                                                                    </svg>
                                                                </NavLink>
                                                            </td>
                                                            <td>
                                                                <button
                                                                    type="button"
                                                                    className="btn btn-outline-danger"
                                                                    onClick={() =>
                                                                        getPropsDeleteEmployee(
                                                                            employee.idEmployee,
                                                                            employee.nameEmployee
                                                                        )
                                                                    }
                                                                    data-bs-toggle="modal"
                                                                    data-bs-target="#deleteEmployee"
                                                                >
                                                                    <svg
                                                                        xmlns="http://www.w3.org/2000/svg"
                                                                        width={16}
                                                                        height={16}
                                                                        fill="currentColor"
                                                                        className="bi bi-trash3"
                                                                        viewBox="0 0 16 16"
                                                                        title="Xóa nhân viên"
                                                                    >
                                                                        <path d="M6.5 1h3a.5.5 0 0 1 .5.5v1H6v-1a.5.5 0 0 1 .5-.5ZM11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3A1.5 1.5 0 0 0 5 1.5v1H2.506a.58.58 0 0 0-.01 0H1.5a.5.5 0 0 0 0 1h.538l.853 10.66A2 2 0 0 0 4.885 16h6.23a2 2 0 0 0 1.994-1.84l.853-10.66h.538a.5.5 0 0 0 0-1h-.995a.59.59 0 0 0-.01 0H11Zm1.958 1-.846 10.58a1 1 0 0 1-.997.92h-6.23a1 1 0 0 1-.997-.92L3.042 3.5h9.916Zm-7.487 1a.5.5 0 0 1 .528.47l.5 8.5a.5.5 0 0 1-.998.06L5 5.03a.5.5 0 0 1 .47-.53Zm5.058 0a.5.5 0 0 1 .47.53l-.5 8.5a.5.5 0 1 1-.998-.06l.5-8.5a.5.5 0 0 1 .528-.47ZM8 4.5a.5.5 0 0 1 .5.5v8.5a.5.5 0 0 1-1 0V5a.5.5 0 0 1 .5-.5Z" />
                                                                    </svg>
                                                                </button>
                                                            </td>
                                                        </tr>
                                                    ))}
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <div className=" d-flex justify-content-center">
                                        <ReactPaginate
                                            previousLabel="Trước"
                                            nextLabel="Sau"
                                            pageCount={pageCount}
                                            onPageChange={handlePageClick}
                                            containerClassName="pagination"
                                            previousClassName="page-item"
                                            previousLinkClassName="page-link"
                                            nextClassName="page-item"
                                            nextLinkClassName="page-link"
                                            pageClassName="page-item"
                                            pageLinkClassName="page-link"
                                            activeClassName="active"
                                            activeLinkClassName="page-link"
                                            forcePage={currentPage}
                                            pageRangeDisplayed={2} // Hiển thị 3 trang trên mỗi lần render
                                            marginPagesDisplayed={1} // Hiển thị 1 trang ở đầu và cuối danh sách trang
                                        />
                                    </div>
                                </div>
                            </>
                        )}
                        <EmployeeDelete
                            id={deleteId}
                            name={deleteName}
                            showList={() => {
                                showList();
                            }}
                        />
                    </div>
                </div>
            </div>
        </>
    );
}