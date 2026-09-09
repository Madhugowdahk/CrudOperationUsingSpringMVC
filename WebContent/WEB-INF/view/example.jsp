<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WebService Example</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>

<h1>WebService Example</h1>

<div id="responseContainer">
    <!-- Response from the web service will be displayed here -->
</div>

<!-- Button to trigger the AJAX request -->
<button id="getDataButton">Get Data</button>

<script>
    $(document).ready(function() {
        $('#getDataButton').on('click', function() {
            $.ajax({
                type: 'GET',
                url: '/webservice/hell',
                success: function(responseData, status) {
                    alert(responseData.name);
                    // Update the content in the response container
                    $('#responseContainer').html(
                        '<p>Name: ' + responseData.name + '</p>' +
                        '<p>Age: ' + responseData.age + '</p>' +
                        '<p>Email: ' + responseData.email + '</p>' +
                        '<p>Number: ' + responseData.number + '</p>'
                    );
                },
                error: function(error) {
                    // Handle errors
                    $('#responseContainer').html('<p>Error occurred: ' + error.statusText + '</p>');
                }
            });
        });
    });
</script>


</body>
</html>
