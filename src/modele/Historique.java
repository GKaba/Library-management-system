package modele;


public class Historique {

		private int id_historique;
                private int num_enrg;
                private Document doc;
                private Membre membre;
                private String ISBN;
                private String titre;
                private int type_doc;
		private String dateE;
		private String dateR;

    public Historique() {
    }

    public Historique(int id_historique, int num_enrg, Document doc, Membre membre, String ISBN, String titre, int type_doc, String dateE, String dateR) {
        this.id_historique = id_historique;
        this.num_enrg = num_enrg;
        this.doc = doc;
        this.membre = membre;
        this.ISBN = ISBN;
        this.titre = titre;
        this.type_doc = type_doc;
        this.dateE = dateE;
        this.dateR = dateR;
    }

    public int getId_historique() {
        return id_historique;
    }

    public void setId_historique(int id_historique) {
        this.id_historique = id_historique;
    }

    public int getNum_enrg() {
        return num_enrg;
    }

    public void setNum_enrg(int num_enrg) {
        this.num_enrg = num_enrg;
    }

    public Document getDoc() {
        return doc;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public String getISBN() {
        return doc.getISBN();
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitre() {
        return doc.getTitre();
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getType_doc() {
        return doc.getType_doc();
    }

    public void setType_doc(int type_doc) {
        this.type_doc = type_doc;
    }

    public String getDateE() {
        return dateE;
    }

    public void setDateE(String dateE) {
        this.dateE = dateE;
    }

    public String getDateR() {
        return dateR;
    }

    public void setDateR(String dateR) {
        this.dateR = dateR;
    }

  
               


		
}
