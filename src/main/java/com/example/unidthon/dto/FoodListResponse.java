package com.example.unidthon.dto;

import com.example.unidthon.entity.Food;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public class FoodListResponse {
    private String name;
    private LocalDate expiryDate;
    private LocalDate purchaseDate;
    private int price;
    private String imageUrl;

    public static FoodListResponse toFoodList(Food food) {
        return FoodListResponse.builder()
                .name(food.getName())
                .expiryDate(food.getExpiryDate())
                .purchaseDate(food.getPurchaseDate())
                .price(food.getPrice())
                .imageUrl(food.getFoodImage() != null ? food.getFoodImage().getFoodImageURL() : null)
                .build();
    }
}
