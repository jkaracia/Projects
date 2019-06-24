package com.techelevator;

public class Drink extends Product {

	public Drink(String productName, String productPrice, String productCategory) {
		super(productName, productPrice, productCategory);
	}

	private String vendSound = "Glug Glug, Yum!";

	public String getVendSound() {
		return vendSound;
	}

}
