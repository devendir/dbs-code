package com.consumer.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consumer.service.daoImpl.ConsumerDao;
import com.consumer.service.entity.ProductEntity;

@Service
public class ConsumerServiceImpl implements ConsumerService {

	@Autowired
	private ConsumerDao consumerDao;
	
	
	@Override
	public List<ProductEntity> getAllProducts() {
		return consumerDao.getAllProducts();
	}

	@Override
	public ProductEntity getProductById(Long productId) {
		return consumerDao.getProductById(productId);
	}

}
