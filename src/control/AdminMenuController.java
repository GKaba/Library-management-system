/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

/**
 *
 * @author georg
 */
import Dao.CompteBD;
import Dao.DocumentBD;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modele.Compte;
import modele.Dictionnaire;
import modele.Livre;
import modele.Magazine;
import modele.Document;
import utils.ExisteException;
import utils.InvalidDAteException;
import utils.InvalidIsbnException;
import utils.InvalidMPassException;
import utils.InvalidStringException;
import utils.InvalidTelException;
import utils.InvalidYearException;
import utils.Utilitaire;

/**
 * FXML Controller class
 *
 * @author georg
 */
public class AdminMenuController extends Utilitaire implements Initializable {

    @FXML
    private Label lbluser;
    @FXML
    private Pane modifutilisateur;
    @FXML
    private JFXTextField txt_mid_u;
    @FXML
    private JFXTextField txt_mnom_u;
    @FXML
    private JFXTextField txt_mprenom_u;
    @FXML
    private JFXTextField txt_madresse_u;
    @FXML
    private JFXTextField txt_mtel_u;
    @FXML
    private JFXButton btn_mmodifier_u;
    @FXML
    private JFXTextField txt_mmotpass_u;
    @FXML
    private JFXTextField txt_msurnom_u;
    @FXML
    private JFXButton btn_msupprimer_u;
    @FXML
    private JFXButton btn_mchercher_u;
    @FXML
    private TextField txt_mchercher_u;
    @FXML
    private Pane ajouterutilisateur;
    @FXML
    private JFXTextField txt_asurnom;
    @FXML
    private JFXPasswordField txt_amotpass;
    @FXML
    private JFXTextField txt_anom;
    @FXML
    private JFXTextField txt_aprenom;
    @FXML
    private JFXTextField txt_aadresse;
    @FXML
    private JFXButton btn_uajouter;

    @FXML
    private JFXTextField txt_atel;
    @FXML
    private Pane utilisateur;
    @FXML
    private JFXButton btn_dchercher_u;
    @FXML
    private TextField txt_dchercher_u;
    ///////////Les Tableux///////////
    @FXML
    private TableView<Compte> tableuUtilisateur;
    @FXML
    private TableColumn<Compte, String> surnom, nome, prenome, adressee, tele;
    @FXML
    private TableColumn<Compte, Integer> userid;

    @FXML
    private TableView<Livre> tableulivre;
    @FXML
    private TableColumn<Document, String> isbn, titre, editeur, type;
    @FXML
    private TableColumn<Document, Integer> anne, nbrdispo, nbrpage;

    @FXML
    private TableView<Magazine> tableumagazine;
    @FXML
    private TableColumn<Document, String> isbnm, titrem, editeurm, periode, dateedition;
    @FXML
    private TableColumn<Document, Integer> annem, nbrdispom;

    @FXML
    private TableView<Dictionnaire> tableudictionnaire;
    @FXML
    private TableColumn<Document, String> isbnd, titred, editeurd, langue;
    @FXML
    private TableColumn<Document, Integer> anned, nbrdispod;

    ////////////////////////////////////////
    @FXML
    private Pane documents;
    @FXML
    private JFXButton btn_dchercher_l;
    @FXML
    private TextField txt_cherche_l;

    @FXML
    private JFXButton btn_dchercher_m;
    @FXML
    private TextField txt_cherche_m;

    @FXML
    private JFXButton btn_dchercher_d;
    @FXML
    private TextField txt_cherche_d;
    @FXML
    private MenuButton menuSelectLivreBy;
    @FXML
    private Pane ajouterdocument;
    @FXML
    private JFXTextField txt_aisbn_l;
    @FXML
    private JFXTextField txt_atitre_l;
    @FXML
    private JFXTextField txt_aediteur_l;
    @FXML
    private JFXTextField txt_aannee_l;
    @FXML
    private JFXTextField txt_anbr_page;
    @FXML
    private JFXButton btn_ajouter_l;
    @FXML
    private JFXTextField txt_atype_l;
    @FXML
    private JFXTextField txt_aauteur1_l;
    @FXML
    private JFXTextField txt_aauteur2_l;
    @FXML
    private JFXTextField txt_aateur3_l;
    @FXML
    private JFXTextField txt_anbrcopie_l;
    @FXML
    private JFXTextField txt_aauteur4_l;
    @FXML
    private JFXTextField txt_aisbn_m;
    @FXML
    private JFXTextField txt_atitre_m;
    @FXML
    private JFXTextField txt_aediteur_m;
    @FXML
    private JFXTextField txt_aannee_m;
    @FXML
    private JFXTextField txt_aperiode_m;
    @FXML
    private DatePicker txt_aedition_m;
    @FXML
    private JFXButton btn_ajouter_m;
    @FXML
    private JFXTextField txt_aauteur1_m;
    @FXML
    private JFXTextField txt_aauteur2_m;
    @FXML
    private JFXTextField txt_aauteur3_m;
    @FXML
    private JFXTextField txt_aauteur4_m;
    @FXML
    private JFXTextField txt_anbrcopie_m;
    @FXML
    private JFXTextField txt_aisbn_d;
    @FXML
    private JFXTextField txt_aauteur4_d;
    @FXML
    private JFXTextField txt_atitre_d;
    @FXML
    private JFXTextField txt_aediteur_d;
    @FXML
    private JFXTextField txt_aannee_d;
    @FXML
    private JFXTextField txt_alangue_d;
    @FXML
    private JFXButton btn_ajouter_d;
    @FXML
    private JFXTextField txt_aauteur1_d;
    @FXML
    private JFXTextField txt_aauteur2_d;
    @FXML
    private JFXTextField txt_aauteur3_d;
    @FXML
    private JFXTextField txt_anbrcopie_d;
    @FXML
    private Pane modifdocument;
    @FXML
    private JFXTextField txt_misbn_l;
    @FXML
    private JFXTextField txt_mtitre_l;
    @FXML
    private JFXTextField txt_mediteur_l;
    @FXML
    private JFXTextField txt_mannee_l;
    @FXML
    private JFXTextField txt_mnbr_page;
    @FXML
    private JFXButton btn_modifier_l;
    @FXML
    private JFXTextField txt_mtype_l;
    @FXML
    private JFXTextField txt_mauteur1_l;
    @FXML
    private JFXTextField txt_mauteur2_l;
    @FXML
    private JFXTextField txt_mateur3_l;
    @FXML
    private JFXTextField txt_mauteur4_l;
    @FXML
    private JFXTextField txt_mnbrcopie_l;
    @FXML
    private JFXButton btn_supprimer_l;
    @FXML
    private JFXButton btn_chercher_l;
    @FXML
    private TextField txt_mcherche_l;
    @FXML
    private JFXTextField txt_misbn_m;
    @FXML
    private JFXTextField txt_mtitre_m;
    @FXML
    private JFXTextField txt_mediteur_m;
    @FXML
    private JFXTextField txt_mannee_m;
    @FXML
    private JFXTextField txt_mperiode_m;
    @FXML
    private JFXTextField txt_medition_m;
    @FXML
    private JFXButton btn_modifier_m;
    @FXML
    private JFXTextField txt_mauteur1_m;
    @FXML
    private JFXTextField txt_mauteur2_m;
    @FXML
    private JFXTextField txt_mateur3_m;
    @FXML
    private JFXTextField txt_mauteur4_m;
    @FXML
    private JFXButton btn_supprimer_m;
    @FXML
    private JFXTextField txt_mnbrcopie_m;
    @FXML
    private JFXButton btn_chercher_m;
    @FXML
    private TextField txt_mcherche_m;
    @FXML
    private JFXTextField txt_misbn_d;
    @FXML
    private JFXTextField txt_mtitre_d;
    @FXML
    private JFXTextField txt_mediteur_d;
    @FXML
    private JFXTextField txt_mannee_d;
    @FXML
    private JFXTextField txt_mlangue_d;
    @FXML
    private JFXButton btn_modifier_d;
    @FXML
    private JFXTextField txt_mauteur1_d;
    @FXML
    private JFXTextField txt_mauteur2_d;
    @FXML
    private JFXTextField txt_mateur3_d;
    @FXML
    private JFXTextField txt_mauteur4_d;
    @FXML
    private JFXButton btn_supprimer_d;
    @FXML
    private JFXTextField txt_mnbrcopie_d;
    @FXML
    private JFXButton btn_chercher_d;
    @FXML
    private TextField txt_mcherche_d;
    @FXML
    private AnchorPane main_admin;
    @FXML
    private MenuButton menuSelectUserBy;
    @FXML
    private MenuButton menuSelectMagBy;
    @FXML
    private MenuButton menuSelectDicBy;
    @FXML
    private JFXTabPane docTabPane;
    @FXML
    private Tab docTabLivre;
    @FXML
    private ComboBox<String> combo_TypeCompte;
    @FXML
    private ComboBox<String> combo_TypeLivre;
    @FXML
    private JFXButton btn_mclear_u;
    
    
    /**
     * Initializes the controller class.
     */
    
    
    int by;
    //objets pour la modification (l'anicien obj avant la modifier)
    private Compte userModifier = null;
    private Livre livreModifier = null;
    private Magazine magModifier = null;
    private Dictionnaire dicModifier = null;

    
    List<Compte> listo=null;
    List<Document> listDoc=null;
    
