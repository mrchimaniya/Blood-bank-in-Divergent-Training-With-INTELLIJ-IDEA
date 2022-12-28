<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="false"%>
<html>
<body>
	<br>
	<jsp:include page="/WEB-INF/view/search.jsp" />
	<h1 style="color: green" align="center">${bloodFilter}</h1>
	<table align="center" border="5"
		style="border-color: gold; font-size: 20">
		<tr>
			<th>Name</th>
			<th>Gender</th>
			<th>Age</th>
			<th>BloodGroup</th>
			<th>NearestBloodBank</th>
			<th>Phone</th>
			<th>Email</th>
			<th>Address</th>
			<th>Disease</th>
		<tr>

			<c:forEach var="donorRecord" items="${donorsRecord}">

				<tr style="color: brown;">
					<td>${donorRecord.getName() }</td>
					<td>${donorRecord.getGender() }</td>
					<td>${donorRecord.getAge() }</td>
					<td>${donorRecord.getBloodgroup() }</td>
					<td>${donorRecord.getNearestbloodbank() }</td>
					<td>${donorRecord.getPhone() }</td>
					<td>${donorRecord.getEmail() }</td>
					<td>${donorRecord.getAddress() }</td>
					<td>${donorRecord.getDisease() }</td>
				</tr>

			</c:forEach>
	</table>
</body>
</html>