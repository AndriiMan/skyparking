package com.example.skyparking.rules;

import com.example.skyparking.TimeCounter;
import com.example.skyparking.entity.PriceForTalons;
import com.example.skyparking.entity.Talon;

public class PriceMoreThenHourRule implements RuleTime {

    TimeCounter timeCounter = new TimeCounter();
    PriceForTalons priceForTalons = new PriceForTalons(50, 40, 50, 50);//Доставатимемо з бази

    @Override
    public boolean matches(Talon talon) {
        int minutesOfStayingInParking = timeCounter.subDate(talon.getTimeIn());
        return minutesOfStayingInParking >= 60 && minutesOfStayingInParking < (12 * 60);
    }


    @Override
    public Talon count(Talon talon, double currentSum) {
        int minutesOfStayingInParking = timeCounter.subDate(talon.getTimeIn());
        int numberOfHour = (minutesOfStayingInParking + 59) / 60;
        int countSumForMoreThenHour = numberOfHour * priceForTalons.getPriceMoreThenHour();

        if (currentSum == 0) {
            talon.setPriceSum(countSumForMoreThenHour);
        } else {
            talon.setPriceSum(talon.getPriceSum() * countSumForMoreThenHour);
        }
        return talon;
    }

}