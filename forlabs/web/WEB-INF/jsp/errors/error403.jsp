<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="localeStr" value="${ not empty sessionScope['localeStr']  ? sessionScope['localeStr'] : initParam['localeStr']}"/>
<fmt:setLocale value="${localeStr}" />
<fmt:setBundle basename="resources.text" />

<c:set var="oldFrom" scope="session" value="${sessionScope.from}">
</c:set>

<c:url var="fromUrl" value="${requestScope['javax.servlet.forward.request_uri']}"/>
<c:set var="from" value="${fromUrl}" scope="session"/>

<html>
<head>
    <title><fmt:message key='errorpage.403.title' /></title>
</head>
<body>

<h1> <fmt:message key='errorpage.403.h1' /> </h1>


<c:if test="${not empty oldFrom}">

    <a href="<c:url value='${oldFrom}'/>"style="width: 250px; display: inline-block;">
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

<% request.getServletContext().log("JSP: error403");%>
</body>
</html>