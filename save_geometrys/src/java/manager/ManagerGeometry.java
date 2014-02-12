/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import bean.BGeometry;
import dao.DaoGeometry;
import datasource.BDConnecion;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author ruben
 */
public class ManagerGeometry {

    Connection cn = null;

    public ManagerGeometry(BDConnecion connecion) {
        this.cn = connecion.getConnection();
    }

    public int optener_ultimo() {
        DaoGeometry daopolygon = new DaoGeometry(cn);

        int ultimo;
        ultimo = daopolygon.optener_ultimo();
        return ultimo;
    }

    public void insert_geometry(BGeometry bGeometry) {
        DaoGeometry daopolygon = new DaoGeometry(cn);

        daopolygon.insert_geometry(bGeometry);

    }

}
