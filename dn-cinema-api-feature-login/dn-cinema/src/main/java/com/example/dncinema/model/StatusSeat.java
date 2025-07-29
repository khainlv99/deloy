package com.example.dncinema.model;

import javax.persistence.*;

@Entity
@Table(name = "status_seat")
public class StatusSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_status_seat")
    private Integer idStatusSeat;
    @Column(name = "name_status_seat", columnDefinition = "varchar(255)")
    private String nameStatusSeat;

    public StatusSeat() {
    }

    public StatusSeat(Integer idStatusSeat) {
        this.idStatusSeat = idStatusSeat;
    }

    public StatusSeat(Integer idStatusSeat, String nameStatusSeat) {
        this.idStatusSeat = idStatusSeat;
        this.nameStatusSeat = nameStatusSeat;
    }

    public Integer getIdStatusSeat() {
        return idStatusSeat;
    }

    public void setIdStatusSeat(Integer idStatusSeat) {
        this.idStatusSeat = idStatusSeat;
    }

    public String getNameStatusSeat() {
        return nameStatusSeat;
    }

    public void setNameStatusSeat(String nameStatusSeat) {
        this.nameStatusSeat = nameStatusSeat;
    }
}
