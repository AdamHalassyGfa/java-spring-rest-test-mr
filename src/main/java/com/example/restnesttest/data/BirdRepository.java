package com.example.restnesttest.data;

import com.example.restnesttest.data.entities.Bird;
import com.example.restnesttest.data.entities.Nest;
import org.springframework.data.repository.CrudRepository;

public interface BirdRepository extends CrudRepository<Bird, Long> {

}
