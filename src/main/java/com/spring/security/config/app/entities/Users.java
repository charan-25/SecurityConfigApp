package com.spring.security.config.app.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "usr_id")
    private Integer userId;

    @Column(name = "usr_created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name="usr_last_updated")
    @UpdateTimestamp
    private Date lastUpdated;

    @Column(name="usr_name")
    private String name;

    @Column(name="usr_pwd")
    private String password;

    @Column(name = "usr_email")
    private String email;

    @Column(name="usr_mob")
    private String mobile;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "usr_role_id",referencedColumnName = "role_id",nullable = false)
    private Roles role;

    public Roles getRole() {
        return role;
    }

    public Users setRole(Roles role) {
        this.role = role;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getMobile() {
        return mobile;
    }

    public Users setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getName() {
        return name;
    }

    public Users setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+role.getName().toString());
        return List.of(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    public Users setPassword(String password) {
        this.password = password;
        return this;
    }

    public Users setUserName(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
