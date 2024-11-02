package com.example.unidthon.dto;

import com.example.unidthon.entity.Food;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class FoodListResponse {

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
