/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import Dao.CompteBD;
import Dao.DocumentBD;
import Dao.MembreBD;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modele.Compte;
import modele.Dictionnaire;
import modele.Document;
import modele.Historique;
import modele.Livre;
import modele.Magazine;
import modele.Membre;
import utils.ExisteException;
import utils.InvalidIdException;
import utils.InvalidIsbnException;
import utils.InvalidMPassException;
import utils.InvalidStringException;
import utils.InvalidTelException;
import utils.Utilitaire;

/**
 *
 * @author georg
 */
public class BibMenuController extends Utilitaire implements Initializable {

    @FXML
    private Label title;
    @FXML
    private Pane historique;
    @FXML
    private Label totalelivre;
    @FXML
    private Label totalemagazin;
    @FXML
    private Label totalemagazine;
    @FXML
    private Label totaledictionnaire;
    @FXML
    private TextField cherchehistorique;
    @FXML
    private JFXButton retourB;
    @FXML
    private Label secceretour;
    @FXML
    private Label echecretour;
    @FXML
    private Pane ajouterMembre;
    @FXML
    private JFXTextField txt_aIdMembre;
    @FXML
    private JFXTextField txt_anom;
    @FXML
    private JFXTextField txt_aprenom;
    @FXML
    private JFXTextField txt_aadresse;
    @FXML
    private JFXButton btn_majouter;
    @FXML
    private JFXTextField txt_atel;
    @FXML
    private Pane documents;
    @FXML
    private JFXTabPane docTabPane;
    @FXML
    private Tab docTabLivre;
    @FXML
    private JFXButton btn_dchercher_l;
    @FXML
    private TextField txt_cherche_l;
    @FXML
    private MenuButton menuSelectLivreBy;

    /////////////////les Tableux//////////////////////////
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

    @FXML
    private TableView<Membre> tableuMembre;
    @FXML
    private TableColumn<Membre, Integer> emprunter;
    @FXML
    private TableColumn<Membre, Integer> code_membre;
    @FXML
    private TableColumn<Membre, String> nome, prenome, adressee, tele;

    @FXML
    private TableView<Historique> tableuHistorique;
    @FXML
    private TableColumn<Historique, Integer> num_enrgH;
    @FXML
    private TableColumn<Historique, String> isbnH;
    @FXML
    private TableColumn<Historique, String> titreH;
    @FXML
    private TableColumn<Historique, Integer> typeH;
    @FXML
    private TableColumn<Historique, String> dateEH;
    @FXML
    private TableColumn<Historique, String> dateRH;

    /////////////////////////////////////////////////////////////////
    @FXML
    private JFXButton btn_emprunter_l;
    @FXML
    private Label succeeprunter1;
    @FXML
    private Label echec_emprunter_l;
    @FXML
    private JFXButton btn_dchercher_m;
    @FXML
    private JFXButton btn_dchercher_membre;
    @FXML
    private MenuButton menuSelectMagBy;
    @FXML
    private TextField txt_cherche_m;

    @FXML
    private JFXButton btn_emprunter_m;
    @FXML
    private Label succeeprunter2;
    @FXML
    private Label echec_emprunter_m;
    @FXML
    private JFXButton btn_dchercher_d;
    @FXML
    private MenuButton menuSelectDicBy;
    @FXML
    private TextField txt_cherche_d;

    @FXML
    private JFXButton btn_emprunter_d;
    @FXML
    private Label succeeprunter;
    @FXML
    private Label echec_emprunter_d;
    @FXML
    private Pane parametres;
    @FXML
    private JFXTextField surnmModif;
    @FXML
    private Label pseudomodifechec;
    @FXML
    private Label pseudomodifsucce;
    @FXML
    private JFXTextField telephoneModif;
    @FXML
    private JFXTextField adresseModif;
    @FXML
    private Label motpassmodifechec;
    @FXML
    private Label motpassmodifsucce;
    @FXML
    private JFXPasswordField oldMotPass;
    @FXML
    private JFXPasswordField newMotPass;
    @FXML
    private JFXPasswordField confirmerMotPass;
    @FXML
    private Pane modifMembre;
    @FXML
    private JFXTextField txt_mCode_m;
    @FXML
    private JFXTextField txt_mnom_m;
    @FXML
    private JFXTextField txt_mprenom_m;
    @FXML
    private JFXTextField txt_madresse_m;
    @FXML
    private JFXTextField txt_mtel_m;
    @FXML
    private JFXButton btn_mmodifier_m;
    @FXML
    private JFXTextField txt_mid_m;
    @FXML
    private JFXButton btn_msupprimer_m;
    @FXML
    private JFXTextField txt_mnbEprunter_m;
    @FXML
    private JFXButton btn_mchercher_m;
    @FXML
    private TextField txt_mchercher_m;
    @FXML
    private Pane membres;
    @FXML
    private MenuButton menuSelectMembreBy;
    @FXML
    private TextField txt_dchercher_m;
    @FXML
    private AnchorPane mainEmployee;
    @FXML
    private JFXTextField txt_livre_mem_code;
    @FXML
    private JFXTextField txt_mag_mem_code;
    @FXML
    private JFXTextField txt_dic_mem_code;

    
    
    //compt de user connectée.
    private Compte cmptConnecte;
    //objet pour la modification (l'anicien obj avant la modifier)
    private Membre membreModifier;
    //document trouvee apres un recherch. pour fair l'emprunte ou a retoure.
    private Document livreEmpr;
    private Document magEmpr;
    private Document dicEmpr;
    private Historique docRetour;
    int by;
    
