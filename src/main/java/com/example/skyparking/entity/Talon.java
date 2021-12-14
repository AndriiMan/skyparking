package com.example.skyparking.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "Talon")
@Getter
@Setter
public class Talon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "machine_id")
    private Terminal terminal;

    private int number;

    private String timeIn;

    private double priceSum;

    boolean isActive;

    boolean isVip;

    public Talon() {
        this.timeIn = getCurrentTime();
        this.isActive = true;
    }

    public Talon(Terminal terminal) {
        this.terminal = terminal;
        this.timeIn = getCurrentTime();
        this.isActive = true;
    }

    private String getCurrentTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime timeForNow = LocalDateTime.now();
        return dateTimeFormatter.format(timeForNow);
    }

}
