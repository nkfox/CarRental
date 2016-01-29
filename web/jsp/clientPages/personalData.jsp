<%-- 
    Document   : carChoise
    Created on : Jan 16, 2016, 2:15:17 PM
    Author     : Admin
--%>

<%@include file="../layout/mainMenu.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="locale" value="${not empty locale ? locale : pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="property.text"/>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" media="screen" href="/css/main.css">
    <title><fmt:message key="title.enterPersonalData"/></title>
</head>

<body>
<%@include file="../layout/clientMenu.jsp" %>

<h1><fmt:message key="personalData.input"/></h1>

<form action="handler" method="post">
    <input type="text" name="id" value="${client.id}" readonly="readonly"/><br>

    <div><fmt:message key="client.nameSurnameEN"/></div>
    <br>
    <input type="text" name="nameSurnameEN" value="${client.nameSurnameEN}"/><br>

    <div><fmt:message key="client.nameSurnameUK"/></div>
    <br>
    <input type="text" name="nameSurnameUK" value="${client.nameSurnameUK}"/><br>

    <div><fmt:message key="client.mail"/></div>
    <br>
    <input type="text" name="mail" value="${client.mail}"/><br>

    <div><fmt:message key="client.driverLicense"/></div>
    <br>
    <input type="text" name="driverLicense" value="${client.driverLicense}"/><br>

    <div><fmt:message key="client.telephone"/></div>
    <br>
    <input type="text" name="telephone" value="${client.telephone}"/><br>

    <input type="hidden" name="act" value="changePersonalData"/>
    <input type="submit" value=<fmt:message key="button.save"/>>
    <c:if test="${personalDataChange != null}">
        <font color="white"><fmt:message key="personalDataChange.${personalDataChange}"/></font>
    </c:if>

</form>
<br><br>

<form action="handler" method="post">
    <div><fmt:message key="client.oldPassword"/></div>
    <br>
    <input type="password" name="oldPassword" value=""/><br>

    <div><fmt:message key="client.newPassword"/></div>
    <br>
    <input type="password" name="newPassword" value=""/><br>

    <div><fmt:message key="client.repeatNewPassword"/></div>
    <br>
    <input type="password" name="repeatedPassword" value=""/><br>

    <input type="hidden" name="act" value="changePassword"/>
    <input type="submit" value=<fmt:message key="button.changePassword"/>>
    <c:if test="${passChanged != null}">
        <font color="white"><fmt:message key="passwordChange.${passChanged}"/></font>
    </c:if>
</form>

</body>
</html>
