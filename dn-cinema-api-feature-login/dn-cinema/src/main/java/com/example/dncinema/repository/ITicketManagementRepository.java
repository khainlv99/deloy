package com.example.dncinema.repository;

import com.example.dncinema.dto.ICustomerPoint;
import com.example.dncinema.dto.ITicketManagement;
import com.example.dncinema.model.TicketManagement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;

@Repository
public interface ITicketManagementRepository extends JpaRepository<TicketManagement, Integer> {
    @Query(value = "select film.name_film as nameFilm,customer.img_customer as imgCustomer , ticket.id_ticket as idTicket,ticket.date_booking as dateBooking , ticket.price_after_discount as priceAfterDiscount, ticket.status_ticket as statusTicket from customer " +
            "join ticket on ticket.id_customer = customer.id_customer " +
            "join seat on ticket.id_seat = seat.id_seat "+
            "join show_time on show_time.id_show_time = seat.id_show_time "+
            "join film on film.id_film = show_time.id_film",
            countQuery = "select count(*) from customer " +
                    "join ticket on ticket.id_customer = customer.id_customer " +
                    "join seat on ticket.id_seat = seat.id_seat " +
                    "join show_time on show_time.id_show_time = seat.id_show_time " +
                    "join film on film.id_film = show_time.id_film",
            nativeQuery = true)
    Page<ITicketManagement> findCustomerByTicket(Pageable pageable);

    @Query(value = "select ticket.date_booking as dateBooking,customer.img_customer as imgCustomer, film.name_film as nameFilm, customer.point_customer as pointCustomer from customer " +
            "join ticket on ticket.id_customer = customer.id_customer " +
            "join seat on ticket.id_seat = seat.id_seat " +
            "join show_time on show_time.id_show_time = seat.id_show_time " +
            "join film on film.id_film = show_time.id_film",
            countQuery = "select count(*) from customer " +
                    "join ticket on ticket.id_customer = customer.id_customer " +
                    "join seat on ticket.id_seat = seat.id_seat " +
                    "join show_time on show_time.id_show_time = seat.id_show_time " +
                    "join film on film.id_film = show_time.id_film",
            nativeQuery = true)
    Page<ICustomerPoint> findAllCustomerPointHistory(Pageable pageable);

    @Query(value = "select ticket.date_booking as dateBooking,customer.img_customer as imgCustomer, film.name_film as nameFilm, customer.point_customer as pointCustomer from customer " +
            "join ticket on ticket.id_customer = customer.id_customer " +
            "join seat on ticket.id_seat = seat.id_seat " +
            "join show_time on show_time.id_show_time = seat.id_show_time " +
            "join film on film.id_film = show_time.id_film where date_booking between ? and ?",
            countQuery = "select count(*) from customer " +
                    "join ticket on ticket.id_customer = customer.id_customer " +
                    "join seat on ticket.id_seat = seat.id_seat " +
                    "join show_time on show_time.id_show_time = seat.id_show_time " +
                    "join film on film.id_film = show_time.id_film where date_booking between ? and ?" ,nativeQuery = true)
    Page<ICustomerPoint> findAllPlusPoint(Pageable pageable,@Param("startDate") LocalDate startDate, @Param("dateEnd")LocalDate dateEnd);
}