package com.myshopping.MyShopping.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    int totalQuantity;
    int totalPrice;
    @ManyToOne // buyer can place multiple orders
    AppUsers user;
    @OneToMany // multiple products can be in an Order
    List<Product> products;
    String status; // delivered | returned | canceled | in transit
    String paymentMode; // UPI | debit/credit Card | COD

    public Orders(UUID id, int totalQuantity, int totalPrice, AppUsers user, List<Product> products, String status, String paymentMode) {
        this.id = id;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
        this.user = user;
        this.products = products;
        this.status = status;
        this.paymentMode = paymentMode;
    }

    public Orders() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public AppUsers getUser() {
        return user;
    }

    public void setUser(AppUsers user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }
}
