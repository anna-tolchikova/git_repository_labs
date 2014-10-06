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

        <title><fmt:message key='admin.home.title' /></title>

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
                    <li>
                        <form name="LocaleForm" action="${servletMap}" method="post">
                            <a href="#" onclick="setHiddenBeBY();">BY</a> | <a  href="#" onclick="setHiddenEnUs();">ENG</a>
                            <input type="hidden" name="localeStrI" value=""/>
                            <input type="hidden" name="command" value="changeLocale"/>
                            <ctg:localeForm />
                        </form>
                    </li>

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
                                <a class="active" href="<fmt:bundle basename='resources.routing'>
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
                                <a href="<fmt:bundle basename='resources.routing'>
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
                    <div class="col-lg-12">
                        <h1 class="page-header"><fmt:message key="admin.home.h1.pageheader"/></h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
                <div class="row">
                    <div class="col-lg-3 col-md-6">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="fa fa-comments fa-5x"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge"><c:out value="${fn:length(newRequests)}"/></div>
                                        <div><fmt:message key="admin.home.panel.newrequests"/></div>
                                    </div>
                                </div>
                            </div>
                            <a href="#newRequestsPanel">
                                <div class="panel-footer">
                                    <span class="pull-left"><fmt:message key="admin.infopanels.footer"/></span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <div class="panel panel-green">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="fa fa-tasks fa-5x"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge"><c:out value="${fn:length(expiredRequests)}"/></div>
                                        <div><fmt:message key="admin.home.panel.expiredrequests"/></div>
                                    </div>
                                </div>
                            </div>
                            <a href="#expiredRequestsPanel">
                                <div class="panel-footer">
                                    <span class="pull-left"><fmt:message key="admin.infopanels.footer"/></span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <div class="panel panel-yellow">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="fa fa-shopping-cart fa-5x"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge"><c:out value="${fn:length(notReturnedRequests)}"/></div>
                                        <div><fmt:message key="admin.home.panel.notreturnedrequests"/></div>
                                    </div>
                                </div>
                            </div>
                            <a href="#notReturnedRequestsPanel">
                                <div class="panel-footer">
                                    <span class="pull-left"><fmt:message key="admin.infopanels.footer"/></span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <div class="panel panel-red">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="fa fa-support fa-5x"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge"><c:out value="${fn:length(damagedRequests)}"/></div>
                                        <div><fmt:message key="admin.home.panel.damagedrequests"/></div>
                                    </div>
                                </div>
                            </div>
                            <a href="#damagedRequestsPanel">
                                <div class="panel-footer">
                                    <span class="pull-left"><fmt:message key="admin.infopanels.footer"/></span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
                <!-- /.row -->





                <div class="row">
                    <div class="col-lg-12">

                        <div class="panel panel-primary" id="newRequestsPanel">
                            <div class="panel-heading">
                                <jsp:useBean id="now" class="java.util.Date" />
                                <b><fmt:message key="admin.home.table.newreq.title"/></b>
                                <i><fmt:formatDate value="${now}" type="both" pattern="dd.MM.y, kk:mm:ss"/></i>
                            </div>
                            <!-- /.panel-heading -->
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-striped table-bordered table-hover" id="newRequestsTable">
                                        <thead>
                                        <tr>
                                            <th></th>
                                            <th><fmt:message key="admin.home.tables.th.requestDate"/></th>
                                            <th><fmt:message key="admin.home.tables.th.name"/></th>
                                            <th><fmt:message key="admin.home.tables.th.model"/></th>
                                            <th><fmt:message key="admin.home.tables.th.rentalDate"/></th>
                                            <th><fmt:message key="admin.home.tables.th.rentalPeriod"/></th>
                                            <th></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="requestCl" items="${newRequests}">
                                            <tr>
                                                <td></td>
                                                <td><fmt:formatDate value='${requestCl.clientRequest.requestDate}' type='both' pattern="dd.MM.y, kk:mm:ss"/></td>
                                                <td><c:out value="${fc:concatName(requestCl.clientRequest.firstName, requestCl.clientRequest.lastName)}"/></td>
                                                <td><c:out value="${requestCl.specification.model}"/></td>
                                                <td><fmt:formatDate value='${requestCl.clientRequest.rentalDate}' type='both' pattern="dd.MM.y, kk:mm:ss"/></td>
                                                <td  class="center"><c:out value="${requestCl.clientRequest.rentalPeriod}"/></td>
                                                <td  class="center">
                                                    <a style="display: inline-block; height:100%;width:100%; text-align:center;"
                                                       href="<fmt:bundle basename='resources.routing'><fmt:message key='path.page.admin.application'/></fmt:bundle>?id=${requestCl.clientRequest.id}">
                                                        <input class="btn btn-outline btn-primary btn-xs" type="button" value="<fmt:message key='admin.home.tables.show'/>" />
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- /.table-responsive -->

                            </div>
                            <!-- /.panel-body -->
                        </div>
                        <!-- /.panel -->


                        <div class="panel panel-green" id="expiredRequestsPanel">
                            <div class="panel-heading">
                                <b><fmt:message key="admin.home.table.expreq.title"/></b>
                                <i><fmt:formatDate value="${now}" type="both" pattern="dd.MM.y, kk:mm:ss"/></i>
                            </div>
                            <!-- /.panel-heading -->
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-striped table-bordered table-hover" id="expiredRequestsTable">
                                        <thead>
                                        <tr>
                                            <th></th>
                                            <th><fmt:message key="admin.home.tables.th.requestDate"/></th>
                                            <th><fmt:message key="admin.home.tables.th.name"/></th>
                                            <th><fmt:message key="admin.home.tables.th.model"/></th>
                                            <th><fmt:message key="admin.home.tables.th.rentalDate"/></th>
                                            <th><fmt:message key="admin.home.tables.th.rentalPeriod"/></th>
                                            <th></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="requestCl" items="${expiredRequests}">
                                            <tr>
                                                <td></td>
                                                <td><fmt:formatDate value='${requestCl.clientRequest.requestDate}' type='both' pattern="dd.MM.y, kk:mm:ss"/></td>
                                                <td><c:out value="${fc:concatName(requestCl.clientRequest.firstName, requestCl.clientRequest.lastName)}"/></td>
                                                <td><c:out value="${requestCl.specification.model}"/></td>
                                                <td><fmt:formatDate value='${requestCl.clientRequest.rentalDate}' type='both' pattern="dd.MM.y, kk:mm:ss"/></td>
                                                <td  class="center"><c:out value="${requestCl.clientRequest.rentalPeriod}"/></td>
                                                <td  class="center">
                                                    <a style="display: inline-block; height:100%;width:100%; text-align:center;"
                                                       href="<fmt:bundle basename='resources.routing'><fmt:message key='path.page.admin.application'/></fmt:bundle>?id=${requestCl.clientRequest.id}">
                                                        <input class="btn btn-outline btn-primary btn-xs" type="button" value="<fmt:message key='admin.home.tables.show'/>" />
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- /.table-responsive -->

                            </div>
                            <!-- /.panel-body -->
                        </div>
                        <!-- /.panel -->
                        

                        <div class="panel panel-yellow" id="notReturnedRequestsPanel">
                            <div class="panel-heading">
                                <b><fmt:message key="admin.home.table.notretreq.title"/></b>
                                <i><fmt:formatDate value="${now}" type="both" pattern="dd.MM.y, kk:mm:ss"/></i>
                            </div>
                            <!-- /.panel-heading -->
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-striped table-bordered table-hover" id="notReturnedRequestsTable">
                                        <thead>
                                        <tr>
                                            <th></th>
                                            <th><fmt:message key="admin.home.tables.th.name"/></th>
                                            <th><fmt:message key="admin.home.tables.th.expectedReturnDate"/></th>
                                            <th><fmt:message key="admin.home.tables.th.model"/></th>
                                            <th><fmt:message key="admin.home.tables.th.rentalDate"/></th>
                                            <th><fmt:message key="admin.home.tables.th.rentalPeriod"/></th>
                                            <th></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="requestCl" items="${notReturnedRequests}">
                                            <tr>
                                                <td></td>
                                                <td><c:out value="${fc:concatName(requestCl.clientRequest.firstName, requestCl.clientRequest.lastName)}"/></td>
                                                <td><fmt:formatDate value='${requestCl.clientRequest.expectedReturnDate}' type='both' pattern="dd.MM.y, kk:mm:ss"/></td>
                                                <td><c:out value="${requestCl.specification.model}"/></td>
                                                <td><fmt:formatDate value='${requestCl.clientRequest.rentalDate}' type='both' pattern="dd.MM.y, kk:mm:ss"/></td>
                                                <td  class="center"><c:out value="${requestCl.clientRequest.rentalPeriod}"/></td>
                                                <td  class="center">
                                                    <a style="display: inline-block; height:100%;width:100%; text-align:center;"
                                                       href="<fmt:bundle basename='resources.routing'><fmt:message key='path.page.admin.application'/></fmt:bundle>?id=${requestCl.clientRequest.id}">
                                                        <input class="btn btn-outline btn-primary btn-xs" type="button" value="<fmt:message key='admin.home.tables.show'/>" />
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- /.table-responsive -->

                            </div>
                            <!-- /.panel-body -->
                        </div>
                        <!-- /.panel -->


                        <div class="panel panel-red" id="damagedRequestsPanel">
                            <div class="panel-heading">
                                <b><fmt:message key="admin.home.table.damreq.title"/></b>
                                <i><fmt:formatDate value="${now}" type="both" pattern="dd.MM.y, kk:mm:ss"/></i>
                            </div>
                            <!-- /.panel-heading -->
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-striped table-bordered table-hover" id="damagedRequestsTable">
                                        <thead>
                                        <tr>
                                            <th></th>
                                            <th><fmt:message key="admin.home.tables.th.name"/></th>
                                            <th><fmt:message key="admin.home.tables.th.repaircost"/></th>
                                            <th><fmt:message key="admin.home.tables.th.model"/></th>
                                            <th><fmt:message key="admin.home.tables.th.returndate"/></th>
                                            <th></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="requestCl" items="${damagedRequests}">
                                            <tr>
                                                <td></td>
                                                <td><c:out value="${fc:concatName(requestCl.clientRequest.firstName, requestCl.clientRequest.lastName)}"/></td>
                                                <td  class="center"><c:out value="${requestCl.clientRequest.repairCost}"/></td>
                                                <td><c:out value="${requestCl.specification.model}"/></td>
                                                <td><fmt:formatDate value='${requestCl.clientRequest.returnDate}' type='both' pattern="dd.MM.y, kk:mm:ss"/></td>
                                                <td  class="center">
                                                    <a style="display: inline-block; height:100%;width:100%; text-align:center;"
                                                       href="<fmt:bundle basename='resources.routing'><fmt:message key='path.page.admin.application'/></fmt:bundle>?id=${requestCl.clientRequest.id}">
                                                        <input class="btn btn-outline btn-primary btn-xs" type="button" value="<fmt:message key='admin.home.tables.show'/>" />
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- /.table-responsive -->

                            </div>
                            <!-- /.panel-body -->
                        </div>
                        <!-- /.panel -->


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

        <!-- DataTables JavaScript -->
        <script src="${context}/js/plugins/dataTables/jquery.dataTables.js"></script>
        <script src="${context}/js/plugins/dataTables/dataTables.bootstrap.js"></script>

        <!-- Custom Theme JavaScript -->
        <script src="${context}/js/sb-admin-2.js"></script>


        <!-- for new requests -->
        <script>
            $(document).ready(function() {
                var t = $('#newRequestsTable').DataTable( {
                    "columnDefs": [ {
                        "searchable": false,
                        "orderable": false,
                        "targets": [0,5]
                    } ],
                    "order": [[ 1, 'asc' ]]
                } );

                t.on( 'order.dt search.dt', function () {
                    t.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
                        cell.innerHTML = i+1;
                    } );
                } ).draw();
            } );
        </script>

        <!-- for expired requests -->
        <script>
            $(document).ready(function() {
                var t = $('#expiredRequestsTable').DataTable( {
                    "columnDefs": [ {
                        "searchable": false,
                        "orderable": false,
                        "targets": [0,5]
                    } ],
                    "order": [[ 1, 'asc' ]]
                } );

                t.on( 'order.dt search.dt', function () {
                    t.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
                        cell.innerHTML = i+1;
                    } );
                } ).draw();
            } );
        </script>


        <!-- for not returned requests -->
        <script>
            $(document).ready(function() {
                var t = $('#notReturnedRequestsTable').DataTable( {
                    "columnDefs": [ {
                        "searchable": false,
                        "orderable": false,
                        "targets": [0,6]
                    } ],
                    "order": [[ 2, 'asc' ]]
                } );

                t.on( 'order.dt search.dt', function () {
                    t.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
                        cell.innerHTML = i+1;
                    } );
                } ).draw();
            } );
        </script>

        <!-- for damaged requests -->
        <script>
            $(document).ready(function() {
                var t = $('#damagedRequestsTable').DataTable( {
                    "columnDefs": [ {
                        "searchable": false,
                        "orderable": false,
                        "targets": [0,5]
                    } ],
                    "order": [[ 2, 'asc' ]]
                } );

                t.on( 'order.dt search.dt', function () {
                    t.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
                        cell.innerHTML = i+1;
                    } );
                } ).draw();
            } );
        </script>

        <script>
            function setHiddenBeBY(){
                document.all.localeStrI.value="be_BY";
                document.LocaleForm.submit();
            }
            function setHiddenEnUs(){
                document.all.localeStrI.value="en_US";
                document.LocaleForm.submit();
            }
        </script>

    </body>
</html>