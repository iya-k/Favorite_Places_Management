$('#loginSubmit').click(function(event){
	var username = $('#email').val();
	var password = $('#password').val();

	$.ajax({
		type: 'POST',
		url: '../ws/users/login',
		dataType: 'json',
		contentType: 'application/json',
		data: JSON.stringify({
            'username' : username,
            'password' : password
        }),
		success: function(data) {
			if(data == 1){
                window.location.replace("http://localhost:8082/map_show/");
			}else if(data == 0){
				alert("username or password incorrect");
			}else{
				alert("something went wrong,\nplease try again !!");
			}
		},
		fail: function(){
            alert("something went wrong,\nplease try again !!");
		},
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert("Status: " + textStatus + "\nError: " + errorThrown);
        }
    });

	event.preventDefault();
});