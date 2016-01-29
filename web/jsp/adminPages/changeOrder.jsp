<%-- 
    Document   : carChoise
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
    <title><fmt:message key="title.changeOrder"/></title>
</head>
<body>


<h1><fmt:message key="title.changeOrder"/></h1>

<br>
<h3><fmt:message key="order"/></h3>
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
    <tr>
        <form action="handler" method="post">
            <td>${order.id}</td>
            <c:choose>
                <c:when test="${locale == 'uk'}">
                    <td>${order.client.nameSurnameUK}</td>
                    <td>${order.carGroup.id}</td>
                    <td>${order.startDate}</td>
                    <td>${order.endDate}</td>
                    <td>${order.startPlace.cityUK}, ${order.startPlace.addressUK}</td>
                    <td>${order.endPlace.cityUK}, ${order.endPlace.addressUK}</td>
                </c:when>
                <c:otherwise>
                    <td>${order.client.nameSurnameEN}</td>
                    <td>${order.carGroup.id}</td>
                    <td>${order.startDate}</td>
                    <td>${order.endDate}</td>
                    <td>${order.startPlace.cityEN}, ${order.startPlace.addressEN}</td>
                    <td>${order.endPlace.cityEN}, ${order.endPlace.addressEN}</td>
                </c:otherwise>
            </c:choose>
            <td>
                <select name="statusId">
                    <c:forEach var="statusNew" items="${statuses}">
                        <c:choose>
                            <c:when test="${statusNew.id == order.status.id}">
                                <option selected value="${statusNew.id}">

                                    <c:choose>
                                        <c:when test="${locale == 'uk'}">
                                            ${statusNew.statusUK}
                                        </c:when>
                                        <c:otherwise>
                                            ${statusNew.statusEN}
                                        </c:otherwise>
                                    </c:choose>

                                </option>
                            </c:when>
                            <c:otherwise>
                                <option value="${statusNew.id}">

                                    <c:choose>
                                        <c:when test="${locale == 'uk'}">
                                            ${statusNew.statusUK}
                                        </c:when>
                                        <c:otherwise>
                                            ${statusNew.statusEN}
                                        </c:otherwise>
                                    </c:choose>

                                </option>
                            </c:otherwise>
                        </c:choose>

                    </c:forEach>
                </select>
            </td>
            <td>
                <input type="text" name="comment" value="${order.comment}"/>
            </td>
            <td>
                <input type="hidden" name="act" value="changeOrderStatus"/>
                <input type="submit" value=<fmt:message key="button.changeOrderStatus"/>>
            </td>
        </form>
    </tr>
</table>

<br>
<h3><fmt:message key="payments"/></h3>
<table border="1" bgcolor="#deb887">
    <tr>
        <td><fmt:message key="payment.id"/></td>
        <td><fmt:message key="payment.carOrder"/></td>
        <td><fmt:message key="payment.price"/></td>
        <td><fmt:message key="payment.name"/></td>
        <td><fmt:message key="payment.paid"/></td>
    </tr>
    <c:forEach var="payment" items="${payments}">
        <tr>
            <form action="handler" method="post">
                <td>${payment.id}</td>
                <td>${payment.carOrder}</td>
                <td>${payment.price}</td>
                <td>
                    <select name="paymentNameId">
                        <c:forEach var="paymentName" items="${paymentNames}">
                            <c:choose>
                                <c:when test="${paymentName.id == payment.paymentName.id}">
                                    <option selected value="${paymentName.id}">

                                        <c:choose>
                                            <c:when test="${locale == 'uk'}">
                                                ${paymentName.nameUK}
                                            </c:when>
                                            <c:otherwise>
                                                ${paymentName.nameEN}
                                            </c:otherwise>
                                        </c:choose>

                                    </option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${paymentName.id}">

                                        <c:choose>
                                            <c:when test="${locale == 'uk'}">
                                                ${paymentName.nameUK}
                                            </c:when>
                                            <c:otherwise>
                                                ${paymentName.nameEN}
                                            </c:otherwise>
                                        </c:choose>

                                    </option>
                                </c:otherwise>
                            </c:choose>

                        </c:forEach>
                    </select>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${payment.paid}">
                            <input type="checkbox" name="paidStatus" checked/>
                        </c:when>
                        <c:otherwise>
                            <input type="checkbox" name="paidStatus"/>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <input type="hidden" name="paymentId" value="${payment.id}"/>
                    <input type="hidden" name="act" value="changePayment"/>
                    <input type="submit" value=<fmt:message key="button.change"/>>
                </td>
            </form>
        </tr>
    </c:forEach>
</table>

<c:choose>
    <c:when test="${rent != null}">
        <br>
        <h3><fmt:message key="rent"/></h3>
        <table border="1" bgcolor="#deb887">
            <tr>
                <td><fmt:message key="rent.id"/></td>
                <td><fmt:message key="rent.carOrder"/></td>
                <td><fmt:message key="rent.car"/></td>
                <td><fmt:message key="rent.status"/></td>
            </tr>
            <tr>
                <form action="handler" method="post">
                    <td>${rent.id}</td>
                    <td>${rent.carOrder}</td>

                    <c:choose>
                        <c:when test="${locale == 'uk'}">
                            <td>${rent.car.id}, ${rent.car.modelUK}</td>
                        </c:when>
                        <c:otherwise>
                            <td>${rent.car.id}, ${rent.car.modelEN}</td>
                        </c:otherwise>
                    </c:choose>

                    <td>
                        <select name="rentStatusId">
                            <c:forEach var="statusNew" items="${rentStatuses}">
                                <c:choose>
                                    <c:when test="${statusNew.id == rent.status.id}">
                                        <option selected value="${statusNew.id}">

                                            <c:choose>
                                                <c:when test="${locale == 'uk'}">
                                                    ${statusNew.statusUK}
                                                </c:when>
                                                <c:otherwise>
                                                    ${statusNew.statusEN}
                                                </c:otherwise>
                                            </c:choose>

                                        </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${statusNew.id}">

                                            <c:choose>
                                                <c:when test="${locale == 'uk'}">
                                                    ${statusNew.statusUK}
                                                </c:when>
                                                <c:otherwise>
                                                    ${statusNew.statusEN}
                                                </c:otherwise>
                                            </c:choose>

                                        </option>
                                    </c:otherwise>
                                </c:choose>

                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <input type="hidden" name="act" value="changeRentStatus"/>
                        <input type="submit" value=<fmt:message key="button.changeRentStatus"/>>
                    </td>
                </form>
            </tr>
        </table>
    </c:when>
    <c:otherwise>
        <br><br>
        <h3><fmt:message key="rent.giveCar"/></h3>
        <form action="handler" method="post">
            <input type="hidden" name="act" value="chooseCar"/>
            <input type="submit" value=<fmt:message key="button.chooseCar"/>>
        </form>
    </c:otherwise>
</c:choose>

</body>
</html>
