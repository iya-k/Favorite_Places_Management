function getServerData(url, success){
    $.ajax({
        url : "ws/users",
	type : "PUT",
	dataType : "json"
    }).done(success);
}

function callDone(result){
	var template = _.template($('#register').html());

	var addUser = template({
		"attribute":JSON.stringify(result)
	});

	$("#result").append(html);