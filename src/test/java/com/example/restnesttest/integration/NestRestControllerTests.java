package com.example.restnesttest.integration;

import com.example.restnesttest.RestnesttestApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
        classes = {RestnesttestApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class NestRestControllerTests {

    private final HttpHeaders headers = new HttpHeaders();
    private final TestRestTemplate restTemplate = new TestRestTemplate();
    @LocalServerPort
    private int port;

    @Test
    void listBirdsNotFoundTest() throws Exception {
        HttpEntity<String> httpEntity = new HttpEntity<>(
                null,
                headers
        );

        ResponseEntity<String> response = restTemplate.exchange(
                String.format("http://localhost:%s/api/nest/19880523/birds", port),
                HttpMethod.GET,
                httpEntity,
                String.class
        );

        assertEquals(404, response.getStatusCode().value());
    }
}
