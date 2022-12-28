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
	<br>
	<br>
	<br>
	<center>Forget User Password</center>
	<br>
	<form action="/donor/forgetpassworddata">
		<table align="center" style="text-align: justify">
			<tr>
				<td>Enter Name</td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td>Enter Phone Number</td>
				<td><input type="text" name="phone" /></td>
			</tr>
			<tr>
				<td>Select Your Blood Group</td>
				<td><select name="bloodgroup">
						<option>A+</option>
						<option>A-</option>
						<option>B+</option>
						<option>B-</option>
						<option>O+</option>
						<option>O-</option>
						<option>AB+</option>
						<option>AB-</option>
				</select></td>
			</tr>
			<tr>
				<td><input type="submit" value="Forget" /></td>
			</tr>
		</table>
	</form>
</body>
</html>