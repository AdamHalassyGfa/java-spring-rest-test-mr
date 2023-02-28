package com.example.restnesttest.controllers;

import com.example.restnesttest.data.entities.Nest;
import com.example.restnesttest.services.BirdService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
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
    void listBirdsOkTest() throws  Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/nest/34/birds")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(65)))
                .andExpect(jsonPath("$[0].name", is("TEST_1")))
                .andExpect(jsonPath("$[1].id", is(66)))
                .andExpect(jsonPath("$[1].name", is("TEST_2")));
    }
}
