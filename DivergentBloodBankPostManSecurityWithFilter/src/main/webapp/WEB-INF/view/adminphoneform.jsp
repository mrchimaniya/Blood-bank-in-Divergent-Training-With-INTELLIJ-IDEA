<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body align="center">
	<jsp:include page="/WEB-INF/view/admincontrol.jsp" />

	<br>
	<form action="/admin/donorsbyphone">
		<table align="center" style="text-align: justify">
			<tr>
				<td>Enter Phone Number</td>
				<td><input type="text" name="phone" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Search" /></td>
			</tr>
		</table>
	</form>
</body>
</html>