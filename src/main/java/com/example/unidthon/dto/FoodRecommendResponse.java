package com.example.unidthon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodRecommendResponse {

    @Schema(description = "레시피 제목", example = "김치볶음밥")
    @JsonProperty("제목")
    private String title;

    @Schema(description = "재료 목록", example = "[\"김치\", \"쌀\", \"계란\"]")
    @JsonProperty("재료")
    private List<String> ingredients;

    @Schema(description = "요리법", example = "1. 팬에 기름을 두르고 김치를 볶는다...")
    @JsonProperty("요리법")
    private String recipe;

    @Schema(description = "조리 시간", example = "10분")
    @JsonProperty("시간")
    private String time;

    @Schema(description = "난이도", example = "쉬움")
    @JsonProperty("난이도")
    private String difficulty;

    @Schema(description = "사진 URL", example = "https://example.com/kimchi_fried_rice.jpg")
    @JsonProperty("사진URL")
    private String imageUrl;
}
