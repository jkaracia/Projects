package com.techelevator.campground.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.text.DateFormatSymbols;


public class Campground {
	
	private int campgroundId;
	private int parkId;
	private String campgroundName;
	private int openFromMonth;
	private int openToMonth;
	private BigDecimal dailyFee;
	private static String[] monthNames = new DateFormatSymbols().getMonths();

	public boolean isCampgroundOpen(LocalDate openFrom, LocalDate openTo) {
		return ((isYearRound() || openFrom.getYear() == openTo.getYear() &&
				openFrom.getMonthValue() >= openFromMonth && openTo.getMonthValue() == openToMonth));
	}
	
	private boolean isYearRound() {
		return (openFromMonth == 1 && openToMonth == 12);
	}
	
	public int getCampgroundId() {
		return campgroundId;
	}
	public void setCampgroundId(int campgroundId) {
		this.campgroundId = campgroundId;
	}
	public int getParkId() {
		return parkId;
	}
	public void setParkId(int parkId) {
		this.parkId = parkId;
	}
	public String getCampgroundName() {
		return campgroundName;
	}
	public void setCampgroundName(String campgroundName) {
		this.campgroundName = campgroundName;
	}
	public String getOpenFromMonth() {
		return monthNames [openFromMonth -1];
	}
	public void setOpenFromMonth(int openFromMonth) {
		this.openFromMonth = openFromMonth;
	}
	public String getOpenToMonth() {
		return monthNames [openToMonth -1];
	}
	public void setOpenToMonth(int openToMonth) {
		this.openToMonth = openToMonth;
	}
	public BigDecimal getDailyFee() {
		return dailyFee;
	}
	public void setDailyFee(BigDecimal dailyFee) {
		this.dailyFee = dailyFee;
	}
	

}
