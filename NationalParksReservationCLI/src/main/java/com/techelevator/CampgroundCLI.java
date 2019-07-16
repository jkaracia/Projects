package com.techelevator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import com.techelevator.campground.model.Campground;
import com.techelevator.campground.model.CampgroundDAO;
import com.techelevator.campground.model.Park;
import com.techelevator.campground.model.ParkDAO;
import com.techelevator.campground.model.Reservation;
import com.techelevator.campground.model.ReservationDAO;
import com.techelevator.campground.model.Site;
import com.techelevator.campground.model.SiteDAO;
import com.techelevator.campground.model.jdbc.JDBCCampgroundDAO;
import com.techelevator.campground.model.jdbc.JDBCParkDAO;
import com.techelevator.campground.model.jdbc.JDBCReservationDAO;
import com.techelevator.campground.model.jdbc.JDBCSiteDAO;
import com.techelevator.campground.view.Menu;

public class CampgroundCLI {
	
	private static final String MAIN_MENU_OPTION_PARKS = "View Parks Interface";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = new String[] { MAIN_MENU_OPTION_PARKS, 
																	 MAIN_MENU_OPTION_EXIT };
	
	private static final String CAMP_MENU_DISPLAY_CAMPGROUNDS = "View Campground";
	private static final String CAMP_MENU_SEARCH_AVAILABLE_RESERVATIONS = "Search for Reservation";
	private static final String CAMP_MENU_VIEW_AVAILBLE_SITES = "View All Available Camp Sites by Park";
	private static final String CAMP_MENU_VIEW_RESERVATIONS_FOR_PARK_IN_NEXT_30_DAYS = "View All Reservations Made for the Park in the Next 30 Days";
	private static final String CAMP_MENU_RETURN_TO_PREVIOUS = "Return to Previous Screen";
	private static final String[] CAMP_MENU_OPTIONS = new String[] { CAMP_MENU_DISPLAY_CAMPGROUNDS, CAMP_MENU_SEARCH_AVAILABLE_RESERVATIONS,
								CAMP_MENU_VIEW_AVAILBLE_SITES, CAMP_MENU_VIEW_RESERVATIONS_FOR_PARK_IN_NEXT_30_DAYS, CAMP_MENU_RETURN_TO_PREVIOUS };
	
	private static final String RESERVATION_MENU_SEARCH_AVAILABLE = "Search for Available Reservation";
	private static final String RESERVATION_MENU_RETURN_TO_PREVIOUS = "Return to Previous Screen";
	private static final String[] RESERVATION_MENU_OPTIONS = new String[] { RESERVATION_MENU_SEARCH_AVAILABLE,
																	RESERVATION_MENU_RETURN_TO_PREVIOUS };
	
