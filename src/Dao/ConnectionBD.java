/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import utils.Utilitaire;

/**
 *
 * @author georg
 */
public class ConnectionBD {
    
    
    /**
     * La configeration de connection avec la base de donnée 
     * user : root 
     * mot de pass:
     * 
     * @return
     */
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bibliotheque?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            con = DriverManager.getConnection(url, "root", "");
            con.setAutoCommit(false);
        } catch (ClassNotFoundException | SQLException e) {
            Utilitaire.MsgError("Pas de connexion avec la base de donée");
        }
        return con;
    }
}
