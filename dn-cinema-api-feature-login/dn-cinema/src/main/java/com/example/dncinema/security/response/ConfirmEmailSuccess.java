package com.example.dncinema.security.response;

public class ConfirmEmailSuccess {
    private String message;
    private String code;
    private String email;

    public ConfirmEmailSuccess(String message, String code, String email) {
        this.message = message;
        this.code = code;
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
