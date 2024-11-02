package com.example.unidthon.service;

import com.example.unidthon.dto.FoodListResponse;
import com.example.unidthon.dto.FoodResponseDto;
import com.example.unidthon.entity.Food;
import com.example.unidthon.entity.FoodImage;
import com.example.unidthon.repository.FoodImageRepository;
import com.example.unidthon.repository.FoodMockRepository;
import com.example.unidthon.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodMockRepository foodMockRepository;
    private final FoodRepository foodRepository;
    private final FoodImageRepository foodImageRepository;

    // 모든 음식 조회
    public List<FoodListResponse> getAllFoods() {
        List<Food> foods = foodMockRepository.findAll();
        return foods.stream()
                .map(FoodListResponse::new)
                .toList();
    }


    // ID로 특정 음식 조회
    public FoodResponseDto getFoodById(Long id) {

        Food food = foodMockRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("음식을 찾지 못했습니다. " + id));

        FoodImage foodImage = foodImageRepository.findByFood(food)
                .orElse(null);

        String imageUrl = foodImage != null ? foodImage.getFoodImageURL() : null;

        return new FoodResponseDto(food.getName(), food.getExpiryDate(), food.getPurchaseDate(), food.getPrice(), imageUrl);
    }


    // 음식 저장
    public void saveFood(Food food) {
        foodMockRepository.save(food);
    }

    // ID로 특정 음식 삭제
    public boolean deleteFoodById(Long id) {
        return foodMockRepository.deleteById(id);
    }
}
