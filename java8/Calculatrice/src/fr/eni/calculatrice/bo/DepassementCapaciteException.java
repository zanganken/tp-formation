package fr.eni.calculatrice.bo;

/**
 * Démo de création d'une classe fille de Exception
 * @author Zanganken
 *
 */
public class DepassementCapaciteException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public DepassementCapaciteException() {
		super("Le résultat dépasse les capacités de la calculatrice");
	}
}
