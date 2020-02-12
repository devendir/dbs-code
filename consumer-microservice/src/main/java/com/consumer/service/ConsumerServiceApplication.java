package com.consumer.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import com.consumer.service.daoImpl.ConsumerDao;
import com.consumer.service.daoImpl.ConsumerDaoImpl;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = { "com.consumer.service", "org.springframework.jms", " org.springframework.jms.core" })
public class ConsumerServiceApplication {

	public static final String ACCOUNTS_SERVICE_URL = "http://product-microservice";

	public static void main(String[] args) {
		SpringApplication.run(ConsumerServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public ConsumerDao consumerDao() {
		return new ConsumerDaoImpl(ACCOUNTS_SERVICE_URL);
	}

}
