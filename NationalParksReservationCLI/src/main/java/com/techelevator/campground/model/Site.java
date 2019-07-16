package com.techelevator.campground.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Site {
	
	private int siteId;
	private int campgroundId;
	private String campgroundName;
	private int siteNumber;
	private int maxOccupancy;
	private boolean isAccessible;
	private int maxRVLength;
	private boolean hasUtilities;
	private int campgroundId2;
	private int parkId;
	private String name;
	private int openFrom;
	private int openTo;
	private BigDecimal dailyFee;
	
	
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public int getCampgroundId() {
		return campgroundId;
	}
	public void setCampgroundId(int campgroundId) {
		this.campgroundId = campgroundId;
	}
	
	public String getCampgroundName() {
		return campgroundName;
	}
	
	public void setCampgroundName(String campgroundName) {
		this.campgroundName = campgroundName;
	}
	
	public int getSiteNumber() {
		return siteNumber;
	}
	public void setSiteNumber(int siteNumber) {
		this.siteNumber = siteNumber;
	}
	public int getMaxOccupancy() {
		return maxOccupancy;
	}
	public void setMaxOccupancy(int maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}
	public boolean isAccessible() {
		return isAccessible;
	}
	public void setAccessible(boolean accessible) {
		this.isAccessible = accessible;
	}
	public int getMaxRVLength() {
		return maxRVLength;
	}
	public void setMaxRVLength(int maxRVLength) {
		this.maxRVLength = maxRVLength;
	}
	public boolean isHasUtilities() {
		return hasUtilities;
	}
	public void setHasUtilities(boolean utilities) {
		this.hasUtilities = hasUtilities;
	}
	public int getCampgroundId2() {
		return campgroundId2;
	}
	public void setCampgroundId2(int campgroundId) {
		this.campgroundId2 = campgroundId;
	}
	public int getParkId() {
		return parkId;
	}
	public void setParkId(int parkId) {
		this.parkId = parkId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOpenFrom() {
		return openFrom;
	}
	public void setOpenFrom(int openFrom) {
		this.openFrom = openFrom;
	}
	public int getOpenTo() {
		return openTo;
	}
	public void setOpenTo(int openTo) {
		this.openTo = openTo;
	}
	public BigDecimal getDailyFee() {
		return dailyFee;
	}
	public void setDailyFee(BigDecimal dailyFee) {
		this.dailyFee = dailyFee;
	}
	public String toString() {
		return "this is a site";
	}

}
