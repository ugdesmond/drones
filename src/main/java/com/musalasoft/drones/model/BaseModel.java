package com.musalasoft.drones.model;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseModel   {


    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

