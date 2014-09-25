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
--%>
<c:url var="fromUrl" value="${requestScope['javax.servlet.forward.request_uri']}"/>
<c:set var="from" value="${fromUrl}" scope="session"/>


<html>
<head>
    <title><fmt:message key="errorpage.title" /></title>
</head>
<body>




<h1><fmt:message key="errorpage.h1.head" /></h1>
<h2><fmt:message key="errorpage.h2.description" /></h2>
<c:if test="${not empty oldFrom}">
    <a href="<c:url value='${oldFrom}'/>"  style="width: 250px; display: inline-block;">
        <fmt:message key="errorpage.a.previous" />
    </a>
</c:if>

<c:choose>
    <c:when test="${not empty sessionScope.user}">
        <c:if test="${sessionScope.user.idRole == 1}">

            <a href="<fmt:bundle basename='resources.routing'><fmt:message key='path.page.admin.home' /></fmt:bundle>"  style="width: 210px;">
                <fmt:message key="errorpage.a.homepage" />
            </a>
        </c:if>
        <c:if test="${sessionScope.user.idRole == 2}">

            <a href='<fmt:bundle basename="resources.routing"><fmt:message key="path.page.client.home" /></fmt:bundle>'  style="width: 210px;">
                <fmt:message key="errorpage.a.homepage" />
            </a>
        </c:if>
    </c:when>
    <c:otherwise>
        <a href='<fmt:bundle basename="resources.routing"><fmt:message key="path.page.login" /></fmt:bundle>'  style="width: 210px;">
            <fmt:message key="errorpage.a.loginpage" />
        </a>
    </c:otherwise>
</c:choose>

<c:if test="${not empty pageContext.errorData}">
    </br>
    Request from <c:out value="${pageContext.errorData.requestURI}"/> is failed
    <br/>
    Servlet name or type:  <c:out value="${pageContext.errorData.servletName}"/>
    <br/>
    Status code:  <c:out value="${pageContext.errorData.statusCode}"/>
    <br/>
    Exception:  <c:out value="${pageContext.errorData.throwable}"/>

    <br/>
    Exception cause:  <c:out value="${pageContext.errorData.throwable.cause}"/>
    <br/>
    Exception trace:
    <br/>
    <c:forEach var="traceElem" items="${pageContext.exception.stackTrace}">
        <c:out value="${traceElem}"/><br/>
    </c:forEach>
</c:if>
</body>
</html>
