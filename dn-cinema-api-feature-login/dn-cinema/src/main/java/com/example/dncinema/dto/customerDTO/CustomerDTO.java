package com.example.dncinema.dto.customerDTO;

import com.example.dncinema.model.AccountUser;
import com.example.dncinema.model.TypeCustomer;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class CustomerDTO {
    private Integer idCustomer;
    @NotBlank(message = "Không được để trống")
    @Pattern(regexp = "^(?=.*[a-zA-Z\\s])[^!@#$%^&*(),.?\":{}|<>]{4,100}$", message = "Tên không h?p l?")
    @Size(min = 4, max = 99, message = "Tên ph?i có ?? dài t? 4 ??n 99 ký t?")
    private String nameCustomer;

    private LocalDate dateOfBirth;
    @Min(value = 0, message = "Điểm phải lớn hơn 0")
    private Double pointCustomer;
    private String gender;
    @Pattern(regexp = "^(\\+?84|0)(3[2-9]|5[2689]|7[06-9]|8[1-9]|9[0-9])[0-9]{7}$",
            message = "Số điện thoại không hợp lệ")
    private String phone;
    @Size(min = 3, max = 200, message = "Địa chỉ phải từ 3 ký tự và không dài quá 200 ký tự")
    private String address;
    @NotBlank(message = "Không được để trống")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email không hợp lệ")
    private String email;
    @Pattern(regexp = "^[0-9]{12}$", message = "CCCD không hợp lệ, phải là 12 chữ số")
    private String identityCard;
    @NotBlank(message = "Không được để trống")
    private String imgCustomer;
    private TypeCustomer typeCustomer;
    private AccountUser accountUser;

    public CustomerDTO() {
    }

    public CustomerDTO(Integer idCustomer, String nameCustomer, LocalDate dateOfBirth,
                       Double pointCustomer, String gender, String phone, String address, String email,
                       String identityCard,
                       String imgCustomer, TypeCustomer typeCustomer, AccountUser accountUser) {
        this.idCustomer = idCustomer;
        this.nameCustomer = nameCustomer;
        this.dateOfBirth = dateOfBirth;
        this.pointCustomer = pointCustomer;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.identityCard = identityCard;
        this.imgCustomer = imgCustomer;
        this.typeCustomer = typeCustomer;
        this.accountUser = accountUser;
    }

    public Integer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Double getPointCustomer() {
        return pointCustomer;
    }

    public void setPointCustomer(Double pointCustomer) {
        this.pointCustomer = pointCustomer;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getImgCustomer() {
        return imgCustomer;
    }

    public void setImgCustomer(String imgCustomer) {
        this.imgCustomer = imgCustomer;
    }

    public TypeCustomer getTypeCustomer() {
        return typeCustomer;
    }

    public void setTypeCustomer(TypeCustomer typeCustomer) {
        this.typeCustomer = typeCustomer;
    }

    public AccountUser getAccountUser() {
        return accountUser;
    }

    public void setAccountUser(AccountUser accountUser) {
        this.accountUser = accountUser;
    }
}

