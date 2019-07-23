<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div id="faveParksDetail">
<h2>Favorite National Parks</h2>

<p>Based on Your Votes!! Here is a list of your favorite national parks! Click on the park image to get more information.</p>

<table id="faveParksTable">
	<c:forEach var="surveyResults" items="${survey}">
	<c:url var="clickableLink" value="/parkDetail">
	<c:param name="parkCode">${surveyResults.parkCode}</c:param></c:url>
	<tr id="surveyResultRows">
		<td id="faveParkPic"><a href="${clickableLink}"><img id="faveParkImg" src="<c:url value="img/parks/${surveyResults.parkCodeLower}.jpg"/>"/></a></td>
		<td id="faveParkTitle"><h5 id="faveParkName">${surveyResults.parkName}</h5></td>
		<td id="faveParkSum"><h5>${surveyResults.surveySum} Votes</h5></td>
	</tr>
	</c:forEach>
</table>
</div>


<c:import url="/WEB-INF/jsp/common/footer.jsp" />