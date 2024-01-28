package com.musalasoft.drones.model;
import com.musalasoft.drones.model.enums.DroneModel;
import com.musalasoft.drones.model.enums.DroneState;

import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Drone  extends  BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String serialNumber;
    @Enumerated(EnumType.STRING)
    private DroneModel model;
    @Column(nullable = false)
    private double weightLimit;
    @Column(nullable = false)
    private int batteryCapacity;
    @Enumerated(EnumType.STRING)
    private DroneState state;


}
