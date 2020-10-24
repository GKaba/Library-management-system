package modele;

import java.util.Objects;

public class Compte {
        private int id;
	private String surnom;
	private String mot_pass;
        private String nom;
        private String prenom;
	private String telephone;
	private String adresse;
	private int typecompte;
	private int etat;

    public Compte(int id, String surnom, String mot_pass, String nom, String prenom, int typecompte, int etat) {
        this.id = id;
        this.surnom = surnom;
        this.mot_pass = mot_pass;
        this.nom = nom;
        this.prenom = prenom;
        this.typecompte = typecompte;
        this.etat = etat;
    }

    public Compte() {
    }

        
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurnom() {
        return surnom;
    }

    public void setSurnom(String surnom) {
        this.surnom = surnom;
    }

    public String getMot_pass() {
        return mot_pass;
    }

    public void setMot_pass(String mot_pass) {
        this.mot_pass = mot_pass;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getTypecompte() {
        return typecompte;
    }

    public void setTypecompte(int typecompte) {
        this.typecompte = typecompte;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
       
        final Compte other = (Compte) obj;
        if ((this.getSurnom().equals(other.getSurnom())) ||(this.getTelephone().equals(other.getTelephone()))) {
        } else {
            return false;
            }
 
        return true;
    }

   
	



}

