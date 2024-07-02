package com.campus.uclauserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UclaUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UclaUserServiceApplication.class, args);
    }

}
