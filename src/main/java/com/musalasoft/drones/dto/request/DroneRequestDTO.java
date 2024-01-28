package com.musalasoft.drones.dto.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;


@Data
@ApiModel(description = "DTO for creating or updating a Drone")
public class DroneRequestDTO {


    @NotNull
    @NotEmpty
    @Valid
    @Size(max = 100, message = "Serial number must be at most 100 characters")
    private String serialNumber;


    @Min(value = 1, message = "Weight limit must be at least 1")
    @Max(value = 500, message = "Weight limit cannot exceed 500")
    @NotNull
    @Valid
    private double weightLimit;

    @NotNull(message = "Battery capacity is required")
    @Positive(message = "Battery capacity must be a positive value")
    @Min(value = 1, message = "Battery capacity limit must be at least 1")
    @Max(value = 100, message = "Battery capacity limit cannot exceed 100")
    private int batteryCapacity;

}
