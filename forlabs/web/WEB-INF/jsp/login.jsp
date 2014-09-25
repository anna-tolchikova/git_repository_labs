<!DOCTYPE html>

<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


    <%--<fmt:bundle basename='resources.config'><fmt:message key='path.template.bundle' var="pathTemplateBundle"/></fmt:bundle>
<jsp:include page="/WEB-INF${pathTemplateBundle}"/>--%>


<c:set var="localeStr" value="${ not empty sessionScope['localeStr']  ? sessionScope['localeStr'] : initParam['localeStr']}"/>
<fmt:setLocale value="${localeStr}" />
<fmt:setBundle basename="resources.text" />



<c:url var="fromUrl" value="${requestScope['javax.servlet.forward.request_uri']}"/>
<c:set var="from" value="${fromUrl}" scope="session"/>

<c:set var="context" value="${pageContext.request.contextPath}"> </c:set>

<html>
    <head>
        <title><fmt:message key="login.title" /></title>

        <link href="${context}/css/auth_style.css" rel="stylesheet" type="text/css" />
        <link href="${context}/css/style.css" rel="stylesheet" type="text/css" />
        <link rel="icon" href="https://github.com/fluidicon.png" type="image/x-icon"/>

        <script src="${context}/js/scripts.js"  type="text/javascript"></script>
    </head>

    <body>
        <div>
            <form name="LocaleForm" action="${sessionScope.from}" method="post">
                <a href="#" onclick="setHiddenBeBY();">BY</a> | <a  href="#" onclick="setHiddenEnUs();">ENG</a>
                <input type="hidden" name="localeStrI" value=""/>
                <input type="hidden" name="command" value="changeLocale"/>
            </form>
        </div>

        <div id="login">
            <form name="LoginForm" action="/site" method="post">
                <fieldset class="clearfix">
                    <p><span class="fontawesome-user"></span><input type="text" name="login" value="<fmt:message key="login.placeholder.login"/>" onBlur="if(this.value == '') this.value = '<fmt:message key="login.placeholder.login" />'" onFocus="if(this.value == '<fmt:message key="login.placeholder.login" />') this.value = ''" required /></p> <!-- JS because of IE support; better: placeholder="Username" -->
                    <p><span class="fontawesome-lock"></span><input type="password"  name="password" value='<fmt:message key="login.placeholder.password" />' onBlur="if(this.value == '') this.value = '<fmt:message key="login.placeholder.password" />'" onFocus="if(this.value == '<fmt:message key="login.placeholder.password" />') this.value = ''" required /></p> <!-- JS because of IE support; better: placeholder="Password" -->

                    <c:if test="${ not empty sessionScope.errorLoginPassMessage}">
                        <p class="server-text">${sessionScope.errorLoginPassMessage}</p>
                        <c:remove var="errorLoginPassMessage" scope="session" />
                    </c:if>

                    <c:if test="${ not empty sessionScope.wrongAction}">
                        <p  class="server-text">${sessionScope.wrongAction}</p>
                        <c:remove var="wrongAction" scope="session" />
                    </c:if>

                    <c:if test="${ not empty sessionScope.nullPage}">
                        <p  class="server-text">${sessionScope.nullPage}</p>
                        <c:remove var="nullPage" scope="session" />
                    </c:if>

                    <c:if test="${ not empty sessionScope.notAuthorised}">
                        <p class="server-text"><fmt:bundle basename="resources.messages"><fmt:message key="message.notauthorised"/></fmt:bundle> </p>
                        <c:remove var="notAuthorised" scope="session" />
                    </c:if>

                    </br>

                    <p><input type="submit" value='<fmt:message key="login.button.enter" />'/></p>
                    <input type="hidden" name="command" value="login" />

                </fieldset>
            </form>
        </div>

    </body>

</html>
