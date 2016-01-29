<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 17.01.2016
  Time: 14:06
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="locale" value="${not empty locale ? locale : pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="property.text"/>

<c:set var="path" value="/jsp/index.jsp" scope="session"/>

<html>
<head>
    <title><fmt:message key="title.rentACar"/></title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" media="screen" href="/css/main.css">
</head>
<body>
<%@include file="layout/mainMenu.jsp" %>
<h1 class="text">
    <div class="b-body"><fmt:message key="home.welcome"/></div>
</h1>
</body>
</html>
