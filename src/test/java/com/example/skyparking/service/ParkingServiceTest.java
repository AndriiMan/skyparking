package com.example.skyparking.service;

import com.example.skyparking.repository.ClientRepository;
import com.example.skyparking.repository.PriceForTalonsRepository;
import com.example.skyparking.repository.TalonRepository;
import com.example.skyparking.repository.MachineRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;


class ParkingServiceTest {
    @Mock
    private MachineRepository machineRepository;
    @Mock
    private TalonRepository talonRepository;
    @Mock
    private PriceForTalonsRepository priceForTalonsRepository;

    @Mock
    private ClientRepository clientRepository;

    ParkingServiceImpl parkingServiceImpl = new ParkingServiceImpl(machineRepository, talonRepository, priceForTalonsRepository, clientRepository);

    @Test
    void countSum() {

    }

    @Test
    void createTalon() {
    }

    @Test
    void checkTalonIsInMachine() {
    }

    @Test
    void exitAndSum() {
    }

    @Test
    void testCountSum() throws ParseException {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime timeForNow = LocalDateTime.now();

        String dt = dateTimeFormatter.format(timeForNow);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(dt));
        c.add(Calendar.MINUTE, -1);
        dt = sdf.format(c.getTime());

        String talonTime = dt;
        ;
        int expected = 1;

        int actual = parkingServiceImpl.countSum(talonTime, 1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void subTwoDate() {

        String date1 = "2021/10/10 14:00";
        String date2 = "2021/10/10 14:10";

        int expected = 10;

        int actual = (int) parkingServiceImpl.subTwoDate(date1, date2);

        Assertions.assertEquals(expected, actual);
    }

}
