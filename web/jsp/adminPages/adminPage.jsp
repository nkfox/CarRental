<%-- 
    Document   : adminPage
    Created on : Jan 16, 2016, 2:15:17 PM
    Author     : Admin
--%>

<%@include file="../layout/mainMenu.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page session="true" %>

<c:set var="locale" value="${not empty locale ? locale : pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="property.text"/>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" media="screen" href="/css/main.css">
    <title><fmt:message key="title.adminPage"/></title>
</head>

<body>
<h1><fmt:message key="title.adminPage"/></h1>
<%@include file="../layout/adminMenu.jsp"%>
</body>
</html>
