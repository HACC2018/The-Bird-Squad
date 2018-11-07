var map = L.map('mapid').setView([21.47, -157.98], 8);
L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
	maxZoom: 18,
	id: 'mapbox.streets',
	accessToken: 'pk.eyJ1Ijoic2VuZGVyam9zaCIsImEiOiJjam55NWs2M2EwbDhjM3BxcGQzdG5mbTNlIn0.VaQXlbgJ99FNMw_-U865hw'
}).addTo(map);

var markers = L.layerGroup().addTo(map);

function closeImage() {
	console.log('heelp');
	$('#largeImageOnClick').html('');
}

var greenIcon = L.icon({
    iconUrl: 'PIN.png',
});

function markerClick(e){
	$('#marker_taxaname').html(e.target.taxaname);
	$('#marker_commonname').html(e.target.commonname);
	$('#marker_fedstatus').html(e.target.fedstatus);
	$('#marker_planttype').html(e.target.planttype);
	$('#marker_locationnotes').html(e.target.locationnotes);
	$('#marker_images').html(e.target.imagehtml);
	$('#marker_plantnotesorstory').html(e.target.plantnotesorstory);
}

//On load
$(document).ready(function () {

	//Autocomplete
	var options = {

		url: function (phrase) {
			return "auto/" + $('#filter_plant').val();
		},

		getValue: function (element) {
			return element.value;
		},

		ajaxSettings: {
			dataType: "json",
			method: "GET",
			data: {
				dataType: "json"
			}
		},

		requestDelay: 400
	};

	$("#filter_plant").easyAutocomplete(options);

	
	$('body').on('click', 'a.image_click', function () {
		$('#largeImageOnClick').html('<img src="' + $(this).find('img').attr('src') + '" /> <button onclick="closeImage()">Close Image</button>');
	});
	

	//Use AJAX to get all pinmarks
	$.ajaxSetup({
		headers: {
			'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
		}
	});

	let obj = { 
			'filter_plant': $('#filter_plant').val(), 
			'filter_island': $('#filter_island').find(':selected').val(), 
			'filter_plant_age': $('#filter_plant_age').find(':selected').val(), 
			'filter_fed_status': $('#filter_fed_status').find(':selected').val() 
		};
	let jsonSend = JSON.stringify(obj);

	//On first load, get markers for all locations
	$.ajax({
		type: "POST",
		url: '/',
		data: { json: jsonSend },
		success: function (msg) {
			getAndSetImages(msg);
		}
	});

	$(".filter").change(function () {
		obj = {
			'filter_plant': $('#filter_plant').val(),
			'filter_island': $('#filter_island').find(':selected').val(),
			'filter_plant_age': $('#filter_plant_age').find(':selected').val(),
			'filter_fed_status': $('#filter_fed_status').find(':selected').val()
		};
		jsonSend = JSON.stringify(obj);
		markers.clearLayers();
		$.ajax({
			type: "POST",
			url: '/',
			data: { json: jsonSend },
			success: function (msg) {
				getAndSetImages(msg);
			}
		});
	});

	function getAndSetImages(msg){
		let jsonGet = JSON.parse(msg);
		console.log(jsonGet);

		let tempFormID = [];
		for (var i = 0; i < jsonGet.length; i++) {
			let formID = jsonGet[i].FormID;
			if (!tempFormID.includes(formID)) {
				tempFormID.push(formID);
				$.ajax({
					type: "GET",
					url: 'image/' + formID + '/' + i,
					success: function (msg) {
						let images = JSON.parse(msg);

						let long = null, lat = null;
						let ind = images.index;
						//Make sure it can at least go once
						if (images.hasOwnProperty('0') && long == null && lat == null) {
							long = images[0].Longitude;
							lat = images[0].Latitude;


							let imageHTML = '<div>';

							for (var k in images) {
								var image = images[k].ImageName;
								if (typeof image !== 'undefined') {
									imageHTML += '<a class="image_click" href="#"><img class="circlePic" src="' + base_url + images[k].ImageName + '"  /></a>';
								}
							}
							imageHTML += '</div>';

							let commonName = jsonGet[ind].CommonName;
							let plantType = jsonGet[ind].PlantType;
							let locationNotes = jsonGet[ind].LocationNotes;
							let fedStatus = jsonGet[ind].FedStatus;
							let plantNotesOrStory = jsonGet[ind].PlantNotesOrStory;
							if(!commonName) {
								commonName = 'No common name';
							}
							if(!fedStatus){
								fedStatus = 'Non-endangered or not reported';
							}
							if(!locationNotes){
								locationNotes = 'No location notes';
							}
							if(!plantNotesOrStory){
								plantNotesOrStory = '<p>No plant notes or story</p>';
							}

							let marker = L.marker([lat, long, {icon: greenIcon}]);
							marker.taxaname = jsonGet[ind].TaxaName;
							marker.fedstatus = fedStatus;
							marker.commonname = commonName;
							marker.planttype = plantType;
							marker.locationnotes = locationNotes;
							marker.imagehtml = imageHTML;
							marker.plantnotesorstory = plantNotesOrStory;
							marker.on('click', markerClick);

							marker.addTo(markers);
						}
					}
				});
			}
		}
	}
});