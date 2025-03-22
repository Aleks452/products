package com.products.products.Entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Products")
public class ProductEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "product_id")
	private Long productId;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "product_category")
	private String productCategory;

	@Column(name = "is_available")
	private boolean isAvailable;

	@Column(name = "product_price")
	private double productPrice;


	public ProductEntity(Long productId, String productName, String productCategory, boolean isAvailable,
			double productPrice) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productCategory = productCategory;
		this.isAvailable = isAvailable;
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

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
}
