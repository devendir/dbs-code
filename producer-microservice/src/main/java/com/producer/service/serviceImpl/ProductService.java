package com.producer.service.serviceImpl;

import java.util.List;

import com.producer.service.entity.ProductEntity;

public interface ProductService {
	
	List<ProductEntity> getAllProductEntities();
	
	ProductEntity getProductById(Long productId);
}