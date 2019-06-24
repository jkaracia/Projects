package com.techelevator;

public class Gum extends Product {

	public Gum(String productName, String productPrice, String productCategory) {
		super(productName, productPrice, productCategory);
	}

	private String vendSound = "Chew Chew, Yum!";

	public String getVendSound() {
		return vendSound;
	}

}
