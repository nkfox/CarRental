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

<c:set var="path" value="/jsp/makeOrder/dateChoice.jsp" scope="session"/>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" media="screen" href="/css/main.css">
    <title><fmt:message key="date.choose"/></title>
</head>
<body>
<form action="handler" method="post">
        <p><fmt:message key="date.startDate"/> <input type=date name="startDate" id=today></p>
        <script>
            document.getElementById('today').value = new Date().toISOString().substring(0, 10);
        </script>
        <p><fmt:message key="date.endDate"/> <input type=date name="endDate" id=today2></p>
        <script>
            document.getElementById('today2').value = new Date().toISOString().substring(0, 10);
        </script>
    <input type="hidden" name="act" value="dateChoice"/>
    <input type="submit" value=<fmt:message key="button.next"/>>
</form>
</body>
</html>
