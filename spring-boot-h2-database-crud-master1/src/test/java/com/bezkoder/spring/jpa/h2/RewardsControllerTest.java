package com.bezkoder.spring.jpa.h2;

import com.bezkoder.spring.jpa.h2.controller.RewardsController;

import com.bezkoder.spring.jpa.h2.model.Transaction;
import com.bezkoder.spring.jpa.h2.service.RewardsCalculator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RewardsControllerTest {

    @Test
    void calculateRewards_ShouldCalculateRewardsCorrectly() {
        RewardsController rewardsController = new RewardsController();
        List<Transaction> transactions = new ArrayList<>();

        // Add some transactions for a customer
        transactions.add(new Transaction(1L, 101L, 120.0, new Date())); // 40 points (20 for (120-100)*2, 20 for (50))
        transactions.add(new Transaction(2L, 101L, 60.0, new Date())); // 10 points (10 for (60-50))
        transactions.add(new Transaction(3L, 101L, 30.0, new Date())); // 0 points (no points for amounts <= 50)

        Map<Long, Integer[]> rewardsMap = rewardsController.calculateRewards(transactions);

        assertEquals(1, rewardsMap.size()); // Only 1 customer's rewards are calculated

        Integer[] rewardsCustomer101 = rewardsMap.get(101L);
        assertEquals(3, rewardsCustomer101.length); // 3 months of rewards for customer 101
        assertEquals(40, rewardsCustomer101[0]); // Rewards for the first month (0-based) should be 40 points
        assertEquals(10, rewardsCustomer101[1]); // Rewards for the second month should be 10 points
        assertEquals(0, rewardsCustomer101[2]); // Rewards for the third month should be 0 points
    }
}
