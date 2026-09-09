<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
<head>
<title>OpenCage Map + Coordinates</title>

<link rel="stylesheet"
 href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css"/>

<script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>

</head>

<body style="background-color:#859cc2">

<h2>Location Picker (Map + Coordinates)</h2>

<!-- MAP -->
<div id="map" style="height:400px; width:600px; border:2px solid black;"></div>

<br/>

<!-- LAT/LON INPUT -->
<label>Latitude:</label>
<input type="text" id="lat" style="width:150px;" />

<label>Longitude:</label>
<input type="text" id="lon" style="width:150px;" />

<button onclick="getFromCoordinates()">Get Address</button>

<br/><br/>

<!-- ADDRESS -->
<label>Address:</label><br/>
<input type="text" id="address" style="width:500px;" />

<script>
var map;
var marker;

// PAGE LOAD
document.addEventListener("DOMContentLoaded", function () {

    map = L.map('map').setView([12.9716, 77.5946], 13);

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png').addTo(map);

    // CURRENT LOCATION
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {

            var lat = position.coords.latitude;
            var lon = position.coords.longitude;

            setMarker(lat, lon);

            document.getElementById("lat").value = lat;
            document.getElementById("lon").value = lon;

            fetchAddress(lat, lon);

        }, function(error) {
            console.log("Location error:", error);
        });
    }

    // MAP CLICK
    map.on('click', function(e) {

        var lat = e.latlng.lat;
        var lon = e.latlng.lng;

        setMarker(lat, lon);

        document.getElementById("lat").value = lat;
        document.getElementById("lon").value = lon;

        fetchAddress(lat, lon);
    });

});

// SET MARKER FUNCTION
function setMarker(lat, lon) {

    if (marker) {
        map.removeLayer(marker);
    }

    marker = L.marker([lat, lon]).addTo(map)
        .bindPopup("Selected Location")
        .openPopup();

    map.setView([lat, lon], 15);
}

// FETCH ADDRESS FROM BACKEND
function fetchAddress(lat, lon) {

    fetch('reverseGeocodeOpenCage?lat=' + encodeURIComponent(lat) + '&lon=' + encodeURIComponent(lon))
        .then(res => res.json())
        .then(data => {

            if (data.results && data.results.length > 0) {
                document.getElementById("address").value =
                    data.results[0].formatted;
            } else {
                document.getElementById("address").value = "Address not found";
            }

        })
        .catch(err => {
            console.error("Error:", err);
        });
}

//  MANUAL INPUT
function getFromCoordinates() {

    var lat = document.getElementById("lat").value.trim();
    var lon = document.getElementById("lon").value.trim();

    if (!lat || !lon) {
        alert("Enter latitude and longitude");
        return;
    }

    setMarker(lat, lon);
    fetchAddress(lat, lon);
}
</script>

</body>
</html>