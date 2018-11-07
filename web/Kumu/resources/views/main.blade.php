<!doctype html>
<html lang="{{ app()->getLocale() }}">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="css/style.css">
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
		<script src="{{ asset('jqueryui/jquery.easy-autocomplete.js') }}"></script>
		<link rel="stylesheet" href="{{ asset('jqueryui/easy-autocomplete.css') }}" />
		<!-- Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,600" rel="stylesheet" type="text/css">

		<link rel="stylesheet" href="{{ asset('css/style.css') }}" />

		<meta name="csrf-token" content="{{ csrf_token() }}">
    </head>
    <body>
	
		<!-- When user clicks on image in popup preview, display in large -->
		<div id="largeImageOnClick">
		</div>

		<div class="grid-container">
			<div class="gContent">
				<!-- Map -->
				<div id="mapid"></div>
			</div>

			<div class="gTopBar">
				<!-- Filters -->
				<div id="filters">
				<ul>
					<li id ="searchBar">
						<input type="text" id="filter_plant" placeholder="Plant Name (Taxa or Common)"/>
					</li>

					<li id ="filterIsland">
						<select id="filter_island" class="filter" >
							<option value="none">Any Island</option>
							<option value="1">Hawaiʻi</option>
							<option value="2">Maui</option>
							<option value="3">Oʻahu</option>
							<option value="4">Kauaʻi</option>
							<option value="5">Molokaʻi</option>
							<option value="6">Lānaʻi</option>
							<option value="7">Niʻihau</option>
							<option value="8">Kahoʻolawe</option>
						</select>
					</li>
					
					<li id="filterPlantAge">
						<select id="filter_plant_age" class="filter" >
							<option value="any">Any Age</option>
							<option value="adult">Adult</option>
							<option value="seedling">Seedling</option>
						</select>
					</li>

					<li id="filterFedStatus">
						<select id="filter_fed_status" class="filter" >
							<option value="any">Any Status</option>
							<option value="e">Endangered</option>
							<option value="t">Threatened</option>
						</select>
					</li>
				</ul>
				</div>
			</div>
			<div class="gSideBar">
				<!-- Clicked marker results -->
				<div id="markerRes">
					<div id="marker_images" ><a class="image_click" href="#"><img class="circlePic"  src="{{asset('weblogo.png')}}"/></a></div>
					<div id="marker_taxaname" class= "markerTitle">Colocasia esculenta</div>
					<div class= "markerLabel" > Common Name </div>
					<div id="marker_commonname" class= "markerValue" >Taro, Kalo</div>
					<div class= "markerLabel" > Federal Status </div>
					<div id="marker_fedstatus" class= "markerValue" >Endangered</div>
					<div class= "markerLabel" > Plant Type </div>
					<div id="marker_planttype" class= "markerValue" >Root Vegetables</div>
					<div class= "markerLabel" > Location Notes </div>
					<div id="marker_locationnotes" class= "markerValue" >Grown in ground</div>
					<div id="marker_plantnotesorstory" class= "markerValue"></div>
				</div>
			</div>
		</div>

	<script>var base_url = '{{ asset("/") }}';</script>
	<script src="{{ asset('js/main.js') }}"></script>
    </body>
</html>
