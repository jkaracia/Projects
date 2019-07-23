<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:url var="logoutUrl" value="/logout"/>

<div id="surveyForm">
<h2>Thank You for Visiting National Park Geek</h2>
<c:url var="loginLink" value="/"></c:url>
<a href="${loginLink}"><p>Not ready to Log Out? Click here to log back in.</p></a>
</div>



<c:import url="/WEB-INF/jsp/common/footer.jsp" />