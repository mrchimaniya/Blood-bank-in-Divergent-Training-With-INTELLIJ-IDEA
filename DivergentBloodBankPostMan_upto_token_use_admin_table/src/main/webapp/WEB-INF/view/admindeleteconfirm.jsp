<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/admincontrol.jsp"></jsp:include>
	<form>
		<br>
		<table align="center" border="5"
			style="border-color: gold; font-size: 25px; text-align: justify">
			<tr>
				<td>Name</td>
				<td>${name}</td>
			</tr>
			<tr>
				<td>Phone</td>
				<td>${phone}<input type="hidden" name="phone" value="${phone }" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Yes"
					formaction="/admin/adminyes" formmethod="POST" /></td>
				<td><input type="submit" value="No" formaction="/admin/adminno"
					formmethod="GET" /></td>
			<tr>
		</table>
	</form>
</body>
</html>