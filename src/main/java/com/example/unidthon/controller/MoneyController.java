package com.example.unidthon.controller;

import com.example.unidthon.entity.Money;
import com.example.unidthon.service.MoneyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/money")
public class MoneyController {

    @Autowired
    private MoneyService moneyService;

    @PostMapping
    public Long addMoney(@Valid @RequestParam Long amount) {
        System.out.println("amount = " + amount);
        Money savedMoney = moneyService.addMoney(amount);
        return savedMoney.getAmount();
    }

    @GetMapping
    public ResponseEntity<List<Money>> getAllMoney() {
        List<Money> moneyList = moneyService.getAllMoney();
        return ResponseEntity.ok(moneyList);
    }
}
