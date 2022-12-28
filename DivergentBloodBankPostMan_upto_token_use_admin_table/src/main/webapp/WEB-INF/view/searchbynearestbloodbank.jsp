<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/search.jsp" />
	<br>
	<br>
	<br>
	<br>
	<center style="color: red">Search Blood In Specific Hospital</center>
	<br>
	<form action="/donor/searchbynearestbloodbankrequest/0" method="GET">
		<table align="center" style="text-align: justify;">
			<tr>
				<td>Select A Blood Group</td>
				<td><select name="bloodGroup">
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
				<td><select name="nearestBloodBank">
						<option>Bombay Hospital</option>
						<option>CHL Hospital</option>
						<option>Choithram Hospital</option>
						<option>Apollo Hospital</option>
						<option>MY Hospital</option>
				</select></td>
			</tr>
			<tr>
				<td><input type="submit" value="Search" /></td>
			</tr>
		</table>
		<input type="hidden" name="sortDir" value="asc" /> <input
			type="hidden" name="sortField" value="name" />
	</form>
</body>
</html>