    List<Membre> listMembre=null;
    
    //Observables listes pour les contenus de table . chaque liste pour une table .
    public ObservableList<Membre> dataMembre = FXCollections.observableArrayList();
    public ObservableList<Livre> dataLivre = FXCollections.observableArrayList();
    public ObservableList<Magazine> dataMagazine = FXCollections.observableArrayList();
    public ObservableList<Dictionnaire> dataDictionnaire = FXCollections.observableArrayList();
    public ObservableList<Historique> dataHistorique = FXCollections.observableArrayList();

    //recuperer l'information de User connectée.
    public void CompteConnecte(Compte c) {
        this.cmptConnecte = c;
        title.setText(cmptConnecte.getSurnom());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AnimerFenetre(mainEmployee);

    }
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
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////Menu de navigation////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////

    //mehode pour deconnecer user connecter.
    @FXML
    private void deconnecter(ActionEvent event) {
        try {
            loadAuth(event);
        } catch (IOException ex) {
            MsgError("Désolé essayez encore une fois");
        }
    }

    @FXML
    private void document(ActionEvent event) {
        documents.toFront();
    }

    @FXML
    private void historique(ActionEvent event) throws SQLException {
        retourB.setDisable(true);
        totalDocStatistique();
        tableHistorique();
        historique.toFront();
    }

    @FXML
    private void ajouterMembrePane(ActionEvent event) {
                    txt_aIdMembre.clear();
                    txt_anom.clear();
                    txt_aprenom.clear();
                    txt_atel.clear();
                    txt_aadresse.clear();
        ajouterMembre.toFront();
    }

    @FXML
    private void modifierMembrePane(ActionEvent event) {
        clearModifierMember();
        modifMembre.toFront();
    }

    @FXML
    private void parametres(ActionEvent event) {
        parametres.toFront();
        surnmModif.setText(cmptConnecte.getSurnom());
        telephoneModif.setText(cmptConnecte.getTelephone());
        adresseModif.setText(cmptConnecte.getAdresse());

    }

    @FXML
    private void membres(ActionEvent event) {
        by = 0;
        txt_dchercher_m.clear();
        btn_dchercher_membre.setDisable(true);
        menuSelectMembreBy.setText("Chercher Par");
        tableMembre();
        membres.toFront();

    }
    ///////////////////////End Menu Navigation////////////////////////////////

    
    ///////////////////////////////////////////////////////////////////////////
    /////////////////////Parametre d'utilisateur/////////////////////////////
    /////////////////////////////////////////////////////////////////////////
   
    /**
     * methode pour le user puisse modifier ses informations 
     * @param event 
     */
    @FXML
    private void utilisateurModif(ActionEvent event) {
        if (!surnmModif.getText().isEmpty()) {
            try {
                Utilitaire.isValidTel(telephoneModif.getText());
                cmptConnecte.setSurnom(surnmModif.getText());
                cmptConnecte.setTelephone(telephoneModif.getText());
                cmptConnecte.setAdresse(adresseModif.getText());

                int test = CompteBD.modifierUtilisateur(cmptConnecte);
                if (test > 0) {
                    MsgInfo("Modification avec succès");
                    parametres(event);
                } else {
                    MsgError("Modification Echoée");
                    parametres(event);
                }
            } catch (SQLException ex) {
                MsgError("Désolé,le système ne peut pas récupérer les information");
            } catch (InvalidTelException ex) {
                
            }
        } else {
            MsgError("les champs sont obligatoires.");
        }

    }
/**
 * methode pour le user puisse modifier son mod de pass
 * @param event 
 */
    @FXML
    private void uMotpassModif(ActionEvent event) {
        if (!(oldMotPass.getText().isEmpty() || newMotPass.getText().isEmpty() || confirmerMotPass.getText().isEmpty())) {
            if (oldMotPass.getText().equals(cmptConnecte.getMot_pass())) {
                if (newMotPass.getText().equals(confirmerMotPass.getText())) {
                    try {
                        Utilitaire.isValideMPass(newMotPass.getText());
                        cmptConnecte.setMot_pass(newMotPass.getText());
                        int test = CompteBD.motpassModif(cmptConnecte);
                        if (test > 0) {
                            MsgInfo("Modification avec succès");
                            oldMotPass.clear();
                            newMotPass.clear();
                            confirmerMotPass.clear();
                        } else {
                            MsgError("Modification Echoée");
                            oldMotPass.clear();
                            newMotPass.clear();
                            confirmerMotPass.clear();
                        }
                    } catch (SQLException ex) {
                        MsgError(ex.getMessage() + "\n Opertion Echoée");
                    } catch (InvalidMPassException ex) {
                       
                    }
                } else {
                    MsgError("Le nouveau mot de passe et la confirmation ne correspondent pas");
                }
            } else {
                MsgError("L'ancien mot de pass est incorrect");
                oldMotPass.clear();
                newMotPass.clear();
                confirmerMotPass.clear();
            }
        } else {
            MsgError("les champs sont obligatoires.");
        }
    }

    //////////////////////// End Parametre d'utilisateur/////////////////////
    
    
    ////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////
    ///////////////////////////////Membres////////////////////////////////
    ////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////
    
    
    ////////////////////////////Ajouter Membre////////////////////////////////////
  
