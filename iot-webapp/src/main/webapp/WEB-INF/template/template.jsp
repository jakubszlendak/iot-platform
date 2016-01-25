<%--
  Created by IntelliJ IDEA.
  User: jakub
  Date: 25.01.16
  Time: 00:22
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/template/includes.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta name="google-site-verification" content="xxxxxxxxx" />
  <title><tiles:insertAttribute name="title" ignore="true"/></title>
  <meta name="description" content="<tiles:insertAttribute name="page_description" ignore="true"/>">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/dashboard.css">

  <script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
  <meta charset="utf-8">
  <%--<meta property="og:image" content="<tiles:insertAttribute name="og_image" ignore="true"/>" />--%>
  <%--<meta property="og:title" content="<tiles:insertAttribute name="og_title" ignore="true"/>" />--%>
  <%--<meta property="og:description" content="<tiles:insertAttribute name="og_desc" ignore="true"/>"/>--%>
  <%--<link rel="stylesheet" href="<tiles:insertAttribute name="jquery_ui_css" ignore="true"/>" />--%>
</head>
<body id="<tiles:insertAttribute name="body-id"/> ">
  <nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
      <tiles:insertAttribute name="navbar-tile" />
    </div>
  </nav>

  <div class="container-fluid">
    <div class="row">
      <div class="col-sm-3 col-md-2 sidebar">
        <tiles:insertAttribute name="sidebar-tile" />
      </div>


      <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        <tiles:insertAttribute name="content-tile" />
      </div>
    </div>
  </div>

</body>
</html>
