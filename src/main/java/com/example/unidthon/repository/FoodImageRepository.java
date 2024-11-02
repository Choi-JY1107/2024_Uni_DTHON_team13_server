package com.example.unidthon.repository;

import com.example.unidthon.entity.Food;
import com.example.unidthon.entity.FoodImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodImageRepository extends JpaRepository<FoodImage, Long> {

    Optional<FoodImage> findByFood(Food food);
}
