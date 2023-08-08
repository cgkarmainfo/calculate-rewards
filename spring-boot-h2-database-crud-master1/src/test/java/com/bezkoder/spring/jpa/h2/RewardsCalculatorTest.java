package com.bezkoder.spring.jpa.h2;

import org.junit.jupiter.api.Test;

import com.bezkoder.spring.jpa.h2.service.RewardsCalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RewardsCalculatorTest {

    @Test
    void calculateRewardPoints_ShouldCalculatePointsCorrectly() {
        // Test for transaction amounts greater than 100
        double transactionAmount1 = 120.0;
        int expectedPoints1 = 40; // 20 for (120-100)*2, 20 for (50)
        int actualPoints1 = RewardsCalculator.calculateRewardPoints(transactionAmount1);
        assertEquals(expectedPoints1, actualPoints1);

        // Test for transaction amounts between 50 and 100
        double transactionAmount2 = 70.0;
        int expectedPoints2 = 20; // 20 for (70-50)
        int actualPoints2 = RewardsCalculator.calculateRewardPoints(transactionAmount2);
        assertEquals(expectedPoints2, actualPoints2);

        // Test for transaction amounts less than or equal to 50
        double transactionAmount3 = 30.0;
        int expectedPoints3 = 0; // No points for amounts <= 50
        int actualPoints3 = RewardsCalculator.calculateRewardPoints(transactionAmount3);
        assertEquals(expectedPoints3, actualPoints3);
    }
}

