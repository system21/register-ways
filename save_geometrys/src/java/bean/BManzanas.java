/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author ruben
 */
public class BManzanas {

    int gid;
    String idmanzana;
    String nombdist;
    String estrato;
    
    String cordinates;

    public String getEstrato() {
        return estrato;
    }

    public void setEstrato(String estrato) {
        this.estrato = estrato;
    }
    

    public String getNombdist() {
        return nombdist;
    }

    public void setNombdist(String nombdist) {
        this.nombdist = nombdist;
    }

    
    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getIdmanzana() {
        return idmanzana;
    }

    public void setIdmanzana(String idmanzana) {
        this.idmanzana = idmanzana;
    }



    public String getCordinates() {
        return cordinates;
    }

    public void setCordinates(String cordinates) {
        this.cordinates = cordinates;
    }
    
    
}
