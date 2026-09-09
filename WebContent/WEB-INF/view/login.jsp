<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <style>
        <%@ include file="stylesummary.css"%>
    </style>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
          crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://raw.githubusercontent.com/botmonster/jquery-bootpag/master/lib/jquery.bootpag.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12">
            <h1 style="color: blue;">Login Form</h1>
        </div>
    </div>

    <form:form action="${pageContext.request.contextPath}/validatelogin" modelAttribute="employee" method="post"
                 class="g-3 needs-validation text-center">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <h5>
                    <label for="validationCustom02" class="form-label">Employee Id</label>
                </h5>
                <input type="number" maxlength="7" class="form-control" id="validationCustom02"
                       placeholder="Enter employee id" autocomplete="off" name="EMP_id"
                       required="required">
                <c:if test="${errorEMP_id ne null}">
                    <span style="color: red;">${errorEMP_id}</span>
                </c:if>
            </div>

            <div class="col-md-4">
               
                <h5>
                    <label for="validationCustom01" class="form-label">Enter your password</label>
                </h5>
              <input class="form-control"  type="password" required="required" id="pass1" name="password" placeholder="Enter your password"
		maxlength="12"  pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$" 
		 title="Suggestions: Enter at least 8 characters with at least one uppercase letter,
		 one lowercase letter, one digit, and one special character (@$!%*?&)."
		autocomplete="off" onkeyup="showSuggestions(this.value)" />
                <c:if test="${error ne null}">
                    <span style="color: red;">${error}</span>
                </c:if>
            </div>

            <div class="col-md-4">
                <h5>
                    <label for="validationCustom03" class="form-label">Confirm password</label>
                </h5>
              <input class="form-control"  type="password" required="required" id="pass1" name="password1" placeholder="Enter password"
		maxlength="12"  pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$" 
		 title="Suggestions: Enter at least 8 characters with at least one uppercase letter,
		 one lowercase letter, one digit, and one special character (@$!%*?&)."
		autocomplete="off" onkeyup="showSuggestions(this.value)" />
                <c:if test="${error ne null}">
                    <span style="color: red;">${error}</span>
                </c:if>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-12">
                <button class="btn btn-primary" type="submit"
                        style="margin-top:40px; margin-left: 5px; width: 8%"
                        >Login
                </button>
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
</html>
