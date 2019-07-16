package com.techelevator.campground.model.jdbc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.Campground;
import com.techelevator.campground.model.Site;
import com.techelevator.campground.model.SiteDAO;
import com.techelevator.campground.view.Menu;

public class JDBCSiteDAO implements SiteDAO {

	private JdbcTemplate jdbcTemplate;
	

	public JDBCSiteDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Site> getSiteBasedOnDates(int campgroundId, LocalDate arrivalDate, LocalDate departureDate)
			throws IllegalArgumentException {
		
			List<Site> availableSites = new ArrayList<Site>();
					
				String sql = "SELECT distinct * FROM site " + 
						"JOIN campground on site.campground_id = campground.campground_id " + 
						"WHERE site.campground_id = ? " + 
						"AND site_id NOT IN " + 
						"(SELECT site.site_id from site " + 
						"JOIN reservation ON reservation.site_id = site.site_id " + 
						"WHERE(NOT (? > reservation.from_date and ? < reservation.to_date))) " + 
						"order by daily_fee " + 
						"LIMIT 5";
				
				Site theSite;
				SqlRowSet results = jdbcTemplate.queryForRowSet(sql, campgroundId, arrivalDate, departureDate);
				while (results.next()) {
					theSite = mapRowToSite(results);
					availableSites.add(theSite);
				}
				
				return availableSites;
					
	}
	@Override
	public List<Site> getAllSitesByPark(int parkId, LocalDate arrivalDate, LocalDate departureDate) {
		
		List<Site> displaySites = new ArrayList<Site>();
		
		
		String sqlParkSites = "SELECT distinct * FROM site " + 
				"JOIN campground ON site.campground_id = campground.campground_id " + 
				"JOIN park ON park.park_id = campground.park_id " + 
				"WHERE park.park_id = ? " + 
				"AND site_id NOT IN " + 
				"(SELECT site.site_id FROM site " + 
				"JOIN reservation ON reservation.site_id = site.site_id " + 
				"WHERE(NOT (? > reservation.from_date and ? < reservation.to_date))) " + 
				"ORDER BY daily_fee " + 
				"LIMIT 5";
		
		Site theSite;
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlParkSites, parkId, arrivalDate, departureDate);
		while (results.next()) {
			theSite = mapRowToSite(results);
			displaySites.add(theSite);
		}		
		return displaySites;	
	}
	
	private Site mapRowToSite(SqlRowSet rows) {
		Site theSite = new Site();
		theSite.setSiteId(rows.getInt("site_id"));
		theSite.setCampgroundId(rows.getInt("campground_id"));
		theSite.setCampgroundName(rows.getString("name"));
		theSite.setSiteNumber(rows.getInt("site_number"));
		theSite.setMaxOccupancy(rows.getInt("max_occupancy"));
		theSite.setAccessible(rows.getBoolean("accessible"));
		theSite.setMaxRVLength(rows.getInt("max_rv_length"));
		theSite.setHasUtilities(rows.getBoolean("utilities"));
		theSite.setCampgroundId2(rows.getInt("campground_id"));
		theSite.setParkId(rows.getInt("park_id"));
		theSite.setName(rows.getString("name"));
		theSite.setOpenFrom(rows.getInt("open_from_mm"));
		theSite.setOpenTo(rows.getInt("open_to_mm"));
		theSite.setDailyFee(rows.getBigDecimal("daily_fee"));
		return theSite;
	
	}

}
