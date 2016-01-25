<%--
  Created by IntelliJ IDEA.
  User: jakub
  Date: 06.01.16
  Time: 22:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Register</title>
    <!-- All the files that are required -->
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <link href='http://fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/login_forms.css">
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.13.1/jquery.validate.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

  <!-- REGISTRATION FORM -->
  <div class="text-center" style="padding:50px 0">
    <div class="logo">register</div>
    <!-- Main Form -->
    <div class="login-form-1">
      <form:form id="register-form"
                 class="text-left"
                 method="POST"
                 modelAttribute="userDTO">
        <div class="login-form-main-message"></div>
        <div class="main-login-form">
          <div class="login-group">
            <div class="form-group">
              <label for="reg_username" class="sr-only">Email address</label>
              <form:input path="username"
                          type="text"
                          class="form-control"
                          id="reg_username"
                          name="reg_username"
                          placeholder="username"/>
                <div class="has-error">
                    <form:errors path="username" class="help-inline"/>
                </div>
            </div>
            <div class="form-group">
              <label for="reg_password" class="sr-only">Password</label>
              <form:input path="password"
                          type="password"
                          class="form-control"
                          id="reg_password"
                          name="reg_password"
                          placeholder="password"/>
                <div class="has-error">
                    <form:errors path="password" class="help-inline"/>
                </div>
            </div>
            <div class="form-group">
              <label for="reg_password_confirm" class="sr-only">Password Confirm</label>
              <form:input path="matchingPassword"
                          type="password"
                          class="form-control"
                          id="reg_password_confirm"
                          name="reg_password_confirm"
                          placeholder="confirm password"/>
                <div class="has-error">
                    <form:errors path="matchingPassword" class="help-inline"/>
                </div>
            </div>

            <div class="form-group">
              <label for="reg_email" class="sr-only">Email</label>
              <form:input path="email"
                          type="text"
                          class="form-control"
                          id="reg_email"
                          name="reg_email"
                          placeholder="email"/>
                <div class="has-error">
                    <form:errors path="email" class="help-inline"/>
                </div>
            </div>
            <div class="form-group">
              <label for="reg_firstname" class="sr-only">First Name</label>
              <form:input path="firstName"
                          type="text"
                          class="form-control"
                          id="reg_firstname"
                          name="reg_firstname"
                          placeholder="first name"/>
                <div class="has-error">
                    <form:errors path="firstName" class="help-inline"/>
                </div>
            </div>
            <div class="form-group">
              <label for="reg_lastname" class="sr-only">Last Name</label>
              <form:input path="lastName"
                          type="text"
                          class="form-control"
                          id="reg_lastname"
                          name="reg_lastname"
                          placeholder="last name"/>
                <div class="has-error">
                    <form:errors path="lastName" class="help-inline"/>
                </div>
            </div>

            <div class="form-group login-group-checkbox">
              <input type="checkbox" class="" id="reg_agree" name="reg_agree">
              <label for="reg_agree">i agree with <a href="#">terms</a></label>
            </div>
          </div>
          <button type="submit" class="login-button"><i class="fa fa-chevron-right"></i></button>
        </div>
        <div class="etc-login-form">
          <p>already have an account? <a href="#">login here</a></p>
        </div>
      </form:form>
    </div>
    <!-- end:Main Form -->
</div>
</body>
</html>
