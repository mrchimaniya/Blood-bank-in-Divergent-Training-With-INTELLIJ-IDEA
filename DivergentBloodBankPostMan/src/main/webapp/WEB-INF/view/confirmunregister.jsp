<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/mainmenu.jsp" />
	<br>
	<br>
	<br>
	<br>
	<center style="color: red">Confirmation To Delte Your Account</center>
	<br>
	<form>
		<table align="center" style="font-size: 20px; text-align: justify">
			<tr>
				<td>Name</td>
				<td><span style="color: green">${name}</span></td>
			</tr>
			<tr>
				<td>Phone</td>
				<td><span style="color: green">${phone}</span> <input
					type="hidden" value="${phone}" name="phone" /></td>
			<tr>
			<tr>
				<td colspan="2" style="font-weight: bold; font-size: 15px">Password
					Required To Delete Your Account</td>
			</tr>
			<tr>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td><span style="color: red">Are You Sure</span></td>
			</tr>
			<tr>
				<td><input type="submit" value="Yes"
					formaction="/donor/unregisterdata" formmethod="POST" />&nbsp;
					&nbsp; <input type="submit" value="No"
					formaction="/donor/unregister" formmethod="GET" /></td>
			</tr>
		</table>
	</form>
</body>
</html>