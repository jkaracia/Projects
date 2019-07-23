package com.techelevator.npgeek;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;


@Component
public class JdbcParksDao implements ParksDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcParksDao(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public List<Parks> getAllParks() {
		List<Parks> allParks = new ArrayList<>();
		String sqlSelectAllParks = "SELECT * FROM park"; //change this
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllParks);
		while(results.next()) {
			allParks.add(mapRowToPark(results));
		}
		return allParks;
	}

	@Override
	public Parks getParkByCode(String parkCode) {
		Parks park = null;
		String sqlSelectParkByCode = "SELECT * FROM park WHERE parkcode = ?"; //change this
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectParkByCode, parkCode);
		if(results.next()) {
			park = mapRowToPark(results);
		}
		return park;
	}
	
	private Parks mapRowToPark(SqlRowSet row) {
		Parks park = new Parks();
		park.setAcreage(row.getInt("acreage"));
		park.setAnimalSpecies(row.getInt("numberofanimalspecies"));
		park.setAnnualVisitors(row.getInt("annualvisitorcount"));
		park.setClimate(row.getString("climate"));
		park.setElevation(row.getInt("elevationinfeet"));
		park.setEntryFee(row.getInt("entryfee"));
		park.setParkCode(row.getString("parkcode"));
		park.setParkCodeLower(row.getString("parkcode"));
		park.setParkDescription(row.getString("parkdescription"));
		park.setParkName(row.getString("parkname"));
		park.setParkState(row.getString("state"));
		park.setQuote(row.getString("inspirationalquote"));
		park.setQuoteSource(row.getString("inspirationalquotesource"));
		park.setTrailMiles(row.getDouble("milesoftrail"));
		park.setNumberOfCampsites(row.getInt("numberofcampsites"));
		park.setYearFounded(row.getInt("yearfounded"));
		return park;
	}

}
