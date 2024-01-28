package com.musalasoft.drones.repository;

import com.musalasoft.drones.model.Drone;
import com.musalasoft.drones.model.Load;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadRepository extends JpaRepository<Load, Long> {
    Load findByCode(String code);

}

