package modele;

import java.util.Objects;

public  class Membre {
        private int code_membre;
	private String identity;
	private String nom;
	private String prenom;
	private String adresse;
	private String tel;
	private int nbr_eprunter;

    public Membre(int code_membre, String identity, String nom, String prenom, String adresse, String tel, int nbr_eprunter) {
        this.code_membre = code_membre;
        this.identity = identity;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.nbr_eprunter = nbr_eprunter;
    }

    public Membre() {
    }

    public int getCode_membre() {
        return code_membre;
    }

    public void setCode_membre(int code_membre) {
        this.code_membre = code_membre;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getNbr_eprunter() {
        return nbr_eprunter;
    }

    public void setNbr_eprunter(int nbr_eprunter) {
        this.nbr_eprunter = nbr_eprunter;
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
        final Membre other = (Membre) obj;
        if ((this.code_membre == other.code_membre) ||(this.identity.equals(other.identity)) ||(this.tel.equals(other.tel))) {  
        }
        else{
            return false;
        }
            return true;     
        }
       
    }



