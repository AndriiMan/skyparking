package com.example.skyparking.rules;

import com.example.skyparking.entity.Talon;

public class PriceForVipRule implements RuleTime {

    @Override
    public boolean matches(Talon talon) {
        return talon.isVip();
    }

    @Override
    public Talon count(Talon talon, double currentSum) {
        if (currentSum == 0) {
            talon.setPriceSum(0.5);
        } else {
            talon.setPriceSum(0.5 * talon.getPriceSum());
        }
        return talon;
    }
}