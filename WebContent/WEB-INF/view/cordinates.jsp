<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
<head>
<title>Google Map Picker</title>

<!-- Keep Maps JS only for map rendering -->
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

function initMap() {

    map = new google.maps.Map(document.getElementById("map"), {
        center: { lat: 12.9716, lng: 77.5946 }, // fallback
        zoom: 12
    });

    //  CURRENT LOCATION LOGIC
    if (navigator.geolocation) {

        navigator.geolocation.getCurrentPosition(function(position) {
            console.log(navigator.geolocation)
            let lat = position.coords.latitude;
            let lng = position.coords.longitude;

            let userLocation = { lat: lat, lng: lng };

            // Move map
            map.setCenter(userLocation);
            map.setZoom(15);

            // Marker
            marker = new google.maps.Marker({
                position: userLocation,
                map: map
            });

            // Fill inputs
            document.getElementById("lat").value = lat;
            document.getElementById("lon").value = lng;

            //  CALL YOUR EXISTING BACKEND
            fetch('getAddressFromGoogle?lat=' + lat + '&lon=' + lng)
                .then(res => res.json())
                .then(data => {

                    if (data && data.display_name) {
                        document.getElementById("address").value = data.display_name;
                    } else {
                        document.getElementById("address").value = "Address not found";
                    }

                })
                .catch(() => {
                    document.getElementById("address").value = "Error fetching address";
                });

        }, function() {
            console.log("User denied location access");
        });

    }

    // EXISTING CLICK LOGIC (unchanged)
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

        fetch('reverseGeocode?lat=' + lat + '&lon=' + lng)
            .then(res => res.json())
            .then(data => {

                if (data && data.display_name) {
                    document.getElementById("address").value = data.display_name;
                } else {
                    document.getElementById("address").value = "Address not found";
                }

            })
            .catch(() => {
                document.getElementById("address").value = "Error fetching address";
            });

    });
}

window.onload = initMap;

</script>
</body>
</html>