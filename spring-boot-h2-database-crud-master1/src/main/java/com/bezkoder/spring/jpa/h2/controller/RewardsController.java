package com.bezkoder.spring.jpa.h2.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.jpa.h2.model.Transaction;
import com.bezkoder.spring.jpa.h2.service.RewardsCalculator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RewardsController {

    @PostMapping("/calculate-rewards")
    public Map<Long, Integer[]> calculateRewards(@RequestBody List<Transaction> transactions) {
    	System.out.println("Test1");
        Map<Long, Integer[]> rewardsMap = new HashMap<>();

        for (Transaction transaction : transactions) {
            Long customerId = transaction.getCustomerId();
            int points = RewardsCalculator.calculateRewardPoints(transaction.getTransactionAmount());

            if (rewardsMap.containsKey(customerId)) {
                Integer[] monthlyPoints = rewardsMap.get(customerId);
                int month = transaction.getTransactionDate().getMonth();
                monthlyPoints[month] += points;
            } else {
                Integer[] monthlyPoints = new Integer[12]; 
                for (int i = 0; i < 12; i++) {
                    monthlyPoints[i] = 0;
                }
                int month = transaction.getTransactionDate().getMonth();
                monthlyPoints[month] = points;
                rewardsMap.put(customerId, monthlyPoints);
            }
        }
        return rewardsMap;
    }
}
