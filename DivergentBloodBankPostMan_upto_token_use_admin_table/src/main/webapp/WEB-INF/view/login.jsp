<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body align="center">
	<jsp:include page="/WEB-INF/view/mainmenu.jsp" />

	<form>
		<input type="submit" value="User Login" formaction="/donor/userlogin" />
		&nbsp; &nbsp; &nbsp; <input type="submit" value="Admin Login"
			formaction="/admin/adminlogin" /> &nbsp; &nbsp; &nbsp;
	</form>
</body>
</html>