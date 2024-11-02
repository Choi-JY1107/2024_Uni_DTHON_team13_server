package com.example.unidthon.service;

import org.springframework.stereotype.Service;

@Service
public class FoodService {

    public String getAllFoods() {
        // 예시로 간단하게 문자열 반환
        return "All Foods";
    }

    public String getFoodById(Long id) {
        return "Food with ID: " + id;
    }

    public String saveFood() {
        return "Food saved";
    }

    public String searchFoodByOCR() {
        return "Food found by OCR";
    }

    public String recommendFood() {
        return "Food recommendation";
    }
}
