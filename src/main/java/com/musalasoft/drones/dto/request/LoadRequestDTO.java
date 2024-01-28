package com.musalasoft.drones.dto.request;

import com.musalasoft.drones.interfaces.Base64Image;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
public class LoadRequestDTO {
    @NotBlank(message = "Name is required")
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9-_]+$", message = "Name must contain only letters, numbers, hyphens, and underscores")
    private String name;
    @Min(value = 1, message = "Weight limit must be at least 1")
    @NotNull
    @Valid
    private double weight;
    @Pattern(regexp = "^[A-Z0-9_]+$", message = "Code must contain only uppercase letters, numbers, and underscores")
    @NotNull
    private String code;
    @Base64Image
    @NotNull
    private String image;

}
