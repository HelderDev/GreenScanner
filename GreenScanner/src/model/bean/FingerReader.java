/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import com.google.gson.Gson;
import java.util.ArrayList;

/**
 *
 * @author hdrhe
 */
public class FingerReader {

    private String version;
    private float width;
    private float height;
    ArrayList<Object> minutiae = new ArrayList<Object>();

    public ArrayList<Object> getMinutiae() {
        return minutiae;
    }

    public void setMinutiae(ArrayList<Object> minutiae) {
        this.minutiae = minutiae;
    }

    // Getter Methods 
    public String getVersion() {
        return version;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    // Setter Methods 
    public void setVersion(String version) {
        this.version = version;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }
    
    

}
