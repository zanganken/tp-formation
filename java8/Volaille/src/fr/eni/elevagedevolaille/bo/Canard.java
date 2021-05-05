package fr.eni.elevagedevolaille.bo;

/**
 * @author Zanganken
 *
 */
public class Canard extends Volaille {
	private static double prixKilo = 23;
	
	/**
	 * @param poids : double (en kg)
	 * @param id : int
	 */
	public Canard(double poids, int id) {
		super(poids, id);
	}
	
	/**
	 * @param prix : double (en €)
	 */
	public static void changerPrix(double prix) {
		Canard.prixKilo = prix;
	}
	
	/**
	 * @return Le résultat du poids en kg par le prix du kilo
	 */
	@Override
	public double prix() {
		return prixKilo * poids;
	}
}
