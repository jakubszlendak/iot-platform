<%--
  Created by IntelliJ IDEA.
  User: jakub
  Date: 06.01.16
  Time: 12:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title></title>
  <!-- All the files that are required -->
  <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
  <link href='http://fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/login_forms.css">
  <script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.13.1/jquery.validate.min.js"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
</head>
<body>

    <c:url value="/j_spring_security_check" var="loginUrl" />
<!-- Where all the magic happens -->
  <!-- LOGIN FORM -->
  <div class="text-center" style="padding:50px 0">
    <div class="logo">login</div>
      <c:if test="${not empty error}">
          <div class="alert alert-danger">${error}</div>
      </c:if>
      <c:if test="${not empty msg}">
          <div class="alert alert-success">${msg}</div>
      </c:if>
    <!-- Main Form -->
    <div class="login-form-1">
      <form id="login-form" class="text-left" action="${loginUrl}" method="post">
        <div class="login-form-main-message"></div>
        <div class="main-login-form">
          <div class="login-group">
            <div class="form-group">
              <label for="lg_username" class="sr-only">Username</label>
              <input type="text" class="form-control" id="lg_username" name="username" placeholder="username">
            </div>
            <div class="form-group">
              <label for="lg_password" class="sr-only">Password</label>
              <input type="password" class="form-control" id="lg_password" name="password" placeholder="password">
            </div>
            <div class="form-group login-group-checkbox">
              <input type="checkbox" id="lg_remember" name="lg_remember">
              <label for="lg_remember">remember</label>
            </div>
          </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
          <button type="submit" class="login-button"><i class="fa fa-chevron-right"></i></button>
        </div>
        <div class="etc-login-form">
          <p>forgot your password? <a href="#">click here</a></p>
          <p>new user? <a href="#">create new account</a></p>
        </div>
      </form>
    </div>
    <!-- end:Main Form -->
  </div>


  <!-- FORGOT PASSWORD FORM -->
  <div class="text-center" style="padding:50px 0">
    <div class="logo">forgot password</div>
    <!-- Main Form -->
    <div class="login-form-1">
      <form id="forgot-password-form" class="text-left">
        <div class="etc-login-form">
          <p>When you fill in your registered email address, you will be sent instructions on how to reset your password.</p>
        </div>
        <div class="login-form-main-message"></div>
        <div class="main-login-form">
          <div class="login-group">
            <div class="form-group">
              <label for="fp_email" class="sr-only">Email address</label>
              <input type="text" class="form-control" id="fp_email" name="fp_email" placeholder="email address">
            </div>
          </div>
          <button type="submit" class="login-button"><i class="fa fa-chevron-right"></i></button>
        </div>
        <div class="etc-login-form">
          <p>already have an account? <a href="#">login here</a></p>
          <p>new user? <a href="#">create new account</a></p>
        </div>
      </form>
    </div>
  </body>
</html>
