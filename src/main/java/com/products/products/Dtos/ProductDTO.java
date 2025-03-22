package com.products.products.Dtos;

public class ProductDTO {

	private Long productId;
	private String productName;
	private String productCategory;
	private boolean isAvailable;
	private double productPrice;
	
	
	public ProductDTO(Long productId, String productName, String productCategory, boolean isAvailable,
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
