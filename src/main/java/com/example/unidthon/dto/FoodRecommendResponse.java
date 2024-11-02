package com.example.unidthon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodRecommendResponse {

    @Schema(description = "레시피 제목", example = "김치찌개")
    @JsonProperty("Recipe")
    private String recipe;

    @Schema(description = "재료 목록", example = "김치 1컵, 돼지고기 100g, 양파 1/2개, 대파 1/2대, 물 2컵, 두부 1/2모, 다진 마늘 1큰술, 고춧가루 1큰술, 소금 약간")
    @JsonProperty("Ingredients")
    private String ingredients;

    @Schema(description = "요리법", example = "1. 돼지고기를 냄비에 넣고 볶는다...")
    @JsonProperty("Instructions")
    private String instructions;

    @Schema(description = "조리 시간", example = "20분")
    @JsonProperty("Time")
    private String time;

    @Schema(description = "난이도", example = "쉬움")
    @JsonProperty("Difficulty")
    private String difficulty;

    @Schema(description = "사진 URL", example = "https://example.com/kimchi_fried_rice.jpg")
    @JsonProperty("ImageUrl")
    private String imageUrl;

    @Schema(description = "소매가", example = "10000")
    @JsonProperty("Price")
    private Integer price;
}
