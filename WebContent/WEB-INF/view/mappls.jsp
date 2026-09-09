<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Mappls Search Only</title>

    <!-- Mappls SDK -->
    <script src="https://apis.mappls.com/advancedmaps/api/584ed90f-f568-4771-878f-60e91ddad5e0/map_sdk?layer=vector&v=3.0"></script>

    <style>
        #map {
            width: 100%;
            height: 500px;
        }

        #searchContainer {
            margin-bottom: 10px;
        }
    </style>
</head>

<body>

<h2>Search Location</h2>

<div id="searchContainer">
    <input type="text" id="searchBox" placeholder="Search location..." style="width:300px; padding:6px;" />
    <button onclick="searchPlace()">Search</button>
</div>

<div id="map"></div>

<script>

    let map;
    let marker;

    function initMap() {
        // Default map (no current location)
        map = new mappls.Map('map', {
            center: [20.5937, 78.9629], // India center
            zoom: 5
        });
    }

    function searchPlace() {

        let query = document.getElementById("searchBox").value.trim();

        if (!query) {
            alert("Please enter a location");
            return;
        }

        fetch("https://atlas.mappls.com/api/places/autoSuggest?query="
                + encodeURIComponent(query)
                + "&key=584ed90f-f568-4771-878f-60e91ddad5e0")            .then(res => res.json())
            .then(data => {

                if (data.suggestedLocations && data.suggestedLocations.length > 0) {

                    let place = data.suggestedLocations[0];

                    let lat = parseFloat(place.latitude);
                    let lng = parseFloat(place.longitude);

                    // Move map
                    map.setCenter([lat, lng]);
                    map.setZoom(15);

                    // Remove old marker
                    if (marker) {
                        marker.remove();
                    }

                    // Add new marker
                    marker = new mappls.Marker({
                        map: map,
                        position: { lat: lat, lng: lng }
                    });

                } else {
                    alert("Location not found");
                }
            })
            .catch(err => {
                console.error(err);
                alert("Search failed");
            });
    }

    // Enter key support
    document.getElementById("searchBox").addEventListener("keypress", function(e) {
        if (e.key === "Enter") {
            searchPlace();
        }
    });

    window.onload = initMap;

</script>

</body>
</html>