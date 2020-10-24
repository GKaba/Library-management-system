/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.jfoenix.controls.JFXButton;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.effect.Bloom;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author georg
 */
public class Utilitaire {

    //parametre pour la coordonence pour animer lla fenetre.
    private double initialx, initialy;         

    /**
     * methode pour costumiser les button (Ferme ,Minimize)
     * @param closebtn
     * @param minimisebtn 
     */
    public static void CustomiseWindowButtons(JFXButton closebtn, JFXButton minimisebtn) {
        closebtn.setOnMouseEntered(e -> {
            closebtn.setStyle("-fx-background-color:  #2196f3");
            closebtn.setEffect(new Bloom(0.7));
        });
        closebtn.setOnMouseExited(e -> {
            closebtn.setStyle("-fx-background-color: transparent");
            closebtn.setEffect(new Bloom(1));
        });

        minimisebtn.setOnMouseEntered(e -> {
            minimisebtn.setStyle("-fx-background-color:  #2196f3");
            minimisebtn.setEffect(new Bloom(0.7));
        });
        minimisebtn.setOnMouseExited(e -> {
            minimisebtn.setStyle("-fx-background-color: transparent");
            minimisebtn.setEffect(new Bloom(1));
        });
    }

    /**
     * methode pour animer la fenetre en clique sur la souri.
     * @param pane 
     */
    public void AnimerFenetre(AnchorPane pane) {
        pane.setOnMousePressed(e -> {
            initialx = e.getSceneX();
            initialy = e.getSceneY();
        });
        pane.setOnMouseDragged(e -> {
            Node source = (Node) e.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.setX(e.getScreenX() - initialx);
            stage.setY(e.getScreenY() - initialy);
        });
    }

    // metode pour minimiser la fenetre 
    public void MinimiserFenetre(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.setIconified(true);
    }
// metode pour fermer la fenetre 
    public void FermerFenetre(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    
    //////////////////////////Messages /////////////////
    
    //message d'erreur
    public static void MsgError(String str) {
        Alert fail = new Alert(Alert.AlertType.ERROR);
        fail.setHeaderText("faile");
        fail.setContentText(str);
        fail.showAndWait();
    }
      //message d'information
    public static void MsgInfo(String str) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setContentText(str);
        alert.showAndWait();
    }

    //////////////////////////////Verification ////////////////////////////
    
    
    //method pour verifier si le num de telephone est valide 
    
    public static boolean isValidTel(String tel) throws InvalidTelException {
        Pattern pattern = Pattern.compile("\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}");
        Matcher matcher = pattern.matcher(tel);

        if (!matcher.matches()) {
            throw new InvalidTelException("Format de Numero de telephone non correct : " + tel);
        } else {
            return true;
        }
    }
    //method pour verifier si le obj existe deja

    public static <T> boolean VerefierDoublan(T c, List<T> ls, String msg) throws ExisteException {
        for (T temp : ls) {
            if (c.equals(temp)) {
                throw new ExisteException(msg);
            }
        }
        return true;
    }
    
//method pour verifier si le mot de passe   est valide 
    public static boolean isValideMPass(String pass) throws InvalidMPassException {
        Pattern pattern = Pattern.compile("^(?:(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*)[^\\s]{8,}$");
        Matcher matcher = pattern.matcher(pass);

        if (!matcher.matches()) {
            throw new InvalidMPassException("Format de Mot de Pass  non correct : \n * au moins 8 chiffres.\n"
                    + "* au moins 1 nombre. \n"
                    + "* au moins une minuscule. \n"
                    + "* au moins une majuscule. \n"
                    + "* au moins un caractère spécial. \n"
                    + "* Pas d'espace");
        } else {
            return true;
        }
    }

    ////method pour verifier si le String  est en bon format 
    public static boolean isValideString(String str,String msg) throws InvalidStringException  {
        Pattern pattern = Pattern.compile("^[\\p{L}\\p{M}]+([\\p{L}\\p{Pd}\\p{Zs}'.]*[\\p{L}\\p{M}])+$|^[\\p{L}\\p{M}]+$");
        Matcher matcher = pattern.matcher(str);

        if (!matcher.matches()) {
            throw new InvalidStringException(msg);
        } else {
            return true;
        }
    }
    
    //method pour verifier si le ISBN de doc est valide 
    public static boolean isValideISBN(String str) throws InvalidIsbnException   {
        Pattern pattern = Pattern.compile("^[ISBN]{4}[ ]{0,1}[0-9]{1}[-]{1}[0-9]{3}[-]{1}[0-9]{5}[-]{1}[0-9]{0,1}$");
        Matcher matcher = pattern.matcher(str);

        if (!matcher.matches()) {
            throw new InvalidIsbnException("Format de ISBN non correct :\n ISBN X-XXX-XXXXX-X ");
        } else {
            return true;
        }
    }
    ////method pour verifier si le format est comme ans.
     public static boolean isValideYear(String str) throws InvalidYearException  {
        Pattern pattern = Pattern.compile("^\\d{4}$");
        Matcher matcher = pattern.matcher(str);

        if (!matcher.matches()) {
            throw new InvalidYearException("Format d'Année non correct (YYYY) .");
        } else {
            return true;
        }
    }
    //method pour verifier si format de date est valide 
      public static boolean isValideDate(String str) throws InvalidDAteException    {
        Pattern pattern = Pattern.compile("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$");
        Matcher matcher = pattern.matcher(str);

        if (!matcher.matches()) {
            throw new InvalidDAteException("Format de Date non correct (YYYY-MM-DD) .");
        } else {
            return true;
        }
    }
    
      //method pour verifier si le id est dans le bon format.
       public static boolean isValideID(String str) throws InvalidIdException   {
        Pattern pattern = Pattern.compile("^\\d{7}$");
        Matcher matcher = pattern.matcher(str);

        if (!matcher.matches()) {
            throw new InvalidIdException("ID doit être numérique de 7 chiffre.");
        } else {
            return true;
        }
    }
}





