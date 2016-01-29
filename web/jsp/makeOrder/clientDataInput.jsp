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
    <title><fmt:message key="title.enterPersonalData"/></title>
</head>
<body>


<h1><fmt:message key="personalData.input"/></h1>

<table>
    <tr>
        <td>
            <div><fmt:message key="selected.period"/></div>
            <br>
            <input type=date name="startDate" readonly="readonly" value= ${startDate}>
            <input type=date name="endDate" readonly="readonly" value= ${endDate}>

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

            <div><fmt:message key="selected.carGroup"/></div>
            <br>

            <ctg:car-group group="${selectedCarGroup}" lang="${locale}"/>

        </td>
        <td width="200"></td>
        <td>
            <font color="white">
                <form action="handler" method="post">

                    <div><fmt:message key="client.passport"/></div><br>
                    <input type="text" name="id" value="" height="10"/>

                    <div><fmt:message key="message.enterPassword"/></div><br>

                    <div><fmt:message key="client.password"/></div><br>
                    <input type="password" name="password" value="" height="20"/><br><br>

                    <div><fmt:message key="message.orOtherInfo"/></div><br>

                    <div><fmt:message key="client.nameSurname"/></div><br>
                    <input type="text" name="nameSurname" value=""/><br>

                    <div><fmt:message key="client.mail"/></div><br>
                    <input type="text" name="mail" value=""/><br>

                    <div><fmt:message key="client.driverLicense"/></div><br>
                    <input type="text" name="driverLicense" value=""/><br>

                    <div><fmt:message key="client.telephone"/></div><br>
                    <input type="text" name="telephone" value=""/><br><br><br>

                    <input type="hidden" name="act" value="clientDataInput"/>
                    <input type="submit" value=<fmt:message key="button.submit"/>>
                </form>
            </font>
        </td>
    </tr>
</table>

</body>
</html>
