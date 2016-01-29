<%-- 
    Document   : carChoise
    Created on : Jan 16, 2016, 2:15:17 PM
    Author     : Admin
--%>

<%@include file="../layout/mainMenu.jsp" %>
<%@include file="../layout/adminMenu.jsp"%>
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
    <title><fmt:message key="admin.clients"/></title>
</head>
<body>


<br>

<h1><fmt:message key="admin.clients"/></h1>
<br>

<form action="handler" method="post">
    <input type="text" name="search" value=""/>
    <input type="hidden" name="act" value="searchClients"/>
    <input type="submit" value=<fmt:message key="button.search"/>>
</form>

<form action="handler" method="post">
    <input type="hidden" name="act" value="clients"/>
    <input type="submit" value=<fmt:message key="button.allClients"/>>
</form>


<br>
<table border="1" bgcolor="#deb887">
    <tr>
        <td><fmt:message key="client.id"/></td>
        <td><fmt:message key="client.nameSurname"/></td>
        <td><fmt:message key="client.mail"/></td>
        <td><fmt:message key="client.driverLicense"/></td>
        <td><fmt:message key="client.telephone"/></td>
        <td><fmt:message key="client.admin"/></td>
    </tr>
    <c:forEach var="client" items="${clients}">
        <tr>
            <form action="handler" method="post">
                <td>${client.id}</td>

                <c:choose>
                    <c:when test="${locale == 'uk'}">
                        <td>${client.nameSurnameUK}</td>
                    </c:when>
                    <c:otherwise>
                        <td>${client.nameSurnameEN}</td>
                    </c:otherwise>
                </c:choose>

                <td>${client.mail}</td>
                <td>${client.driverLicense}</td>
                <td>${client.telephone}</td>
                <td>
                    <c:choose>
                        <c:when test="${client.admin}">
                            <input type="checkbox" name="admin" checked/>
                        </c:when>
                        <c:otherwise>
                            <input type="checkbox" name="admin"/>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <input type="hidden" name="clientId" value="${client.id}"/>
                    <input type="hidden" name="act" value="makeAdmin"/>
                    <input type="submit" value=<fmt:message key="button.makeAdmin"/>>
                </td>
            </form>
        </tr>
    </c:forEach>
</table>

</body>
</html>
