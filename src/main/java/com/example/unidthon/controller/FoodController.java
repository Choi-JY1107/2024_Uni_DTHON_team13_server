package com.example.unidthon.controller;

import com.example.unidthon.service.FoodService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/food")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping
    public String getAllFoods() {
        return foodService.getAllFoods();
    }

    @GetMapping("/{id}")
    public String getFoodById(@PathVariable Long id) {
        return foodService.getFoodById(id);
    }

    @PostMapping
    public String saveFood() {
        return foodService.saveFood();
    }

    @PostMapping("/search/ocr")
    public String searchFoodByOCR() {
        return foodService.searchFoodByOCR();
    }

    @GetMapping("/recommend")
    public String recommendFood() {
        return foodService.recommendFood();
    }
}
