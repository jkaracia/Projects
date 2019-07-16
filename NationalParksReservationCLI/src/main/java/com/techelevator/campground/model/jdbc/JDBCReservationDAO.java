package com.techelevator.campground.model.jdbc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.Campground;
import com.techelevator.campground.model.Park;
import com.techelevator.campground.model.Reservation;
import com.techelevator.campground.model.ReservationDAO;
import com.techelevator.campground.model.Site;

public class JDBCReservationDAO implements ReservationDAO {
	
	private JdbcTemplate jdbcTemplate;

	public JDBCReservationDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int bookReservation(int siteChoice, String nameInput, LocalDate arrivalDate, LocalDate departureDate) {
		int reservationId = getNextReservationId();
		String sqlBook = "INSERT INTO reservation (reservation_id, site_id, name, from_date, to_date, create_date) "
						+ "VALUES (?, ?, ?, ?, ?, NOW())";
		jdbcTemplate.update(sqlBook, reservationId, siteChoice, nameInput, arrivalDate, departureDate);
		
		return reservationId;
	}
	
	public int getNextReservationId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('reservation_reservation_id_seq')");
		if (nextIdResult.next()) {
			return nextIdResult.getInt(1);
		} else {
			throw new RuntimeException("Something went wrong");
		}
	}
	
	@Override
	public List<Reservation> getReservationsFor30DaysByPark(Campground camp) {
		List<Reservation> listOfReservations = new ArrayList<Reservation>();
		String sql = "SELECT reservation.reservation_id, reservation.site_id, reservation.name, reservation.from_date, reservation.to_date, reservation.create_date " +
					 "FROM reservation " +
					 "JOIN site ON reservation.site_id = site.site_id " +
					 "JOIN campground ON campground.campground_id = site.campground_id " + 
					 "JOIN park ON park.park_id = campground.park_id " +
					 "WHERE campground.park_id = ? AND from_date BETWEEN current_date AND current_date + INTERVAL '30 day'";
				
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, camp.getParkId());
		Reservation madereservations;
		while (results.next()) {
			madereservations = mapRowToReservation(results);
			listOfReservations.add(madereservations);
		}
		
		return listOfReservations;	
	}
	
	private Reservation mapRowToReservation(SqlRowSet rows) {
		Reservation r = new Reservation();
		r.setReservationId(rows.getInt("reservation_id"));
		r.setSiteId(rows.getInt("site_id"));
		r.setReservationName(rows.getString("name"));
		r.setReservationFromDate(rows.getDate("from_date").toLocalDate());
		r.setReservationToDate(rows.getDate("to_date").toLocalDate());
		r.setReservationCreateDate(rows.getDate("create_date").toLocalDate());
		
		return r;
	
	}

}
