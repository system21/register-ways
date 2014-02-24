  --select st_srid(geom) from   huaraz limit 1

CREATE OR REPLACE FUNCTION consultar(_string_geom varchar)
  RETURNS TABLE(
  gid int ,
  idmanzana varchar(15),
  nombdist varchar(255),
  estrato varchar(255)
 ) AS
$func$
DECLARE
	_geom Geometry;
	_data  character varying(255);
	_num integer;

	BEGIN
		_geom=ST_GeomFromText(_string_geom,4326);
		--_num=(SELECT count(gid) FROM huaraz WHERE ST_Within(huaraz.geom,_geom));
		RETURN QUERY SELECT  huaraz.gid, huaraz.idmanzana, huaraz."nombdist,", huaraz."estrato,n"  FROM huaraz WHERE ST_Within(huaraz.geom,_geom);
	END;
$func$ LANGUAGE plpgsql;




 select gid, idmanzana,nombdist,estrato from  consultar2( 
'POLYGON((-77.53652572631836 -9.539050126117454,-77.53652572631836 -9.523052054374775,-77.5147247314453 -9.523052054374775,-77.5147247314453 -9.539050126117454,-77.53652572631836 -9.539050126117454  ))', 1.2
)



-------------------------------------------------------------------------------------------------------------------------
--esta funcion ya es con tod rectangulo, poligono y circulo

CREATE OR REPLACE FUNCTION consultar_allways(_string_geom varchar, _radio decimal)
  RETURNS TABLE(
  gid int ,
  idmanzana varchar(15),
  nombdist varchar(255),
  estrato varchar(255)
 ) AS
$func$
DECLARE
	 
	_geom Geometry;
	_data  character varying(255);
	_num integer;

	BEGIN
		IF _radio=0 THEN
			_geom=ST_GeomFromText(_string_geom,4326);
		ELSE
		    _geom=ST_Buffer(ST_GeomFromText(_string_geom,4326), _radio);
		    
		END IF;
		
		--_num=(SELECT count(gid) FROM huaraz WHERE ST_Within(huaraz.geom,_geom));
		RETURN QUERY SELECT  huaraz.gid, huaraz.idmanzana, huaraz."nombdist,", huaraz."estrato,n"  FROM huaraz WHERE ST_Within(huaraz.geom,_geom);
	END;
$func$ LANGUAGE plpgsql;

 select gid, idmanzana,nombdist,estrato from  consultar_allways('POINT(-77.52820014953612 -9.533548228393597)',0.0052208587806825595 )



