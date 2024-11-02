package com.example.unidthon.controller;

import com.example.unidthon.entity.Food;
import com.example.unidthon.service.FoodService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/food")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    // 모든 음식 조회
    @GetMapping
    public List<Food> getAllFoods() {
        return foodService.getAllFoods();
    }

    // 특정 ID의 음식 조회
    @GetMapping("/{id}")
    public Optional<Food> getFoodById(@PathVariable Long id) {
        return foodService.getFoodById(id);
    }

    // 음식 저장
    @PostMapping
    public String saveFood(@RequestBody Food food) {
        foodService.saveFood(food);
        return "Food saved successfully";
    }

    // 음식 추천 요청
    @GetMapping("/recommend")
    public String recommendFood() {
        // 추천 기능을 나중에 구현할 수 있도록 임시 메시지 반환
        return "Food recommendation feature not implemented yet";
    }

    // OCR 검색 기능 (임시 메시지 반환)
    @PostMapping("/search/ocr")
    public String searchFoodByOCR() {
        return "OCR search feature not implemented yet";
    }
}
