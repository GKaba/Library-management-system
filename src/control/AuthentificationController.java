/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import Dao.CompteBD;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modele.Compte;
import utils.Utilitaire;

/**
 * FXML Controller class
 *
 * @author georg
 */
public class AuthentificationController extends Utilitaire implements Initializable {
  
    @FXML
    private JFXTextField txtFiledUser;
    @FXML
    private JFXPasswordField txtFiledPass;
    @FXML
    private JFXButton btnConnecter;
    @FXML
    private Label lblDate;
    @FXML
    private JFXButton btnMin;
    @FXML
    private JFXButton btnClose;
    @FXML
    private AnchorPane mainloginpane;
    @FXML
    private Label lblMsg;

    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblDate.setText(new SimpleDateFormat("EEEE, dd / MMMM /yyyy").format(new Date()));
        CustomiseWindowButtons(btnClose, btnMin);
        AnimerFenetre(mainloginpane);

    }

    ///////////////////Acces aux fenetre///////////////////////////
    
    /**
     * methode pour loader la fenetre d'Employee (pass paramete Compte de la personne qui connect ).
     * @param e
     * @param c
     * @throws IOException 
     */
    private void loadMenuBib(ActionEvent e,Compte c) throws IOException {
              try{ 
       FXMLLoader fxmlloader=new FXMLLoader();
       fxmlloader.setLocation(getClass().getResource("/ui/BibMenu.fxml"));
       Parent root=fxmlloader.load();
       BibMenuController bictr=fxmlloader.<BibMenuController>getController();
        
        bictr.CompteConnecte(c); 
        Scene scene=new Scene(root);
        Stage fenetre = (Stage) ((Node) e.getSource()).getScene().getWindow();
        fenetre.setResizable(false);
        fenetre.setScene(scene);
        fenetre.show();
       }
       catch(IOException | NullPointerException ex)
       {
           MsgError("Error ,le systeme ne peut pas ouvrir la Menu.");
       }
        
    }

    /**
     * methode pour loader la fenetre de Admin (pass paramete Compte de la personne qui connect ).
     * @param e
     * @param c
     * @throws IOException 
     */
    private void loadMenuAdm(ActionEvent e,Compte c) throws IOException {
        
       try{ 
       FXMLLoader fxmlloader=new FXMLLoader();
       fxmlloader.setLocation(getClass().getResource("/ui/AdminMenu.fxml"));
       Parent root=fxmlloader.load();
       AdminMenuController adctr=fxmlloader.<AdminMenuController>getController();
        
        adctr.CompteConnecte(c); 
        Scene scene=new Scene(root);
        Stage fenetre = (Stage) ((Node) e.getSource()).getScene().getWindow();
        fenetre.setResizable(false);
        fenetre.setScene(scene);
        fenetre.show();
       }
       catch(IOException | NullPointerException ex)
       {
           MsgError("Error ,le systeme ne peut pas ouvrir la Menu.");
       }
        

    }


    
    
    /////////////////// Se Connecter///////////////////////////
    /**
     * methode pour identifier Utilisateur .
     * @param event 
     */
    
 @FXML
    private void SeConnecter(ActionEvent event) {

        try {
            List<Compte> listCo = CompteBD.getComptes();
            for (Compte c : listCo) {
                if (c.getSurnom().equals(txtFiledUser.getText()) && c.getMot_pass().equals(txtFiledPass.getText()) && c.getTypecompte() == 0) {
                    try {
                        
                        loadMenuAdm(event,c);
                        
                    } catch (IOException ex) {
                        Logger.getLogger(AuthentificationController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if ((c.getSurnom().equals(txtFiledUser.getText()) && c.getMot_pass().equals(txtFiledPass.getText()) && c.getTypecompte() == 1)) {
                    try {
                        
                        loadMenuBib(event,c);
                    } catch (IOException |NullPointerException ex) {
                        MsgError(ex.getMessage());                }
                }
            }
            lblMsg.setText("\"votre nom d'utilisateur ou/et mot de passe est incorrect\"");
        } catch (SQLException ex) {
            MsgError("Désolé, opération échouée . \n SVP redemarez l'application");
        } catch (NullPointerException ex) {
            MsgError("Désolé, opération échouée . \n SVP redemarez l'application");
        }
    }

  

//        Map<String, String> map = new HashMap<>();
//        for (Compte c : listCo) {
//            map.put(c.getSurnom(), c.getMot_pass());
//        }
//        if (map.containsKey(txtFiledUser.getText())) {
//            String pass = map.get(txtFiledUser.getText());
//           if (pass.equals(txtFiledPass.getText())) {
        //enLigne(txtFiledUser.getText());

  
    
    

    
    
}
