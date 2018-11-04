var base_url = '{{ asset("/") }}';

var map = L.map('mapid').setView([21.47, -157.98], 8);
L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
	maxZoom: 18,
	id: 'mapbox.streets',
	accessToken: 'pk.eyJ1Ijoic2VuZGVyam9zaCIsImEiOiJjam55NWs2M2EwbDhjM3BxcGQzdG5mbTNlIn0.VaQXlbgJ99FNMw_-U865hw'
}).addTo(map);

var markers = L.layerGroup().addTo(map);

function closeImage() {

	$('#largeImageOnClick').html('');
}

//On load
$(document).ready(function () {
	$('body').on('click', 'a.image_click', function () {
		$('#largeImageOnClick').html('<img src="' + $(this).find('img').attr('src') + '" /><button onclick="closeImage()">Close Image</button>');
	});

	//Use AJAX to get all pinmarks
	$.ajaxSetup({
		headers: {
			'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
		}
	});

	$(".filter").change(function () {
		let obj = { 'filter_plant': $('#filter_plant').val(), 'filter_island': $('#filter_island').find(':selected').val(), 'filter_plant_age': $('#filter_plant_age').find(':selected').val() };
		let jsonSend = JSON.stringify(obj);

		markers.clearLayers();

		event.preventDefault();
		$.ajax({
			type: "POST",
			url: '/',
			data: { json: jsonSend },
			success: function (msg) {
				let jsonGet = JSON.parse(msg);

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
											imageHTML += '<a class="image_click" href="#"><img src="' + base_url + images[k].ImageName + '" style="width:100px;" /></a>';
										}
									}
									imageHTML += '</div>';
									console.log(imageHTML);
									L.marker([lat, long]).addTo(markers)
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