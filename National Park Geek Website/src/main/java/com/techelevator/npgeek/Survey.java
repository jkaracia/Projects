package com.techelevator.npgeek;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class Survey {

	private int surveyId;
	@NotBlank(message=" * Favorite Park is required")
	private String parkCode;
	
	private String parkName;
	
	@NotBlank(message=" * Email is required")
	@Email(message="invalid email address")
	private String email;
	
	@NotBlank(message=" * State of Residence is required")
	private String residenceState;
	
	@NotBlank(message=" * Activity Level is required")
	private String activityLevel;

	public int getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}
	
	public String getParkCode() {
		return parkCode;
	}

	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getResidenceState() {
		return residenceState;
	}

	public void setResidenceState(String residenceState) {
		this.residenceState = residenceState;
	}

	public String getActivityLevel() {
		return activityLevel;
	}

	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}

}
