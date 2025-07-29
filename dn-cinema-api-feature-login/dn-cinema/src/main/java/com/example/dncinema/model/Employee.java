package com.example.dncinema.model;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee")
    private Integer idEmployee;

    @Column(name = "name_employee", columnDefinition = "varchar(255)")
    private String nameEmployee;

    @Column(name = "phone", columnDefinition = "varchar(25)")
    private String phone;

    @Column(name = "address", columnDefinition = "varchar(255)")
    private String address;

    @Column(name = "gender", columnDefinition = "varchar(45)")
    private String gender;

    @Column(name = "date_of_birth", columnDefinition = "varchar(45)")
    private String dateOfBirth;

    @Column(name = "img_employee", columnDefinition = "varchar(255)")
    private String imgEmployee;

    @Column(name = "email", columnDefinition = "varchar(255)")
    private String email;

    @Column(name = "identity_card", columnDefinition = "varchar(255)")
    private String identityCard;
  
    private Boolean isDelete = false;

    @OneToOne
    @JoinColumn(name = "id")
    private AccountUser accountUser;

    public Employee() {
    }

    public Employee(Integer idEmployee, String nameEmployee, String phone, String address, String gender, String dateOfBirth, String imgEmployee, String email, String identityCard, Boolean isDelete, AccountUser accountUser) {
        this.idEmployee = idEmployee;
        this.nameEmployee = nameEmployee;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.imgEmployee = imgEmployee;
        this.email = email;
        this.identityCard = identityCard;
        this.isDelete = isDelete;
        this.accountUser = accountUser;
    }

    public Integer getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getImgEmployee() {
        return imgEmployee;
    }

    public void setImgEmployee(String imgEmployee) {
        this.imgEmployee = imgEmployee;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public AccountUser getAccountUser() {
        return accountUser;
    }

    public void setAccountUser(AccountUser accountUser) {
        this.accountUser = accountUser;
    }
}
