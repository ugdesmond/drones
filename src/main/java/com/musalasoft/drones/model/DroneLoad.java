package com.musalasoft.drones.model;
import com.musalasoft.drones.model.enums.DroneState;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class DroneLoad extends  BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "droneId")
    private Drone drone;

    @ManyToOne
    @JoinColumn(name = "loadId")
    private Load load;
    @Enumerated(EnumType.STRING)
    private DroneState status;

}
