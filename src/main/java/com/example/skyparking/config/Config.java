package com.example.skyparking.config;

import com.example.skyparking.entity.PriceForTalons;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
public class Config {

    private final Properties prop = new Properties();

    @Bean
    public PriceForTalons priceForTalons() {
        PriceForTalons price = new PriceForTalons();
        try (InputStream input = new FileInputStream("src/main/resources/application.properties")) {
            prop.load(input);
            price.setPriceLessHour(getPriceForProperties("price.priceLessHour"));
            price.setPriceMoreThenHour(getPriceForProperties("price.priceMoreThenHour"));
            price.setPricePerTwelveHour(getPriceForProperties("price.pricePerTwelveHour"));
            price.setPricePerDay(getPriceForProperties("price.pricePerDay"));
            price.setPriceForLostTalon(getPriceForProperties("price.priceForLostTalon"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return price;
    }

    private int getPriceForProperties(String s) {
        return Integer.parseInt(prop.getProperty(s));
    }
}