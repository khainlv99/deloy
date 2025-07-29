import "./App.css";
import React from "react";
import { Routes, Route } from "react-router-dom";
import CommonLayout from "./layout/CommonLayout";
import Home from "./pages/Home";
import { CreateFilm } from "./components/film/CreateFilm";
import CreateDiscount from "./components/discount/CreateDiscount";
import UpdateDiscount from "./components/discount/UpdateDiscount";
import EmployeeList from "./components/employee/List";
import { DetailDiscount } from "./components/discount/DetailDiscount";
import ListDiscount from "./components/discount/ListDiscount";
import { ListTicket } from "./components/ticket/ListTicket";
import BookingTicket from "./pages/Booking/BookingTicket";
import DiscountList from "./components/discount/ListDiscount";
import DiscountListPublic from "./components/discount/DiscountList";
import { ListFilm } from "./components/film/ListFilm";
import { CreateEmployee } from "./components/employee/Create";
import { UpdateEmployee } from "./components/employee/Update";
import { UpdateFilm } from "./components/film/UpdateFilm";
import { TickBookingList } from "./components/customer/detail-customer/customer_ticket";
import { CustomerPointHistory } from "./components/customer/detail-customer/customer_point_history";
import ListAllFilm from "./components/film/ListAllFilm";
import Login from "./pages/Login/Login";
import ConfirmEmail from "./pages/Login/ConfirmEmail";
import ResetPassword from "./pages/Login/ResetPassword";
import DetailFilm from "./components/film/DetailFilm";
import { CreateCustomerAccount } from "./components/customer/Create";
import { FetchApi } from "./components/confirm-ticket/FetchApi";
import { ListShowRoom } from "./components/show_room/ListShowRoom";
import DetailShowRoom from "./components/show_room/DetailShowRoom";
import List from "./components/customer/List";
import {
  UpdateCustomer,
  UpdateCustomerAccount,
} from "./components/customer/Update";
import Update from "./components/customer/detail-customer/UpdateCustomerThanhNV";
import { StatisticalFilm } from "./components/statistic/StatisticFilm";
import { StatisticalMember } from "./components/statistic/StatisticMember";
import TicketDetail from "./components/ticket/TicketDetail";

function App() {
  return (
    <Routes>
      <Route path="" element={<CommonLayout />}>
        <Route path="/" element={<Home />} />
        <Route path="/film" element={<ListAllFilm />} />
        <Route path="/film/detail/:id" element={<DetailFilm />} />
        <Route path="/booking-ticket" element={<BookingTicket />} />
        <Route path="/booking-ticket/:id" element={<BookingTicket />} />
        <Route path="/confirm" element={<FetchApi />} />
        <Route path="/discount" element={<DiscountListPublic />} />
        <Route path="/discount/create" element={<CreateDiscount />} />
        <Route path="/discount/update/:id" element={<UpdateDiscount />} />
        <Route path="/detail-discount/:id" element={<DetailDiscount />} />
        {/* LOGIN_REGISTER */}
        <Route path="/login" element={<Login />} />
        <Route path="/confirm-email" element={<ConfirmEmail />} />
        <Route path="/reset-password" element={<ResetPassword />} />
        <Route path="/register" element={<CreateCustomerAccount />} />
        <Route
          path="/customer/change-information/:id"
          element={<UpdateCustomerAccount />}
        />
        {/* ADMIN_DISCOUNT */}
        <Route path="/admin/discount/list" element={<ListDiscount />} />
        <Route path="/admin/discount/create" element={<CreateDiscount />} />
        <Route path="/admin/discount/update/:id" element={<UpdateDiscount />} />
        {/* ADMIN_FILM */}
        <Route path="/admin/film/create" element={<CreateFilm />} />
        <Route path="/admin/film/edit/:id" element={<UpdateFilm />} />
        <Route path="/admin/film/list" element={<ListFilm />} />
        {/* ADMIN_SHOWROOM */}
        <Route path="/admin/showroom/list" element={<ListShowRoom />} />
        <Route path="/admin/showroom/detail/:id" element={<DetailShowRoom />} />
        {/* ADMIN_EMPLOYEE */}
        <Route path="/admin/employee/create" element={<CreateEmployee />} />
        <Route path="/admin/employee/update/:id" element={<UpdateEmployee />} />
        <Route path="/admin/employee/list" element={<EmployeeList />} />
        {/* ADMIN_CUSTOMER */}
        <Route path="/admin/customer/list" element={<List />} />
        <Route path="/admin/customer/edit/:id" element={<Update />} />
        {/* ADMIN_STATISTIC */}
        <Route path="/admin/statistic-film" element={<StatisticalFilm />} />
        <Route
          path="/admin/statistic-customer"
          element={<StatisticalMember />}
        />
        {/* EMPLOYEE_TICKET */}
        <Route path="/employee/ticket/list" element={<ListTicket />} />
        <Route path="/employee/ticket/detail/:id" element={<TicketDetail />} />
        {/* USER_TICKET */}
        <Route path="/ticket-customer" element={<TickBookingList />} />
        <Route
          path="/ticket-customer/history"
          element={<CustomerPointHistory />}
        />
      </Route>
    </Routes>
  );
}

export default App;
