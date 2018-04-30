/**
 * function get file from {@param fileInput} then code it to base64 string.
 * the string result get saved into {@param base64Input}
 *
 * @param fileInput
 * @param base64Input
 */
function getBase64(fileInput, base64Input) {
	var reader = new FileReader();
	reader.onload = function(){
		base64Input.val(reader.result);
	}
    reader.readAsDataURL(fileInput.get(0).files[0]);
}

/**
 * function get files from {@param fileInput} then code them to base64 string,
 * separate each file code by '|' separator.
 * the string result get saved into {@param base64Input}
 *
 * @param fileInput
 * @param base64Input
 */
function getBase64ManyImages(fileInput, base64Input) {
    $.each(fileInput.get(0).files, function(index, file){
        var reader = new FileReader();
        reader.onload = function(){
            var oldVal = "";
            if(base64Input.val().length != 0){
                oldVal = base64Input.val() + "|";
            }
            base64Input.val(oldVal + reader.result);
        }

        reader.readAsDataURL(file);
    });
}

/**
 * Add map button on click
 */
$("#submitAddMap").click(function(event) {
    var base64Input = $("#map-image-base64");

    var data = {
        'name': $("#map-name").val(),
        'privacy': $("#map-privacy").val(),
        'image': base64Input.val()
    };

    if(data.image.length != 0){
        $.ajax({
            type: 'PUT',
            url: '../ws/maps/add_map',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function(data) {
                console.log("success");

                if(data == 1){
                    $("#add-map-modal").hide();
                    alert("Map has being added with successes");
                }else{
                    alert("Please, Fill all required fields !!!");
                }
            },
            fail: function(data) {
                console.log("fail");
                alert("something went wrong,\nplease, try again !!!");
            }
        });
        base64Input.val("");
    }

    event.preventDefault();
});

$("#submitAddPlace").click(function(event) {
    var fileInput = $("#place-images-form");
    var base64Input = $("#place-image-base64");

    var data = {
        'type': place_type,
        'lat': $("#place-lat").val(),
        'lng': $("#place-lng").val(),
        'name': $("#place-name").val(),
        'message': $("#place-message-form").val(),
        'images' : base64Input.val().split("|"),
        'startDate': $("#begin-date").val(),
        'endDate': $("#end-date").val()
    }

    if(data.images.length != 0){
        $.ajax({
            type: 'POST',
            url: '../ws/id_map/add_place',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function(data) {
                console.log("success")
            },
            fail: function(data) {
                console.log("fail");
            }
        });
        base64Input.val("");
    }
    
    event.preventDefault();
});

$("#map-images-form").change(function(event){
    var fileInput = $("#map-images-form");
    var base64Input = $("#map-image-base64");
    getBase64(fileInput, base64Input);

    event.preventDefault(); 
});

$("#place-images-form").change(function(event){
    var fileInput = $("#place-images-form");
    var base64Input = $("#place-image-base64");
    getBase64ManyImages(fileInput, base64Input);

    event.preventDefault(); 
});

$("#logout").click(function(event){
    $.ajax({
        type: 'POST',
        url: '../ws/users/logout',
        dataType: 'json',
        data: {},
        success: function(data) {
            if(data == 1){
                window.location.replace("http://localhost:8082/login/");
            }else{
                alert("something went wrong,\nplease try again !!!");
            }
        },
        fail: function(data) {
            alert("something went wrong,\nplease try again !!!");
            console.log("fail");
        }
    });

    event.preventDefault();
});