package com.msg.server.model;

public class Product {
	
	private String productType;
	private Double unitPrice;
	
	public Product() {
		
	}
	
	public Product(String type, Double price) {
		this.productType = type;
		this.unitPrice = price;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}
}
