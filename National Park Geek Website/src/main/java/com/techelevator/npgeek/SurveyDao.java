package com.techelevator.npgeek;

import java.util.List;

public interface SurveyDao {

	public List<SurveyResults> getAllSurveys();
	
	public void save (Survey survey);
}
