package com.example.unidthon.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.unidthon.dto.FoodFormRequest;
import com.example.unidthon.dto.FoodListResponse;
import com.example.unidthon.dto.FoodRecommendResponse;
import com.example.unidthon.dto.FoodResponse;
import com.example.unidthon.entity.Food;
import com.example.unidthon.service.FoodService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/food")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @Operation(summary = "모든 음식 조회", description = "냉장고에 있는 모든 음식을 조회합니다.")
    @GetMapping
    public List<FoodListResponse> getAllFoods() {
        return foodService.getAllFoods();
    }

    @Operation(summary = "특정 ID의 음식 조회", description = "특정 ID에 해당하는 음식을 조회합니다.")
    @GetMapping("/{id}")
    public FoodResponse getFoodById(@PathVariable Long id) {
        return foodService.getFoodById(id);
    }

    @Operation(summary = "음식 저장", description = "새로운 음식을 냉장고에 저장합니다.")
    @PostMapping
    public String saveFood(@RequestBody FoodFormRequest foodFormRequest) {
        Food food = new Food(
                foodFormRequest.getId(),
                foodFormRequest.getName(),
                foodFormRequest.getExpiryDate(),
                foodFormRequest.getPurchaseDate(),
                foodFormRequest.getPrice()
        );

        foodService.saveFood(food);
        return "Food saved successfully";
    }

    @Operation(summary = "OCR을 통한 음식 검색", description = "OCR 기능을 통해 음식을 검색합니다.")
    @PostMapping("/search/ocr")
    public String searchFoodByOCR() {
        return "OCR search feature not implemented yet";
    }

    @Operation(summary = "오늘 저녁 추천", description = "냉장고에 있는 재료를 이용해 GPT가 추천한 레시피 목록을 반환합니다.")
    @GetMapping("/recommend")
    public List<FoodRecommendResponse> getFoodRecommendations() {
        return foodService.getFoodRecommendations();
    }
}
