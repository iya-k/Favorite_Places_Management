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
			document.getElementById("username").value = "";
			document.getElementById("first-name").value = "";
			document.getElementById("email").value = "";
			document.getElementById("last-name").value = "";
			document.getElementById("confirm-password").value = "";
			document.getElementById("password").value = "";
		},
		fail: function(data) {
			alert("fail");
		}
	});

	event.preventDefault();
});