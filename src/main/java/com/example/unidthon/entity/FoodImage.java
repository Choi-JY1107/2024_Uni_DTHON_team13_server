package com.example.unidthon.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Getter
@NoArgsConstructor
public class FoodImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

    private String foodImageURL;

    @Builder
    public FoodImage(Long imageId, Food food, String foodImageURL) {
        this.imageId = imageId;
        this.food = food;
        this.foodImageURL = foodImageURL;
    }
}