/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author georg
 */
public class InvalidTelException extends Exception {


    public InvalidTelException(String msg) {
        Utilitaire.MsgError(msg);
    }
    
}
