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
    <title><fmt:message key="admin.payments"/></title>
</head>
<body>


<br>

<h1><fmt:message key="admin.payments"/></h1>
<br>

<form action="handler" method="post">
    <input type="text" name="search" value=""/>
    <input type="hidden" name="act" value="searchPayments"/>
    <input type="submit" value=<fmt:message key="button.search"/>>
</form>

<form action="handler" method="post">
    <input type="hidden" name="act" value="payments"/>
    <input type="submit" value=<fmt:message key="button.allPayments"/>>
</form>


<br>
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

                <c:choose>
                    <c:when test="${locale == 'uk'}">
                        <td>${payment.paymentName.nameUK}</td>
                    </c:when>
                    <c:otherwise>
                        <td>${payment.paymentName.nameEN}</td>
                    </c:otherwise>
                </c:choose>

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
                    <input type="submit" value=<fmt:message key="button.changePayment"/>>
                </td>
            </form>
        </tr>
    </c:forEach>
</table>

</body>
</html>
