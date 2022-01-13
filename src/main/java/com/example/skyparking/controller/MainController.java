package com.example.skyparking.controller;

import com.example.skyparking.service.ParkingServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    private final ParkingServiceImpl parkingServiceImpl;

    public MainController(ParkingServiceImpl parkingServiceImpl) {
        this.parkingServiceImpl = parkingServiceImpl;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String homePage() {
        return "home";
    }


    //@PostMapping("/createTalon")
    @ResponseBody
    @RequestMapping(value = "/createTalon", method = RequestMethod.POST)
    public ResponseEntity<String> createTalon() {
        try {
            parkingServiceImpl.createTalon("termainal1");
            return new ResponseEntity<>("CREATED", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/checkTalon", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> checkTalon(@RequestParam int number) {
        try {
            String s = String.valueOf(parkingServiceImpl.exitAndSum(number));
            return new ResponseEntity<>(s, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}