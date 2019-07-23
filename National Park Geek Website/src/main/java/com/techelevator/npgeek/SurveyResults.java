package com.techelevator.npgeek;

public class SurveyResults {
	
	private String parkCode;
	private String parkCodeLower;
	private String parkName;
	private int surveySum;
	
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public String getParkCodeLower() {
		parkCodeLower = parkCodeLower.toLowerCase();
		return parkCodeLower;
	}
	public void setParkCodeLower(String parkCodeLower) {
		this.parkCodeLower = parkCodeLower;
	}
	public String getParkName() {
		return parkName;
	}
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
	public int getSurveySum() {
		return surveySum;
	}
	public void setSurveySum(int surveySum) {
		this.surveySum = surveySum;
	}

}
