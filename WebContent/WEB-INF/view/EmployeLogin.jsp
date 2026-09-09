<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@page import="java.sql.*" %>
 <%@ page import="java.util.Date" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
 

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Employee </title>
	
<style>
    .success-message {
        color: white;
        font-weight: bold;
        text-align:center;
    }



<%@ include file="stylesummary.css"%>
   
      #server-time {
        position: fixed;
        top: 0;
        left: 1020px;
        padding: 10px;
        background-color: #fff;
        font-size: 20px;
        font-weight: bold;
        border-bottom-right-radius: 5px;
      }
       
 
</style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
	integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://raw.githubusercontent.com/botmonster/jquery-bootpag/master/lib/jquery.bootpag.min.js"></script>

</head>

<body >


  <div id="server-time"><%= new Date() %></div>
        
<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12" >
				<h1 style="color: blue;"><i class="fas fa-user-plus" style="color: black;"></i>
				Registration Form</h1>
			</div>
		</div>
		</div>
<br>
	<a href="newEmployee/"
		 class="btn btn-primary" type="submit"
	style="margin-right: 10px; margin-top:10px; margin-left:10px; background-color: #395870; "><i class="fas fa-user-plus" style="color: black;"></i>New Employee</a>
 	<br><br>
 	<a href="newjsp"
		 class="btn btn-primary" type="submit"
	style="margin-right: 10px; margin-top:10px; margin-left:10px; background-color: #395870; "><i class="fas fa-user-plus" style="color: black;"></i>New Jsp</a>
 	<br><br>
 
 <a href="login/"
		 class="btn btn-primary" type="submit"
	style="margin-right: 10px; margin-top:10px; margin-left:10px; background-color: #395870; "><i class="fas fa-user-plus" style="color: black;"></i>login</a>
		<br><br>
	
          <c:set var="actionUrl" value="SearchEmployeeDetails"></c:set>
          <form action="${actionUrl}" method="post">
						
						<div class="row">
							<div class="col-sm-12">
								<button class="btn btn-primary" type="submit" 
					style="background-color: #395870; margin-left:10px; padding-left: 2px;"> Click here to Search Employee</button>
									</div>
							</div>
							</form>
						<br><br>
							 <c:if test="${not empty success}">
    <div class="success-message hideDiv">${success1}</div>
</c:if>
	
		 <c:if test="${not empty success}">
    <div class="success-message hideDiv" >${success}</div>
</c:if>

<marquee style="font-size: 150%;color: red">Employee Data</marquee>
<div class="row">
			<div class="col-sm-12">
				<table>
					<thead>
						<tr>
							<th>
								<h5 style="color: white;">Action</h5></th>
							<th><h5 style="color: white;">Employee Id</h5></th>
							<th><h5 style="color: white;"> Name</h5></th>
							<th><h5 style="color: white;"> Email</h5></th>
						    <th><h5 style="color: white;">Phone Number</h5></th>
						 
							<th><h5 style="color: white;"> Address</h5></th>
							   <th><h5 style="color: white;"> gender</h5></th>
							
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${Employee}" var="emp">
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
    <li style="list-style-type: none;">
				<a href="view/<c:out value='${emp.EMP_id}'/>" style="color: black; text-decoration: none;" 
				 onclick="return confirm(`Are you sure you want to view image emp id of  <c:out value='${emp.EMP_id}'/>?`)">View Image</a>
				 </li>
				  <li style="list-style-type: none;">
				<a href="download/<c:out value='${emp.EMP_id}'/>" style="color: black; text-decoration: none;" 
				 onclick="return confirm(`Are you sure you want to download image  <c:out value='${emp.EMP_id}'/>?`)">Download Image</a>
				 </li>
									</ul>
								</td>
								<td style="color: black"><c:out value="${emp.EMP_id}" /></td>
								<td style="color: black"><c:out value="${emp.EMP_name}" /></td>
								<td style="color: black"><c:out value="${emp.EMP_email}" /></td>
								<td style="color: black"><c:out value="${emp.EMP_phoneno}" /></td>
									
								<td style="color: black"><c:out value="${emp.EMP_adress}" /></td>
							<td style="color: black"><c:choose>
    <c:when test="${emp.EMP_gender eq 'M'}">
      Male
    </c:when>
    <c:when test="${emp.EMP_gender eq 'F'}">
      Female
    </c:when>
    <c:when test="${emp.EMP_gender eq 'T'}">
    Transgender
    </c:when>
    <c:otherwise>
      unknown
    </c:otherwise>
  </c:choose><c:out value="" /></td> 
							</tr>
						</c:forEach>
					
					</tbody>
				</table>
				
			</div>
		</div>	
		<%@ include file="pagination.jsp"%>
	
</body>
<script>
$(document).ready(function() {

    $(".hideDiv").fadeOut(5000);
       
});



</html>