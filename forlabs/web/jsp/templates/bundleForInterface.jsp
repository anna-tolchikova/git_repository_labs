
<%--
установка локали из сессии (в нее заносится по событию sessionCreated) или из параметров инициализации контекста
--%>

<c:set var="localeStr" value="${ not empty sessionScope['localeStr']  ? sessionScope['localeStr'] : initParam['localeStr']}"/>
<fmt:setLocale value="${localeStr}" />
<fmt:setBundle basename="resources.text" />

