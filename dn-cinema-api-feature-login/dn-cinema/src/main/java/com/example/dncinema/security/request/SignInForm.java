package com.example.dncinema.security.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignInForm {
    @NotBlank(message = "Tên đăng nhập không được để trống")
    @Size(min = 6, max = 30, message = "Tên đăng nhập chỉ từ 6 đến 30 ký tự")
    private String username;
    @NotBlank(message = "Mật khẩu không được để trống")
    @Size(min = 6, max = 30, message = "Mật khẩu chỉ từ 6 đến 30 ký tự")
    private String password;

    public SignInForm() {
    }

    public SignInForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
