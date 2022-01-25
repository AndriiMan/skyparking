package com.example.skyparking;

import com.example.skyparking.entity.Talon;
import com.example.skyparking.service.MachineServiceImpl;
import com.example.skyparking.service.ParkingServiceImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void sumUpPrice() {
    }


    @Test
    void countSum() throws ParseException {
        // arrange
        int expected = 1;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime timeForNow = LocalDateTime.now();

        String dt = dateTimeFormatter.format(timeForNow);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(dt));
        c.add(Calendar.MINUTE, -1);
        String talonTime = sdf.format(c.getTime());
        int actual = timeCounter.countSum(talonTime, 1);

        Assertions.assertEquals(expected, actual);
    }


    @Test
    void subTwoDate() {

        String date1 = "2021/10/10 14:00";
        String date2 = "2021/10/10 14:10";

        int expected = 10;

        int actual = (int) timeCounter.subTwoDate(date1, date2);

        Assertions.assertEquals(expected, actual);
    }

    /*@Test
    void subTwoDateWithException() {

        String date1 = "14:00";
        String date2 = "2021/10/10 14:10";

        assertThrows(ParseException.class,
                () -> {
                    timeCounter.subTwoDate(date1, date2);
                });
    }*/
}
