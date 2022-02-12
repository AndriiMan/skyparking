package com.example.skyparking.service;

import com.example.skyparking.entity.Machine;
import com.example.skyparking.entity.PriceForTalons;
import com.example.skyparking.entity.Talon;
import com.example.skyparking.repository.MachineRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class MachineServiceImpl implements MachineService {

    final MachineRepository machineRepository;

    public MachineServiceImpl(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    @PostConstruct
    public void init() {
        if (machineRepository.count() == 0) {
            Machine machine = new Machine("terminalTest1");
            PriceForTalons newPriceForTalons = new PriceForTalons(12, 12,
                    12, 12, 107);
            machine.setPriceForTalons(newPriceForTalons);
            machineRepository.save(machine);
        }
        System.out.println("Init method");
    }


    @Override
    public Machine createMachine(String terminalName) {
        if (machineRepository.findByName(terminalName) != null) {
            return machineRepository.findByName(terminalName);
        } else {
            Machine machine = new Machine(terminalName);
            PriceForTalons newPriceForTalons = new PriceForTalons(12, 12,
                    12, 12, 17);
            machine.setPriceForTalons(newPriceForTalons);
            return machine;
        }
    }

    public boolean checkTalonIsInMachine(int number, String machineName) {
        boolean flg = false;
        if (machineRepository.findByName(machineName) == null) {
            return false;
        }
        Machine machine = machineRepository.findByName(machineName);
        List<Talon> talonList = machine.getTalonList();
        for (Talon talon : talonList) {
            if (talon.getNumber() == number) {
                flg = true;
                break;
            }
        }
        return flg;
    }

    public Machine getMachineByName(String machineName) {
        return machineRepository.findByName(machineName);
    }
}