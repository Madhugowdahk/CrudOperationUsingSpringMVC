 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Image</title>
</head>
<body>
 

      <h1>Employee Image</h1>
    <img src="${pageContext.request.contextPath}/resources/images/${emp.document_name}" width="500" hieght="500" alt="Employee Image">

</body>
</html>

