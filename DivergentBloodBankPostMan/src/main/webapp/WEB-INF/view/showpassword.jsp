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
	<table align="center" style="text-align: center; font-size: 20px;">
		<tr>
			<td>Phone:</td>
			<td style="color: red"><b>${donor.getPhone() }</b></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td style="color: red"><b>${donor.getPassword() }</b></td>
		</tr>
	</table>
</body>
</html>