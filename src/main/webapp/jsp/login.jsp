<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录界面</title>
</head>
<body>
	<h3>登录界面</h3>
	
		<form id="loginForm" action="/login" method="post">
			<table>
			    <tr><td>User:</td><td><input type='text' name='j_username' value=''></td></tr>
			    <tr><td>Password:</td><td><input type='password' name='j_password'/></td></tr>
			    <tr><td colspan='2'><input name="submit" type="submit" value="Login"/></td></tr>
			</table>
			<c:if test="${param.error != null}">
				<div>
					<p>Invalid username and password.</p>
				</div>
			</c:if>
		</form>
	
</body>
</html>