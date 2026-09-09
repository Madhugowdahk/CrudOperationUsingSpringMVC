<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
<head>
<title>Google Map Picker</title>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCr-ySJ0G_4ZK4VYOIRr_cpGrSyBeEh1pE"></script>

</head>

<body style="background-color:#859cc2">

<h2>Select Location</h2>

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
let geocoder;

function initMap() {

    map = new google.maps.Map(document.getElementById("map"), {
        center: { lat: 12.9716, lng: 77.5946 }, // fallback (Bangalore)
        zoom: 12
    });

    geocoder = new google.maps.Geocoder();

    // GET CURRENT LOCATION
    if (navigator.geolocation) {

        navigator.geolocation.getCurrentPosition(function(position) {

            let lat = position.coords.latitude;
            let lng = position.coords.longitude;

            let userLocation = { lat: lat, lng: lng };

            // Move map to current location
            map.setCenter(userLocation);
            map.setZoom(15);

            // Place marker
            marker = new google.maps.Marker({
                position: userLocation,
                map: map
            });

            // Fill inputs
            document.getElementById("lat").value = lat;
            document.getElementById("lon").value = lng;

            // Fetch address
            geocoder.geocode({ location: userLocation }, function(results, status) {

                if (status === "OK" && results[0]) {
                    document.getElementById("address").value = results[0].formatted_address;
                } else {
                    document.getElementById("address").value = "Address not found";
                }

            });

        }, function() {
            alert("Location access denied");
        });

    } else {
        alert("Geolocation not supported");
    }

    // Existing click logic
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

        geocoder.geocode({ location: { lat: lat, lng: lng } }, function(results, status) {

            if (status === "OK" && results[0]) {
                document.getElementById("address").value = results[0].formatted_address;
            } else {
                document.getElementById("address").value = "Address not found";
            }

        });

    });
}
window.onload = initMap;

</script>

</body>
</html>