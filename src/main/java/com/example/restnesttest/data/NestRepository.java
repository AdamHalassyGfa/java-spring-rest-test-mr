package com.example.restnesttest.data;

import com.example.restnesttest.data.entities.Nest;
import org.springframework.data.repository.CrudRepository;

public interface NestRepository extends CrudRepository<Nest, Long> {

    Nest findById(long id   );

}
