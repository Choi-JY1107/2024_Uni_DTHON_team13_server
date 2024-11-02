package com.example.unidthon.repository;

import com.example.unidthon.entity.Food;

import com.example.unidthon.entity.FoodImage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FoodMockRepository {

    private final List<Food> mockFoods = new ArrayList<>();

    public FoodMockRepository() {
        // Mock 데이터 초기화
        mockFoods.add(new Food(1L, "Apple", LocalDate.of(2024, 11, 10), LocalDate.of(2024, 11, 1), 1000));
        mockFoods.add(new Food(2L, "Milk", LocalDate.of(2024, 11, 5), LocalDate.of(2024, 11, 1), 1500));
        mockFoods.add(new Food(3L, "Bread", LocalDate.of(2024, 11, 3), LocalDate.of(2024, 11, 1), 2000));
    }

    // 모든 음식 조회
    public List<Food> findAll() {
        return new ArrayList<>(mockFoods); // 원본 보호를 위해 복사본 반환
    }

    // ID로 음식 조회
    public Optional<Food> findById(Long id) {
        return mockFoods.stream()
                .filter(food -> food.getId().equals(id))
                .findFirst();
    }


    // 음식 저장 (데이터베이스가 아닌 리스트에 추가)
    public void save(Food food) {
        mockFoods.add(food);
    }

    // 특정 ID의 음식 삭제
    public boolean deleteById(Long id) {
        return mockFoods.removeIf(food -> food.getId().equals(id));
    }
}
