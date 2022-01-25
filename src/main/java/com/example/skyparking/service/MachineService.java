package com.example.skyparking.service;

import com.example.skyparking.entity.Machine;
import org.springframework.stereotype.Service;

public interface MachineService {
    Machine createMachine(String terminalName);
}
