package com.example.dncinema.security.response;

public class ErrorMessage {
    private String field;
    private String errorMessage;

    public ErrorMessage(String field, String errorMessage) {
        this.field = field;
        this.errorMessage = errorMessage;
    }

    public ErrorMessage() {
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
