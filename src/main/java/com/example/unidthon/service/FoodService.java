package com.example.unidthon.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.unidthon.dto.FoodRecommendResponse;
import com.example.unidthon.dto.FoodResponse;
import com.example.unidthon.dto.OcrRecord;
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
    private final ObjectMapper objectMapper = new ObjectMapper();  // ObjectMapper 인스턴스 생성

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

    public Long saveFood(Food food) {
        Food savedFood = foodRepository.save(food);
        return savedFood.getId();
    }

    // ID로 특정 음식 삭제
    public boolean deleteFoodById(Long id) {
        if (foodRepository.existsById(id)) {
            foodRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // GPT 추천 레시피 목록 반환
    public List<FoodRecommendResponse> getFoodRecommendations() {
        List<FoodResponse> foodResponseList = getAllFoods();
        String userPrompt = createUserPromptJson(foodResponseList);
        String gptResponseJson = gptService.getRecipeRecommendations(userPrompt);
        return parseGptResponse(gptResponseJson);
    }

    public String createUserPromptJson(List<FoodResponse> foodResponseList) {
        StringBuilder jsonBuilder = new StringBuilder();

        jsonBuilder.append("{\"user_prompt\":[");

        for (int i = 0; i < foodResponseList.size(); i++) {
            FoodResponse food = foodResponseList.get(i);

            jsonBuilder.append("[\"")
                    .append(food.getName())
                    .append("\",")
                    .append(food.getQuantity())  // 예: 수량을 반환하는 getQuantity() 메서드 필요
                    .append("]");

            if (i < foodResponseList.size() - 1) {
                jsonBuilder.append(",");
            }
        }

        jsonBuilder.append("]}");

        return jsonBuilder.toString();
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

    public void saveFoodByOcrRecords(List<OcrRecord> ocrRecord) {
        // OCR 결과를 저장하는 로직을 추후 구현 예정
    }
}
