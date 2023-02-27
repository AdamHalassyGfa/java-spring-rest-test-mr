package com.example.restnesttest.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.Charset;
import static org.hamcrest.core.Is.is;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DiagnosticsRestController.class)
class DiagnosticsRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void echo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/diagnostics/echo")
                        .param("message", "hello")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is("hello")));

    }
}