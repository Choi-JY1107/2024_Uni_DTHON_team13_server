package com.example.unidthon.repository;

import com.example.unidthon.entity.FoodImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodImageRepository extends JpaRepository<FoodImage, Long> {

    @Query("SELECT i FROM FoodImage i WHERE i.food_id = :foodId")
    Optional<FoodImage> findByFoodId(@Param("foodId") Long foodId);
}
