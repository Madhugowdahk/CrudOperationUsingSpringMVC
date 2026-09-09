<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
<head>
<title>MapmyIndia Search + Picker</title>

<script src="https://apis.mapmyindia.com/advancedmaps/v1/584ed90f-f568-4771-878f-60e91ddad5e0/map_load?v=1.5"></script>

</head>

<body style="background-color:#859cc2">

<h2>Search Location (MapmyIndia)</h2>

<input type="text" id="search" placeholder="Enter location..." style="width:400px; height:30px;" />
<button onclick="searchLocation()">Search</button>

<div id="suggestions" style="background:white; width:400px;"></div>

<br/><br/>

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
let selectedPlace = null;

function initMap() {
    map = new MapmyIndia.Map("map", {
        center: [12.9716, 77.5946],
        zoom: 10
    });
}

function searchLocation() {

    let query = document.getElementById("search").value;

    if (!query.trim()) {
        alert("Enter location");
        return;
    }

    let contextPath = "<%= request.getContextPath() %>";

    fetch(contextPath + `/api/search?query=` + encodeURIComponent(query))
    .then(res => res.json())
    .then(data => {

        if (!data.suggestedLocations || data.suggestedLocations.length === 0) {
            alert("No results found");
            return;
        }

        let place = selectedPlace ? selectedPlace : data.suggestedLocations[0];

        showLocation(place);
    });
}
document.getElementById("search").addEventListener("keyup", function() {
	
	selectedPlace = null;

    let query = this.value;
    if (query.length < 3) return;

    let contextPath = "<%= request.getContextPath() %>";

    fetch(contextPath + `/api/search?query=` + encodeURIComponent(query))
    .then(res => res.json())
    .then(data => {

        let box = document.getElementById("suggestions");
        box.innerHTML = "";

        if (!data.suggestedLocations) return;

        data.suggestedLocations.forEach(place => {

            let div = document.createElement("div");
            div.innerHTML = place.placeName + ", " + place.placeAddress;
            div.style.cursor = "pointer";
            div.style.padding = "5px";
            div.style.borderBottom = "1px solid #ccc";

            div.onmouseover = () => div.style.background = "#eee";
            div.onmouseout = () => div.style.background = "#fff";

            div.onclick = function() {
                document.getElementById("search").value =
                    place.placeName + ", " + place.placeAddress;

                selectedPlace = place;
                box.innerHTML = "";

                showLocation(place); // auto show
            };

            box.appendChild(div);
        });
    });
});
function showLocation(place) {

    let fullAddress = place.placeName + ", " + place.placeAddress;

    fetch("https://nominatim.openstreetmap.org/search?format=json&q=" + encodeURIComponent(fullAddress))
    .then(res => res.json())
    .then(data => {

        if (!data || data.length === 0) {
            alert("Location not found");
            return;
        }

        let lat = parseFloat(data[0].lat);
        let lng = parseFloat(data[0].lon);

        // Move map
        map.setView([lat, lng], 15);

        //  Remove old marker
        if (marker) {
            map.removeLayer(marker);
        }

        // Correct marker (Leaflet)
        marker = L.marker([lat, lng]).addTo(map)
            .bindPopup("<b>" + fullAddress + "</b>")
            .openPopup();

        //  Fill fields
        document.getElementById("lat").value = lat;
        document.getElementById("lon").value = lng;
        document.getElementById("address").value = fullAddress;

    })
    .catch(err => {
        console.error(err);
        alert("Error fetching location");
    });
}


window.onload = initMap;

</script>

</body>
</html>