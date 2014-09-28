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

<c:set var="servletMap" value="${initParam['map']}" />

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
                    <a class="navbar-brand" href="<fmt:bundle basename='resources.routing'><fmt:message key='path.page.admin.home' /></fmt:bundle>">
                        <strong>
                            <fmt:message key="admin.servicetitile"/>
                        </strong>
                    </a>
                </div>

                <ul class="nav navbar-top-links navbar-right"  style="margin-top: 15px;">
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


                <div class="row">
                    <div class="col-lg-12">
                        <div class="row">
                            <div class="col-lg-8">
                                <div class="panel panel-info">
                                    <div class="panel-heading">
                                        <fmt:message key="admin.application.infopanel.title"/>
                                    </div>
                                    <div class="panel-body">
                                        <div class="table-responsive">
                                            <table class="table">
                                                <tbody>
                                                    <tr>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /.col-lg-8 -->
                        </div>
                        <!-- /.row -->

                        <div class="row">
                            <div class="col-lg-8">
                                <div class="panel panel-warning">
                                    <div class="panel-heading">
                                        Yellow Panel
                                    </div>
                                    <div class="panel-body">
                                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum tincidunt est vitae ultrices accumsan. Aliquam ornare lacus adipiscing, posuere lectus et, fringilla augue.</p>
                                    </div>

                                </div>
                            </div>
                            <!-- /.col-lg-8 -->
                        </div>
                        <!-- /.row -->

                        <div class="panel-group" id="accordion">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">Collapsible Group Item #1</a>
                                    </h4>
                                </div>
                                <div id="collapseOne" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                                    </div>
                                </div>
                            </div>
                            <!-- /.collapse panel -->
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">Collapsible Group Item #2</a>
                                    </h4>
                                </div>
                                <div id="collapseTwo" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                                    </div>
                                </div>
                            </div>
                            <!-- /.collapse panel -->
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree">Collapsible Group Item #3</a>
                                    </h4>
                                </div>
                                <div id="collapseThree" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                                    </div>
                                </div>
                            </div>
                            <!-- /.collapse panel -->
                        </div>
                        <!-- /.collaps-panels group -->
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
            $(document).ready(function() {
                var t = $('#newApplicationsTable').DataTable( {
                    "columnDefs": [ {
                        "searchable": false,
                        "orderable": false,
                        "targets": [0,5]
                    } ],
                    "order": [[ 3, 'asc' ]]
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