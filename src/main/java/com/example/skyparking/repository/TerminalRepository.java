package com.example.skyparking.repository;

import com.example.skyparking.entity.Terminal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TerminalRepository extends CrudRepository<Terminal, Long> {
    Terminal findByName(String name);
}
