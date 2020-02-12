package com.consumer.service.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class ProductEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//primary key auto generated
	@Id
	@Column (name = "product_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	
	private String productName;
	private String productPhoto;
	private Double productPrice;
	
	public ProductEntity() {
		super();
	}

	public ProductEntity(Long productId, String productName, String productPhoto, Double productPrice) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPhoto = productPhoto;
		this.productPrice = productPrice;
	}
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductPhoto() {
		return productPhoto;
	}
	public void setProductPhoto(String productPhoto) {
		this.productPhoto = productPhoto;
	}
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	@Override
	public String toString() {
		return "ProductEntity2 [productId=" + productId + ", productName=" + productName + ", productPhoto="
				+ productPhoto + ", productPrice=" + productPrice + "]";
	}

	
	
	
	
	
	
	

}
