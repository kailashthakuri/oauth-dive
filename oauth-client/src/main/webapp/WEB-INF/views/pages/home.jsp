<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="RESOURCES_PATH" value="${pageContext.request.contextPath}/resources"/>
<html lang="en">
<head>
    <!-- Access the bootstrap Css like this,
        Spring boot will handle the resource mapping automcatically -->
    <link rel="stylesheet" type="text/css" href="${RESOURCES_PATH}/bootstrap/css/bootstrap.min.css"/>
    <!--
	<%--<spring:url value="/css/main.css" var="springCss" />--%>
	<link href="${springCss}" rel="stylesheet" />
	 -->
    <%--<c:url value="/css/main.css" var="jstlCss" />--%>
    <%--<link href="${jstlCss}" rel="stylesheet" />--%>
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Spring Boot</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="#about">About</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <div class="starter-template">
        <h3>Spring Boot OAuth Example</h3>
        <h4>Login with:</h4>
        <p>
            <a href="${authUri}">Google</a>
        </p>
    </div>
</div>
<script type="text/javascript" src="${RESOURCES_PATH}/js/jquery-3.5.0.js"></script>
<script type="text/javascript" src="${RESOURCES_PATH}/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>