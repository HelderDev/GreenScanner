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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.User;

/**
 *
 * @author AmorimHe
 */
public class UserDAO {

    public void create(User u) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO user (title, name, permission, creation)VALUES(?,?,?,?)");
            stmt.setString(1, u.getTitle());
            stmt.setString(2, u.getName());
            stmt.setInt(3, u.getPermission());
            stmt.setString(4, u.getCreation());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso|");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar:" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    public List <User> read(){
         Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        ResultSet rs = null;
        
        List <User> users = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM user");
           rs = stmt.executeQuery();
           
           while (rs.next()){
               User user = new User();
               user.setId(rs.getInt("id"));
               user.setName(rs.getString("name"));
               user.setTitle(rs.getString("title"));
               user.setPermission(rs.getInt("permission"));
               user.setCreation(rs.getString("creation"));

               users.add(user);
           }
        } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao acessar o banco" + ex);

         }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return users;
    }
    
}
