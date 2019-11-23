/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.FingerPrint;

/**
 *
 * @author hdrhe
 */
public class FingerPrintDAO {

    public List<FingerPrint> readAllFingers() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        ResultSet rs = null;

        List<FingerPrint> fPrints = new ArrayList<>();

        try {

            stmt = con.prepareStatement("select * from fingerprint order by finger_detail desc");
            rs = stmt.executeQuery();

            while (rs.next()) {
                FingerPrint fp = new FingerPrint();
                fp.setId(rs.getInt("id"));
                fp.setId_user(rs.getInt("id_user"));
                fp.setFinger_detail(rs.getString("finger_detail"));
 
                fPrints.add(fp);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao acessar o banco: " + ex);

        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return fPrints;
    }
}
