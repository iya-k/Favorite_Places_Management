function postLogin(pseudo, pass, success){
	$.ajax({
        dataType: "json",
        url: "ws/users/"+pseudo+"/"+pass,
        type: "POST",
    }).done(success)
	.fail(success);
}

function login(result){
	var r =  JSON.stringify(result[0]);
	var role = r.toString().substring(1,r.toString().length-1);
	//alert(role);
	console.log("role in login "+role)
	if(role === "incorrect"){
		
		$(".alert").html("Wrong Username or Password");
		$(".alert").show();
		
	}
	else
	{	
		var pseudo =localStorage.getItem("tmp");
		localStorage.setItem("pseudo",pseudo);
		localStorage.setItem("role",role);
		document.location.href="show_map.html";
	}
}

$("#ok").click(function (){
	var pseudo =$('#username').val();
	var pass = $('#password').val();
	if(pseudo == ""){
		$(".alert").html("<a class='close' data-dismiss='alert' href='#'>×</a>Username required");
		$(".alert").show();
	}
	else if (pass == "") {
		$(".alert").html("<a class='close' data-dismiss='alert' href='#'>×</a>password required");
		$(".alert").show();
	}
	else {
		localStorage.setItem("tmp",pseudo);
		
		var jq = $.post( "ws/login/"+pseudo+"/"+pass, function() {
			})
			  .done(login)
			  .fail(function() {
			    alert( "error" );
			  });
		//postLogin(pseudo,pass,login);
	}
});
