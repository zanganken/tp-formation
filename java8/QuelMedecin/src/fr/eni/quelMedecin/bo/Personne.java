package fr.eni.quelMedecin.bo;

public class Personne {
	protected String nom;
	protected String prenom;
	protected String numeroDeTelephone;
	protected Adresse adresse;
	/**
	 * @param nom : String
	 * @param prenom : String
	 * @param numeroDeTelephone : String
	 * @param adresse : Adresse
	 */
	public Personne(String nom, String prenom, String numeroDeTelephone, Adresse adresse) {
		this.nom = nom;
		this.prenom = prenom;
		this.numeroDeTelephone = numeroDeTelephone;
		this.adresse = adresse;
	}
	/**
	 * @return NOM Prénom
	 */
	public String getNom() {
		return nom.toUpperCase() +" "+ prenom;
	}
	/**
	 * @return Le numéro de téléphone
	 */
	public String getNumeroDeTelephone() {
		return numeroDeTelephone;
	}
	
	public void afficher() {
		System.out.println(this);
	}
}
