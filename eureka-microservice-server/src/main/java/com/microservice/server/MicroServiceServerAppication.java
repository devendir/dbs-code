package com.microservice.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MicroServiceServerAppication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceServerAppication.class, args);
	}

}
