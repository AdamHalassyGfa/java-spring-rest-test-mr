package com.example.restnesttest.services;

import com.example.restnesttest.data.BirdRepository;
import com.example.restnesttest.data.NestRepository;
import com.example.restnesttest.data.entities.Bird;
import com.example.restnesttest.data.entities.Nest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class BirdService {

    private final NestRepository nestRepository;
    private final BirdRepository birdRepository;

    @Autowired
    public BirdService(
            NestRepository nestRepository,
            BirdRepository birdRepository
    ) {
        this.nestRepository = nestRepository;
        this.birdRepository = birdRepository;
    }

    public Nest[] getAllNests() {
        return new Nest[0];
    }

    public Optional<Nest> findNestById(long id) {
        return nestRepository.findById(id);
    }

    public Nest createNest(String name) {
        Nest newNest = new Nest();
        newNest.setName(name);

        return nestRepository.save(newNest);
    }

    public List<Nest> findAll() {
        return StreamSupport
                .stream(nestRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Bird createBird(int nestID, String name) {
        Optional<Nest> nest = findNestById(nestID);
        if (nest.isEmpty())
            throw new BirdException("Nest not found.");

        Bird bird = new Bird(0l, name, nest.get());
        return birdRepository.save(bird);
    }
}
