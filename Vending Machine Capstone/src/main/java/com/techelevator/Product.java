package com.techelevator;

public abstract class Product {

	private String productName;
	private double productPrice = 0;
	private String productCategory;
	private int stock = 5;

	public Product(String productName, String productPrice, String productCategory) {
		this.productName = productName;
		this.productPrice = Double.parseDouble(productPrice);
		this.productCategory = productCategory;
	}

	public String getProductName() {
		return productName;
	}

	public int getProductPrice() {
		return (int) (productPrice * 100); // converting productPrice to pennies- integer
	}

	public String getProductCategory() {
		return productCategory;
	}

	public int getStock() {
		return stock;
	}

	public void purchaseItem() {
		if (stock > 0) {
			stock--;
		}
	}

	public abstract String getVendSound();

}
