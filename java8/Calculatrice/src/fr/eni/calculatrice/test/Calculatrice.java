package fr.eni.calculatrice.test;

import java.math.BigInteger;
import java.util.Scanner;

import fr.eni.calculatrice.bo.DepassementCapaciteException;
import fr.eni.calculatrice.bo.Operation;

/**
 * @author Orhan HERSARD
 */
public class Calculatrice {
	private static Scanner s = new Scanner(System.in);
	private static final String OPERATEURS = "+-*/%q";
	
	public static void main(String[] args) {
		int valeur1 = saisirEntier();
		int valeur2;
		int res;
		char operateur;
		
		do {
			operateur = saisirOperateur();
			
			try {
				if(operateur != 'q') {
					res = 0;
					valeur2 = saisirEntier();
					
					switch (operateur) {
					case '+':
						res = Operation.ajouter(valeur1, valeur2);
						break;
					case '-':
						res = Operation.soustraire(valeur1, valeur2);
						break;
					case '*':
						res = Operation.multiplier(valeur1, valeur2);
						break;
					case '/':
						res = valeur1 / valeur2;
						break;
					case '%':
						res = valeur1 % valeur2;
						break;
					}
					
					System.out.printf("%d %s %d = %d%n",
							valeur1,
							operateur,
							valeur2,
							res);
					
					valeur1 = res;
				}
			} catch (DepassementCapaciteException e) {
				System.err.println(e.getMessage());
			} catch (ArithmeticException e) {
				System.err.println("La division par zéro n'est pas définie pour des entiers");
			}
		} while(operateur != 'q');
	}
	
	/**
	 * @return Opérateur saisi
	 */
	private static char saisirOperateur() {
		String saisie;
		boolean isOk = false;
		
		do {
			System.out.println("Opérateur ? (+ - * / % ou q pour quitter)");
			saisie = s.nextLine();
			
			isOk = saisie.length() == 1 &&
					Calculatrice.OPERATEURS.indexOf(saisie.charAt(0)) > -1;
			
			if(!isOk) {
				System.err.println("Opérateur non valide");
			}
		} while(!isOk);
		
		return saisie.charAt(0);
	}

	/**
	 * @return Entier saisi
	 */
	public static int saisirEntier() {
		int entier = 0;
		boolean isOk = false;
		
		System.out.println("Saisir un entier :");
		String entierS = s.nextLine();
		
		do {
			try {
				entier = Integer.parseInt(entierS);
				isOk = true;
			} catch (NumberFormatException e) {
				try {
					new BigInteger(entierS);
					
					System.err.println("La valeur saisie dépasse les capacités de cette calculatrice. Réessayez...");
				} catch (NumberFormatException f) {
					System.err.println("Saisie incorrecte. Réessayez...");
				}
				
				entierS = s.nextLine();
			}
		} while(!isOk);
		
		return entier;
	}
}
