package fr.eni.calculatrice.bo;

/**
 * Classe utilitaire Operation
 * @author Zanganken
 *
 */
public class Operation {
	/**
	 * @param a : int (Valeur de départ) 
	 * @param b : int (Valeur à ajouter)
	 * @return La somme de a + b
	 * @throws DepassementCapaciteException
	 * 	Si le résultat du calcul ne peut être contenu dans un int
	 */
	public static int ajouter(int a, int b) throws DepassementCapaciteException {
		long resLong = (long) a + b;
		int resInt = a + b;
		
		if(resInt != resLong) {
			throw new DepassementCapaciteException();
		}
		
		return resInt;
	}
	
	/**
	 * @param a : int (Valeur de départ) 
	 * @param b : int (Valeur à soustraire)
	 * @return La soustraction de a par b
	 * @throws DepassementCapaciteException
	 * 	Si le résultat du calcul ne peut être contenu dans un int
	 */
	public static int soustraire(int a, int b) throws DepassementCapaciteException {
		return Operation.ajouter(a, -b);
	}
	
	public static int multiplier(int a, int b) throws DepassementCapaciteException {
		long resLong = (long) a * b;
		int resInt = a * b;
		
		if(resInt != resLong) {
			throw new DepassementCapaciteException();
		}
		
		return resInt;
	}
}
