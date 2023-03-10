package com.example.restnesttest.controllers;

import com.example.restnesttest.data.entities.Bird;
import com.example.restnesttest.data.entities.Nest;
import com.example.restnesttest.services.BirdService;
import com.example.restnesttest.services.NestNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NestController.class)
public class NestRestControllerTests {

    @MockBean
    private BirdService birdService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getNestTestOkTest() throws Exception {
        when(birdService.findNestById(anyLong()))
                .thenAnswer(a -> Optional.of(new Nest(a.getArgument(0), "FOUND_NEST", new ArrayList<>())));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/nest/701")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(701)))
                .andExpect(jsonPath("$.name", is("FOUND_NEST")));
    }

    @Test
    void getNestTestNotFoundTest() throws Exception {
        when(birdService.findNestById(eq(10L)))
                .thenAnswer(a -> Optional.of(new Nest(a.getArgument(0), "FOUND_NEST", new ArrayList<>())));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/nest/45")
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void getNestTestBadRequestTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/nest/-45")
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void createNestTest() throws Exception {
        when(birdService.createNest(anyString()))
                .thenAnswer(a -> new Nest(666L, a.getArgument(0), new ArrayList<>()));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/nest")
                        .param("name", "TEST")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(666)))
                .andExpect(jsonPath("$.name", is("TEST")));
    }

    @Test
    void listBirdsOkTest() throws Exception {
        Nest mockNest = new Nest(34L, "MOCK_NEST", new ArrayList<>());
        when(birdService.findBirdsByNest(eq(34L)))
                .thenAnswer(a -> Arrays.asList(
                        new Bird(65L, "BIRD_1", mockNest),
                        new Bird(69L, "BIRD_2", mockNest))
                );

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/nest/34/birds")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(65)))
                .andExpect(jsonPath("$[0].name", is("BIRD_1")))
                .andExpect(jsonPath("$[1].id", is(69)))
                .andExpect(jsonPath("$[1].name", is("BIRD_2")));
    }

    @Test
    void listBirdsNotFoundTest() throws Exception {
        when(birdService.findBirdsByNest(eq(23L)))
                .thenThrow(new NestNotFoundException("Nest not found"));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/nest/23/birds")
                )
                .andExpect(status().isNotFound());
    }
}
