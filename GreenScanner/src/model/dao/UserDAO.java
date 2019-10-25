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
import model.bean.User;

/**
 *
 * @author AmorimHe
 */
public class UserDAO {

    public static int idValue;
    public static String userName;
    public static String titleName;

    public boolean checkLogin(String login, String senha) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        ResultSet rs = null;
        boolean check = false;
        //  List<User> users = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM user WHERE name = ? and id = ?");
            stmt.setString(1, login);
            stmt.setString(2, senha);

            rs = stmt.executeQuery();

            if (rs.next()) {
                check = true;
 
                idValue = rs.getInt("id");
                userName = rs.getString("name");
                titleName = rs.getString("title");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao acessar o banco: " + ex);

        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return check;
    }

    public int checkPermission(String name, String id) {
        int permission = 0;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        //User u = new User();
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT PERMISSION FROM user  WHERE name = ? and id = ?");
            stmt.setString(1, name);
            stmt.setString(2, id);

            rs = stmt.executeQuery();
            if (rs.next()) {
                permission = rs.getInt("permission");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao acessar o banco: " + ex);

        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return permission;
    }

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

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<User> readAll() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        ResultSet rs = null;

        List<User> users = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM user");
            rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User(rs.getInt("id"));
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setTitle(rs.getString("title"));
                user.setPermission(rs.getInt("permission"));
                user.setCreation(rs.getString("creation"));
                users.add(user);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao acessar o banco: " + ex);

        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return users;
    }

    public List<User> readRow(int u) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        ResultSet rs = null;

        List<User> users = new ArrayList<>();

        try {
            String sql = "select u.id, u.name, u.title, u.permission, u.creation "
                    + "from user u inner join plantation p on u.id = p.id_owner where p.id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, u);
            rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User(rs.getInt("id"));
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setTitle(rs.getString("title"));
                user.setPermission(rs.getInt("permission"));
                user.setCreation(rs.getString("creation"));
                users.add(user);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao acessar o banco: " + ex);

        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return users;
    }

        public List<User> readLike(String u) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        ResultSet rs = null;

        List<User> users = new ArrayList<>();

        try {
            String sql = "select * from user where name like ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, u+"%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User(rs.getInt("id"));
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setTitle(rs.getString("title"));
                user.setPermission(rs.getInt("permission"));
                user.setCreation(rs.getString("creation"));
                users.add(user);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao acessar o banco: " + ex);

        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return users;
    }
    
     

    public void update(User u) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE User SET title = ?, name = ?, permission = ?, creation = ? WHERE id = ?");
            stmt.setString(1, u.getTitle());
            stmt.setString(2, u.getName());
            stmt.setInt(3, u.getPermission());
            stmt.setString(4, u.getCreation());
            stmt.setInt(5, u.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
