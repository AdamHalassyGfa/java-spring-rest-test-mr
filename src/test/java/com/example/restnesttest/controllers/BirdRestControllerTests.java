package com.example.restnesttest.controllers;


import com.example.restnesttest.data.entities.Bird;
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

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(BirdController.class)
public class BirdRestControllerTests {

    @MockBean
    private BirdService birdService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createBirdTest() throws Exception {
        when(birdService.createBird(eq(89l), anyString()))
                .thenAnswer(a -> new Bird(78l, a.getArgument(1), new Nest(a.getArgument(0), "TEST_NEST", new ArrayList<>())));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/bird")
                        .param("name", "TEST_BIRD")
                        .param("nestID",  "89")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(78)))
                .andExpect(jsonPath("$.name", is("TEST_BIRD")));

    }
}
