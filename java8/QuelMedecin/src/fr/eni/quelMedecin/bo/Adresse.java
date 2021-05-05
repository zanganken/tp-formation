package fr.eni.quelMedecin.bo;

public class Adresse {
	private String complement;
	private int numVoie;
	private String typeVoie;
	private String nomVoie;
	private int cp;
	private String commune;
	/**
	 * CONSTRUCTEUR
	 * @param complement Complément d'adresse : String
	 * @param numVoie Numéro de voie : int
	 * @param typeVoie Type de voie : String
	 * @param nomVoie Nom de la voie : String
	 * @param cp Code postal : int
	 * @param commune Commune : String
	 */
	public Adresse(String complement, int numVoie, String typeVoie, String nomVoie, int cp, String commune) {
		this.complement = complement;
		this.numVoie = numVoie;
		this.typeVoie = typeVoie;
		this.nomVoie = nomVoie;
		this.cp = cp;
		this.commune = commune;
	}
	/**
	 * SURCHAGE CONSTRUCTEUR
	 * @param numVoie Numéro de voie : int
	 * @param typeVoie Type de voie : String
	 * @param nomVoie Nom de la voie : String
	 * @param cp Code postal : int
	 * @param commune Commune : String
	 */
	public Adresse(int numVoie, String typeVoie, String nomVoie, int cp, String commune) {
		this(null, numVoie, typeVoie, nomVoie, cp, commune);
	}
	
	public String toString() {
		return String.format("%s%d%s %s%n%05d %s",
				complement != null ? complement +"\n" : "",
				numVoie,
				typeVoie != null ? typeVoie : "",
				nomVoie,
				cp,
				commune.toUpperCase());
	}
	
	/**
	 * Affiche les informations de l'adresse
	 */
	public void afficher() {
		System.out.println(this);
	}
}
