package com.example.unidthon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Getter
public class FoodImage {
    @Id
    private Long image_id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

    private String foodImageURL;

    public FoodImage(Long image_id, Food food, String foodImageURL) {
        this.image_id = image_id;
        this.food = food;
        this.foodImageURL = foodImageURL;
    }
}
