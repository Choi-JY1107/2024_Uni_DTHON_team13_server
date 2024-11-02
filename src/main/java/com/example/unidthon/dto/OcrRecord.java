package com.example.unidthon.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OcrRecord {

    @Schema(description = "음식 이름", example = "Apple")
    private String name;

    @Schema(description = "유통기한", example = "2024-11-10")
    private LocalDate expiryDate;

    @Schema(description = "구매 날짜", example = "2024-11-01")
    private LocalDate purchaseDate;

    @Schema(description = "가격", example = "1000")
    private int price;
}
