package com.example.skyparking.controller;

import com.example.skyparking.service.MachineServiceImpl;
import com.example.skyparking.service.ParkingServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    final ParkingServiceImpl parkingServiceImpl;
    final MachineServiceImpl machineService;

    public MainController(ParkingServiceImpl parkingServiceImpl, MachineServiceImpl machineService) {
        this.parkingServiceImpl = parkingServiceImpl;
        this.machineService = machineService;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    @ResponseBody
    public String homePage() {
        return "home";
    }


    //@PostMapping("/createTalon")
    @ResponseBody
    @RequestMapping(value = "/createTalon", method = RequestMethod.POST)
    public ResponseEntity<String> createTalon(@RequestParam String terminalName) {
        try {
            parkingServiceImpl.createTalon(terminalName);
            return new ResponseEntity<>("CREATED", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/checkTalon", method = RequestMethod.GET, params = {"number", "machineName"})
    @ResponseBody
    public ResponseEntity<String> checkTalon(@RequestParam int number, String machineName) {
        try {
            String s = String.valueOf(parkingServiceImpl.exit(number, machineName));
            return new ResponseEntity<>(s, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}