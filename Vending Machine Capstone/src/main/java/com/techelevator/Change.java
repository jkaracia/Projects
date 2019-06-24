package com.techelevator;

public class Change {

	private int changeBalance;
	private int quarter;
	private int dime;
	private int nickel;

	public int getChangeBalance() {
		return changeBalance;
	}

	public int getQuarter() {
		return quarter;
	}

	public int getDime() {
		return dime;
	}

	public int getNickel() {
		return nickel;
	}

	public Change(int balance) {

		changeBalance = balance;

		quarter = (int) changeBalance / 25;
		changeBalance = changeBalance - (quarter * 25);

		dime = (int) changeBalance / 10;
		changeBalance = changeBalance - (dime * 10);

		nickel = (int) changeBalance / 5;
		changeBalance = changeBalance - (nickel * 5);
	}
}
