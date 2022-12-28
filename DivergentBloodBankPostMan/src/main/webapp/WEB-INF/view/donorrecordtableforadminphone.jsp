<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="false"%>
<html>
<head>
<style>
.pagination {
	display: flex;
	justify-content: center;
}

.pagination a {
	color: black;
	float: left;
	padding: 8px 16px;
	text-decoration: none;
	transition: background-color .3s;
	border: 1px solid #ddd;
}

.pagination a.active {
	background-color: #4CAF50;
	color: white;
	border: 1px solid #4CAF50;
}

.pagination a:hover:not(.active) {
	background-color: #ddd;
}
</style>
</head>

<body>
	<jsp:include page="/WEB-INF/view/admincontrol.jsp" />
	<h1 style="color: green" align="center">${bloodFilter}</h1>
	<table align="center" border="5"
		style="border-color: gold; font-size: 20; text-align: justify">
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
					<td>
						<form action="/admin/admineditdonorform">
							<input type="hidden" name="phone"
								value="${donorRecord.getPhone() }" /> <input type="submit"
								value="Change Details" />
						</form>
					</td>
					<td>
						<form action="/admin/admindeleteconfirm">
							<input type="hidden" name="name"
								value="${donorRecord.getName() }" /> <input type="hidden"
								name="phone" value="${donorRecord.getPhone() }" /> <input
								type="submit" value="Delete" />
						</form>
					</td>
				</tr>
			</c:forEach>
	</table>
	<br>

</body>
</html>




