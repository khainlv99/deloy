package com.example.dncinema.model;

import javax.persistence.*;

@Entity
@Table(name = "type_film")
public class TypeFilm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_film")
    private Integer idTypeFilm;
    @Column(name = "name_type_film", columnDefinition = "varchar(255)")
    private String nameTypeFilm;

    public TypeFilm() {
    }

    public TypeFilm(Integer idTypeFilm, String nameTypeFilm) {
        this.idTypeFilm = idTypeFilm;
        this.nameTypeFilm = nameTypeFilm;
    }

    public Integer getIdTypeFilm() {
        return idTypeFilm;
    }

    public void setIdTypeFilm(Integer idTypeFilm) {
        this.idTypeFilm = idTypeFilm;
    }

    public String getNameTypeFilm() {
        return nameTypeFilm;
    }

    public void setNameTypeFilm(String nameTypeFilm) {
        this.nameTypeFilm = nameTypeFilm;
    }
}
