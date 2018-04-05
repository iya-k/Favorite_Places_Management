$('#registerSubmit').click(function(event){
	var fullname = $('#fullname').val();
	var username = $('#email').val();
	var password = $('#password').val();
	var password2 = $('#confirm-password').val();

	$.ajax({
		type: 'POST',
		url: '../ws/users/register',
		dataType: 'json',
		contentType: 'application/json',
		data: JSON.stringify({
            'fullname' : fullname,
            'username' : username,
            'password' : password,
            'password2' : password2
        }),
		success: function(data) {
			alert("success");
		},
		fail: function(data) {
			alert("fail");
		}
	});

	event.preventDefault();
});