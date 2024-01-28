package com.musalasoft.drones.controller;

import com.musalasoft.drones.dto.request.LoadRequestDTO;
import com.musalasoft.drones.dto.response.ApiDataResponse;
import com.musalasoft.drones.model.Load;
import com.musalasoft.drones.service.LoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/loads")
public class LoadController {

    private final LoadService loadService;

    @Autowired
    public LoadController(LoadService loadService) {
        this.loadService = loadService;
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<ApiDataResponse<Load>>> createLoadAsync(@Valid @RequestBody LoadRequestDTO load) {
        return CompletableFuture.supplyAsync(() -> {
            CompletableFuture<Load> createdLoadFuture = loadService.createLoadAsync(load);
            Load createdLoad = createdLoadFuture.join();
            ApiDataResponse<Load> response = new ApiDataResponse<>(createdLoad, "Load created successfully", true);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        });
    }



}

