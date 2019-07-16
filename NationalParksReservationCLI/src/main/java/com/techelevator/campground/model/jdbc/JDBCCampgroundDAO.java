package com.techelevator.campground.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.Campground;
import com.techelevator.campground.model.CampgroundDAO;


public class JDBCCampgroundDAO implements CampgroundDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public JDBCCampgroundDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Campground> getAllCampgroundsByParkId(int parkId) {
		List<Campground> displayCampgrounds = new ArrayList<Campground>();
		
		String sql = "SELECT campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee FROM campground WHERE park_id = ? ORDER BY campground_id";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkId);
		
		while (results.next()) {
			Campground availableCampgrounds = mapRowToCampground(results);
			displayCampgrounds.add(availableCampgrounds);
		}
		
		return displayCampgrounds;
		
	}
	
	private Campground mapRowToCampground(SqlRowSet rows) {
		Campground c = new Campground();
		c.setCampgroundId(rows.getInt("campground_id"));
		c.setParkId(rows.getInt("park_id"));
		c.setCampgroundName(rows.getString("name"));
		c.setOpenFromMonth(rows.getInt("open_from_mm"));
		c.setOpenToMonth(rows.getInt("open_to_mm"));
		c.setDailyFee(rows.getBigDecimal("daily_fee"));
	
		return c;
	}

}
