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
	<br>
	<center>User Login Page</center>
	<br>
	<form action="/donor/userloginrequest" method="GET">
		<table align="center" style="text-align: justify;">
			<tr>
				<td>Enter Phone Number</td>
				<td><input type="text" name="phone" /></td>
			</tr>
			<tr>
				<td>Enter Password</td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td><a href="/donor/forgetpassword">Click Here To Forget
						Password</a></td>
			</tr>
			<tr>
				<td><input type="submit" value="Search" /></td>
			</tr>
		</table>
	</form>
</body>
</html>