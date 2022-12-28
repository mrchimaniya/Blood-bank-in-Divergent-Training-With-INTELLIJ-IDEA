<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/adminlogin.jsp" />
	<center>Choose An Operation</center>
	<table align="center" style="text-align: justify">
		<tr>
			<td><a href="/admin/alldonors/0?sortField=name&sortDir=asc"><button>View
						All Donors</button></a></td>
			<td><a href="/admin/adminbloodform"><button>View
						Donors For Specific Blood</button></a></td>
			<td><a href="/admin/adminphoneform"><button>View
						Donors By Phone No.</button></a></td>
		</tr>
	</table>
	</form>
	<hr>
</body>
</html>