/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AmorimHe
 */
public class ConnectionFactory {

    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String URL = "jdbc:mysql://sql10.freesqldatabase.com/sql10308481";
    private static String USER = "sql10308481";
    private static String PASS = "6uQwmH3Jaz";

    public static Connection getConnection() {
        try {

            try {
                Class.forName(DRIVER);

                return DriverManager.getConnection(URL, USER, PASS);
            } catch (SQLException ex) {
                throw new RuntimeException("Erro na Conexão: ", ex);

            }

        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Erro na Conexão: ", ex);
        }
        
        
    }
    
    public static void closeConnection (Connection con){
            
                try {
                    if(con !=null){
                    con.close();}
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
                }
           
        }
    
       public static void closeConnection (Connection con, PreparedStatement stmt){
           closeConnection(con);
           
             try {
            
                 if(stmt != null){
                     stmt.close();
                 }
                 
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
       
              public static void closeConnection (Connection con, PreparedStatement stmt, ResultSet rs){
           closeConnection(con, stmt);
           
             try {
            if(rs != null){
                rs.close();
            }
             
                 
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
}
