<!DOCTYPE html>
<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib prefix="fc" uri="/functions"%>


<c:set var="localeStr" value="${ not empty sessionScope['localeStr']  ? sessionScope['localeStr'] : initParam['localeStr']}"/>
<fmt:setLocale value="${localeStr}" />
<fmt:setBundle basename="resources.text" />



<c:set var="from" scope="session">
    <fmt:bundle basename='resources.routing'><fmt:message key='path.page.admin.home' /></fmt:bundle>
</c:set>


<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><fmt:message key='admin.home.title' /></title>

    <c:set var="context" value="${pageContext.request.contextPath}"> </c:set>

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



    <script src="${context}/js/scripts.js"  type="text/javascript"></script>
</head>
<body>



<div>
    <form name="LocaleForm" action="/site" method="get">
        <a href="#" onclick="setHiddenBeBY();">BY</a> | <a  href="#" onclick="setHiddenEnUs();">ENG</a>
        <input type="hidden" name="localeStrI" value=""/>
        <input type="hidden" name="command" value="changeLocale"/>
    </form>
</div>



<div id="wrapper">

<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
<div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="index.html"><fmt:message key="admin.servicetitile"/></a>
</div>


<!-- /.navbar-top-links -->

<div class="navbar-default sidebar" role="navigation">
    <div class="sidebar-nav navbar-collapse">
        <ul class="nav" id="side-menu">

            <li>
                <a class="active" href="index.html"><i class="fa fa-dashboard fa-fw"></i> <fmt:message key="admin.home.navbar.home"/> </a>
            </li>
            <li>
                <a href="tables.html"><i class="fa fa-table fa-fw"></i> <fmt:message key="admin.home.navbar.cars"/></a>
            </li>
            <li>
                <a href="forms.html"><i class="fa fa-edit fa-fw"></i> <fmt:message key="admin.home.navbar.requests"/></a>
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
                        <div><fmt:message key="admin.panel.newrequests"/></div>
                    </div>
                </div>
            </div>
            <a href="#">
                <div class="panel-footer">
                    <span class="pull-left">View Details</span>
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
                        <div class="huge">12</div>
                        <div>New Tasks!</div>
                    </div>
                </div>
            </div>
            <a href="#">
                <div class="panel-footer">
                    <span class="pull-left">View Details</span>
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
                        <div class="huge">124</div>
                        <div>New Orders!</div>
                    </div>
                </div>
            </div>
            <a href="#">
                <div class="panel-footer">
                    <span class="pull-left">View Details</span>
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
                        <div class="huge">13</div>
                        <div>Support Tickets!</div>
                    </div>
                </div>
            </div>
            <a href="#">
                <div class="panel-footer">
                    <span class="pull-left">View Details</span>
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

<div class="panel panel-default">
<div class="panel-heading">
    DataTables Advanced Tables
</div>
<!-- /.panel-heading -->
<div class="panel-body">
<div class="table-responsive">
<table class="table table-striped table-bordered table-hover" id="dataTablesExample">
<thead>
<tr>
    <th><fmt:message key="admin.home.table.newreq.th.name"/></th>
    <th><fmt:message key="admin.home.table.newreq.th.model"/></th>
    <th><fmt:message key="admin.home.table.newreq.th.rentalDate"/></th>
    <th><fmt:message key="admin.home.table.newreq.th.period"/></th>
</tr>
</thead>
<tbody>

    <c:forEach var="requestCl" items="${newRequests}">
        <tr>
            <td><c:out value="${fc:concatName(requestCl.firstName, requestCl.lastName)}"/></td>
            <td><c:out value="${requestCl.model}"/></td>
            <td><c:out value="${requestCl.rentalDate}"/></td>
            <td  class="center"><c:out value="${requestCl.period}"/></td>
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











<!-- jQuery Version 1.11.1 there is local version-->
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>


<!-- Bootstrap Core JavaScript -->
<script src="${context}/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="${context}/js/plugins/metisMenu/metisMenu.min.js"></script>

<!-- DataTables JavaScript -->
<script src="${context}/js/plugins/dataTables/jquery.dataTables.js"></script>
<script src="${context}/js/plugins/dataTables/dataTables.bootstrap.js"></script>


<!-- Custom Theme JavaScript -->
<script src="${context}/js/sb-admin-2.js"></script>

<script>
    $(document).ready(function(){
        $('#dataTablesExample').dataTable({
            "order": [[ 3, "desc" ]]
        } );
    });
</script>

</body>
</html>