    /**
     * methode pour ajouter un membre .
     * @param event 
     */
    @FXML
    private void ajouterMembre(ActionEvent event) {
        if (txt_aIdMembre.getText().isEmpty() || txt_anom.getText().isEmpty() || txt_anom.getText().isEmpty() || txt_aprenom.getText().isEmpty() || txt_atel.getText().isEmpty()) {
            MsgError("Les champs sont obligatoire");
        } else {
            try {
                //verification 
                Utilitaire.isValideID(txt_aIdMembre.getText());
                Utilitaire.isValideString(txt_anom.getText(), "Le nom ne ressemble pas à un nom valide");
                Utilitaire.isValideString(txt_aprenom.getText(), "Le Prenom ne ressemble pas à un nom valide");
                Utilitaire.isValidTel(txt_atel.getText());
                        
                Membre membre = new Membre();
                membre.setIdentity(txt_aIdMembre.getText());
                membre.setNom(txt_anom.getText());
                membre.setPrenom(txt_aprenom.getText());
                membre.setTel(txt_atel.getText());
                membre.setAdresse(txt_aadresse.getText());

                //verifier membre existe deja .
                listMembre=MembreBD.getAllMembres();
                
                Utilitaire.VerefierDoublan(membre, listMembre, "Membre Existe Déjà.    (SVP,Assurez que le Code ,N.Tel et Id Membre sont Uniques.)");
                
                int nombre = MembreBD.insertMembre(membre);
                if (nombre > 0) {
                    MsgInfo("Insertion avec succès");
                    txt_aIdMembre.clear();
                    txt_anom.clear();
                    txt_aprenom.clear();
                    txt_atel.clear();
                    txt_aadresse.clear();

                } else {
                    MsgError("Insertion échouée");
                }
            }catch (ExisteException | InvalidStringException | InvalidTelException | InvalidIdException ex) {
               
            }catch (SQLException ex) {
                MsgError("Opertaion échouée");
            } 
        }

    }

    ////////////////////////////////Afficher Membre/////////////////////////////////
   //methode pour loader les datas dans la table membre 
    public void tableMembre() {
        try {
            tableuMembre.getItems().clear();
            List<Membre> membreListe = MembreBD.getAllMembres();
            for (Membre m : membreListe) {
                dataMembre.add(m);
            }
            if (dataMembre.isEmpty()) {
                MsgInfo("Desolé,Pas de membres à afficher ..");
            } else {
                code_membre.setCellValueFactory(new PropertyValueFactory<>("identity"));
                nome.setCellValueFactory(new PropertyValueFactory<>("nom"));
                prenome.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                adressee.setCellValueFactory(new PropertyValueFactory<>("adresse"));
                tele.setCellValueFactory(new PropertyValueFactory<>("tel"));
                emprunter.setCellValueFactory(new PropertyValueFactory<>("nbr_eprunter"));
                tableuMembre.setItems(dataMembre);
            }
        } catch (SQLException ex) {
            MsgError("Désolé,le système ne peut pas récupérer les information");
        }
    }

    /**
     * On clique button Chercher .
     *
     * @param event
     */
    @FXML
    private void chercherMembreModif(ActionEvent event) {
        if (!txt_dchercher_m.getText().isEmpty()) {
            if (by == 1) {
                try {
                    Utilitaire.isValideID(txt_dchercher_m.getText());
                    tableuMembre.getItems().clear();
                    chMembreByCode(txt_dchercher_m.getText());
                     txt_dchercher_m.clear();
                     txt_dchercher_m.setDisable(true);
                     btn_dchercher_membre.setDisable(true);
                     menuSelectMembreBy.setText("Chercher par");
                     by=0;
                } catch (InvalidIdException ex) {}
            } else if (by == 2) {
                try {
                    Utilitaire.isValidTel(txt_dchercher_m.getText());
                    tableuMembre.getItems().clear();
                    chMembreByTel(txt_dchercher_m.getText());
                     txt_dchercher_m.clear();
                     txt_dchercher_m.setDisable(true);
                     btn_dchercher_membre.setDisable(true);
                     menuSelectMembreBy.setText("Chercher par");
                     by=0;
                } catch (InvalidTelException ex) {}                  
            }
        } else {
            MsgError("Information demandée.. ");
        }

    }

    /**
     * action on selection option chercher Tous 
     * @param event 
     */
    @FXML
    private void chercherMembreTous(ActionEvent event) {
        txt_dchercher_m.clear();
        txt_dchercher_m.setDisable(true);
        btn_dchercher_membre.setDisable(true);
        menuSelectMembreBy.setText("Chercher par");
        tableMembre();
    }
/**
 * action on selection option chercher par identity
 * @param event 
 */
    @FXML
    private void chercherMembreByCode(ActionEvent event) {
        txt_dchercher_m.clear();
        txt_dchercher_m.setDisable(false);
        btn_dchercher_membre.setDisable(false);
        menuSelectMembreBy.setText("Par identité");
        by = 1;
    }
/**
 * action on selection option chercher par telephone
 * @param event 
 */
    @FXML
    private void chercherMembreByTel(ActionEvent event) {
        txt_dchercher_m.clear();
        txt_dchercher_m.setDisable(false);
        btn_dchercher_membre.setDisable(false);
        menuSelectMembreBy.setText("Par Tel");
        by = 2;
    }

