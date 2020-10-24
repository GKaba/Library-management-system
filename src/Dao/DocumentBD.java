/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.Dictionnaire;
import modele.Document;
import modele.Historique;
import modele.Livre;
import modele.Magazine;
import utils.Utilitaire;

/**
 *
 * @author georg
 */
public class DocumentBD extends ConnectionBD {

    /**
     * method pour récupérer tous les Documents par ISBN pour la verification de
     * doublons.
     *
     * @return
     * @throws SQLException
     * @throws NullPointerException
     */
    public static List<Document> getDocsVerDoublon() throws SQLException, NullPointerException {
        List<Document> list = new ArrayList<>();
        Connection con = null;
        Document doc = null;
        Statement stm = null;
        ResultSet resultSet = null;
        try {
            con = getConnection();
            String sql = "Select isbn from document group by isbn";
            stm = con.createStatement();
            resultSet = stm.executeQuery(sql);

            while (resultSet.next()) {
                doc = new Document() {
                };
                doc.setISBN(resultSet.getString(1));
                list.add(doc);
            }
        } catch (SQLException | NullPointerException e) {
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
     * method pour récupérer un Documents Disponible par son ISBN dans la base
     * de données .
     *
     * @param isbn
     * @return
     * @throws SQLException
     */
    public static Document getDocByIsbn(String isbn) throws SQLException {
        Document doc = new Document() {
        };
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            String sql = "Select id_doc,isbn,titre,editeur,annee,Count(id_doc),auteur1,auteur2,auteur3,auteur4 from document where id_membre is null and isbn=?";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, isbn);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                doc.setNumeroEnrg(rs.getInt(1));
                doc.setISBN(rs.getString(2));
                doc.setTitre(rs.getString(3));
                doc.setEditeur(rs.getString(4));
                doc.setAnnee(rs.getInt(5));
                doc.setNombreCopie(rs.getInt(6));
                doc.setAuteur1(rs.getString(7));
                doc.setAuteur2(rs.getString(8));
                doc.setAuteur3(rs.getString(9));
                doc.setAuteur4(rs.getString(10));
            } else {
                return null;
            }
        } catch (SQLException e) {
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

        return doc;
    }

