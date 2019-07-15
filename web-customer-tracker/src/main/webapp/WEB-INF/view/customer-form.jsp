<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Save</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/add-customer-style.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>
<div id="wrapper" class="container">
    <div id="header">
        <h2>CRM - Customer Relationship Manager</h2>
    </div>
</div>

<div id="container" class="container">
	<h3>Save Customer</h3>
	<form:form action="saveCustomer" modelAttribute="customer" method="POST">
	
	<!-- associate this data with customer id, track and tell which one was updated -->
	<form:hidden path="id"/>
	
		<table>
			<tbody>
				<tr>
					<td><label>First name: </label>
					<td><form:input path="firstName" /></td>
				</tr>
				<tr>
					<td><label>Last name: </label>
					<td><form:input path="lastName" /></td>
				</tr>
				<tr>
					<td><label>Email: </label>
					<td><form:input path="email" /></td>
				</tr>
				<tr>
					<td><label></label>
					<td><button type="submit" value="Save" class="btn btn-outline-success">Save</button>
					</td>
				</tr>
				
			</tbody>
		</table>
	</form:form>
	
	<div style="clear;both;">	
	</div>
	
	<p>
		<a href="${pageContext.request.contextPath}/customer/list">Back to list</a>
	</p>

</div>

</body>
</html>