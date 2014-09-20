<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--
  Created by IntelliJ IDEA.
  User: Ann
  Date: 20.08.2014
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>

<c:set var="localeStr" value="${ not empty sessionScope['localeStr']  ? sessionScope['localeStr'] : initParam['localeStr']}"/>
<fmt:setLocale value="${localeStr}" />
<fmt:setBundle basename="resources.text" />

<c:set var="oldFrom" scope="page" value="${sessionScope.from}">
</c:set>

<%--
в сессию устанавливается параметр from - текущая страница, с которой будет переход.
в request param (устанавливается при необходимости возврата на предыдущую страницу)
          в это время хранится параметр from - страница,
          с которой был выполнен переход на текущую
--%>
<c:set var="from" scope="session">
    <fmt:bundle basename='resources.config'><fmt:message key='path.page.error' /></fmt:bundle>
</c:set>

<html>
<head>
    <title><fmt:message key="errorpage.title" /></title>
</head>
<body>




<h1><fmt:message key="errorpage.h1.head" /></h1>
<h2><fmt:message key="errorpage.h2.description" /></h2>
<c:if test="${not empty oldFrom}">
    <a href="<c:url value='${oldFrom}'/>"  style="width: 210px;">
        <fmt:message key="errorpage.a.previous" />
    </a>
</c:if>

<c:choose>
    <c:when test="${not empty sessionScope.user}">
        <a href='/WEB-INF<fmt:bundle basename="resources.config"><fmt:message key="path.page.start" /></fmt:bundle>'  style="width: 210px;">
            <fmt:message key="errorpage.a.startpage" />
        </a>
    </c:when>
    <c:otherwise>
        <a href='/WEB-INF<fmt:bundle basename="resources.config"><fmt:message key="path.page.login" /></fmt:bundle>'  style="width: 210px;">
            <fmt:message key="errorpage.a.loginpage" />
        </a>
    </c:otherwise>
</c:choose>

<c:if test="${not empty pageContext.errorData}">
    </br>
    Request from ${pageContext.errorData.requestURI} is failed
    <br/>
    Servlet name or type: ${pageContext.errorData.servletName}
    <br/>
    Status code: ${pageContext.errorData.statusCode}
    <br/>
    Exception: ${pageContext.errorData.throwable}

    <br/>
    Exception cause: ${pageContext.errorData.throwable.cause}
    <br/>
    Exception trace:
    <br/>
    <c:forEach var="traceElem" items="${pageContext.exception.stackTrace}">
        <c:out value="${traceElem}"/><br/>
    </c:forEach>
</c:if>
</body>
</html>
