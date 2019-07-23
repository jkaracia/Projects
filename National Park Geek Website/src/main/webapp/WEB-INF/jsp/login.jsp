<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:url var="loginUrl" value="/"/>

<div id="surveyForm">
<h2>Login to National Park Geek</h2>

 <div class="container body-content" style=margin:0px>
    <c:if test="${not empty message}">
        <div class="message alert alert-danger" role="alert">
            <c:out value="${message}"/>
        </div>
    </c:if>


	<form id="loginForm" action="${loginUrl}" method="POST" style=margin-bottom:20px>
	    <div class="form-group">
	        <label for="username">Username</label>
	        <input type="text" class="form-control" id="username" name="username" placeholder="Username">
	    </div>
	    <div class="form-group">
	        <label for="password">Password</label>
	        <input type="password" class="form-control" id="password" name="password" placeholder="Password">
	    </div>
	    <button type="submit" class="btn btn-primary">Login</button>  
	</form>
	<c:url var="registerLink" value="/register"></c:url>
	<a href="${registerLink}"><p>Don't have a login yet? Register for one here.</p></a>
</div>


<c:import url="/WEB-INF/jsp/common/footer.jsp" />