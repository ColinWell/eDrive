<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
    <title>缴费规则分配</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Fonts -->
    <link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:300,400' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900' rel='stylesheet' type='text/css'>
    <!-- CSS Libs -->
    <link rel="stylesheet" type="text/css" href="../../lib/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../../lib/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="../../lib/css/animate.min.css">
    <link rel="stylesheet" type="text/css" href="../../lib/css/bootstrap-switch.min.css">
    <link rel="stylesheet" type="text/css" href="../../lib/css/checkbox3.min.css">
    <link rel="stylesheet" type="text/css" href="../../lib/css/jquery.dataTables.min.css">
    <link rel="stylesheet" type="text/css" href="../../lib/css/dataTables.bootstrap.css">
    <link rel="stylesheet" type="text/css" href="../../lib/css/select2.min.css">
    <link rel="stylesheet" type="text/css" href="../../lib/css/custom.css">
    <!-- CSS App -->
    <link rel="stylesheet" type="text/css" href="../../css/style.css">
    <link rel="stylesheet" type="text/css" href="../../css/themes/flat-blue.css">
</head>

<body class="flat-blue">
    <div class="app-container">
        <div class="row content-container">
            <nav class="navbar navbar-default navbar-fixed-top navbar-top">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-expand-toggle">
                            <i class="fa fa-bars icon"></i>
                        </button>
                        <ol class="breadcrumb navbar-breadcrumb">
                            <li>缴费管理</li>
                            <li class="active">缴费规则分配</li>
                        </ol>
                        <button type="button" class="navbar-right-expand-toggle pull-right visible-xs">
                            <i class="fa fa-th icon"></i>
                        </button>
                    </div>
                    <ul class="nav navbar-nav navbar-right">
                        <button type="button" class="navbar-right-expand-toggle pull-right visible-xs">
                            <i class="fa fa-times icon"></i>
                        </button>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-comments-o"></i></a>
                            <ul class="dropdown-menu animated fadeInDown">
                                <li class="title">
                                    Notification <span class="badge pull-right">0</span>
                                </li>
                                <li class="message">
                                    No new notification
                                </li>
                            </ul>
                        </li>
                        <li class="dropdown danger">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-star-half-o"></i> 4</a>
                            <ul class="dropdown-menu danger  animated fadeInDown">
                                <li class="title">
                                    Notification <span class="badge pull-right">4</span>
                                </li>
                                <li>
                                    <ul class="list-group notifications">
                                        <a href="#">
                                            <li class="list-group-item">
                                                <span class="badge">1</span> <i class="fa fa-exclamation-circle icon"></i> new registration
                                            </li>
                                        </a>
                                        <a href="#">
                                            <li class="list-group-item">
                                                <span class="badge success">1</span> <i class="fa fa-check icon"></i> new orders
                                            </li>
                                        </a>
                                        <a href="#">
                                            <li class="list-group-item">
                                                <span class="badge danger">2</span> <i class="fa fa-comments icon"></i> customers messages
                                            </li>
                                        </a>
                                        <a href="#">
                                            <li class="list-group-item message">
                                                view all
                                            </li>
                                        </a>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <li class="dropdown profile">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><sec:authentication property="principal.username"></sec:authentication><span class="caret"></span></a>
                            <ul class="dropdown-menu animated fadeInDown">
                                <%--<li class="profile-img">--%>
                                <%--<img src="../img/profile/picjumbo.com_HNCK4153_resize.jpg" class="profile-img">--%>
                                <%--</li>--%>
                                <li>
                                    <div class="profile-info">
                                        <h4 class="username"><sec:authentication property="principal.username"></sec:authentication></h4>
                                        <div class="btn-group margin-bottom-2x" role="group">
                                            <button type="button" class="btn btn-default" id="logoutBtn"  name="logoutBtn"><i class="fa fa-sign-out"></i> Logout</button>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
            <div class="side-menu sidebar-inverse">
                <nav class="navbar navbar-default" role="navigation">
                    <div class="side-menu-container">
                        <div class="navbar-header">
                            <a class="navbar-brand" href="#">
                                <div class="icon fa fa-paper-plane"></div>
                                <div class="title">Property Service MS</div>
                            </a>
                            <button type="button" class="navbar-expand-toggle pull-right visible-xs">
                                <i class="fa fa-times icon"></i>
                            </button>
                        </div>
                        <ul class="nav navbar-nav">
                            <li>
                                <a href="../index.jsp">
                                    <span class="icon fa fa-tachometer"></span><span class="title">Dashboard</span>
                                </a>
                            </li>
                            <li class="active panel panel-default dropdown">
                                <a data-toggle="collapse" href="#dropdown-element">
                                    <span class="icon fa fa-desktop"></span><span class="title">缴费管理</span>
                                </a>
                                <!-- Dropdown level 1 -->
                                <div id="dropdown-element" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <ul class="nav navbar-nav">
                                            <li><a href="./query.jsp">缴费情况查询</a>
                                            </li>
                                            <!-- 具有管理员权限 -->
                                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                                <li><a href="./rule.jsp">缴费细则编辑</a>
                                                </li>
                                                <li><a href="#">缴费规则分配</a>
                                                </li>
                                                <li><a href="./interface.jsp">缴费接口配置</a>
                                                </li>
                                            </sec:authorize>
                                        </ul>
                                    </div>
                                </div>
                            </li>
                            <li class="panel panel-default dropdown">
                                <a data-toggle="collapse" href="#dropdown-table">
                                    <span class="icon fa fa-table"></span><span class="title">通知管理</span>
                                </a>
                                <!-- Dropdown level 1 -->
                                <div id="dropdown-table" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <ul class="nav navbar-nav">
                                            <li><a href="../notice/individual.jsp">推送通知至个别用户</a>
                                            </li>
                                            <li><a href="../notice/announce.jsp">推送公告至全部用户</a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </li>
                            <li class="panel panel-default dropdown">
                                <a data-toggle="collapse" href="#dropdown-form">
                                    <span class="icon fa fa-file-text-o"></span><span class="title">用户管理</span>
                                </a>
                                <!-- Dropdown level 1 -->
                                <div id="dropdown-form" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <ul class="nav navbar-nav">
