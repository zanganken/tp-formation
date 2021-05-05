package fr.eni.quelMedecin.bo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Patient extends Personne {
	private char sexe;
	private long numSecu;
	private LocalDate dateDeNaissance;
	private String commentaires;
	/**
	 * @param nom : String
	 * @param prenom : String
	 * @param sexe : char
	 * @param numSecu : long
	 * @param dateDeNaissance : LocalDate
	 * @param commentaires : String
	 * @param adresse : Adresse
	 * @throws Exception si le sexe renseigné n'est pas valide
	 */
	public Patient(String nom, String prenom, String numeroDeTelephone, char sexe, long numSecu, LocalDate dateDeNaissance, String commentaires, Adresse adresse) /*throws Exception*/ {
		super(nom, prenom, numeroDeTelephone, adresse);
		this.setSexe(sexe);
		this.numSecu = numSecu;
		this.setDateDeNaissance(dateDeNaissance);
		this.commentaires = commentaires;
	}
	/**
	 * @return "Masculin" ou "Féminin"
	 */
	public String getSexe() {
		return sexe == 'M' ? "Masculin" : "Féminin";
	}
	/**
	 * @param sexe 'F' ou 'M'
	 * @throws Exception si le sexe renseigné n'est pas valide
	 */
	public void setSexe(char sexe) { // throws Exception {
		//Patient.verifSexe(sexe);
		this.sexe = sexe;
	}
	/**
	 * Vérification d'intégrité de sexe
	 * @param sexe 'F' ou 'M'
	 * @throws Exception si le sexe renseigné n'est pas valide
	 */
	/*private static void verifSexe(char sexe) throws Exception {
		if(sexe != 'F' && sexe != 'M') {
			throw new Exception("Le sexe renseigné n'est pas valide");
		}
	}
	/**
	 * @return Le numéro de sécurité sociale de type long
	 */
	public long getNumSecu() {
		return numSecu;
	}
	/**
	 * @return la date de naissance au format String
	 */
	public String getDateDeNaissance() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
		return dateDeNaissance.format(formatter);
	}
	/**
	 * @param dateDeNaissance the dateDeNaissance to set
	 */
	public void setDateDeNaissance(LocalDate dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}
	/**
	 * surcharge de la méthode setDateDeNaissance
	 * @param y année de naissance
	 * @param m mois de naissance
	 * @param d jour de naissance
	 * @throws Exception si la date n'est pas valide
	 */
	/*public void setDateDeNaissance(int y, int m, int d) throws Exception {
		//Patient.verifDate(y, m, d);
		setDateDeNaissance(LocalDate.of(y, m, d));
	}
	/**
	 * Vérification d'intégrité de la date
	 * @throws Exception si la date n'est pas valide
	 */
	/*private static void verifDate(int y, int m, int d) throws Exception {
		if(m < 1 || m > 12) {
			throw new Exception("Mois de naissance invalide");
		}
		
		if(d < 1 || d > LocalDate.of(y, m, 1).lengthOfMonth()) {
			throw new Exception("Jour de naissance invalide");
		}
	}
	/**
	 * @return the commentaires
	 */
	public String getCommentaires() {
		return commentaires != null ? commentaires : "[aucun commentaire]";
	}
	
	@Override
	public String toString() {
		return String.format("%s%nTéléphone : %s%nSexe : %s%nNuméro de sécurité sociale : %d%nDate de naissance"+
				": %s%nCommentaires : %s%nAdresse :%n%s",
					getNom(),
					getNumeroDeTelephone(),
					getSexe(),
					getNumSecu(),
					getDateDeNaissance(),
					getCommentaires(),
					adresse);
	}

	/*
	 * Affiche les informations du patient
	 
	public void afficher() {
		System.out.println(this);
	}*/
}
