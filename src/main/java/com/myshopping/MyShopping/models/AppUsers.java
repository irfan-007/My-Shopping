package com.myshopping.MyShopping.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity // create table for this class
public class AppUsers {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false,unique = true)
    String email;
    @Column(nullable = false)
    String password;
    @Column(unique = true)
    Long phone;
    String type;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public AppUsers(UUID id, String name, String type, String email, String password, Long phone) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public AppUsers() {
    }
}
