<%--
  Created by IntelliJ IDEA.
  User: 85732
  Date: 2018/3/14
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Flat Admin V.2 - Free Bootstrap Admin Templates</title>
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
                        <li>报修管理</li>
                        <li class="active">报修申请细则编辑</li>
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
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Emily Hart <span class="caret"></span></a>
                        <ul class="dropdown-menu animated fadeInDown">
                            <li class="profile-img">
                                <img src="../../img/profile/picjumbo.com_HNCK4153_resize.jpg" class="profile-img">
                            </li>
                            <li>
                                <div class="profile-info">
                                    <h4 class="username">Emily Hart</h4>
                                    <p>emily_hart@email.com</p>
                                    <div class="btn-group margin-bottom-2x" role="group">
                                        <button type="button" class="btn btn-default"><i class="fa fa-user"></i> Profile</button>
                                        <button type="button" class="btn btn-default"><i class="fa fa-sign-out"></i> Logout</button>
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
                        <li class="active">
                            <a href="index.jsp">
                                <span class="icon fa fa-tachometer"></span><span class="title">Dashboard</span>
                            </a>
                        </li>
                        <li class="panel panel-default dropdown">
                            <a data-toggle="collapse" href="#dropdown-element">
                                <span class="icon fa fa-desktop"></span><span class="title">缴费管理</span>
                            </a>
                            <!-- Dropdown level 1 -->
                            <div id="dropdown-element" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <ul class="nav navbar-nav">
                                        <li><a href="ui-kits/grid.html">缴费情况查询</a>
                                        </li>
                                        <!-- 具有管理员权限 -->
                                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                                            <li><a href="ui-kits/theming.html">缴费细则编辑</a>
                                            </li>
                                            <li><a href="ui-kits/button.html">缴费接口配置</a>
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
                                        <li><a href="table/table.html">推送通知至个别用户</a>
                                        </li>
                                        <li><a href="table/datatable.html">推送公告至全部用户</a>
                                        </li>
                                        <li><a href="table/datatable.html">通知查询</a>
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
                                        <li><a href="form/ui-kits.html">权限管理</a>
                                        </li>
                                        <li><a href="form/ui-kits.html">用户资料查询</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </li>

                        <!-- Dropdown-->
                        <li class="panel panel-default dropdown">
                            <a data-toggle="collapse" href="#component-example">
                                <span class="icon fa fa-cubes"></span><span class="title">装修申请管理</span>
                            </a>
                            <!-- Dropdown level 1 -->
                            <div id="component-example" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <ul class="nav navbar-nav">
                                        <li><a href="DecorationManage.jsp">装修申请审核</a>
                                        </li>
                                        <li><a href="DecorationRule.jsp">装修细则编辑</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </li>
                        <!-- Dropdown-->
                        <li class="panel panel-default dropdown">
                            <a data-toggle="collapse" href="#dropdown-example">
                                <span class="icon fa fa-slack"></span><span class="title">报修申请管理</span>
                            </a>
                            <!-- Dropdown level 1 -->
                            <div id="dropdown-example" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <ul class="nav navbar-nav">
                                        <li><a href=RepairManage.jsp">报修申请管理</a>
                                        </li>
                                        <li><a href="RepairRule.jsp">报修规则编辑</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </li>
                        <!-- Dropdown-->
                        <li class="panel panel-default dropdown">
                            <a data-toggle="collapse" href="#dropdown-icon">
                                <span class="icon fa fa-archive"></span><span class="title">紧急联系人</span>
                            </a>
                            <!-- Dropdown level 1 -->
                            <div id="dropdown-icon" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <ul class="nav navbar-nav">
                                        <li><a href="EmergencyManage.jsp">紧急联系人设置</a>
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
                    <span class="title">报修申请细则编辑</span>
                    <div class="description"></div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <div class="card">
                            <div class="card-header">

                                <div class="card-title">
                                    <div class="title">报修申请细则</div>
                                </div>
                            </div>

                            <div class="card-body">
                                报修细则：1.在17点以后进行报修，维修时间要在第二天之后。2.要确定损坏，才能报修。3.公用设备和家用设备都可以进行报修。
                                <div class="sub-title">细则编辑</div>
                                <div>
                                    <input type="text" class="form-control" placeholder="Text input">
                                </div>
                                <button type="submit" class="btn btn-default" id="rRuleChange">修改</button>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <footer class="app-footer">
        <div class="wrapper">
            <span class="pull-right">2.1 <a href="#"><i class="fa fa-long-arrow-up"></i></a></span> © 2015 Copyright.
        </div>
    </footer>
    <div>
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
        <!-- Javascript -->
        <script type="text/javascript" src="../../js/app.js"></script>
</body>
</html>
