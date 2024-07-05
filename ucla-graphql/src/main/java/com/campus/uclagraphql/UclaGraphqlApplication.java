package com.campus.uclagraphql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UclaGraphqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(UclaGraphqlApplication.class, args);
    }
}
