
package Dao;

/**
 *
 * @author georg
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.Compte;
import utils.Utilitaire;

public class CompteBD extends ConnectionBD {

   

    /**
     * method pour récupérer tous les Comptes dans la base de données.
     *
     * @return List<Compte>
     * @throws java.sql.SQLException
     */
    public static List<Compte> getComptes() throws SQLException,NullPointerException {
        List<Compte> list = new ArrayList<>();
        Connection con = null;
        Compte compte = null;
        Statement stm = null;
        ResultSet resultSet = null;
        try {
            con = getConnection();
            String sql = "select * from Compte";
            stm = con.createStatement();
            resultSet = stm.executeQuery(sql);

            while (resultSet.next()) {
                compte = new Compte();
                compte.setId(resultSet.getInt(1));
                compte.setSurnom(resultSet.getString(2));
                compte.setMot_pass(resultSet.getString(3));
                compte.setNom(resultSet.getString(4));
                compte.setPrenom(resultSet.getString(5));
                compte.setTelephone(resultSet.getString(6));
                compte.setAdresse(resultSet.getString(7));
                compte.setTypecompte(resultSet.getInt(8));
                compte.setEtat(resultSet.getInt(9));
                list.add(compte);
            }
        } catch (SQLException |NullPointerException e) {
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
     * Methode pour inserer un utilisateurs dans la BD.
     *
     * @param cmpt
     * @return int
     * @throws java.sql.SQLException
     */
    public static int insertCompte(Compte cmpt) throws SQLException {
        int st = 0;
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = getConnection();
            String sql = "INSERT INTO Compte (surnom,mot_pass,nom,prenom,telephone,adresse,typecompte,etat) Values (?,?,?,?,?,?,?,?)";
            preparedStatement = (PreparedStatement) con.prepareStatement(sql);

            preparedStatement.setString(1, cmpt.getSurnom());
            preparedStatement.setString(2, cmpt.getMot_pass());
            preparedStatement.setString(3, cmpt.getNom());
            preparedStatement.setString(4, cmpt.getPrenom());
            preparedStatement.setString(5, cmpt.getTelephone());
            preparedStatement.setString(6, cmpt.getAdresse());
            preparedStatement.setInt(7, cmpt.getTypecompte());
            preparedStatement.setInt(8, 0);

            st = preparedStatement.executeUpdate();
              con.commit();
        } catch (SQLException e) {
                con.rollback();
                Utilitaire.MsgError(e.getMessage());
            } 
              finally {
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
     * method pour récupérer les Comptes par un id .
     * @param id
     * @return 
     * @throws java.sql.SQLException 
     */
    public static Compte getCompteById(int id) throws SQLException {
        Compte cmpt = new Compte();
        Connection con = null;
        Statement stm = null;
        ResultSet resultSet = null;
        try {
            con = getConnection();
            String sql = "select * from compte WHERE id=" + id;
            stm = con.createStatement();
            resultSet = stm.executeQuery(sql);
            if (resultSet.next()) {
                cmpt.setId(resultSet.getInt(1));
                cmpt.setSurnom(resultSet.getString(2));
                cmpt.setMot_pass(resultSet.getString(3));
                cmpt.setNom(resultSet.getString(4));
                cmpt.setPrenom(resultSet.getString(5));
                cmpt.setTelephone(resultSet.getString(6));
                cmpt.setAdresse(resultSet.getString(7));
                cmpt.setTypecompte(resultSet.getInt(8));
            }

        } catch (SQLException e) {
           
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
        return cmpt;
    }
    
    


    /**
     * modifier un Compte.
     *
     * @param id
     * @return
     * @throws java.sql.SQLException
     */
    public static int compteSupprimer(int id) throws SQLException {
        int st = 0;
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "DELETE from compte WHERE id=?";
            con = getConnection();
            preparedStatement = (PreparedStatement) con.prepareStatement(sql);

            preparedStatement.setInt(1, id);

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
     * modifier un Compte.
     *
     * @param cmpt
     * @return
     * @throws java.sql.SQLException
     */
    public static int compteModif(Compte cmpt) throws SQLException {
        int st = 0;
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "UPDATE compte SET surnom=?,mot_pass=?,nom=?,prenom=?,telephone=?,adresse=? WHERE id=?";
            con = getConnection();
            preparedStatement = (PreparedStatement) con.prepareStatement(sql);

            preparedStatement.setString(1, cmpt.getSurnom());
            preparedStatement.setString(2, cmpt.getMot_pass());
            preparedStatement.setString(3, cmpt.getNom());
            preparedStatement.setString(4, cmpt.getPrenom());
            preparedStatement.setString(5, cmpt.getTelephone());
            preparedStatement.setString(6, cmpt.getAdresse());
            preparedStatement.setInt(7, cmpt.getId());

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
     * methode por l'employee pour modifier ses informations.
     * @param cmp
     * @return
     * @throws SQLException 
     */
    public static int modifierUtilisateur(Compte cmp) throws SQLException {
        int st = 0;
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "UPDATE compte SET surnom=?,telephone=?,adresse=? WHERE id=?";
            con = CompteBD.getConnection();
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, cmp.getSurnom());
            preparedStatement.setString(2, cmp.getTelephone());
            preparedStatement.setString(3, cmp.getAdresse());
            preparedStatement.setInt(4, cmp.getId());
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
     * methode pour l'employee pour modifier son mot de pass .
     * @param cmp
     * @return
     * @throws SQLException 
     */
    public static int motpassModif(Compte cmp) throws SQLException {
        int st = 0;
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "UPDATE compte SET mot_pass=?  WHERE id=?";
            con = CompteBD.getConnection();
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, cmp.getMot_pass());
            preparedStatement.setInt(2, cmp.getId());
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
}
