package com.musalasoft.drones.unit.services;

import com.musalasoft.drones.dto.request.DroneRequestDTO;
import com.musalasoft.drones.model.Drone;
import com.musalasoft.drones.model.enums.DroneState;
import com.musalasoft.drones.repository.DroneRepository;
import com.musalasoft.drones.service.DroneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class DroneServiceTest {

    @Mock
    private DroneRepository droneRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private DroneService droneService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void testRegisterDroneServiceWithExistingDrone() {
        DroneRequestDTO droneRequestDTO = new DroneRequestDTO();
        droneRequestDTO.setSerialNumber("123");
        when(droneRepository.findBySerialNumber("123")).thenReturn(new Drone());
        assertThrows(ResponseStatusException.class,
                () -> droneService.registerDroneService(droneRequestDTO));
    }

    @Test
    void testGetAllDrones() {
        Page<Drone> mockPage = new PageImpl<>(Collections.emptyList());
        when(droneRepository.findByState(any(DroneState.class), any(Pageable.class))).thenReturn(mockPage);
        Page<Drone> result = droneService.getAllDrones(Pageable.unpaged(), DroneState.IDLE);
        assertNotNull(result);
        assertEquals(0, result.getContent().size());
    }

    @Test
    void testGetDroneByIdWithExistingId() {
        Long droneId = 1L;
        when(droneRepository.findById(droneId)).thenReturn(Optional.of(new Drone()));
        Drone result = droneService.getDroneById(droneId);
        assertNotNull(result);
    }

    @Test
    void testGetDroneByIdWithNonExistingId() {
        Long droneId = 1L;
        when(droneRepository.findById(droneId)).thenReturn(Optional.empty());
        assertNull(droneService.getDroneById(droneId));
    }
}
