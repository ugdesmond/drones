package com.musalasoft.drones.model.enums;

import java.util.Arrays;

public enum DroneModel {
    Lightweight(1, 100),
    Middleweight(101, 200),
    Cruiserweight(201, 300),
    Heavyweight(301,500);

    private final int minValue;
    private final int maxValue;

    DroneModel(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public static DroneModel getByValue(double value) {
        return Arrays.stream(values())
                .filter(model -> value >= model.minValue && value <= model.maxValue)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No enum constant for value " + value));
    }

}

