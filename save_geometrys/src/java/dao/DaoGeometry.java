/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.BGeometry;
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
            if (b.getTipo().equals("rectangle")) {

                geom = "POLYGON((" + b.getCordenadas() + "))";
            }else{
            geom=b.getCordenadas();
            }
            String sql = "INSERT INTO geometries VALUES ('" + b.getId() + "','" + b.getTipo() + "', '" + geom + "');";
            
            System.out.println("SQ=======================:" + sql);
            pstmt = cn.prepareStatement(sql);
          //  pstmt.executeUpdate();
            pstmt.executeQuery();
            cn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DaoGeometry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
