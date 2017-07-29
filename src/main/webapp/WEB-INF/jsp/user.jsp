<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	

	
	<script>
	
	STORE.userLoginUrl = '<spring:url value="users/login/" />';
	
	
	$(function(){
		
		
		function showLogin(iurl){
			$('#loginFormContent').hide();
		    $("#loginFormContentLoading").show();

		    $.ajax({
		         type: "GET",
		         cache: false,
		         url: iurl,
		         success: function(response){
		     	    $("#loginFormContentLoading").fadeOut(600, function(){
			            $('#loginFormContent').html(response).fadeIn('slow');
		     	    });
		         }
		    });	    
		}
		
		$('#loginModal').on('shown.bs.modal', function (event) {
			showLogin(STORE.userLoginUrl);
		});
		
		$('#loginModal').appendTo("body");
		
		$("#loginButton").click(function( event ) {
			var jqxhr = $.ajax( {
		           type: "POST",
		           url: "login",
		           data: $("#loginForm").serialize()
		         } )
			  .done(function() {
	        	   $('#loginModal').modal('hide');
		       		showBooks(STORE.searchUrl);
		    		showUser(STORE.userUrl);
		      })
			  .fail(function(e) {
				  	//console.log(e);
				  	var em = JSON.parse(e.responseText).message;
					showLogin(STORE.userLoginUrl.concat("?error="+em));
			  });
		});	
		
	});
	


	</script>
	
	<!-- Button trigger modal -->
	<button type="button" class="btn btn-primary" data-toggle="modal" id="loginOpener" data-target="#loginModal">
	  	<sec:authorize access="isAuthenticated()">
		    Welcome, <sec:authentication property="principal.username"/>.
		</sec:authorize>
		<sec:authorize access="!isAuthenticated()">
		    Login
		</sec:authorize>
	</button>
	

	
	<!-- Login Modal -->
	<div class="modal" id="loginModal" role="dialog"  tabindex="-1" aria-labelledby="loginModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">

			<form id="loginForm" action="/login" method="post"> 
			
			      <div class="modal-header">
			        <h5 class="modal-title" id="loginModalLabel" >Login</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>		
			      <div class="modal-body" style="min-height:190px;" >
			      			<div class="row justify-content-md-center">
								<div id="loginFormContentLoading"  style="display: none;margin-bottom:5px;">
							  		<img src="<c:url value="img/ajax-loader.gif" />" />
								</div>
							</div>
							<div class="container" id="loginFormContent" >
							</div>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			        <button id="loginButton" type="button" class="btn btn-primary" type="button" type="submit">Login</button>
			      </div>					
			
			</form>
	      

	    </div>
	  </div>
	</div>