<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Map</title>

        <link rel="stylesheet" href="libs/libs/leaflet.css" />
        <link rel="stylesheet" href="libs/dist/leaflet.draw.css" />
        <link rel="stylesheet" href="libs/dist/css/bootstrap.css" />
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="libs/libs/leaflet-src.js"></script>

        <script src="libs/src/Leaflet.draw.js"></script>

        <script src="libs/src/edit/handler/Edit.Poly.js"></script>
        <script src="libs/src/edit/handler/Edit.SimpleShape.js"></script>
        <script src="libs/src/edit/handler/Edit.Circle.js"></script>
        <script src="libs/src/edit/handler/Edit.Rectangle.js"></script>

        <script src="libs/src/draw/handler/Draw.Feature.js"></script>
        <script src="libs/src/draw/handler/Draw.Polyline.js"></script>
        <script src="libs/src/draw/handler/Draw.Polygon.js"></script>
        <script src="libs/src/draw/handler/Draw.SimpleShape.js"></script>
        <script src="libs/src/draw/handler/Draw.Rectangle.js"></script>
        <script src="libs/src/draw/handler/Draw.Circle.js"></script>
        <script src="libs/src/draw/handler/Draw.Marker.js"></script>

        <script src="libs/src/ext/LatLngUtil.js"></script>
        <script src="libs/src/ext/GeometryUtil.js"></script>
        <script src="libs/src/ext/LineUtil.Intersect.js"></script>
        <script src="libs/src/ext/Polyline.Intersect.js"></script>
        <script src="libs/src/ext/Polygon.Intersect.js"></script>

        <script src="libs/src/Control.Draw.js"></script>
        <script src="libs/src/Tooltip.js"></script>
        <script src="libs/src/Toolbar.js"></script>

        <script src="libs/src/draw/DrawToolbar.js"></script>
        <script src="libs/src/edit/EditToolbar.js"></script>
        <script src="libs/src/edit/handler/EditToolbar.Edit.js"></script>
        <script src="libs/src/edit/handler/EditToolbar.Delete.js"></script>

        <script src="libs/dist/js/bootstrap.js"></script>
        <link rel="stylesheet" href="css/style.css" />

    </head>
    <body>
        <div id="map" ></div>
        <div id="info">
            <form class="form" method="post"  action="<%=request.getContextPath()%>/SGeometry">

                <div id="rectangle" class="well">

                </div>
                <div id="polygon" class="well">

                </div>
                <div id="circle" class="well">

                </div>

                <div id="line" class="well">

                </div>

                <button class="btn btn-primary" type="submit">Registrar</button>
            </form>
        </div>




        <button id="changeColor">Rectangle -> Blue</button>

        <script src="js/app.js"></script>
    </body>
</html>