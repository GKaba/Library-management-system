package modele;

import java.util.List;

public class Livre extends Document{
	private int nbrpage;
	private String type;
	
	
	public Livre( String iSBN, String titre, String editeur, int annee,
			 int nbrpage, String type,int nombreCopie) {
		super( iSBN, titre, editeur, annee, nombreCopie);
		this.nbrpage = nbrpage;
		this.type = type;
		
	}
	
	public Livre( String iSBN, String titre, String editeur, int annee,
			 int nbrpage, String type,int nombreCopie,String auteur1,String auteur2,String auteur3,String auteur4) {
		
		super( iSBN, titre, editeur, annee, nombreCopie, auteur1,auteur2, auteur3,auteur4);
		this.nbrpage = nbrpage;
		this.type = type;
		
	}

	public Livre() {
		super();
		
	}

	public int getNbrpage() {
		return nbrpage;
	}

	public void setNbrpage(int nbrpage) {
		this.nbrpage = nbrpage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	@Override
	public int getNumeroEnrg() {
		return numeroEnrg;
	}
	@Override
	public void setNumeroEnrg(int numeroEnrg) {
		this.numeroEnrg = numeroEnrg;
	}

        
        
}
