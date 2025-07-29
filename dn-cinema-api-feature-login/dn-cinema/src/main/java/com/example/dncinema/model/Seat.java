package com.example.dncinema.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_seat")
    private Integer idSeat;
    @Column(name = "name_seat", columnDefinition = "varchar(255)")
    private String nameSeat;

    @ManyToOne
    @JoinColumn(name = "id_status_seat")
    private StatusSeat seat;

    @ManyToOne
    @JoinColumn(name = "id_type_seat")
    private TypeSeat typeSeat;

    @ManyToOne
    @JoinColumn(name = "id_show_room")
    private ShowRoom showRoom;
    @ManyToOne
    @JoinColumn(name = "id_show_time")
    private ShowTime showTime;

    public Seat() {
    }

    public Seat(Integer idSeat, String nameSeat, StatusSeat seat, TypeSeat typeSeat, ShowRoom showRoom, ShowTime showTime) {
        this.idSeat = idSeat;
        this.nameSeat = nameSeat;
        this.seat = seat;
        this.typeSeat = typeSeat;
        this.showRoom = showRoom;
        this.showTime = showTime;
    }

    public ShowTime getShowTime() {
        return showTime;
    }

    public void setShowTime(ShowTime showTime) {
        this.showTime = showTime;
    }

    public Integer getIdSeat() {
        return idSeat;
    }

    public void setIdSeat(Integer idSeat) {
        this.idSeat = idSeat;
    }

    public String getNameSeat() {
        return nameSeat;
    }

    public void setNameSeat(String nameSeat) {
        this.nameSeat = nameSeat;
    }

    public StatusSeat getSeat() {
        return seat;
    }

    public void setSeat(StatusSeat seat) {
        this.seat = seat;
    }

    public TypeSeat getTypeSeat() {
        return typeSeat;
    }

    public void setTypeSeat(TypeSeat typeSeat) {
        this.typeSeat = typeSeat;
    }

    public ShowRoom getShowRoom() {
        return showRoom;
    }

    public void setShowRoom(ShowRoom showRoom) {
        this.showRoom = showRoom;
    }

}
