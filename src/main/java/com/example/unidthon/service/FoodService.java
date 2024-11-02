package com.example.unidthon.service;

import com.example.unidthon.entity.Food;
import com.example.unidthon.repository.FoodMockRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    private final FoodMockRepository foodRepository;

    // 생성자에서 FoodMockRepository 의존성 주입
    public FoodService(FoodMockRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    // 모든 음식 조회
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    // ID로 특정 음식 조회
    public Optional<Food> getFoodById(Long id) {
        return foodRepository.findById(id);
    }

    // 음식 저장
    public void saveFood(Food food) {
        foodRepository.save(food);
    }

    // ID로 특정 음식 삭제
    public boolean deleteFoodById(Long id) {
        return foodRepository.deleteById(id);
    }
}
