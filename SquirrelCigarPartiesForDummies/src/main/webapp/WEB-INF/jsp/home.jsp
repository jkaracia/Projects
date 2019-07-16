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
		<a href="#" class="navbar-brand">BOOK</a>
		<button class="navbar-toggler" data-toggle="collapse"
			data-target="#navLinks" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navLinks">
			<ul class="navbar-nav">
				<c:url var="postReview" value="/reviewPost" />
				<li class="nav-item"><a href="${postReview}" class="nav-link">POST REVIEW</a></li>
			</ul>
		</div>
	</nav>
	
	<section class="container-fluid px-0">
	  <div id="top" class="row align-items-center">
	    <div class="col-lg-6">
	      <div id="headingGroup" class="text-black text-center d-none d-lg-block mt-5">
	        <h1>SQUIRREL<span> // </span>CIGAR<span> // </span>PARTIES</h1>
	        <h1>SQUIRREL<span> // </span>CIGAR<span> // </span>PARTIES</h1>
	        <h1>SQUIRREL<span> // </span>CIGAR<span> // </span>PARTIES</h1>
	        <h1>SQUIRREL<span> // </span>CIGAR<span> // </span>PARTIES</h1>
	        <h1>SQUIRREL<span> // </span>CIGAR<span> // </span>PARTIES</h1>
	        <h1>SQUIRREL<span> // </span>CIGAR<span> // </span>PARTIES</h1>
	        <h1>SQUIRREL<span> // </span>CIGAR<span> // </span>PARTIES</h1>
	      </div>
	    </div>
	        <div id="top-right" class="col-lg-3">
	        <c:url var="bookCover" value="/etc/forDummies.png" />
			<img id="bookcover" src="${bookCover}" class=img-fluid>
	      </div>
	      <div id="top-right" class="col-lg-3">
	        <c:url var="authorShot" value="/etc/Craig_headshot_aviary.png" />
			<img id="authorshot" src="${authorShot}" class=img-fluid>
	      </div>
	  </div>
	</section>
	
	<section class="container-fluid px-0">
	  <div class="row align-items-center content">
	    <div class="col-md-6 order-2 order-md-1">
	    <c:url var="cigar" value="/etc/cigar.png" />
			<img src="${cigar}" class=img-fluid>
	    </div>
	    <div class="col-md-6 text-center order-1 order-md-2">
	      <div class="row justify-content-center">
	        <div class="col-10 col-lg 8 blurb mb-5 mb-md-0">
	        	<c:url var="squirrelIcon" value="/etc/squirrel-01.png" />
	        		<img src="${squirrelIcon}">
	            <h2>THE MUST-HAVE REFERENCE FOR SQUIRREL CIGAR PARTIES</h2>
	            <p class="lead">Actually man braid meh polaroid hell of, franzen hexagon schlitz intelligentsia affogato crucifix. Subway tile disrupt four dollar toast, hoodie distillery waistcoat live-edge cred. Ethical VHS try-hard food truck, trust fund mlkshk kitsch poutine 3 wolf moon intelligentsia disrupt. Next level raclette messenger bag, raw denim leggings semiotics umami enamel pin chambray woke cornhole. Freegan franzen tote bag DIY tattooed. Art party twee trust fund, post-ironic kombucha pug hot chicken offal pork belly cornhole.</p>
	        </div>
	      </div>
	    </div>
	  </div>
	<div class="row align-items-center content">
      <div class="col-md-6 text-center">
        <div class="row justify-content-center">
          <div class="col-10 col-lg 8 blurb mb-5 mb-md-0">
              <h2>REVIEWS FOR SQUIRREL CIGAR PARTIES FOR DUMMIES</h2>
	           <c:forEach var="review" items="${reviews}">
					<div>
						<hr>
						<h4>${review.title} (${review.username})</h4>
						<p>${review.formattedDate}</p>
						<c:forEach begin="1" end="${review.rating}">
							<c:url var="star" value="/etc/nice_star.png" />
							<img src="${star}" />
						</c:forEach>
						<p class="message">${review.text}</p>
					</div>
				</c:forEach>
          </div>
        </div>
      </div>
      <div class="col-md-6 order-2 order-md-1">
	      <c:url var="standingSquirrel" value="/etc/squirrel.png" />
			<img src="${standingSquirrel}" class=img-fluid>
      </div>
    </div>
</section>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    
    <script>
      $(function () {
        $(document).scroll(function(){
          var $nav = $("#mainNavbar");
          $nav.toggleClass("scrolled", $(this).scrollTop() > $nav.height());
        });
      });
    </script>


</body>
</html>