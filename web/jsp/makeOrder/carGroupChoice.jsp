<%-- 
    Document   : carChoise
    Created on : Jan 16, 2016, 2:15:17 PM
    Author     : Admin
--%>

<%@include file="../layout/mainMenu.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="ctg" uri="customtags" %>

<c:set var="locale" value="${not empty locale ? locale : pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="property.text"/>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" media="screen" href="/css/main.css">
    <title><fmt:message key="title.chooseACarGroup"/></title>
</head>
<body>

<div><fmt:message key="selected.period"/></div>
<br>
<input type=date name="startDate" readonly="readonly" value= ${startDate}>
<input type=date name="endDate" readonly="readonly" value= ${endDate}>
<br>

<div><fmt:message key="selected.places"/></div>
<br>

<c:set var="startCity" value="${startPlace.cityEN}"/>
<c:set var="startAddress" value="${startPlace.addressEN}"/>
<c:set var="endCity" value="${endPlace.cityEN}"/>
<c:set var="endAddress" value="${endPlace.addressEN}"/>
<c:if test="${locale == 'uk'}">
    <c:set var="startCity" value="${startPlace.cityUK}"/>
    <c:set var="startAddress" value="${startPlace.addressUK}"/>
    <c:set var="endCity" value="${endPlace.cityUK}"/>
    <c:set var="endAddress" value="${endPlace.addressUK}"/>
</c:if>

<input type=text name="startPlace" readonly="readonly" value="${startCity},${startAddress}">
<input type=text name=" endPlace" readonly="readonly" value="${endCity},${endAddress}">
<br><br>

<h1><fmt:message key="carGroup.choose"/></h1><br>
<table>
    <tr>
        <c:forEach items="${allCarGroups}" var="group">
            <td>
                <form action="handler" method="post">
                    <ctg:car-group group="${group}" lang="${locale}"/>

                    <input type="hidden" name="carGroup" value="${group.id}"/>
                    <input type="hidden" name="act" value="carGroupChoice"/>
                    <input type="submit" value=<fmt:message key="button.selectGroup"/>><br>

                </form>
            </td>
        </c:forEach>
    </tr>
</table>
</body>
</html>
