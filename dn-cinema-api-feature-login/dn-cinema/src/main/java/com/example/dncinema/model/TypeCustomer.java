package com.example.dncinema.model;

import javax.persistence.*;

@Entity
@Table(name = "type_customer")
public class TypeCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_customer")
    private Integer idTypeCustomer;
    @Column(name = "name_type_customer", columnDefinition = "varchar(255)")
    private String nameTypeCustomer;

    public TypeCustomer() {
    }

    public TypeCustomer(Integer idTypeCustomer, String nameTypeCustomer) {
        this.idTypeCustomer = idTypeCustomer;
        this.nameTypeCustomer = nameTypeCustomer;
    }

    public Integer getIdTypeCustomer() {
        return idTypeCustomer;
    }

    public void setIdTypeCustomer(Integer idTypeCustomer) {
        this.idTypeCustomer = idTypeCustomer;
    }

    public String getNameTypeCustomer() {
        return nameTypeCustomer;
    }

    public void setNameTypeCustomer(String nameTypeCustomer) {
        this.nameTypeCustomer = nameTypeCustomer;
    }
}
