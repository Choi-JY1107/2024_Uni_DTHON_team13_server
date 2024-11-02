package com.example.unidthon.repository;

import com.example.unidthon.entity.Food;
import com.example.unidthon.entity.FoodImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {

    @Query("SELECT i FROM FoodImage i WHERE i.food_id = :food_id")
    Optional<FoodImage> findByFoodId(Long id);

    Optional<Food> findById(Long id);

    @Query("SELECT f FROM Food f")
    List<Food> findAll();
}
