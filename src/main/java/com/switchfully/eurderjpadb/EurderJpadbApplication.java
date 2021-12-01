package com.switchfully.eurderjpadb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.switchfully.eurderjpadb"})
public class EurderJpadbApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurderJpadbApplication.class, args);
    }

}
