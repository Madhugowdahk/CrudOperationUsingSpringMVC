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



<!-- <input type="text" id="search" placeholder="Search location..." style="width:300px;">
<button onclick="searchLocation()">Search</button>
 -->
<script>
var map;
var marker;

document.addEventListener("DOMContentLoaded", function () {

    map = L.map('map').setView([12.9716, 77.5946], 13);

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '© OpenStreetMap'
    }).addTo(map);

    //  CURRENT LOCATION
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {

            var lat = position.coords.latitude;
            var lon = position.coords.longitude;

            setMarker(lat, lon);

            document.getElementById("lat").value = lat;
            document.getElementById("lon").value = lon;

            fetchAddress(lat, lon);

        }, function() {
            alert("Location access denied");
        });
    }

    //  MAP CLICK
    map.on('click', function(e) {

        var lat = e.latlng.lat;
        var lon = e.latlng.lng;

        setMarker(lat, lon);

        document.getElementById("lat").value = lat;
        document.getElementById("lon").value = lon;

        fetchAddress(lat, lon);
    });

});

//  MARKER
function setMarker(lat, lon) {
    if (marker) {
        map.removeLayer(marker);
    }
    marker = L.marker([lat, lon]).addTo(map);
    map.setView([lat, lon], 13);
}

//  FETCH ADDRESS (CALL BACKEND)
function fetchAddress(lat, lon) {

	fetch('/CRUD_operaationproject/api/reverse-geocode?lat=' + lat + '&lon=' + lon)    .then(res => {
        if (!res.ok) {
            throw new Error("HTTP error " + res.status);
        }
        return res.json();
    })
    .then(data => {
        document.getElementById("address").value = data.display_name;
    })
    .catch(err => {
        console.error(err);
        document.getElementById("address").value = "Error fetching address";
    });
	
	}
// MANUAL INPUT
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

function searchLocation() {

    var query = document.getElementById("search").value;

    fetch("https://nominatim.openstreetmap.org/search?format=json&q=" + encodeURIComponent(query))
        .then(res => res.json())
        .then(data => {

            if (data.length > 0) {

                var lat = data[0].lat;
                var lon = data[0].lon;

                setMarker(lat, lon);

                document.getElementById("lat").value = lat;
                document.getElementById("lon").value = lon;

                fetchAddress(lat, lon);

            } else {
                alert("Location not found");
            }
        });
}
</script>
</body>
</html>