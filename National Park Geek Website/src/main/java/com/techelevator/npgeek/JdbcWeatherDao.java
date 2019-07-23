package com.techelevator.npgeek;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcWeatherDao implements WeatherDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcWeatherDao(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	@Override
	public List<Weather> get5DayForecast(String parkCode) {
		List<Weather> parkWeather = new ArrayList<>();
		String sqlWeatherByPark = "SELECT * FROM weather WHERE parkcode = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlWeatherByPark, parkCode);
		while(results.next()) {
			parkWeather.add(mapRowToWeather(results));
		}
		return parkWeather;
	}
	
	private Weather mapRowToWeather(SqlRowSet row) {
		Weather weather = new Weather();
		weather.setParkCode(row.getString("parkcode"));
		weather.setDay(row.getInt("fivedayforecastvalue"));
		weather.setForecast(row.getString("forecast"));
		weather.setWeatherAdvisory(row.getString("forecast"));
		weather.setTempAdvisory(row.getString("forecast"));
		weather.setCelsiusAdvisory(row.getString("forecast"));
		weather.setPicForecast(row.getString("forecast"));
		weather.setHighTemp(row.getInt("high"));
		weather.setLowTemp(row.getInt("low"));
		return weather;
	}
	
}
