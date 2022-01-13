package com.example.skyparking.repository;

import com.example.skyparking.entity.Client;
import com.example.skyparking.entity.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
