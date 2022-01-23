package com.example.skyparking.service;

import com.example.skyparking.entity.Machine;
import com.example.skyparking.entity.PriceForTalons;
import com.example.skyparking.entity.Talon;
import com.example.skyparking.repository.ClientRepository;
import com.example.skyparking.repository.PriceForTalonsRepository;
import com.example.skyparking.repository.TalonRepository;
import com.example.skyparking.repository.MachineRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class ParkingServiceTest {

    ParkingServiceImpl parkingServiceImpl;

    @Mock
    private MachineRepository machineRepository;
    @Mock
    private TalonRepository talonRepository;
    @Mock
    private PriceForTalonsRepository priceForTalonsRepository;
    @Mock
    private ClientRepository clientRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        parkingServiceImpl = new ParkingServiceImpl(machineRepository, talonRepository, priceForTalonsRepository, clientRepository);

        PriceForTalons newPriceForTalons = new PriceForTalons(60, 50, 300, 500);
        Machine machine = new Machine("terminal1");
        machine.setPriceForTalons(newPriceForTalons);

        Talon talon1 = new Talon(machine);
        talon1.setNumber(11110111);
        talon1.setActive(false);

        Talon talon2 = new Talon(machine);
        talon2.setNumber(11111111);
        talon1.setActive(false);

        List<Talon> talonList = Arrays.asList(talon1, talon2);
        machine.setTalonList(talonList);

        when(talonRepository.findByNumber(11110111)).thenReturn(talon1);
        when(talonRepository.findByNumber(11111111)).thenReturn(talon2);
        when(machineRepository.findByName("termainal1")).thenReturn(machine);
    }


    @Test
    void createTalon() {
//        parkingServiceImpl.createTalon("test");
//
        //when(machineRepository.findByName("test")).thenReturn(machine)
    }

    @Test
    void createNewTalon() {

    }

    @Test
    void checkTalonIsInMachine() {
        // arrange
        boolean expected = true;

        // act
        boolean actual = parkingServiceImpl.checkTalonIsInMachine(11110111);

        // assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void exitAndSumCorrectTalon() {
        String actual = parkingServiceImpl.exitAndSum(11111111);
        String expected = "50.0";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void exitAndSumNotCorrectTalon() {
        String actual = parkingServiceImpl.exitAndSum(21111111);
        String expected = "there is not talon in this terminal";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void countSum() throws ParseException {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime timeForNow = LocalDateTime.now();

        String dt = dateTimeFormatter.format(timeForNow);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(dt));
        c.add(Calendar.MINUTE, -1);
        dt = sdf.format(c.getTime());

        String talonTime = dt;

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

    @Test
    void subTwoDateWithException() {

        String date1 = "14:00";
        String date2 = "2021/10/10 14:10";

        assertThrows(ParseException.class,
                () -> {
                    parkingServiceImpl.subTwoDate(date1, date2);
                });
    }

}
