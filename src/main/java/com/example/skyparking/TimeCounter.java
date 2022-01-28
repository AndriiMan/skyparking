package com.example.skyparking;

import com.example.skyparking.rules.PriceForVipRule;
import com.example.skyparking.rules.PriceLessHourRule;
import com.example.skyparking.rules.PriceMoreThenHourRule;
import com.example.skyparking.rules.RuleTime;
import com.example.skyparking.entity.Talon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.abs;

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


    public int countSum(String talonTime, int pricePerHour) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime timeForNow = LocalDateTime.now();
        String currentTime = dateTimeFormatter.format(timeForNow);
        return abs((int) (pricePerHour * (subTwoDate(currentTime, talonTime))));
    }

    public long subTwoDate(String date1, String date2) {
        long diff = 0;
        TimeUnit time = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.ENGLISH);
            Date firstDate = sdf.parse(date1);
            Date secondDate = sdf.parse(date2);
            diff = secondDate.getTime() - firstDate.getTime();
            time = TimeUnit.MINUTES;
            return time.convert(diff, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int subDate(String date1) {
        long diff = 0;
        TimeUnit time = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.ENGLISH);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
            LocalDateTime timeForNow = LocalDateTime.now();
            String currentTime = dateTimeFormatter.format(timeForNow);
            Date firstDate = sdf.parse(date1);
            Date secondDate = sdf.parse(currentTime);
            diff = secondDate.getTime() - firstDate.getTime();
            time = TimeUnit.MINUTES;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (int) time.convert(diff, TimeUnit.MILLISECONDS);
    }
}
