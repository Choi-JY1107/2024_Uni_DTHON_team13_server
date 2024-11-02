package com.example.unidthon.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodFormRequest {

    @NotNull(message = "이름은 필수 값입니다.")
    @Size(min = 1, max = 50, message = "이름은 1자에서 50자 사이여야 합니다.")
    @JsonProperty("name")
    private String name;

    @NotNull(message = "유통기한은 필수 값입니다.")
    @JsonProperty("expiry_date")
    private LocalDate expiryDate;

    @NotNull(message = "구매 날짜는 필수 값입니다.")
    @JsonProperty("purchase_date")
    private LocalDate purchaseDate;

    @Min(value = 0, message = "가격은 0 이상이어야 합니다.")
    @JsonProperty("price")
    private int price;
}
