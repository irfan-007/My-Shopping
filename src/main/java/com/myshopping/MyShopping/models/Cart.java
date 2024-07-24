package com.myshopping.MyShopping.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    int quantity;
    @OneToOne
    AppUsers user;
    int totalPrice;
    @OneToMany
    List<Product> products;

    public Cart() {
    }

    public Cart(UUID id, int quantity, AppUsers user, int totalPrice, List<Product> products) {
        this.id = id;
        this.quantity = quantity;
        this.user = user;
        this.totalPrice = totalPrice;
        this.products = products;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public AppUsers getUser() {
        return user;
    }

    public void setUser(AppUsers user) {
        this.user = user;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
