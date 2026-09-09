<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
<%@ include file="stylesummary.css"%>
</style>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
	integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://raw.githubusercontent.com/botmonster/jquery-bootpag/master/lib/jquery.bootpag.min.js"></script>

</head>
<body>
<div class="row">
			<div class="col-sm-12">
				<table>
					<thead>
						<tr>
							<th><i class="fa fa-list"></i>
								<h5 style="color: white;">Action</h5></th>
							<th><h5 style="color: white;">Employee Id</h5></th>
							<th><h5 style="color: white;">Employee Name</h5></th>
							<th><h5 style="color: white;">Employee Email</h5></th>
						    <th><h5 style="color: white;">Employee PhoneNumber</h5></th>
							<th><h5 style="color: white;">Employee Address</h5></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${employee}" var="emp">
							<tr>
									<td>
									<ul style="padding: 2px 2px 2px 10px">
										<li style="list-style-type: none;">
										<a href="getemployee/<c:out  value='${emp.EMP_id}'/>"
											style="color: black; text-decoration: none;">Modify</a></li>
										<li style="list-style-type: none;">
										<a
											href="delete/<c:out value='${emp.EMP_id}'/>"
											style="color: black; text-decoration: none;"
onclick="return confirm(`Are you sure you want to delete emp id <c:out value='${emp.EMP_id}'/>?`)">Delete</a></li>
									</ul>
								</td>
								
								<td style="color: black"><c:out value="${emp.EMP_id}" /></td>
								<td style="color: black"><c:out value="${emp.EMP_name}" /></td>
								<td style="color: black"><c:out value="${emp.EMP_email}" /></td>
								<td style="color: black"><c:out value="${emp.EMP_phoneno}" /></td>
								<td style="color: black"><c:out value="${emp.EMP_adress}" /></td>
							</tr>
						</c:forEach>
					
					</tbody>
				</table>
				
			</div>
		</div>
		<a href="http://localhost:8080/CRUD_operaationproject/"
		 class="btn btn-primary" type="submit"
					style="margin-right: 10px; margin-top:10px; margin-left:610px; background-color: #395870; ">Back</a>
		
<%@ include file="pagination.jsp"%>
</body>
</html>