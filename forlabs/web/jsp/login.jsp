    <%@page contentType="text/html; charset=UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


    <%--<fmt:bundle basename='resources.config'><fmt:message key='path.template.bundle' var="pathTemplateBundle"/></fmt:bundle>
<jsp:include page="/WEB-INF${pathTemplateBundle}"/>--%>


    <c:set var="localeStr" value="${ not empty sessionScope['localeStr']  ? sessionScope['localeStr'] : initParam['localeStr']}"/>
    <fmt:setLocale value="${localeStr}" />
    <fmt:setBundle basename="resources.text" />



    <c:set var="from" scope="session">
        <fmt:bundle basename='resources.config'><fmt:message key='path.page.login' /></fmt:bundle>
    </c:set>



    <html>
    <head>
        <title><fmt:message key="login.title" /></title>

        <link href="../css/auth_style.css" rel="stylesheet" type="text/css" />
        <link href="../css/style.css" rel="stylesheet" type="text/css" />
        <link rel="icon" href="https://github.com/fluidicon.png" type="image/x-icon"/>

    </head>

    <body>
    <script src="/js/scripts.js"  type="text/javascript"></script>

    <div>
        <form name="LocaleForm" action="/pages" method="post">
            <a href="#" onclick="setHiddenBeBY();">BY</a> | <a  href="#" onclick="setHiddenEnUs();">ENG</a>
            <input type="hidden" name="localeStrI" value=""/>
            <input type="hidden" name="command" value="changeLocale"/>
        </form>
    </div>


    <div id="login">
        <form name="LoginForm" action="/pages/" method="post">
            <fieldset class="clearfix">

                <fmt:message key="login.placeholder.login" var="loginPlaceholderLogin"/>
                <p><span class="fontawesome-user"></span><input type="text" name="login" value="${fn:escapeXml(pageScope.loginPlaceholderLogin)}" onBlur="if(this.value == '') this.value = '<fmt:message key="login.placeholder.login" />'" onFocus="if(this.value == '<fmt:message key="login.placeholder.login" />') this.value = ''" required /></p> <!-- JS because of IE support; better: placeholder="Username" -->
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


                <p><input type="submit" value='<fmt:message key="login.button.enter" />'/></p>
                <input type="hidden" name="command" value="login" />

            </fieldset>
        </form>
    </div>


    </body>

    </html>