    /**
     * Methode chercher Membre par son telephone.
     *
     * @param tel
     */
    public void chMembreByTel(String tel) {
        try {
            List<Membre> membreListe = MembreBD.getAllMembres();
            membreListe.stream().filter((m) -> (m.getTel().equals(tel))).forEachOrdered((m) -> {
                dataMembre.add(m);
            });
            if (dataMembre.isEmpty()) {
                MsgInfo("Desolé,Pas de membres à afficher ..");
            } else {
                code_membre.setCellValueFactory(new PropertyValueFactory<>("identity"));
                nome.setCellValueFactory(new PropertyValueFactory<>("nom"));
                prenome.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                adressee.setCellValueFactory(new PropertyValueFactory<>("adresse"));
                tele.setCellValueFactory(new PropertyValueFactory<>("tel"));
                emprunter.setCellValueFactory(new PropertyValueFactory<>("nbr_eprunter"));
                tableuMembre.setItems(dataMembre);
            }
        } catch (SQLException ex) {
            MsgError("Désolé,le système ne peut pas récupérer les information");
        }
    }

    /**
     * Methode chercher Membre par son identité .
     *
     * @param id
     */
    public void chMembreByCode(String id) {
        try {
            List<Membre> membreListe = MembreBD.getAllMembres();
            membreListe.stream().filter((m) -> (m.getIdentity().equals(id) )).forEachOrdered((m) -> {
                dataMembre.add(m);
            });
            if (dataMembre.isEmpty()) {
                MsgInfo("Desolé,Pas de membres à afficher ..");
            } else {
                code_membre.setCellValueFactory(new PropertyValueFactory<>("identity"));
                nome.setCellValueFactory(new PropertyValueFactory<>("nom"));
                prenome.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                adressee.setCellValueFactory(new PropertyValueFactory<>("adresse"));
                tele.setCellValueFactory(new PropertyValueFactory<>("tel"));
                emprunter.setCellValueFactory(new PropertyValueFactory<>("nbr_eprunter"));
                tableuMembre.setItems(dataMembre);
            }
        } catch (SQLException ex) {
            MsgError("Désolé,le système ne peut pas récupérer les information");
        }
    }

    //////////////////////////////Modifier & Supprimer Membre//////////////////////////////////
   /**
    * chercher un membre par son id pour le modifier ou supprimer .
    * @param event 
    */
    
    @FXML
    private void getMembreById(ActionEvent event) {
        if (!txt_mchercher_m.getText().isEmpty()) {
            try {
                Utilitaire.isValideID(txt_mchercher_m.getText());
                    membreModifier = MembreBD.getMembreByIdentity(txt_mchercher_m.getText());
                if (membreModifier.getCode_membre() > 0) {
                    txt_mCode_m.setText(String.valueOf(membreModifier.getCode_membre()));
                    txt_mnom_m.setText(membreModifier.getNom());
                    txt_mprenom_m.setText(membreModifier.getPrenom());
                    txt_madresse_m.setText(membreModifier.getAdresse());
                    txt_mtel_m.setText(membreModifier.getTel());
                    txt_mid_m.setText(membreModifier.getIdentity());
                    txt_mnbEprunter_m.setText(String.valueOf(membreModifier.getNbr_eprunter()));
                    txt_mchercher_m.clear();
                    btn_msupprimer_m.setDisable(false);
                    btn_mmodifier_m.setDisable(false);
                } else {
                    MsgInfo("Membre inexist..");
                    clearModifierMember();
                }
            } catch (SQLException ex) {
                MsgError("Membre introuvable..");
                clearModifierMember();
            } catch (InvalidIdException ex) {
                
            }
        } else {
            MsgError("Information demandée..");
            clearModifierMember();
        }
    }

    /**
     * modifier un membre 
     * @param event 
     */
    @FXML
    private void modifirMembre(ActionEvent event) {
        if (txt_mnom_m.getText().isEmpty() || txt_mprenom_m.getText().isEmpty() || txt_mtel_m.getText().isEmpty()
                || txt_mid_m.getText().isEmpty()) {
            MsgError("les champs sont Obligatoire..");
        } else {
            try {
                //verifiaction 
                Utilitaire.isValideID(txt_mid_m.getText());
                Utilitaire.isValideString(txt_mnom_m.getText(), "Le nom ne ressemble pas à un nom valide");
                Utilitaire.isValideString(txt_mprenom_m.getText(), "Le Prenom ne ressemble pas à un nom valide");
                Utilitaire.isValidTel(txt_mtel_m.getText());
                
                       
          
                
                Membre mNew = new Membre();
                mNew.setCode_membre(membreModifier.getCode_membre());
                mNew.setIdentity(txt_mid_m.getText());
                mNew.setNom(txt_mnom_m.getText());
                mNew.setPrenom(txt_mprenom_m.getText());
                mNew.setTel(txt_mtel_m.getText());
                mNew.setAdresse(txt_madresse_m.getText());
                mNew.setNbr_eprunter(membreModifier.getNbr_eprunter());

                //verifier existe deja .
                listMembre=MembreBD.getAllMembres();
                List<Membre> listoMe= new ArrayList<>();
                for(Membre temp:listMembre)
                {
                    if(!temp.getIdentity().equals(membreModifier.getIdentity()))
                    {
                        listoMe.add(temp);
                    }
                }
               
                Utilitaire.VerefierDoublan(mNew,listoMe,"Membre Existe Déjà.    (SVP,Assurez que le N.Tel et Id Membre sont Uniques.)");
                
                int test = MembreBD.membreModif(mNew);
                if (test > 0) {
                    MsgInfo("Modification avec succès");
                    clearModifierMember();
                } else {
                    MsgError("Modification échouée");
                }
            } catch (SQLException ex) {
                MsgError("Operation échouée");
            } catch (InvalidIdException | InvalidStringException | InvalidTelException | ExisteException ex) {
               
            }
        }
    }

