package com.spring.security.config.app.exception;

public class ErrorDetails {

    private String message;
    private String Details;

    public ErrorDetails(String message, String details) {
        this.message = message;
        Details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }
}
