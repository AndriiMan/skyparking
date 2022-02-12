/*
package com.example.skyparking;

import com.example.skyparking.entity.PriceForTalons;
import com.example.skyparking.repository.PriceForTalonsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.util.Properties;

//OR use @PostConstruct init() in MachineServiceImpl.class
@Configuration
public class BootStrap {

    @Bean
    CommandLineRunner initDatabase(@Autowired PriceForTalonsRepository repository) {
        PriceForTalons newPriceForTalons = new PriceForTalons(12, 12,
                12, 12, 12);
        return args -> {
            if (repository.count() == 0) {
                System.out.println("Preloading " + repository.save(newPriceForTalons));
            }
        };
    }
}
*/
