package com.musalasoft.drones.service;

import com.musalasoft.drones.dto.request.LoadRequestDTO;
import com.musalasoft.drones.model.Load;
import com.musalasoft.drones.model.enums.LoadType;
import com.musalasoft.drones.repository.LoadRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class LoadService {

    private final LoadRepository loadRepository;
    private final CloudinaryServices cloudinaryService;


    public LoadService(LoadRepository loadRepository, CloudinaryServices cloudinaryService) {
        this.loadRepository = loadRepository;
        this.cloudinaryService = cloudinaryService;
    }

    public CompletableFuture<Load> createLoadAsync(LoadRequestDTO loadRequestDTO) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                if (loadRepository.findByCode(loadRequestDTO.getCode()) != null) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Load with the same code already exists");
                }
                Load load = new Load();
                String imageUrl = cloudinaryService.uploadBase64Image(loadRequestDTO.getImage());
                load.setImage(imageUrl);
                load.setLoadType(LoadType.MEDICATION);
                load.setCode(loadRequestDTO.getCode());
                load.setName(loadRequestDTO.getName());
                load.setWeight(loadRequestDTO.getWeight());
                return loadRepository.save(load);
            } catch (IOException e) {
                throw new RuntimeException("Error creating load", e);
            }
        });
    }

    public List<Load> getAllLoads() {
        return loadRepository.findAll();
    }

    public Load getLoadById(Long id) {
        return loadRepository.findById(id).orElse(null);
    }

    public void deleteLoad(Long id) {
        loadRepository.deleteById(id);
    }
}
