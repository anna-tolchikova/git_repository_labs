<!DOCTYPE html>
<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib prefix="fc" uri="/functions"%>


<c:set var="localeStr" value="${ not empty sessionScope['localeStr']  ? sessionScope['localeStr'] : initParam['localeStr']}"/>
<fmt:setLocale value="${localeStr}" />
<fmt:setBundle basename="resources.text" />


<c:url var="fromUrl" value="${requestScope['javax.servlet.forward.request_uri']}">
    <c:param name="id" value="${param.id}"/>
</c:url>
<c:set var="from" value="${fromUrl}" scope="session"/>


<c:set var="context" value="${pageContext.request.contextPath}"> </c:set>

<html>
    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title><fmt:message key='admin.application.title' /></title>

        <!-- Bootstrap Core CSS -->
        <link href="${context}/css/bootstrap.min.css" rel="stylesheet">

        <!-- MetisMenu CSS -->
        <link href="${context}/css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet"  type="text/css"/>

        <!-- DataTables CSS -->
        <link href="${context}/css/plugins/dataTables.bootstrap.css" rel="stylesheet">

        <!-- Timeline CSS -->
        <link href="${context}/css/plugins/timeline.css" rel="stylesheet"/>

        <!-- Custom CSS -->
        <link href="${context}/css/sb-admin-2.css" rel="stylesheet"/>

        <!-- Custom Fonts -->
        <link href="${context}/css/fonts/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

    </head>

    <body>
        <div id="wrapper">

            <div>
            </div>

            <!-- Navigation -->
            <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="<fmt:bundle basename='resources.routing'><fmt:message key='path.page.admin.home' /></fmt:bundle>"><fmt:message key="admin.servicetitile"/></a>
                </div>

                <ul class="nav navbar-top-links navbar-right">
                    <li>
                        <form name="LocaleForm" action="${sessionScope.from}" method="post">
                            <a href="#" onclick="setHiddenBeBY();">BY</a> | <a  href="#" onclick="setHiddenEnUs();">ENG</a>
                            <input type="hidden" name="id" value="${param.id}">
                            <input type="hidden" name="localeStrI" value=""/>
                            <input type="hidden" name="command" value="changeLocale"/>
                        </form>
                    </li>
                    <li class="dropdown">

                        <form  name="LogoutForm" action="/site" method="post">
                            <a href="#" onclick="document.LogoutForm.submit(); return false;"><i class="fa fa-sign-out fa-fw"></i> <fmt:message key="admin.logout"/></a>
                            <input type="hidden" name="command" value="logout"/>
                        </form>
                    </li>

                </ul>

                <!-- /.navbar-top-links -->

                <div class="navbar-default sidebar" role="navigation">
                    <div class="sidebar-nav navbar-collapse">
                        <ul class="nav" id="side-menu">
                            <li>
                                <a href="<fmt:bundle basename='resources.routing'><fmt:message key='path.page.admin.home'/></fmt:bundle>">
                                    <i class="fa fa-dashboard fa-fw"></i>
                                    <fmt:message key="admin.navbar.home"/>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <i class="fa fa-table fa-fw"></i>
                                    <fmt:message key="admin.navbar.cars"/>
                                </a>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-edit fa-fw"></i> <fmt:message key="admin.navbar.requests"/></a>
                            </li>
                        </ul>
                    </div>
                    <!-- /.sidebar-collapse -->
                </div>
                <!-- /.navbar-static-side -->
            </nav>

            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header"><fmt:message key="admin.application.h1.appnumber"/><c:out value="${param.id}" /></h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
            <!-- /.row -->
            </div>
            <!-- /#page-wrapper -->

        </div>
        <!-- /#wrapper -->

        <jsp:directive.include file=""/>

    </body>
</html>