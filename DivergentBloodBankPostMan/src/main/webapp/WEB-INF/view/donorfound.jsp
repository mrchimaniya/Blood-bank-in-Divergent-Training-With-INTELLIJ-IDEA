<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/unregister.jsp" />
	<br>
	<center style="color: blue">Your Profile Is Matched</center>
	<br>
	<form action="/donor/confirmunregister">
		<table align="center" border="5"
			style="text-align: justify; border-color: gold; font-size: 20">
			<tr>
				<td>Enter Your Name</td>
				<td>${donor.getName() }<input type="hidden"
					value="${donor.getName() }" name="name" /></td>
			</tr>
			<tr>
				<td>Select Your Blood Group</td>
				<td>${donor.getBloodgroup() }</td>
			</tr>
			<tr>
				<td>Select Nearest Hospital</td>
				<td>${donor.getNearestbloodbank() }</td>
			</tr>
			<tr>
				<td>Enter Your Phone Number</td>
				<td>${donor.getPhone() }<input type="hidden"
					value="${donor.getPhone() }" name="phone" /></td>
			</tr>
			<tr>
				<td>Enter Your Email</td>
				<td>${donor.getEmail() }</td>
			</tr>
		</table>
		<br> <input type="submit" value="Delete Your Profile"
			style="color: red" />

	</form>
</body>
</html>