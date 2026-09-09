<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- employees.jsp -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#getEmployeesButton').click(function() {
                var id = $('#employeeId').val();
                var url = "getemployee1/" + id;
                $.ajax({
                    type: 'GET',
                    url: url,
                    dataType: 'json',
                    success: function(response) {
                        console.log(response);
                        displayEmployeeData(response);
                    },
                    error: function(xhr, status, error) {
                        console.log("An error occurred: " + error);
                    }
                });
            });
        });

        function displayEmployeeData(data) {
            var table = "<table>";
            table += "<thead>";
            table += "<tr>";
            table += "<th><i class='fa fa-list'></i><h5 style='color: white;'>Action</h5></th>";
            table += "<th><h5 style='color: white;'>Employee Id</h5></th>";
            table += "<th><h5 style='color: white;'>Employee Name</h5></th>";
            table += "<th><h5 style='color: white;'>Employee Email</h5></th>";
            table += "<th><h5 style='color: white;'>Employee PhoneNumber</h5></th>";
            table += "<th><h5 style='color: white;'>Employee Address</h5></th>";
            table += "</tr>";
            table += "</thead>";
            table += "<tbody>";

            for (var i = 0; i < data.length; i++) {
                var emp = data[i];
                table += "<tr>";
                table += "<td>";
                table += "<ul style='padding: 2px 2px 2px 10px'>";
                table += "<li style='list-style-type: none;'>";
                table += "<a href='getemployee/" + emp.EMP1_id + "' style='color: black; text-decoration: none;'>Modify</a></li>";
                table += "<li style='list-style-type: none;'>";
                table += "<a href='delete/" + emp.EMP_id + "' style='color: black; text-decoration: none;' onclick='return confirm(`Are you sure you want to delete emp id " + emp.EMP_id + "?`)'>Delete</a></li>";
                table += "</ul>";
                table += "</td>";
                table += "<td style='color: black'>" + emp.EMP_id + "</td>";
                table += "<td style='color: black'>" + emp.EMP_name + "</td>";
                table += "<td style='color: black'>" + emp.EMP_email + "</td>";
                table += "<td style='color: black'>" + emp.EMP_phoneno + "</td>";
                table += "<td style='color: black'>" + emp.EMP_adress + "</td>";
                table += "</tr>";
            }

            table += "</tbody>";
            table += "</table>";
            $('#employeeTable').html(table);
        }
    </script>
</head>
<body>
    <h1>Employee Data</h1>
    <label for="employeeId">Employee ID:</label>
    <input type="text" id="employeeId">
    <button id="getEmployeesButton" style="background-color: red">Get Employee</button>
    <div id="employeeTable"></div>
    
   
</body>
</html>
