package com.example.unidthon.service;

import com.example.unidthon.dto.FoodRequestDto;
import com.example.unidthon.entity.Food;
import com.example.unidthon.entity.FoodImage;
import com.example.unidthon.repository.FoodMockRepository;
import com.example.unidthon.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodMockRepository foodMockRepository;
    private final FoodRepository foodRepository;

    // 모든 음식 조회
    public List<Food> getAllFoods() {
        return foodMockRepository.findAll();
    }

    // ID로 특정 음식 조회
    public FoodRequestDto getFoodById(Long id) {
        Food newFood = foodRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());

        // food id로 이미지 조회
        FoodImage foodImage = foodRepository.findByFoodId(id)
                .orElseThrow(() -> new IllegalArgumentException());
        // imageURL 저장
        String imageURL = foodImage.getFoodImageURL();

        // 응답 객체 반환
        return new FoodRequestDto(imageURL, newFood);
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
