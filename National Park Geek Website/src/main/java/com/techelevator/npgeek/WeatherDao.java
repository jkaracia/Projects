package com.techelevator.npgeek;

import java.util.List;

public interface WeatherDao {
	
	public List<Weather> get5DayForecast(String parkCode);
	
}
