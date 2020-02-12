package com.consumer.service.serviceImpl;

import java.util.List;

import com.consumer.service.entity.ProductEntity;

public interface ConsumerService {
	
	List<ProductEntity> getAllProducts();
	
	ProductEntity getProductById(Long productId);
}