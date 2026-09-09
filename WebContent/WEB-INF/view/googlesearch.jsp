<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
<head>
<title>Google Map Search + Picker</title>

<!-- IMPORTANT: include places library -->
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCr-ySJ0G_4ZK4VYOIRr_cpGrSyBeEh1pE&libraries=places"></script>

</head>

<body style="background-color:#859cc2">

<h2>Search Location (Autocomplete)</h2>

<!-- SEARCH BOX -->
<input type="text" id="search" placeholder="Enter location..." style="width:400px; height:30px;" />

<br/><br/>

<!-- MAP -->
<div id="map" style="height:400px; width:600px; border:2px solid black;"></div>

<br/>

<label>Latitude:</label>
<input type="text" id="lat" />

<label>Longitude:</label>
<input type="text" id="lon" />

<br/><br/>

<label>Address:</label><br/>
<input type="text" id="address" style="width:500px;" />

<script>

let map;
let marker;
let autocomplete;

function initMap() {

    // Default map
    map = new google.maps.Map(document.getElementById("map"), {
        center: { lat: 12.9716, lng: 77.5946 },
        zoom: 12
    });

    // AUTOCOMPLETE SETUP
    const input = document.getElementById("search");

    autocomplete = new google.maps.places.Autocomplete(input);

    // When user selects suggestion
    autocomplete.addListener("place_changed", function () {

        const place = autocomplete.getPlace();

        if (!place.geometry) {
            alert("No details available");
            return;
        }

        let lat = place.geometry.location.lat();
        let lng = place.geometry.location.lng();

        // Move map
        map.setCenter({ lat: lat, lng: lng });
        map.setZoom(15);

        // Marker
        if (marker) {
            marker.setMap(null);
        }

        marker = new google.maps.Marker({
            position: { lat: lat, lng: lng },
            map: map
        });

        // Fill fields
        document.getElementById("lat").value = lat;
        document.getElementById("lon").value = lng;
        document.getElementById("address").value = place.formatted_address;

    });

    // OPTIONAL: click support also
    map.addListener("click", function(event) {

        let lat = event.latLng.lat();
        let lng = event.latLng.lng();

        if (marker) {
            marker.setMap(null);
        }

        marker = new google.maps.Marker({
            position: { lat: lat, lng: lng },
            map: map
        });

        document.getElementById("lat").value = lat;
        document.getElementById("lon").value = lng;

    });

}

window.onload = initMap;

</script>

</body>
</html>