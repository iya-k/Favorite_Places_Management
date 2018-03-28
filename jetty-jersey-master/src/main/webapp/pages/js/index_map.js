function getServerData(url, success){
    $.ajax({
        url : "ws/maps",
	type : "GET",
	dataType : "json"
    }).done(success);
}

function callDone(result){
	var template = _.template($('#index_map').html());

	var indexMap = template({
		"attribute":JSON.stringify(result)
	});

	$("#result").append(html);
