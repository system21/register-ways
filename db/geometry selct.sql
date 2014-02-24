 SELECT gid, idmanzana,"estrato,n" as estrato, ST_AsText(geom) from huaraz where "estrato,n" = '' ;

bit_length

 SELECT gid, idmanzana,bit_length("estrato,n") as estrato, ST_AsText(geom) from huaraz where bit_length("estrato,n")<0
 
CREATE OR REPLACE VIEW manzanas AS 
	SELECT gid, idmanzana,"estrato,n" as estrato,
	 replace(replace(replace( ST_AsText(geom), 'MULTIPOLYGON', ''),'(','['),')',']')
	 as cordinates FROM huaraz where bit_length("estrato,n")=8;


select gid,idmanzana, estrato, cordinates from manzanas


