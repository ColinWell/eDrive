<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Cxy
  Date: 2018/4/13
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>

<head>
    <title>推送公告至全体用户</title>
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
    <link rel="stylesheet" type="text/css" href="../../lib/css/material-wfont.min.css">
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
                        <li>通知管理</li>
                        <li class="active">推送公告至全部用户</li>
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
                        <li class="panel panel-default dropdown">
                            <a data-toggle="collapse" href="#dropdown-element">
                                <span class="icon fa fa-desktop"></span><span class="title">缴费管理</span>
                            </a>
                            <!-- Dropdown level 1 -->
                            <div id="dropdown-element" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <ul class="nav navbar-nav">
                                        <li><a href="../payment/query.jsp">缴费情况查询</a>
                                        </li>
                                        <!-- 具有管理员权限 -->
                                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                                            <li><a href="../payment/rule.jsp">缴费细则编辑</a>
                                            </li>
                                            <li><a href="../payment/ruleDistribute.jsp">缴费规则分配</a>
                                            </li>
                                            <li><a href="../payment/interface.jsp">缴费接口配置</a>
                                            </li>
                                        </sec:authorize>
                                    </ul>
                                </div>
                            </div>
                        </li>
                        <li class="active panel panel-default dropdown">
                            <a data-toggle="collapse" href="#dropdown-table">
                                <span class="icon fa fa-table"></span><span class="title">通知管理</span>
                            </a>
                            <!-- Dropdown level 1 -->
                            <div id="dropdown-table" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <ul class="nav navbar-nav">
                                        <li><a href="./individual.jsp">推送通知至个别用户</a>
                                        </li>
                                        <li><a href="#">推送公告至全部用户</a>
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
                    <span class="title">公告管理</span>
                    <div class="description">便捷地查询或发布公告信息</div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <div class="card">
                            <div class="card-header">
                                <div class="card-title">
                                    <div class="title">公告管理</div>
                                </div>
                            </div>
                            <div class="card-body">
                                <table id="menuTable" class="table" cellspacing="0" width="100%">
                                    <tr>
                                        <td>
                                            <label>起始日期</label>
                                        </td>
                                        <td>
                                            <input name="dateBegin" type="date"/>
                                        </td>
                                        <td>
                                            <label>终止日期</label>
                                        </td>
                                        <td>
                                            <input name="dateEnd" type="date">
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td>
                                            <a class="add-popup"><i name="addBtn" class="fa fa-plus"></i>&nbsp;新增</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <%--<td>--%>
                                        <%--<label>缴费类型</label>--%>
                                        <%--</td>--%>
                                        <%--<td>--%>
                                        <%--<select name="payType">--%>
                                        <%--<option>所有</option>--%>
                                        <%--<option value=""></option>--%>
                                        <%--</select>--%>
                                        <%--</td>--%>
                                        <td>
                                            <label>公告类型：</label>
                                        </td>
                                        <td>
                                            <select name="announceType" style="width:70px !important;">
                                                <option value="0" selected="selected">全部</option>
                                                <option value="1">紧急</option>
                                                <option value="2">一般</option>
                                            </select>
                                        </td>
                                        <td>
                                            <label>发布者：</label>
                                        </td>
                                        <td>
                                            <input type="text" name="pubUserName" value="" placeholder="输入发布者名称"/>
                                        </td>
                                            <td>
                                                <label>公告关键字：</label>
                                            </td>
                                            <td>
                                                <input type="text" name="keyWords" value="" placeholder="输入关键字"/>
                                            </td>
                                        <td>
                                            <a href="javascript:queryAnnounce();"><i name="searchBtn" class="fa fa-search"></i>&nbsp;查询</a>
                                        </td>
                                    </tr>
                                </table>
                                <table id="announceQuery" class="table table-striped" cellspacing="0" width="100%">
                                    <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>公告类型</th>
                                        <th>公告发布时间</th>
                                        <th>公告主题</th>
                                        <th>公告内容</th>
                                        <th>发布者</th>
                                    </tr>
                                    </thead>
                                    <tbody id="queryTbody">
                                    <tr>
                                        <td>Tiger Nixon</td>
                                        <td>System Architect</td>
                                        <td>Edinburgh</td>
                                        <td>61</td>
                                        <td>2011/04/25</td>
                                        <td></td>
                                    </tr>
                                    </tbody>
                                </table>
                                <div id="add-popup" class="popup">
                                    <form class="form-horizontal">
                                        <fieldset>
                                            <!-- Form Name -->
                                            <legend>发布全体公告</legend>
                                            <!-- Text input-->
                                            <div class="form-group">
                                                <label class="col-md-4 control-label" for="name.type">公告类型</label>
                                                <div class="col-md-6">
                                                    <select id="name.type" name="name.type" class="form-control input-md">
                                                        <option value="1" selected>紧急</option>
                                                        <option value="2">一般</option>
                                                    </select>
                                                    <span class="help-block">选择公告类型</span>
                                                </div>
                                            </div>
                                            <!-- Text input-->
                                            <div class="form-group">
                                                <label class="col-md-4 control-label" for="name.title">公告主题</label>
                                                <div class="col-md-6">
                                                    <input id="name.title" name="name.title" type="text" placeholder="" class="form-control input-md"  value="">
                                                    <span class="help-block">简短描述公告主题</span>
                                                </div>
                                            </div>
                                            <!-- Text input-->
                                            <div class="form-group">
                                                <label class="col-md-4 control-label" for="name.content">公告内容</label>
                                                <div class="col-md-6">
                                                    <textarea class="form-control" id="name.content" name="name.content"></textarea>
                                                    <span class="help-block">输入公告的内容</span>
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
<script type="text/javascript" src="../../lib/js/jquery.dateFormat.min.js"></script>
<script type="text/javascript" src="../../lib/js/jquery-prompt21.js"></script>
<!-- Javascript -->
<script type="text/javascript" src="../../js/app.js"></script>
<script type="text/javascript" src="../../js/notice/announce.js"></script>
</body>

</html>

