<!DOCTYPE html>

<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<c:set var="localeStr" value="${ not empty sessionScope['localeStr']  ? sessionScope['localeStr'] : initParam['localeStr']}"/>
<fmt:setLocale value="${localeStr}" />
<fmt:setBundle basename="resources.text" />



<c:set var="from" scope="session">
    <fmt:bundle basename='resources.config'><fmt:message key='path.page.client.home' /></fmt:bundle>
</c:set>


<html>
<head>
    <title><fmt:message key='client.home.title' /></title>
</head>
<body>

<div>
    <form name="LocaleForm" action="/pages" method="post">
        <a href="#" onclick="setHiddenBeBY();">BY</a> | <a  href="#" onclick="setHiddenEnUs();">ENG</a>
        <input type="hidden" name="localeStrI" value=""/>
        <input type="hidden" name="command" value="changeLocale"/>
    </form>
</div>

</body>
</html>
