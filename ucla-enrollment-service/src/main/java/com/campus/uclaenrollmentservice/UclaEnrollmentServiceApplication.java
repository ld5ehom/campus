package com.campus.uclaenrollmentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UclaEnrollmentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UclaEnrollmentServiceApplication.class, args);
    }

}
