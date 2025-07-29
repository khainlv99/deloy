package com.example.dncinema.dto;

public class CustomerPointDTO {
    private String imgCustomer;
    private String nameFilm;
    private String dateBooking;
    private String pointCustomer;

    public CustomerPointDTO() {
    }

    public String getImgCustomer() {
        return imgCustomer;
    }

    public void setImgCustomer(String imgCustomer) {
        this.imgCustomer = imgCustomer;
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

    public String getPointCustomer() {
        return pointCustomer;
    }

    public void setPointCustomer(String pointCustomer) {
        this.pointCustomer = pointCustomer;
    }
}
