package com.spring.security.config.app.models;

public class RegisterDTO {
    private String email;
    private String name;
    private String password;
    private String mobile;

    public String getEmail() {
        return email;
    }

    public RegisterDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getName() {
        return name;
    }

    public RegisterDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public RegisterDTO setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }
}
