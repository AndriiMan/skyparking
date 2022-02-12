package com.example.skyparking.rules;

import com.example.skyparking.TimeCounter;
import com.example.skyparking.entity.Machine;
import com.example.skyparking.entity.PriceForTalons;
import com.example.skyparking.entity.Talon;

public class PriceMoreThenHourRule implements RuleTime {

    TimeCounter timeCounter = new TimeCounter();

    @Override
    public boolean matches(Talon talon) {
        int minutesOfStayingInParking = timeCounter.subDate(talon.getTimeIn());
        return minutesOfStayingInParking >= 60 && minutesOfStayingInParking < (12 * 60);
    }

    @Override
    public Talon count(Talon talon, double currentSum) {
        int minutesOfStayingInParking = timeCounter.subDate(talon.getTimeIn());
        int numberOfHour = (minutesOfStayingInParking + 59) / 60;
        Machine machine = talon.getMachine();
        double price = machine.getPriceForTalons().getPriceMoreThenHour() * numberOfHour;
        if (currentSum == 0) {
            talon.setPriceSum(price);
        } else {
            talon.setPriceSum(talon.getPriceSum() * price);
        }
        return talon;
    }
}