    //compt de user connectée.
    private Compte cmptConnecte;

    //Observables Lists pour remplir les tables .
    public ObservableList<Compte> dataUtilisateur = FXCollections.observableArrayList();
    public ObservableList<Livre> dataLivre = FXCollections.observableArrayList();
    public ObservableList<Magazine> dataMagazine = FXCollections.observableArrayList();
    public ObservableList<Dictionnaire> dataDictionnaire = FXCollections.observableArrayList();
    

    
     //methode pour recuperer l'information de user connecte .
    public void CompteConnecte(Compte c) {
        this.cmptConnecte = c;
        lbluser.setText(cmptConnecte.getSurnom());
    }

    //initializer la fenetre avec les options de comboBox.
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        AnimerFenetre(main_admin);
        combo_TypeCompte.getItems().add("Admin");
        combo_TypeCompte.getItems().add("Employee");
        
        combo_TypeLivre.getItems().add("Lyrique");
        combo_TypeLivre.getItems().add("Fantastique");
        combo_TypeLivre.getItems().add("Épique");
        combo_TypeLivre.getItems().add("Roman d'amour");
        combo_TypeLivre.getItems().add("Historique");
        combo_TypeLivre.getItems().add("Dramatique");
        combo_TypeLivre.getItems().add("Biographies");
        combo_TypeLivre.getItems().add("Sacrés");
        combo_TypeLivre.getItems().add("Bande dessinée");
 
                        
    }

    
    
    /////////////////////////Menu de Navigation ///////////////////
    @FXML
    private void document(ActionEvent event) {
        livreInitial(true);
        tableLivre();
        menuSelectLivreBy.setText("Chercher par");
        by = 0;
        documents.toFront();
        docTabPane.getSelectionModel().select(docTabLivre);

    }

    @FXML
    private void deconnecter(ActionEvent event) {
        try {
            loadAuth(event);
        } catch (IOException ex) {
            MsgError("Désolé essayez encore une fois");
        }
    }

    @FXML
    private void ajouterdocument(ActionEvent event) {
        ajouterdocument.toFront();
        clearAjouterLivre();
        clearAjouterMag();
        clearAjouterDic();
    }

    @FXML
    private void modifdocument(ActionEvent event) {
        modifdocument.toFront();
    }

    @FXML
    private void ajouterutilisateur(ActionEvent event) {
        clearAjouterUtitlisateur();
        ajouterutilisateur.toFront();

    }

    @FXML
    private void modifutilisateur(ActionEvent event) {
        modifutilisateur.toFront();
    }

    @FXML
    private void utilisateur(ActionEvent event) {
        menuSelectUserBy.setText("Chercher par");
        utilisateurEtatInitial();
        utilisateur.toFront();
        tableCompte();
    }

    @FXML
    private void afficheDetLivre(ActionEvent event) {
    }

    @FXML
    private void afficheMagazine(ActionEvent event) {
    }

/////////////////////////////////////////////////////////////////////
    
    /**
     * methode pour loader la fenetre de authentication  apres la deconnection.
     * @param e
     * @throws IOException 
     */
    private void loadAuth(ActionEvent e) throws IOException {
        URL url = Paths.get("./src/ui/Authentification.fxml").toUri().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage fenetre = (Stage) ((Node) e.getSource()).getScene().getWindow();
        fenetre.setResizable(false);
        fenetre.setScene(scene);
        fenetre.show();
    }
    
    
    ////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////
    ///////////////////Comptes////////////////////////////////////
    ////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////

    
    
    
    
    /**
     * Methode pour remplir la table Utilisateur dans la page Utilisateur.
     */
    public void tableCompte() {
        try {
            tableuUtilisateur.getItems().clear();
            List<Compte> compteListe = CompteBD.getComptes();
            for (Compte c : compteListe) {
                dataUtilisateur.add(c);
            }
            userid.setCellValueFactory(new PropertyValueFactory<>("id"));
            surnom.setCellValueFactory(new PropertyValueFactory<>("surnom"));
            nome.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenome.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            adressee.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            tele.setCellValueFactory(new PropertyValueFactory<>("telephone"));
            tableuUtilisateur.setItems(dataUtilisateur);
        } catch (SQLException | NullPointerException ex) {
            MsgError("Désolé ,Le système ne peut pas récupérer les information");
        }
    }

    /**
     * on click button Ajouter Utilisateur.
     *
     * @param event
     */
    @FXML
    private void ajouterUtilisateur(ActionEvent event) {    
        if (txt_asurnom.getText().isEmpty() || txt_amotpass.getText().isEmpty() || txt_anom.getText().isEmpty() ||
                txt_aprenom.getText().isEmpty() || combo_TypeCompte.getSelectionModel().isEmpty()) {
            MsgError("Les champs sont obligatoire");
        } else {
            try {
                
                 listo=CompteBD.getComptes();
                
                Utilitaire.isValidTel(txt_atel.getText());

                Utilitaire.isValideString(txt_anom.getText(),"Format de Nom non correct : \n "
                    + "* pas d'espace au debut. \n"
                    + "* pas de nombres. \n"
                    + "* pas de caractère spécial sauf{ ' , - }. \n");
                
                Utilitaire.isValideString(txt_aprenom.getText(), "Format de Prenom non correct : \n "
                    + "* pas d'espace au debut. \n"
                    + "* pas de nombres. \n"
                    + "* pas de caractère spécial sauf{ ' , - }. \n");
                
                Utilitaire.isValideMPass(txt_amotpass.getText());
                
                Compte cmpt = new Compte();
                cmpt.setSurnom(txt_asurnom.getText());
                cmpt.setMot_pass(txt_amotpass.getText());
                cmpt.setNom(txt_anom.getText());
                cmpt.setPrenom(txt_aprenom.getText());
                cmpt.setTelephone(txt_atel.getText());
                cmpt.setAdresse(txt_aadresse.getText());
                if(combo_TypeCompte.getValue().equals("Admin"))
                {
                    cmpt.setTypecompte(0);
                }else if(combo_TypeCompte.getValue().equals("Employee"))
                {
                    cmpt.setTypecompte(1);
                }
               Utilitaire.VerefierDoublan(cmpt,listo,"Utilisateur Existe Déjà.    (SVP,Assurez que les Surnom et N.Tel sont Uniques.)");
             
                    int nombre =CompteBD.insertCompte(cmpt);
                if (nombre > 0) {
                    MsgInfo("Insertion avec succès");
                    clearAjouterUtitlisateur();
                } else {
                    MsgError("Insertion échouée");
                }
                } catch (ExisteException ex) {
                
            }catch(InvalidTelException |InvalidStringException | InvalidMPassException e){} 
             catch (SQLException ex) {
              MsgError("Operation échouée");
            } 
        }
    }

    //vider la forme.
    private void clearAjouterUtitlisateur()
    {
                    txt_asurnom.clear();
                    txt_amotpass.clear();
                    txt_anom.clear();
                    txt_aprenom.clear();
                    txt_atel.clear();
                    txt_aadresse.clear();
                    combo_TypeCompte.getSelectionModel().clearSelection();
    }
    
