package com.example.restnesttest.model;

public class EchoResponse {

    private final String message;

    public EchoResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
