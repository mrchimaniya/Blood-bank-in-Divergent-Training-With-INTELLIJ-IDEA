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
	<center>
		<b>Register Yourself For Donate Blood</b>
	</center>
	<br>
	<form method="POST" action="/donor/registerdonorrequest">

		<table align="center" style="font-size: 20; text-align: justify;">
			<tr>
				<td>Enter Your Name</td>
				<td><input type="text" name="name" /></td>
				<td>Enter Your Address</td>
				<td><input type="text" name="address" /></td>
			</tr>
			<tr>
				<td>Select Your Gender</td>
				<td><select name="gender">
						<option>Male</option>
						<option>Female</option>
						<option>Others</option>
				</select></td>
				<td>Do You Have/Had Any Disease</td>
				<td><input type="text" name="disease" /></td>
			</tr>
			<tr>
				<td>Select Your Age</td>
				<td><input type="number" min="17" max="65" name="age" /></td>
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
				<td>Select Nearest Hospital</td>
				<td><select name="nearestbloodbank">
						<option>Bombay Hospital</option>
						<option>CHL Hospital</option>
						<option>Choithram Hospital</option>
						<option>Apollo Hospital</option>
						<option>MY Hospital</option>
				</select></td>
				<td>Enter Your Phone Number</td>
				<td><input type="tel" pattern="[6789][0-9]{9}" name="phone"></td>
			</tr>
			<tr>
				<td>Enter New Password</td>
				<td><input type="password" name="password" /></td>
				<td>Enter Your Email</td>
				<td><input name="email" type="email"
					pattern="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{1,63}$" /></td>
			</tr>
			<tr>
				<td>Re-Enter Your New Password</td>
				<td><input type="password" name="rePassword" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Register" /></td>
			</tr>
		</table>

	</form>
</body>
</html>