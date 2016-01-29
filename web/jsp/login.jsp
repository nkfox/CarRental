<%-- 
    Document   : carChoise
    Created on : Jan 16, 2016, 2:15:17 PM
    Author     : Admin
--%>

<%@include file="layout/mainMenu.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="locale" value="${not empty locale ? locale : pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="property.text"/>

<c:set var="path" value="/jsp/login.jsp" scope="session"/>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" media="screen" href="/css/main.css">
    <title><fmt:message key="title.login"/></title>
</head>
<body>

<h2 class="text">
    <div class="b-body"><fmt:message key="title.login"/></div>
</h2>

<form action="handler" method="post">
    <div><fmt:message key="client.passport"/></div>
    <br>
    <input type="text" name="id" value=""/><br><br>

    <div><fmt:message key="client.password"/></div>
    <br>
    <input type="password" name="password" value=""/><br><br>

    <c:if test="${info != null}">
        <font color="white"><fmt:message key="login.${info}"/></font><br>
    </c:if>

    <input type="hidden" name="act" value="login"/>
    <input type="submit" value=<fmt:message key="button.submit"/>>
</form>

</body>
</html>
