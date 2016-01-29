<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 26.01.2016
  Time: 18:25
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="locale" value="${not empty locale ? locale : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="property.text" />


<div class="b-head">
  <ul class="b-menu">
    <li class="b-menu__item">
      <a href="/jsp/index.jsp" class="b-menu__link"><fmt:message key="button.main"/></a>
    </li>
    <li class="b-menu__item">
      <a href="/jsp/makeOrder/dateChoice.jsp" class="b-menu__link"><fmt:message key="button.rentACar"/></a>
    </li>
    <c:choose>
      <c:when test="${client != null}">
        <li class="b-menu__item">
          <a href="/jsp/clientPages/personalPage.jsp" class="b-menu__link"><fmt:message key="button.personalPage"/></a>
        </li>
        <c:if test="${client.admin}">
          <li class="b-menu__item">
            <a href="/jsp/adminPages/adminPage.jsp" class="b-menu__link"><fmt:message key="button.adminPage"/></a>
          </li>
        </c:if>
      </c:when>
      <c:otherwise>
        <li class="b-menu__item">
          <a href="/jsp/login.jsp" class="b-menu__link"><fmt:message key="button.login"/></a>
        </li>
      </c:otherwise>

    </c:choose>
  </ul>
  <ul class="b-menu__languages">
    <li class="b-menu__languages-item">
      <form action="handler" method="post">
        <input type="hidden" name="newLanguage" value="en"/>
        <input type="hidden" name="act" value="changeLanguage"/>
        <input type="submit" value=
        <fmt:message key="button.language.en"/> class="b-menu__languages-link">
      </form>
    </li>
    <li class="b-menu__languages-item">
      <form action="handler" method="post">
        <input type="hidden" name="newLanguage" value="uk"/>
        <input type="hidden" name="act" value="changeLanguage"/>
        <input type="submit" value=
        <fmt:message key="button.language.uk"/> class="b-menu__languages-link">
      </form>
    </li>
  </ul>
</div>