package com.example.skyparking.service;

import com.example.skyparking.entity.Machine;
import com.example.skyparking.entity.PriceForTalons;
import com.example.skyparking.entity.Talon;
import com.example.skyparking.repository.MachineRepository;
import com.example.skyparking.repository.PriceForTalonsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineServiceImpl implements MachineService {

    final MachineRepository machineRepository;

    final PriceForTalonsRepository priceForTalonsRepository;

    public MachineServiceImpl(MachineRepository machineRepository, PriceForTalonsRepository priceForTalonsRepository) {
        this.machineRepository = machineRepository;
        this.priceForTalonsRepository = priceForTalonsRepository;
    }

    @Override
    public Machine createMachine(String terminalName) {
        if (machineRepository.findByName(terminalName) != null) {
            return machineRepository.findByName(terminalName);
        } else {
            Machine machine = new Machine(terminalName);
            PriceForTalons newPriceForTalons = new PriceForTalons(12, 12, 12, 12);
            machine.setPriceForTalons(newPriceForTalons);
            return machine;
        }
    }

    public boolean checkTalonIsInMachine(int number, String machineName) {
        boolean flg = false;
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
}
