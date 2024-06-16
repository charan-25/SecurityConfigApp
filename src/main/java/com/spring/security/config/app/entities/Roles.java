package com.spring.security.config.app.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="role_id")
    @ManyToOne
    private RoleEnum roleId;

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    @Column(name = "role_desc")
    private String description;

    @Column(name = "role_created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "role_last_updated_at")
    @UpdateTimestamp
    private Date lastUpdatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RoleEnum getRoleId() {
        return roleId;
    }

    public Roles setRoleId(RoleEnum roleId) {
        this.roleId = roleId;
        return this;
    }

    public RoleEnum getName() {
        return name;
    }

    public Roles setName(RoleEnum name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Roles setDescription(String description) {
        this.description = description;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(Date lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }
}
