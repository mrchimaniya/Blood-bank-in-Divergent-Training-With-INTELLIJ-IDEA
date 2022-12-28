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
	<br>
	<br>
	<form>
		<input type="submit" value="Search Blood In Any Area"
			formaction="/donor/searchbyarea" /> &nbsp; &nbsp; &nbsp; <input
			type="submit" value="Search Blood In Specific Hospital"
			formaction="/donor/searchbynearestbloodbank" /> &nbsp; &nbsp; &nbsp;
	</form>
</body>
</html>
</html>