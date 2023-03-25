package com.example.exceptionhandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class SpringbootExceptionHandlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootExceptionHandlerApplication.class, args);
    }

}
