package com.example.skyparking.repository;

import com.example.skyparking.entity.PriceForTalons;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceForTalonsRepository extends CrudRepository<PriceForTalons, Long> {
}
