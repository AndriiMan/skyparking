package com.example.skyparking.rules;

import com.example.skyparking.entity.Machine;
import com.example.skyparking.entity.Talon;

public class PriceForLostTalonRule implements RuleTime {

    @Override
    public boolean matches(Talon talon) {
        return talon.getNumber() == 0;
    }

    @Override
    public Talon count(Talon talon, double currentSum) {
        Machine machine = talon.getMachine();
        double price = machine.getPriceForTalons().getPriceForLostTalon();
        if (currentSum == 0) {
            talon.setPriceSum(price);
        } else {
            talon.setPriceSum(price);
        }
        return talon;
    }
}