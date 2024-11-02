package com.example.unidthon.entity;

import java.time.LocalDate;

public class Food {
    private Long id;
    private String name;
    private LocalDate expiryDate;
    private LocalDate purchaseDate;
    private int price;

    public Food(Long id, String name, LocalDate expiryDate, LocalDate purchaseDate, int price) {
        this.id = id;
        this.name = name;
        this.expiryDate = expiryDate;
        this.purchaseDate = purchaseDate;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public int getPrice() {
        return price;
    }
}
