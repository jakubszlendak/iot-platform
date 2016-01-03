<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
	<head>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">         
		<script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>  
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Register</title>
		<style>
			.jumbotron {
				text-align: center;
			}
		</style>
		
		<c:url value="/" var="homepage"/>
	</head>
	<body>
		<div class="container">
			<div class="jumbotron">
				<h1> User added successfully</h1>
				<p class="lead"> User of name <span class="text-primary">${userName}</span> and email <span class="text-primary">${userEmail}</span> was added to database.</p>
				<p><a class="btn btn-lg btn-success" href="$homepage" role="button">Back to homepage</a>
			</div>
		</div>
	</body>
</html>