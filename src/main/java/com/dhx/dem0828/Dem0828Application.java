package com.dhx.dem0828;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class Dem0828Application {

    public static void main(String[] args) {
        SpringApplication.run(Dem0828Application.class, args);
    }

}
