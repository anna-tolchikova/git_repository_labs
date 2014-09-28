<!DOCTYPE HTML>
<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="localeStr" value="${ not empty sessionScope['localeStr']  ? sessionScope['localeStr'] : initParam['localeStr']}"/>
<fmt:setLocale value="${localeStr}" />
<fmt:setBundle basename="resources.text" />

<html>
<head>
    <title><fmt:message key='errorpage.403.title' /></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="/css/error_style.css" rel="stylesheet" type="text/css" media="all" />

</head>
<body>
<div class="wrap">
    <!-----start-content--------->
    <div class="content">
        <!-----start-logo--------->
        <div class="logo">
            <h1><a href="#"></a></h1>
            <span><img src="/images/signal.png"/><fmt:message key="errorpage.403.h1"/></span>
        </div>
        <!-----end-logo--------->
        <!-----start-search-bar-section--------->
        <div class="buttom">
            <div class="seach_bar">
                <p>
                    <c:if test="${not empty requestScope.previousPage}">
                        <a href="<c:url value='${requestScope.previousPage}'/>" style="width: 250px; display: inline-block;">
                            <fmt:message key="errorpage.a.previous"/>
                        </a>
                    </c:if>

                    <a href='<fmt:bundle basename="resources.routing"><fmt:message key="path.page.login" /></fmt:bundle>'
                       style="width: 210px;">
                        <fmt:message key="errorpage.a.loginpage"/>
                    </a>

                </p>

            </div>
        </div>
        <!-----end-sear-bar--------->
    </div>
</div>

<!---------end-wrap---------->


<% request.getServletContext().log("JSP: error403");%>
</body>
</html>