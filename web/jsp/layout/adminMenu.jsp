<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 26.01.2016
  Time: 18:25
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="locale" value="${not empty locale ? locale : pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="property.text"/>

<div class="sub-menu">
    <div class="sm_item">
        <ul id="yw1">
            <li>
                <form action="handler" method="post">
                    <input type="hidden" name="act" value="orders"/>
                    <input type="submit" value=<fmt:message key="button.orders"/>>
                </form>
            </li>
            <li>
                <form action="handler" method="post">
                    <input type="hidden" name="act" value="payments"/>
                    <input type="submit" value=<fmt:message key="button.payments"/>>
                </form>
            </li>
            <li>
                <form action="handler" method="post">
                    <input type="hidden" name="act" value="clients"/>
                    <input type="submit" value=<fmt:message key="button.clients"/>>
                </form>
            </li>
            <li>
                <form action="handler" method="post">
                    <input type="hidden" name="act" value="cars"/>
                    <input type="submit" value=<fmt:message key="button.cars"/>>
                </form>
            </li>
        </ul>
    </div>
</div>