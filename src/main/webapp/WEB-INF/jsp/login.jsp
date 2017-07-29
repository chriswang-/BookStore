<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
	

			<div class="container">
					<div class="form-group ${name ne null ? 'has-danger' : ''} ">
					  <label class="form-control-label " for="username">User Name</label>
					  <input type="text" class="form-control " name="username" placeholder="eg:user">
					  			<c:if test="${name ne null}">
									<div class="form-control-feedback alert-danger">Invalid username.</div>
								</c:if>
					</div>
					
					<div class="form-group ${password ne null ? 'has-danger' : ''} ">
					  <label class="form-control-label" for="password">Password</label>
					  <input type="text" class="form-control " name="password" placeholder="eg:user">
	  					  			<c:if test="${password ne null}">
										<div class=" form-control-feedback alert-danger">Invalid password.</div>
									</c:if>
					</div>
			</div>		
	
	
	