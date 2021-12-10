package com.example.skyparking.repository;

import com.example.skyparking.entity.Talon;
import com.example.skyparking.entity.Terminal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TalonRepository extends CrudRepository<Talon, Long> {
    Talon findByNumber(int number);
}
