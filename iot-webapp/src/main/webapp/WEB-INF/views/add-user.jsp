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
	</head>
	<body>
		<div class="container">
			<h2>Add new user</h2>
			<form:form 	class="form-horizontal" 
						method="POST" 
						modelAttribute="userDTO"
						role="form">
				<div class="form-group">
					<!-- nickname -->
					<label class="col-sm-3 control-label" for="username">User name</label>
					<div class="col-sm-5">
						<form:input path="username" 
									type="text" 
									class="form-control input-sm"
									id = "username"
									placeholder= "User Name"/>
						 <div class="has-error">
						 	<form:errors path="username" class="help-inline"/>
						 </div>
					</div>
				</div>
				<div class="form-group">
					<!-- password -->
					<label for="password" class="col-sm-3 control-label">Password</label>
					<div class="col-sm-5">
						<form:input path="password" 
									type="password" 
									class="form-control"
									id = "password"
									placeholder= "Password"/>
						 <div class="has-error">
						 	<form:errors path="password" class="help-inline"/>
						 </div>
					</div>
				</div>
				<div class="form-group">
					<!-- email -->
					<label for="email" class="col-sm-3 control-label">Email</label>
					<div class="col-sm-5">
						<form:input path="email" 
									type="email" 
									class="form-control"
									id = "email"
									placeholder= "name@domain.com"/>
						 <div class="has-error">
						 	<form:errors path="email" class="help-inline"/>
						 </div>
					</div>
				</div>
				<div class="form-group"><p class="col-sm-offset-3 col-sm-8">Additional info:</p></div>
				<div class="form-group">
					<!-- first name -->
					<label class="col-sm-3 control-label">First Name</label>
					<div class="col-sm-5">
						<form:input path="firstName" 
									type="text" 
									class="form-control"
									id = "firstName"
									placeholder= "First Name"/>
						 <div class="has-error">
						 	<form:errors path="firstName" class="help-inline"/>
						 </div>
					</div>
				</div>
				<div class="form-group">
					<!-- last name -->
					<label class="col-sm-3 control-label">Last Name</label>
					<div class="col-sm-5">
						<form:input path="lastName" 
									type="text" 
									class="form-control"
									id = "lastName"
									placeholder= "Last Name"/>
						<div class="has-error">
							<form:errors path="lastName" class="help-inline"/>
						</div>
					</div>
				</div>
				<div class="form-group">
				    <div class="col-sm-offset-3 col-sm-5">
				      <button type="submit" class="btn btn-default">Submit</button>
				    </div>
  				</div>
				
			</form:form>
		</div>
	</body>
</html>