    /**
     * supprimer un membre .
     * @param event 
     */
    @FXML
    private void supprimerMembre(ActionEvent event) {
        try {
            int test = MembreBD.membreSupprimer(membreModifier.getCode_membre());
            if (test > 0) {
                MsgInfo("Supprission avec succès");
                clearModifierMember();
            } else {
                MsgError("Supprission échouée");
            }
        } catch (SQLException ex) {
            MsgError("Operation échouée");
        }
    }

    //vider la forme modifier membre .
    private void clearModifierMember() {
        txt_mCode_m.clear();
        txt_mnom_m.clear();
        txt_mprenom_m.clear();
        txt_madresse_m.clear();
        txt_mtel_m.clear();
        txt_mid_m.clear();
        txt_mnbEprunter_m.clear();
        btn_msupprimer_m.setDisable(true);
        btn_mmodifier_m.setDisable(true);
        txt_mchercher_m.clear();

    }

    ///////////////////////////////////////////////////////////////
    //////////////////////////Documents///////////////////////////
    ///////////////////////////////////////////////////////////////
    
    
    //////////////////////////////Livre///////////////////////////
    
    
   // action on selection la tab livre dans fenetre document 
    @FXML
    private void livreSelected(Event event) {
        livreInitial(true);
        menuSelectLivreBy.setText("Chercher par");
        by = 0;
        tableLivre();
    }
    
    //loader les datas dans la table livre .
    public void tableLivre() {
        try {
            tableulivre.getItems().clear();
            List<Livre> livreListe = DocumentBD.getLivresDispo();
            for (Livre temp : livreListe) {
                dataLivre.add(temp);
            }
            if (dataLivre.isEmpty()) {
                MsgInfo("Desolé,Pas de Livres à afficher ..");
            } else {
                isbn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
                titre.setCellValueFactory(new PropertyValueFactory<>("Titre"));
                editeur.setCellValueFactory(new PropertyValueFactory<>("Editeur"));
                anne.setCellValueFactory(new PropertyValueFactory<>("Annee"));
                nbrpage.setCellValueFactory(new PropertyValueFactory<>("Nbrpage"));
                type.setCellValueFactory(new PropertyValueFactory<>("Type"));
                nbrdispo.setCellValueFactory(new PropertyValueFactory<>("nombreCopie"));
                tableulivre.setItems(dataLivre);
            }
        } catch (SQLException ex) {
            MsgError("Désolé,le système ne peut pas récupérer les information");
        }
    }

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
            if (dataLivre.isEmpty()) {
                MsgInfo("Desolé,Pas de Livres à afficher ..");
            } else {
                isbn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
                titre.setCellValueFactory(new PropertyValueFactory<>("Titre"));
                editeur.setCellValueFactory(new PropertyValueFactory<>("Editeur"));
                anne.setCellValueFactory(new PropertyValueFactory<>("Annee"));
                nbrpage.setCellValueFactory(new PropertyValueFactory<>("Nbrpage"));
                type.setCellValueFactory(new PropertyValueFactory<>("Type"));
                nbrdispo.setCellValueFactory(new PropertyValueFactory<>("nombreCopie"));
                tableulivre.setItems(dataLivre);
            }
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
            if (dataLivre.isEmpty()) {
                MsgInfo("Desolé,Pas de Livres à afficher ..");
            } else {
                isbn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
                titre.setCellValueFactory(new PropertyValueFactory<>("Titre"));
                editeur.setCellValueFactory(new PropertyValueFactory<>("Editeur"));
                anne.setCellValueFactory(new PropertyValueFactory<>("Annee"));
                nbrpage.setCellValueFactory(new PropertyValueFactory<>("Nbrpage"));
                type.setCellValueFactory(new PropertyValueFactory<>("Type"));
                nbrdispo.setCellValueFactory(new PropertyValueFactory<>("nombreCopie"));
                tableulivre.setItems(dataLivre);
            }
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
                } catch (InvalidIsbnException ex) {
                   
                }
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
     * on select option vide pour afficher tous les livre disponible.
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

    //etat inital tab livre .
    public void livreInitial(boolean bool) {
        txt_cherche_l.clear();
        txt_cherche_l.setDisable(bool);
        btn_dchercher_l.setDisable(bool);
    }

    ////////////////////////////Magazine//////////////////////////////
   
    //action on sellection la tab magazine dans fenetre documents .
    @FXML
    private void magSelected(Event event) {
        magInitial(true);
        menuSelectMagBy.setText("Chercher par");
        by = 0;
        tableMag();
    }
