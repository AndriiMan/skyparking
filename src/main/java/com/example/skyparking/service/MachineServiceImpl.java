package com.example.skyparking.service;

import com.example.skyparking.entity.Machine;
import com.example.skyparking.entity.PriceForTalons;
import com.example.skyparking.entity.Talon;
import com.example.skyparking.repository.MachineRepository;
import com.example.skyparking.repository.PriceForTalonsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
            PriceForTalons newPriceForTalons = new PriceForTalons();
            machine.setPriceForTalons(newPriceForTalons);
            List<Machine> machineList = new ArrayList<>();
            newPriceForTalons.setTerminalsPrices(machineList);
            priceForTalonsRepository.save(newPriceForTalons);

            return machine;
        }
    }
}
