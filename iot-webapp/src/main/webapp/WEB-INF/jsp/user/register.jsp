
<%@ include file="/WEB-INF/template/includes.jsp" %>
<c:if test="${not empty errorMessage}">
    <div class="alert alert-danger">
            ${errorMessage}
    </div>
</c:if>
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
                        class="col-sm-3 form-control"
                        id = "password"
                        placeholder= "Password"/>
            <div class="has-error">
                <form:errors path="password" class="help-inline"/>
            </div>
        </div>
    </div>
    <div class="form-group">
        <!-- password -->
        <label for="password" class="col-sm-3 control-label">Repeat password</label>
        <div class="col-sm-5">
            <form:input path="matchingPassword"
                        type="password"
                        class="form-control"
                        id = "matchingPassword"
                        placeholder= "Repeat password"/>
            <div class="has-error">
                <form:errors path="matchingPassword" class="help-inline"/>
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