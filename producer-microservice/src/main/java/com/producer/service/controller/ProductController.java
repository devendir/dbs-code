package com.producer.service.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jms.Queue;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.producer.service.entity.ItemEntity;
import com.producer.service.entity.ProductEntity;
import com.producer.service.serviceImpl.ProductService;

@RestController
@ComponentScan(basePackages = { "com.producer.service", "org.springframework.jms", " org.springframework.jms.core" })
public class ProductController {

	protected Logger logger = Logger.getLogger(ProductController.class.getName());

	@Autowired
	private ProductService productService;

	@Autowired
	private Queue queue;

	@Autowired
	private JmsTemplate jmsTemplate;

	@RequestMapping(value = "/products")
	public List<ProductEntity> allProducts() {
		logger.info("producer-microservice allProducts() invoked");
		List<ProductEntity> products = productService.getAllProductEntities();
		logger.info("producer-microservice allProducts() found: " + products.size());
		return products;
	}

	@RequestMapping(value = "/products/{productId}")
	public ProductEntity productById(@PathVariable("productId") Long productId) {
		logger.info("producer-microservice productById() invoked");
		ProductEntity productEntity = productService.getProductById(productId);
		logger.info("producer-microservice productById() found: " + productEntity);
		System.out.println("### START pushing message Message [" + productId + "] ###");
		pushMessageIntoQueueByActiveMQ(queue, productEntity);

		return productEntity;
	}

	private void pushMessageIntoQueueByActiveMQ(Queue queue, ProductEntity productEntity) {

		ObjectMapper Obj = new ObjectMapper();
		try {
			String jsonStr = Obj.writeValueAsString(productEntity);

			System.out.println(jsonStr);
			jmsTemplate.convertAndSend(queue, jsonStr);
			System.out.println("### Message Successfully Pushed into Queue ###");
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void pushMessageIntoQueueByJms(String QueueName, ProductEntity productEntity) {
		jmsTemplate.convertAndSend("OrderQueue", productEntity);
		System.out.println("### Message Successfully Pushed into Queue ###");
	}

	private int ifProductExists(Long id, List<ItemEntity> itemEntityList) {
		for (int i = 0; i < itemEntityList.size(); i++) {
			if (itemEntityList.get(i).getProduct().getProductId().equals(id)) {
				return i;
			}
		}
		return -1;
	}

	@RequestMapping(value = "/products1/{productId}", method = RequestMethod.GET)
	public String productById(@PathVariable("productId") Long productId, HttpSession session) {
		List<ItemEntity> itemEntityList = new ArrayList<ItemEntity>();

		if (session.getAttribute("item") == null) {
			itemEntityList = new ArrayList<ItemEntity>();

			ProductEntity productEntity = productService.getProductById(productId);
			ItemEntity itemEntity = new ItemEntity(productEntity, 1);
			itemEntityList.add(itemEntity);

			session.setAttribute("item", itemEntityList);
		} else {
			itemEntityList = (List<ItemEntity>) session.getAttribute("item");

			int index = this.ifProductExists(productId, itemEntityList);
			if (index == -1) {
				ProductEntity productEntity = productService.getProductById(productId);
				ItemEntity itemEntity = new ItemEntity(productEntity, 1);
				itemEntityList.add(itemEntity);
			} else {
				int quantity = itemEntityList.get(index).getProductQuantity() + 1;
				itemEntityList.get(index).setProductQuantity(quantity);
			}
			session.setAttribute("item", itemEntityList);
		}
		return "redirect:/item/Selection";
	}

	@RequestMapping(value = "/products2/{productId}")
	public ProductEntity productById1(@PathVariable("productId") Long productId) {
		logger.info("producer-microservice productById() invoked");
		ProductEntity productEntity = productService.getProductById(productId);
		logger.info("producer-microservice productById() found: " + productEntity);

		return productEntity;
	}

}
