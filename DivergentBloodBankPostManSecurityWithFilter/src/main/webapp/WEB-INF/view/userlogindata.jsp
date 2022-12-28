<%@page import="com.divergentsl.entity.Donor"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/userlogin.jsp" />
	<form action="/donor/updateuser">
		<table align="center" border="5"
			style="border-color: gold; font-size: 20;">
			<tr>
				<td>Enter Your Name</td>
				<td>${donor.getName() }</td>
			</tr>
			<tr>
				<td>Select Your Gender</td>
				<td>${donor.getGender() }</td>
			</tr>
			<tr>
				<td>Select Your Age</td>
				<td>${donor.getAge() }</td>
			</tr>
			<tr>
				<td>Select Nearest Hospital</td>
				<td>${donor.getNearestbloodbank() }</td>
			</tr>
			<tr>
				<td>Enter Your Email</td>
				<td>${donor.getEmail() }</td>
			</tr>
			<tr>
				<td>Enter Your Address</td>
				<td>${donor.getAddress() }</td>
			</tr>
			<tr>
				<td>Do You Have/Had Any Disease</td>
				<td>${donor.getDisease() }</td>
			</tr>
			<tr>
				<td>Select Your Blood Group</td>
				<td>${donor.getBloodgroup() }</td>
			</tr>
			<tr>
				<td>Enter Your Phone Number</td>
				<td>${donor.getPhone()}</td>
			</tr>
		</table>
		<input type="hidden" name="phone" value="${donor.getPhone()}" />
		<h3>Click Here To Update Profile</h3>
		<input type="submit" value="Update Record" style="color: green" />
	</form>
</body>
</html>