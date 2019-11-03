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
public class FingerPrint {
    private int id;
    private int id_user;
    private String finger_detail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getFinger_detail() {
        return finger_detail;
    }

    public void setFinger_detail(String finger_detail) {
        this.finger_detail = finger_detail;
    }
}
