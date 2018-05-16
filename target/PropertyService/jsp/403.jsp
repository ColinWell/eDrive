<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>无法访问</title>
	<script language="javascript" type="text/javascript">
		var i = 5;
		var intervalid;
		var to = "http://"+window.location.host+"/jsp/login.jsp";
		intervalid = setInterval("fun()", 1000);
		function fun() {
			if (i == 0) {
				window.location.href = to;
				clearInterval(intervalid);
			}
			document.getElementById("t").innerHTML = i;
			i--;
		}
	</script>
</head>
<body>
	<h3>您没有权限访问当前页面，请用相应的身份登录</h3>
	<h4><span id = t>5</span>秒后自动跳转到登录页面，如果没有自动跳转，点击<a href="javascript:window.location.href=to;" >手动跳转...</a></h4>
</body>
</html>