<sec:authorize access="hasRole('ROLE_ADMIN')">
                                            <li><a href="../user/power.jsp">权限管理</a>
                                            </li>
    </sec:authorize>
                                            <li><a href="../user/info.jsp">用户资料查询</a>
                                            </li>
                                            <li><a href="../user/check.jsp">用户房屋关系审查</a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </li>

                            <!-- Dropdown-->
                            <li class="panel panel-default dropdown">
                                <a data-toggle="collapse" href="#component-example">
                                    <span class="icon fa fa-cubes"></span><span class="title">Components</span>
                                </a>
                                <!-- Dropdown level 1 -->
                                <div id="component-example" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <ul class="nav navbar-nav">
                                            <li><a href="components/pricing-table.html">Pricing Table</a>
                                            </li>
                                            <li><a href="components/chartjs.html">Chart.JS</a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </li>
                            <!-- Dropdown-->
                            <li class="panel panel-default dropdown">
                                <a data-toggle="collapse" href="#dropdown-example">
                                    <span class="icon fa fa-slack"></span><span class="title">Page Example</span>
                                </a>
                                <!-- Dropdown level 1 -->
                                <div id="dropdown-example" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <ul class="nav navbar-nav">
                                            <li><a href="pages/login.html">Login</a>
                                            </li>
                                            <li><a href="pages/index.html">Landing Page</a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </li>
                            <!-- Dropdown-->
                            <li class="panel panel-default dropdown">
                                <a data-toggle="collapse" href="#dropdown-icon">
                                    <span class="icon fa fa-archive"></span><span class="title">Icons</span>
                                </a>
                                <!-- Dropdown level 1 -->
                                <div id="dropdown-icon" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <ul class="nav navbar-nav">
                                            <li><a href="icons/glyphicons.html">Glyphicons</a>
                                            </li>
                                            <li><a href="icons/font-awesome.html">Font Awesomes</a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <a href="license.html">
                                    <span class="icon fa fa-thumbs-o-up"></span><span class="title">License</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <!-- /.navbar-collapse -->
                </nav>
            </div>
            <!-- Main Content -->
            <div class="container-fluid">
                <div class="side-body">
                    <div class="page-title">
                        <span class="title">缴费规则配置</span>
                        <div class="description">完成缴费版本分配以及住户与房屋的关系绑定</div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="card">
                                <div class="card-header">
                                    <div class="card-title">
                                        <div class="title">缴费规则分配</div>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <div class="panel panel-default">
                                        <!-- Default panel contents -->
                                        <div class="panel-heading">规则分配表</div>
                                        <%--<div class="panel-body">--%>
                                            <%--<p>Some default panel content here. Nulla vitae elit libero, a pharetra augue. Aenean lacinia bibendum nulla sed consectetur. Aenean eu leo quam. Pellentesque ornare sem lacinia quam venenatis vestibulum. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>--%>
                                        <%--</div>--%>
                                        <!-- Table -->
                                        <table id="ruleTable" class="table">
                                            <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>房屋ID</th>
                                                <th>房屋名称</th>
                                                <th>房屋面积</th>
                                                <th>物业费版本</th>
                                                <th>公共水电费缴费版本</th>
                                                <th>停车费版本</th>
                                                <th>操作</th>
                                            </tr>
                                            </thead>
                                            <tbody id="ruleTBody">
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="card">
                                <div class="card-header">
                                    <div class="card-title">
                                    <div class="title">房屋用户绑定</div>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <div class="panel panel-default">
                                        <!-- Default panel contents -->
                                        <div class="panel-heading">
                                            <span>房屋-用户 关系表</span>
                                            <span class="add-popup" style="cursor:pointer;float:right;">新增</span>
                                        </div>
                                        <%--<div class="panel-body">--%>
                                            <%--<p>Some default panel content here. Nulla vitae elit libero, a pharetra augue. Aenean lacinia bibendum nulla sed consectetur. Aenean eu leo quam. Pellentesque ornare sem lacinia quam venenatis vestibulum. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>--%>
                                        <%--</div>--%>
                                        <!-- Table -->
                                        <table id="urTable" class="table">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>房屋ID</th>
                                                    <th>房屋名称</th>
                                                    <th>用户</th>
                                                    <th>操作</th>
                                                </tr>
                                            </thead>
                                            <tbody id="urTBody">
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="add-popup" class="popup">
            <form class="form-horizontal">
                <fieldset>
                    <!-- Form Name -->
                    <legend>新增用户房屋关系</legend>
                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="roomList">请选择房屋：</label>
                        <div class="col-md-6">
                            <select id="roomList" name="roomList"  class="form-control input-md"  >
                            </select>
                            <span class="help-block">选择房屋</span>
                        </div>
                    </div>
                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="userList">请选择用户：</label>
                        <div class="col-md-6">
                            <select id="userList" name="userList"  class="form-control input-md"  >
                            </select>
                            <span class="help-block">选择要绑定的用户</span>
                        </div>
                    </div>
                    <!-- Button (Double) -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="cancel"></label>
                        <div class="col-md-6">
                            <button type="button" id="cancel" class="btn btn-default cancel" style="background-color: transparent">Cancel</button>
                            <button type="submit" id="save" class="btn btn-success submit">OK</button>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
        <footer class="app-footer">
            <div class="wrapper">
                <span class="pull-right">2.1 <a href="#"><i class="fa fa-long-arrow-up"></i></a></span> © 2015 Copyright.
            </div>
        </footer>
    </div>
    <!-- Javascript Libs -->
    <script type="text/javascript" src="../../lib/js/jquery.min.js"></script>
    <script type="text/javascript" src="../../lib/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../lib/js/Chart.min.js"></script>
    <script type="text/javascript" src="../../lib/js/bootstrap-switch.min.js"></script>

    <script type="text/javascript" src="../../lib/js/jquery.matchHeight-min.js"></script>
    <script type="text/javascript" src="../../lib/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="../../lib/js/dataTables.bootstrap.min.js"></script>
    <script type="text/javascript" src="../../lib/js/select2.full.min.js"></script>
    <script type="text/javascript" src="../../lib/js/ace/ace.js"></script>
    <script type="text/javascript" src="../../lib/js/ace/mode-html.js"></script>
    <script type="text/javascript" src="../../lib/js/ace/theme-github.js"></script>
    <script type="text/javascript" src="../../lib/js/jquery-prompt21.js"></script>
    <!-- Javascript -->
    <script type="text/javascript" src="../../js/app.js"></script>
    <script type="text/javascript" src="../../js/payment/ruleDistribute.js"></script>
</body>

</html>
