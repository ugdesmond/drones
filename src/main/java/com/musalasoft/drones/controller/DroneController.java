package com.musalasoft.drones.controller;

import com.musalasoft.drones.dto.request.DroneRequestDTO;
import com.musalasoft.drones.dto.response.ApiDataResponse;
import com.musalasoft.drones.dto.response.PaginatedApiDataResponse;
import com.musalasoft.drones.model.Drone;
import com.musalasoft.drones.model.enums.DroneState;
import com.musalasoft.drones.service.DroneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/drones")
@Api(tags = "Drones", value = "Operations related to drones")
@Validated
public class DroneController {
    private final DroneService droneService;

    @Autowired
    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }


    @ApiOperation("Register a new drone")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiDataResponse<Drone>> registerDrone(@Valid @RequestBody DroneRequestDTO droneRequestDTO) {
        Drone registeredDrone = droneService.registerDroneService(droneRequestDTO);
        ApiDataResponse<Drone> response = new ApiDataResponse<>(registeredDrone, "Drone registered successfully", true);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<PaginatedApiDataResponse<List<Drone>>> getAllDrones(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "IDLE") DroneState stateStatus) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Drone> drones = droneService.getAllDrones(pageable, stateStatus);
        List<Drone> droneList = drones.getContent();
        PaginatedApiDataResponse<List<Drone>> response = new PaginatedApiDataResponse<>(droneList, "Drones retrieved successfully", true, page, pageSize, drones.getTotalPages());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Drone>> getDroneById(@PathVariable Long id) {
        Drone drone = droneService.getDroneById(id);
        ApiDataResponse<Drone> response = new ApiDataResponse<>(drone, "Drone retrieved successfully", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/battery-level")
    public ResponseEntity<ApiDataResponse<Integer>> getDroneBatteryLevel(@PathVariable Long id) {
        Integer batteryLevel = droneService.getDroneBatteryLevelById(id);
        ApiDataResponse<Integer> response = new ApiDataResponse<>(batteryLevel, "Drone battery level retrieved successfully", true);
        return ResponseEntity.ok(response);
    }

}



