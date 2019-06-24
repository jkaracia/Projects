package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AuditFileLog {

	PrintWriter auditReportWriter;

	public AuditFileLog() {

		File auditReport = new File("AuditReport.txt");
		try {
			auditReportWriter = new PrintWriter(auditReport);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void addAuditLine(String transactionType, int amount, int balance) {
		LocalDate today = LocalDate.now();
		DateTimeFormatter prettyDate = DateTimeFormatter.ofPattern("MM/dd/yyyy");

		LocalTime now = LocalTime.now();
		DateTimeFormatter prettyTime = DateTimeFormatter.ofPattern("hh:mm:ss a");

		String printTransactionType = transactionType.toString();
		double printAmount = (double) amount / 100;
		String prettyAmount = String.format("%.2f", (float) printAmount);
		double printBalance = (double) balance / 100;
		String prettyBalance = String.format("%.2f", (float) printBalance);

		auditReportWriter.println(today.format(prettyDate) + " " + now.format(prettyTime) + " "
				+ String.format("%-30s", printTransactionType) + String.format("%-10s", "$" + prettyAmount)
				+ String.format("%-10s", "$" + prettyBalance));

	}

	public void closeAuditFile() {
		auditReportWriter.close();

	}

}
