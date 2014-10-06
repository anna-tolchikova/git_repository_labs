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

<c:set var="context" value="${pageContext.request.contextPath}"> </c:set>

<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><fmt:message key='admin.app.title'/></title>

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
        <li>
            <form name="LocaleForm" action="${servletMap}" method="post">
                <a href="#" onclick="setHiddenBeBY();">BY</a> | <a href="#" onclick="setHiddenEnUs();">ENG</a>
                <input type="hidden" name="localeStrI" value=""/>
                <input type="hidden" name="command" value="changeLocale"/>
                <ctg:localeForm/>
            </form>
        </li>
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

<c:set var="clientRequest" value="${composedInfo.clientRequest}"/>
<c:set var="status" value="${composedInfo.status}"/>
<c:set var="specification" value="${composedInfo.specification}"/>


<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12 tooltip-demo">
            <h1 class="page-header"><fmt:message key="admin.app.h1.appnumber"/><c:out value="${param.id}"/></h1>

            <h2>
                <small>
                    <fmt:message key="admin.app.h1.subheading"/>
                </small>
                <button class="btn btn-default" title=""
                        data-placement="right"
                        data-toggle="tooltip" type="button"
                        data-original-title="${status.description}"><c:out value="${status.name}"/>
                </button>

            </h2>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <br/>

    <div class="row">
        <div class="col-lg-12">
            <div class="row">
                <div class="col-lg-8">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <fmt:message key="admin.app.infopanel.title"/>
                        </div>
                        <div class="panel-body">
                            <c:if test="${ not empty sessionScope.commandError}">
                                <div class="alert alert-danger alert-dismissable">
                                    <button class="close" aria-hidden="true" data-dismiss="alert" type="button">×</button>
                                    <fmt:bundle basename="resources.messages">
                                        <fmt:message key="message.commanderror"/>
                                    </fmt:bundle>
                                </div>
                                <c:remove var="commandError" scope="session" />
                            </c:if>
                            <div class="table-responsive">
                                <table class="table">
                                    <tbody>
                                    <tr>
                                        <td><fmt:message key="admin.app.label.requestDate"/></td>
                                        <td><fmt:formatDate value='${clientRequest.requestDate}' type='both'
                                                            pattern="dd.MM.y, kk:mm:ss"/>

                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="admin.app.label.name"/></td>
                                        <td><c:out
                                                value="${fc:concatName(clientRequest.firstName,
                                                                 clientRequest.lastName)}"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="admin.app.label.birthdate"/></td>
                                        <td><fmt:formatDate value="${clientRequest.birthDate}" type="date"
                                                            pattern="dd.MM.y"/></td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="admin.app.label.issuedate"/></td>
                                        <td><fmt:formatDate value="${clientRequest.issueDate}" type="date"
                                                            pattern="dd.MM.y"/></td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="admin.app.label.serialnumber"/></td>
                                        <td><c:out value="${clientRequest.serialNumber}"/></td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="admin.app.label.model"/></td>
                                        <td><c:out value="${specification.model}"/></td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="admin.app.label.rentaldate"/></td>
                                        <td><fmt:formatDate value="${clientRequest.rentalDate}" type="both"
                                                            pattern="dd.MM.y, kk:mm:ss"/></td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="admin.app.label.rentalperiod"/></td>
                                        <td><c:out value="${clientRequest.rentalPeriod}"/></td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="admin.app.label.rentalcost"/></td>
                                        <td><c:out
                                                value="${clientRequest.rentalPeriod * specification.costPerDay}"/></td>
                                    </tr>


                                    <c:if test="${status.id == 3}">
                                        <tr>
                                            <td>
                                                <fmt:message key="admin.app.label.rejectcomment"/>
                                            </td>
                                            <td>
                                                <c:out value="${clientRequest.statusComment}"/>
                                            </td>
                                        </tr>
                                    </c:if>

                                    <c:if test="${status.id == 5}">
                                        <tr>
                                            <td>
                                                <fmt:message key="admin.app.label.returndate"/>
                                            </td>
                                            <td>
                                                <fmt:formatDate value="${clientRequest.returnDate}" type="both"
                                                                pattern="dd.MM.y, kk:mm:ss"/>
                                            </td>
                                        </tr>
                                    </c:if>

                                    <c:if test="${status.id == 6}">
                                        <tr>
                                            <td>
                                                <fmt:message key="admin.app.label.repaircost"/>
                                            </td>
                                            <td>
                                                <c:out value="${clientRequest.repairCost}"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <fmt:message key="admin.app.label.damagecomment"/>
                                            </td>
                                            <td>
                                                <c:out value="${clientRequest.statusComment}"/>
                                            </td>
                                        </tr>
                                    </c:if>

                                    <c:if test="${status.id == 1}">

                                        <tr>
                                            <td><fmt:message key="admin.app.label.freecars"/></td>
                                            <td><c:out value="${freeCarsCount}"/></td>
                                        </tr>

                                        <tr>
                                            <td colspan="2">
                                                <form action="${servletMap}" method="post"
                                                      style="display: inline-block; height:100%;width:100%; text-align:center;">
                                                    <input type="hidden" name="id" value="${clientRequest.id}"/>
                                                    <input type="hidden" name="command" value="approve_request"/>
                                                    <c:choose>
                                                        <c:when test="${freeCarsCount == 0}">
                                                            <input class="btn btn-success disabled" type="submit"
                                                                   value="<fmt:message key='admin.app.buttons.approve'/>"/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input class="btn btn-outline btn-success" type="submit"
                                                                   value="<fmt:message key='admin.app.buttons.approve'/>"/>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:if>

                                    <c:if test="${status.id == 4}">
                                        <tr>
                                            <td colspan="2">
                                                <form action="${servletMap}" method="post"
                                                      style="display: inline-block; height:100%;width:100%; text-align:center;">

                                                    <label><fmt:message key="admin.app.label.returncomment"/></label>
                                                    <textarea class="form-control" rows="4" name="returnCommentArea">
                                                    </textarea>

                                                    <input type="hidden" name="id" value="${clientRequest.id}"/>
                                                    <input type="hidden" name="command" value="return_car"/>
                                                    <input class="btn btn-outline btn-success" type="submit"
                                                           value="<fmt:message key='admin.app.buttons.return'/>"/>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:if>


                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                    <br/>


                    <c:if test="${status.id == 1 || status.id == 2 }">

                        <div class="panel panel-red" id="accordion">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a style="color: #ffffff;" data-toggle="collapse" data-parent="#accordion" href="#collapseReject">
                                        <fmt:message key="admin.app.rejectpanel.title"/>
                                    </a>
                                </h4>
                            </div>
                            <div id="collapseReject" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <table class="table">
                                        <tr>
                                            <td>
                                                <label><fmt:message key="admin.app.rejectpanel.h1"/></label>
                                                <textarea class="form-control" rows="4" name="rejectCommentArea">
                                                </textarea>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td>
                                                <form action="${servletMap}" method="post"
                                                      style="display: inline-block; height:100%;width:100%; text-align:center;">
                                                    <input type="hidden" name="id" value="${clientRequest.id}"/>
                                                    <input type="hidden" name="command" value="reject_request"/>
                                                    <input class="btn btn-outline btn-danger" type="submit"
                                                           value="<fmt:message key='admin.app.buttons.reject'/>"/>
                                                </form>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <!-- /.collapse panel -->

                    </c:if>

                    <br/>

                    <c:if test="${status.id == 5}">

                        <div class="panel panel-red" id="accordion2">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a style="color: #ffffff;" data-toggle="collapse" data-parent="#accordion2"
                                       href="#collapseDamagedReturn">
                                       <fmt:message key="admin.app.damagedreturnpanel.title"/>
                                    </a>
                                </h4>
                            </div>
                            <div id="collapseDamagedReturn" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <c:if test="${ not empty sessionScope.commandError}">
                                        <div class="alert alert-danger alert-dismissable">
                                            <button class="close" aria-hidden="true" data-dismiss="alert" type="button">×</button>
                                            <fmt:bundle basename="resources.messages">
                                                <fmt:message key="message.commanderror"/>
                                            </fmt:bundle>
                                        </div>
                                        <c:remove var="commandError" scope="session" />
                                    </c:if>
                                    <c:if test="${ not empty sessionScope.requiredFieldsEmpty}">
                                        <div class="alert alert-warning alert-dismissable">
                                            <button class="close" aria-hidden="true" data-dismiss="alert" type="button">×</button>
                                            <c:out value="${sessionScope.requiredFieldsEmpty}"/>
                                        </div>
                                        <c:remove var="requiredFieldsEmpty" scope="session" />
                                    </c:if>
                                    <table class="table">
                                        <form action="${servletMap}" method="post">
                                            <input type="hidden" name="id" value="${clientRequest.id}"/>
                                            <input type="hidden" name="command" value="set_repair_bill"/>
                                            <tr>
                                                <td>
                                                    <fmt:message key="admin.app.label.repaircost"/>
                                                </td>
                                                <td>
                                                    <input type="text" name="repairCost" required min="0"
                                                            placeholder="<fmt:formatNumber value='0' type='currency' currencySymbol='$'/>"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <fmt:message key="admin.app.label.damagecomment"/>
                                                </td>
                                                <td>
                                                    <textarea class="form-control" rows="4" name="damageDescriptionArea" required>
                                                    </textarea>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="2">

                                                    <input class="btn btn-outline btn-danger" type="submit"
                                                           value="<fmt:message key='admin.app.buttons.setrepairbill'/>"/>
                                                </td>
                                            </tr>
                                        </form>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <!-- /.collapse panel -->

                    </c:if>


                    <br/>
                    <br/>
                    <br/>

                    <!-- /.collaps-panels group -->


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


<script>
    function setHiddenBeBY() {
        document.all.localeStrI.value = "be_BY";
        document.LocaleForm.submit();
    }
    function setHiddenEnUs() {
        document.all.localeStrI.value = "en_US";
        document.LocaleForm.submit();
    }
</script>


<script type="text/javascript">
    $('.tooltip-demo').tooltip({
        selector: "[data-toggle=tooltip]",
        container: "body"
    })
</script>


</body>
</html>