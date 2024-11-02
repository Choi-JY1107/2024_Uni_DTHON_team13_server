package com.example.unidthon.dto;

import com.example.unidthon.entity.Food;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FoodListResponse {
    private String name;
    private LocalDate expiryDate;
    private LocalDate purchaseDate;
    private int price;
    private String imageUrl;

    public FoodListResponse(Food food) {
        this.name = food.getName();
        this.expiryDate = food.getExpiryDate();
        this.purchaseDate = food.getPurchaseDate();
        this.price = food.getPrice();
        this.imageUrl = food.getFoodImage() != null ? food.getFoodImage().getFoodImageURL() : null;
    }
}