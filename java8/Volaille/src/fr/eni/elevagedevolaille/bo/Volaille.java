package fr.eni.elevagedevolaille.bo;

/**
 * Création de classes + méthodes abstraites (fr.eni.elevagedevolaille.test.MainVolaille fourni pour ce TP)
 * @author Zanganken
 *
 */
public abstract class Volaille {
	protected int id;
	protected double poids;
	protected static double poidsAbattage = 1;
	
	/** 
	 * -- CONSTRUCTEUR --
	 * @param id : int
	 * @param poids : double (en kg)
	 */
	protected Volaille(double poids, int id) {
		this.id = id;
		this.poids = poids;
	}
	
	/**
	 * @return L'id de la volaille
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @return Le nom de la classe
	 */
	public String getClassName() {
		return getClass().getSimpleName();
	}
	
	/**
	 * @param poids : double (Changement du poids auquel on décide d'abattre nos volailles)
	 */
	public static void changerPoidsAbattage(double poids) {
		Volaille.poidsAbattage = poids;
	}
	
	/**
	 * @param poids : double (Changement du poids d'une volaille)
	 */
	public void changerPoids(double poids) {
		this.poids = poids;
	}
	
	/**
	 * @return La valeur de la volaille
	 */
	public abstract double prix();
	
	/**
	 * @return Vrai si le poids est supérieur ou égale au poids d'abattage
	 */
	public boolean assezGrosse() {
		return poids >= Volaille.poidsAbattage;
	}

	@Override
	public String toString() {
		return String.format("%s %d, poids : %.3fKg, prix : %.2f€",
				getClassName(),
				id,
				poids,
				prix());
	}
}
