package com.example.unidthon.repository;

import com.example.unidthon.entity.Money;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoneyRepository extends JpaRepository<Money, Long> {
}
