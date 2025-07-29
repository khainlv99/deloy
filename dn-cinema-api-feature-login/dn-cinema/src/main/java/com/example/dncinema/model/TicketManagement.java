package com.example.dncinema.model;

import javax.persistence.*;

@Entity
@Table(name = "table_management")
public class TicketManagement {
    @Id
    private Integer idTicket;
    private String nameFilm;
    private String dateBooking;
    private String priceAfterDiscount;
    private String statusTicket;

    public TicketManagement() {
    }

    public String getNameFilm() {
        return nameFilm;
    }

    public void setNameFilm(String nameFilm) {
        this.nameFilm = nameFilm;
    }

    public String getDateBooking() {
        return dateBooking;
    }

    public void setDateBooking(String dateBooking) {
        this.dateBooking = dateBooking;
    }

    public String getPriceAfterDiscount() {
        return priceAfterDiscount;
    }

    public void setPriceAfterDiscount(String priceAfterDiscount) {
        this.priceAfterDiscount = priceAfterDiscount;
    }

    public String getStatusTicket() {
        return statusTicket;
    }

    public void setStatusTicket(String statusTicket) {
        this.statusTicket = statusTicket;
    }
}
