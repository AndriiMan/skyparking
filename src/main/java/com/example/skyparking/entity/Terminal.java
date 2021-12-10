package com.example.skyparking.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Machine")
@Getter
@Setter
public class Terminal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int pricePerHour;

    private String name;

    @OneToMany(
            mappedBy = "terminal",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Talon> talonList = new ArrayList<>();

    public Terminal() {

    }

    public Terminal(int pricePerHour, String name) {
        this.pricePerHour = pricePerHour;
        this.name = name;
    }

    public List<Talon> getTalonList() {
        return talonList;
    }

    public void setTalonList(List<Talon> talonList) {
        this.talonList = talonList;
    }
}