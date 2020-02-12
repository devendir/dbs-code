package com.consumer.service.daoImpl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.consumer.service.entity.ProductEntity;

@Repository
public class ConsumerDaoImpl implements ConsumerDao{
	
	@Autowired
	protected RestTemplate restTemplate;
	
	protected String serviceUrl;
	
	public ConsumerDaoImpl(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl: "http://" + serviceUrl;
	}
	

	public ConsumerDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public List<ProductEntity> getAllProducts() {
		ProductEntity[] productEntityListArr = restTemplate.getForObject(serviceUrl+"/products",ProductEntity[].class);
		List<ProductEntity> productEntityList = Arrays.asList(productEntityListArr);
		System.out.println("productEntityList.size() = "+productEntityList.size());
		return productEntityList;
	}

	
	@Override
	public ProductEntity getProductById(Long productId) {
		ProductEntity productEntity = restTemplate.getForObject(serviceUrl+"/products/"+productId, ProductEntity.class);
		System.out.println("productEntity.getProductName() = "+productEntity.getProductName());
		return productEntity;
	}

}
