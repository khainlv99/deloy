package com.example.dncinema.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_ticket")
    private Integer idTicket;
    @Column(name = "status_ticket")
    private boolean statusTicket;
    @Column(name = "price_after_discount")
    private Double priceAfterDiscount;
    @Column(name = "date_booking", columnDefinition = "date")
    private LocalDate dateBooking;
    @Column(name = "id_qr")
    private String idQr;
    @Column(name = "is_delete")
    private Boolean isDelete;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_discount")
    private Discount discount;

    @ManyToOne
    @JoinColumn(name = "id_employee")
    private Employee employee;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "id_seat")
    private Seat seat;

    public Ticket() {
    }


    public Ticket( Boolean statusTicket, Double priceAfterDiscount, LocalDate dateBooking, String idQr,Boolean isDelete, Discount discount, Employee employee, Customer customer, Seat seat) {
        this.statusTicket = statusTicket;
        this.priceAfterDiscount = priceAfterDiscount;
        this.dateBooking = dateBooking;
        this.idQr = idQr;
        this.isDelete = isDelete;
        this.discount = discount;
        this.employee = employee;
        this.customer = customer;
        this.seat = seat;
    }


    public Ticket(Integer idTicket, Boolean statusTicket, Double priceAfterDiscount, LocalDate dateBooking, String idQr, Boolean isDelete, Discount discount, Employee employee, Customer customer, Seat seat) {
        this.idTicket = idTicket;
        this.statusTicket = statusTicket;
        this.priceAfterDiscount = priceAfterDiscount;
        this.dateBooking = dateBooking;
        this.idQr = idQr;
        this.isDelete = isDelete;
        this.discount = discount;
        this.employee = employee;
        this.customer = customer;
        this.seat = seat;
    }

    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }


    public Boolean getStatusTicket() {
        return statusTicket;
    }

    public void setStatusTicket(boolean statusTicket) {
        this.statusTicket = statusTicket;
    }

    public Double getPriceAfterDiscount() {
        return priceAfterDiscount;
    }

    public void setPriceAfterDiscount(Double priceAfterDiscount) {
        this.priceAfterDiscount = priceAfterDiscount;
    }

    public LocalDate getDateBooking() {
        return dateBooking;
    }

    public void setDateBooking(LocalDate dateBooking) {
        this.dateBooking = dateBooking;
    }

    public String getIdQr() {
        return idQr;
    }

    public void setIdQr(String idQr) {
        this.idQr = idQr;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
