_.templateSettings.variable = "rc";

var new_place_coordinate;

var add_place_autority = false;

var place_type = 0;

//iCheck for checkbox and radio inputs
$('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
    checkboxClass: 'icheckbox_minimal-blue',
    radioClass: 'iradio_minimal-blue'
});

/**
 * fuinction to pop up add map model
 * 
 * @param  {[type]} data [description]
 * @return {[type]}      [description]
 */
function popupAddPlaceModel(data) {
    if (data.hasOwnProperty("lat") && data.hasOwnProperty("lng")) {
        $("#place-lat").val(data.lat);
        $("#place-lng").val(data.lng);
    }
    $("#add-place-modal").modal("show");
};

/**
 * pop up maps list model
 * 
 * @param  {[type]} data [description]
 * @return {[type]}      [description]
 */
function popupMapsListModel(data) {
    var template = _.template($("#maps-list-content").html(), { variable: 'data' });
    var html = template({ data: data.maps });
    $("#maps-list-modal .modal-body").html(html);
    $("#maps-list-modal").modal("show");
}

function popupAddMapModel(data) {
    $("#add-map-modal").modal("show");
}

function initMap() {
    markers = [{
            coords: {
                lat: 48.8662189,
                lng: 2.3189001
            }, // coordiantes
            icon: "../dist/img/map-marker-red.png", // icon image
            label: "location 1", // place name
        },
        {
            coords: {
                lat: 48.8456664,
                lng: 2.3409146
            }, // coordiantes
            icon: "../dist/img/map-marker-blue.png", // icon image
            label: "location 2", // place name
        },
        {
            coords: {
                lat: 48.8375083,
                lng: 2.1000479
            }, // coordiantes
            icon: "../dist/img/map-marker-green.png", // icon image
            label: "location 3", // place name
        },
    ]; // markers to fill on a map

    var map = new google.maps.Map(document.getElementById("google-map"), {
        center: new google.maps.LatLng(48.8589507, 2.2770201),
        zoom: 12
    });

    /**
     * Right click on map listener
     * 
     */
    google.maps.event.addListener(map, 'click', function(event) {
        if (add_place_autority) {
            new_place_coordinate = {
                "lat": event.latLng.lat(),
                "lng": event.latLng.lng()
            };

            $("#markers").show();
            $("#markers").css('top', (event.pixel.y - 60));
            $("#markers").css('left', (event.pixel.x - 85));
            // popupAddPlaceModel(data);

            /*addMarker({coords: {
              lat: latitude,
              lng: longitude
            }});*/
        }
    });

    for (i = 0; i < markers.length; i++) {
        addMarker(markers[i]);
    }

    /**
     * function to add marker on a map
     * 
     */
    function addMarker(markerProp) {
        var marker = new google.maps.Marker({
            map: map,
            position: markerProp.coords,
            label: markerProp.label,
            icon: markerProp.icon,
        });

        marker.addListener('click', function() {
            $("#place-info-modal").modal("show");
            $("#place-info-modal .box-body").html(_.template($("#place-info-content").html()));
            $("#place-lat").val(marker.getPosition().lat);
            $("#place-lng").val(marker.getPosition().lng);

            map.setCenter(this.getPosition());
        });
    }

    $("[data-widget='leave']").click(function() {
        $("#place-info").hide();
        $("#map-content").removeClass("col-md-8");
        $("#map-content").addClass("col-xs-12");
    });
}

$("#markers [marker-target]").click(function() {
    if (add_place_autority) {
        $("#markers").hide();
        console.log($(this).attr('marker-target'));
        popupAddPlaceModel(new_place_coordinate);
        add_place_autority = false;
    }
});

/****************************************************************/
/**      other script for some html element buttons, links     **/
/****************************************************************/
/*
  show edit model edit target
*/
$("[data-widget='edit']").on("click", function() {
    $("#add-place-modal").modal("show");
});

/*
  show add model add target
 */
$("[data-widget='add']").click(function() {
    $("#add-place-modal").modal("show");
});

/* 
  show list of option when user click on plus option
*/
$("#plus-option").click(function() {
    $("#menu-list").toggleClass("show-list");
});

$("#index-map").click(function() {
    // alert("index map get clicked");
    var data = {
        maps: [{
                "url": "../dist/img/map-1.png",
                "name": "Map 1"
            },
            {
                "url": "../dist/img/map-2.png",
                "name": "Map 2"
            },
            {
                "url": "../dist/img/map-3.png",
                "name": "Map 3"
            }
        ]
    };
    popupMapsListModel(data);
});

$("#add-place").click(function() {
    add_place_autority = true;
    // popupAddPlaceModel({});
});

$("#edit-map").click(function() {
    popupAddMapModel({});
});

$("[data-widget='add-map']").click(function() {
    popupAddMapModel();
});

$('#radio-event').on('ifChecked', function(event) {
    $("#begin-date-group").show();
    $("#end-date-group").show();
    place_type = 1;
});

$("#radio-place").on("ifChecked", function(event) {
    $("#begin-date-group").hide();
    $("#end-date-group").hide();
    place_type = 0;
});

//Date range picker
// $('#reservation').daterangepicker();