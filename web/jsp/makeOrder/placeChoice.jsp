<%-- 
    Document   : dateChoise
    Created on : Jan 16, 2016, 10:58:27 AM
    Author     : Admin
--%>

<%@include file="../layout/mainMenu.jsp" %>
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
    <title><fmt:message key="title.choosePlace"/></title>
</head>
<body>

<div><fmt:message key="selected.period"/></div>
<br>
<input type=date name="startDate" readonly="readonly" value= ${startDate}>
<input type=date name="endDate" readonly="readonly" value= ${endDate}>
<br><br>

<form action="handler" method="post">

    <fmt:message key="place.choosePlace"/><br>

    <fmt:message key="place.from"/>
    <select name="selectStartPlace">
        <c:forEach items="${allPlaces}" var="place">
            <c:set var="city" value="${place.cityEN}"/>
            <c:set var="address" value="${place.addressEN}"/>
            <c:if test="${locale == 'uk'}">
                <c:set var="city" value="${place.cityUK}"/>
                <c:set var="address" value="${place.addressUK}"/>
            </c:if>

            <option value="${place.id}"> ${city}, ${address}</option>
        </c:forEach>
    </select><br>

    <fmt:message key="place.to"/>
    <select name="selectEndPlace">
        <c:forEach items="${allPlaces}" var="place">
            <c:set var="city" value="${place.cityEN}"/>
            <c:set var="address" value="${place.addressEN}"/>
            <c:if test="${locale == 'uk'}">
                <c:set var="city" value="${place.cityUK}"/>
                <c:set var="address" value="${place.addressUK}"/>
            </c:if>

            <option value="${place.id}"> ${city}, ${address}</option>
        </c:forEach>
    </select>

    <br>
    <input type="hidden" name="act" value="placeChoice"/>
    <input type="submit" value=<fmt:message key="button.next"/>>
</form>
</body>
</html>
