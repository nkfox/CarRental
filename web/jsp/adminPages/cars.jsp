<%-- 
    Document   : cars
    Created on : Jan 16, 2016, 2:15:17 PM
    Author     : Admin
--%>

<%@include file="../layout/mainMenu.jsp" %>
<%@include file="../layout/adminMenu.jsp" %>
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
    <title><fmt:message key="admin.cars"/></title>
</head>
<body>


<br>

<h1><fmt:message key="admin.cars"/></h1>
<br>

<form action="handler" method="post">
    <input type="text" name="search" value=""/>
    <input type="hidden" name="act" value="searchCars"/>
    <input type="submit" value=<fmt:message key="button.search"/>>
</form>

<form action="handler" method="post">
    <input type="hidden" name="act" value="cars"/>
    <input type="submit" value=<fmt:message key="button.allCars"/>>
</form>

<table border="1" bgcolor="#deb887">
    <tr>
        <td><fmt:message key="car.id"/></td>
        <td><fmt:message key="car.model"/></td>
        <td><fmt:message key="car.carGroup"/></td>
        <td><fmt:message key="car.place"/></td>
        <td><fmt:message key="car.carState"/></td>
    </tr>
    <c:forEach var="car" items="${cars}">
        <tr>
            <form action="handler" method="post">
                <td>${car.id}</td>

                <c:choose>
                    <c:when test="${locale == 'uk'}">
                        <td>${car.modelUK}</td>
                        <td>${car.carGroup}</td>
                        <td>${car.place.cityUK}, ${car.place.addressUK}</td>
                    </c:when>
                    <c:otherwise>
                        <td>${car.modelEN}</td>
                        <td>${car.carGroup}</td>
                        <td>${car.place.cityEN}, ${car.place.addressEN}</td>
                    </c:otherwise>
                </c:choose>

                <td>
                    <select name="carStateId">
                        <c:forEach var="state" items="${states}">
                            <c:choose>
                                <c:when test="${state.id == car.state.id}">
                                    <option selected value="${state.id}">

                                        <c:choose>
                                            <c:when test="${locale == 'uk'}">
                                                ${state.stateUK}
                                            </c:when>
                                            <c:otherwise>
                                                ${state.stateEN}
                                            </c:otherwise>
                                        </c:choose>

                                    </option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${state.id}">

                                        <c:choose>
                                            <c:when test="${locale == 'uk'}">
                                                ${state.stateUK}
                                            </c:when>
                                            <c:otherwise>
                                                ${state.stateEN}
                                            </c:otherwise>
                                        </c:choose>

                                    </option>
                                </c:otherwise>
                            </c:choose>

                        </c:forEach>
                    </select>
                </td>
                <td>
                    <input type="hidden" name="carId" value="${car.id}"/>
                    <input type="hidden" name="act" value="changeCarState"/>
                    <input type="submit" value=<fmt:message key="button.change"/>>
                </td>
            </form>
        </tr>
    </c:forEach>
</table>
</body>
</html>
