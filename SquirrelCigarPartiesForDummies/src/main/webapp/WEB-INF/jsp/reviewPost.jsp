<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="uft-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,300,400,700"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">

<c:url value="css/site.css" var="cssHref" />
<link rel="stylesheet" href="${cssHref}">

<title>Squirrel Cigar Parties For Dummies</title>
</head>

<body>


	<nav id=mainNavbar
		class="navbar navbar-dark navbar-expand-md py-0 fixed-top">
		<c:url var="postReview" value="/reviewPost" />
		<a href="${postReview}" class="navbar-brand">POST REVIEW</a>
		
		<button class="navbar-toggler" data-toggle="collapse"
			data-target="#navLinks" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navLinks">
			<ul class="navbar-nav">
				<c:url var="homePage" value="/home" />
				<li class="nav-item"><a href="${homePage}" class="nav-link">BOOK</a></li>
			</ul>
		</div>
	</nav>
	
	<section class="container-fluid px-0">
		<div class="row align-items-center">
			<div class="col-lg-4">
				<div id="headingGroup" class="text-black text-center d-none d-lg-block mt-5">
					<h1>POST<span> // </span>A<span> // </span>REVIEW</h1>
					<h1>POST<span> // </span>A<span> // </span>REVIEW</h1>
					<h1>POST<span> // </span>A<span> // </span>REVIEW</h1>
					<h1>POST<span> // </span>A<span> // </span>REVIEW</h1>
					<h1>POST<span> // </span>A<span> // </span>REVIEW</h1>
					<h1>POST<span> // </span>A<span> // </span>REVIEW</h1>
					<h1>POST<span> // </span>A<span> // </span>REVIEW</h1>
				</div>
			</div>
		<div class="row align-items-center col-lg-5" id="review-form">
		<div>
		<form action="reviewPost" method="POST">
			<div class="form-group">
				<h3>REVIEW FORM</h3>
				<div class="form-group">
					<label for="username">User Name:  </label>
					<input type="text" name="username" id="username" placeholder="Will appear on review" />
				</div>
				<div class="form-group">
					<label for="rating">Rating: (1-5) </label>
					<select name="rating" id="rating">
						<option>5</option>
						<option>4</option>
						<option>3</option>
						<option>2</option>
						<option>1</option>
					</select>
				</div>
				<div class="form-group">
					<label for="title">Review Title: </label>
					<input type="text" id="title" name="title" placeholder="Subject of your review"/>
				</div>
				<div class="form-group">
					<label for="text">Review Text: </label>
					<textarea class="form-control" rows="5" name="text" id="text" placeholder="What do you want everyone to know about 'Squirrel Cigar Parties for Dummies' ?"></textarea>
				</div>
				<div class="form-group">
					<button type="submit" value="Submit" class="btn btn-outline-light">Submit</button>
				</div>
			</div>
		</form>
		</div>
				
		</div>
			<div id="top-right" class="col-lg-3">
		        <c:url var="bookCover" value="/etc/forDummies.png" />
				<img id="bookcover" src="${bookCover}" class=img-fluid>
			</div>
	</div>
	</section>
	
</body>
</html>