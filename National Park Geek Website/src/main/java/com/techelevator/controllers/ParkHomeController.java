package com.techelevator.controllers;


import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.authentication.AuthProvider;
import com.techelevator.authentication.UnauthorizedException;
import com.techelevator.npgeek.Parks;
import com.techelevator.npgeek.ParksDao;
import com.techelevator.npgeek.Survey;
import com.techelevator.npgeek.SurveyDao;
import com.techelevator.npgeek.SurveyResults;
import com.techelevator.npgeek.Weather;
import com.techelevator.npgeek.WeatherDao;

	@Controller
	@SessionAttributes({"temperature", "parkCode"})
	public class ParkHomeController {
		
		@Autowired
		private AuthProvider auth;
		
		@Autowired
		private ParksDao parksDao;
		
		@Autowired
		private WeatherDao weatherDao;
		
		String fahrenheit = "fahrenheit"; //make constants
		String celsius = "celsius";
		
		@Autowired
		private SurveyDao surveyDao;
		

		@RequestMapping("/parksHome")
		public String displayAllParks(ModelMap modelMap) throws UnauthorizedException {
			if (auth.isLoggedIn() == true) {
			modelMap.put("parksHome", parksDao.getAllParks());
			return "parksHome";
			}
			else {
				throw new UnauthorizedException();
			}
		}
		
		@RequestMapping(path="/parkDetail", method=RequestMethod.GET)
		public String showParkDetail(@RequestParam String parkCode, ModelMap modelMap, @RequestParam(required = false)
									String temperature, HttpSession session) throws UnauthorizedException {
			if (auth.isLoggedIn() == true) {
				Parks park = parksDao.getParkByCode(parkCode);
				modelMap.put("park", park);
				
				List <Weather> weatherList = weatherDao.get5DayForecast(parkCode);
				modelMap.put("weather", weatherList);
				
				if (temperature != null) {
					modelMap.addAttribute("temperature", temperature);
				}
				
				modelMap.addAttribute("parkCode", parkCode);
				String choice = (String)modelMap.get("temperature");
				if(session.getAttribute("tempAttribute") == null) {
					session.setAttribute("tempAttribute", "F");
				}
				
				this.setTemperatureByChoice(choice, parkCode, modelMap, session);
				if(session.getAttribute("convertedTemp") == null) {
					session.setAttribute("convertedTemp", weatherList);
				}
				
				return "parkDetail";
			} else {
				throw new UnauthorizedException();
			}
			
			
		}
		

		
		@RequestMapping(path="/favoriteParks", method=RequestMethod.GET)
		public String faveParks(ModelMap modelMap) throws UnauthorizedException {
			
			if (auth.isLoggedIn() == true) {
				
			List<SurveyResults> surveyResults = surveyDao.getAllSurveys();
			modelMap.put("survey", surveyResults);
			return "favoriteParks";
			}
			else {
				throw new UnauthorizedException();
			}
		}
		
		public void setTemperatureByChoice (String choice, String parkCode, ModelMap modelMap, HttpSession session) {
			List<Weather> weatherList = new ArrayList<Weather>();
			weatherList = weatherDao.get5DayForecast(parkCode);
			
			if (choice == null) {
				modelMap.addAttribute("temperature", fahrenheit);
				modelMap.addAttribute("weatherList", weatherList);
				modelMap.addAttribute("temp", "F");
			}
			else if (choice.equals(celsius)) {
				for (Weather weather : weatherList) {
					int fahrenheitHigh = weather.getHighTemp();
					int fahrenheitLow = weather.getLowTemp();
					weather.setHighTemp(convertToCelsius(fahrenheitHigh));
					weather.setLowTemp(convertToCelsius(fahrenheitLow));
				}
				modelMap.addAttribute("weatherList", weatherList);
				modelMap.addAttribute("temp","C");
				session.setAttribute("convertedTemp", weatherList);
				session.setAttribute("tempAttribute", "C");
			} else {
				modelMap.addAttribute("weatherList", weatherList);
				modelMap.addAttribute("temp", "F");
				session.setAttribute("convertedTemp", weatherList);
				session.setAttribute("tempAttribute", "F");
			}
			
		}
		
		public int convertToCelsius(int fahrenheit) {
			int celsius = (fahrenheit - 32) * 5 / 9;
			return celsius;
		}
	

	@RequestMapping(path="/survey", method=RequestMethod.GET)
	public String newSurvey(ModelMap modelMap) throws UnauthorizedException {
		if (auth.isLoggedIn() == true) {
		if (modelMap.containsAttribute("survey") == false) {
			Survey example = new Survey();
			modelMap.put("survey", example);
			
		}
		
		List<Parks> parksForSelection = parksDao.getAllParks();
		modelMap.put("park", parksForSelection);
		
		return "survey";
		}
		else {
			throw new UnauthorizedException();
		}
	}
	
	@RequestMapping(path="/survey", method=RequestMethod.POST)
	public String completeSurvey(@Valid @ModelAttribute Survey survey,  BindingResult result, RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("survey", survey);
			redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "survey", result);
			return "redirect:/survey";
		}

		surveyDao.save(survey);
		return "redirect:/favoriteParks";
	}
	}

