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
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "price_for_talons_id")
    private PriceForTalons priceForTalons;

    private String name;

    @OneToMany(
            mappedBy = "machine",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Talon> talonList = new ArrayList<>();

    public Machine() {

    }

    public Machine(String name) {
        this.name = name;
    }

    public List<Talon> getTalonList() {
        return talonList;
    }

    public void setTalonList(List<Talon> talonList) {
        this.talonList = talonList;
    }
}