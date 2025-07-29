package com.example.dncinema.dto;

import java.util.List;

public class SeatPriceDTO {
   private List<String> listSeats;
   private double priceTicket;

    public SeatPriceDTO() {
    }

    public SeatPriceDTO(List<String> listSeats, double priceTicket) {
        this.listSeats = listSeats;
        this.priceTicket = priceTicket;
    }

    public List<String> getListSeats() {
        return listSeats;
    }

    public void setListSeats(List<String> listSeats) {
        this.listSeats = listSeats;
    }

    public double getPriceTicket() {
        return priceTicket;
    }

    public void setPriceTicket(double priceTicket) {
        this.priceTicket = priceTicket;
    }
}
