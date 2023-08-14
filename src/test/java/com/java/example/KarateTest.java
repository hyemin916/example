package com.java.example;

import com.intuit.karate.junit5.Karate;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KarateTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        System.setProperty("test.server.port", String.valueOf(port));
    }

    @Karate.Test
    Karate test() {
        return Karate.run("classpath:com/java/example/demo.feature");
    }
}