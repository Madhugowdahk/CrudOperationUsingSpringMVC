<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<title>Employee Data</title>
</head>
<body>


<h1><marquee>Employees Data</marquee></h1>
<%
try{
	String url="jdbc:postgresql://localhost:5432/EMPLOYEE";
	String username="postgres";
	String password="unibrain";
	String query="select*from EmployeDetails where id";
	Class.forName("org.postgresql.Driver");
	Connection conn=DriverManager.getConnection(url,username,password);
	Statement statement=conn.createStatement();
	statement.execute(query);
	ResultSet resultSet=statement.executeQuery(query);
	while(resultSet.next())
	{
 %>
 <table align="center" border="1" width="100" style="width:100%" class="table table-dark">
 <tbody>
 <tr>
 <th width="25%">action</th>
 <th width="25%">EMP_id</th>
  <th width="25%">EMP_name</th>
  <th width="25%">EMP_email</th>
   <th width="25%">EMP_phoneno</th>
    <th width="25%">EMP_adress</th>
 </tr>
 <td>
									<ul style="padding: 2px 2px 2px 10px">
										<li style="list-style-type: none;">
										<a href="getemployee/<c:out  value='${emp.id}'/>"
											style="color: black; text-decoration: none;">Modify</a></li>
										<li style="list-style-type: none;">
										<a
											href="delete/<c:out value='${emp.id}'/>"
											style="color: black; text-decoration: none;"
onclick="return confirm(`Are you sure you want to delete emp id <c:out value='${emp.id}'/>?`)">Delete</a></li>
									</ul>
								</td>
 <td width="25%"><%=resultSet.getInt(1) %></td>
  <td width="25%"><%=resultSet.getString(2) %></td>
  <td width="25%"><%=resultSet.getString(3) %></td>
  <td width="25%"><%=resultSet.getString(4) %></td>
  <td width="25%"><%=resultSet.getString(5) %></td>
 </tbody>
 </table>
 <%
	}
}
 
catch(Exception e){
	e.printStackTrace();
}
 %>
</body>
</html>