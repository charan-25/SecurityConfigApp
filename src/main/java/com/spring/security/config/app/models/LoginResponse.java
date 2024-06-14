package com.spring.security.config.app.models;

public class LoginResponse {
    private String token;

    private long expirationTime;

    public String getToken() {
        return token;
    }

    public LoginResponse setToken(String token) {
        this.token = token;
        return this;
    }

    public long getExpirationTime() {
        return expirationTime;
    }

    public LoginResponse setExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
        return this;
    }
}
