package com.example.dncinema.model;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_roles")
    private Integer idRoles;
    @Column(name = "name_roles", columnDefinition = "varchar(255)")
    private String nameRoles;

    public Roles() {
    }

    public Roles(Integer idRoles, String nameRoles) {
        this.idRoles = idRoles;
        this.nameRoles = nameRoles;
    }

    public Integer getIdRoles() {
        return idRoles;
    }

    public void setIdRoles(Integer idRoles) {
        this.idRoles = idRoles;
    }

    public String getNameRoles() {
        return nameRoles;
    }

    public void setNameRoles(String nameRoles) {
        this.nameRoles = nameRoles;
    }
}
