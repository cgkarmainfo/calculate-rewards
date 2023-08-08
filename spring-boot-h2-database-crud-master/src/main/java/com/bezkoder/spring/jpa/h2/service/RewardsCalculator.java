package com.bezkoder.spring.jpa.h2.service;


public class RewardsCalculator {

    public static int calculateRewardPoints(double transactionAmount) {
        int points = 0;
        if (transactionAmount > 100) {
            points += (int) ((transactionAmount - 100) * 2);
        }
        if (transactionAmount > 50) {
            points += (int) Math.min(50, (transactionAmount - 50));
        }
        return points;
    }
}

