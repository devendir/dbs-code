package com.consumer.service.entity;

import java.io.Serializable;

public class ItemEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private ProductEntity product;
	private Integer productQuantity;
	
	public ItemEntity() {
		super();
	}
	
	public ItemEntity(ProductEntity product, Integer productQuantity) {
		super();
		this.product = product;
		this.productQuantity = productQuantity;
	}




	public ProductEntity getProduct() {
		return product;
	}
	public void setProduct(ProductEntity product) {
		this.product = product;
	}
	public Integer getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}

	@Override
	public String toString() {
		return "ItemEntity [product=" + product + ", productQuantity=" + productQuantity + "]";
	}
	
	

}
