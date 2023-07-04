package com.example.wix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {

        System.out.println("app is running");
        SpringApplication.run(MainApplication.class, args);
    }

}
