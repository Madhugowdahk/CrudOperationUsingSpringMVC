<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
<head>
<title>Search Location Map</title>

<link rel="stylesheet"
 href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css"/>

<script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>

</head>

<body style="background-color:#859cc2">


<h2>Search Location (Auto Zoom)</h2>

<!-- SEARCH BAR -->
<label>Search:</label>
<input type="text" id="search" style="width:300px;" />
<button onclick="searchLocation()">Search</button>

<br/><br/>

<!-- MAP -->
<div id="map" style="height:400px; width:600px; border:2px solid black;"></div>

<br/>

<!-- DETAILS -->
<label>Latitude:</label>
<input type="text" id="lat" />

<label>Longitude:</label>
<input type="text" id="lon" />

<br/><br/>

<label>Address:</label><br/>
<input type="text" id="address" style="width:500px;" />

<script>

var map;
var marker;

document.addEventListener("DOMContentLoaded", function () {

    map = L.map('map').setView([12.9716, 77.5946], 5);

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png').addTo(map);

});

// SEARCH FUNCTION
function searchLocation() {

    var query = document.getElementById("search").value.trim();

    if (!query) {
        alert("Enter location");
        return;
    }

    fetch('searchLocationOpenCage?query=' + encodeURIComponent(query))
        .then(res => res.json())
        .then(data => {

            if (data.results && data.results.length > 0) {

                var result = data.results[0];

                var lat = result.geometry.lat;
                var lon = result.geometry.lng;

                // Move map
                if (marker) {
                    map.removeLayer(marker);
                }

                marker = L.marker([lat, lon]).addTo(map);
                map.setView([lat, lon], 13);

                // Fill values
                document.getElementById("lat").value = lat;
                document.getElementById("lon").value = lon;
                document.getElementById("address").value = result.formatted;

            } else {
                alert("Location not found");
            }

        })
        .catch(err => {
            alert("Error occurred");
        });
}

</script>

</body>
</html>