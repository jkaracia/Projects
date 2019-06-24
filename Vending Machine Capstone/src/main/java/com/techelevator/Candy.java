package com.techelevator;

public class Candy extends Product {
	
	public Candy(String productName, String productPrice, String productCategory) {
		super(productName, productPrice, productCategory);
	}

	private String vendSound = "Munch Munch, Yum!";
	
	public String getVendSound() {
		return vendSound;
	}	

}
