$('#resetPasswordSubmit').click(function(event){
    var activationCode = $('#activation-code').val();
    var newPassword = $('#new-password').val();
    var confirmPassword = $('#confirm-password').val();

    console.log("forgetPasswordSubmit");

    $.ajax({
        type: 'POST',
        url: '../ws/users/reset_password',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({
            'activationCode' : activationCode,
            'newPassword' : newPassword,
            'confirmPassword' : confirmPassword
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