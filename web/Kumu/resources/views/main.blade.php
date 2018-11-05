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
		<script src="{{ asset('jqueryui/jquery.easy-autocomplete.js') }}"></script>
		<link rel="stylesheet" href="{{ asset('jqueryui/easy-autocomplete.css') }}" />
		<!-- Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,600" rel="stylesheet" type="text/css">

	<style>
		.autocomplete-suggestions { border: 1px solid #999; background: #FFF; overflow: auto; }
		.autocomplete-suggestion { padding: 2px 5px; white-space: nowrap; overflow: hidden; }
		.autocomplete-selected { background: #F0F0F0; }
		.autocomplete-suggestions strong { font-weight: normal; color: #3399FF; }
		.autocomplete-group { padding: 2px 5px; }
		.autocomplete-group strong { display: block; border-bottom: 1px solid #000; }
	</style>

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
				<input type="text" id="filter_plant" placeholder="Plant Name (Taxa or Common)"/>
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


	<script>var base_url = '{{ asset("/") }}';</script>
	<script src="{{ asset('js/main.js') }}"></script>
    </body>
</html>
