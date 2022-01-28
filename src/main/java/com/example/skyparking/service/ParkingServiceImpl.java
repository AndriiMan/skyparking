package com.example.skyparking.service;

import com.example.skyparking.TimeCounter;
import com.example.skyparking.entity.PriceForTalons;
import com.example.skyparking.entity.Talon;
import com.example.skyparking.entity.Machine;
import com.example.skyparking.repository.ClientRepository;
import com.example.skyparking.repository.PriceForTalonsRepository;
import com.example.skyparking.repository.TalonRepository;
import com.example.skyparking.repository.MachineRepository;
import com.example.skyparking.rules.RuleTime;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.abs;

@Service
public class ParkingServiceImpl implements ParkingService {

    final TalonRepository talonRepository;

    final ClientRepository clientRepository;

    final MachineServiceImpl machineService;

    public ParkingServiceImpl(
            TalonRepository talonRepository,
            ClientRepository clientRepository,
            MachineServiceImpl machineService) {
        this.talonRepository = talonRepository;
        this.clientRepository = clientRepository;
        this.machineService = machineService;
    }

    public Talon createTalon(String terminal) {
        Machine machine = machineService.createMachine(terminal);
        Talon newTalon = new Talon();
        newTalon.setNumber((int) (Math.random() * (((Integer.MAX_VALUE - 1)) + 1) - 0));
        newTalon.setMachine(machine);
        talonRepository.save(newTalon);

        return newTalon;
    }

    public String exit(int number, String machine) {
        TimeCounter timeCounter = new TimeCounter();
        if (machineService.checkTalonIsInMachine(number, machine)) {
            Talon talon = talonRepository.findByNumber(number);
            talon.setActive(false);
            talonRepository.save(talon);
            return String.valueOf(timeCounter.sumUpPrice(talon));
        } else {
            return "there is not talon in this terminal";
        }
    }

}