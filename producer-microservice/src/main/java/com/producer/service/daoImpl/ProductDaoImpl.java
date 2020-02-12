package com.producer.service.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.producer.service.entity.ProductEntity;

/*
 * This is STUB DAO
 */
@Repository
public class ProductDaoImpl implements ProductDao{
	
	private List<ProductEntity> productList;
	
	public ProductDaoImpl() {
		productList = new ArrayList<ProductEntity>();

		//public ProductEntity(String productId, String productName, String productPhoto, Double productPrice) {
		ProductEntity iphone = new ProductEntity(1L,"Apple IPhone 10 plus","iphone_8_plus.png", 1590D);
		ProductEntity samsung = new ProductEntity(2L,"Samsung Note 10","samsung_note_10.jpg", 1300D);
		ProductEntity huawei = new ProductEntity(3L,"Huawei Pro","huawei.jpg", 1200D);
		
		productList.add(iphone);
		productList.add(samsung);
		productList.add(huawei);
		
	}

	@Override
	public List<ProductEntity> getAllProductEntities() {
		return productList;
	}

	@Override
	public ProductEntity getProductById(Long productId) {
		for(ProductEntity productEntity: this.productList) {
			if(productEntity.getProductId().equals(productId)) {
				return productEntity;
			}
		}
		return null;
	}

}
