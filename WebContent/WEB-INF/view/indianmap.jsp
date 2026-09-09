<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Current Location Map</title>

    <!-- MapmyIndia SDK -->
    <script src="https://apis.mappls.com/advancedmaps/api/utrhxhpqhdgfntlnnuqmwxydwmyezupzvmez/map_sdk?layer=vector&v=3.0"></script>

    <style>
        body {
            font-family: Arial;
        }
        #map {
            width: 100%;
            height: 500px;
        }
    </style>
</head>

<body>

<h2>My Current Location</h2>

<div id="map"></div>
<p id="coords"></p>
<p id="address"></p>

<script>
function initLocation() {

    if (!navigator.geolocation) {
        alert("Geolocation not supported");
        return;
    }

    navigator.geolocation.getCurrentPosition(function(position) {

        var lat = position.coords.latitude;
        var lon = position.coords.longitude;

        document.getElementById("coords").innerHTML =
            "Latitude: " + lat + "<br>Longitude: " + lon;

        // IMPORTANT: Wait until mappls loads
        if (typeof mappls === "undefined") {
            alert("Map SDK not loaded. Check API key or script.");
            return;
        }

        // Create map
        var map = new mappls.Map('map', {
            center: {lat: lat, lng: lon},
            zoom: 15
        });

        // Marker
        new mappls.Marker({
            map: map,
            position: {lat: lat, lng: lon}
        });

    }, function(error) {
        console.error(error);
        alert("Location access denied");
    });
}

window.onload = function() {
    setTimeout(initLocation, 1000); // delay helps SDK load
};
</script>
</body>
</html>