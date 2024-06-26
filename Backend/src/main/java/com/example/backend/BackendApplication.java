package com.example.backend;

import com.example.backend.data.SimulatorDataStorage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
        SimulatorDataStorage.initialize();
    }

}
