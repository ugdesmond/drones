package com.musalasoft.drones.dto.response;

import lombok.Data;
@Data
public class PaginatedApiDataResponse<T> {
    private T data;
    private String message;
    private boolean isSuccessful;
    private int page;
    private int pageSize;
    private int totalPages;

    public PaginatedApiDataResponse(T data, String message, boolean isSuccessful, int page, int pageSize, int totalPages) {
        this.data = data;
        this.message = message;
        this.isSuccessful = isSuccessful;
        this.page = page;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
    }
}
