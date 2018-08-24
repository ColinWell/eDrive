<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Login</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- Fonts -->
	<link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:300,400' rel='stylesheet' type='text/css'>
	<link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900' rel='stylesheet' type='text/css'>
	<!-- CSS Libs -->
	<link rel="stylesheet" type="text/css" href="../lib/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="../lib/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="../lib/css/animate.min.css">
	<link rel="stylesheet" type="text/css" href="../lib/css/bootstrap-switch.min.css">
	<link rel="stylesheet" type="text/css" href="../lib/css/checkbox3.min.css">
	<link rel="stylesheet" type="text/css" href="../lib/css/jquery.dataTables.min.css">
	<link rel="stylesheet" type="text/css" href="../lib/css/dataTables.bootstrap.css">
	<link rel="stylesheet" type="text/css" href="../lib/css/select2.min.css">
	<!-- CSS App -->
	<link rel="stylesheet" type="text/css" href="../css/style.css">
	<link rel="stylesheet" type="text/css" href="../css/themes/flat-blue.css">

</head>

<body class="flat-blue login-page">
<div class="container">
	<div class="login-box">
		<div>
			<div class="login-form row">
				<div class="col-sm-12 text-center login-header">
					<i class="login-logo fa fa-connectdevelop fa-5x"></i>
					<h4 class="login-title">eDrive Management System</h4>
				</div>
				<div class="col-sm-12">
					<div class="login-body">
						<div class="progress hidden" id="login-progress">
							<div class="progress-bar progress-bar-success progress-bar-striped active" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
								Log In...
							</div>
						</div>
						<form id="loginForm"  method="post">
							<div class="control">
								<input type="text" class="form-control" name="j_username"  placeholder="please input your account" />
							</div>
							<div class="control">
								<input type="password" class="form-control" name="j_password"  placeholder="please input your password" />
							</div>
							<div class="login-button text-center">
								<input id="loginBtn" type="button" class="btn btn-primary" value="Login">
							</div>
						</form>
					</div>
					<c:if test="${param.error != null}">
						<div>
							<p>Invalid username and password.</p>
						</div>
					</c:if>
					<div class="login-footer">
						<span class="text-right"><a href="#" class="color-white">Forgot password?</a></span>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Javascript Libs -->
<script type="text/javascript" src="../lib/js/jquery.min.js"></script>
<script type="text/javascript" src="../lib/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../lib/js/Chart.min.js"></script>
<script type="text/javascript" src="../lib/js/bootstrap-switch.min.js"></script>

<script type="text/javascript" src="../lib/js/jquery.matchHeight-min.js"></script>
<script type="text/javascript" src="../lib/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="../lib/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="../lib/js/select2.full.min.js"></script>
<script type="text/javascript" src="../lib/js/ace/ace.js"></script>
<script type="text/javascript" src="../lib/js/ace/mode-html.js"></script>
<script type="text/javascript" src="../lib/js/ace/theme-github.js"></script>
<!-- Javascript -->
<script type="text/javascript" src="../js/app.js"></script>
<script type="text/javascript">
	$('#loginBtn').click(function() {
		var timestamp = Date.parse(new Date());
		var username = loginForm.j_username.value;
		var password = loginForm.j_password.value;
		$.ajax({
			type: "POST",
			url: "/login?j_username="+username+"&j_password="+password,
			contentType: "application/json", //必须有
//            dataType: "json", //表示返回值类型，不必须
			data: JSON.stringify({
//                j_username:'zhang',
//                //password: 'e10adc3949ba59abbe56e057f20f883e'
//                j_password:'123'
			}),  //相当于 //data: "{'str1':'foovalue', 'str2':'barvalue'}",
			success: function (jsonResult) {

				console.log(jsonResult,"jsonResult");
				console.log(jsonResult.code,"jsonResult");
				console.log(JSON.stringify(jsonResult));
				console.log(jsonResult.loginResult);
				//var info = jQuery.parseJSON(jsonResult);
			//	alert("登录成功");
				if(jsonResult.loginResult == "true") {
					window.location.href = "/jsp/index.jsp";
				}
			},
			error: function (jsonResult) {
				console.log("what");
				console.log(jsonResult);
			}

		});
	});
</script>
</body>

</html>