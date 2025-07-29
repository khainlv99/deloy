package com.example.dncinema.dto;

public class TypeFilmDTO {
    private Integer idTypeFilm;
    private String nameTypeFilm;

    public TypeFilmDTO() {
    }

    public TypeFilmDTO(Integer idTypeFilm, String nameTypeFilm) {
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
