package com.example.skyparking;

import com.example.skyparking.entity.Talon;
import lombok.SneakyThrows;

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

    @SneakyThrows
    public long subTwoDate(String date1, String date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.ENGLISH);
        Date firstDate = sdf.parse(date1);
        Date secondDate = sdf.parse(date2);
        long diff = secondDate.getTime() - firstDate.getTime();
        TimeUnit time = TimeUnit.MINUTES;
        return time.convert(diff, TimeUnit.MILLISECONDS);
    }

    @SneakyThrows
    public int subDate(String date1) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.ENGLISH);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime timeForNow = LocalDateTime.now();
        String currentTime = dateTimeFormatter.format(timeForNow);
        Date firstDate = sdf.parse(date1);
        Date secondDate = sdf.parse(currentTime);
        long diff = secondDate.getTime() - firstDate.getTime();
        TimeUnit time = TimeUnit.MINUTES;
        return (int) time.convert(diff, TimeUnit.MILLISECONDS);
    }
}
