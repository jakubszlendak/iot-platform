<%@ include file="/WEB-INF/template/includes.jsp" %>

    <c:url value="/j_spring_security_check" var="loginUrl" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/user/signin.css">
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
    <c:if test="${not empty msg}">
        <div class="alert alert-success">${msg}</div>
    </c:if>
    <form:form class="form-signin" method="post" action="${loginUrl}" >
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="idUsername" class="sr-only">Email address</label>
        <input type="text" id="idUsername" name="username" class="form-control" placeholder="Username" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> Remember me
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form:form>