////////////////////////////////////////////////////////////////////
    //////////////////////Chercher Utilisateur//////////////////////
    ///////////////////////////////////////////////////////////////
  
    /**
     * Action On click button Chercher
     *
     * @param event
     */
    @FXML
    private void byUtilisateur(ActionEvent event) {
        if (!txt_dchercher_u.getText().isEmpty()) {
            if (by == 1) {
                try{
                int id=Integer.parseInt(txt_dchercher_u.getText());
                tableuUtilisateur.getItems().clear();
                chUserById(id);
                menuSelectUserBy.setText("Chercher Par");
                utilisateurEtatInitial();
                }catch(NumberFormatException ex)
                {
                    MsgError("Entrez un nombre valide..");
                }
            } else if (by == 2) {
                try {
                    Utilitaire.isValideString(txt_dchercher_u.getText(), "Format de Nom non correct : \n "
                            + "* pas d'espace au debut. \n"
                            + "* pas de nombres. \n"
                            + "* pas de caractère spécial sauf{ ' , - }. \n");
                    tableuUtilisateur.getItems().clear();
                    chUserByNom(txt_dchercher_u.getText());
                    menuSelectUserBy.setText("Chercher Par");
                     utilisateurEtatInitial();
                } catch (InvalidStringException ex) {}
            }
        } else {
            MsgError("Information demandée.. ");
        }
    }
    
    /**
     * on select option chercher par id.
     * @param event
     */
    @FXML
    private void chercherUserByID(ActionEvent event) {
        txt_dchercher_u.clear();
        txt_dchercher_u.setDisable(false);
        btn_dchercher_u.setDisable(false);
        menuSelectUserBy.setText("By ID");
        by = 1;
    }

    /**
     * on select option chercher par Nom.
     * @param event
     */
    @FXML
    private void chercherUserByNom(ActionEvent event) {
        txt_dchercher_u.clear();
        txt_dchercher_u.setDisable(false);
        btn_dchercher_u.setDisable(false);
        menuSelectUserBy.setText("By Nom");
        by = 2;
    }
    
    /**
     * on select option chercher Tous.
     *
     * @param event
     */
    @FXML
    private void chercherTousUsers(ActionEvent event) {
        utilisateurEtatInitial();
        menuSelectUserBy.setText("Chercher par");
        tableCompte();
    }
    
    
    /**
     * Methode chercher Utilisateurs par son id .
     * @param id
     */
    public void chUserById(int id) {
        try {
            List<Compte> compteListe = CompteBD.getComptes();
            for (Compte c : compteListe) {
                if (c.getId() == id) {
                    dataUtilisateur.add(c);
                }
            }
            userid.setCellValueFactory(new PropertyValueFactory<>("id"));
            surnom.setCellValueFactory(new PropertyValueFactory<>("surnom"));
            nome.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenome.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            adressee.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            tele.setCellValueFactory(new PropertyValueFactory<>("telephone"));
            tableuUtilisateur.setItems(dataUtilisateur);
        } catch (SQLException | NullPointerException ex) {
            MsgError("Désolé,le système ne peut pas récupérer les information");
        }
    }

    /**
     * Methode chercher Utilisateurs par son nom .
     * @param nom
     */
    public void chUserByNom(String nom) {
        try {
            List<Compte> compteListe = CompteBD.getComptes();
            for (Compte c : compteListe) {
                if (c.getNom().equals(nom)) {
                    dataUtilisateur.add(c);
                }
            }
            userid.setCellValueFactory(new PropertyValueFactory<>("id"));
            surnom.setCellValueFactory(new PropertyValueFactory<>("surnom"));
            nome.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenome.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            adressee.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            tele.setCellValueFactory(new PropertyValueFactory<>("telephone"));
            tableuUtilisateur.setItems(dataUtilisateur);
        } catch (SQLException | NullPointerException ex) {
            MsgError("Désolé,le système ne peut pas récupérer les information");
        }
    }

    //retourner la fenetre a l'etat initial .
    private void utilisateurEtatInitial()
    {
        txt_dchercher_u.clear();
        txt_dchercher_u.setDisable(true);
        btn_dchercher_u.setDisable(true);
        by=0;
    }
    

    ///////////////////////////////////////////////////////////////////////////////
    //////////////////////Modifier et Supprimer Utilisateur//////////////////////
    ////////////////////////////////////////////////////////////////////////////////
    /**
     * methode pour modifier un utilisateur.
     * @param event 
     */
    @FXML
    private void modifirUtilisateur(ActionEvent event) {

        if (txt_mnom_u.getText().isEmpty() || txt_mprenom_u.getText().isEmpty() || txt_mmotpass_u.getText().isEmpty()) {
            MsgError("les champs sont Obligatoire..");

        } else {
            try {
                Utilitaire.isValidTel(txt_mtel_u.getText());

                Utilitaire.isValideString(txt_mnom_u.getText(),"Format de Nom non correct : \n "
                    + "* pas d'espace au debut. \n"
                    + "* pas de nombres. \n"
                    + "* pas de caractère spécial sauf{ ' , - }. \n");
                
                Utilitaire.isValideString(txt_mprenom_u.getText(), "Format de Prenom non correct : \n "
                    + "* pas d'espace au debut. \n"
                    + "* pas de nombres. \n"
                    + "* pas de caractère spécial sauf{ ' , - }. \n");
                
                Utilitaire.isValideMPass(txt_mmotpass_u.getText());
                
                Compte cNew = new Compte();
                
                cNew.setTelephone(txt_mtel_u.getText());
                cNew.setId(userModifier.getId());
                cNew.setSurnom(userModifier.getSurnom());
                cNew.setNom(txt_mnom_u.getText());
                cNew.setPrenom(txt_mprenom_u.getText());
                cNew.setMot_pass(txt_mmotpass_u.getText());
                cNew.setAdresse(txt_madresse_u.getText());
                

                int test = CompteBD.compteModif(cNew);
                if (test > 0) {
                    MsgInfo("Modification avec succès");
                   clearModifierUserPage();
                } else {
                    MsgError("Modification échouée");
                }
            }  catch (InvalidTelException | InvalidStringException|InvalidMPassException ex) {
               
            }catch (SQLException |NullPointerException ex) {
                MsgError("Operation échouée");
            }
        }
    }
    
    /**
     * methode pour supprimer un utilisateur.
     * @param event 
     */
    @FXML
    private void supprimerUtilisateur(ActionEvent event) {
        try {
            int test = CompteBD.compteSupprimer(userModifier.getId());
            if (test > 0) {
                MsgInfo("Supprission avec succès");
                
              clearModifierUserPage();
            } else {
                MsgError("Supprission échouée");
            }
        } catch (SQLException ex) {
                MsgError("Operation échouée");
        }
    }

    /**
     * methode pour chercher l'utlisateur par son  id pour modifier ou supprimer .
     * @param event 
     */
    @FXML
    private void getUtilisateurById(ActionEvent event) {

        if (!txt_mchercher_u.getText().isEmpty()) {
           try{
            userModifier = CompteBD.getCompteById(Integer.parseInt(txt_mchercher_u.getText()));
            
            if (userModifier.getId() > 0) {
                btn_msupprimer_u.setDisable(false);
                btn_mmodifier_u.setDisable(false);
                txt_mid_u.setText(String.valueOf(userModifier.getId()));
                txt_mnom_u.setText(userModifier.getNom());
                txt_mprenom_u.setText(userModifier.getPrenom());
                txt_madresse_u.setText(userModifier.getAdresse());
                txt_mtel_u.setText(userModifier.getTelephone());
                txt_msurnom_u.setText(userModifier.getSurnom());
                txt_mmotpass_u.setText(userModifier.getMot_pass());

            } else {
                btn_msupprimer_u.setDisable(true);
                btn_mmodifier_u.setDisable(true);
                MsgInfo("Utilisateur inexist.. ");
                txt_mchercher_u.clear();
            }
        
        }catch(NumberFormatException ex)
                {
                    MsgError("Entrez un nombre valide..");
                } catch (SQLException ex) {
               MsgError("Désolé,le système ne peut pas récupérer les information");
            }
        }else {
            MsgInfo("Il faut saisir un Id Pour la recherch.. ");
        }

    }
    
    //button pour vider la forme 
     @FXML
    private void clear_m_utilisateur(ActionEvent event) {
        clearModifierUserPage();
    }
    //methode  pour vider la forme 
    public void clearModifierUserPage()
    {
                btn_msupprimer_u.setDisable(true);
                btn_mmodifier_u.setDisable(true);
                txt_mchercher_u.clear();
                txt_mid_u.clear();
                txt_mnom_u.clear();
                txt_mprenom_u.clear();
                txt_madresse_u.clear();
                txt_mtel_u.clear();
                txt_msurnom_u.clear();
                txt_mmotpass_u.clear();
    }

    ////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////
    ///////////////////Documents////////////////////////////////////
    ////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////
    
    
    
    
    ///////////////////////////////////////////////////
    ////////////////////////Livre///////////////////
    ///////////////////////////////////////////////////
   
    //Action  pour  selectionner tab Livre dans fenetre Document
    @FXML
    private void livreSelected(Event event) {
        livreInitial(true);
        menuSelectLivreBy.setText("Chercher par");
        by = 0;
        tableLivre();
    }

    //loader les data dans la table livre .
    private void tableLivre() {
        try {
            tableulivre.getItems().clear();
            List<Livre> livreListe = DocumentBD.getLivresDispo();
            for (Livre temp : livreListe) {
                dataLivre.add(temp);
            }
            isbn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
            titre.setCellValueFactory(new PropertyValueFactory<>("Titre"));
            editeur.setCellValueFactory(new PropertyValueFactory<>("Editeur"));
            anne.setCellValueFactory(new PropertyValueFactory<>("Annee"));
            nbrpage.setCellValueFactory(new PropertyValueFactory<>("Nbrpage"));
            type.setCellValueFactory(new PropertyValueFactory<>("Type"));
            nbrdispo.setCellValueFactory(new PropertyValueFactory<>("nombreCopie"));
            tableulivre.setItems(dataLivre);
        } catch (SQLException ex) {
            MsgError("Désolé,le système ne peut pas récupérer les information");
        }
    }

    /**
     * Ajouter livre aux decoments .
     * @param event 
     */
    @FXML
    private void ajouterLivre(ActionEvent event) { //  TODO Exception   
        int st;
        if (txt_aisbn_l.getText().isEmpty() || txt_atitre_l.getText().isEmpty() || txt_aediteur_l.getText().isEmpty() || txt_aannee_l.getText().isEmpty()
                || txt_anbrcopie_l.getText().isEmpty() || txt_aauteur1_l.getText().isEmpty() || combo_TypeLivre.getSelectionModel().isEmpty()) {
            MsgError("les champs sont obligatoire");
        } else {
            try {
                // verification 
                Utilitaire.isValideString(txt_aediteur_l.getText(), " Format de Nom Editeur non correct.");
                Utilitaire.isValideString(txt_aauteur1_l.getText(), "Format de Nom Auteur1 non correct.");
                Utilitaire.isValideISBN(txt_aisbn_l.getText());
                Utilitaire.isValideYear(txt_aannee_l.getText());
                
                Livre livreNeo = new Livre();
                livreNeo.setISBN(txt_aisbn_l.getText());
                livreNeo.setTitre(txt_atitre_l.getText());
                livreNeo.setEditeur(txt_aediteur_l.getText());
                livreNeo.setAnnee(Integer.parseInt(txt_aannee_l.getText()));
                livreNeo.setNombreCopie(Integer.parseInt(txt_anbrcopie_l.getText()));
                livreNeo.setNbrpage(Integer.parseInt(txt_anbr_page.getText()));
                livreNeo.setType(combo_TypeLivre.getValue());
                livreNeo.setAuteur1(txt_aauteur1_l.getText());
                livreNeo.setAuteur2(txt_aauteur2_l.getText());
                livreNeo.setAuteur3(txt_aateur3_l.getText());
                livreNeo.setAuteur4(txt_aauteur4_l.getText());
                
               //verification si le livre existe deja .
                listDoc=DocumentBD.getDocsVerDoublon();
                Utilitaire.VerefierDoublan(livreNeo, listDoc, "Document Existe Déjà.    (SVP,Assurez que le ISBN est Unique.)");
                st = DocumentBD.ajouterLivre(livreNeo);
                if (st > 0) {

                    MsgInfo("L'insertion est valid! Merci.");
                    clearAjouterLivre();

                } else {
                    MsgError("Désolé, Insertion échouée");
                }
            }  catch (ExisteException | InvalidStringException | InvalidIsbnException | InvalidYearException e){
            } catch (SQLException ex) {
                 MsgError("Operation Echoée.");
            } catch (NumberFormatException ex) {
                 MsgError("Nombre Format non correct.");
            } 
        }
    }

    //////////////////////Chercher Livre//////////////////////
    /**
     * Methode chercher Livre par son ISBN .
     */
    private void chLivreByIsbn(String isb) {
        try {
            List<Livre> livreListe = DocumentBD.getLivresDispo();
            for (Livre l : livreListe) {
                if (l.getISBN().equals(isb)) {
                    dataLivre.add(l);
                }
            }
            isbn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
            titre.setCellValueFactory(new PropertyValueFactory<>("Titre"));
            editeur.setCellValueFactory(new PropertyValueFactory<>("Editeur"));
            anne.setCellValueFactory(new PropertyValueFactory<>("Annee"));
            nbrpage.setCellValueFactory(new PropertyValueFactory<>("Nbrpage"));
            type.setCellValueFactory(new PropertyValueFactory<>("Type"));
            nbrdispo.setCellValueFactory(new PropertyValueFactory<>("nombreCopie"));
            tableulivre.setItems(dataLivre);
        } catch (SQLException ex) {
            MsgError("Désolé,le système ne peut pas récupérer les information");
        }
    }

    /**
     * Methode chercher Livre par son Nom .
     */
    private void chLivreByTitre(String titr) {
        try {
            List<Livre> livreListe = DocumentBD.getLivresDispo();
            for (Livre l : livreListe) {
                if (l.getTitre().equals(titr)) {
                    dataLivre.add(l);
                }
            }
            isbn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
            titre.setCellValueFactory(new PropertyValueFactory<>("Titre"));
            editeur.setCellValueFactory(new PropertyValueFactory<>("Editeur"));
            anne.setCellValueFactory(new PropertyValueFactory<>("Annee"));
            nbrpage.setCellValueFactory(new PropertyValueFactory<>("Nbrpage"));
            type.setCellValueFactory(new PropertyValueFactory<>("Type"));
            nbrdispo.setCellValueFactory(new PropertyValueFactory<>("nombreCopie"));
            tableulivre.setItems(dataLivre);
        } catch (SQLException ex) {
            MsgError("Désolé,le système ne peut pas récupérer les information");
        }
    }

    /**
     * Action On click button Chercher .
     * @param event
     */
    @FXML
    private void livreBY(ActionEvent event) {
        if (!txt_cherche_l.getText().isEmpty()) {
            if (by == 1) {
                try {
                    Utilitaire.isValideISBN(txt_cherche_l.getText());
                    tableulivre.getItems().clear();
                    chLivreByIsbn(txt_cherche_l.getText());
                    livreInitial(true);
                    menuSelectLivreBy.setText("Chercher par");
                    by = 0;
                } catch (InvalidIsbnException ex) {}
            } else if (by == 2) {
                tableulivre.getItems().clear();
                chLivreByTitre(txt_cherche_l.getText());
                livreInitial(true);
                menuSelectLivreBy.setText("Chercher par");
                by = 0;
            }
        } else {
            MsgError("Information demandée.. ");
        }
    }

    /**
     * on select option vide pour afficher tous les Livre disponible.
     *
     * @param event
     */
    @FXML
    private void livreByTous(ActionEvent event) {
        livreInitial(true);
        menuSelectLivreBy.setText("Chercher par");
        by = 0;
        tableLivre();
    }

    /**
     * on select option chercher par isbn.
     *
     * @param event
     */
    @FXML
    private void livreByISBN(ActionEvent event) {
        livreInitial(false);
        menuSelectLivreBy.setText("By ISBN");
        by = 1;
    }

    /**
     * on select option chercher par titre.
     *
     * @param event
     */
    @FXML
    private void livreByTitre(ActionEvent event) {
        livreInitial(false);
        menuSelectLivreBy.setText("By Titre");
        by = 2;
    }

    //////////////////////Modifier & Supprimer Livre//////////////////////
   /**
    * la recherche dun livre par son isbn pour la modification ou la suppression.
    * @param event 
    */
    @FXML
    private void getLivreByISBN(ActionEvent event) {
        
        if (!txt_mcherche_l.getText().isEmpty()) {
            try {
                Utilitaire.isValideISBN(txt_mcherche_l.getText());
                
                livreModifier = DocumentBD.getLivreByIsbn(txt_mcherche_l.getText());

                if (livreModifier.getNumeroEnrg() > 0) {
                    btn_supprimer_l.setDisable(false);
                    btn_modifier_l.setDisable(false);

                    txt_misbn_l.setText(livreModifier.getISBN());
                    txt_mtitre_l.setText(livreModifier.getTitre());
                    txt_mediteur_l.setText(livreModifier.getEditeur());
                    txt_mannee_l.setText(String.valueOf(livreModifier.getAnnee()));
                    txt_mnbr_page.setText(String.valueOf(livreModifier.getNbrpage()));
                    txt_mnbrcopie_l.setText(String.valueOf(livreModifier.getNombreCopie()));
                    txt_mtype_l.setText(livreModifier.getType());
                    txt_mauteur1_l.setText(livreModifier.getAuteur1());
                    txt_mauteur2_l.setText(livreModifier.getAuteur2());
                    txt_mateur3_l.setText(livreModifier.getAuteur3());
                    txt_mauteur4_l.setText(livreModifier.getAuteur4());
                } else {
                    btn_supprimer_l.setDisable(true);
                    btn_modifier_l.setDisable(true);
                    MsgInfo("Livre inexist.. ");
                    txt_mcherche_l.clear();
                }
            } catch (SQLException e) {
                MsgError("Operation Echoée");
            } catch (InvalidIsbnException ex) {
                
            }
        } else {
            MsgInfo("Il faut saisr un ISBN Pour la recherch.. ");
        }

    }

    /**
     * methode pour modifier un livre ..
     * @param event 
     */
    @FXML
    private void modifierLivre(ActionEvent event) {
        if (txt_mtitre_l.getText().isEmpty() || txt_mediteur_l.getText().isEmpty() || txt_mannee_l.getText().isEmpty() || txt_mnbr_page.getText().isEmpty() || txt_mtype_l.getText().isEmpty()
                || txt_mauteur1_l.getText().isEmpty()) {
            MsgError("les champs sont Obligatoire..");

        } else {
            try {
                //verification 
                Utilitaire.isValideString(txt_mediteur_l.getText(), " Format de Nom Editeur non correct.");
                Utilitaire.isValideString(txt_mauteur1_l.getText(), "Format de Nom Auteur1 non correct.");
                Utilitaire.isValideYear(txt_mannee_l.getText());
                
                
                Livre lNew = new Livre();
                lNew.setISBN(livreModifier.getISBN());
                lNew.setNombreCopie(livreModifier.getNombreCopie());
                lNew.setTitre(txt_mtitre_l.getText());
                lNew.setEditeur(txt_mediteur_l.getText());
                lNew.setAnnee(Integer.parseInt(txt_mannee_l.getText()));
                lNew.setNbrpage(Integer.parseInt(txt_mnbr_page.getText()));
                lNew.setType(txt_mtype_l.getText());
                lNew.setAuteur1(txt_mauteur1_l.getText());
                lNew.setAuteur2(txt_mauteur2_l.getText());
                lNew.setAuteur3(txt_mateur3_l.getText());
                lNew.setAuteur4(txt_mauteur4_l.getText());

                int test = DocumentBD.livreModif(lNew);
                if (test > 0) {
                    MsgInfo("Modification avec succès");

                    btn_supprimer_l.setDisable(true);
                    btn_modifier_l.setDisable(true);
                    clearModifierLivre();
                } else {
                    MsgError("Modification échouée");
                }

            } catch (SQLException ex) {
                 MsgError("Operation Echoée.");
            } catch (NumberFormatException ex) {
                 MsgError("Nombre Format non correct.");
            } catch (InvalidStringException | InvalidYearException ex) {
                
            } 
        }
    }

    /**
     * methode pour supprimer un livre .
     * @param event 
     */
    @FXML
    private void supprimerLivre(ActionEvent event) {
        try {
            int test = DocumentBD.livreSupprimer(livreModifier.getISBN());
            if (test > 0) {

                MsgInfo("Supprission avec succès");

                btn_supprimer_l.setDisable(true);
                btn_modifier_l.setDisable(true);
                clearModifierLivre();
            } else {
                MsgError("Supprission échouée");
            }
        } catch (SQLException e) {
            MsgError(e.getMessage());
        }
    }

    //vider les text Fileds de la page ajouter livre.
    private void clearAjouterLivre() {
        txt_aisbn_l.clear();
        txt_atitre_l.clear();
        txt_aediteur_l.clear();
        txt_aannee_l.clear();
        txt_anbrcopie_l.clear();
        txt_anbr_page.clear();
        combo_TypeLivre.getSelectionModel().clearSelection();
        txt_aauteur1_l.clear();
        txt_aauteur2_l.clear();
        txt_aateur3_l.clear();
        txt_aauteur4_l.clear();
    }
    
    //vider les text Fileds de la page modifier livre.
    private void clearModifierLivre() {
        txt_mcherche_l.clear();
        txt_misbn_l.clear();
        txt_mtitre_l.clear();
        txt_mediteur_l.clear();
        txt_mnbr_page.clear();
        txt_mtype_l.clear();
        txt_mannee_l.clear();
        txt_mnbrcopie_l.clear();
        txt_mauteur1_l.clear();
        txt_mauteur2_l.clear();
        txt_mateur3_l.clear();
        txt_mauteur4_l.clear();
    }
    //initialiser l'etat de la page modifier livre.
    private void livreInitial(boolean bool) {
        txt_cherche_l.clear();
        txt_cherche_l.setDisable(bool);
        btn_dchercher_l.setDisable(bool);
    }

    ///////////////////////////////////////////////////
    ////////////////////////Magazine///////////////////
    ///////////////////////////////////////////////////
    
    //action en sellectioner la tab Magazine dans la fenetre Documents
    @FXML
    private void magSelected(Event event) {
        magInitial(true);
        menuSelectMagBy.setText("Chercher par");
        by = 0;
        tableMag();
    }
