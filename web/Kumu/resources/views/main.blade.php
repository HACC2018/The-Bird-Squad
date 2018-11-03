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

		<meta name="csrf-token" content="{{ csrf_token() }}">
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
				<input type="text" id="filter_plant" class="filter" placeholder="Plant Name" />
			</div>
			<div>
				<label>Island: </label>
				<select id="filter_island" class="filter">
					<option value="none">None</option>
					<option value="1">Hawaiʻi</option>
					<option value="2">Maui</option>
					<option value="3">Oʻahu</option>
					<option value="4">Kauaʻi</option>
					<option value="5">Molokaʻi</option>
					<option value="6">Lānaʻi</option>
					<option value="7">Niʻihau</option>
					<option value="8">Kahoʻolawe</option>
				</select>
			</div>
			<div>
				<label>Plant Age: </label>
				<select id="filter_plant_age" class="filter">
					<option value="any">Any</option>
					<option value="adult">Adult</option>
					<option value="seedling">Seedling</option>
				</select>
			</div>
		</div>

		<!-- Map -->
		<div id="mapid" style="height: 500px; "></div>

		<!-- When user clicks on image in popup preview, display in large -->
		<div id="largeImageOnClick"></div>

		<script>
			var base_url = '{{ asset("/") }}';

			var map = L.map('mapid').setView([21.47, -157.98], 8);
			L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
				maxZoom: 18,
				id: 'mapbox.streets',
				accessToken: 'pk.eyJ1Ijoic2VuZGVyam9zaCIsImEiOiJjam55NWs2M2EwbDhjM3BxcGQzdG5mbTNlIn0.VaQXlbgJ99FNMw_-U865hw'
			}).addTo(map);

			var markers = L.layerGroup().addTo(map);

			function closeImage(){
				
				$('#largeImageOnClick').html('');
			}

			//On load
			$(document).ready(function(){
				$('body').on('click', 'a.image_click', function(){
					$('#largeImageOnClick').html('<img src="' + $(this).find('img').attr('src') + '" /><button onclick="closeImage()">Close Image</button>');
				});

				//Use AJAX to get all pinmarks
				$.ajaxSetup({
					headers: {
						'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
					}
				});

				$(".filter").change(function() {
					let obj = {'filter_plant': $('#filter_plant').val(), 'filter_island': $('#filter_island').find(':selected').val(), 'filter_plant_age': $('#filter_plant_age').find(':selected').val()};
					let jsonSend = JSON.stringify(obj);

					markers.clearLayers();

					event.preventDefault();
					$.ajax({
						type: "POST",  
						url : '/', 
						data: {json: jsonSend},
						success: function(msg){
							let jsonGet = JSON.parse(msg);
							
							let tempFormID = [];
							for(var i = 0; i<jsonGet.length; i++){
								let formID = jsonGet[i].FormID;
								if(!tempFormID.includes(formID)) {
									tempFormID.push(formID);
									$.ajax({
										type: "GET",
										url : 'image/' + formID + '/' + i,
										success: function(msg){
											let images = JSON.parse(msg);
											
											let long = null, lat = null;
											let ind = images.index;
											//Make sure it can at least go once
											if(images.hasOwnProperty('0') && long == null && lat == null){
												long = images[0].Longitude;
												lat = images[0].Latitude;
												

												let imageHTML = '<div>';
												
												for(var k in images){
													var image = images[k].ImageName;
													if(typeof image !== 'undefined'){
														imageHTML += '<a class="image_click" href="#"><img src="' + base_url + images[k].ImageName + '" style="width:100px;" /></a>';
													}
												}
												imageHTML += '</div>';
												console.log(imageHTML);
												L.marker([lat,long]).addTo(markers)
													.bindPopup('<h1><strong>' + jsonGet[ind].TaxaName + '</strong></h1><br>' + 
														'<h3>' + jsonGet[ind].CommonName + '</h3><br>' +
														jsonGet[ind].PlantType + '<br>' + imageHTML);
											}
										}
									});
								}
							}
						}
					});
				});
			});

		</script>
    </body>
</html>
