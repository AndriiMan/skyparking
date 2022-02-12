package com.example.skyparking.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PriceForTalons")
public class PriceForTalons {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(
            mappedBy = "priceForTalons",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Machine> terminalsPrices = new ArrayList<>();

    int priceLessHour;
    int priceMoreThenHour;
    int pricePerTwelveHour;
    int pricePerDay;
    int priceForLostTalon;

    public PriceForTalons() {

    }

    public PriceForTalons(int priceLessHour, int priceMoreThenHour, int pricePerTwelveHour, int pricePerDay, int priceForLostTalon) {
        this.priceLessHour = priceLessHour;
        this.priceMoreThenHour = priceMoreThenHour;
        this.pricePerTwelveHour = pricePerTwelveHour;
        this.pricePerDay = pricePerDay;
        this.priceForLostTalon = priceForLostTalon;
    }
}