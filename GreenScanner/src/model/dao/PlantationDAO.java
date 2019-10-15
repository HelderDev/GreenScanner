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
import model.bean.User;

/**
 *
 * @author AmorimHe
 */
public class PlantationDAO {

 
    public List<Plantation> read(int u) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        ResultSet rs = null;
        
        List<Plantation> plants = new ArrayList<>();
        
        try {
            
            stmt = con.prepareStatement("select * from plantation p inner join user u on  u.id = p.id_owner where u.id = ?");
            stmt.setInt(1, u);
            System.out.println("ID PlantationDAO: " + u);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Plantation plant = new Plantation();
                plant.setId(rs.getInt("p.id"));
                plant.setName(rs.getString("p.name"));
                plant.setId_owner(rs.getInt("p.id_owner"));
                plant.setAddress(rs.getString("p.address"));
                plant.setCity(rs.getString("p.city"));
                plant.setState(rs.getString("p.state"));
                
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