//methode pour loader les data dans la table Magazine.
    public void tableMag() {
        try {
            tableumagazine.getItems().clear();
            List<Magazine> magListe = DocumentBD.getMagDispo();
            for (Magazine temp : magListe) {
                dataMagazine.add(temp);
            }
            isbnm.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
            titrem.setCellValueFactory(new PropertyValueFactory<>("Titre"));
            editeurm.setCellValueFactory(new PropertyValueFactory<>("Editeur"));
            annem.setCellValueFactory(new PropertyValueFactory<>("Annee"));
            periode.setCellValueFactory(new PropertyValueFactory<>("periode"));
            dateedition.setCellValueFactory(new PropertyValueFactory<>("date_edit"));
            nbrdispom.setCellValueFactory(new PropertyValueFactory<>("nombreCopie"));
            tableumagazine.setItems(dataMagazine);
        } catch (SQLException ex) {
            MsgError("Désolé,le système ne peut pas récupérer les information");
        }
    }

    /**
     * Ajouter Magazine
     * @param event 
     */
    @FXML
    private void ajouterMagazine(ActionEvent event) {

        int st;
        if (txt_aisbn_m.getText().isEmpty() || txt_atitre_m.getText().isEmpty() || txt_aediteur_m.getText().isEmpty()
                || txt_anbrcopie_m.getText().isEmpty() || txt_aauteur1_m.getText().isEmpty() || txt_aperiode_m.getText().isEmpty() || txt_aannee_m.getText().isEmpty()) {
            MsgError("les champs sont obligatoire");
        } else {
            try {
                //verification 
                Utilitaire.isValideISBN(txt_aisbn_m.getText());
                Utilitaire.isValideString(txt_aediteur_m.getText(),  " Format de Nom Editeur non correct.");
                Utilitaire.isValideString(txt_aperiode_m.getText(), " Format de Period non correct.");
                Utilitaire.isValideString(txt_aauteur1_m.getText(), "Format de Nom Auteur1 non correct.");
                Utilitaire.isValideYear(txt_aannee_m.getText());
                
                
                Magazine magazinNeo = new Magazine();

                magazinNeo.setISBN(txt_aisbn_m.getText());
                magazinNeo.setTitre(txt_atitre_m.getText());
                magazinNeo.setEditeur(txt_aediteur_m.getText());
                magazinNeo.setAnnee(Integer.parseInt(txt_aannee_m.getText()));
                magazinNeo.setPeriode(txt_aperiode_m.getText());
                magazinNeo.setDate_edit(txt_aedition_m.getValue().toString());
                magazinNeo.setNombreCopie(Integer.parseInt(txt_anbrcopie_m.getText()));
                magazinNeo.setAuteur1(txt_aauteur1_m.getText());
                magazinNeo.setAuteur2(txt_aauteur2_m.getText());
                magazinNeo.setAuteur3(txt_aauteur3_m.getText());
                magazinNeo.setAuteur4(txt_aauteur4_m.getText());

                //verifier Magazine existe deja .
                listDoc=DocumentBD.getDocsVerDoublon();
                if(Utilitaire.VerefierDoublan(magazinNeo, listDoc, "Document Existe Déjà.    (SVP,Assurez que le ISSN est Unique.)")){
                
                st = DocumentBD.ajouterMagazine(magazinNeo);
                if (st > 0) {
                    MsgInfo("L'insertion est valid! Merci.");
                    clearAjouterMag();

                } else {
                    MsgError("Désolé, Insertion échouée");
                }}
            }catch (ExisteException | InvalidIsbnException | InvalidStringException | InvalidYearException ex){}
            catch (SQLException ex) {
                 MsgError("Operation Echoée.");
            }catch (NumberFormatException ex) {
                 MsgError("Nombre Format non correct.");
            } 
        }

    }
    
    