//methode pour loader les datas dans la table magazin
    public void tableMag() {
        try {
            tableumagazine.getItems().clear();
            List<Magazine> magListe = DocumentBD.getMagDispo();
            for (Magazine temp : magListe) {
                dataMagazine.add(temp);
            }
            if (dataMagazine.isEmpty()) {
                MsgInfo("Desolé,Pas de Magazines à afficher ..");
            } else {
                isbnm.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
                titrem.setCellValueFactory(new PropertyValueFactory<>("Titre"));
                editeurm.setCellValueFactory(new PropertyValueFactory<>("Editeur"));
                annem.setCellValueFactory(new PropertyValueFactory<>("Annee"));
                periode.setCellValueFactory(new PropertyValueFactory<>("periode"));
                dateedition.setCellValueFactory(new PropertyValueFactory<>("date_edit"));
                nbrdispom.setCellValueFactory(new PropertyValueFactory<>("nombreCopie"));
                tableumagazine.setItems(dataMagazine);
            }
        } catch (SQLException ex) {
           MsgError("Désolé,le système ne peut pas récupérer les information");
        }
    }

    /**
     * Methode chercher Magazine par son ISBN .
     *
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
            if (dataMagazine.isEmpty()) {
                MsgInfo("Desolé,Pas de Magazines à afficher ..");
            } else {
                isbnm.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
                titrem.setCellValueFactory(new PropertyValueFactory<>("Titre"));
                editeurm.setCellValueFactory(new PropertyValueFactory<>("Editeur"));
                annem.setCellValueFactory(new PropertyValueFactory<>("Annee"));
                periode.setCellValueFactory(new PropertyValueFactory<>("periode"));
                dateedition.setCellValueFactory(new PropertyValueFactory<>("date_edit"));
                nbrdispom.setCellValueFactory(new PropertyValueFactory<>("nombreCopie"));
                tableumagazine.setItems(dataMagazine);
            }
        } catch (SQLException ex) {
            MsgError("Désolé,le système ne peut pas récupérer les information");
        }
    }

    /**
     * Methode chercher Magazine par son Nom .
     *
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
            if (dataMagazine.isEmpty()) {
                MsgInfo("Desolé,Pas de Magazines à afficher ..");
            } else {
                isbnm.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
                titrem.setCellValueFactory(new PropertyValueFactory<>("Titre"));
                editeurm.setCellValueFactory(new PropertyValueFactory<>("Editeur"));
                annem.setCellValueFactory(new PropertyValueFactory<>("Annee"));
                periode.setCellValueFactory(new PropertyValueFactory<>("periode"));
                dateedition.setCellValueFactory(new PropertyValueFactory<>("date_edit"));
                nbrdispom.setCellValueFactory(new PropertyValueFactory<>("nombreCopie"));
                tableumagazine.setItems(dataMagazine);
            }
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

    private void magInitial(boolean bool) {
        txt_cherche_m.clear();
        txt_cherche_m.setDisable(bool);
        btn_dchercher_m.setDisable(bool);
    }

    ///////////////////////////Dictionnaire////////////////////////////////////
   
    //action selectionne la tab dictionnaire dans la fenetre document
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
            if (dataDictionnaire.isEmpty()) {
                MsgInfo("Desolé,Pas de Dictionnaire à afficher ..");
            } else {
                isbnd.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
                titred.setCellValueFactory(new PropertyValueFactory<>("Titre"));
                editeurd.setCellValueFactory(new PropertyValueFactory<>("Editeur"));
                anned.setCellValueFactory(new PropertyValueFactory<>("Annee"));
                langue.setCellValueFactory(new PropertyValueFactory<>("langue"));
                nbrdispod.setCellValueFactory(new PropertyValueFactory<>("nombreCopie"));
                tableudictionnaire.setItems(dataDictionnaire);
            }
        } catch (SQLException ex) {
            MsgError("Désolé,le système ne peut pas récupérer les information");
        }
    }

    /**
     * Methode chercher Dictionnaire par son ISBN .
     *
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
            if (dataDictionnaire.isEmpty()) {
                MsgInfo("Desolé,Pas de Dictionnaire à afficher ..");
            } else {
                isbnd.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
                titred.setCellValueFactory(new PropertyValueFactory<>("Titre"));
                editeurd.setCellValueFactory(new PropertyValueFactory<>("Editeur"));
                anned.setCellValueFactory(new PropertyValueFactory<>("Annee"));
                langue.setCellValueFactory(new PropertyValueFactory<>("langue"));
                nbrdispod.setCellValueFactory(new PropertyValueFactory<>("nombreCopie"));
                tableudictionnaire.setItems(dataDictionnaire);
            }
        } catch (SQLException ex) {
            MsgError("Désolé,le système ne peut pas récupérer les information");
        }
    }

    /**
     * Methode chercher Dictionnaire par son Nom .
     *
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
            if (dataDictionnaire.isEmpty()) {
                MsgInfo("Desolé,Pas de Dictionnaire à afficher ..");
            } else {
                isbnd.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
                titred.setCellValueFactory(new PropertyValueFactory<>("Titre"));
                editeurd.setCellValueFactory(new PropertyValueFactory<>("Editeur"));
                anned.setCellValueFactory(new PropertyValueFactory<>("Annee"));
                langue.setCellValueFactory(new PropertyValueFactory<>("langue"));
                nbrdispod.setCellValueFactory(new PropertyValueFactory<>("nombreCopie"));
                tableudictionnaire.setItems(dataDictionnaire);
            }
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

    private void dicInitial(boolean bool) {
        txt_cherche_d.clear();
        txt_cherche_d.setDisable(bool);
        btn_dchercher_d.setDisable(bool);
    }

    /////////////////////////////Historique////////////////////////////////////////  
   
    //methode pour loades les datas dans la table historique 
    private void tableHistorique() {
        try {
            tableuHistorique.getItems().clear();
            List<Historique> histoListe = DocumentBD.getHistorique();
            for (Historique temp : histoListe) {
                dataHistorique.add(temp);
            }
            if (dataHistorique.isEmpty()) {
                MsgInfo("Desolé,Pas de Livres à afficher ..");
            } else {
                num_enrgH.setCellValueFactory(new PropertyValueFactory<>("num_enrg"));
                isbnH.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
                titreH.setCellValueFactory(new PropertyValueFactory<>("titre"));
                typeH.setCellValueFactory(new PropertyValueFactory<>("type_doc"));
                dateEH.setCellValueFactory(new PropertyValueFactory<>("dateE"));
                dateRH.setCellValueFactory(new PropertyValueFactory<>("dateR"));
                tableuHistorique.setItems(dataHistorique);
            }
        } catch (SQLException ex) {
            MsgError("Désolé,le système ne peut pas récupérer les information");
        }
    }

    //methode pour chercher le historique par id de membre .
    private void chHistoriqueByIdMembre(String id)
    {
         try {
            List<Historique> histoListe = DocumentBD.getHistorique();
            for (Historique temp : histoListe) {
                if(temp.getMembre().getIdentity().equals(id)){
                    tableuHistorique.getItems().clear();
                    dataHistorique.add(temp);
                }

            }
            if (dataHistorique.isEmpty()) {
                MsgInfo("Desolé,Pas de Documents à afficher ..");
            } else {
                num_enrgH.setCellValueFactory(new PropertyValueFactory<>("num_enrg"));
                isbnH.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
                titreH.setCellValueFactory(new PropertyValueFactory<>("titre"));
                typeH.setCellValueFactory(new PropertyValueFactory<>("type_doc"));
                dateEH.setCellValueFactory(new PropertyValueFactory<>("dateE"));
                dateRH.setCellValueFactory(new PropertyValueFactory<>("dateR"));
                tableuHistorique.setItems(dataHistorique);
            }
        } catch (SQLException ex) {
            MsgError("Désolé,le système ne peut pas récupérer les information");
        }
    }
    
    /**
     * on clicke button chercher .
     * @param event 
     */
    @FXML
    private void chercherHistorique(ActionEvent event) {
         if (!cherchehistorique.getText().isEmpty()) {
                try {
                    Utilitaire.isValideID(cherchehistorique.getText());
                    tableuHistorique.getItems().clear();
                    chHistoriqueByIdMembre(cherchehistorique.getText());
                    cherchehistorique.clear();
                } catch (InvalidIdException ex) {
             }
        } else {
            MsgError("Information demandée.. ");
        }
    }
    
    
    /**
     * on selectionner un historique dans la table historique .
     * @param event 
     */
    @FXML
    private void selectionHistorique(MouseEvent event) {
        retourB.setDisable(true);
        docRetour = tableuHistorique.getSelectionModel().getSelectedItem();
        if (docRetour == null) {
            retourB.setDisable(true);
        } else if (docRetour.getDateR() == null) {
            retourB.setDisable(false);
        } else {
            retourB.setDisable(false);
        }

    }
