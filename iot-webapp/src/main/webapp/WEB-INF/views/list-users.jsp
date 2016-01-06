<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">         
		<script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>List users</title>
		<c:url value="/" var="homepage"/>
	</head>
	<body>
		<div class="container">
			<h2>Users</h2>
			<h4>Search:</h4>
			<%--Search form--%>
			<form class="form-inline"
				  action="/users"
				  method="GET"
				  id="userSearchForm"
				  role="form" >
				<div class="form-group">
					<label for="username">Username:</label>
					<input type="text" name="username" id="username" class="form-control" />
				</div>
				<div class="form-group">
					<label for="email">Email:</label>
					<input type="email" name="email" id="email" class="form-control"/>
				</div>
				<button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"/> Search</button>
			</form>

			<%--report message--%>
			<c:if test="${not empty message}">
				<div class="alert alert-success">
					${message}
				</div>
			</c:if>
			<%--result table--%>
			<form action="/users" method="POST" id="userListControls" role="form">
				<input type="hidden" id="idUser" name="idUser">
				<input type="hidden" id="action" name="action">
				<c:choose>
					<c:when test="${not empty usersList}">
						<table class="table table-responsive">
							<thead>
								<tr>
									<td>#</td>
									<td>Username</td>
									<td>Name</td>
									<td>Last Name</td>
									<td>Email</td>
									<td></td>
								</tr>
							</thead>
							<c:forEach var="user" items="${usersList}" varStatus="status">
								<tr>
									<td>${user.ID}</td>
									<td>${user.username}</td>
									<td>${user.firstName}</td>
									<td>${user.lastName}</td>
									<td>${user.email}</td>
									<td><a href="#" id="remove"
											onclick="
												document.getElementById('action').value='remove';
												document.getElementById('idUser').value='${user.ID}';
												document.getElementById('userListControls').submit();">
										<span class="glyphicon glyphicon-remove"/>
									</a></td>
								</tr>
							</c:forEach>
						</table>
					</c:when>
					<c:otherwise>
						<div class="alert alert-info"> No users found matching given criteria</div>
					</c:otherwise>
				</c:choose>
			</form>
		</div>
	</body>
</html>