//////////////////////Chercher Magazin//////////////////////

    /**
     * Methode chercher Magazine par son ISBN .
     * @param isb
     */
    public void chMagazineByIsbn(String isb) {
        try {
            List<Magazine> magListe = DocumentBD.getMagDispo();
            for (Magazine m : magListe) {
                if (m.getISBN().equals(isb)) {
                    dataMagazine.add(m);
                }
            }
            isbnm.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
            titrem.setCellValueFactory(new PropertyValueFactory<>("Titre"));
            editeurm.setCellValueFactory(new PropertyValueFactory<>("Editeur"));
            annem.setCellValueFactory(new PropertyValueFactory<>("Annee"));
            periode.setCellValueFactory(new PropertyValueFactory<>("periode"));
            dateedition.setCellValueFactory(new PropertyValueFactory<>("date_edit"));
            nbrdispom.setCellValueFactory(new PropertyValueFactory<>("nombreCopie"));
            tableumagazine.setItems(dataMagazine);
        } catch (SQLException ex) {
            MsgError("Désolé,le système ne peut pas récupérer les information");
        }
    }

    /**
     * Methode chercher Magazine par son Nom .
     * @param titr
     */
    public void chMagazinByTitre(String titr) {
        try {
            List<Magazine> magListe = DocumentBD.getMagDispo();
            for (Magazine m : magListe) {
                if (m.getTitre().equals(titr)) {
                    dataMagazine.add(m);
                }
            }
             isbnm.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
            titrem.setCellValueFactory(new PropertyValueFactory<>("Titre"));
            editeurm.setCellValueFactory(new PropertyValueFactory<>("Editeur"));
            annem.setCellValueFactory(new PropertyValueFactory<>("Annee"));
            periode.setCellValueFactory(new PropertyValueFactory<>("periode"));
            dateedition.setCellValueFactory(new PropertyValueFactory<>("date_edit"));
            nbrdispom.setCellValueFactory(new PropertyValueFactory<>("nombreCopie"));
            tableumagazine.setItems(dataMagazine);
        } catch (SQLException ex) {
             MsgError("Désolé,le système ne peut pas récupérer les information");
        }
    }

    /**
     * On click button Chercher .
     *
     * @param event
     */
    @FXML
    private void magazineBY(ActionEvent event) {
        if (!txt_cherche_m.getText().isEmpty()) {
            if (by == 1) {
                try {
                    Utilitaire.isValideISBN(txt_cherche_m.getText());
                    tableumagazine.getItems().clear();
                    chMagazineByIsbn(txt_cherche_m.getText());
                    magInitial(true);
                    menuSelectMagBy.setText("Chercher par");
                    by = 0;
                } catch (InvalidIsbnException ex) { 
                }
            } else if (by == 2) {
                tableumagazine.getItems().clear();
                chMagazinByTitre(txt_cherche_m.getText());
                magInitial(true);
                menuSelectMagBy.setText("Chercher par");
                by = 0;
            }
        } else {
            MsgError("Information demandée.. ");
        }
    }

    /**
     * on select option vide pour afficher tous les Magazines disponibles.
     *
     * @param event
     */
    @FXML
    private void magByTous(ActionEvent event) {
        magInitial(true);
        menuSelectMagBy.setText("Chercher par");
        by = 0;
        tableMag();
    }

    /**
     * on select option chercher par isbn.
     *
     * @param event
     */
    @FXML
    private void magByISBN(ActionEvent event) {
        magInitial(false);
        menuSelectMagBy.setText("By ISSN");
        by = 1;
    }

    /**
     * on select option chercher par titre.
     *
     * @param event
     */
    @FXML
    private void magByTitre(ActionEvent event) {
        magInitial(false);
        menuSelectMagBy.setText("By Titre");
        by = 2;
    }
    
