<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
<div id="surveyForm">
<h2>National Parks Survey</h2>

<p>Tell us about your favorite National Park! What activities activities do you enjoy in the park? How many dead bodies have you found? Share your email and we'll make sure you get our newsletter.</p>

<c:url var="surveyUrl" value="/survey"/>
<form:form id="survey" action="${surveyUrl}" method="POST" modelAttribute="survey">
	<div class="form-group row">
		<label for="favePark" class="font-weight-bold col-sm-2 col-form-label" style="padding-top: 0px">Favorite Park</label>
		<form:select path="parkCode">
			<form:option value="">Select Your Favorite Park</form:option>
			<c:forEach var="item" items="${park}">
			<form:option value="${item.parkCode}">${item.parkName}</form:option>
			</c:forEach>		
		</form:select>
	<form:errors path="parkCode" class="text-danger font-weight-bold"/>
	</div>
	<div class="form-group row">
		<label for="email" class="font-weight-bold col-sm-2 col-form-label" style="padding-top: 0px">Email</label>
		<form:input id="emailInputBox" name="email" type="text" path="email" class="form-control" placeholder="enter email"/>
		<form:errors path="email" class="text-danger font-weight-bold"/>
	</div>
	<div class="form-group row">
		<label for="state" class="font-weight-bold col-sm-2 col-form-label" style="padding-top: 0px">State of Residence</label>
		<form:select path="residenceState">
			<form:option value="">State of Residence</form:option>
			<form:option value="AL">Alabama</form:option>
			<form:option value="AK">Alaska</form:option>
			<form:option value="AZ">Arizona</form:option>
			<form:option value="AR">Arkansas</form:option>
			<form:option value="CA">California</form:option>
			<form:option value="CO">Colorado</form:option>
			<form:option value="CT">Connecticut</form:option>
			<form:option value="DE">Delaware</form:option>
			<form:option value="DC">District Of Columbia</form:option>
			<form:option value="FL">Florida</form:option>
			<form:option value="GA">Georgia</form:option>
			<form:option value="HI">Hawaii</form:option>
			<form:option value="ID">Idaho</form:option>
			<form:option value="IL">Illinois</form:option>
			<form:option value="IN">Indiana</form:option>
			<form:option value="IA">Iowa</form:option>
			<form:option value="KS">Kansas</form:option>
			<form:option value="KY">Kentucky</form:option>
			<form:option value="LA">Louisiana</form:option>
			<form:option value="ME">Maine</form:option>
			<form:option value="MD">Maryland</form:option>
			<form:option value="MA">Massachusetts</form:option>
			<form:option value="MI">Michigan</form:option>
			<form:option value="MN">Minnesota</form:option>
			<form:option value="MS">Mississippi</form:option>
			<form:option value="MO">Missouri</form:option>
			<form:option value="MT">Montana</form:option>
			<form:option value="NE">Nebraska</form:option>
			<form:option value="NV">Nevada</form:option>
			<form:option value="NH">New Hampshire</form:option>
			<form:option value="NJ">New Jersey</form:option>
			<form:option value="NM">New Mexico</form:option>
			<form:option value="NY">New York</form:option>
			<form:option value="NC">North Carolina</form:option>
			<form:option value="ND">North Dakota</form:option>
			<form:option value="OH">Ohio</form:option>
			<form:option value="OK">Oklahoma</form:option>
			<form:option value="OR">Oregon</form:option>
			<form:option value="PA">Pennsylvania</form:option>
			<form:option value="RI">Rhode Island</form:option>
			<form:option value="SC">South Carolina</form:option>
			<form:option value="SD">South Dakota</form:option>
			<form:option value="TN">Tennessee</form:option>
			<form:option value="TX">Texas</form:option>
			<form:option value="UT">Utah</form:option>
			<form:option value="VT">Vermont</form:option>
			<form:option value="VA">Virginia</form:option>
			<form:option value="WA">Washington</form:option>
			<form:option value="WV">West Virginia</form:option>
			<form:option value="WI">Wisconsin</form:option>
			<form:option value="WY">Wyoming</form:option>
		</form:select>
		<form:errors path="residenceState" class="text-danger font-weight-bold"/>
	</div>
	<fieldset class="form-group">
	<div id= "activitySelection" class="form-group row">
		<legend  class="font-weight-bold col-sm-2 col-form-label" style="padding-top: 0px">Activity Level</legend>
		<div>
		<div class="form-check">
			<form:radiobutton id="inactive" class="form-check-input" path="activityLevel" value="inactive"/>
			<label class="form-check-label" for="inactive">Inactive  </label>
			<form:errors path="activityLevel" class="text-danger font-weight-bold"/>
		</div>
		<div class="form-check">
			<form:radiobutton id="sedentary" class="form-check-input" path="activityLevel" value="sedentary"/>
			<label class="form-check-label" for="sedentary">Sedentary  </label>		
		</div>
		<div class="form-check">
			<form:radiobutton id="active" class="form-check-input" path="activityLevel" value="active"/>
			<label class="form-check-label" for="active">Active  </label>
		</div>
		<div class="form-check">
			<form:radiobutton id="extremelyActive" class="form-check-input" path="activityLevel" value="extremelyActive"/>
			<label class="form-check-label" for="extremelyActive">Extremely Active  </label>
		</div>
		
		</div>	
	</div>
	</fieldset>
	<div class="form-group row">
		<button type="submit" value="Submit" class="btn btn-primary" style="margin-left: 17px">Submit</button>
	</div>
</form:form>
</div>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />
