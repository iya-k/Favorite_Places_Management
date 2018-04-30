$.ajax({
    type: 'POST',
    url: '../ws/users/check-user',
    dataType: 'json',
    data: {},
    success: function(data) {
        if(data == 1){
            window.location.replace("/map_show");
        }else{
            window.location.replace("/login");
        }
    },
    fail: function(){
        window.location.replace("/login/");
    },
    error: function() {
        window.location.replace("/login");
    }
});
