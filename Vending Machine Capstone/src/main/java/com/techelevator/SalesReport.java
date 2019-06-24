package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SalesReport {

	PrintWriter salesReportWriter;

	public SalesReport() {
		File salesReport = new File("SalesReport.txt");
		try {
			salesReportWriter = new PrintWriter(salesReport);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void addSalesLine(String productName, int quantitySold) {

		String printProductName = productName; // name of each item in vending machine
		int printQuantitySold = quantitySold; // quantity of item sold during program life
		String printPrettyLine = String.format("%20s%3s%d%5s", printProductName, " | ", printQuantitySold, " SOLD");
		// format the item line to line up appearance
		salesReportWriter.println(printPrettyLine);
	}

	public void addTotalLine(double totalSales) {
		double printTotalSales = totalSales; // total sales made for the during program life
		String prettyTotalSales = String.format("%.2f", printTotalSales); // format the total sales line
		salesReportWriter.println();
		salesReportWriter.println();
		salesReportWriter.println("***TOTAL SALES*** " + String.format("%-10s", "$" + prettyTotalSales));
	}

	public void closeSalesReport() {
		salesReportWriter.close();

	}

}
