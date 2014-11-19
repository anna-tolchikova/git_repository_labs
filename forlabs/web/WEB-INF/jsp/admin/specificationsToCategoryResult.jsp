<!DOCTYPE html>
<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib prefix="fc" uri="/functions"%>
<%@ taglib prefix="ctg" uri="/customtags"%>


<c:set var="localeStr" value="${ not empty sessionScope['localeStr']  ? sessionScope['localeStr'] : initParam['localeStr']}"/>
<fmt:setLocale value="${localeStr}" />
<fmt:setBundle basename="resources.text" />

<c:set var="servletMap" value="${initParam['map']}"/>

<c:set var="context" value="${pageContext.request.contextPath}"> </c:set>


<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title><fmt:message key='admin.specifications.new.result.title' /></title>

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
                    <a class="navbar-brand" href="<fmt:bundle basename='resources.routing'><fmt:message key='path.page.admin.home' /></fmt:bundle>">
                        <strong>
                            <fmt:message key="admin.servicetitile"/>
                        </strong>
                    </a>
                </div>

                <ul class="nav navbar-top-links navbar-right" style="margin-top: 15px;">

                    <li class="dropdown">
                        <form  name="LogoutForm" action="${servletMap}" method="post">
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
                                <a href="<fmt:bundle basename='resources.routing'>
                                         <fmt:message key='path.page.admin.home'/>
                                         </fmt:bundle>">
                                    <i class="fa fa-dashboard fa-fw"></i>
                                    <fmt:message key="admin.navbar.home"/>
                                </a>
                            </li>
                            <li>
                                <a class="active" href="#">
                                    <i class="fa fa-sitemap fa-fw"></i>
                                    <fmt:message key="admin.navbar.cars"/>
                                    <span class="fa arrow"></span>
                                </a>

                                <ul class="nav nav-second-level collapse in" style="">
                                    <li>
                                        <a href="<fmt:bundle basename='resources.routing'>
                                         <fmt:message key='path.page.admin.category.specifications'/></fmt:bundle>?category=1">
                                            <fmt:message key="admin.navbar.cars.econom"/>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="<fmt:bundle basename='resources.routing'>
                                         <fmt:message key='path.page.admin.category.specifications'/></fmt:bundle>?category=2">
                                            <fmt:message key="admin.navbar.cars.middle"/>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="<fmt:bundle basename='resources.routing'>
                                         <fmt:message key='path.page.admin.category.specifications'/></fmt:bundle>?category=3">
                                            <fmt:message key="admin.navbar.cars.business"/>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="<fmt:bundle basename='resources.routing'>
                                         <fmt:message key='path.page.admin.category.specifications'/></fmt:bundle>?category=4">
                                            <fmt:message key="admin.navbar.cars.jeaps"/>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="<fmt:bundle basename='resources.routing'>
                                         <fmt:message key='path.page.admin.category.specifications'/></fmt:bundle>?category=5">
                                            <fmt:message key="admin.navbar.cars.kupe"/>
                                        </a>
                                    </li>
                                </ul>

                            </li>
                            <li>
                                <a class="active" href="<fmt:bundle basename='resources.routing'>
                                         <fmt:message key='path.page.admin.applications'/>
                                         </fmt:bundle>">
                                    <i class="fa fa-edit fa-fw"></i>
                                    <fmt:message key="admin.navbar.requests"/></a>
                            </li>
                        </ul>
                    </div>
                    <!-- /.sidebar-collapse -->
                </div>
                <!-- /.navbar-static-side -->
            </nav>

            <div id="page-wrapper">

                <div class="row">
                    <div class="col-lg-8">

                        <br/>
                        <br/>
                        <c:if test="${ not empty sessionScope.successAdd}">
                            <div class="alert alert-success">
                                <c:out value="${sessionScope.successAdd}"/>
                            </div>
                            <c:remove var="successAdd" scope="session"/>
                        </c:if>
                    </div>
                    <!-- /.col-lg-12 -->

                </div>
                <!-- /.row -->
            </div>
            <!-- /#page-wrapper -->

        </div>
        <!-- /#wrapper -->

        <!-- my scripts-- >
        <script src="${context}/js/scripts.js"></script>

        <!-- jQuery Version 1.11.1 version-->
        <script src="${context}/js/jquery-1.11.1.min.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="${context}/js/bootstrap.min.js"></script>

        <!-- Metis Menu Plugin JavaScript -->
        <script src="${context}/js/plugins/metisMenu/metisMenu.min.js"></script>

        <!-- Custom Theme JavaScript -->
        <script src="${context}/js/sb-admin-2.js"></script>

    </body>
</html>