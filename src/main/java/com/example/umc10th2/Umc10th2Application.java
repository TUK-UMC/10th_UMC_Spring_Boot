package com.example.umc10th2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Umc10th2Application {

    public static void main(String[] args) {
        SpringApplication.run(Umc10th2Application.class, args);
    }
}
