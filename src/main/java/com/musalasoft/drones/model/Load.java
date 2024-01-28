package com.musalasoft.drones.model;

import com.musalasoft.drones.model.enums.LoadType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Load  extends  BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Pattern(regexp = "^[a-zA-Z0-9-_]+$", message = "Name must contain only letters, numbers, hyphens, and underscores")
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private double weight;
    @Pattern(regexp = "^[A-Z0-9_]+$", message = "Code must contain only uppercase letters, numbers, and underscores")
    @Column(unique = true,nullable = false)
    private String code;
    private String image;
    @Enumerated(EnumType.STRING)
    private LoadType loadType;

}
