package com.example.unidthon.repository;

import com.example.unidthon.entity.Food;
import com.example.unidthon.entity.FoodImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    @Query("SELECT fi FROM FoodImage fi WHERE fi.food.id = :id")
    Optional<FoodImage> findByFoodId(Long id);

    @Query("SELECT f FROM Food f")
    List<Food> findAll();
}
