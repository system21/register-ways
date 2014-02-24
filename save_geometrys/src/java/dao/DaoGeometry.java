/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.BGeometry;
import bean.BManzanas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ruben
 */
public class DaoGeometry {

    Connection cn = null;//connecion
    PreparedStatement pstmt = null;//pa query
    ResultSet rs = null;///resultados

    public DaoGeometry(Connection cn) {
        this.cn = cn;
    }

    public int optener_ultimo() {
        int num = 0;
        try {
            String sql = "SELECT count(*) as num  FROM geometries;";

            pstmt = cn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                num = rs.getInt("num");
            }

            pstmt.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error en optener posicion  : " + ex);
        }
        return num + 1;

    }

    public void insert_geometry(BGeometry b) {
        String geom = "";
        try {

            /* if (b.getTipo().equals("marker")) {
             geom = "'POINT(" + b.getCordenadas() + ")'";
             } else
             */
            if (b.getTipo().equals("rectangle")) {
                geom = "'POLYGON((" + b.getCordenadas() + "))'";
            } else if (b.getTipo().equals("circle")) {
                geom = b.getCordenadas();
                int indice = geom.indexOf("/");
                String cordenadas = geom.substring(0, indice);
                String radio = geom.substring(indice + 1, geom.length());
                geom = "ST_Buffer(ST_GeomFromText('POINT(" + cordenadas + ")'), " + radio + ")";
                // System.out.println(geom);
            } else if (b.getTipo().equals("polygon")) {
                geom = "'POLYGON((" + b.getCordenadas() + "))'";
            } else if (b.getTipo().equals("polyline")) {

                geom = "'LINESTRING(" + b.getCordenadas() + ")'";
            }

            String sql = "INSERT INTO geometries VALUES ('" + b.getId() + "','" + b.getTipo() + "', " + geom + ");";

            System.out.println("SQ=======================:" + sql);
            pstmt = cn.prepareStatement(sql);
            //  pstmt.executeUpdate();
            pstmt.executeQuery();
            cn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DaoGeometry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insert_geometry(List<BGeometry> list) {

        String query = "";
        String sql = " ";
        String geom = "";
        for (int i = 0; i < list.size(); i++) {
            BGeometry b = list.get(i);
            if (b.getTipo().equals("marker")) {
                geom = "'POINT(" + b.getCordenadas() + ")'";
            }
            if (b.getTipo().equals("rectangle")) {
                geom = "'POLYGON((" + b.getCordenadas() + "))'";
            } else if (b.getTipo().equals("circle")) {
                geom = b.getCordenadas();
                int indice = geom.indexOf("/");
                String cordenadas = geom.substring(0, indice);
                String radio = geom.substring(indice + 1, geom.length());
                geom = "ST_Buffer(ST_GeomFromText('POINT(" + cordenadas + ")'), " + radio + ")";
                // System.out.println(geom);
            } else if (b.getTipo().equals("polygon")) {
                geom = "'POLYGON((" + b.getCordenadas() + "))'";
            } else if (b.getTipo().equals("polyline")) {

                geom = "'LINESTRING(" + b.getCordenadas() + ")'";
            }

            sql = sql + "INSERT INTO geometries VALUES ('" + b.getId() + "','" + b.getTipo() + "', " + geom + ");";
        }

        try {

            System.out.println("SQ=:" + sql);
            pstmt = cn.prepareStatement(sql);
            //  pstmt.executeUpdate();
            pstmt.executeQuery();
            cn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DaoGeometry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<BManzanas> get_manzana() {
        List<BManzanas> list = new LinkedList<BManzanas>();

        try {
            //String sql = "select gid,idmanzana, estrato, cordinates from manzanas;";
            String sql = "select gid,idmanzana, estrato from manzanas;";

            pstmt = cn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                BManzanas bManzanas = new BManzanas();
                System.out.println(rs.getInt("gid"));
                bManzanas.setGid(rs.getInt("gid"));
                bManzanas.setIdmanzana(rs.getString("idmanzana"));
                bManzanas.setEstrato(rs.getString("estrato"));
                // bManzanas.setCordinates(rs.getString("cordinates"));
                list.add(bManzanas);

            }

            pstmt.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error en optener posicion  : " + ex);
        }
        return list;

    }

    public List<BManzanas> consultar(BGeometry b) {
        List<BManzanas> list = new LinkedList<BManzanas>();
        String geom = null;
        double radio = 0;
        try {
            if (b.getTipo().equals("rectangle") || b.getTipo().equals("polygon")) {
                geom = "'POLYGON((" + b.getCordenadas() + "))'";
                radio = 0;
                System.out.println("Re orr P" + geom + "++++" + radio);
            } else if (b.getTipo().equals("circle")) {
                geom = b.getCordenadas();
                int indice = geom.indexOf("/");
                String cordenadas = geom.substring(0, indice);
                radio = Double.parseDouble(geom.substring(indice + 1, geom.length()));

                geom = "'POINT(" + cordenadas + ")'";
                System.out.println("Circle" + geom + "++++" + radio);
            }

            String sql = " select gid, idmanzana,nombdist,estrato from  consultar_allways(" + geom + "," + radio + " )";
            System.out.println(sql);
            pstmt = cn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                BManzanas bManzanas = new BManzanas();
                // System.out.println(rs.getInt("gid"));
                bManzanas.setGid(rs.getInt("gid"));
                bManzanas.setIdmanzana(rs.getString("idmanzana"));
                bManzanas.setNombdist(rs.getString("nombdist"));

                bManzanas.setEstrato(rs.getString("estrato"));
                list.add(bManzanas);
            }

            pstmt.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error en optener posicion  : " + ex);
        }
        return list;

    }

}
