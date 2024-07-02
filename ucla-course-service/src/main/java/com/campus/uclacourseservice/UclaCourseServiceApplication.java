package com.campus.uclacourseservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UclaCourseServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UclaCourseServiceApplication.class, args);
    }

}
