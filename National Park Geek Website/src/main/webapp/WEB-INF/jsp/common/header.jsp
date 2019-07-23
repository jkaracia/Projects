<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<meta charset="UTF-8">
<title>National Park Geek</title>
<c:url value="/css/nationalparkgeek.css" var="cssHref" />
<link rel="stylesheet" href="${cssHref}">
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
</head>

<body>
	<header>
		<c:url value="/" var="homePageHref" />
		<c:url value="/img/logo.png" var="logoSrc" />
		<a href="${homePageHref}"> <img id="logo" src="${logoSrc}"
			alt="National Park Geek logo" />
		</a>
	</header>

	<nav id="navBar" class="navbar navbar-expand-lg navbar-dark" style="background-color: #2A96DE;">
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item active"><a class="nav-link"
					href="<c:url value="/parksHome"/>">Home <span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item"><a class="nav-link"
					href="<c:url value="/survey"/>">Survey</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<c:url value="/favoriteParks"/>">Favorite Parks</a></li>
			</ul>
			<ul id="login" class="nav navbar-nav">
	      <c:if test="${empty appCurrentUser}">
	      	<li class="nav-item ${param.activeNav == 'login'? 'active':''}">
	       		<c:url value="/" var="loginUrl"/>
	        	<a class="nav-link" href="${loginUrl}">Login</a>
	      	</li>
	      	<li class="nav-item ${param.activeNav == 'register'? 'active':''}">
	        	<c:url value="/register" var="registerUrl"/>
	         	<a class="nav-link" href="${registerUrl}">Register</a>
	        </li>
		  </c:if>
          <c:if test="${not empty appCurrentUser}">
			<li class="nav-item">
			    <span class="navbar-text mr-3"><c:out value="Hello, ${appCurrentUser.username}!" /></span>
			</li>
            <li class="nav-item">
                <c:url var="logoutUrl" value="/logout"/>
            	<form action="${logoutUrl}" method="POST" class="navbar-form">
                   <button type="submit" class="btn btn-light">Log Out</button>
                </form>
            </li>
          </c:if>
	    </ul>
		</div>
	</nav>