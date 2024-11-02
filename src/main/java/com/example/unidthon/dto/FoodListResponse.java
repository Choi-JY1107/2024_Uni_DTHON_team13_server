package com.example.unidthon.dto;

import java.time.LocalDate;

import com.example.unidthon.entity.Food;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FoodListResponse {

    @Schema(description = "음식 ID", example = "1")
    private Long id;

    @Schema(description = "음식 이름", example = "Apple")
    private String name;

    @Schema(description = "유통기한", example = "2024-11-10")
    private LocalDate expiryDate;

    @Schema(description = "구매 날짜", example = "2024-11-01")
    private LocalDate purchaseDate;

    @Schema(description = "가격", example = "1000")
    private int price;

    @Schema(description = "이미지 URL", example = "http://example.com/image.jpg")
    private String imageUrl;

    public FoodListResponse(Food food) {
        this.id = food.getId();
        this.name = food.getName();
        this.expiryDate = food.getExpiryDate();
        this.purchaseDate = food.getPurchaseDate();
        this.price = food.getPrice();
        this.imageUrl = food.getFoodImage() != null ? food.getFoodImage().getFoodImageURL() : null;
    }
}
