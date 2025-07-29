package com.example.dncinema.dto;

import com.example.dncinema.model.Employee;
import com.example.dncinema.model.TypeSeat;

public class TicketUpdateDTO {
    private Integer idTicket;
    private Integer idCustomer;
    private Integer idSeat;
    private String nameTypeSeat;

    public TicketUpdateDTO() {
    }

    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public Integer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Integer getIdSeat() {
        return idSeat;
    }

    public void setIdSeat(Integer idSeat) {
        this.idSeat = idSeat;
    }

    public String getNameTypeSeat() {
        return nameTypeSeat;
    }

    public void setNameTypeSeat(String nameTypeSeat) {
        this.nameTypeSeat = nameTypeSeat;
    }

}
