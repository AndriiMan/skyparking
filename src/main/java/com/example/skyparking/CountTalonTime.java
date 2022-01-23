package com.example.skyparking;

import com.example.skyparking.entity.Talon;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.abs;

public class CountTalonTime {
    public String exitAndSum(Talon talon) {
        return "null is ";//String.valueOf(countSum(String.valueOf(talon.getTimeIn())));
    }

    public int countSum(String talonTime, int pricePerHour) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime timeForNow = LocalDateTime.now();
        String currentTime = dateTimeFormatter.format(timeForNow);
        return abs((int) (pricePerHour * (subTwoDate(currentTime, talonTime))));
    }

    public long subTwoDate(String date1, String date2) {
        TimeUnit time = null;
        long diff = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.ENGLISH);
            Date firstDate = sdf.parse(date1);
            Date secondDate = sdf.parse(date2);
            diff = secondDate.getTime() - firstDate.getTime();
            time = TimeUnit.MINUTES;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return time.convert(diff, TimeUnit.MILLISECONDS);
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