	private Menu menu;
	private ParkDAO parkDAO;
	private CampgroundDAO campgroundDAO;
	private SiteDAO siteDAO;
	private ReservationDAO reservationDAO;
	private int choiceNum;
	private Park chosenPark;
	public static String wrap;
	private List<Campground> campsByPark;
	private int chosenCampId = 0;
	private int chosenSiteId = 0;
	private LocalDate arrival;
	private LocalDate departure;
	private List<Site> availableSites;
	private List<Park> results;
	
	
	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		CampgroundCLI application = new CampgroundCLI(dataSource);
		application.run();
	}

	public CampgroundCLI(DataSource dataSource) {
		this.menu = new Menu(System.in, System.out);
		parkDAO = new JDBCParkDAO(dataSource);
		campgroundDAO = new JDBCCampgroundDAO(dataSource);
		siteDAO = new JDBCSiteDAO(dataSource);
		reservationDAO = new JDBCReservationDAO(dataSource);
	}

	public void run() {
		displayApplicationBanner();
		printHeading("National Park Campsite Reservation System");
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_PARKS)) {
				displayParks();
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.out.println("Thank you for using the National Park Campsite Reservation System!");
				break;
			}
		}
	}
	
	private void displayParks() {	//displays numbered list of parks in reservation system with quit option
		System.out.println("\nSelect a Park for Further Details");
		System.out.println();
		results = parkDAO.getAllParks();
		int count = 1;
		for (Park park : results) {
			System.out.println(count++ + ") " + park.getParkName());
		}
			System.out.println("Q) " + "Quit");
		
		System.out.println("Please choose and option >>>");
		System.out.println();
		
		String choice1 = menu.getUserInput();
		choiceNum = parkChoice(choice1, results);
		
		for (Park park : results) {
			if (choiceNum == park.getParkId()) {
				chosenPark = park;
			}
		}
		displayParkInfo(choiceNum, chosenPark);
		
		displayCampInfo();
	}
	
	private int parkChoice(String choice1, List<Park> results) {	//returns park based on user input. returns error for invalid input
		int choiceNum = 0;
		String choice = "";
		int parkSearch = 0;
		
		try {
			choiceNum = Integer.parseInt(choice1);
			
			for (Park park : results) {
				if (choiceNum == park.getParkId()) {
					parkSearch = park.getParkId();
				}
			}
			if (parkSearch == 0) {
				System.out.println("*** " + choice1 + " is not a valid option ***");
				displayParks();
			}
		
		} catch (NumberFormatException e) {
			choice = choice1.toUpperCase();
			
			if (choice.equals("Q")) {
				System.out.println();
				System.out.println("Thank you for using the National Park Campsite Reservation System!");
				System.exit(0);
			} else {
				System.out.println("*** " + choice + " is not a valid option ***");
				displayParks();
			}
		}
		return choiceNum;
	}
	
	private void displayParkInfo (int parkId, Park aPark) {	//displays all park information from database
		
		DateTimeFormatter prettyDate = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		WordWrap formatDesc = new WordWrap();
		String formattedDesc = aPark.getDescription();
		
		System.out.println();
		System.out.println("*** Park Information Screen ***");
		System.out.println();
		System.out.println(aPark.getParkName() + " National Park");
		System.out.printf(String.format("\n%-20s%1s%-10s", "Location: ", "", aPark.getLocation()));
		System.out.printf(String.format("\n%-20s%1s%-10s", "Established: ", "", aPark.getEstablishDate().format(prettyDate)));
		System.out.printf(String.format("\n%-20s%1s%-5d%2s", "Area: ", "", aPark.getArea(), " sq km"));
		System.out.printf(String.format("\n%-20s%1s%,-10d", "Annual Visitors: ", "", aPark.getVisitors()));
		System.out.println();
		System.out.println();
		formatDesc.minNumLinesWrap(formattedDesc);
		System.out.println();
		
		}
	
	private void displayCampInfo () {	//method for all Camp related Menu Options
		String choice = (String)menu.getChoiceFromOptions(CAMP_MENU_OPTIONS);
		if (choice.equals(CAMP_MENU_DISPLAY_CAMPGROUNDS)) {
			System.out.println(chosenPark.getParkName() + " National Park Campgrounds");
			displayCampgroundsByPark(choiceNum, chosenPark);
			takeReservation();
			
		} else if (choice.equals(CAMP_MENU_SEARCH_AVAILABLE_RESERVATIONS)) {
			System.out.println ("Search for Campground Reservation");
			System.out.println();
			displayCampgroundsByPark(choiceNum, chosenPark);
			catchDateExceptions();
			
		} else if (choice.equals(CAMP_MENU_VIEW_AVAILBLE_SITES)) {
			catchSiteDateExceptions();
			System.out.println ("Sites Available in the Park");
			displaySitesByPark(choiceNum, chosenPark);
			
		} else if (choice.contentEquals(CAMP_MENU_VIEW_RESERVATIONS_FOR_PARK_IN_NEXT_30_DAYS)) {
			System.out.println("Reservations Made for the Park in the Next 30 Days");
			printReservationsForPark(chosenPark);
			
		} else if (choice.equals(CAMP_MENU_RETURN_TO_PREVIOUS)) {
			displayParks();
			
		}
	}
	
	private void takeReservation() { //method for all reservation related menu options
		System.out.println("Select a Command");
		
		String choice = (String)menu.getChoiceFromOptions(RESERVATION_MENU_OPTIONS);
		if (choice.equals(RESERVATION_MENU_SEARCH_AVAILABLE)) {
			catchDateExceptions();
		} else if (choice.equals(RESERVATION_MENU_RETURN_TO_PREVIOUS)) {
			displayCampInfo();
		}
	}
	
	private void catchDateExceptions() { //menu for available reservation with camp ground choice getArrivalAndDepartureDates
		
		System.out.println("Which Campground (enter 0 to cancel)? __");  //where we're calling catchDateExceptions
		String stringCampId = menu.getUserInput();
		chosenCampId = campgroundExceptions(stringCampId);
		
		//Arrival
		System.out.println("Please enter arrival date (mm/dd/yyyy)");
		String arrivalStr = menu.getUserInput();
		arrival = dateExceptions(arrivalStr);
		
		//Departure
		System.out.println("Please enter departure date (mm/dd/yyyy)");
		String departureStr = menu.getUserInput();
		departure = dateExceptions(departureStr);
		
		if(departure.compareTo(arrival) <= 0) {
			System.out.println("Please enter a valid date range");
			catchDateExceptions();
		}
		displayAvailableSites();
	}
	
	private void catchSiteDateExceptions() { //menu for park-wide site availability
		//Arrival
		System.out.println("Please enter arrival date (mm/dd/yyyy)");
		String arrivalStr = menu.getUserInput();
		arrival = dateExceptions(arrivalStr); //method for getDate with String parameter
		
		//Departure
		System.out.println("Please enter departure date (mm/dd/yyyy)");
		String departureStr = menu.getUserInput();
		departure = dateExceptions(departureStr);
		
		if(departure.compareTo(arrival) <= 0) {
			System.out.println("Please enter a valid date range");
			catchSiteDateExceptions();
		}
		displaySitesByPark(choiceNum, chosenPark);
		
	}
	
	private LocalDate dateExceptions(String dateEntered) { //exception handling for date input "convertStringToDate"
		LocalDate result = null;
		if(dateEntered.length() != 10) {
			System.out.println("Invalid date format");
			catchDateExceptions();
		} else {
			String[] dateArray = dateEntered.split("/");
			for (String s : dateArray) {
				try {
					Integer.parseInt(s);
				} catch (NumberFormatException e) {
					System.out.println("Invalid date format");
					catchDateExceptions();
				}
			}
			if(dateArray.length !=3) {
				System.out.println("Invalid date format");
				catchDateExceptions();
			}
			int Month = Integer.parseInt(dateArray[0]);
			int Day = Integer.parseInt(dateArray[1]);
			int Year = Integer.parseInt(dateArray[2]);
			
			if(Month < 1 || Month > 12){
				System.out.println("Invalid date format");
				catchDateExceptions();
			}
			if(Day < 1 || Day > 31) {
				System.out.println("Invalid date format");
				catchDateExceptions();
			}
			if(Day > 30 && (Month == 2 || Month == 4 || Month == 6 || Month == 9 || Month == 11)) {
				System.out.println("Invalid date format");
				catchDateExceptions();
			}
			if(Year > 2019) {
				System.out.println("Invalid year");
				catchDateExceptions();
			}
			result = LocalDate.of(Year,Month,Day);
			
	}
			return result;
	
	}
		
	private int campgroundExceptions(String campId) { //exception handling for campground input //validateCampground or convertCampgroundIdToInt
		int chosenCampground = 0;
		int result = 0;
		try {
			chosenCampground = Integer.parseInt(campId);
			
		} catch (NumberFormatException e) {
			System.out.println("Invalid entry");
			catchDateExceptions();
			
		}
		if (chosenCampground == 0) {
			System.out.println("Returning to Camp Menu");
			displayCampInfo();
			
		}
		chosenCampground--;
		if(chosenCampground < 0 || chosenCampground > campsByPark.size()-1) {
			System.out.println("Please select a different value");
			catchDateExceptions();
		} else {
			result = campsByPark.get(chosenCampground).getCampgroundId();
		}
		return result;
	}
	
	private int siteExceptions (String siteNum) { //exception handling for site input //convertSiteIdToInt
		int chosenSite = 0;
		int result = 0;
		
		try {
			chosenSite = Integer.parseInt(siteNum);
			
		} catch (NumberFormatException e) {
			System.out.println("Invalid Entry");
			makeReservation();
		}
		if (chosenSite == 0) {
			System.out.println("Returning to Camp Menu");
			displayCampInfo();
		}
		chosenSite--; //decrement to get correct site index
		if (chosenSite < 0 || chosenSite > availableSites.size() -1) {
			System.out.println("Please select a different value");
			makeReservation();
		} else {
			result = availableSites.get(chosenSite).getSiteId();
		}
		return result;
		
	}
	
	private void displayAvailableSites() { //displays available camp sites based on search criteria and calculates total cost of reservation
		System.out.println();
		System.out.println("Results Matching Your Search Criteria");
		System.out.println();
		availableSites = siteDAO.getSiteBasedOnDates(chosenCampId, arrival, departure);
		
		BigDecimal days = new BigDecimal((int)ChronoUnit.DAYS.between(arrival, departure));
		
		System.out.println(String.format("%-12s%-14s%-12s%15s%14s%10s", "Site No.", "Max Occup.", "Accessible?", "Max RV Length", "Utility", "Cost"));
		
		String yOrN = "";
		String rvLength = "";
		String utility = "";
		String totalCost = "";
		int count = 1;
		
		for (Site site : availableSites) {
			BigDecimal totalFee = site.getDailyFee().multiply(days);
			if (site.isAccessible()) {
				yOrN = "Yes";
			} else {
				yOrN = "No";
			}
			
			if (site.getMaxRVLength() == 0) {
				rvLength = "N/A";
			} else {
				rvLength = Integer.toString(site.getMaxRVLength());
			}
			
			if (site.isHasUtilities()) {
				utility = "Yes";
			} else {
				utility = "N/A";
			}
			totalCost = "$" + Double.toString(totalFee.doubleValue()) + "0";
			
			System.out.println(String.format("%-12d%-14s%-14s%-20s%-13s%1s", 
										count++, site.getMaxOccupancy(), yOrN, rvLength, utility, totalCost));
		}
		if (availableSites.size() == 0) {
			altDateRange();
		}
		makeReservation();
	}
	
	private void altDateRange() {  //if site isn't available, allows user to search alternate dates
		System.out.println("There are no available sites for the specified date range. Enter alternate date range? (Y/N) ");
		String yOrN = menu.getUserInput();
		if (yOrN.toUpperCase().equals("Y")) {
			catchSiteDateExceptions();
		} else if (yOrN.toUpperCase().equals("N")) {
			System.out.println("Returning to Camp Menu");
			displayCampInfo();
		} else {
			System.out.println("Invalid Entry");
			altDateRange();
		}
	}
	
	private void makeReservation() { //records reservation to database and returns a reservation id
			System.out.println ("Which site would you like to reserve (enter 0 to cancel)? __");
			String siteNum = menu.getUserInput();
			chosenSiteId = siteExceptions(siteNum);
			
			System.out.println("What name should the reservation be made under? __");
			String reservationName = menu.getUserInput();
			
			int reservationId = reservationDAO.bookReservation(chosenSiteId, reservationName, arrival, departure);
			
			System.out.println("The reservation has been made. Your confirmation id is: { " + reservationId + " }");
			displayParks();		
	}
	
	private void displayCampgroundsByPark(int parkId, Park selectedPark) {
		campsByPark = campgroundDAO.getAllCampgroundsByParkId(parkId);
		System.out.println();
		System.out.println(String.format("%-10s%1s%-35s%1s%-9s%1s%-10s%1s%-20s",  "Camp No.", "", "Name", "", "Opens", "", "Closes", "", "Daily Fee"));
		
		int count = 1;
		
		for (Campground campground : campsByPark) {
			String price = "$" + String.format("%.2f", campground.getDailyFee());
			String countToString = "#" + count++;
			System.out.println(String.format("%-10s%1s%-35s%1s%-9s%1s%-10s%1s%-20s", countToString, "",campground.getCampgroundName(), "", campground.getOpenFromMonth(), 
												"", campground.getOpenToMonth(), "", price));
		}
		
		System.out.println();
	}
	
	private void displaySitesByPark(int parkId, Park selectedPark) {
		System.out.println();
		System.out.println("Results Matching Your Search Criteria");
		System.out.println();
		availableSites = siteDAO.getAllSitesByPark(parkId, arrival, departure);
		
		BigDecimal days = new BigDecimal((int)ChronoUnit.DAYS.between(arrival, departure));
		
		System.out.println(String.format("%-35s%-12s%-14s%-12s%15s%14s%10s","Campground", "Site No.", "Max Occup.", "Accessible?", "Max RV Length", "Utility", "Cost"));
		
		String yOrN = "";
		String rvLength = "";
		String utility = "";
		String totalCost = "";
		int count = 1;
		
		for (Site site : availableSites) {
			BigDecimal totalFee = site.getDailyFee().multiply(days);
			if(site.isAccessible()) {
				yOrN = "Yes";
			} else {
				yOrN = "No";
			}
			
			if (site.getMaxRVLength() == 0) {
				rvLength = "N/A";
			} else {
				rvLength = Integer.toString(site.getMaxRVLength());
			}
			
			if (site.isHasUtilities()) {
				utility = "Yes";
			} else {
				utility = "N/A";
			}
			totalCost = "$" + Double.toString(totalFee.doubleValue()) + "0";
			
			System.out.println(String.format("%-35s%-12d%-14s%-14s%-20s%-13s%1s", 
										site.getCampgroundName(), count++, site.getMaxOccupancy(), yOrN, rvLength, utility, totalCost));
		}
		if (availableSites.size() == 0) {
			altDateRange();
		}
		makeReservation();
		
	}
	
	private void printReservationsForPark(Park park) { //prints upcoming reservations for 30-days based on park input
		
		List<Campground> campList = campgroundDAO.getAllCampgroundsByParkId(park.getParkId());
		
		System.out.println("Reservations for Campgrounds in " +park.getParkName()+" for the next 30 days");
		System.out.println();
		System.out.println(String.format("%-10s%-35s%-10s%-14s%-14s", "Site No.", "Name", "Res. No.", "Arrival Date", "Departure Date"));
		
		for (Campground camp : campList) {
			
			System.out.println();
			
			List<Reservation> reserve = reservationDAO.getReservationsFor30DaysByPark(camp);
			for (Reservation res : reserve)
			{
				System.out.format("%-10d%-35s%-10d%-14s%-14s%n", res.getSiteId(), res.getReservationName(), res.getReservationId(),
						res.getReservationFromDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")), res.getReservationToDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
			}
			displayParks();
		}
		
	}
	
	private void printHeading(String headingText) {
		System.out.println("\n" + headingText);
		System.out.println("------------------------------------------");
		System.out.println();
	}
		
	private void displayApplicationBanner() {

		System.out.println("    \n" + 
				"_________                                                             .___\n" + 
				"\\_   ___ \\_____    _____ ______   ___________  ____  __ __  ____    __| _/\n" + 
				"/    \\  \\/\\__  \\  /     \\\\____ \\ / ___\\_  __ \\/  _ \\|  |  \\/    \\  / __ | \n" + 
				"\\     \\____/ __ \\|  Y Y  \\  |_> > /_/  >  | \\(  <_> )  |  /   |  \\/ /_/ | \n" + 
				" \\______  (____  /__|_|  /   __/\\___  /|__|   \\____/|____/|___|  /\\____ | \n" + 
				"        \\/     \\/      \\/|__|  /_____/                         \\/      \\/ \n" + 
				" \n" +   
				
				""
				+ ""
				+ "                                   /\\\n" + 
				"                              /\\  //\\\\\n" + 
				"                       /\\    //\\\\///\\\\\\        /\\\n" + 
				"                      //\\\\  ///\\////\\\\\\\\  /\\  //\\\\\n" + 
				"         /\\          /  ^ \\/^ ^/^  ^  ^ \\/^ \\/  ^ \\\n" + 
				"        / ^\\    /\\  / ^   /  ^/ ^ ^ ^   ^\\ ^/  ^^  \\\n" + 
				"       /^   \\  / ^\\/ ^ ^   ^ / ^  ^    ^  \\/ ^   ^  \\       *\n" + 
				"      /  ^ ^ \\/^  ^\\ ^ ^ ^   ^  ^   ^   ____  ^   ^  \\     /|\\\n" + 
				"     / ^ ^  ^ \\ ^  _\\___________________|  |_____^ ^  \\   /||o\\\n" + 
				"    / ^^  ^ ^ ^\\  /______________________________\\ ^ ^ \\ /|o|||\\\n" + 
				"   /  ^  ^^ ^ ^  /________________________________\\  ^  /|||||o|\\\n" + 
				"  /^ ^  ^ ^^  ^    ||___|___||||||||||||___|__|||      /||o||||||\\       |\n" + 
				" / ^   ^   ^    ^  ||___|___||||||||||||___|__|||          | |           |\n" + 
				"/ ^ ^ ^  ^  ^  ^   ||||||||||||||||||||||||||||||oooooooooo| |ooooooo  |\n" + 
				"ooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
		
		System.out.println();
	}
}
