CREATE TABLE geometries (id varchar(15), type varchar, geom geometry);

INSERT INTO geometries VALUES
  ('1g','Point', 'POINT(0 0)'),
  ('2g','Linestring', 'LINESTRING(0 0, 1 1, 2 1, 2 2)'),
  ('3g','Polygon', 'POLYGON((0 0, 1 0, 1 1, 0 1, 0 0))'),
  ('4g','PolygonWithHole', 'POLYGON((0 0, 10 0, 10 10, 0 10, 0 0),(1 1, 1 2, 2 2, 2 1, 1 1))'),
  ('5g','Collection', 'GEOMETRYCOLLECTION(POINT(2 0),POLYGON((0 0, 1 0, 1 1, 0 1, 0 0)))');
INSERT INTO geometries VALUES ('10g','circle',ST_Buffer(ST_GeomFromText('POINT(100 90)'), 50))

/*

  
INSERT INTO geometries VALUES ('9g','circle',ST_GeomFromText('POINT(0.0 20)',4326))


INSERT INTO geometries VALUES ('10g','circle',
ST_Transform(geometry(ST_Buffer(geography(ST_Transform( 'POINT(-73.26 -13.65)', 4326 )), 0.584)), 4326))
SELECT ST_GeomFromText('POINT(0.0 20)',4326) As the_geom)*/
INSERT INTO geometries VALUES ('13g','polygon', POLYGON((-73.2952880859375 -12.774303696888841,-71.180419921875 -14.232437996569342,-73.45458984375 -14.679253895204697,-74.3994140625 -13.149026971287395,-72.57568359375 -12.157485962814548,-57.3486328125 -9.622414142924793,21.09375 -8.581021215641842,-16.875 -47.8721439688873,-62.05078125 -19.31114335506463,-70.86181640625 -12.91890657418042,-73.2952880859375 -12.774303696888841)));

INSERT INTO geometries VALUES ('11g','circle', ST_Buffer(ST_GeomFromText('POINT(-74.3609619140625 -13.047372256948762)'), 2.119143582247862));

SELECT id, type, ST_AsText(geom) FROM geometries;



