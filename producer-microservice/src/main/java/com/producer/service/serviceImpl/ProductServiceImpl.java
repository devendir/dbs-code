package com.producer.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.producer.service.daoImpl.ProductDao;
import com.producer.service.entity.ProductEntity;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;
	
	
	@Override
	public List<ProductEntity> getAllProductEntities() {
		return productDao.getAllProductEntities();
	}

	@Override
	public ProductEntity getProductById(Long productId) {
		return productDao.getProductById(productId);
	}

}
