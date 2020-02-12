package com.producer.service;

import java.net.URI;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.producer.service.daoImpl.ProductDao;

@Configuration
@EnableAutoConfiguration
@ComponentScan( basePackages = { "com.producer.service"
		, "org.springframework.jms"
		, "org.springframework.jms.core"
		, "javax.jms"
		, "com.fasterxml.jackson"} )
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class ProducerServiceApplicationTests {

	@LocalServerPort
    int randomServerPort;
	
	@Autowired
	private ProductDao ProductDao;
	
	@Test
	public void getProductsList() throws Exception {
		
		RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:"+randomServerPort+"/products/";
        URI uri = new URI(baseUrl);
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals(true, result.getBody().contains("List"));
	      
	}

}
