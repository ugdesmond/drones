package com.musalasoft.drones.repository;

import com.musalasoft.drones.model.Drone;
import com.musalasoft.drones.model.enums.DroneState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {

    Drone findBySerialNumber(String serialNumber);
    Page<Drone> findByState(DroneState state, Pageable pageable);
    @Query("SELECT d.batteryCapacity FROM Drone d WHERE d.id = :id")
    Optional<Integer> findBatteryLevelById(Long id);


}