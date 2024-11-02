package com.example.unidthon.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.unidthon.dto.FoodRecommendResponse;
import com.example.unidthon.dto.FoodResponse;
import com.example.unidthon.entity.Food;
import com.example.unidthon.entity.FoodImage;
import com.example.unidthon.repository.FoodImageRepository;
import com.example.unidthon.repository.FoodRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final GPTService gptService;
    private final FoodRepository foodRepository;
    private final FoodImageRepository foodImageRepository;

    // 모든 음식 조회
    public List<FoodResponse> getAllFoods() {
        List<Food> foods = foodRepository.findAll();
        return foods.stream()
                .map(food -> {
                    FoodImage foodImage = foodImageRepository.findByFood(food).orElse(null);
                    String imageUrl = foodImage != null ? foodImage.getFoodImageURL() : null;
                    return new FoodResponse(food.getId(), food.getName(), food.getExpiryDate(),
                            food.getPurchaseDate(), food.getPrice(), imageUrl);
                })
                .toList();
    }

    // ID로 특정 음식 조회
    public FoodResponse getFoodById(Long id) {
        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("음식을 찾지 못했습니다. ID: " + id));

        FoodImage foodImage = foodImageRepository.findByFood(food).orElse(null);
        String imageUrl = foodImage != null ? foodImage.getFoodImageURL() : null;

        return new FoodResponse(food.getId(), food.getName(), food.getExpiryDate(), food.getPurchaseDate(),
                food.getPrice(), imageUrl);
    }

    // 음식 저장
    public void saveFood(Food food) {
        foodRepository.save(food);
    }

    // ID로 특정 음식 삭제
    public boolean deleteFoodById(Long id) {
        if (foodRepository.existsById(id)) {
            foodRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<FoodRecommendResponse> getFoodRecommendations() {
        String gptResponseJson = gptService.getRecipeRecommendations();
        return parseGptResponse(gptResponseJson);
    }

    private List<FoodRecommendResponse> parseGptResponse(String gptResponseJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(gptResponseJson, new TypeReference<List<FoodRecommendResponse>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse GPT response", e);
        }
    }
}
