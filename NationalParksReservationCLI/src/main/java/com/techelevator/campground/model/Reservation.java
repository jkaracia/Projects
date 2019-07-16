package com.techelevator.campground.model;

import java.time.LocalDate;

public class Reservation {
	
	private int reservationId;
	private int siteId;
	private String reservationName;
	private LocalDate reservationFromDate;
	private LocalDate reservationToDate;
	private LocalDate reservationCreateDate;
	private String campgroundName;
	private String parkName;
	private int parkId;
	
	
	public int getReservationId() {
		return reservationId;
	}
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public String getReservationName() {
		return reservationName;
	}
	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}
	public LocalDate getReservationFromDate() {
		return reservationFromDate;
	}
	public void setReservationFromDate(LocalDate reservationFromDate) {
		this.reservationFromDate = reservationFromDate;
	}
	public LocalDate getReservationToDate() {
		return reservationToDate;
	}
	public void setReservationToDate(LocalDate reservationToDate) {
		this.reservationToDate = reservationToDate;
	}
	public LocalDate getReservationCreateDate() {
		return reservationCreateDate;
	}
	public void setReservationCreateDate(LocalDate reservationCreateDate) {
		this.reservationCreateDate = reservationCreateDate;
	}
	public String getCampgroundName() {
		return campgroundName;
	}
	public void setCampgroundName(String campgroundName) {
		this.campgroundName = campgroundName;
	}
	public String getParkName() {
		return parkName;
	}
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
	public int getParkId() {
		return parkId;
	}
	public void setParkId(int parkId) {
		this.parkId = parkId;
	}

}