///////////////////Modifier & Supprimer un Magazine//////////////////////
    
/**
 * method pour recuperer le Magazine par son ISBN pour la modification ou suprission.
 * @param event 
 */
    @FXML
    private void getMagazineByISBN(ActionEvent event) {
        if (!txt_mcherche_m.getText().isEmpty()) {
            try {
              Utilitaire.isValideISBN(txt_mcherche_m.getText());
                magModifier = DocumentBD.getMagByIsbn(txt_mcherche_m.getText());

                if (magModifier.getNumeroEnrg() > 0) {
                    btn_supprimer_m.setDisable(false);
                    btn_modifier_m.setDisable(false);
                    
                    txt_misbn_m.setText(magModifier.getISBN());
                    txt_mtitre_m.setText(magModifier.getTitre());
                    txt_mediteur_m.setText(magModifier.getEditeur());
                    txt_mannee_m.setText(String.valueOf(magModifier.getAnnee()));
                    txt_mperiode_m.setText(magModifier.getPeriode());
                    txt_medition_m.setText(magModifier.getDate_edit());
                    txt_mnbrcopie_m.setText(String.valueOf(magModifier.getNombreCopie()));
                    txt_mauteur1_m.setText(magModifier.getAuteur1());
                    txt_mauteur2_m.setText(magModifier.getAuteur2());
                    txt_mateur3_m.setText(magModifier.getAuteur3());
                    txt_mauteur4_m.setText(magModifier.getAuteur4());
                } else {
                    btn_supprimer_m.setDisable(true);
                    btn_modifier_m.setDisable(true);
                    MsgInfo("Magazine inexist.. ");
                    txt_mcherche_m.clear();
                }
            } catch (SQLException e) {
                MsgError("Operation Echoée");
            } catch (InvalidIsbnException ex) {
                
            }
        } else {
            MsgInfo("Il faut saisr un ISBN Pour la recherch.. ");
        }

    }

    /**
     * methode pour modifier un magazine .
     * @param event 
     */
    @FXML
    private void modifierMagazine(ActionEvent event) {
        if (txt_mtitre_m.getText().isEmpty() || txt_mediteur_m.getText().isEmpty() || txt_mannee_m.getText().isEmpty() || txt_mperiode_m.getText().isEmpty() || txt_medition_m.getText().isEmpty()
                || txt_mauteur1_m.getText().isEmpty()) {
            MsgError("les champs sont Obligatoire..");

        } else {
            try {
                //verification 
                Utilitaire.isValideString(txt_mediteur_m.getText(), " Format de Nom Editeur non correct.");
                Utilitaire.isValideString(txt_mauteur1_m.getText(), "Format de Nom Auteur1 non correct.");
                Utilitaire.isValideString(txt_mperiode_m.getText(), "Format de Period non correct.");
                Utilitaire.isValideYear(txt_mannee_m.getText());
                Utilitaire.isValideDate(txt_medition_m.getText());
                
                Magazine mNew = new Magazine();
                mNew.setISBN(magModifier.getISBN());
                mNew.setNombreCopie(magModifier.getNombreCopie());
                mNew.setTitre(txt_mtitre_m.getText());
                mNew.setEditeur(txt_mediteur_m.getText());
                mNew.setAnnee(Integer.parseInt(txt_mannee_m.getText()));
                mNew.setPeriode(txt_mperiode_m.getText());
                mNew.setDate_edit(txt_medition_m.getText());
                mNew.setAuteur1(txt_mauteur1_m.getText());
                mNew.setAuteur2(txt_mauteur2_m.getText());
                mNew.setAuteur3(txt_mateur3_m.getText());
                mNew.setAuteur4(txt_mauteur4_m.getText());

                int test = DocumentBD.magModif(mNew);
                if (test > 0) {
                    MsgInfo("Modification avec succès");

                    btn_supprimer_m.setDisable(true);
                    btn_modifier_m.setDisable(true);
                    clearModifierMag();
                } else {
                    MsgError("Modification échouée");
                }

            }  catch (InvalidStringException | InvalidYearException | InvalidDAteException ex) {
                
            }catch (SQLException ex) {
                 MsgError("Operation Echoée.");
            }catch (NumberFormatException ex) {
                 MsgError("Nombre Format non correct.");
            } 
        }
    }

    /**
     * methode pour supprimer un magazine 
     * @param event 
     */
    @FXML
    private void supprimerMagazine(ActionEvent event) {
        try {
            int test = DocumentBD.magSupprimer(magModifier.getISBN());
            if (test > 0) {

                MsgInfo("Supprission avec succès");

                btn_supprimer_m.setDisable(true);
                btn_modifier_m.setDisable(true);
                clearModifierMag();
            } else {
                MsgError("Supprission échouée");
            }
        } catch (SQLException e) {
             MsgError("Désolé,le système ne peut pas récupérer les information");
        }
    }

    //methode ppour vider la form Ajuter magazine
    private void clearAjouterMag() {
        txt_aisbn_m.clear();
        txt_atitre_m.clear();
        txt_aediteur_m.clear();
        txt_aedition_m.getEditor().clear();
        txt_aannee_m.clear();
        txt_anbrcopie_m.clear();
        txt_aauteur1_m.clear();
        txt_aauteur2_m.clear();
        txt_aauteur3_m.clear();
        txt_aauteur4_m.clear();
        txt_aperiode_m.clear();
    }
