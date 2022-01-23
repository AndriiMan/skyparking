package com.example.skyparking.service;

import com.example.skyparking.TimeCounter;
import com.example.skyparking.entity.PriceForTalons;
import com.example.skyparking.entity.Talon;
import com.example.skyparking.entity.Machine;
import com.example.skyparking.repository.ClientRepository;
import com.example.skyparking.repository.PriceForTalonsRepository;
import com.example.skyparking.repository.TalonRepository;
import com.example.skyparking.repository.MachineRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.abs;

@Service
public class ParkingServiceImpl implements ParkingService {

    final MachineRepository machineRepository;

    final TalonRepository talonRepository;

    final PriceForTalonsRepository priceForTalonsRepository;

    final ClientRepository clientRepository;

    MachineServiceImpl machineService;

    public ParkingServiceImpl(MachineRepository machineRepository, TalonRepository talonRepository, PriceForTalonsRepository priceForTalonsRepository,
                              ClientRepository clientRepository) {
        this.machineRepository = machineRepository;
        this.talonRepository = talonRepository;
        this.priceForTalonsRepository = priceForTalonsRepository;
        this.clientRepository = clientRepository;
        this.machineService = new MachineServiceImpl(machineRepository, priceForTalonsRepository);
    }

    //TODO experement with delete newTalon.setMachine(machine);
    public void createTalon(String terminal) {
        Machine machine = machineService.createMachine(terminal);
        List<Talon> talonList = machine.getTalonList();
        Talon newTalon = new Talon();
        newTalon.setNumber((int) (Math.random() * (((Integer.MAX_VALUE - 1)) + 1) - 0));
        talonList.add(newTalon);
        newTalon.setMachine(machine);
        talonRepository.save(newTalon);
    }

    public boolean checkTalonIsInMachine(int number) {
        boolean flg = false;
        Machine machine = machineRepository.findByName("termainal1");
        List<Talon> talonList = machine.getTalonList();
        for (Talon talon : talonList) {
            if (talon.getNumber() == number) {
                flg = true;
                break;
            }
        }
        return flg;
    }

    public String exitAndSum(int number) {
        TimeCounter timeCounter = new TimeCounter();
        if (checkTalonIsInMachine(number)) {
            Talon talon = talonRepository.findByNumber(number);
            talon.setActive(false);
            talonRepository.save(talon);
            return String.valueOf(timeCounter.sumUpPrice(talon));
        } else {
            return "there is not talon in this terminal";
        }
    }

    public int countSum(String talonTime, int pricePerHour) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime timeForNow = LocalDateTime.now();
        String currentTime = dateTimeFormatter.format(timeForNow);
        return abs((int) (pricePerHour * (subTwoDate(currentTime, talonTime))));
    }

    //TODO my exeption for data
    @SneakyThrows
    public long subTwoDate(String date1, String date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.ENGLISH);
        Date firstDate = sdf.parse(date1);
        Date secondDate = sdf.parse(date2);
        long diff = secondDate.getTime() - firstDate.getTime();
        TimeUnit time = TimeUnit.MINUTES;
        return time.convert(diff, TimeUnit.MILLISECONDS);
    }
}