//mettre a jour les statistiques .
    private void totalDocStatistique() {
        try {
            int livre = 0;
            int mag = 0;
            int dic = 0;
            for (Historique temp : DocumentBD.getHistorique()) {
                switch (temp.getType_doc()) {
                    case 0:
                        livre++;
                        break;
                    case 1:
                        mag++;
                        break;
                    case 2:
                        dic++;
                        break;
                    default:
                        break;
                }
            }

            totalelivre.setText(String.valueOf(livre));
            totalemagazine.setText(String.valueOf(mag));
            totaledictionnaire.setText(String.valueOf(dic));
        } catch (SQLException ex) {
            MsgError("Error de calculer les Statistiques..");
        }
    }

    /////////////////////////////////Emprunter ///////////////////////////////////////////////
    
    /**
     * on selectionner un livre pour emprunter  dans la table livre .
     * @param e 
     */
    @FXML
    public void tableLivreSelectionne(MouseEvent e) {
        btn_emprunter_l.setDisable(true);
        Document docselected = tableulivre.getSelectionModel().getSelectedItem();

        if (docselected == null) {
            btn_emprunter_l.setDisable(true);
        } else {
            try {
                livreEmpr = DocumentBD.getDocByIsbn(docselected.getISBN());
                btn_emprunter_l.setDisable(false);
            } catch (SQLException | NullPointerException ex) {
                MsgError("le system ne trouve pas le Document");
            }
        }

    }
