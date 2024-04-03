package com.myshopping.MyShopping.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // create unique id automatically
    UUID id;
    String name;
    String category;
    int quantity;
    int price;
    String description;
    String rating;
    @ManyToOne // one seller can have multiple products
    AppUsers user;

    public Product(UUID id, String name, String category, int quantity, int price, String description, String rating, AppUsers user) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.rating = rating;
        this.user = user;
    }

    public Product() {
    }

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public AppUsers getUser() {
        return user;
    }

    public void setUser(AppUsers user) {
        this.user = user;
    }
}
