package com.example.restnesttest.controllers;

import com.example.restnesttest.data.entities.Bird;
import com.example.restnesttest.services.BirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BirdController {

    private final BirdService birdService;

    @Autowired
    public BirdController(BirdService birdService) {
        this.birdService = birdService;
    }

    @PostMapping("api/bird")
    public ResponseEntity<Bird> createBird(int nestID, String name) {
        Bird bird = birdService.createBird(nestID, name);
        return ResponseEntity.ok(bird);
    }
}
