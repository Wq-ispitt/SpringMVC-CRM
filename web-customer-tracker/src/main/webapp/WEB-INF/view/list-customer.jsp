<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<html>
<head>
<title>List Customers</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

</head>
<body>
	<div id="wrapper" class="container">
		<div id="header" class="row" align="center">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>

	<div id="container" class="container">
		<div id="content" class="row">
			<input type="button" value="Add Customer"
				class="btn btn-outline-success"
				onclick="window.location.href='showFormForAdd'; return false;" >
		</div>
		<br>
		
		<form:form action="search" method="GET">
		Search Customer: 
		<input type="text" name="theSearchName" >
		<input type="submit" value="Search" class="btn btn-outline-success" >
		</form:form>
		<br>

		
			<table class="table">
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				
				<c:forEach var="tempCustomer" items="${customers}">

					<c:url var="updateLink" value="/customer/showFormForUpdate">
						<c:param name="customerId" value="${tempCustomer.id}"></c:param>
					</c:url>

					<c:url var="deleteLink" value="/customer/delete">
						<c:param name="customerId" value="${tempCustomer.id}"></c:param>
					</c:url>

					<tr>
						<td>${tempCustomer.firstName}</td>
						<td>${tempCustomer.lastName}</td>
						<td>${tempCustomer.email}</td>
						<td>
						<a href="${updateLink}">Update</a>
						<a href="${deleteLink}"
						onclick="return (confirm('Confirm to delete the record?'))">Delete</a>
						</td>
						
					</tr>

				</c:forEach>
			</table>
		</div>

	
	

</body>
</html>