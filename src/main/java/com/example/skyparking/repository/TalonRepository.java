package com.example.skyparking.repository;

import com.example.skyparking.entity.Talon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TalonRepository extends JpaRepository<Talon, Long> {
    Talon findByNumber(int number);
}