    /**
     * method pour récupérer un Document par Id Document.
     *
     * @param num_enrg
     * @return
     * @throws SQLException
     */
    public static Document getDocByNumEnrg(int num_enrg) throws SQLException {
        Document doc = new Document() {
        };
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            String sql = "Select id_doc,type_doc,isbn,titre,editeur,annee,Count(id_doc),auteur1,auteur2,auteur3,auteur4 from document where id_doc=?";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, num_enrg);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                doc.setNumeroEnrg(rs.getInt(1));
                doc.setType_doc(rs.getInt(2));
                doc.setISBN(rs.getString(3));
                doc.setTitre(rs.getString(4));
                doc.setEditeur(rs.getString(5));
                doc.setAnnee(rs.getInt(6));
                doc.setNombreCopie(rs.getInt(7));
                doc.setAuteur1(rs.getString(8));
                doc.setAuteur2(rs.getString(9));
                doc.setAuteur3(rs.getString(10));
                doc.setAuteur4(rs.getString(11));
            } else {
                return null;
            }
        } catch (SQLException e) {
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

        return doc;
    }

    /**
     * method pour Ajouter un Livre .
     *
     * @param livre
     * @return
     * @throws SQLException
     */
    public static int ajouterLivre(Document livre) throws SQLException {
        int st1 = 0;
        int st2 = 0;
        Connection con = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        try {
            con = getConnection();
            String sql1 = "INSERT INTO document(type_doc, isbn, titre, editeur, annee, nombre_Copie, auteur1, auteur2, auteur3, auteur4) VALUES "
                    + "(?,?,?,?,?,?,?,?,?,?)";
            String sql2 = "INSERT INTO Livre Values (?,?,?)";

            preparedStatement1 = con.prepareStatement(sql1);
            preparedStatement2 = con.prepareStatement(sql2);

            preparedStatement1.setInt(1, 0);
            preparedStatement1.setString(2, livre.getISBN());
            preparedStatement1.setString(3, livre.getTitre());
            preparedStatement1.setString(4, livre.getEditeur());
            preparedStatement1.setInt(5, livre.getAnnee());
            preparedStatement1.setInt(6, livre.getNombreCopie());
            preparedStatement1.setString(7, livre.getAuteur1());
            preparedStatement1.setString(8, livre.getAuteur2());
            preparedStatement1.setString(9, livre.getAuteur3());
            preparedStatement1.setString(10, livre.getAuteur4());

            preparedStatement2.setString(1, livre.getISBN());
            preparedStatement2.setInt(2, ((Livre) livre).getNbrpage());
            preparedStatement2.setString(3, ((Livre) livre).getType());

            //Ajouter un enregistrement pour chaque copie de document .
            st1 = preparedStatement1.executeUpdate();
            for (int i = 0; i < livre.getNombreCopie() - 1; i++) {
                st1 *= preparedStatement1.executeUpdate();
            }

            if (st1 > 0) {
                st2 = preparedStatement2.executeUpdate();
            }

            con.commit();

        } catch (SQLException e) {
            con.rollback();
        } finally {
            if (preparedStatement1 != null) {
                preparedStatement1.close();
            }
            if (preparedStatement2 != null) {
                preparedStatement2.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return st1 * st2;
    }

    /**
     * method pour récupérer tous les livre Disponible.
     *
     * @return
     * @throws java.sql.SQLException
     */
    public static List<Livre> getLivresDispo() throws SQLException {
        List<Livre> list = new ArrayList<>();
        Connection con = null;
        Livre livre = null;
        Statement stm = null;
        ResultSet resultSet = null;
        try {
            con = getConnection();
            String sql = "select document.isbn,titre,editeur,annee,count(document.isbn),livre.nbr_page,livre.type from Document,Livre where Document.isbn=Livre.isbn and document.id_membre is null group by document.isbn";
            stm = con.createStatement();
            resultSet = stm.executeQuery(sql);

            while (resultSet.next()) {
                livre = new Livre();
                livre.setISBN(resultSet.getString(1));
                livre.setTitre(resultSet.getString(2));
                livre.setEditeur(resultSet.getString(3));
                livre.setAnnee(resultSet.getInt(4));
                livre.setNombreCopie(resultSet.getInt(5));
                livre.setNbrpage(resultSet.getInt(6));
                livre.setType(resultSet.getString(7));
                list.add(livre);
            }
        } catch (SQLException e) {
            Logger.getLogger(CompteBD.class.getName()).log(Level.SEVERE, null, e);
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
     * method pour récupérer un livre par son ISBN.
     * @param isbn
     * @return
     * @throws SQLException 
     */
    public static Livre getLivreByIsbn(String isbn) throws SQLException {
        Livre livre = new Livre();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            String sql = "Select id_doc,document.isbn,titre,editeur,annee,Livre.nbr_page,Livre.type,Count(id_doc),auteur1,auteur2,auteur3,auteur4 from document,Livre where document.isbn=Livre.isbn and id_membre is null and document.isbn=?";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, isbn);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                livre.setNumeroEnrg(rs.getInt(1));
                livre.setISBN(rs.getString(2));
                livre.setTitre(rs.getString(3));
                livre.setEditeur(rs.getString(4));
                livre.setAnnee(rs.getInt(5));
                livre.setNbrpage(rs.getInt(6));
                livre.setType(rs.getString(7));
                livre.setNombreCopie(rs.getInt(8));
                livre.setAuteur1(rs.getString(9));
                livre.setAuteur2(rs.getString(10));
                livre.setAuteur3(rs.getString(11));
                livre.setAuteur4(rs.getString(12));
            } else {
                return null;
            }
        } catch (SQLException e) {
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
        return livre;
    }

    
    /**
     * method pour Modifier un livre .
     * @param livre
     * @return
     * @throws SQLException 
     */
    public static int livreModif(Livre livre) throws SQLException {
        int st1 = 0;
        int st2 = 0;
        Connection con = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        try {
            String sql1 = "UPDATE document SET titre=?,editeur=?,annee=?,auteur1=?,auteur2=?,auteur3=?,auteur4=? WHERE document.isbn=?";
            String sql2 = "UPDATE livre SET nbr_page=?,type=? WHERE isbn=?";
            con = getConnection();
            preparedStatement1 = con.prepareStatement(sql1);
            preparedStatement2 = con.prepareStatement(sql2);

            preparedStatement1.setString(1, livre.getTitre());
            preparedStatement1.setString(2, livre.getEditeur());
            preparedStatement1.setInt(3, livre.getAnnee());
            preparedStatement1.setString(4, livre.getAuteur1());
            preparedStatement1.setString(5, livre.getAuteur2());
            preparedStatement1.setString(6, livre.getAuteur3());
            preparedStatement1.setString(7, livre.getAuteur4());
            preparedStatement1.setString(8, livre.getISBN());

            preparedStatement2.setInt(1, livre.getNbrpage());
            preparedStatement2.setString(2, livre.getType());
            preparedStatement2.setString(3, livre.getISBN());

            st1 = preparedStatement1.executeUpdate();

            if (st1 > 0) {
                st2 = preparedStatement2.executeUpdate();
            }
            con.commit();

        } catch (SQLException e) {
            con.rollback();
            Utilitaire.MsgError(e.getMessage());
        } finally {
            
                if (preparedStatement1 != null) {
                    preparedStatement1.close();
                }
                if (preparedStatement2 != null) {
                    preparedStatement2.close();
                }
                if (con != null) {
                    con.close();
                }
            } 
       
        return st1 * st2;
    }

    
    /**
     * method pour supprimer un livre 
     * @param isbn
     * @return
     * @throws SQLException 
     */
    public static int livreSupprimer(String isbn) throws SQLException {
        int st1 = 0;
        int st2 = 0;
        Connection con = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        try {
            String sql1 = "DELETE from document WHERE isbn=?";
            String sql2 = "DELETE from livre WHERE isbn=?";

            con = getConnection();
            preparedStatement1 = con.prepareStatement(sql1);
            preparedStatement2 = con.prepareStatement(sql2);

            preparedStatement1.setString(1, isbn);
            preparedStatement2.setString(1, isbn);

            st2 = preparedStatement2.executeUpdate();
            if (st2 > 0) {
                st1 = preparedStatement1.executeUpdate();
            }
            con.commit();

        } catch (SQLException e) {
            con.rollback();
            Utilitaire.MsgError(e.getMessage());
        } finally {
         
                if (preparedStatement1 != null) {
                    preparedStatement1.close();
                }
                if (preparedStatement2 != null) {
                    preparedStatement2.close();
                }
                if (con != null) {
                    con.close();
                }
            } 
        return st1 * st2;
    }

    ///////////////////Magazine//////////////////////////
    
    
    /**
     * method pour récupérer tous les Magazine Disponible.
     * @return
     * @throws SQLException 
     */
    
    public static List<Magazine> getMagDispo() throws SQLException {
        List<Magazine> list = new ArrayList<>();
        Connection con = null;
        Magazine mag = null;
        Statement stm = null;
        ResultSet resultSet = null;
        try {
            con = getConnection();
            String sql = "select document.isbn,titre,editeur,annee,count(document.isbn),Magazine.periode,Magazine.date_edit from Document,Magazine where Document.isbn=Magazine.isbn and document.id_membre is null group by document.isbn";
            stm = con.createStatement();
            resultSet = stm.executeQuery(sql);

            while (resultSet.next()) {
                mag = new Magazine();
                mag.setISBN(resultSet.getString(1));
                mag.setTitre(resultSet.getString(2));
                mag.setEditeur(resultSet.getString(3));
                mag.setAnnee(resultSet.getInt(4));
                mag.setNombreCopie(resultSet.getInt(5));
                mag.setPeriode(resultSet.getString(6));
                mag.setDate_edit(resultSet.getString(7));
                list.add(mag);
            }
        } catch (SQLException e) {
            Logger.getLogger(CompteBD.class.getName()).log(Level.SEVERE, null, e);
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
     * method pour Ajouter un Magazine .
     * @param magazine
     * @return
     * @throws SQLException 
     */
    public static int ajouterMagazine(Document magazine) throws SQLException {
        int st1 = 0;
        int st2 = 0;
        Connection con = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        try {
            con = getConnection();
            String sql1 = "INSERT INTO document(type_doc, isbn, titre, editeur, annee, nombre_Copie, auteur1, auteur2, auteur3, auteur4) VALUES "
                    + "(?,?,?,?,?,?,?,?,?,?)";
            String sql2 = "INSERT INTO Magazine Values (?,?,?)";

            preparedStatement1 = con.prepareStatement(sql1);
            preparedStatement2 = con.prepareStatement(sql2);

            preparedStatement1.setInt(1, 1);
            preparedStatement1.setString(2, magazine.getISBN());
            preparedStatement1.setString(3, magazine.getTitre());
            preparedStatement1.setString(4, magazine.getEditeur());
            preparedStatement1.setInt(5, magazine.getAnnee());
            preparedStatement1.setInt(6, magazine.getNombreCopie());
            preparedStatement1.setString(7, magazine.getAuteur1());
            preparedStatement1.setString(8, magazine.getAuteur2());
            preparedStatement1.setString(9, magazine.getAuteur3());
            preparedStatement1.setString(10, magazine.getAuteur4());

            preparedStatement2.setString(1, magazine.getISBN());
            preparedStatement2.setString(2, ((Magazine) magazine).getPeriode());
            preparedStatement2.setString(3, ((Magazine) magazine).getDate_edit());

            //Ajouter un enregistrement pour chaque copie de document .
            st1 = preparedStatement1.executeUpdate();
            for (int i = 0; i < magazine.getNombreCopie() - 1; i++) {
                st1 *= preparedStatement1.executeUpdate();
            }
            if (st1 > 0) {
                st2 = preparedStatement2.executeUpdate();
            }

            con.commit();

        } catch (SQLException e) {
            con.rollback();
        } finally {
            if (preparedStatement1 != null) {
                preparedStatement1.close();
            }
            if (preparedStatement2 != null) {
                preparedStatement2.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return st1 * st2;
    }

    /**
     * method pour récupérer un  Magazine par son ISBN.
     * @param isbn
     * @return
     * @throws SQLException 
     */
    public static Magazine getMagByIsbn(String isbn) throws SQLException {
        Magazine mag = new Magazine();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            String sql = "Select id_doc,document.isbn,titre,editeur,annee,magazine.periode,magazine.date_edit,Count(id_doc),auteur1,auteur2,auteur3,auteur4 from document,magazine where document.isbn=magazine.isbn and document.isbn=?";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, isbn);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                mag.setNumeroEnrg(rs.getInt(1));
                mag.setISBN(rs.getString(2));
                mag.setTitre(rs.getString(3));
                mag.setEditeur(rs.getString(4));
                mag.setAnnee(rs.getInt(5));
                mag.setPeriode(rs.getString(6));
                mag.setDate_edit(rs.getString(7));
                mag.setNombreCopie(rs.getInt(8));
                mag.setAuteur1(rs.getString(9));
                mag.setAuteur2(rs.getString(10));
                mag.setAuteur3(rs.getString(11));
                mag.setAuteur4(rs.getString(12));
            } else {
                return null;
            }
        } catch (SQLException e) {
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
        return mag;
    }

    
    /**
     * method pour modifier un Magazine .
     * @param mag
     * @return
     * @throws SQLException 
     */
    public static int magModif(Magazine mag) throws SQLException {
        int st1 = 0;
        int st2 = 0;
        Connection con = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        try {
            String sql1 = "UPDATE document SET titre=?,editeur=?,annee=?,auteur1=?,auteur2=?,auteur3=? ,auteur4=? WHERE document.isbn=?";
            String sql2 = "UPDATE magazine SET periode=?,date_edit=? WHERE isbn=?";
            con = getConnection();
            preparedStatement1 = con.prepareStatement(sql1);
            preparedStatement2 = con.prepareStatement(sql2);

            preparedStatement1.setString(1, mag.getTitre());
            preparedStatement1.setString(2, mag.getEditeur());
            preparedStatement1.setInt(3, mag.getAnnee());
            preparedStatement1.setString(4, mag.getAuteur1());
            preparedStatement1.setString(5, mag.getAuteur2());
            preparedStatement1.setString(6, mag.getAuteur3());
            preparedStatement1.setString(7, mag.getAuteur4());
            preparedStatement1.setString(8, mag.getISBN());

            preparedStatement2.setString(1, mag.getPeriode());
            preparedStatement2.setString(2, mag.getDate_edit());
            preparedStatement2.setString(3, mag.getISBN());

            st1 = preparedStatement1.executeUpdate();

            if (st1 > 0) {
                st2 = preparedStatement2.executeUpdate();
            }
            con.commit();

        } catch (SQLException e) {
            con.rollback();
            Utilitaire.MsgError(e.getMessage());
        } finally {
           
                if (preparedStatement1 != null) {
                    preparedStatement1.close();
                }
                if (preparedStatement2 != null) {
                    preparedStatement2.close();
                }
                if (con != null) {
                    con.close();
                }
            } 
   
        return st1 * st2;
    }

    /**
     * method pour supprimer un  Magazine .
     * @param isbn
     * @return
     * @throws SQLException 
     */
    public static int magSupprimer(String isbn) throws SQLException {
        int st1 = 0;
        int st2 = 0;
        Connection con = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        try {
            String sql1 = "DELETE from document WHERE isbn=?";
            String sql2 = "DELETE from magazine WHERE isbn=?";

            con = getConnection();
            preparedStatement1 = con.prepareStatement(sql1);
            preparedStatement2 = con.prepareStatement(sql2);

            preparedStatement1.setString(1, isbn);
            preparedStatement2.setString(1, isbn);

            st2 = preparedStatement2.executeUpdate();
            if (st2 > 0) {
                st1 = preparedStatement1.executeUpdate();
            }
            con.commit();

        } catch (SQLException e) {
            con.rollback();
            Utilitaire.MsgError(e.getMessage());
        } finally {
            
                if (preparedStatement1 != null) {
                    preparedStatement1.close();
                }
                if (preparedStatement2 != null) {
                    preparedStatement2.close();
                }
                if (con != null) {
                    con.close();
                }
            } 
        return st1 * st2;
    }

    ///////////////////Dictionnaire//////////////////////////
    
    /**
     * method pour récupérer tous les Dictionnaire Disponible.
     * @return 
     * @throws java.sql.SQLException 
     */
    public static List<Dictionnaire> getDicDispo() throws SQLException {
        List<Dictionnaire> list = new ArrayList<>();
        Connection con = null;
        Dictionnaire dic = null;
        Statement stm = null;
        ResultSet resultSet = null;
        try {
            con = getConnection();
            String sql = "select document.isbn,titre,editeur,annee,count(document.isbn),Dictionnaire.langue from Document,Dictionnaire where Document.isbn=Dictionnaire.isbn and document.id_membre is null group by document.isbn";
            stm = con.createStatement();
            resultSet = stm.executeQuery(sql);

            while (resultSet.next()) {
                dic = new Dictionnaire();
                dic.setISBN(resultSet.getString(1));
                dic.setTitre(resultSet.getString(2));
                dic.setEditeur(resultSet.getString(3));
                dic.setAnnee(resultSet.getInt(4));
                dic.setNombreCopie(resultSet.getInt(5));
                dic.setLangue(resultSet.getString(6));

                list.add(dic);
            }
        } catch (SQLException e) {
            Logger.getLogger(CompteBD.class.getName()).log(Level.SEVERE, null, e);
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
     * method pour Ajouter un Dictionnaire .
     * @param dictionnaire
     * @return
     * @throws SQLException 
     */
    public static int ajouterDictionnaire(Document dictionnaire) throws SQLException {
        int st1 = 0;
        int st2 = 0;
        Connection con = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;

        try {
            con = getConnection();
            String sql1 = "INSERT INTO document(type_doc, isbn, titre, editeur, annee, nombre_Copie, auteur1, auteur2, auteur3, auteur4) VALUES "
                    + "(?,?,?,?,?,?,?,?,?,?)";
            String sql2 = "INSERT INTO dictionnaire Values (?,?)";

            preparedStatement1 = con.prepareStatement(sql1);
            preparedStatement2 = con.prepareStatement(sql2);

            preparedStatement1.setInt(1, 2);
            preparedStatement1.setString(2, dictionnaire.getISBN());
            preparedStatement1.setString(3, dictionnaire.getTitre());
            preparedStatement1.setString(4, dictionnaire.getEditeur());
            preparedStatement1.setInt(5, dictionnaire.getAnnee());
            preparedStatement1.setInt(6, dictionnaire.getNombreCopie());
            preparedStatement1.setString(7, dictionnaire.getAuteur1());
            preparedStatement1.setString(8, dictionnaire.getAuteur2());
            preparedStatement1.setString(9, dictionnaire.getAuteur3());
            preparedStatement1.setString(10, dictionnaire.getAuteur4());

            preparedStatement2.setString(1, dictionnaire.getISBN());
            preparedStatement2.setString(2, ((Dictionnaire) dictionnaire).getLangue());

            //Ajouter un enregistrement pour chaque copie de document .
            st1 = preparedStatement1.executeUpdate();
            for (int i = 0; i < dictionnaire.getNombreCopie() - 1; i++) {
                st1 *= preparedStatement1.executeUpdate();
            }

            st2 = preparedStatement2.executeUpdate();

            con.commit();

        } catch (SQLException e) {
            con.rollback();
        } finally {
            if (preparedStatement1 != null) {
                preparedStatement1.close();
            }
            if (preparedStatement2 != null) {
                preparedStatement2.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return st1 * st2;
    }

    /**
     * method pour récupérer un Dictionnaire par son ISBN.
     * @param isbn
     * @return
     * @throws SQLException 
     */
    public static Dictionnaire getDicByIsbn(String isbn) throws SQLException {
        Dictionnaire dic = null;
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            String sql = "Select id_doc,document.isbn,titre,editeur,annee,dictionnaire.langue,Count(id_doc),auteur1,auteur2,auteur3,auteur4 from document,dictionnaire where document.isbn=dictionnaire.isbn and document.isbn=?";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, isbn);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                dic = new Dictionnaire();
                dic.setNumeroEnrg(rs.getInt(1));
                dic.setISBN(rs.getString(2));
                dic.setTitre(rs.getString(3));
                dic.setEditeur(rs.getString(4));
                dic.setAnnee(rs.getInt(5));
                dic.setLangue(rs.getString(6));
                dic.setNombreCopie(rs.getInt(7));
                dic.setAuteur1(rs.getString(8));
                dic.setAuteur2(rs.getString(9));
                dic.setAuteur3(rs.getString(10));
                dic.setAuteur4(rs.getString(11));

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
        return dic;
    }

    /**
     * method pour modifier un  Dictionnaire .
     * @param dic
     * @return
     * @throws SQLException 
     */
    public static int dicModif(Dictionnaire dic) throws SQLException {
        int st1 = 0;
        int st2 = 0;
        Connection con = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        try {
            String sql1 = "UPDATE document SET titre=?,editeur=?,annee=?,auteur1=?,auteur2=?,auteur3=?,auteur4=? WHERE document.isbn=?";
            String sql2 = "UPDATE dictionnaire SET langue=? WHERE isbn=?";
            con = getConnection();
            preparedStatement1 = con.prepareStatement(sql1);
            preparedStatement2 = con.prepareStatement(sql2);

            preparedStatement1.setString(1, dic.getTitre());
            preparedStatement1.setString(2, dic.getEditeur());
            preparedStatement1.setInt(3, dic.getAnnee());
            preparedStatement1.setString(4, dic.getAuteur1());
            preparedStatement1.setString(5, dic.getAuteur2());
            preparedStatement1.setString(6, dic.getAuteur3());
            preparedStatement1.setString(7, dic.getAuteur4());
            preparedStatement1.setString(8, dic.getISBN());

            preparedStatement2.setString(1, dic.getLangue());
            preparedStatement2.setString(2, dic.getISBN());

            st1 = preparedStatement1.executeUpdate();

            if (st1 > 0) {
                st2 = preparedStatement2.executeUpdate();
            }
            con.commit();

        } catch (SQLException e) {
            con.rollback();
            Utilitaire.MsgError(e.getMessage());
        } finally {
            
                if (preparedStatement1 != null) {
                    preparedStatement1.close();
                }
                if (preparedStatement2 != null) {
                    preparedStatement2.close();
                }
                if (con != null) {
                    con.close();
                }
            } 
        return st1 * st2;
    }

    /**
     * method pour supprimer un  Dictionnaire .
     * @param isbn
     * @return
     * @throws SQLException 
     */
    public static int dicSupprimer(String isbn) throws SQLException {
        int st1 = 0;
        int st2 = 0;
        Connection con = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        try {
            String sql1 = "DELETE from document WHERE isbn=?";
            String sql2 = "DELETE from dictionnaire WHERE isbn=?";

            con = getConnection();
            preparedStatement1 = con.prepareStatement(sql1);
            preparedStatement2 = con.prepareStatement(sql2);

            preparedStatement1.setString(1, isbn);
            preparedStatement2.setString(1, isbn);

            st2 = preparedStatement2.executeUpdate();
            if (st2 > 0) {
                st1 = preparedStatement1.executeUpdate();
            }
            con.commit();

        } catch (SQLException e) {
            con.rollback();
            Utilitaire.MsgError(e.getMessage());
        } finally {
  
                if (preparedStatement1 != null) {
                    preparedStatement1.close();
                }
                if (preparedStatement2 != null) {
                    preparedStatement2.close();
                }
                if (con != null) {
                    con.close();
                }
            } 
      
        return st1 * st2;
    }

    /////////////////////////emprunter & retour //////////////////////////////////////
    
    /**
     * method pour Emprunter un document .
     * @param doc
     * @return
     * @throws SQLException 
     */
    public static int docEmprunter(Document doc, String id_membre) throws SQLException {
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        Connection con = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        PreparedStatement preparedStatement3 = null;
        int st1 = 0, st2 = 0, st3 = 0;
        try {
            String sql1 = "UPDATE document set id_membre=? where id_doc=?";
            String sql2 = "UPDATE membre set nbr_eprunter = nbr_eprunter + 1 where id_membre=?";
            String sql3 = "INSERT INTO  historique (num_enrg,membr_id,date_emprunt) values(?,?,?)";
            con = DocumentBD.getConnection();
            preparedStatement1 = con.prepareStatement(sql1);
            preparedStatement1.setString(1, id_membre);
            preparedStatement1.setInt(2, doc.getNumeroEnrg());

            st1 = preparedStatement1.executeUpdate();

            preparedStatement2 = con.prepareStatement(sql2);
            preparedStatement2.setString(1, id_membre);

            st2 = preparedStatement2.executeUpdate();

            preparedStatement3 = con.prepareStatement(sql3);
            preparedStatement3.setInt(1, doc.getNumeroEnrg());
            preparedStatement3.setString(2, id_membre);
            preparedStatement3.setString(3, format.format(date));
            st3 = preparedStatement3.executeUpdate();

            con.commit();
        } catch (SQLException e) {
            con.rollback();
            Utilitaire.MsgError(e.getMessage());
        } finally {
            if (preparedStatement2 != null) {
                preparedStatement2.close();
            }
            if (preparedStatement1 != null) {
                preparedStatement1.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return st1 * st2;
    }

    /**
     * method pour retourner un document.
     * @param num_enrg
     * @param id_membre
     * @return
     * @throws SQLException 
     */
    public static int docRetour(int num_enrg, String id_membre) throws SQLException {
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        Connection con = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        PreparedStatement preparedStatement3 = null;
        int st1 = 0, st2 = 0, st3 = 0;
        try {
            String sql1 = "UPDATE document set id_membre=null where id_doc=?";
            String sql2 = "UPDATE membre set nbr_eprunter = nbr_eprunter -1 where id_membre=?";
            String sql3 = "UPDATE historique set date_retour = ?  where num_enrg=?";
            con = DocumentBD.getConnection();

            preparedStatement1 = con.prepareStatement(sql1);
            preparedStatement1.setInt(1, num_enrg);

            st1 = preparedStatement1.executeUpdate();

            preparedStatement2 = con.prepareStatement(sql2);
            preparedStatement2.setString(1, id_membre);

            st2 = preparedStatement2.executeUpdate();

            preparedStatement3 = con.prepareStatement(sql3);
            preparedStatement3.setString(1, format.format(date));
            preparedStatement3.setInt(2, num_enrg);

            st3 = preparedStatement3.executeUpdate();

            con.commit();
        } catch (SQLException e) {
            con.rollback();
        } finally {
            if (preparedStatement3 != null) {
                preparedStatement3.close();
            }
            if (preparedStatement2 != null) {
                preparedStatement2.close();
            }
            if (preparedStatement1 != null) {
                preparedStatement1.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return st1 * st2 * st3;
    }

    ////////////////////////Histriue//////////////////////////////////
    /**
     * method pour récupérer tous les Historiques la la BD.
     * @return
     * @throws SQLException 
     */
    public static List<Historique> getHistorique() throws SQLException {
        List<Historique> list = new ArrayList<>();
        Connection con = null;
        Historique hist = null;
        Statement stm = null;
        ResultSet resultSet = null;
        try {
            con = getConnection();
            String sql = "Select * from Historique";
            stm = con.createStatement();
            resultSet = stm.executeQuery(sql);

            while (resultSet.next()) {
                hist = new Historique();
                hist.setId_historique(resultSet.getInt(1));
                hist.setDoc(getDocByNumEnrg(resultSet.getInt(2)));
                hist.setNum_enrg(getDocByNumEnrg(resultSet.getInt(2)).getNumeroEnrg());
                hist.setMembre(MembreBD.getMembreByIdentity(resultSet.getString(3)));
                hist.setDateE(resultSet.getString(4));
                hist.setDateR(resultSet.getString(5));

                list.add(hist);
            }
        } catch (SQLException e) {
            Logger.getLogger(CompteBD.class.getName()).log(Level.SEVERE, null, e);
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

}
