package com.example.skyparking.entity;

import javax.persistence.*;

@Entity
@Table(name = "Client")
public class Client {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String password;
    @OneToOne
    @JoinColumn(name = "talon_id")
    private Talon talon;

    public Talon getTalon() {
        return talon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}