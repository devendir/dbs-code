package com.consumer.service.daoImpl;

import java.util.List;

import com.consumer.service.entity.ProductEntity;

public interface ConsumerDao {
	
	List<ProductEntity> getAllProducts();
	
	ProductEntity getProductById(Long productId);
}
