package com.github.cylleon.carmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
public class CarMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarMicroserviceApplication.class, args);
	}

}
