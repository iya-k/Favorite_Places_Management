$("#submitAddMap").click(function(event){

	var data = {
		'name' : $("#map-name").val(),
		'privacy' : $("#map-privacy").val()
	};

	/*var name = $("#map-name").val();
	var privacy = $("#map-privacy").val();
	var file = $("#map-images-form");
	var image = null;
	if(file.prop("files").length > 0){
		var image = $("#map-images-form").prop("files")[0].__proto__;
		console.log("image: " + image);
	}*/

	$.ajax({
		type: 'PUT',
		url: '../ws/maps/add_map',
		dataType: 'json',
		contentType: 'application/json',
		data: JSON.stringify(data),
		success: function(data) {
			alert("success");
		},
		fail: function(data){
			alert("fail");
		}
	});

	event.preventDefault();
});

$("#submitAddPlace").click(function(event){
	var data = {
		'type' : place_type,
		'lat' : $("#place-lat").val(),
		'lng' : $("#place-lng").val(),
		'name' : $("#place-name").val(),
		'message' : $("#place-message-form").val(),
		'startDate' : $("#begin-date").val(),
		'endDate' : $("#end-date").val()
	}

	$.ajax({
		type: 'POST',
		url: '../ws/id_map/add_place',
		dataType: 'json',
		contentType: 'application/json',
		data: JSON.stringify(data),
		success: function(data) {
			alert("success");
		},
		fail: function(data){
			alert("fail");
		}
	});

	event.preventDefault();
});