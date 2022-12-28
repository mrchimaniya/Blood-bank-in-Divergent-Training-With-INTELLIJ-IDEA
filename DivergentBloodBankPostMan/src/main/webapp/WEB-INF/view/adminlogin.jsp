<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/login.jsp" />
	<center>
		<b>Admin Login Page</b>
	</center>
	<br>
	<center>
		<span style="color: red">Alert! Only Admin Can Log-In Here<span />
	</center>
	<br>
	<form action="/admin/admincontrol">
		<table align="center" style="text-align: justify;">
			<tr>
				<td>Enter Admin Name</td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td>Enter Admin Password</td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Login" /></td>
			</tr>
		</table>
	</form>
</body>
</html>