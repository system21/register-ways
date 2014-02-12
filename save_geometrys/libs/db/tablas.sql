CREATE TABLE geometries (id varchar(15), type varchar, geom geometry);
/*
INSERT INTO geometries VALUES
  ('1g','Point', 'POINT(0 0)'),
  ('2g','Linestring', 'LINESTRING(0 0, 1 1, 2 1, 2 2)'),
  ('3g','Polygon', 'POLYGON((0 0, 1 0, 1 1, 0 1, 0 0))'),
  ('4g','PolygonWithHole', 'POLYGON((0 0, 10 0, 10 10, 0 10, 0 0),(1 1, 1 2, 2 2, 2 1, 1 1))'),
  ('5g','Collection', 'GEOMETRYCOLLECTION(POINT(2 0),POLYGON((0 0, 1 0, 1 1, 0 1, 0 0)))');*/

  
INSERT INTO geometries VALUES ('9g','circle',ST_GeomFromText('POINT(0.0 20)',4326))


INSERT INTO geometries VALUES ('10g','circle',
ST_Transform(geometry(ST_Buffer(geography(ST_Transform( 'POINT(-73.26 -13.65)', 4326 )), 0.584)), 4326))

INSERT INTO geometries VALUES ('10g','circle',ST_Buffer(ST_GeomFromText('POINT(100 90)'), 50))


SELECT ST_GeomFromText('POINT(0.0 20)',4326) As the_geom)

SELECT id, type, ST_AsText(geom) FROM geometries;



