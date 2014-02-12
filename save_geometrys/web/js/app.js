var cloudmadeUrl = 'http://{s}.tile.cloudmade.com/BC9A493B41014CAABB98F0471D759707/997/256/{z}/{x}/{y}.png',
        cloudmade = new L.TileLayer(cloudmadeUrl, {
            maxZoom: 18
        }),
map = new L.Map('map', {
    layers: [cloudmade],
    center: new L.LatLng(-13.7772, -74.2756),
    zoom: 8
});

var drawnItems = new L.FeatureGroup();
map.addLayer(drawnItems);

var layer_to_safe = [];
var bloque = 0;
// Set the title to show on the polygon button
L.drawLocal.draw.toolbar.buttons.polygon = 'Draw a sexy polygon!';

var drawControl = new L.Control.Draw({
    position: 'topright',
    draw: {
        polyline: {
            metric: true
        },
        polygon: {
            allowIntersection: false,
            showArea: true,
            drawError: {
                color: '#b00b00',
                timeout: 1000
            },
            shapeOptions: {
                color: '#bada55'
            }
        },
        circle: {
            shapeOptions: {
                color: '#662d91'
            }
        },
        marker: false
    },
    edit: {
        featureGroup: drawnItems,
        remove: false
    }
});
map.addControl(drawControl);

map.on('draw:created', function(e) {
    var type = e.layerType,
            layer = e.layer;

    console.log(type);
    if (type === 'marker') {
        layer.bindPopup('A popup!');
    }

    layer_to_safe.push(layer);

    if (type === 'rectangle') {
        var cordenadas = layer.toGeoJSON().geometry.coordinates[0];
        var string_cordenadas = null;
        $('#rectangle').append('<div class="well" id="b' + bloque + '"></div>');
        $.each(cordenadas, function(key, value) {
            if (string_cordenadas === null) {
                string_cordenadas = string_cordenadas + cordenadas[key][0] + ' ' + cordenadas[key][1];
            } else {
                string_cordenadas = string_cordenadas + ',' + cordenadas[key][0] + ' ' + cordenadas[key][1];
            }
        });
        $('#b' + bloque).append('<input type="text" name="tipo_geometry" value="' + type + '" >' + '<input type="text" name="geometry' + bloque + '" value="' + string_cordenadas + '"  style="width: 150px" readonly>')
        bloque = bloque + 1;
    } else if (type === 'circle') {
        /* console.log(layer._getLngRadius());
         console.log(layer.toGeoJSON().geometry);
         */
        $('#circle').append('<div class="well" id="b' + bloque + '"></div>');
        var circle = layer.toGeoJSON().geometry.coordinates[0] + ' ' + layer.toGeoJSON().geometry.coordinates[1] + '/' + layer._getLngRadius();
        console.log(circle);
        $('#b' + bloque).append('<input type="text" name="tipo_geometry" value="' + type + '" >' + '<input type="text" name="geometry' + bloque + '" value="' + circle + '"  style="width: 150px" readonly>')
        bloque = bloque + 1;

        //



    } else if (type === 'polygon') {
        var cordenadas = layer.toGeoJSON().geometry.coordinates[0];
        $('#polygon').append('<div class="well" id="b' + bloque + '"></div>');
        // console.log(cordenadas)
        var string_cordenadas = null;
        $.each(cordenadas, function(key, value) {
            if (string_cordenadas === null) {
                string_cordenadas = string_cordenadas + cordenadas[key][0] + ' ' + cordenadas[key][1];
            } else {
                string_cordenadas = string_cordenadas + ',' + cordenadas[key][0] + ' ' + cordenadas[key][1];
            }

            console.log(string_cordenadas)
        });

        var polygon = string_cordenadas;
        $('#b' + bloque).append('<input type="text" name="tipo_geometry" value="' + type + '" >' + '<input type="text" name="geometry' + bloque + '" value="' + polygon + '"  style="width: 150px" readonly>')
        bloque = bloque + 1;

    } else if (type === 'polyline') {

        $('#polyline').append('<div class="well" id="b' + bloque + '"></div>');

        var cordenadas = layer.toGeoJSON().geometry.coordinates;
        var string_cordenadas = null;

        $.each(cordenadas, function(key, value) {
            cordenadas[key]
            if (string_cordenadas === null) {
                string_cordenadas = string_cordenadas + cordenadas[key][0] + ' ' + cordenadas[key][1];
            } else {
                string_cordenadas = string_cordenadas + ',' + cordenadas[key][0] + ' ' + cordenadas[key][1];
            }

            // console.log(string_cordenadas)
        });

        var polyline = string_cordenadas;
        $('#b' + bloque).append('<input type="text" name="tipo_geometry" value="' + type + '" >' + '<input type="text" name="geometry' + bloque + '" value="' + polyline + '"  style="width: 150px" readonly>')
        bloque = bloque + 1;



    }

    $("#num_objects").val(bloque);

    drawnItems.addLayer(layer);
});

map.on('draw:edited', function(e) {
    var layers = e.layers;

    $.each(layer_to_safe, function(key, value) {
        console.log(layer_to_safe[key].toGeoJSON());
    });

    getevent();

});

L.DomUtil.get('changeColor').onclick = function() {
    drawControl.setDrawingOptions({
        rectangle: {
            shapeOptions: {
                color: '#004a80'
            }
        }
    });
};


function getevent(layers) {

    //	console.log(layer_to_safe);



}
;


$(document).on('ready', function() {



});