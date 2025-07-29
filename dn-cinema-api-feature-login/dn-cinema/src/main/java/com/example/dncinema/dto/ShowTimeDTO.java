package com.example.dncinema.dto;

import com.example.dncinema.model.Film;
import com.example.dncinema.model.ShowRoom;

import javax.persistence.*;
import java.time.LocalDate;

public class ShowTimeDTO {
    private Integer idShowTime;
    private LocalDate showDate;
    private String showTime;
    private ShowRoom showRoom;
    private Film film;

    public ShowTimeDTO() {
    }

    public ShowTimeDTO(Integer idShowTime, LocalDate showDate, String showTime, ShowRoom showRoom, Film film) {
        this.idShowTime = idShowTime;
        this.showDate = showDate;
        this.showTime = showTime;
        this.showRoom = showRoom;
        this.film = film;
    }

    public Integer getIdShowTime() {
        return idShowTime;
    }

    public void setIdShowTime(Integer idShowTime) {
        this.idShowTime = idShowTime;
    }

    public LocalDate getShowDate() {
        return showDate;
    }

    public void setShowDate(LocalDate showDate) {
        this.showDate = showDate;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public ShowRoom getShowRoom() {
        return showRoom;
    }

    public void setShowRoom(ShowRoom showRoom) {
        this.showRoom = showRoom;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
