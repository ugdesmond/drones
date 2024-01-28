package com.musalasoft.drones.service;

import com.musalasoft.drones.dto.request.DroneRequestDTO;
import com.musalasoft.drones.model.Drone;
import com.musalasoft.drones.model.enums.DroneModel;
import com.musalasoft.drones.model.enums.DroneState;
import com.musalasoft.drones.repository.DroneRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DroneService {

    private final DroneRepository droneRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DroneService(DroneRepository droneRepository, ModelMapper modelMapper) {
        this.droneRepository = droneRepository;
        this.modelMapper = modelMapper;
    }

    public Drone registerDroneService(DroneRequestDTO droneRequestDTO) {
        if (droneRepository.findBySerialNumber(droneRequestDTO.getSerialNumber()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Drone with the same serial number already exists");
        }
        Drone drone = modelMapper.map(droneRequestDTO, Drone.class);
        drone.setState(DroneState.IDLE);
        drone.setModel(DroneModel.getByValue(drone.getWeightLimit()));
        return droneRepository.save(drone);
    }

    public  Page<Drone> getAllDrones(Pageable pageable, DroneState stateStatus) {
        return droneRepository.findByState(stateStatus, pageable);
    }
    public Integer getDroneBatteryLevelById(Long id) {
        return droneRepository.findBatteryLevelById(id)
                .orElse(null);
    }

    public Drone getDroneById(Long id) {
        return droneRepository.findById(id).orElse(null);
    }

}
