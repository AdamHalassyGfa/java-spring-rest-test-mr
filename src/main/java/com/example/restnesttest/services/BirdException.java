package com.example.restnesttest.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class BirdException extends RuntimeException {
    public BirdException(String s) {
        super(s);
    }
}
