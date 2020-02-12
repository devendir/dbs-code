package com.producer.service.daoImpl;

import java.util.List;

import com.producer.service.entity.ProductEntity;

public interface ProductDao {
	
	List<ProductEntity> getAllProductEntities();
	
	ProductEntity getProductById(Long productId);
}
