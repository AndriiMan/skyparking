package com.example.skyparking;

import com.example.skyparking.entity.Talon;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

class TimeCounterTest {

    TimeCounter timeCounter = new TimeCounter();
    Talon talon = new Talon();
    Calendar calendar;
    String dateTime;
    SimpleDateFormat simpleDateFormat;

    @SneakyThrows
    @BeforeEach
    void init() {
        talon.setVip(true);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime timeForNow = LocalDateTime.now();
        dateTime = dateTimeFormatter.format(timeForNow);
        simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        calendar = Calendar.getInstance();
        calendar.setTime(simpleDateFormat.parse(dateTime));
    }

    @Test
    void isOneHour() {
        calendar.add(Calendar.MINUTE, -59);
        dateTime = simpleDateFormat.format(calendar.getTime());
        talon.setTimeIn(dateTime);

        double expected = 25;
        double actual = timeCounter.sumUpPrice(talon);
        Assertions.assertEquals(expected, actual);
    }

    @SneakyThrows
    @Test
    void isTwoHour() {
        calendar.add(Calendar.MINUTE, -120);
        dateTime = simpleDateFormat.format(calendar.getTime());
        talon.setTimeIn(dateTime);

        double expected = 40;
        double actual = timeCounter.sumUpPrice(talon);

        Assertions.assertEquals(expected, actual);

    }

    @SneakyThrows
    @Test
    void isTwoHourAndTwoMinutes() {
        calendar.add(Calendar.MINUTE, -122);
        dateTime = simpleDateFormat.format(calendar.getTime());
        String talonTime = dateTime;
        talon.setTimeIn(talonTime);

        double expected = 60;
        double actual = timeCounter.sumUpPrice(talon);

        Assertions.assertEquals(expected, actual);
    }
}