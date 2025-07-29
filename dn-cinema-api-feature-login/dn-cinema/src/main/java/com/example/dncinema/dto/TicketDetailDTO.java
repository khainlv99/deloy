package com.example.dncinema.dto;

import com.example.dncinema.model.Discount;

import java.time.LocalDate;

public class TicketDetailDTO {
    private Integer idTicket;

    private String statusTicket;

    private Long priceAfterDiscount;

    private LocalDate dateBooking;

    private String idQr;

    private Discount discount;

    private EmployeeDTO employeeDTO;

    private CustomerDTO customerDTO;
    private SeatDTO seatDTO;

    public TicketDetailDTO() {
    }

    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public String getStatusTicket() {
        return statusTicket;
    }

    public void setStatusTicket(String statusTicket) {
        this.statusTicket = statusTicket;
    }

    public Long getPriceAfterDiscount() {
        return priceAfterDiscount;
    }

    public void setPriceAfterDiscount(Long priceAfterDiscount) {
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

    public EmployeeDTO getEmployeeDTO() {
        return employeeDTO;
    }

    public void setEmployeeDTO(EmployeeDTO employeeDTO) {
        this.employeeDTO = employeeDTO;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public SeatDTO getSeatDTO() {
        return seatDTO;
    }

    public void setSeatDTO(SeatDTO seatDTO) {
        this.seatDTO = seatDTO;
    }
}
