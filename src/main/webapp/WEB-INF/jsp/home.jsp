<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>


<link rel="stylesheet"
	href="https://www.contribe.se/hs-fs/hub/2685895/hub_generated/template_assets/1497453802135/custom/page/Contribe_Mar2017-theme/Contribe_Mar2017-style.min.css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ"
	crossorigin="anonymous">
	
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
	integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"
	integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn"
	crossorigin="anonymous"></script>



<style type="text/css">
.blue {
	color: #0275d8;
}

.title {
	font-family: edo;
}

.blank {
	padding: 4rem 2rem;
}
</style>

<script>
	
	function showBooks(iurl){
		$('#booksViews').hide();
	    $("#LoadingImage").show();

	    $.ajax({
	         type: "GET",
	         cache: false,
	         url: iurl,
	         success: function(response){
	     	    $("#LoadingImage").fadeOut(600, function(){
		            $('#booksViews').html(response).fadeIn('slow');
	     	    });

	         }
	    });	    
	}
	
	
	function showUser(iurl){
		$('#userViews').hide();
	    $("#LoadingImageUser").show();

	    $.ajax({
	         type: "GET",
	         cache: false,
	         url: iurl,
	         success: function(response){
	     	    $("#LoadingImageUser").fadeOut(600, function(){
		            $('#userViews').html(response).fadeIn('slow');
	     	    });

	         }
	    });	    
	}
	

	
	
	var STORE = {};
	STORE.searchUrl = '<spring:url value="books/list/" />';
	STORE.userUrl = '<spring:url value="users/get/" />';

	
	$(function(){
		


		//1
		showBooks(STORE.searchUrl);
		showUser(STORE.userUrl);

		//2 button click
		$("#searchButton").click(function(){
			var text = $( "#searchInput" ).val();
			showBooks(STORE.searchUrl.concat(text));
		});
		
		$( "#searchForm" ).submit(function( event ) {
			var text = $( "#searchInput" ).val();
			showBooks(STORE.searchUrl.concat(text));
			event.preventDefault();
		});
		

		
	});
	

	
</script>

<title>Chris Book Store</title>
</head>
<body>


	


	<div class="container">

		<nav
			class="navbar navbar-toggleable-md navbar-inverse fixed-top bg-inverse">
			<button class="navbar-toggler navbar-toggler-right" type="button"
				data-toggle="collapse" data-target="#navbarsExampleDefault"
				aria-controls="navbarsExampleDefault" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<a class=" navbar-brand title" href="#">Chris Book Store</a>

			<div class="collapse navbar-collapse " id="navbarsExampleDefault">

				<form id="searchFrom" class="form-inline mr-auto">
					<input id="searchInput"  class="form-control mr-sm-2" type="text"
						placeholder="Search">
					<button id="searchButton"  class="btn btn-outline-primary my-2 my-sm-0" type="button">Search</button>
				</form>

				<ul class="navbar-nav my-2 my-lg-0">
					<li class="nav-item active" style="margin:0px 10px;">					
								<div id="LoadingImageUser"  style="display: none;">
							  		<img src="<c:url value="img/ajax-loader.gif" />"/>
								</div>
								<div class="row"  id = "userViews"></div>	
					</li>

				</ul>

			</div>
		</nav>

		<div class="blank"></div>

		<div class="container">
			<div class="row justify-content-md-center">
				<div id="LoadingImage"  style="display: none;margin-bottom:5px;">
			  		<img src="<c:url value="img/ajax-loader.gif" />" />
				</div>
			</div>

			<div class="row"  id = "booksViews">
			</div>
		</div>

	</div>
	
	
	



	

	
	
	
</body>
</html>
