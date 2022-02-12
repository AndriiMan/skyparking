package com.example.skyparking.service;

import com.example.skyparking.TimeCounter;
import com.example.skyparking.entity.Talon;
import com.example.skyparking.entity.Machine;
import com.example.skyparking.repository.ClientRepository;
import com.example.skyparking.repository.TalonRepository;
import org.springframework.stereotype.Service;

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
        if (number == 0) {
            //TODO how to improve?
            if (machineService.getMachineByName(machine) == null) {
                return "there isn't such machine";
            } else {
                Talon lostTalon = new Talon();
                lostTalon.setNumber(0);
                lostTalon.setMachine(machineService.getMachineByName(machine));
                System.out.println("exit with num 0");
                return String.valueOf(timeCounter.sumUpPrice(lostTalon));
            }
        }
        if (machineService.checkTalonIsInMachine(number, machine)) {
            Talon talon = talonRepository.findByNumber(number);
            if (talon.isActive()) {
                talon.setActive(false);
                talonRepository.save(timeCounter.sumUpPrice(talon));
                return String.valueOf(talon.getPriceSum());
            } else {
                return "Talon is not active";
            }
        } else {
            return "there is not talon in this terminal";
        }
    }
}