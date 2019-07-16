package com.techelevator.campground.model;

import java.time.LocalDate;
import java.util.List;

public interface ReservationDAO {
	
	public List<Reservation> getReservationsFor30DaysByPark(Campground camp);
	
	public int bookReservation (int siteChoice, String nameInput, LocalDate arrivalDate, LocalDate departureDate);

}
