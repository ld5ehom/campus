package com.campus.ucladiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class UclaDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(UclaDiscoveryApplication.class, args);
	}

}
