/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import static Dao.CompteBD.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modele.Membre;
import utils.Utilitaire;

/**
 *
 * @author georg
 */
public class MembreBD extends ConnectionBD {
    
/**
 * Methode pour Ajouter un membre.
 * @param membre
 * @return
 * @throws SQLException 
 */
    public static int insertMembre(Membre membre) throws SQLException {
        int st = 0;
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con =getConnection();
            String sql = "INSERT INTO membre(id_membre, nom, prenom, adresse, tel) VALUES (?,?,?,?,?)";
            preparedStatement = (PreparedStatement) con.prepareStatement(sql);

            preparedStatement.setString(1, membre.getIdentity());
            preparedStatement.setString(2, membre.getNom());
            preparedStatement.setString(3, membre.getPrenom());
            preparedStatement.setString(4, membre.getAdresse());
            preparedStatement.setString(5, membre.getTel());

            st = preparedStatement.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            Utilitaire.MsgError(e.getMessage());
        } finally {
           
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (con != null) {
                    con.close();
                }
            } 

        return st;
    }

    /**
     * method pour récupérer tous les Membres dans la base de données.
     * @return
     * @throws SQLException 
     */
    public static List<Membre> getAllMembres() throws SQLException {
        List<Membre> list = new ArrayList<>();
        Connection con = null;
        Membre membre =null;
        Statement stm = null;
        ResultSet resultSet = null;
        try {
            con = getConnection();
            String sql = "select * from Membre";
            stm = con.createStatement();
            resultSet = stm.executeQuery(sql);

            while (resultSet.next()) {
                membre=new Membre();
                membre.setCode_membre(resultSet.getInt(1));
                membre.setIdentity(resultSet.getString(2));
                membre.setNom(resultSet.getString(3));
                membre.setPrenom(resultSet.getString(4));
                membre.setAdresse(resultSet.getString(5));
                membre.setTel(resultSet.getString(6));
                membre.setNbr_eprunter(resultSet.getInt(7));
                list.add(membre);
            }
        } catch (SQLException e) {
            Utilitaire.MsgError(e.getMessage());
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    /**
     * method pour récupérer tous les Membres par son code de bibliotheque.
     * @param code
     * @return
     * @throws SQLException 
     */
    public static Membre getMembreByCode(int code) throws SQLException {
        Connection con = null;
        Membre membre = new Membre();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            String sql = "select * from Membre where code=?";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, code);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                membre.setCode_membre(rs.getInt(1));
                membre.setIdentity(rs.getString(2));
                membre.setNom(rs.getString(3));
                membre.setPrenom(rs.getString(4));
                membre.setAdresse(rs.getString(5));
                membre.setTel(rs.getString(6));
                membre.setNbr_eprunter(rs.getInt(7));
            }
        } catch (SQLException e) {
            Utilitaire.MsgError(e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return membre;
    }

    /**
     * method pour récupérer tous les Membres par son id ou id etudiant.
     * @param id
     * @return
     * @throws SQLException 
     */
     public static Membre getMembreByIdentity(String id) throws SQLException {
        Connection con = null;
        Membre membre = new Membre();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            String sql = "select * from Membre where id_membre=?";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, id);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                membre.setCode_membre(rs.getInt(1));
                membre.setIdentity(rs.getString(2));
                membre.setNom(rs.getString(3));
                membre.setPrenom(rs.getString(4));
                membre.setAdresse(rs.getString(5));
                membre.setTel(rs.getString(6));
                membre.setNbr_eprunter(rs.getInt(7));
            }
        } catch (SQLException e) {
            Utilitaire.MsgError(e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return membre;
    }
     
     /**
      * method pour modifier un Membre .
      * @param membre
      * @return
      * @throws SQLException 
      */
    public static int membreModif(Membre membre) throws SQLException {
        int st = 0;
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "UPDATE membre SET id_membre=?,nom=?,prenom=?,tel=?,adresse=? WHERE code=?";
            con = getConnection();
            preparedStatement = (PreparedStatement) con.prepareStatement(sql);

            preparedStatement.setString(1, membre.getIdentity());
            preparedStatement.setString(2, membre.getNom());
            preparedStatement.setString(3, membre.getPrenom());
            preparedStatement.setString(4, membre.getTel());
            preparedStatement.setString(5, membre.getAdresse());
            preparedStatement.setInt(6, membre.getCode_membre());

            st = preparedStatement.executeUpdate();
            con.commit();

        } catch (SQLException e) {
            con.rollback();
            Utilitaire.MsgError(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();

                if (con != null) {

                    con.close();
                }
            }
        }
        return st;
    }
   
    /**
     * method pour supprimer un Membre .
     * @param code
     * @return
     * @throws SQLException 
     */
     public static int membreSupprimer(int code) throws SQLException {
        int st = 0;
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "DELETE from membre WHERE code=?";
            con = getConnection();
            preparedStatement = con.prepareStatement(sql);

            preparedStatement.setInt(1, code);

            st = preparedStatement.executeUpdate();
            con.commit();

        } catch (SQLException e) {
            con.rollback();
            Utilitaire.MsgError(e.getMessage());
        } finally {
            if (preparedStatement != null) 
                    preparedStatement.close();
            if (con != null) 
                    con.close();
        }
        return st;
    }
}
