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

    <title><fmt:message key='client.home.title' /></title>

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

    <style>
        #allRequestsTable td{
            padding: 3px;
        }
    </style>

</head>
<body>

<div id="wrapper">

<div>
</div>

<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
        <a class="navbar-brand" href="<fmt:bundle basename='resources.routing'><fmt:message key='path.page.client.home' /></fmt:bundle>">
            <strong>
                <fmt:message key="client.servicetitile"/>
            </strong>
        </a>
    </div>

    <ul class="nav navbar-top-links navbar-right" style="margin-top: 15px;">
        <li>
            <form name="LocaleForm" action="${servletMap}" method="post">
                <a href="#" onclick="setHiddenBeBY();">BY</a> | <a  href="#" onclick="setHiddenEnUs();">ENG</a>
                <input type="hidden" name="localeStrI" value=""/>
                <input type="hidden" name="command" value="changeLocale"/>
                <input type="hidden" name="from" value="<fmt:bundle basename='resources.routing'><fmt:message key='path.page.client.home' /></fmt:bundle>" />
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
                                         <fmt:message key='path.page.client.home'/>
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
                                         <fmt:message key='path.page.client.category.specifications'/></fmt:bundle>?category=1">
                                <fmt:message key="admin.navbar.cars.econom"/>
                            </a>
                        </li>
                        <li>
                            <a href="<fmt:bundle basename='resources.routing'>
                                         <fmt:message key='path.page.client.category.specifications'/></fmt:bundle>?category=2">
                                <fmt:message key="admin.navbar.cars.middle"/>
                            </a>
                        </li>
                        <li>
                            <a href="<fmt:bundle basename='resources.routing'>
                                         <fmt:message key='path.page.client.category.specifications'/></fmt:bundle>?category=3">
                                <fmt:message key="admin.navbar.cars.business"/>
                            </a>
                        </li>
                        <li>
                            <a href="<fmt:bundle basename='resources.routing'>
                                         <fmt:message key='path.page.client.category.specifications'/></fmt:bundle>?category=4">
                                <fmt:message key="admin.navbar.cars.jeaps"/>
                            </a>
                        </li>
                        <li>
                            <a href="<fmt:bundle basename='resources.routing'>
                                         <fmt:message key='path.page.client.category.specifications'/></fmt:bundle>?category=5">
                                <fmt:message key="admin.navbar.cars.kupe"/>
                            </a>
                        </li>
                    </ul>

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
    <div class="col-lg-12">

        <c:if test="${ not empty sessionScope.commandError}">
            <div class="alert alert-danger alert-dismissable">
                <button class="close" aria-hidden="true" data-dismiss="alert" type="button">×</button>
                <c:out value="${sessionScope.commandError}"/>
            </div>
            <c:remove var="commandError" scope="session"/>
        </c:if>

        <c:if test="${ not empty sessionScope.successEdit}">
            <div class="alert alert-success alert-dismissable">
                <button class="close" aria-hidden="true" data-dismiss="alert" type="button">×</button>
                <c:out value="${sessionScope.successEdit}"/>
            </div>
            <c:remove var="successEdit" scope="session"/>
        </c:if>

        <div class="panel panel-yellow" id="allRequestsPanel">
            <div class="panel-heading">
                <jsp:useBean id="now" class="java.util.Date" />
                <b><fmt:message key="client.home.table.title"/> </b>
                <i><fmt:formatDate value="${now}" type="both" pattern="dd.MM.y, kk:mm:ss"/></i>
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover" id="allRequestsTable">
                        <thead>
                        <tr>
                            <th></th>
                            <th><fmt:message key="admin.apps.table.th.requestDate"/></th>
                            <th><fmt:message key="admin.apps.table.th.status"/></th>
                            <th><fmt:message key="admin.home.tables.th.model"/></th>
                            <th><fmt:message key="admin.home.tables.th.rentalDate"/></th>
                            <th><fmt:message key="admin.home.tables.th.rentalPeriod"/></th>
                            <th><fmt:message key="admin.apps.table.th.returndate"/></th>
                            <th><fmt:message key="admin.apps.table.th.rentalcost"/></th>
                            <th><fmt:message key="admin.apps.table.th.repaircost"/></th>
                            <th><fmt:message key="admin.apps.table.th.comments"/></th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="composed" items="${composedInfo}">
                            <tr>
                                <td></td>
                                <td><fmt:formatDate value='${composed.clientRequest.requestDate}' type='both' pattern="dd.MM.y, kk:mm:ss"/></td>
                                <td><c:out value="${composed.status.name}"/></td>
                                <td>
                                    <a href="<fmt:bundle basename='resources.routing'><fmt:message key='path.page.client.specifications'/></fmt:bundle>?id=${composed.specification.id}">
                                        <c:out value="${composed.specification.model}, ${composed.specification.year}"/>
                                    </a>
                                </td>
                                <td><fmt:formatDate value='${composed.clientRequest.rentalDate}' type='date' pattern="dd.MM.y"/></td>
                                <td  class="center"><c:out value="${composed.clientRequest.rentalPeriod}"/></td>
                                <td><fmt:formatDate value='${composed.clientRequest.returnDate}' type='date' pattern="dd.MM.y"/></td>
                                <td><c:out value="${clientRequest.rentalPeriod * specification.costPerDay}"/></td>
                                <td><c:out value='${composed.clientRequest.repairCost}'/></td>
                                <td><c:out value='${composed.clientRequest.statusComment}'/></td>
                                <td  class="center">

                                    <c:if test="${composed.status.id == 2}">
                                        <form name="payForm" action="${servletMap}" method="post">
                                            <button type="sumbit" class="btn btn-outline btn-warning">
                                                <fmt:message key="client.home.table.paybutton"/>
                                            </button>
                                            <input type="hidden" name="id" value="${composed.clientRequest.id}"/>
                                            <input type="hidden" name="command" value="pay_bill"/>
                                        </form>
                                    </c:if>
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
        var t = $('#allRequestsTable').DataTable( {
            "columnDefs": [{
                "searchable": false,
                "orderable": false,
                "targets": [0,10]
            } ],
            "order": [[ 1, 'asc' ]],
            "columnDefs": [
                {
                    "orderable": false,
                    "targets": [2, 3, 6, 9]
                }]
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