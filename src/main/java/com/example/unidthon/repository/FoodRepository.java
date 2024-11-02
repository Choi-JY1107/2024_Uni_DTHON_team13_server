package com.example.unidthon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.unidthon.entity.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
}
