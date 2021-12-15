package com.example.skyparking.service;

import com.example.skyparking.entity.PriceForTalons;
import com.example.skyparking.entity.Talon;
import com.example.skyparking.entity.Terminal;
import com.example.skyparking.repository.PriceForTalonsRepository;
import com.example.skyparking.repository.TalonRepository;
import com.example.skyparking.repository.TerminalRepository;
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

    final TerminalRepository terminalRepository;

    final TalonRepository talonRepository;

    final PriceForTalonsRepository priceForTalonsRepository;

    public ParkingServiceImpl(TerminalRepository terminalRepository, TalonRepository talonRepository, PriceForTalonsRepository priceForTalonsRepository) {
        this.terminalRepository = terminalRepository;
        this.talonRepository = talonRepository;
        this.priceForTalonsRepository = priceForTalonsRepository;
    }

    public void createTalon(String terminalName) {
        PriceForTalons priceForTalons1 = new PriceForTalons(60, 50, 300, 500);

        if (terminalRepository.findByName(terminalName) != null) {
            Terminal terminal = terminalRepository.findByName(terminalName);
            terminal.setPriceForTalons(priceForTalons1);

            List<Talon> talonList = terminal.getTalonList();

            priceForTalons1.setTerminalsPrices(List.of(terminal));

            createNewTalon(terminal, talonList, priceForTalons1);
        } else {
            Terminal newTerminal = new Terminal(terminalName);
            List<Talon> newTalonList = new ArrayList<>();
            newTerminal.setPriceForTalons(priceForTalons1);
            createNewTalon(newTerminal, newTalonList, priceForTalons1);
        }
    }

    private void createNewTalon(Terminal terminal, List<Talon> talonList, PriceForTalons priceForTalons1) {

        Talon newTalon = new Talon();
        // Terminal terminal1 = priceForTalons1.get(0);//TODO rewrite this if priceForTalons is null
        newTalon.setNumber((int) (Math.random() * (((Integer.MAX_VALUE - 1)) + 1) - 0));
        talonList.add(newTalon);
        terminal.setPriceForTalons(priceForTalons1);
        priceForTalons1.setTerminalsPrices(List.of(terminal));
        terminal.setTalonList(talonList);
        newTalon.setTerminal(terminal);
        talonRepository.save(newTalon);
        terminalRepository.save(terminal);
        priceForTalonsRepository.save(priceForTalons1);

    }

    public boolean checkTalonIsInMachine(int number) {
        boolean flg = false;
        Terminal terminal = terminalRepository.findByName("termainal1");
        List<Talon> talonList = terminal.getTalonList();
        for (Talon talon : talonList) {
            if (talon.getNumber() == number) {
                flg = true;
                break;
            }
        }
        return flg;
    }

    public String exitAndSum(int number) {
        if (checkTalonIsInMachine(number)) {
            Talon talon = talonRepository.findByNumber(number);
            return "null is"; //String.valueOf(countSum(String.valueOf(talon.getTimeIn())));
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