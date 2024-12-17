package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ws.config.annotation.EnableWs;

@EnableWs
@SpringBootApplication
public class SoapWsDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoapWsDemoApplication.class, args);
    }
}