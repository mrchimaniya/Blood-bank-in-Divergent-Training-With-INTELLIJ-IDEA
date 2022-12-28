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
	<table align="center" border="5"
		style="border-color: gold; font-size: 20; text-align: justify">
		<tr>
			<th><a
				href="/admin/alldonors/${currentPage}?sortField=name&sortDir=${reverseDir}">Name</a>
			</th>
			<th><a
				href="/admin/alldonors/${currentPage}?sortField=gender&sortDir=${reverseDir}">Gender</a>
			</th>
			<th><a
				href="/admin/alldonors/${currentPage}?sortField=age&sortDir=${reverseDir}">Age</a>
			</th>
			<th><a
				href="/admin/alldonors/${currentPage}?sortField=bloodgroup&sortDir=${reverseDir}">BloodGroup</a>
			</th>
			<th><a
				href="/admin/alldonors/${currentPage}?sortField=nearestbloodbank&sortDir=${reverseDir}">NearestBloodBank</a>
			</th>
			<th><a
				href="/admin/alldonors/${currentPage}?sortField=phone&sortDir=${reverseDir}">Phone</a>
			</th>
			<th><a
				href="/admin/alldonors/${currentPage}?sortField=email&sortDir=${reverseDir}">Email</a>
			</th>
			<th><a
				href="/admin/alldonors/${currentPage}?sortField=address&sortDir=${reverseDir}">Address</a>
			</th>
			<th><a
				href="/admin/alldonors/${currentPage}?sortField=disease&sortDir=${reverseDir}">Disease</a>
			</th>
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

	<!-- Pagination -->
	<label style="border: 1; color: red; font-size: 20px">Total
		Pages : <b style="color: green">${totalPages}</b>
	</label>
	<br>
	<br>
	<label style="border: 1; color: red; font-size: 20px">Page No :
		<b style="color: green">${currentPage+1}</b>
	</label>
	<br>
	<br>
	<div class="pagination">



		<c:if test="${currentPage!=0}">
			<a
				href="/admin/alldonors/${currentPage-1}?sortField=${sortField}&sortDir=${sortDir}">Previous</a>
		</c:if>

		<!-- 	This Is For All Pages But when Too Many Pages Will Come So It Will Create Problem     
		<c:forEach var="i" begin="1" end="${totalPages}">     
		  <a href="/admin/alldonors/${i-1}" class="${currentPage==i-1 ? 'active' : '' } }">${i}</a>
		</c:forEach>    
	 -->
		<a href="/admin/alldonors/0?sortField=${sortField}&sortDir=${sortDir}"
			class="${currentPage==0 ? 'active' : '' } }">1</a> <a
			href="/admin/alldonors/1?sortField=${sortField}&sortDir=${sortDir}"
			class="${currentPage==1 ? 'active' : '' } }">2</a> <a
			href="/admin/alldonors/2?sortField=${sortField}&sortDir=${sortDir}"
			class="${currentPage==2 ? 'active' : '' } }">3</a> <a
			href="/admin/alldonors/${totalPages-1}?sortField=${sortField}&sortDir=${sortDir}"
			class="${currentPage==totalPages-1 ? 'active' : '' } }">Last</a>

		<c:if test="${currentPage!=(totalPages-1)}">
			<a
				href="/admin/alldonors/${currentPage+1}?sortField=${sortField}&sortDir=${sortDir}">Next</a>
		</c:if>


	</div>


</body>
</html>




