package com.example.restnesttest.controllers;

import com.example.restnesttest.data.entities.Nest;
import com.example.restnesttest.services.BirdService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.swing.*;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DiagnosticsRestController.class)
public class NestRestControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createNestTest() throws Exception {
        BirdService birdMock = mock(BirdService.class);
        when(birdMock.createNest(anyString()))
                .thenAnswer(a -> new Nest(666l, a.getArgument(0)));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/nest")
                        .param("name", "TEST")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(666l)))
                .andExpect(jsonPath("$.name", is("TEST")));
    }
}
