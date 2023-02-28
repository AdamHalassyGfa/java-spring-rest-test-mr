package com.example.restnesttest.data;

import com.example.restnesttest.data.entities.Nest;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface NestRepository extends CrudRepository<Nest, Long> {

    Optional<Nest> findById(long id);

}
