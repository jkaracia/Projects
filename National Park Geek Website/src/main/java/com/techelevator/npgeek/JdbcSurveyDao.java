package com.techelevator.npgeek;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcSurveyDao implements SurveyDao {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcSurveyDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<SurveyResults> getAllSurveys() {
		List<SurveyResults> allSurveys = new ArrayList<>();
		String sqlSelectAllSurveys = "SELECT survey_result.parkcode, park.parkname, count(survey_result.parkCode) AS surveycount "
									 + "FROM survey_result JOIN park ON park.parkcode = survey_result.parkcode "
									 + "GROUP BY survey_result.parkcode, park.parkname ORDER BY surveycount DESC, park.parkname ASC";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllSurveys);
		while(results.next()) {
			SurveyResults survey = new SurveyResults();
			survey.setParkName(results.getString("parkname"));
			survey.setParkCode(results.getString("parkcode"));
			survey.setParkCodeLower(results.getString("parkcode"));
			survey.setSurveySum(results.getInt("surveycount"));
			allSurveys.add(survey);
		}
		return allSurveys;
	}

	@Override
	public void save(Survey survey) {
		int surveyId = getNextSurveyId();
		String sqlInsertSurvey = "INSERT INTO survey_result(surveyid, parkcode, emailaddress, state, activitylevel) VALUES (?,?,?,?,?)";
		jdbcTemplate.update(sqlInsertSurvey, surveyId, survey.getParkCode(), survey.getEmail(), survey.getResidenceState(), survey.getActivityLevel());
		survey.setSurveyId(surveyId);
	}
	
	private int getNextSurveyId() {
		String sqlSelectNextSurveyId = "SELECT NEXTVAL('seq_surveyid')";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectNextSurveyId);
		int surveyId = 0;
		if (results.next()) {
			surveyId = results.getInt(1);
		} else {
			throw new RuntimeException("Something strange happened, unable to select next survey id from sequence");
		}
		return surveyId;
	}
	
	

}
