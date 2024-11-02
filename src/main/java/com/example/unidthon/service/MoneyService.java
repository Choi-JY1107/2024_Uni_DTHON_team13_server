package com.example.unidthon.service;

import com.example.unidthon.entity.Money;
import com.example.unidthon.repository.MoneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MoneyService {

    @Autowired
    private MoneyRepository moneyRepository;

    public Money addMoney(Long amount) {
        Optional<Money> existingMoney = moneyRepository.findById(1L);

        Money money;
        if (existingMoney.isPresent()) {
            money = existingMoney.get();
            money.setAmount(money.getAmount() + amount);
        } else {
            money = new Money(amount);
        }

        return moneyRepository.save(money);
    }

    public List<Money> getAllMoney() {
        return moneyRepository.findAll();
    }
}
