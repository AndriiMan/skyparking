package com.example.skyparking.service;

import com.example.skyparking.entity.Machine;
import com.example.skyparking.entity.PriceForTalons;
import com.example.skyparking.entity.Talon;
import com.example.skyparking.repository.MachineRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.when;

class MachineServiceImplTest {
    @Mock
    private MachineRepository machineRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createNewMachine() {
        // arrange
        String expected="testTermainal";
        MachineService machineService = new MachineServiceImpl(machineRepository);
        // act
        Machine machineTest = machineService.createMachine("testTermainal");
        // assert
        Assertions.assertEquals(expected,machineTest.getName());
    }

    @Test
    void createMachineAndGetFromDB() {
        // arrange
        String expected="machineMock";
        MachineService machineService = new MachineServiceImpl(machineRepository);
        Machine machineMock = new Machine();
        machineMock.setName("machineMock");
        PriceForTalons newPriceForTalons = new PriceForTalons(12, 12,
                12, 12,1);
        machineMock.setId(1L);
        machineMock.setTalonList(new ArrayList<>());
        machineMock.setPriceForTalons(newPriceForTalons);
        when(machineRepository.findByName("testTermainal")).thenReturn(machineMock);
        // act
        Machine machineTest = machineService.createMachine("testTermainal");
        // assert
        Assertions.assertEquals(expected, machineTest.getName());
    }

    @Test
    void checkTalonIsInMachine() {
        // arrange
        MachineServiceImpl machineService = new MachineServiceImpl(machineRepository);
        Machine machineMock = new Machine("testTermainalMock");
        PriceForTalons newPriceForTalons = new PriceForTalons(12, 12,
                12, 12,1);
        machineMock.setId(1L);
        Talon talon1 = new Talon();
        Talon talon2 = new Talon();
        talon1.setNumber(12121212);
        talon2.setNumber(11111111);
        machineMock.setTalonList(new ArrayList<>(Arrays.asList(talon1, talon2)));
        machineMock.setPriceForTalons(newPriceForTalons);
        when(machineRepository.findByName("testTermainal")).thenReturn(machineMock);
        // act
        boolean aBoolean = machineService.checkTalonIsInMachine(11111111, "testTermainal");
        // assert
        Assertions.assertTrue(aBoolean);
    }
}