package com.example.skyparking.service;

import com.example.skyparking.entity.Talon;
import com.example.skyparking.entity.Terminal;
import com.example.skyparking.repository.TalonRepository;
import com.example.skyparking.repository.TerminalRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.abs;

@Service
public class ParkingServiceImpl implements ParkingService{

    final TerminalRepository terminalRepository;

    final TalonRepository talonRepository;

    public ParkingServiceImpl(TerminalRepository terminalRepository, TalonRepository talonRepository) {
        this.terminalRepository = terminalRepository;
        this.talonRepository = talonRepository;
    }

    public void createTalon(String terminalName) {
        if (terminalRepository.findByName(terminalName) != null) {
            Terminal terminal = terminalRepository.findByName(terminalName);
            List<Talon> talonList = terminal.getTalonList();
            createNewTalon(terminal, talonList);
        } else {
            Terminal newTerminal = new Terminal(30, terminalName);
            List<Talon> newTalonList = new ArrayList<>();
            createNewTalon(newTerminal, newTalonList);
        }
    }

    private void createNewTalon(Terminal terminal, List<Talon> talonList) {
        Talon newTalon2 = new Talon();
        newTalon2.setNumber((int) (Math.random() * (((Integer.MAX_VALUE - 1)) + 1) - 0));
        talonList.add(newTalon2);
        terminal.setTalonList(talonList);
        newTalon2.setTerminal(terminal);
        talonRepository.save(newTalon2);
        terminalRepository.save(terminal);
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
            return String.valueOf(countSum(String.valueOf(talon.getTimeIn()), talon.getTerminal().getPricePerHour()));
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