/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author hdrhe
 */
public class PlantationPesticide {

    private int id;
    public static int id_plantation;
    private int id_pesticide;
    private String plantName;
    private String pestName;
    private String allowed;

    public String getAllowed() {
        return allowed;
    }

    public void setAllowed(String allowed) {
        this.allowed = allowed;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getPestName() {
        return pestName;
    }

    public void setPestName(String pestName) {
        this.pestName = pestName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_plantation() {
        return id_plantation;
    }

    public void setId_plantation(int id_plantation) {
        this.id_plantation = id_plantation;
    }

    public int getId_pesticide() {
        return id_pesticide;
    }

    public void setId_pesticide(int id_pesticide) {
        this.id_pesticide = id_pesticide;
    }

}
