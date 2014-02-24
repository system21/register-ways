

//var map = L.mapbox.map('map', 'examples.map-9ijuk24y') 



var map = L.mapbox.map('map').setView([-9.49719531412241, -77.5372011697607], 12);

var osm = L.tileLayer('http://tile.openstreetmap.org/{z}/{x}/{y}.png');
var goglesatelite = L.tileLayer('https://khms0.google.com/kh/v=145&src=app&x={x}&y={y}&z={z}');
var goglemap = L.tileLayer('https://mts0.google.com/vt/hl=es&src=app&x={x}&y={y}&z={z}');

L.control.layers({
    'Mapbox': L.mapbox.tileLayer('ruben.h9n9cb8m').addTo(map),
    'Open Street Map': osm,
    'Google Satelite': goglesatelite
            //'Google Map': goglemap

}).addTo(map);


var geojson = {
    "type": "FeatureCollection",
    "features": []
};







var popup = new L.Popup({autoPan: false});

var url_data = "http://localhost:8080/save_geometrys/SManzanas";
$.getJSON(url_data, {
    format: "json"
}).done(function(data) {

    $.each(huaraz.features, function(key, val) {
        var gid = val.properties.gid;

        $.each(data, function(index, value) {
            if (value.gid === gid) {
                huaraz.features[key].properties['estrato'] = value.estrato;
                geojson.features.push(huaraz.features[key]);
                return false;
            }



        });
    });

   // setTimeout(function() {



        var statesLayer = L.geoJson(geojson, {
            style: getStyle,
            onEachFeature: onEachFeature
        }).addTo(map);

        function getStyle(feature) {
            return {
                weight: 2,
                opacity: 0.1,
                color: 'black',
                fillOpacity: 0.7,
                fillColor: getColor(feature.properties.estrato)
            };
        }

// get color depending on population density value
        function getColor(d) {
            return d > 5 ? '#0000ff' :
                    d > 4 ? '#3333ff' :
                    d > 3 ? '#6666ff' :
                    d > 2 ? '#9999ff' :
                    d > 1 ? '#ccccff' :
                    '#ffffff';
        }

        function onEachFeature(feature, layer) {
            layer.on({
                mousemove: mousemove,
                mouseout: mouseout,
                click: zoomToFeature
            });
        }

        var closeTooltip;

        function mousemove(e) {
            var layer = e.target;

            console.log(layer.feature.properties);
            popup.setLatLng(e.latlng);
            popup.setContent('<div class="marker-title">Estrato :' + layer.feature.properties.estrato + '</div>');

            if (!popup._map)
                popup.openOn(map);
            window.clearTimeout(closeTooltip);

            // highlight feature
            layer.setStyle({
                weight: 3,
                opacity: 0.3,
                fillOpacity: 0.9
            });

            if (!L.Browser.ie && !L.Browser.opera) {
                layer.bringToFront();
            }
        }

        function mouseout(e) {
            statesLayer.resetStyle(e.target);
            closeTooltip = window.setTimeout(function() {
                map.closePopup();
            }, 100);
        }

        function zoomToFeature(e) {
            map.fitBounds(e.target.getBounds());
        }

        map.legendControl.addLegend(getLegendHTML());

        function getLegendHTML() {
            var grades = [0, 1, 2, 3, 4, 5],
                    labels = [],
                    from, to;

            for (var i = 0; i < grades.length; i++) {
                from = grades[i];
                to = grades[i + 1];

      labels.push(
        '<li><span class="swatch" style="background:' + getColor(from + 1) + '"></span> ' +
        from + (to ? '&ndash;' + to : '+')) + '</li>';
            }

            return '<span>----Estratos------</span><ul>' + labels.join('') + '</ul>';
        }
        ;




   // }, 1000);

});








