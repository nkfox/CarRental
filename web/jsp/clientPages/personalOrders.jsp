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
    <title><fmt:message key="personal.orders"/></title>
</head>
<body>


<h1><fmt:message key="personal.orders"/></h1>

<br>
<table border="1" bgcolor="#deb887">
    <tr>
        <td><fmt:message key="order.id"/></td>
        <td><fmt:message key="order.client"/></td>
        <td><fmt:message key="order.carGroup"/></td>
        <td><fmt:message key="order.startDate"/></td>
        <td><fmt:message key="order.endDate"/></td>
        <td><fmt:message key="order.startPlace"/></td>
        <td><fmt:message key="order.endPlace"/></td>
        <td><fmt:message key="order.status"/></td>
        <td><fmt:message key="order.comment"/></td>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <form action="handler" method="post">
                <td>
                    <input type="hidden" name="orderId" value="${order.id}"/>
                        ${order.id}
                </td>

                <c:choose>
                    <c:when test="${locale == 'uk'}">
                        <td>${order.client.nameSurnameUK}</td>
                        <td>${order.carGroup.id}</td>
                        <td>${order.startDate}</td>
                        <td>${order.endDate}</td>
                        <td>${order.startPlace.cityUK}, ${order.startPlace.addressUK}</td>
                        <td>${order.endPlace.cityUK}, ${order.endPlace.addressUK}</td>
                        <td>${order.status.statusUK}</td>
                    </c:when>
                    <c:otherwise>
                        <td>${order.client.nameSurnameEN}</td>
                        <td>${order.carGroup.id}</td>
                        <td>${order.startDate}</td>
                        <td>${order.endDate}</td>
                        <td>${order.startPlace.cityEN}, ${order.startPlace.addressEN}</td>
                        <td>${order.endPlace.cityEN}, ${order.endPlace.addressEN}</td>
                        <td>${order.status.statusEN}</td>
                    </c:otherwise>
                </c:choose>

                <td>${order.comment}</td>
                <td>
                    <input type="hidden" name="act" value="cancelOrder"/>
                    <input type="submit" value=<fmt:message key="button.cancel"/>>
                </td>
            </form>
        </tr>
    </c:forEach>
</table>
</body>
</html>
