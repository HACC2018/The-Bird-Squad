<!doctype html>
<html lang="{{ app()->getLocale() }}">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Kumu</title>

		<!-- leafletjs CDNs -->
		<link rel="stylesheet" href="https://unpkg.com/leaflet@1.3.4/dist/leaflet.css"
		integrity="sha512-puBpdR0798OZvTTbP4A8Ix/l+A4dHDD0DGqYW6RQ+9jxkRFclaxxQb/SJAWZfWAkuyeQUytO7+7N4QKrDh+drA=="
		crossorigin=""/>
		<script src="https://unpkg.com/leaflet@1.3.4/dist/leaflet.js"
		integrity="sha512-nMMmRyTVoLYqjP9hrbed9S+FzjZHW5gY1TWCHA5ckwXZBadntCNs8kEqAWdrb9O7rxbCaA4lKTIWjDXZxflOcA=="
		crossorigin=""></script>

		<!-- JQuery -->
		<script
			src="https://code.jquery.com/jquery-3.3.1.min.js"
			integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
			crossorigin="anonymous"></script>
		
		<!-- Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,600" rel="stylesheet" type="text/css">

    </head>
    <body>
		<!-- Title -->
        <div>
			KUMU
		</div>

		<!-- Filters -->
		<div id="filters">
			<div>
				<label>Search: </label>
				<input type="text" id="filter_plant" placeholder="Plant Name" />
			</div>
			<div>
				<label>Island: </label>
				<select>
					<option value="none">None</option>
					<option value="hawaii">Hawaiʻi</option>
					<option value="maui">Maui</option>
					<option value="oahu">Oʻahu</option>
					<option value="kauai">Kauaʻi</option>
					<option value="molokai">Molokaʻi</option>
					<option value="lanai">Lānaʻi[</option>
					<option value="niihau">Niʻihau</option>
					<option value="kahoolawe">Kahoʻolawe</option>
				</select>
			</div>
			<div>
				<label>Plant Age: </label>
				<select>
					<option value="any">Any</option>
					<option value="adult">Adult</option>
					<option value="seedling">Seedling</option>
				</select>
			</div>
		</div>

		<!-- Map -->
		<div id="mapid" style="height: 500px; "></div>

		<script>
			var mymap = L.map('mapid').setView([21.47, -157.98], 8);
			L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
				maxZoom: 18,
				id: 'mapbox.streets',
				accessToken: 'pk.eyJ1Ijoic2VuZGVyam9zaCIsImEiOiJjam55NWs2M2EwbDhjM3BxcGQzdG5mbTNlIn0.VaQXlbgJ99FNMw_-U865hw'
			}).addTo(mymap);

			//On load
			$(document).ready(function(){
				//Use AJAX to get all pinmarks
			});

		</script>
    </body>
</html>
