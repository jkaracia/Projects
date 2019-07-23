package com.techelevator.npgeek;

public class Weather {
	
	private String parkCode;
	private int day;
	private int lowTemp;
	private int highTemp;
	private String forecast;
	private String picForecast;
	private String weatherAdvisory;
	private String tempAdvisory;
	private String celsiusAdvisory;
	
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getLowTemp() {
		return lowTemp;
	}
	public void setLowTemp(int lowTemp) {
		this.lowTemp = lowTemp;
	}
	public int getHighTemp() {
		return highTemp;
	}
	public void setHighTemp(int highTemp) {
		this.highTemp = highTemp;
	}
	public String getForecast() {
		return forecast;
	}
	public void setForecast(String forecast) {
		this.forecast = forecast;
	}
	public String getPicForecast() {
	
		if (forecast.contains("partly cloudy")) {
			picForecast = "partlyCloudy";
		}
		return picForecast;
	}
	public void setPicForecast(String picForecast) {
		this.picForecast = picForecast;
	}
	public String getWeatherAdvisory() {
		if (forecast.contains("snow")) {
			weatherAdvisory = "Pack snow shoes.";	
		}
		else if (forecast.contains("rain")) {
			weatherAdvisory = "Pack rain gear and wear waterproof shoes.";
		}
		else if (forecast.contains("thunderstorms")) {
			weatherAdvisory = "Seek shelter and avoid hiking on exposed ridges.";
		}
		else if (forecast.contains("sunny")) {
			weatherAdvisory = "Pack sunblock.";
		}
		else {
			weatherAdvisory = "";
		}
		return weatherAdvisory;
	}
	
	public String getTempAdvisory() {
		if (highTemp > 75) {
			tempAdvisory = "Bring an extra gallon of water.";
		}
		if (highTemp - lowTemp > 20) {
			tempAdvisory = "Wear breathable layers.";
		}
		if (lowTemp < 20 || highTemp < 20) {
			tempAdvisory = "Exposure to frigid temperatures is dangerous.";
		}
		else {
			tempAdvisory = "";
		}
		
		return tempAdvisory;
	}
	
	public String getCelsiusAdvisory() {
		if (highTemp > 24) {
			tempAdvisory = "Bring an extra gallon of water.";
		}
		if (highTemp - lowTemp > 11) {
			tempAdvisory = "Wear breathable layers.";
		}
		if (lowTemp < -6 || highTemp < -6) {
			tempAdvisory = "Exposure to frigid temperatures is dangerous.";
		}
		else {
			tempAdvisory = "";
		}
		
		return tempAdvisory;
	}
	
	public void setWeatherAdvisory(String weatherAdvisory) {
		this.weatherAdvisory = weatherAdvisory;
	}
	public void setTempAdvisory(String tempAdvisory) {
		this.tempAdvisory = tempAdvisory;
	}
	public void setCelsiusAdvisory(String tempAdvisory) {
		this.tempAdvisory = tempAdvisory;
	}
	
	
}
