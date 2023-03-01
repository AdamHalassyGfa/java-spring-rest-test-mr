package com.example.restnesttest.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NestNotFoundException extends RuntimeException {
    public NestNotFoundException(String s) {
        super(s);
    }
}
