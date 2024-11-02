package com.example.unidthon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Entity
@Table(name = "food")

@Getter
@NoArgsConstructor
public class Food {
    @Id @Column(name = "food_id")
    private Long id;

    private String name;

    private LocalDate expiryDate;

    private LocalDate purchaseDate;

    private int price;

    @OneToOne(mappedBy = "image_id")
    private String imageUrl;

    @Builder
    public Food(Long id, String name, LocalDate expiryDate, LocalDate purchaseDate, int price) {
        this.id = id;
        this.name = name;
        this.expiryDate = expiryDate;
        this.purchaseDate = purchaseDate;
        this.price = price;
    }
}
