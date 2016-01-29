<%-- 
    Document   : carChoise
    Created on : Jan 16, 2016, 2:15:17 PM
    Author     : Admin
--%>

<%@include file="../layout/mainMenu.jsp" %>
<%@include file="../layout/clientMenu.jsp" %>
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
    <title><fmt:message key="personal.rents"/></title>
</head>
<body>

<h1><fmt:message key="personal.rents"/></h1>

<br>
<table border="1" bgcolor="#deb887">
    <tr>
        <td><fmt:message key="rent.id"/></td>
        <td><fmt:message key="rent.carOrder"/></td>
        <td><fmt:message key="rent.car"/></td>
        <td><fmt:message key="rent.status"/></td>
    </tr>
    <c:forEach var="rent" items="${rents}">
        <tr>
            <td>${rent.id}</td>
            <td>${rent.carOrder}</td>

            <c:choose>
                <c:when test="${locale == 'uk'}">
                    <td>${rent.car.id}, ${rent.car.modelUK}</td>
                    <td>${rent.status.statusUK}</td>
                </c:when>
                <c:otherwise>
                    <td>${rent.car.id}, ${rent.car.modelEN}</td>
                    <td>${rent.status.statusEN}</td>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
</table>
</body>
</html>
