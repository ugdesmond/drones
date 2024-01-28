package com.musalasoft.drones.dto.response;

import lombok.Data;

@Data
public class ApiDataResponse<T> {
    private T data;
    private String message;
    private boolean isSuccessful;


    public ApiDataResponse(T data, String message, boolean isSuccessful) {
        this.data = data;
        this.message = message;
        this.isSuccessful = isSuccessful;
    }
    public ApiDataResponse( String message, boolean isSuccessful) {
        this.message = message;
        this.isSuccessful = isSuccessful;
    }
}
