<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Employee</title>
<style>
<%@ include file="stylesummary.css"%>
</style>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
	integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://raw.githubusercontent.com/botmonster/jquery-bootpag/master/lib/jquery.bootpag.min.js"> 
</script>

</head>


<body>

<div class="container-fluid">
	<div class="row">
		<div class="col-sm-12">
			<h1 style="color: blue;">New Registration Form</h1>
		</div>
	</div>
	

      <form:form action="${pageContext.request.contextPath}/saveEmployee" modelAttribute="employee" 
      method="post" class="g-3 needs-validation text-center"
     enctype="multipart/form-data"  >
 
 		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<h5>
					<label for="validationCustom02" class="form-label">Employee Id</label>
				</h5>
				<input type="number" maxlength="7" class="form-control" id="validationCustom02" placeholder="Enter employee id"
				 autocomplete="off" value="${emp.EMP_id}" name="EMP_id" required="required">
				<c:if test="${errorEmp_id ne null}">
					<span style="color: red;">${errorEMP_id}</span>
				</c:if>
			</div>

			<div class="col-md-4">									
				<input type="hidden" name="EMP_Id" value="${emp.EMP_id}"> 
				<h5>
					<label for="validationCustom01" class="form-label">Employee Name</label>
				</h5>
				<input pattern="^[a-zA-Z][\sa-zA-Z]*{3 50}" minlength="2" maxlength="50" title="Alphabets and space only" autocomplete="off" type="text" class="form-control" id="validationCustom01" placeholder="Enter employee name" value="${emp.EMP_name}" name="EMP_name" required="required">
				<c:if test="${errorEMP_name ne null}">
					<span style="color: red;">${errorEMP_name}</span>
				</c:if>
			</div>
					
			<div class="col-md-4">
				<h5>
					<label for="validationCustom03" class="form-label">Email Id</label>
				</h5>
				<input pattern="[a-zA-Z0-9.@_-]+@[a-z0-9.-]+\.[a-z]{2,3}$" title="Format:abc@xyz.com" data-toggle="myToolTip" minlength="6" maxlength="50" type="text" class="form-control" id="validationCustom03" autocomplete="off" placeholder="Enter email ID" value="${emp.EMP_email}" name="EMP_email" required="required">
				<c:if test="${errorEMP_email ne null}">
					<span style="color: red;">${errorEMP_email}</span>
				</c:if>
			</div>
		</div>

		<div class="row">
			<div class="col-md-4">
				<h5>
					<label for="validationCustom06" class="form-label">Mobile Number</label>
				</h5>
				<input maxlength="10" data-toggle="myToolTip" minlength="5" 
				pattern="(?:(?:\+|0{0,2})91(\s*[\-]\s*)?|[0]?)?[6789]\d{9}$" type="number" 
				class="form-control" id="validationCustom06" maxlength="10" placeholder="Enter mobile number"
				 value="${emp.EMP_phoneno}" name="EMP_phoneno"  required="required">
				<c:if test="${errorEMP_phoneno ne null}">
					<span style="color: red;">${errorEMP_phoneno}</span>
				</c:if>
				<div class="invalid-feedback">Please provide a valid mobile number.</div>
			</div>
				
			<div class="col-md-4 ">
				<h5>
					<label for="validationCustom07" class="form-label">Address</label>
				</h5>
				<input pattern="^[a-zA-Z][\sa-zA-Z]*" minlength="3" maxlength="50" title="Alphabets and space only" 
				autocomplete="off" type="text" class="form-control"   id="validationCustom07" placeholder="Enter city" value="${emp.EMP_adress}" name="EMP_adress" required="required">
				<c:if test="${errorEMP_adress ne null}">
					<span style="color: red;">${errorEMP_adress}</span>
				</c:if>
				<div class="invalid-feedback">Please provide a valid address</div>
			</div>

			<div class="col-md-4">
				<h5>
					<label for="EMP_gender" class="form-label">Employee Gender</label>
				</h5>
				<select name="EMP_gender" class="form-control cll" required="required">
					<option>Please select the employee's gender</option>
					<c:forEach items="${genderEnum}" var="genderType">
						<option value="${genderType.genderCharValue}">${genderType.genderStringValue}</option>
					</c:forEach>
				</select>
				<c:if test="${errorEMP_gender ne null}">
					<span style="color: red;">${errorEMP_gender}</span>
				</c:if>
			</div>
		</div>
		
<div class="row">
			<div class="col-md-4">
				<h5>
				<!-- label_font -->
				<label  for="document_name"class="form-label"> Upload  </label></h5>
                       <input  class="form-control"  type="file" required="required" id="browse11" 
                           accept=".png, .jpg, .pdf" name="DD_filename" placeholder="Upload document" autocomplete="off" />
                           <c:if test="${not empty error}">
					<div style="color: red;">${error}</div>
				</c:if>
				<c:if test="${not empty error1}">
					<div style="color: red;">${error1}</div>
				</c:if>
				
			</div>
			<div class="col-md-4">
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
		</div>
 
		<div class="row">
			<div class="col-sm-12">
				<button class="btn btn-primary" type="submit" style="margin-top:40px; margin-left: 5px; width: 8%" 
				onclick="return confirm('Are you sure you want to save this employee?')">SignUp</button>
			</div>
		</div>
	</form:form>


	
	<div class="row text-center position-relative m-4">
		<div class="col-md-4">
			<a href="${pageContext.request.contextPath}/" class="btn btn-primary" type="button"
			 style="margin-right: 10px; margin-top:0px; margin-left:600px; background-color: #395870;">Back</a>
		</div>
	</div>
</div>
</body>
 <script>
    function showSuggestions(inputValue) {
      // Clear the suggestion block
      document.getElementById("suggestionBlock").innerHTML = "";

      // Check if the input matches the requested format and display suggestions
      var pattern = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
      if (!pattern.test(inputValue)) {
        var suggestionBlock = document.getElementById("suggestionBlock");
        suggestionBlock.innerHTML = "";

        var suggestions = document.createElement("span");
        suggestions.style.color = "red";
        suggestions.innerHTML = "Suggestions: Enter at least 8 characters with at least one uppercase letter, one lowercase letter, one digit, and one special character (@$!%*?&).";

        suggestionBlock.appendChild(suggestions);
      }
    }
  </script>
</html>
