package com.example.dncinema.dto;

import com.example.dncinema.model.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TicketDTO {


    private Integer idCustomer;

    private Integer idFilm;
    private Integer[] listSeat;
    private Integer idDiscount;
    private long price;

    public TicketDTO() {
    }

    public TicketDTO(Integer idCustomer, Integer idFilm, Integer[] listSeat, Integer idDiscount, long price) {
        this.idCustomer = idCustomer;
        this.idFilm = idFilm;
        this.listSeat = listSeat;
        this.idDiscount = idDiscount;
        this.price = price;
    }

    public Integer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Integer getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(Integer idFilm) {
        this.idFilm = idFilm;
    }

    public Integer[] getListSeat() {
        return listSeat;
    }

    public void setListSeat(Integer[] listSeat) {
        this.listSeat = listSeat;
    }

    public Integer getIdDiscount() {
        return idDiscount;
    }

    public void setIdDiscount(Integer idDiscount) {
        this.idDiscount = idDiscount;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
