package com.techelevator;

public class Chips extends Product {

	public Chips(String productName, String productPrice, String productCategory) {
		super(productName, productPrice, productCategory);
	}

	private String vendSound = "Crunch Crunch, Yum!";

	public String getVendSound() {
		return vendSound;
	}

}