//methode ppour vider la form modifier magazine
    private void clearModifierMag() {
        txt_mcherche_m.clear();
        txt_misbn_m.clear();
        txt_mtitre_m.clear();
        txt_mediteur_m.clear();
        txt_mperiode_m.clear();
        txt_medition_m.clear();
        txt_mannee_m.clear();
        txt_mnbrcopie_m.clear();
        txt_mauteur1_m.clear();
        txt_mauteur2_m.clear();
        txt_mateur3_m.clear();
        txt_mauteur4_m.clear();
    }

    //etat initial tab magazine 
    private void magInitial(boolean bool) {
        txt_cherche_m.clear();
        txt_cherche_m.setDisable(bool);
        btn_dchercher_m.setDisable(bool);
    }

    ///////////////////////////////////////////////////
    ////////////////////////Dictionnaire///////////////////
    ///////////////////////////////////////////////////
  
    //action en sellectioner la tab dictionnaire  dans la fenetre Documents
    @FXML
    private void decSelected(Event event) {
        dicInitial(true);
        menuSelectDicBy.setText("Chercher par");
        by = 0;
        tableDic();
    }

    //methode pour loader les datas dans la table dictionnaire 
    public void tableDic() {
        try {
            tableudictionnaire.getItems().clear();
            List<Dictionnaire> dicListe = DocumentBD.getDicDispo();
            for (Dictionnaire temp : dicListe) {
                dataDictionnaire.add(temp);
            }
            isbnd.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
            titred.setCellValueFactory(new PropertyValueFactory<>("Titre"));
            editeurd.setCellValueFactory(new PropertyValueFactory<>("Editeur"));
            anned.setCellValueFactory(new PropertyValueFactory<>("Annee"));
            langue.setCellValueFactory(new PropertyValueFactory<>("langue"));
            nbrdispod.setCellValueFactory(new PropertyValueFactory<>("nombreCopie"));
            tableudictionnaire.setItems(dataDictionnaire);
        } catch (SQLException ex) {
            MsgError("Désolé,le système ne peut pas récupérer les information");
        }
    }

    /**
     * method ajuter dictonnaire .
     * @param event 
     */
    @FXML
    private void ajouterDictionnaire(ActionEvent event) {
        int st;
        if (txt_aisbn_d.getText().isEmpty() || txt_atitre_d.getText().isEmpty() || txt_aediteur_d.getText().isEmpty() || txt_aannee_d.getText().isEmpty()
                || txt_alangue_d.getText().isEmpty() || txt_aauteur1_d.getText().isEmpty() || txt_anbrcopie_d.getText().isEmpty()) {
            MsgError("les champs sont obligatoire");
        } else {
            try {
                //verification 
                Utilitaire.isValideISBN(txt_aisbn_d.getText());
                Utilitaire.isValideString(txt_aediteur_d.getText(), " Format de Nom Editeur non correct.");
                Utilitaire.isValideString(txt_aauteur1_d.getText(), "Format de Nom Auteur1 non correct.");
                Utilitaire.isValideYear(txt_aannee_d.getText());
                
                
                Dictionnaire dictNeo = new Dictionnaire();

                dictNeo.setISBN(txt_aisbn_d.getText());
                dictNeo.setTitre(txt_atitre_d.getText());
                dictNeo.setEditeur(txt_aediteur_d.getText());
                dictNeo.setLangue(txt_alangue_d.getText());
                dictNeo.setAnnee(Integer.parseInt(txt_aannee_d.getText()));
                dictNeo.setNombreCopie(Integer.parseInt(txt_anbrcopie_d.getText()));
                dictNeo.setAuteur1(txt_aauteur1_d.getText());
                dictNeo.setAuteur2(txt_aauteur2_d.getText());
                dictNeo.setAuteur3(txt_aauteur3_d.getText());
                dictNeo.setAuteur4(txt_aauteur4_d.getText());

                //verifier existe deja .
                listDoc=DocumentBD.getDocsVerDoublon();
                Utilitaire.VerefierDoublan(dictNeo, listDoc, "Document Existe Déjà.    (SVP,Assurez que le ISBN est Unique.)");
                st = DocumentBD.ajouterDictionnaire(dictNeo);
                if (st > 0) {
                    MsgInfo("L'insertion est valid! Merci.");
                    clearAjouterDic();
                } else {
                    MsgError("Désolé, Insertion échouée");
                }
            }  catch (InvalidIsbnException | InvalidStringException | InvalidYearException | ExisteException ex) {
                
            }catch (SQLException ex) {
                MsgError("Désolé, Operation échouée");
            }catch (NumberFormatException ex) {
                MsgError("Nombre Format non correct.");
            }
        }
    }

   
    
    //////////////////////Chercher Dictionnaire//////////////////////
    /**
     * Methode chercher Dictionnaire par son ISBN .
     * @param isb
     */
    public void chDictByIsbn(String isb) {
        try {
            List<Dictionnaire> dicListe = DocumentBD.getDicDispo();
            for (Dictionnaire d : dicListe) {
                if (d.getISBN().equals(isb)) {
                    dataDictionnaire.add(d);
                }
            }
            isbnd.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
            titred.setCellValueFactory(new PropertyValueFactory<>("Titre"));
            editeurd.setCellValueFactory(new PropertyValueFactory<>("Editeur"));
            anned.setCellValueFactory(new PropertyValueFactory<>("Annee"));
            langue.setCellValueFactory(new PropertyValueFactory<>("langue"));
            nbrdispod.setCellValueFactory(new PropertyValueFactory<>("nombreCopie"));
            tableudictionnaire.setItems(dataDictionnaire);
        } catch (SQLException ex) {
            MsgError("Désolé,le système ne peut pas récupérer les information");
        }
    }

    /**
     * Methode chercher Dictionnaire par son Nom .
     * @param titr
     */
    public void chDictByTitre(String titr) {
        try {
            List<Dictionnaire> dicListe = DocumentBD.getDicDispo();
            for (Dictionnaire d : dicListe) {
                if (d.getTitre().equals(titr)) {
                    dataDictionnaire.add(d);
                }
            }
            isbnd.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
            titred.setCellValueFactory(new PropertyValueFactory<>("Titre"));
            editeurd.setCellValueFactory(new PropertyValueFactory<>("Editeur"));
            anned.setCellValueFactory(new PropertyValueFactory<>("Annee"));
            langue.setCellValueFactory(new PropertyValueFactory<>("langue"));
            nbrdispod.setCellValueFactory(new PropertyValueFactory<>("nombreCopie"));
            tableudictionnaire.setItems(dataDictionnaire);
        } catch (SQLException ex) {
            MsgError("Désolé,le système ne peut pas récupérer les information");
        }
    }

    /**
     * On click button Chercher .
     *
     * @param event
     */
    @FXML
    private void dictionnaireBY(ActionEvent event) {
        if (!txt_cherche_d.getText().isEmpty()) {
            if (by == 1) {
                try {
                    Utilitaire.isValideISBN(txt_cherche_d.getText());
                    tableudictionnaire.getItems().clear();
                    chDictByIsbn(txt_cherche_d.getText());
                    dicInitial(true);
                    menuSelectDicBy.setText("Chercher par");
                    by = 0;
                } catch (InvalidIsbnException ex) {
                  
                }
            } else if (by == 2) {
                tableudictionnaire.getItems().clear();
                chDictByTitre(txt_cherche_d.getText());
                dicInitial(true);
                menuSelectDicBy.setText("Chercher par");
                by = 0;
            }
        } else {
            MsgError("Information demandée.. ");
        }
    }

    /**
     * on select option vide pour afficher tous les Dictionnaires disponibles.
     *
     * @param event
     */
    @FXML
    private void dicByTous(ActionEvent event) {
        dicInitial(true);
        menuSelectDicBy.setText("Chercher par");
        by = 0;
        tableDic();
    }

    /**
     * on select option chercher par isbn.
     *
     * @param event
     */
    @FXML
    private void dicByISBN(ActionEvent event) {
        dicInitial(false);
        menuSelectDicBy.setText("By ISBN");
        by = 1;
    }

    /**
     * on select option chercher par titre.
     *
     * @param event
     */
    @FXML
    private void dicByTitre(ActionEvent event) {
        dicInitial(false);
        menuSelectDicBy.setText("By Titre");
        by = 2;
    }

    ///////////////////////////////////////////////////////////////////////////////
    //////////////////////Modifier et Supprimer Dictionnaire//////////////////////
    ////////////////////////////////////////////////////////////////////////////////
   
    /**
     * methode pour recuperer un dictionnaire par son ISBN pour la modification ou la supprission .
     * @param event 
     */
    @FXML
    private void getDictionnaireByISBN(ActionEvent event) {
        if (!txt_mcherche_d.getText().isEmpty()) {
            try {
                Utilitaire.isValideISBN(txt_mcherche_d.getText());
                dicModifier = DocumentBD.getDicByIsbn(txt_mcherche_d.getText());

                if (dicModifier.getNumeroEnrg() > 0) {
                    btn_supprimer_d.setDisable(false);
                    btn_modifier_d.setDisable(false);

                    txt_misbn_d.setText(dicModifier.getISBN());
                    txt_mtitre_d.setText(dicModifier.getTitre());
                    txt_mediteur_d.setText(dicModifier.getEditeur());
                    txt_mannee_d.setText(String.valueOf(dicModifier.getAnnee()));
                    txt_mlangue_d.setText(dicModifier.getLangue());
                    txt_mnbrcopie_d.setText(String.valueOf(dicModifier.getNombreCopie()));
                    txt_mauteur1_d.setText(dicModifier.getAuteur1());
                    txt_mauteur2_d.setText(dicModifier.getAuteur2());
                    txt_mateur3_d.setText(dicModifier.getAuteur3());
                    txt_mauteur4_d.setText(dicModifier.getAuteur4());
                } else {
                    btn_supprimer_d.setDisable(true);
                    btn_modifier_d.setDisable(true);
                    MsgInfo("Dictionnaire inexist.. ");
                    txt_mcherche_m.clear();
                }
            } catch (SQLException e) {
                MsgError("Operation Echoée");
            } catch (InvalidIsbnException ex) {
                
            }
        } else {
            MsgInfo("Il faut saisr un ISBN Pour la recherch.. ");
        }

    }

    /**
     * methde pour modifier dictionnaire .
     * @param event 
     */
    @FXML
    private void modifierDictionnaire(ActionEvent event) {
        if (txt_mtitre_d.getText().isEmpty() || txt_mediteur_d.getText().isEmpty() || txt_mannee_d.getText().isEmpty() || txt_mlangue_d.getText().isEmpty()
                || txt_mauteur1_d.getText().isEmpty()) {
            MsgError("les champs sont Obligatoire..");

        } else {
            try {
                
                Utilitaire.isValideString(txt_mediteur_d.getText(), " Format de Nom Editeur non correct.");
                Utilitaire.isValideYear(txt_mannee_d.getText());
                Utilitaire.isValideString(txt_mauteur1_d.getText(), "Format de Nom Auteur1 non correct.");
                 
                Dictionnaire dNew = new Dictionnaire();
                dNew.setISBN(dicModifier.getISBN());
                dNew.setNombreCopie(dicModifier.getNombreCopie());
                dNew.setTitre(txt_mtitre_d.getText());
                dNew.setEditeur(txt_mediteur_d.getText());
                dNew.setAnnee(Integer.parseInt(txt_mannee_d.getText()));
                dNew.setLangue(txt_mlangue_d.getText());
                dNew.setAuteur1(txt_mauteur1_d.getText());
                dNew.setAuteur2(txt_mauteur2_d.getText());
                dNew.setAuteur3(txt_mateur3_d.getText());
                dNew.setAuteur4(txt_mauteur4_d.getText());


                int test = DocumentBD.dicModif(dNew);
                if (test > 0) {
                    MsgInfo("Modification avec succès");

                    btn_supprimer_d.setDisable(true);
                    btn_modifier_d.setDisable(true);
                    clearModifierDic();
                } else {
                    MsgError("Modification échouée");

                }

            } catch (SQLException e) {
                MsgError("Désolé,le système ne peut pas récupérer les information");
            } catch (InvalidStringException | InvalidYearException ex) {
              
            }
        }

    }

    /**
     * methode pour supprimer un dictionnaire 
     * @param event 
     */
    @FXML
    private void supprimerDictionnaire(ActionEvent event) {
        try {
            int test = DocumentBD.dicSupprimer(dicModifier.getISBN());
            if (test > 0) {

                MsgInfo("Supprission avec succès");

                btn_supprimer_d.setDisable(true);
                btn_modifier_d.setDisable(true);
                clearModifierDic();
            } else {
                MsgError("Supprission échouée");
            }
        } catch (SQLException e) {
            MsgError("Désolé,le système ne peut pas récupérer les information");
        }
    }

    //vider la forme dans la fenetre Ajouter dictionnaire
    private void clearAjouterDic() {
        txt_aisbn_d.clear();
        txt_atitre_d.clear();
        txt_aediteur_d.clear();
        txt_alangue_d.clear();
        txt_aannee_d.clear();
        txt_anbrcopie_d.clear();
        txt_aauteur1_d.clear();
        txt_aauteur2_d.clear();
        txt_aauteur3_d.clear();
        txt_aauteur4_d.clear();
    }
//vider la forme dans la fenetre Modifier Dictionnaire 
    private void clearModifierDic() {
        txt_mcherche_d.clear();
        txt_misbn_d.clear();
        txt_mtitre_d.clear();
        txt_mediteur_d.clear();
        txt_mlangue_d.clear();
        txt_mannee_d.clear();
        txt_mnbrcopie_d.clear();
        txt_mauteur1_d.clear();
        txt_mauteur2_d.clear();
        txt_mateur3_d.clear();
        txt_mauteur4_d.clear();
    }

    //etat initial fenetre dictionnaire .
    private void dicInitial(boolean bool) {
        txt_cherche_d.clear();
        txt_cherche_d.setDisable(bool);
        btn_dchercher_d.setDisable(bool);
    }


}
