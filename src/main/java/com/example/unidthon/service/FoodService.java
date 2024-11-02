package com.example.unidthon.service;

import com.example.unidthon.dto.FoodListResponse;
import com.example.unidthon.dto.FoodRecommendResponse;
import com.example.unidthon.dto.FoodResponseDto;
import com.example.unidthon.entity.Food;
import com.example.unidthon.entity.FoodImage;
import com.example.unidthon.repository.FoodImageRepository;
import com.example.unidthon.repository.FoodMockRepository;
import com.example.unidthon.repository.FoodRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final GPTService gptService;
    private final FoodRepository foodRepository;
    private final FoodMockRepository foodMockRepository;
    private final FoodImageRepository foodImageRepository;

    // 모든 음식 조회
    public List<FoodListResponse> getAllFoods() {
        List<Food> foods = foodRepository.findAll();
        return foods.stream()
                .map(FoodListResponse::toFoodList)
                .collect(Collectors.toList());
    }

    // ID로 특정 음식 조회
    public FoodResponseDto getFoodById(Long id) {
        Food newFood = foodRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());

        // food id로 이미지 조회
        FoodImage foodImage = foodImageRepository.findByFood(
                        foodRepository.findById(id).orElseThrow(() -> new IllegalArgumentException()))
                .orElseThrow(() -> new IllegalArgumentException());
        // imageURL 저장
        String imageURL = foodImage.getFoodImageURL();

        // 응답 객체 반환
        return new FoodResponseDto(imageURL, newFood);
    }

    // 음식 저장
    public void saveFood(Food food) {
        foodMockRepository.save(food);
    }

    // ID로 특정 음식 삭제
    public boolean deleteFoodById(Long id) {
        return foodMockRepository.deleteById(id);
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
