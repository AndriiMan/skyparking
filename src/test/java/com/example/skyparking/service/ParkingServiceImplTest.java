package com.example.skyparking.service;

import com.example.skyparking.entity.Machine;
import com.example.skyparking.entity.PriceForTalons;
import com.example.skyparking.entity.Talon;
import com.example.skyparking.repository.ClientRepository;
import com.example.skyparking.repository.MachineRepository;
import com.example.skyparking.repository.PriceForTalonsRepository;
import com.example.skyparking.repository.TalonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.mockito.Mockito.when;

class ParkingServiceImplTest {

    @Mock
    private TalonRepository talonRepository;
    @Mock
    private PriceForTalonsRepository priceForTalonsRepository;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private MachineRepository machineRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        //TODO put when() to his own test, not BeforeEach, add test to machine service
        //подумай над тим щоб логічно відділити класи
        //машини і талона(сервіси)
    }

    @Test
    void createTalon() {
        // arrange
        String actual = "test";
        MachineServiceImpl machineService = new MachineServiceImpl(machineRepository, priceForTalonsRepository);
        ParkingServiceImpl parkingServiceImpl = new ParkingServiceImpl(talonRepository,
                priceForTalonsRepository, clientRepository, machineService);
        // act
        Talon talon = parkingServiceImpl.createTalon("test");

        // assert
        Assertions.assertEquals(actual, talon.getMachine().getName());
    }

    @Test
    void exitAndSumCorrectTalon() {
        // arrange
        String expected = "50.0";
        MachineServiceImpl machineService = new MachineServiceImpl(machineRepository, priceForTalonsRepository);
        ParkingServiceImpl parkingServiceImpl = new ParkingServiceImpl(talonRepository,
                priceForTalonsRepository, clientRepository, machineService);
        Talon talon1 = new Talon();
        talon1.setNumber(11111111);
        talon1.setActive(true);

        Machine machine = new Machine("terminal1");
        PriceForTalons newPriceForTalons = new PriceForTalons(60, 50, 300, 500);
        machine.setPriceForTalons(newPriceForTalons);
        List<Talon> talonList = new ArrayList<>();
        talonList.add(talon1);
        machine.setTalonList(talonList);
        talon1.setMachine(machine);


        when(talonRepository.findByNumber(11111111)).thenReturn(talon1);
        when(machineRepository.findByName("terminal1")).thenReturn(machine);

        // act
        String actual = parkingServiceImpl.exitAndSum(11111111, "terminal1");

        // assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void exitAndSumNotCorrectTalon() {
        // arrange
        String expected = "there is not talon in this terminal";
        MachineServiceImpl machineService = new MachineServiceImpl(machineRepository, priceForTalonsRepository);
        ParkingServiceImpl parkingServiceImpl = new ParkingServiceImpl(talonRepository,
                priceForTalonsRepository, clientRepository, machineService);
        Talon talon1 = new Talon();
        talon1.setNumber(21111111);
        talon1.setActive(true);

        Machine machine = new Machine("terminal1");
        PriceForTalons newPriceForTalons = new PriceForTalons(60, 50, 300, 500);
        machine.setPriceForTalons(newPriceForTalons);
        List<Talon> talonList = new ArrayList<>();
        talonList.add(talon1);
        machine.setTalonList(talonList);
        talon1.setMachine(machine);


        when(talonRepository.findByNumber(11111111)).thenReturn(talon1);
        when(machineRepository.findByName("terminal1")).thenReturn(machine);

        // act
        String actual = parkingServiceImpl.exitAndSum(11111111, "terminal1");

        // assert
        Assertions.assertEquals(expected, actual);
    }
}
