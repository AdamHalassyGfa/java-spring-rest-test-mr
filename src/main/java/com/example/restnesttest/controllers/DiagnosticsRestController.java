package com.example.restnesttest.controllers;

import com.example.restnesttest.model.EchoResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class DiagnosticsRestController
{
    @RequestMapping("api/diagnostics/echo")
    public EchoResponse echo(String message) {
        return new EchoResponse(message);
    }

}
