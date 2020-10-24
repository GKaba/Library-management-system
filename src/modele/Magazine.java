package modele;

import java.time.LocalDate;
import java.util.Date;

public class Magazine extends Document{
	private String periode;
	private String date_edit;
	
	
	public Magazine(String iSBN, String titre, String editeur, int annee, int nombreCopie, String periode,
			String date_edit) {
		super(iSBN, titre, editeur, annee, nombreCopie);
		this.periode = periode;
		this.date_edit = date_edit;
	}

	public Magazine(String iSBN, String titre, String editeur, int annee, int nombreCopie, String periode,
			String date_edit,String auteur1,String auteur2,String auteur3,String auteur4) {
		
		super( iSBN, titre, editeur, annee, nombreCopie, auteur1,auteur2, auteur3,auteur4);

		this.periode = periode;
		this.date_edit = date_edit;
	}
	
	
	public Magazine() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Magazine(String iSBN, String titre, String editeur, int annee, int nombreCopie) {
		super(iSBN, titre, editeur, annee, nombreCopie);
		// TODO Auto-generated constructor stub
	}

	public String getPeriode() {
		return periode;
	}


	public void setPeriode(String periode) {
		this.periode = periode;
	}


	public String getDate_edit() {
		return date_edit;
	}


	public void setDate_edit(String date_edit) {
		this.date_edit = date_edit;
	}

		
	
}
