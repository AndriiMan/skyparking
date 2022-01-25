package com.example.skyparking.repository;

import com.example.skyparking.entity.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {
    Machine findByName(String name);
}
