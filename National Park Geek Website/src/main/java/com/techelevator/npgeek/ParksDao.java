package com.techelevator.npgeek;

import java.util.List;

public interface ParksDao {

	public List<Parks> getAllParks();
	public Parks getParkByCode(String parkCode);
	
	}