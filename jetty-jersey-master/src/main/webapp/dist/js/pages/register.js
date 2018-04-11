$('#registerSubmit').click(function(event){
	var firstName = $('#first-name').val();
	var lastName = $('#last-name').val();
	var username = $('#username').val();
	var email = $('#email').val();
	var password = $('#password').val();
	var confirmPassword = $('#confirm-password').val();

	$.ajax({
		type: 'POST',
		url: '../ws/users/register',
		dataType: 'json',
		contentType: 'application/json',
		data: JSON.stringify({
            'firstName' : firstName,
            'lastName' : lastName,
            'username' : username,
            'email' : email,
            'password' : password,
            'confirmPassword' : confirmPassword
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