$.ajax({
    type: 'POST',
    url: '../ws/users/check-user',
    dataType: 'json',
    data: {},
    success: function(data) {
        if(data == 1){
            window.location.replace("http://localhost:8082/map_show/");
        }else{
            window.location.replace("http://localhost:8082/login/");
        }
    },
    fail: function(){
        window.location.replace("http://localhost:8082/login/");
    },
    error: function() {
        window.location.replace("http://localhost:8082/login/");
    }
});
