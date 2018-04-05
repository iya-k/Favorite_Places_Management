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
			alert("success");
		},
		fail: function(data){
			alert("fail");
		}
	});

	event.preventDefault();
});