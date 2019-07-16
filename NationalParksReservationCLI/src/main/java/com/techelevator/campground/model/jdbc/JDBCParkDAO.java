package com.techelevator.campground.model.jdbc;

import java.util.ArrayList;


import java.util.List;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.Park;
import com.techelevator.campground.model.ParkDAO;

public class JDBCParkDAO implements ParkDAO{
	
	private JdbcTemplate jdbcTemplate;

	public JDBCParkDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Park> getAllParks() {
		List<Park> displayParks = new ArrayList<Park>();
		
		String sql = "SELECT park_id, name, location, establish_date, area, visitors, description FROM park ORDER BY name";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		
		Park availableParks;
		while (results.next()) {
			availableParks = mapRowToPark(results);
			displayParks.add(availableParks);
		}
		
		return displayParks;
	}
	
	private Park mapRowToPark(SqlRowSet rows) {
		Park p = new Park();
		p.setParkId(rows.getInt("park_id"));
		p.setParkName(rows.getString("name"));
		p.setLocation(rows.getString("location"));
		p.setEstablishDate(rows.getDate("establish_date").toLocalDate());
		p.setArea(rows.getInt("area"));
		p.setVisitors(rows.getInt("visitors"));
		String str = rows.getString("description");
		String updatedString = str.replaceAll("(.{75})", "$1\n");
		p.setDescription(updatedString);
		
		return p;
	}
}
