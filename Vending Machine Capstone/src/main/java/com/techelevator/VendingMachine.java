package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {

	private Map<String, Product> inventoryList = new LinkedHashMap<String, Product>();
	private int balance = 0;
	Map<String, Integer> purchaseList = new LinkedHashMap<String, Integer>();
	AuditFileLog fileLog = new AuditFileLog();

	public VendingMachine() {

		File inventoryFile = new File("VendingMachine.txt");
		try (Scanner inventoryFileScanner = new Scanner(inventoryFile)) {
			while (inventoryFileScanner.hasNextLine()) {
				String line = inventoryFileScanner.nextLine();
				String[] inventoryArray = line.split("\\|");
				// slot id = inventoryArray[0]
				// product name = inventoryArray[1]
				// product price = inventoryArray[2] //imported as String/ parse to double and
				// returned as (int) pennies(*100)
				// product category = inventoryArray[3]
				Product p;
				if (inventoryArray[3].equals("Chip")) {
					p = new Chips(inventoryArray[1], inventoryArray[2], inventoryArray[3]);
				} else if (inventoryArray[3].equals("Candy")) {
					p = new Candy(inventoryArray[1], inventoryArray[2], inventoryArray[3]);
				} else if (inventoryArray[3].equals("Drink")) {
					p = new Drink(inventoryArray[1], inventoryArray[2], inventoryArray[3]);
				} else {
					p = new Gum(inventoryArray[1], inventoryArray[2], inventoryArray[3]);
				}
				inventoryList.put(inventoryArray[0], p);
				purchaseList.put(inventoryArray[0], 0);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
	}

	public String displayInventory() {
		String displayAll = "";
		String stock = "";
		for (String key : inventoryList.keySet()) {
			stock = "";
			if (inventoryList.get(key).getStock() == 0) {
				stock += "SOLD OUT";
			} else {
				stock += inventoryList.get(key).getStock() + " left";
			}
			displayAll += String.format("%-3s %-20s $%-5.2f  | %-5s\n", key, inventoryList.get(key).getProductName(),
					(double) inventoryList.get(key).getProductPrice() / 100, stock);
			// format into columns
		}
		return displayAll;
	}

	// create Sales Report
	public void produceReport() {
		double totalSales = 0;
		SalesReport report = new SalesReport();
		System.out.println("SalesReport.txt Generated");
		for (String key : inventoryList.keySet()) {
			String name = inventoryList.get(key).getProductName();
			int quantity = purchaseList.get(key);
			if (quantity >= 1) {
				totalSales += inventoryList.get(key).getProductPrice() * quantity;

			}

			report.addSalesLine(name, quantity);
		}
		totalSales = (double) totalSales / 100;
		report.addTotalLine(totalSales);

		report.closeSalesReport();

	}

	public String feedMoney(int addMoney) {
		String transactionType = "FEED MONEY: ";
		int feedAmount = 0;

		if (addMoney == 1) {
			balance = balance + 100; // feeding 1.00 in pennies
			feedAmount = 100;
		} else if (addMoney == 5) {
			balance = balance + 500; // feeding 5.00 in pennies
			feedAmount = 500;
		} else if (addMoney == 10) {
			balance = balance + 1000; // feeding 10.00 in pennies
			feedAmount = 1000;
		}

		fileLog.addAuditLine(transactionType, feedAmount, balance);
		String balanceMessage = "";
		balanceMessage = String.format("%-16s%-10.2f", "Your balance is $", balance / (float) 100);
		return balanceMessage;
	}

	public String completeTransaction() {
		String transactionTypeC = "GIVE CHANGE: ";
		Change thisChange = new Change(balance);
		int changeReturned = balance;
		String coinsReturned = "";
		balance = balance - changeReturned;
		if (changeReturned > 0) {
			coinsReturned += ("Your change is " + thisChange.getQuarter() + " quarters and " + thisChange.getDime()
					+ " dimes and " + thisChange.getNickel() + " nickels. ");
		}

		fileLog.addAuditLine(transactionTypeC, changeReturned, balance);
		return coinsReturned;
	}

	public String purchase(String selection) {
		String message = "";

		if (selection.toUpperCase().startsWith("Q")) {
			message = "Returning to previous menu.";

		} else if (!inventoryList.containsKey(selection.toUpperCase())) {
			message = "Sorry, that product does not exist. Please choose another item.";

		} else if (inventoryList.get(selection.toUpperCase()).getStock() == 0) {

			message = "SOLD OUT";

		} else if (inventoryList.get(selection.toUpperCase()).getProductPrice() <= (balance)) {
			balance -= inventoryList.get(selection.toUpperCase()).getProductPrice();
			inventoryList.get(selection.toUpperCase()).purchaseItem();
			int count = purchaseList.containsKey(selection.toUpperCase()) ? purchaseList.get(selection.toUpperCase())
					: 0;
			purchaseList.put(selection.toUpperCase(), count + 1);
			String soldItem = "Sold: " + selection.toUpperCase() + " "
					+ inventoryList.get(selection.toUpperCase()).getProductName();
			fileLog.addAuditLine(soldItem, ((int) inventoryList.get(selection.toUpperCase()).getProductPrice()),
					balance);

			message = inventoryList.get(selection.toUpperCase()).getVendSound() + "\n";
			message += String.format("%-26s%-10.2f", "Your remaining balance is $", (float) balance / 100);

		} else if (inventoryList.get(selection.toUpperCase()).getProductPrice() > balance) {
			message = "Insufficient Funds - Cannot Complete Transaction";
		}
		return message;
	}

	public void closeAuditFile() {

		fileLog.closeAuditFile();

	}

}
