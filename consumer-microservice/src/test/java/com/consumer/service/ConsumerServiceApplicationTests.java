package com.consumer.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.consumer.service.entity.ProductEntity;

/*@Configuration
@EnableAutoConfiguration
@ComponentScan( basePackages = { "com.consumer.service"
		, "org.springframework.jms"
		, "org.springframework.jms.core"
		, "javax.jms"
		, "com.fasterxml.jackson"
		, "org.springframework.data"
		, "org.springframework.stereotype"} )*/
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
//@ContextConfiguration(classes=ConsumerServiceApplication.class)
//@WebMvcTest(ConsumerController.class)
public class ConsumerServiceApplicationTests {

	@Autowired
    private MockMvc mockMvc;
	/*
	 * @Test public void shouldReturnDefaultMessage() throws Exception {
	 * this.mockMvc.perform(get("/consume/productList")).andDo(print()).andExpect(
	 * status().isOk())
	 * .andExpect(content().string(containsString("product/ListInventory"))); }
	 */

	@Test
	   public void getProductsListViaConsumer() throws Exception {
	      String uri = "/product";

	      /*
		 * this.mockMvc.perform(get("/product")) .andExpect(status().isOk())//;
		 * .andExpect(content().string("/product/ListInventory"));
		 */
	      
	      MvcResult mvcResult = this.mockMvc.perform(get("/consume/productList")).andReturn();
	      
	      String productResObject = mvcResult.getResponse().getContentAsString();
	      
	      String viewNameFromResponse = mvcResult.getModelAndView().getViewName();
	        
	      System.out.println("product result Object = "+productResObject);
	        
	      System.out.println("product result View Name = "+viewNameFromResponse);
	       
	      
	      //compare API using mock
	      assertEquals("product/ListInventory", viewNameFromResponse);
	      
		/*
		 * int status = mvcResult.getResponse().getStatus();
		 * System.out.println("status = "+status); assertEquals(200, status); String
		 * content = mvcResult.getResponse().getContentAsString();
		 */
	     
	   }

}
