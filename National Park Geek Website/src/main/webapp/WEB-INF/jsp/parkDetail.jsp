<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />


<div id="parkDetailView"> <div id="parkImage"><img src="<c:url value="img/parks/${park.parkCodeLower}.jpg"/>"/></div>
<div id="parkHeader"> <h2>${park.parkName} - ${park.parkState}</h2></div>
<div id="parkQuote"><p style="color: #2e5cab"><em>${park.quote}</em></p> <p style="color: #2e5cab"><em>- ${park.quoteSource}</em></p></div>
<div id="descriptionBlock"> <h5>${park.parkDescription}</h5></div>
<div id="infoList">
<ul class="fa-ul">
	<li>Acreage: <fmt:formatNumber type="number" value="${park.acreage}"/></li>
	<li>Elevation: <fmt:formatNumber type="number" value="${park.elevation}"/> ft.</li>
	<li>Miles of Trail: ${park.trailMiles} miles</li>
	<li>No. of Campsites: <fmt:formatNumber type="number" value="${park.numberOfCampsites}"/></li>
	<li>Climate: ${park.climate}</li>
	<li>Year Founded: ${park.yearFounded}</li>
	<li>No. of Annual Visitors: <fmt:formatNumber type="number" value="${park.annualVisitors}"/></li>
	<li>No. of Animal Species: ${park.animalSpecies}</li>
	<li>Entry Fee: $${park.entryFee}</li>
</ul>
</div>
</div>


<table id="weather">
<tr id="tempToggle">
	<td id="tempSetter">
		<c:url var="tempLink" value="/parkDetail">
		<c:param name="temperature" value="celsius" />
		<c:param name="parkCode" value="${park.parkCode}" />
		</c:url>
		<a href="${tempLink}" class="btn btn-primary ${tempAttribute == 'C' ? 'disabled' : ''}">°C</a> 
		<c:url var="tempLink" value="/parkDetail">
		<c:param name="temperature" value="fahrenheit" />
		<c:param name="parkCode" value="${park.parkCode}" />
		</c:url>
		<a href="${tempLink}" class="btn btn-primary ${tempAttribute == 'F' ? 'disabled' : ''}">°F</a>
	</td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
</tr>
<tr>
	<c:forEach var="weatherList" items="${weatherList}" varStatus="status">
	<td>
		<c:if test="${status.first}">
			<div class="today" ><h3>Today</h3>
			<div id="todayWeatherPic"><img src="<c:url value="img/weather/${weatherList.picForecast}.png"/>"/></div>
			<div id="todayTemp"><h4> <font color="blue">${weatherList.lowTemp} ${tempAttribute} </font></h4><h4><font color="red">${weatherList.highTemp} ${tempAttribute}</font></h4></div>
			<div id="todayAdvisory">
				<c:if test="${tempAttribute == 'C'}">
				${weatherList.weatherAdvisory} ${weatherList.celsiusAdvisory}
				</c:if>
				<c:if test="${tempAttribute == 'F'}">
				${weatherList.weatherAdvisory} ${weatherList.tempAdvisory}
				</c:if>
			</div>
			</div>
		</c:if>
		
		<c:if test="${!status.first}">
			<div class="fourDay">
			<h3 id="dayNumber"> Day ${weatherList.day} </h3>
			<img id="weatherPic" src="<c:url value="img/weather/${weatherList.picForecast}.png"/>"/>
			<div id="temp"><h6><font color="blue">${weatherList.lowTemp} ${tempAttribute} </font></h6>
			<h6><font color="red">${weatherList.highTemp} ${tempAttribute}</font></h6></div>
			<p id="advisory"><c:if test="${tempAttribute == 'C'}">
				${weatherList.weatherAdvisory} ${weatherList.celsiusAdvisory}
				</c:if>
				<c:if test="${tempAttribute == 'F'}">
				${weatherList.weatherAdvisory} ${weatherList.tempAdvisory}
				</c:if>
			</p>
			</div>
		</c:if>
	</td>
	</c:forEach>
</tr>
</table>






<c:import url="/WEB-INF/jsp/common/footer.jsp" />