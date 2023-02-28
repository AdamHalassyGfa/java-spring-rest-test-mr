package com.example.restnesttest.controllers;

import com.example.restnesttest.data.entities.Nest;
import com.example.restnesttest.services.BirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.notFound;

@RestController
public class NestController {

    private final BirdService birdService;

    @Autowired
    public NestController(BirdService birdService) {

        this.birdService = birdService;
    }

    @PostMapping("api/nest")
    public ResponseEntity<Nest> create(String name) {
        Nest nest = birdService.createNest(name);
        return ResponseEntity.ok(nest);
    }

    @GetMapping("api/nest/{id}")
    public ResponseEntity<Nest> find(
            @PathVariable("id") long id
    ) {
        if (id <= 0)
            return ResponseEntity.badRequest().build();

        Optional<Nest> nest = birdService.findNestById(id);
        return nest.isPresent()
                ? ResponseEntity.ok(nest.get())
                : ResponseEntity.notFound().build();
    }

    @GetMapping("api/nest/list")
    public ResponseEntity<Nest[]> list() {
        Nest[] nests = birdService.findAll()
                .toArray(Nest[]::new);
        return ResponseEntity.ok(nests);

    }
}
