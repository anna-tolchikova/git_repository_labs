<!DOCTYPE html>
<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib prefix="fc" uri="/functions" %>
<%@ taglib prefix="ctg" uri="/customtags" %>


<c:set var="localeStr"
       value="${ not empty sessionScope['localeStr']  ? sessionScope['localeStr'] : initParam['localeStr']}"/>
<fmt:setLocale value="${localeStr}"/>
<fmt:setBundle basename="resources.text"/>

<c:set var="servletMap" value="${initParam['map']}"/>
<c:set var="imagesMap" value="${initParam['imagesMap']}"/>
<c:set var="context" value="${pageContext.request.contextPath}"> </c:set>

<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><fmt:message key='admin.specifications.new.title'/></title>

    <!-- Bootstrap Core CSS -->
    <link href="${context}/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="${context}/css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet" type="text/css"/>

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
        <a class="navbar-brand"
           href="<fmt:bundle basename='resources.routing'><fmt:message key='path.page.admin.home' /></fmt:bundle>">
            <strong>
                <fmt:message key="admin.servicetitile"/>
            </strong>
        </a>
    </div>

    <ul class="nav navbar-top-links navbar-right" style="margin-top: 15px;">

        <li class="dropdown">

            <form name="LogoutForm" action="${servletMap}" method="post">
                <a href="#" onclick="document.LogoutForm.submit(); return false;"><i class="fa fa-sign-out fa-fw"></i>
                    <fmt:message key="admin.logout"/></a>
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
            <h1 class="page-header">
                <fmt:bundle basename="resources.routing">
                    <fmt:message key="path.page.admin.category.specifications" var="categoryPath"/>
                </fmt:bundle>
                <a href="${categoryPath}?category=${category.id}"><c:out value="${category.name}"/></a>
                <fmt:message key="admin.specifications.new.header" var="pageHeader"/>
                <c:out value="\\${pageHeader}"/>
            </h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <br/>

    <div class="row">
        <div class="col-lg-12">
            <div class="row">
                <div class="col-lg-8">

                    <c:if test="${ not empty sessionScope.commandError}">
                        <div class="alert alert-danger alert-dismissable">
                            <button class="close" aria-hidden="true" data-dismiss="alert" type="button">×</button>
                            <c:out value="${sessionScope.commandError}"/>
                        </div>
                        <c:remove var="commandError" scope="session"/>
                    </c:if>

                    <c:if test="${ not empty sessionScope.requiredFieldsEmpty}">
                        <div class="alert alert-danger alert-dismissable">
                            <button class="close" aria-hidden="true" data-dismiss="alert" type="button">×</button>
                            <c:out value="${sessionScope.requiredFieldsEmpty}"/>
                        </div>
                        <c:remove var="requiredFieldsEmpty" scope="session"/>
                    </c:if>

                    <c:if test="${ not empty sessionScope.patternError}">
                        <div class="alert alert-danger alert-dismissable">
                            <button class="close" aria-hidden="true" data-dismiss="alert" type="button">×</button>
                            <c:out value="${sessionScope.patternError}"/>
                        </div>
                        <c:remove var="patternError" scope="session"/>
                    </c:if>

                    <c:if test="${ not empty sessionScope.uploadError}">
                        <div class="alert alert-success">
                            <c:out value="${sessionScope.uploadError}"/>
                        </div>
                        <c:remove var="uploadError" scope="session"/>
                    </c:if>



                    <br/>

                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <fmt:message key="admin.specifications.new.panelheader"/>
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">

                                <!-- specificationsForm with submitted info -->
                                <form name="specificationsForm" action="${servletMap}" method="post" enctype="multipart/form-data">

                                    <table class="table">
                                        <tbody>
                                        <c:choose>
                                            <c:when test="${not empty sessionScope.specifications}">
                                                <tr>
                                                    <td>*<fmt:message key="admin.cars.label.producer"/></td>
                                                    <td><input type="text" name="producer" value="${specifications.producer}" required/></td>
                                                </tr>
                                                <tr>
                                                    <td>*<fmt:message key="admin.cars.label.model"/></td>
                                                    <td><input type="text" name="model" value="${specifications.model}" required/></td>
                                                </tr>
                                                <tr>
                                                    <td>*<fmt:message key="admin.cars.label.year"/></td>
                                                    <td><input type="text" name="year" value="${specifications.year}" min="1884" max="2014" pattern="\d{4}" required/></td>
                                                </tr>
                                                <tr>
                                                    <td>*<fmt:message key="admin.cars.label.transmission"/></td>
                                                    <td><input type="text" name="transmission" value="${specifications.transmission}" required/></td>
                                                </tr>
                                                <tr>
                                                    <td>*<fmt:message key="admin.cars.label.fuelType"/></td>
                                                    <td><input type="text" name="fuelType" value="${specifications.fuelType}" required/></td>
                                                </tr>
                                                <tr>
                                                    <td>*<fmt:message key="admin.cars.label.engineCapacity"/></td>
                                                    <td><input type="text" name="engineCapacity" value="${specifications.engineCapacity}" min="500" max="10000"  pattern="\d{3,5}" required/></td>
                                                </tr>
                                                <tr>
                                                    <td>*<fmt:message key="admin.cars.label.costPerDay"/></td>
                                                    <td>
                                                        <fmt:setLocale value="en_US"/>
                                                        <input type="text" name="costPerDay" value="${specifications.costPerDay}" min="0" pattern="\d*[.]{1}\d{1,2}"
                                                               placeholder="<fmt:formatNumber value='0' type='currency'/>"
                                                               required/>
                                                        <fmt:setLocale value="${localeStr}"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>*<fmt:message key="admin.cars.label.carNumber"/></td>
                                                    <td><input type="text" name="number" pattern="\d{4}-\d{2}\s[A-Za-z]{2}"
                                                               value="" required/></td>
                                                    </td>
                                                </tr>
                                            </c:when>

                                            <c:otherwise>
                                                <tr>
                                                    <td>*<fmt:message key="admin.cars.label.producer"/></td>
                                                    <td><input type="text" name="producer" required/></td>
                                                </tr>
                                                <tr>
                                                    <td>*<fmt:message key="admin.cars.label.model"/></td>
                                                    <td><input type="text" name="model" required/></td>
                                                </tr>
                                                <tr>
                                                    <td>*<fmt:message key="admin.cars.label.year"/></td>
                                                    <td><input type="text" name="year" min="1884" max="2014" pattern="\d{4}" required/></td>
                                                </tr>
                                                <tr>
                                                    <td>*<fmt:message key="admin.cars.label.transmission"/></td>
                                                    <td><input type="text" name="transmission" required/></td>
                                                </tr>
                                                <tr>
                                                    <td>*<fmt:message key="admin.cars.label.fuelType"/></td>
                                                    <td><input type="text" name="fuelType" required/></td>
                                                </tr>
                                                <tr>
                                                    <td>*<fmt:message key="admin.cars.label.engineCapacity"/></td>
                                                    <td><input type="text" name="engineCapacity" min="500" max="10000"  pattern="\d{3,5}" required/></td>
                                                </tr>
                                                <tr>
                                                    <td>*<fmt:message key="admin.cars.label.costPerDay"/></td>
                                                    <td>
                                                        <fmt:setLocale value="en_US"/>
                                                        <input type="text" name="costPerDay" min="0" pattern="\d*[.]{1}\d{1,2}"
                                                               placeholder="<fmt:formatNumber value='0' type='currency'/>"
                                                               required/>
                                                        <fmt:setLocale value="${localeStr}"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>*<fmt:message key="admin.cars.label.carNumber"/></td>
                                                    <td><input type="text" name="number" pattern="\d{4}-\d{2}\s[A-Za-z]{2}"
                                                               value="${car.number}" required/></td>
                                                    </td>
                                                </tr>

                                            </c:otherwise>
                                        </c:choose>
                                        <c:remove var="specifications" scope="session"/>

                                        <tr>
                                            <td>*<fmt:message key="admin.cars.label.image"/></td>
                                            <td><input type="file" name="image"/></td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">
                                                <!-- Button trigger modal -->
                                                <button type="button" class="btn btn-primary" data-toggle="modal"
                                                        data-target="#addModal">
                                                    <fmt:message key="admin.cars.add"/>
                                                </button>
                                            </td>
                                        </tr>

                                        </tbody>
                                    </table>

                                    <!-- Add Modal -->
                                    <div class="modal fade" id="addModal" tabindex="-1" role="dialog"
                                         aria-labelledby="myModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-body">
                                                    <fmt:message key="admin.specifications.modal.addition.text"/>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default"
                                                            data-dismiss="modal">
                                                        <fmt:message
                                                                key="admin.cars.modal.addition.close"/></button>
                                                    <button type="submit" class="btn btn-primary"><fmt:message
                                                            key="admin.cars.modal.addition.confirm"/></button>
                                                </div>
                                            </div>
                                            <!-- /.modal-content -->
                                        </div>
                                        <!-- /.modal-dialog -->
                                    </div>
                                    <!-- /.add modal -->

                                    <input type="hidden" name="command" value="add_specifications"/>
                                    <input type="hidden" name="idCategory" value="${category.id}"/>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- cars panel-->


                <br/>
                <br/>

            </div>
            <!-- /.col-lg-8 -->
        </div>
        <!-- /.row -->


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

<!-- jQuery Version 1.11.0 version-->
<script src="${context}/js/jquery-1.11.0.js"></script>


<!-- jQuery Version 1.11.1 version-->
<!-- <script src="${context}/js/jquery-1.11.1.min.js"></script>-->

<!-- Bootstrap Core JavaScript -->
<script src="${context}/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="${context}/js/plugins/metisMenu/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="${context}/js/sb-admin-2.js"></script>


</body>
</html>