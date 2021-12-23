package com.example.skyparking.rules;

import com.example.skyparking.CountTalonTime;
import com.example.skyparking.entity.PriceForTalons;
import com.example.skyparking.entity.Talon;

public class PriceLessHourRule implements RuleTime {

    CountTalonTime countTalonTime = new CountTalonTime();
    PriceForTalons priceForTalons = new PriceForTalons(50, 40, 50, 50);//Доставатимемо з бази

    @Override
    public boolean matches(Talon talon) {
        int minutesOfStayingInParking = countTalonTime.subDate(talon.getTimeIn());
        return minutesOfStayingInParking < 60;
    }

    @Override
    public Talon count(Talon talon, double currentSum) {
        if (currentSum == 0) {
            talon.setPriceSum(priceForTalons.getPriceLessHour());
        } else {
            talon.setPriceSum(currentSum * priceForTalons.getPriceLessHour());
        }
        return talon;
    }
}
