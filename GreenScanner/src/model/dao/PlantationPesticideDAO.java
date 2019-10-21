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
import model.bean.Plantation;
import model.bean.PlantationPesticide;

/**
 *
 * @author hdrhe
 */
public class PlantationPesticideDAO {

    public List<PlantationPesticide> pesticides(int id) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        ResultSet rs = null;

        List<PlantationPesticide> plants = new ArrayList<>();

        try {
            String sql = "select * from plants_and_pesticides where plantID = ?";

            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                PlantationPesticide plant = new PlantationPesticide();

                plant.setId(rs.getInt("plantPestID"));
                plant.setId_plantation(rs.getInt("plantID"));
                //   id_plantation = rs.getInt("plantPest.id_plantation");
                plant.setId_pesticide(rs.getInt("pestID"));
                plant.setPlantName(rs.getString("plantName"));
                plant.setPestName(rs.getString("pestName"));
                if (rs.getBoolean("allowed")) {
                    plant.setAllowed("✔");
                } else {
                    plant.setAllowed("✖");

                }
                 plants.add(plant);

             }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao acessar o banco" + ex);

        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return plants;
    }
}
