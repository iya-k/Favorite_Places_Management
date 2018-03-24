
function getServerData(url, success){
    $.ajax({
        dataType: "json",
        url: url
    }).done(success);
}

function callDone(result){
	var templateExample = _.template($('#templateExample').html());

	var html = templateExample({
		"attribute":JSON.stringify(result)
	});

	$("#result").append(html);
}

$(function(){
	$("#button").click(function(){
		getServerData("ws/example/aircraft",callDone);
	});
	$("#place").click(function(){
		getServerData("ws/id_map/id_place",callDone);
	});
	$("#map").click(function(){
		getServerData("ws/map/pass",callDone);
	});
	$("#event").click(function(){
		getServerData("ws/id_map/id_event",callDone);
	});
	$("#login").click(function(){
		getServerData("ws/login/user",callDone);
	});
});