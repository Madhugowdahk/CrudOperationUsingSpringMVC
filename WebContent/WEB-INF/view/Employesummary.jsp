<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Form</title>
<style>
<%@ include file="stylesummary.css"%>
</style>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
</head>

<body>
<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12">
				<h1 style="color: blue;">Update Form</h1>
			</div>
		</div>
		
	<c:choose>
		<c:when test="${emp.EMP_id ne null and error eq null}">
			 <%-- <c:set var="actionUrl" value="updateEmployee"></c:set> --%>
			<%--<c:set var="actionUrl" value="http://localhost:8080/CRUD_operaationproject/updateEmployee"></c:set> --%>
			<c:set var="actionUrl" value="updateEmployee"></c:set>
			 </c:when>
		<c:otherwise>
			<c:set var="actionUrl" value="saveEmployee"></c:set>
		</c:otherwise>
	</c:choose>

			<form action="${pageContext.request.contextPath}/${actionUrl}" method="post" class="Employees_details g-3 needs-validation text-center">
			<%-- <form class="Employees_details g-3 needs-validation text-center"
						action="${actionUrl}" method="post"> --%>
						
					<div class="row">
		<div class="col-md-4 col-md-offset-4">
			     <h5>
					<label for="validationCustom02" class="form-label">Employee Id</label>
				 </h5>
		              <input type="number" maxlength="7" class="form-control searchid"
							id="validationCustom02" placeholder="Enter employee id"
							value="${emp.EMP_id}" name="EMP_id" required="required">
						<c:if test="${errorEmp_id ne null}">
							<span style="color: red;">${errorEMP_id}</span>
						</c:if>
							</div>
							
				<div class="col-md-4">									
				<input type="hidden" name="EMP_Id" value="${emp.EMP_id}"> 
				<h5>
					<label for="validationCustom01" class="form-label">Employee Name</label>
				</h5>
				<input pattern="^[a-zA-Z][\sa-zA-Z]*{3 50}"
					title="Alphabets and space only" autocomplete="off" type="text"
					class="form-control" id="validationCustom01"
					placeholder="Enter employee name" value="${emp.EMP_name}"
					name="EMP_name" required="required">
				<c:if test="${errorEMP_name ne null}">
					<span style="color: red;">${errorEMP_name}</span>
				</c:if>
                    </div>
                    
					
				<div class="col-md-4">
				<h5>
					<label for="validationCustom03" class="form-label">Email Id</label>
				</h5>
				<input pattern="[a-zA-Z0-9.@_-]+@[a-z0-9.-]+\.[a-z]{2,3}$"
					title="Format:abc@xyz.com" data-toggle="myToolTip" minlength="6" maxlength="50" type="text"
					class="form-control" id="validationCustom03"
					placeholder="Enter email ID" value="${emp.EMP_email}" name="EMP_email"
					required="required">
				<c:if test="${errorEMP_email ne null}">
					<span style="color: red;">${errorEMP_email}</span>
				</c:if>
		        	</div>
		           </div>
					<div class="row">
					<!-- 	<div class="row text-center position-relative m-4"> -->
						
						<div class="col-md-4">
					   <h5>
						<label for="validationCustom06" class="form-label">Mobile
							Number</label>
					</h5>
					<input maxlength="10" data-toggle="myToolTip" minlength="5"
						pattern="(?:(?:\+|0{0,2})91(\s*[\-]\s*)?|[0]?)?[6789]\d{9}$"
						type="number" class="form-control" id="validationCustom06"
						maxlength="10" placeholder="Enter mobile number"
						value="${emp.EMP_phoneno}" name="EMP_phoneno" maxlength='10'
						required="required">
					<c:if test="${errorEMP_phoneno ne null}">
						<span style="color: red;">${errorEMP_phoneno}</span>
					</c:if>
					<div class="invalid-feedback">Please provide a valid mobile
						number.</div>
				</div>
				
				<div class="col-md-4 ">
					<h5>
						<label for="validationCustom07" class="form-label">Address</label>
					</h5>

					<input pattern="^[a-zA-Z][\sa-zA-Z]*" minlength="3" maxlength="50"
						title="Alphabets and space only" autocomplete="off" type="text"
						class="form-control" minlength="3" maxlength="50"
						id="validationCustom07" placeholder="Enter city"
						value="${emp.EMP_adress}" name="EMP_adress" required="required">
					<c:if test="${errorEMP_adress ne null}">
						<span style="color: red;">${errorEMP_adress}</span>
					</c:if>
					
					<div class="invalid-feedback">Please provide a address.</div>
		          </div>	
		          
		         <div class="col-md-4">
  <h5>
    <label for="validationCustom07" class="form-label">Gender</label>
  </h5>

  <select name="EMP_gender" class="form-control" id="validationCustom07" required>
    <option value="">Please select the gender</option>
    <option value="M" ${emp.EMP_gender == 'M' ? 'selected' : ''}>Male</option>
    <option value="F" ${emp.EMP_gender == 'F' ? 'selected' : ''}>Female</option>
    <option value="T" ${emp.EMP_gender == 'T' ? 'selected' : ''}>Transgender</option>
  </select>

  <c:if test="${errorEMP_gender ne null}">
    <span style="color: red;">${errorEMP_gender}</span>
  </c:if>

  <div class="invalid-feedback">Please select a gender.</div>
</div>

			<%-- 			<div class="col-md-4">
			<h5>
			<label for="password" class="form-label">Enter password</label>
			</h5>
			<input class="form-control"  type="password" required="required" id="pass1" name="password" placeholder="Enter password"
		maxlength="12"  pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$" 
		 title="Suggestions: Enter at least 8 characters with at least one uppercase letter,
		 one lowercase letter, one digit, and one special character (@$!%*?&)."
		autocomplete="off" onkeyup="showSuggestions(this.value)" />
		<c:if test="${errorPassword ne null}">
					<span style="color: red;">${errorPassword}</span>
				</c:if>
			</div>
			<div class="col-md-4">
			</div>
		</div> --%>
						
			<div class="row">
			<div class="col-sm-12">
		<button class="btn btn-primary" type="submit"  style="margin-right: 5px; width:6%; margin-top: 10px; margin-left: 3px;
		onclick="return confirm('Are you sure you want to update?')">update</button>
					
						</div>
						</div>
						</form>
						</div>
				 <a href="http://localhost:8080/CRUD_operaationproject/"
		                       class="btn btn-primary" type="submit"
						style="margin-right: 10px; margin-top:0px; margin-left:630px; background-color: #395870; ">Back</a>
				<%@ include file="pagination.jsp"%>


</body>
</html>