/**
 * on selectionner un magazine pour emprunter dans la table magazine .
 * @param event 
 */
    @FXML
    private void tableMagSelectionne(MouseEvent event) {
        btn_emprunter_m.setDisable(true);
        Document docselected = tableumagazine.getSelectionModel().getSelectedItem();

        if (docselected == null) {
            btn_emprunter_m.setDisable(true);
        } else {
            try {
                magEmpr = DocumentBD.getDocByIsbn(docselected.getISBN());
                btn_emprunter_m.setDisable(false);
            } catch (SQLException | NullPointerException ex) {
                MsgError("le system ne trouve pas le Document");
            }
        }
    }

    /**
     * on selectionner un dictionnaire pour emprunter dans la table dictionnaire .
     * @param event 
     */
    @FXML
    private void tableDicSelectionne(MouseEvent event) {
        btn_emprunter_d.setDisable(true);
        Document docselected = tableudictionnaire.getSelectionModel().getSelectedItem();

        if (docselected == null) {
            btn_emprunter_d.setDisable(true);
        } else {
            try {
                dicEmpr = DocumentBD.getDocByIsbn(docselected.getISBN());
                btn_emprunter_d.setDisable(false);
            } catch (SQLException | NullPointerException ex) {
                MsgError("le system ne trouve pas le Document");
            }
        }

    }

    /**
     * methode pour emprunter un magazine deja selectionée
     * @param event 
     */
    @FXML
    private void emprunterMag(ActionEvent event) {
        Membre memo = null;
        try {
            if (txt_mag_mem_code.getText().isEmpty()) {
                MsgError("Il faut entrer le code de membre..");
            } else {
                Utilitaire.isValideID(txt_mag_mem_code.getText());
                memo = MembreBD.getMembreByIdentity(txt_mag_mem_code.getText());

                if (memo.getCode_membre() != 0) {
                    if (memo.getNbr_eprunter() < 3) {
                        int test = DocumentBD.docEmprunter(magEmpr, txt_mag_mem_code.getText());
                        if (test > 0) {
                            MsgInfo("Done,Operation Reussie");
                            tableMag();
                            txt_mag_mem_code.clear();
                            btn_emprunter_m.setDisable(true);
                        } else {
                            MsgError("Desolé ,Operation Echoée");
                            txt_mag_mem_code.clear();
                            btn_emprunter_m.setDisable(true);
                        }
                    } else {
                        MsgInfo("Vous avez deja depasser le nombre maximal");
                        btn_emprunter_m.setDisable(true);
                        txt_mag_mem_code.clear();
                    }
                } else {
                    MsgError("Membre inexist..");
                }
            }
        } catch (NullPointerException ex) {
            MsgError("il faut choisir un document.");
            btn_emprunter_m.setDisable(true);
        } catch (SQLException ex) {
            MsgError(ex.getMessage());
            btn_emprunter_m.setDisable(true);
        } catch (InvalidIdException ex) {
            btn_emprunter_m.setDisable(true);
        }
    }

    
    /**
     * methode pour emprunter un dictionnaire deja selectionée.
     * @param event 
     */
    @FXML
    private void emprunterDic(ActionEvent event) {
        Membre memo = null;
        try {
            if (txt_dic_mem_code.getText().isEmpty()) {
                MsgError("Il faut entrer le code de membre..");
            } else {
                Utilitaire.isValideID(txt_dic_mem_code.getText());
                memo = MembreBD.getMembreByIdentity(txt_dic_mem_code.getText());
                System.out.println(String.valueOf(memo.getCode_membre()));
                if (memo.getCode_membre() != 0) {
                    if (memo.getNbr_eprunter() < 3) {
                        int test = DocumentBD.docEmprunter(dicEmpr, txt_dic_mem_code.getText());
                        if (test > 0) {
                            MsgInfo("Done,Operation Reussie");
                            tableDic();
                            txt_dic_mem_code.clear();
                            btn_emprunter_d.setDisable(true);
                        } else {
                            MsgError("Desolé ,Operation Echoée");
                            txt_dic_mem_code.clear();
                            btn_emprunter_d.setDisable(true);
                        }
                    } else {
                        MsgInfo("Vous avez deja depasser le nombre maximal");
                        btn_emprunter_d.setDisable(true);
                        txt_dic_mem_code.clear();
                    }
                } else {
                    MsgError("Membre inexist..");
                }
            }
        } catch (NullPointerException ex) {
            MsgError("il faut choisir un document.");
            btn_emprunter_d.setDisable(true);
        } catch (SQLException ex) {
            MsgError(ex.getMessage());
            btn_emprunter_d.setDisable(true);
        } catch (InvalidIdException ex) {
            btn_emprunter_d.setDisable(true);
        }
    }

    /**
     * methode pour emprunter un livre deja selectionée
     * @param event 
     */
    @FXML
    private void emprunterLivre(ActionEvent event) {
        Membre memo = null;
        try {
            if (txt_livre_mem_code.getText().isEmpty()) {
                MsgError("Il faut entrer le code de membre..");
            } else {
                Utilitaire.isValideID(txt_livre_mem_code.getText());
                memo = MembreBD.getMembreByIdentity(txt_livre_mem_code.getText());

                if (memo.getCode_membre() != 0) {
                    if (memo.getNbr_eprunter() < 3) {
                        int test = DocumentBD.docEmprunter(livreEmpr, txt_livre_mem_code.getText());

                        if (test > 0) {
                            MsgInfo("Done,Operation Reussie");
                            tableLivre();
                            txt_livre_mem_code.clear();
                            btn_emprunter_l.setDisable(true);
                        } else {
                            MsgError("Desolé ,Operation Echoée");
                            txt_livre_mem_code.clear();
                            btn_emprunter_l.setDisable(true);
                        }
                    } else {
                        MsgInfo("Vous avez deja depasser le nombre maximal");
                        btn_emprunter_l.setDisable(true);
                        txt_livre_mem_code.clear();
                    }
                } else {
                    MsgError("Membre inexist..");
                }
            }
        } catch (NullPointerException ex) {
            MsgError("il faut choisir un document.");
            btn_emprunter_l.setDisable(true);
        } catch (SQLException ex) {
            MsgError(ex.getMessage());
            btn_emprunter_l.setDisable(true);
        } catch (InvalidIdException ex) {
           btn_emprunter_l.setDisable(true);
        }
    }

    //////////////////////////////////retour////////////////////////////////////////
   /**
    * returner un document deja selectionnée .
    * @param event 
    */
    
    @FXML
    private void retour(ActionEvent event) {

        try {
            int test = DocumentBD.docRetour(docRetour.getNum_enrg(), docRetour.getMembre().getIdentity());
            System.out.println(docRetour.getMembre().getIdentity());
            if (test > 0) {
                MsgInfo("Le Document : " + docRetour.getTitre() + " de Membre :" + docRetour.getMembre().getIdentity() + " ,est bien Retournée ");
                tableHistorique();
                totalDocStatistique();

            } else {
                MsgError("Il y un Error ,Operation Echoée..");

            }
        } catch (SQLException ex) {
            MsgError(ex.getMessage());
        }

    }


}
