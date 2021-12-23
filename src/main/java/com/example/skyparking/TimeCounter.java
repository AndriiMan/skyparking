package com.example.skyparking;

import com.example.skyparking.rules.PriceForVipRule;
import com.example.skyparking.rules.PriceLessHourRule;
import com.example.skyparking.rules.PriceMoreThenHourRule;
import com.example.skyparking.rules.RuleTime;
import com.example.skyparking.entity.Talon;

import java.util.ArrayList;
import java.util.List;

public class TimeCounter {

    public List<RuleTime> initialization() {
        RuleTime ruleTime1 = new PriceMoreThenHourRule();
        RuleTime ruleTime2 = new PriceLessHourRule();
        RuleTime ruleTime3 = new PriceForVipRule();
        List<RuleTime> rules = new ArrayList<>();
        rules.add(ruleTime1);
        rules.add(ruleTime2);
        rules.add(ruleTime3);
        return rules;
    }

    public double sumUpPrice(Talon talon) {
        List<RuleTime> rules = initialization();

        rules.stream()
                .filter(rule -> rule.matches(talon))
                .forEach(rule -> rule.count(talon, talon.getPriceSum()));

        return talon.getPriceSum();
    }
}
