package com.example.dncinema.dto.accounUserDTO;

import com.example.dncinema.model.Roles;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class AccountUserDTO {
    private int id;
    private String nameAccount;
//    @NotBlank(message = "Không được để trống")
//    @Size(min = 3, max = 200, message = "password phải từ 3 ký tự và không dài quá 200 ký tự")
    private String passwordAccount;
    private Set<Roles> roles;

    public AccountUserDTO() {
    }

    public AccountUserDTO(int id, String nameAccount, String passwordAccount, Set<Roles> roles) {
        this.id = id;
        this.nameAccount = nameAccount;
        this.passwordAccount = passwordAccount;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameAccount() {
        return nameAccount;
    }

    public void setNameAccount(String nameAccount) {
        this.nameAccount = nameAccount;
    }

    public String getPasswordAccount() {
        return passwordAccount;
    }

    public void setPasswordAccount(String passwordAccount) {
        this.passwordAccount = passwordAccount;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }
}
