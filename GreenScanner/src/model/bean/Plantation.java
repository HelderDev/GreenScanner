/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author AmorimHe
 */
public class Plantation {

    private int uID;
    private String uName;
    private String uTitle;
    private int uPermission;
    private String uCreation;

    private int id;
    private int id_owner;
    private String name;
    private String address;
    private String city;
    private String state;

    public int getuID() {
        return uID;
    }

    public void setuID(int uID) {
        this.uID = uID;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_owner() {
        return id_owner;
    }

    public void setId_owner(int id_owner) {
        this.id_owner = id_owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getuTitle() {
        return uTitle;
    }

    public void setuTitle(String uTitle) {
        this.uTitle = uTitle;
    }

    public int getuPermission() {
        return uPermission;
    }

    public void setuPermission(int uPermission) {
        this.uPermission = uPermission;
    }

    public String getuCreation() {
        return uCreation;
    }

    public void setuCreation(String uCreation) {
        this.uCreation = uCreation;
    }

}
