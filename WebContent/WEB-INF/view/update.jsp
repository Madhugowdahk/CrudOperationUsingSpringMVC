<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Employe</title>
<style>
<%@include file="stylesummary.css"%>

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
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12">
				<h1 style="color: blue;">Enter ID to get Employee Details</h1>
			</div>
		</div>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-12">
					<div id="myDIV">  
                 <%--   <c:set var="actionUrl" value="/search1" /> --%>
                   
                     <form class="Employees_details g-3 needs-validation text-center"
                     action="${pageContext.request.contextPath}/search1" method="post">
                     
					
							<div class="row text-center position-relative m-4">
								<div class="col-md-4">
									<h5>
										<label for="inputid">Employee Id</label>
									</h5>
									<input type="text" class="form-control" name="EMP_id"
									 pattern="^[a-zA-Z][\sa-zA-Z]*{3 50}" minlength="1"  maxlength="50"
					title="Alphabets and space only" autocomplete="off" type="number"
										placeholder="Enter employee id">
								</div>
								<div class="col-md-4">
									<h5>
										<label for="inputname">Employee Name</label>
									</h5>
									<input type="text" class="form-control" name="EMP_name" 
									 pattern="^[a-zA-Z][\sa-zA-Z]*{3 50}" minlength="3"  maxlength="50"
					title="Alphabets and space only" autocomplete="off" type="text"
										placeholder="Enter employee name">
					

								</div>
								<div class="col-md-4">
									<h5>
										<label for="inputEmail">Email Id</label>
									</h5>
									<input type="text" class="form-control" name="EMP_email"
									pattern="[a-zA-Z0-9.@_-]+@[a-z0-9.-]+\.[a-z]{2,3}$"
					title="Format:abc@xyz.com" data-toggle="myToolTip" minlength="6" maxlength="50" type="text"
					autocomplete="off"
										placeholder="Enter email Id">
								</div>
							</div>
							<div class="row text-center position-relative m-4">

								<div class="col-md-4">
									<h5>
										<label for="inputNumber">Mobile Number</label>
									</h5>
									<input type="text" class="form-control" name="EMP_phoneno" 
									maxlength="10" data-toggle="myToolTip" minlength="5"
						pattern="(?:(?:\+|0{0,2})91(\s*[\-]\s*)?|[0]?)?[6789]\d{9}$"
						type="number" title="9987654321"
										placeholder="Enter mobile number">
								</div>


								<div class="col-md-4">
									<h5>
										<label for="inputAddress">Address</label>
									</h5>
									<input type="text" class="form-control" name="EMP_adress"
									pattern="^[a-zA-Z][\sa-zA-Z]*" minlength="3" maxlength="50"
						title="Alphabets and space only" autocomplete="off" type="text"
						class="form-control" minlength="3" maxlength="50"
										id="inputAddress" placeholder="Enter address">
								</div>
								<div class="col-md-4">
									<h5>
										<label for="inputAddress">gender</label>
									</h5>
									<input type="text" class="form-control" name="EMP_gender"
									pattern="^[a-zA-Z][\sa-zA-Z]*" 
						title="Alphabets and space only" autocomplete="off" type="text"
						class="form-control"
										id="inputgender" placeholder="Enter gender">
								</div>
							</div>
							 	<div class="row text-center position-relative m-4">
        <div class="col-md-4">
            <h5>
                <label for="inputSortBy">Sort By</label>
            </h5>
            <select class="form-select" name="sortby">
                <option value="EMP_id">Employee Id</option>
                <option value="EMP_name">Name</option>
                <option value="EMP_email">Email</option>
                <option value="EMP_phoneno">Phone Number</option>
                <option value="EMP_address">Address</option>
                <option value="EMP_gender">Gender</option>
            </select>
        </div>
        <div class="col-md-4">
            <h5>
                <label for="inputSortOrder">Sort Order</label>
            </h5>
            <select class="form-select" name="sortorder">
                <option value="asc">Ascending</option>
                <option value="desc">Descending</option>
            </select>
        </div>
    </div>
							
							
							<div class="row">
								<div class="col-sm-12">
									<button class="btn btn-primary" type="submit"  
										style= "margin-top:40px; margin-left: 8px;margin-right:15px; width: 15%"; background-color: #395870;">Click here to Search</button>
								</div>
								<br><br>
								<a href="http://localhost:8080/CRUD_operaationproject/"
							class="btn btn-primary" type="submit"
							style="margin-right: 5px; width:5%; margin-top: 10px; margin-left: 633px; background-color: #395870;">Back</a>
							</div>
						</form>
						
					</div>
				</div>
			</div>
		</div>
		<!-- try -->
		<div class="row">
			<div class="col-sm-12">
				<table>
					<thead>
						<tr>
							<th><i class="fa fa-list"></i>
								<h5 style="color: white;">Action</h5></th>
							<th><h5 style="color: white;">Employee Id</h5></th>
							<th><h5 style="color: white;"> Name</h5></th>
							<th><h5 style="color: white;"> Email</h5></th>
						    <th><h5 style="color: white;"> PhoneNumber</h5></th>
							<th><h5 style="color: white;"> Address</h5></th>
							<th><h5 style="color: white;"> gender</h5></th>
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
								<td style="color: black"><c:out value="${emp.EMP_gender}" /></td>
							</tr>
						</c:forEach>
					
					</tbody>
				</table>
				
			</div>
		</div>
<a href="http://localhost:8080/CRUD_operaationproject/"
							class="btn btn-primary" type="submit"
							style="margin-right: 5px; width:5%; margin-top: 10px; margin-left: 633px; background-color: #395870;">Back</a>
		
		
		 <%-- 	<%@ include file="pagination.jsp"%>  --%> 
</body>
</html>