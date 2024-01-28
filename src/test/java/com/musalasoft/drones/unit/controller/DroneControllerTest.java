package com.musalasoft.drones.unit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musalasoft.drones.controller.DroneController;
import com.musalasoft.drones.dto.request.DroneRequestDTO;
import com.musalasoft.drones.model.Drone;
import com.musalasoft.drones.repository.DroneRepository;
import com.musalasoft.drones.service.DroneService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(DroneController.class)
@ExtendWith(MockitoExtension.class)

class DroneControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private DroneController droneController;

    @MockBean
    private DroneService droneService;

    @MockBean
    private DroneRepository droneRepository;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void testRegisterDrone() throws Exception {
        DroneRequestDTO droneRequestDTO = new DroneRequestDTO();
        droneRequestDTO.setSerialNumber("123");
        droneRequestDTO.setBatteryCapacity(100);
        droneRequestDTO.setWeightLimit(2.5);
        Drone registeredDrone = new Drone();
        registeredDrone.setId(1L);
        registeredDrone.setSerialNumber("123");
        droneRequestDTO.setBatteryCapacity(100);

        when(droneService.registerDroneService(droneRequestDTO)).thenReturn(registeredDrone);

        mockMvc.perform(MockMvcRequestBuilders.post("/drones")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(droneRequestDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.status").doesNotExist())
                .andExpect(jsonPath("$.data.serialNumber").value(registeredDrone.getSerialNumber()))
                .andExpect(jsonPath("$.data.batteryCapacity").value(registeredDrone.getBatteryCapacity()))
                .andExpect(jsonPath("$.message").value("Drone registered successfully"))
                .andExpect(jsonPath("$.successful").value(true));

        verify(droneService, times(1)).registerDroneService(droneRequestDTO);
    }



    @Test
    void testRegisterDroneWithInvalidDto() throws Exception {
        DroneRequestDTO invalidDto = new DroneRequestDTO();
        invalidDto.setSerialNumber("123");
        invalidDto.setBatteryCapacity(0);
        invalidDto.setWeightLimit(0);
        mockMvc.perform(MockMvcRequestBuilders.post("/drones")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidDto)))
                .andExpect(status().isBadRequest()) ;}





}
