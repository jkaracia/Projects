package com.techelevator.campground.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface SiteDAO {
	
	public List<Site> getSiteBasedOnDates(int campgroundId, LocalDate openFrom, LocalDate openTo);
	
	public List<Site> getAllSitesByPark(int parkId, LocalDate arrivalDate, LocalDate departureDate);

}
