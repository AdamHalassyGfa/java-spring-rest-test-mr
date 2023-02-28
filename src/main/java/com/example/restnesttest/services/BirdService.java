package com.example.restnesttest.services;

import com.example.restnesttest.data.NestRepository;
import com.example.restnesttest.data.entities.Nest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class BirdService {

    private final NestRepository nestRepository;

    @Autowired
    public  BirdService(NestRepository nestRepository)    {
        this.nestRepository = nestRepository;
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
}
