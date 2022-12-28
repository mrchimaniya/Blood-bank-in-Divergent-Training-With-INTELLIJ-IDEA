<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/login.jsp"></jsp:include>
	<br>
	<b align="center" style="color: red">Admin Editable Form</b>
	<br>
	<br>
	<form method="POST" action="/admin/admineditdonor">
		<table align="center" style="font-size: 20; text-align: justify;">
			<tr>
				<td>Enter Your Name</td>
				<td><input type="text" name="name" value="${donor.getName() }" /></td>
				<td>Enter Your Address</td>
				<td><input type="text" name="address"
					value="${donor.getAddress() }" /></td>
			</tr>
			<tr>
				<td>Select Your Gender</td>
				<td><span style="color: red">${donor.getGender()}</span> <select
					name="gender">
						<option>Male</option>
						<option>Female</option>
						<option>Others</option>
				</select></td>
				<td>Do You Have/Had Any Disease</td>
				<td><input type="text" name="disease"
					value="${donor.getDisease() }" /></td>
			</tr>
			<tr>
				<td>Select Your Age</td>
				<td><input type="number" min="17" max="65" name="age"
					value="${donor.getAge() }" /></td>
				<td>Select Your Blood Group</td>
				<td><span style="color: red">${donor.getBloodgroup() }</span> <select
					name="bloodgroup">
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
				<td>Select Nearest Hospital</td>
				<td><span style="color: red">${donor.getNearestbloodbank() }</span>
					<select name="nearestbloodbank">
						<option>Bombay Hospital</option>
						<option>CHL Hospital</option>
						<option>Choithram Hospital</option>
						<option>Apollo Hospital</option>
						<option>MY Hospital</option>
				</select></td>
				<td>Enter Your Phone Number</td>
				<td><input type="tel" pattern="[6789][0-9]{9}" name="phone"
					value="${donor.getPhone() }" />
			</tr>
			<tr>
				<td>Enter New Password</td>
				<td><input type="text" name="password"
					value="${donor.getPassword() }" /></td>
				<td>Enter Your Email</td>
				<td><input name="email" type="email"
					pattern="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{1,63}$"
					value="${donor.getEmail() }" /></td>
			</tr>
			<tr>
				<td>Re-Enter Your New Password</td>
				<td><input type="text" name="rePassword"
					value="${donor.getPassword() }" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Update" /></td>
			</tr>
		</table>
		<input type="hidden" name="oldphone" value="${donor.getPhone() }" />
	</form>
</body>
</html>