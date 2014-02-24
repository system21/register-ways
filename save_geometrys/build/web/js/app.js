var cloudmadeUrl = 'http://{s}.tile.cloudmade.com/BC9A493B41014CAABB98F0471D759707/997/256/{z}/{x}/{y}.png',
        cloudmade = new L.TileLayer(cloudmadeUrl, {
            maxZoom: 18
        }),
map = new L.Map('map', {
    layers: [cloudmade],
    center: new L.LatLng(-9.49719531412241, -77.5372011697607),
    zoom: 14
});



var drawnItems = new L.FeatureGroup();
map.addLayer(drawnItems);

var layer_to_safe = [];
var bloque = 0;

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
        marker: true
    },
    edit: {
        featureGroup: drawnItems,
        remove: false
    }
});
map.addControl(drawControl);

map.on('draw:created', function(e) {
    var type = e.layerType;
    var layer = e.layer;
    save_object(type, layer);
    drawnItems.addLayer(layer);
});

map.on('draw:edited', function(e) {
    var layers = e.layers;
    var countOfEditedLayers = 0;
    layers.eachLayer(function(layer) {
        countOfEditedLayers++;
        modific_object(layer);
    });
    console.log("Edited " + countOfEditedLayers + " layers");
});

function modific_object(layer) {
    console.log('Objeto Modificado:' + layer.options.id);
    var id = parseInt(layer.options.id.replace("b", ""));
    layer.toGeoJSON().geometry.coordinates[0];

    var type = $('[name=tipo_geometry' + id + ']').val();


    if (type === 'marker') {

        layer.bindPopup('Soy un marker modificado!');
        var longitud = layer.toGeoJSON().geometry.coordinates[0];
        var latitud = layer.toGeoJSON().geometry.coordinates[1];
        var marker = longitud + ' ' + latitud;
        $('[name=geometry' + id + ']').val(marker);


    } else if (type === 'rectangle') {
        var cordenadas = layer.toGeoJSON().geometry.coordinates[0];
        var rectangle = null;
        $.each(cordenadas, function(key, value) {
            if (rectangle === null) {
                rectangle = rectangle + cordenadas[key][0] + ' ' + cordenadas[key][1];
            } else {
                rectangle = rectangle + ',' + cordenadas[key][0] + ' ' + cordenadas[key][1];
            }
        });
        $('[name=geometry' + id + ']').val(rectangle);
    } else if (type === 'circle') {
        var circle = layer.toGeoJSON().geometry.coordinates[0] + ' ' + layer.toGeoJSON().geometry.coordinates[1] + '/' + layer._getLngRadius();
        $('[name=geometry' + id + ']').val(circle);
    }
    else if (type === 'polygon') {
        var cordenadas = layer.toGeoJSON().geometry.coordinates[0];
        var polygon = null;
        $.each(cordenadas, function(key, value) {
            if (polygon === null) {
                polygon = polygon + cordenadas[key][0] + ' ' + cordenadas[key][1];
            } else {
                polygon = polygon + ',' + cordenadas[key][0] + ' ' + cordenadas[key][1];
            }
        });
        $('[name=geometry' + id + ']').val(polygon);
    } else if (type === 'polyline') {

        var cordenadas = layer.toGeoJSON().geometry.coordinates;
        var polyline = null;
        $.each(cordenadas, function(key, value) {

            if (polyline === null) {
                polyline = polyline + cordenadas[key][0] + ' ' + cordenadas[key][1];
            } else {
                polyline = polyline + ',' + cordenadas[key][0] + ' ' + cordenadas[key][1];
            }
        });
        $('[name=geometry' + id + ']').val(polyline);
    }
}
;
function save_object(type, layer) {
    layer_to_safe.push(layer);
    layer.options['id'] = 'b' + bloque;//va a trabajar como un id del objeto creado
    if (type === 'marker') {

        layer.bindPopup('Soy un marker!');
        var longitud = layer.toGeoJSON().geometry.coordinates[0];
        var latitud = layer.toGeoJSON().geometry.coordinates[1];
        $('#marker').append('<div class="well" id="b' + bloque + '"></div>');

        var marker = longitud + ' ' + latitud;
        console.log(marker);
        $('#b' + bloque).append('<input type="text" name="tipo_geometry' + bloque + '" value="' + type + '" >' + '<input type="text" name="geometry' + bloque + '" value="' + marker + '"   readonly>')
        bloque = bloque + 1;
    } else if (type === 'rectangle') {
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
        var rectangle = string_cordenadas;
        $('#b' + bloque).append('<input type="text" name="tipo_geometry' + bloque + '" value="' + type + '" >' + '<input type="text" name="geometry' + bloque + '" value="' + rectangle + '"   readonly>')
        bloque = bloque + 1;




    } else if (type === 'circle') {
    
        $('#circle').append('<div class="well" id="b' + bloque + '"></div>');
        var circle = layer.toGeoJSON().geometry.coordinates[0] + ' ' + layer.toGeoJSON().geometry.coordinates[1] + '/' + layer._getLngRadius();
        //console.log(circle);
        $('#b' + bloque).append('<input type="text" name="tipo_geometry' + bloque + '" value="' + type + '" >' + '<input type="text" name="geometry' + bloque + '" value="' + circle + '"   readonly>')
        bloque = bloque + 1;
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


        });

        var polygon = string_cordenadas;
        $('#b' + bloque).append('<input type="text" name="tipo_geometry' + bloque + '" value="' + type + '" >' + '<input type="text" name="geometry' + bloque + '" value="' + polygon + '"   readonly>')
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
        });

        var polyline = string_cordenadas;

        $('#b' + bloque).append('<input type="text" name="tipo_geometry' + bloque + '" value="' + type + '" >' + '<input type="text" name="geometry' + bloque + '" value="' + polyline + '"   readonly>')
        bloque = bloque + 1;
    }
    $('#objects_table').append('<tr><td>' + bloque + '</td><td>' + type + '</td></tr>')
    $("#num_objects_label").text(bloque);
    $("#num_objects").val(bloque);
    console.log('Objeto creado:' + layer.options.id);
}
;

$(document).on('ready', function() {

});