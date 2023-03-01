package com.example.restnesttest.data;

import com.example.restnesttest.data.entities.Bird;
import com.example.restnesttest.data.entities.Nest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BirdRepository extends CrudRepository<Bird, Long> {

    List<Bird> findByNest(Nest nest);

}
