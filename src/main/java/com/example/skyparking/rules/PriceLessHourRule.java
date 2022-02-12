package com.example.skyparking.rules;

import com.example.skyparking.TimeCounter;
import com.example.skyparking.entity.Machine;
import com.example.skyparking.entity.PriceForTalons;
import com.example.skyparking.entity.Talon;

public class PriceLessHourRule implements RuleTime {

    TimeCounter timeCounter = new TimeCounter();

   /* //Доставатимемо з бази
    PriceForTalons priceForTalons = new PriceForTalons(50, 40,
            50, 50,18);*/

    @Override
    public boolean matches(Talon talon) {
        int minutesOfStayingInParking = timeCounter.subDate(talon.getTimeIn());
        return minutesOfStayingInParking < 60;
    }

    @Override
    public Talon count(Talon talon, double currentSum) {
        Machine machine = talon.getMachine();
        double price = machine.getPriceForTalons().getPriceLessHour();
        if (currentSum == 0) {
            talon.setPriceSum(price);
        } else {
            talon.setPriceSum(currentSum + price);
        }
        return talon;
    }
}
