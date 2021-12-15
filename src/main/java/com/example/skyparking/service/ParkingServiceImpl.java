package com.example.skyparking.service;

import com.example.skyparking.TimeCounter;
import com.example.skyparking.entity.PriceForTalons;
import com.example.skyparking.entity.Talon;
import com.example.skyparking.entity.Machine;
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

    public ParkingServiceImpl(MachineRepository machineRepository, TalonRepository talonRepository, PriceForTalonsRepository priceForTalonsRepository) {
        this.machineRepository = machineRepository;
        this.talonRepository = talonRepository;
        this.priceForTalonsRepository = priceForTalonsRepository;
    }

    public void createTalon(String terminalName) {
        if (machineRepository.findByName(terminalName) != null) {
            Machine machine = machineRepository.findByName(terminalName);
            List<Talon> talonList = machine.getTalonList();
            PriceForTalons newPriceForTalons = machine.getPriceForTalons();

            createNewTalon(machine, talonList, newPriceForTalons);
        } else {
            Machine newMachine = new Machine(terminalName);
            List<Talon> newTalonList = new ArrayList<>();
            PriceForTalons newPriceForTalons = new PriceForTalons(60, 50, 300, 500);

            createNewTalon(newMachine, newTalonList, newPriceForTalons);
        }
    }

    private void createNewTalon(Machine machine, List<Talon> talonList, PriceForTalons priceForTalons) {
        Talon newTalon = new Talon();
        List<Machine> machineList = new ArrayList<>();

        newTalon.setNumber((int) (Math.random() * (((Integer.MAX_VALUE - 1)) + 1) - 0));
        talonList.add(newTalon);
        machine.setTalonList(talonList);
        machineList.add(machine);
        newTalon.setMachine(machine);

        if (machine.getPriceForTalons() == null) {
            machine.setPriceForTalons(priceForTalons);
            priceForTalons.setTerminalsPrices(machineList);
            priceForTalonsRepository.save(priceForTalons);
        }
        //talonRepository.save(newTalon);
        machineRepository.save(machine);
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
            return String.valueOf(timeCounter.sumUpPrice(talon));
            //return String.valueOf(countSum(String.valueOf(talon.getTimeIn())));
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