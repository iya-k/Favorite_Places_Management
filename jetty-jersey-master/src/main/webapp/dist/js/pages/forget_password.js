$('#forgetPasswordSubmit').click(function(event){
    var email = $('#email').val();

    console.log("forgetPasswordSubmit");

    $.ajax({
        type: 'POST',
        url: '../ws/users/forget_password',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({
            'email' : email
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