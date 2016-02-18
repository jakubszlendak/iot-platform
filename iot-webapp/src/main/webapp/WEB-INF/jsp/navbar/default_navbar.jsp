<%@ include file="/WEB-INF/template/includes.jsp" %>


        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">IoT Platform</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="/dashboard">Dashboard</a></li>
                    <c:choose>
                        <c:when test="${empty username}">
                            <li><a href="/login">Sign in</a></li>
                        </c:when>
                        <c:when test="${not empty username}">
                            <li><a href="/dashboard/user/">${username}</a></li>
                            <li><a href="<c:url value="/j_spring_security_logout" />">Logout</a></li>
                        </c:when>
                    </c:choose>

                </ul>
                <form class="navbar-form navbar-right">
                    <input type="text" class="form-control" placeholder="Search device...">
                </form>
            </div>
        </div>