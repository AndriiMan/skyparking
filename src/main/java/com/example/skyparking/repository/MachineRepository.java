package com.example.skyparking.repository;

import com.example.skyparking.entity.Machine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineRepository extends CrudRepository<Machine, Long> {
    Machine findByName(String name);
}
