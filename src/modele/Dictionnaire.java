package modele;

public class Dictionnaire extends Document {
	private String langue;
	
	
	
	public Dictionnaire(String iSBN, String titre, String editeur, int annee, int nombreCopie, String langue) {
		super(iSBN, titre, editeur, annee, nombreCopie);
		this.langue = langue;
		
	}

	
	public Dictionnaire(String iSBN, String titre, String editeur, int annee, int nombreCopie, String langue,
			String auteur1,String auteur2,String auteur3,String auteur4) {
		super( iSBN, titre, editeur, annee, nombreCopie, auteur1,auteur2, auteur3,auteur4);
		this.langue = langue;
		
		
	}
	

	public Dictionnaire() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Dictionnaire(String iSBN, String titre, String editeur, int annee, int nombreCopie) {
		super(iSBN, titre, editeur, annee, nombreCopie);
		// TODO Auto-generated constructor stub
	}


	public String getLangue() {
		return langue;
	}


	public void setLangue(String langue) {
		this.langue = langue;
	}




}
