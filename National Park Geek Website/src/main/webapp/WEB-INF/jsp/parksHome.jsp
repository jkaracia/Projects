<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

	
	<c:forEach var="parksList" items="${parksHome}">
	<section id="parkInfo">
	
		<c:url var="clickableLink" value="/parkDetail">
		<c:param name="parkCode">${parksList.parkCode}</c:param></c:url>	
		<div id="image"><a href="${clickableLink}"><img id="parkPic" src="<c:url value="img/parks/${parksList.parkCodeLower}.jpg"/>"/></a></div>
		<div id="parkName"><a href="${clickableLink}" style="color: black"><h2>${parksList.parkName}</h2></a></div>
		<div id=parkDescription><a href="${clickableLink}" style="color: black"><p>${parksList.parkDescription}</p></a></div>
	</section>
	</c:forEach>
	




<c:import url="/WEB-INF/jsp/common/footer.jsp" />