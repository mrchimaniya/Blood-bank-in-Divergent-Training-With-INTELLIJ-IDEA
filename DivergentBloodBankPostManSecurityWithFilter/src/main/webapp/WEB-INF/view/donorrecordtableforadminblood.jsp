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
			<th>
				<form action="/admin/donorsbyblood/${currentPage}">
					<input type="hidden" name="sortField" value="name" /> <input
						type="hidden" name="sortDir" value="${reverseDir}" /> <input
						type="hidden" name="bloodGroup" value="${bloodGroup}" /> <input
						type="submit" value="Name"
						style="border-top: 1px; font-size: 20px; background: skyblue; color: blue" />
				</form>
			</th>
			<th>
				<form action="/admin/donorsbyblood/${currentPage}">
					<input type="hidden" name="sortField" value="gender" /> <input
						type="hidden" name="sortDir" value="${reverseDir}" /> <input
						type="hidden" name="bloodGroup" value="${bloodGroup}" /> <input
						type="submit" value="Gender"
						style="border-top: 1px; font-size: 20px; background: skyblue; color: blue" />
				</form>
			</th>
			<th>
				<form action="/admin/donorsbyblood/${currentPage}">
					<input type="hidden" name="sortField" value="age" /> <input
						type="hidden" name="sortDir" value="${reverseDir}" /> <input
						type="hidden" name="bloodGroup" value="${bloodGroup}" /> <input
						type="submit" value="Age"
						style="border-top: 1px; font-size: 20px; background: skyblue; color: blue" />
				</form>
			</th>
			<th>
				<form action="/admin/donorsbyblood/${currentPage}">
					<input type="hidden" name="sortField" value="bloodgroup" /> <input
						type="hidden" name="sortDir" value="${reverseDir}" /> <input
						type="hidden" name="bloodGroup" value="${bloodGroup}" /> <input
						type="submit" value="BloodGroup"
						style="border-top: 1px; font-size: 20px; background: skyblue; color: blue" />
				</form>
			</th>
			<th>
				<form action="/admin/donorsbyblood/${currentPage}">
					<input type="hidden" name="sortField" value="nearestbloodbank" /> <input
						type="hidden" name="sortDir" value="${reverseDir}" /> <input
						type="hidden" name="bloodGroup" value="${bloodGroup}" /> <input
						type="submit" value="NearestBloodBank"
						style="border-top: 1px; font-size: 20px; background: skyblue; color: blue" />
				</form>
			</th>
			<th>
				<form action="/admin/donorsbyblood/${currentPage}">
					<input type="hidden" name="sortField" value="phone" /> <input
						type="hidden" name="sortDir" value="${reverseDir}" /> <input
						type="hidden" name="bloodGroup" value="${bloodGroup}" /> <input
						type="submit" value="Phone"
						style="border-top: 1px; font-size: 20px; background: skyblue; color: blue" />
				</form>
			</th>
			<th>
				<form action="/admin/donorsbyblood/${currentPage}">
					<input type="hidden" name="sortField" value="email" /> <input
						type="hidden" name="sortDir" value="${reverseDir}" /> <input
						type="hidden" name="bloodGroup" value="${bloodGroup}" /> <input
						type="submit" value="Email"
						style="border-top: 1px; font-size: 20px; background: skyblue; color: blue" />
				</form>
			</th>
			<th>
				<form action="/admin/donorsbyblood/${currentPage}">
					<input type="hidden" name="sortField" value="address" /> <input
						type="hidden" name="sortDir" value="${reverseDir}" /> <input
						type="hidden" name="bloodGroup" value="${bloodGroup}" /> <input
						type="submit" value="Address"
						style="border-top: 1px; font-size: 20px; background: skyblue; color: blue" />
				</form>
			</th>
			<th>
				<form action="/admin/donorsbyblood/${currentPage}">
					<input type="hidden" name="sortField" value="disease" /> <input
						type="hidden" name="sortDir" value="${reverseDir}" /> <input
						type="hidden" name="bloodGroup" value="${bloodGroup}" /> <input
						type="submit" value="Disease"
						style="border-top: 1px; font-size: 20px; background: skyblue; color: blue" />
				</form>
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
			<!-- <a href="/admin/donorsbyblood/${currentPage-1}?sortField=${sortField}&sortDir=${sortDir} ">Previous</a> -->
			<form action="/admin/donorsbyblood/${currentPage-1}">
				<input type="hidden" name="sortField" value="${sortField}" /> <input
					type="hidden" name="sortDir" value="${sortDir}" /> <input
					type="hidden" name="bloodGroup" value="${bloodGroup}" /> <input
					type="submit" value="Previous"
					style="border: 0px; font-size: 20px; background: skyblue; color: blue" />
			</form>
		</c:if>


		<form action="/admin/donorsbyblood/0">
			<input type="hidden" name="sortField" value="${sortField}" /> <input
				type="hidden" name="sortDir" value="${sortDir}" /> <input
				type="hidden" name="bloodGroup" value="${bloodGroup}" /> <input
				type="submit" value="1"
				style="border:0px;font-size: 20px; background:${currentPage==0?'orange':'skyblue'};color: blue" />
		</form>
		<%-- <a href="/admin/donorsbyblood/0?sortField=${sortField}&sortDir=${sortDir} " class="${currentPage==0 ? 'active' : '' } }">1</a> --%>

		<form action="/admin/donorsbyblood/1">
			<input type="hidden" name="sortField" value="${sortField}" /> <input
				type="hidden" name="sortDir" value="${sortDir}" /> <input
				type="hidden" name="bloodGroup" value="${bloodGroup}" /> <input
				type="submit" value="2"
				style="border:0px;font-size: 20px; background:${currentPage==1?'orange':'skyblue'};color: blue" />
		</form>
		<%-- <a href="/admin/donorsbyblood/1?sortField=${sortField}&sortDir=${sortDir} " class="${currentPage==1 ? 'active' : '' } }">2</a> --%>

		<form action="/admin/donorsbyblood/2">
			<input type="hidden" name="sortField" value="${sortField}" /> <input
				type="hidden" name="sortDir" value="${sortDir}" /> <input
				type="hidden" name="bloodGroup" value="${bloodGroup}" /> <input
				type="submit" value="3"
				style="border:0px;font-size: 20px; background:${currentPage==2?'orange':'skyblue'};color: blue" />
		</form>
		<%-- <a href="/admin/donorsbyblood/2?sortField=${sortField}&sortDir=${sortDir} " class="${currentPage==2 ? 'active' : '' } }">3</a> --%>

		<form action="/admin/donorsbyblood/${totalPages-1}">
			<input type="hidden" name="sortField" value="${sortField}" /> <input
				type="hidden" name="sortDir" value="${sortDir}" /> <input
				type="hidden" name="bloodGroup" value="${bloodGroup}" /> <input
				type="submit" value="Last"
				style="border:0px;font-size: 20px; background:${currentPage==totalPages-1 ?'orange':'skyblue'};color: blue" />
		</form>
		<%-- <a href="/admin/donorsbyblood/${totalPages-1}?sortField=${sortField}&sortDir=${sortDir} " class="${currentPage==totalPages-1 ? 'active' : '' } }">Last</a> --%>

		<c:if test="${currentPage!=(totalPages-1)}">
			<%-- <a href="/admin/donorsbyblood/${currentPage+1}?sortField=${sortField}&sortDir=${sortDir} ">Next</a> --%>
			<form action="/admin/donorsbyblood/${currentPage+1}">
				<input type="hidden" name="sortField" value="${sortField}" /> <input
					type="hidden" name="sortDir" value="${sortDir}" /> <input
					type="hidden" name="bloodGroup" value="${bloodGroup}" /> <input
					type="submit" value="Next"
					style="border: 0px; font-size: 20px; background-color: skyblue; color: blue" />
			</form>
		</c:if>


	</div>


</body>
</html>




