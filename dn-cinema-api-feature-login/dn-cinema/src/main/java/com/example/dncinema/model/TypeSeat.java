package com.example.dncinema.model;

import javax.persistence.*;

@Entity
@Table(name = "type_seat")
public class TypeSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_seat")
    private Integer idTypeSeat;
    @Column(name = "name_type_seat", columnDefinition = "varchar(255)")
    private String nameTypeSeat;

    public TypeSeat() {
    }

    public TypeSeat(Integer idTypeSeat) {
        this.idTypeSeat = idTypeSeat;
    }


    public TypeSeat(Integer idTypeSeat, String nameTypeSeat) {
        this.idTypeSeat = idTypeSeat;
        this.nameTypeSeat = nameTypeSeat;
    }

    public Integer getIdTypeSeat() {
        return idTypeSeat;
    }

    public void setIdTypeSeat(Integer idTypeSeat) {
        this.idTypeSeat = idTypeSeat;
    }

    public String getNameTypeSeat() {
        return nameTypeSeat;
    }

    public void setNameTypeSeat(String nameTypeSeat) {
        this.nameTypeSeat = nameTypeSeat;
    }
}
