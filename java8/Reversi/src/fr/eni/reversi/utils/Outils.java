package fr.eni.reversi.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Outils {
	private static Scanner s = new Scanner(System.in);
	
	public static int saisie(String prompt, int min, int max, String error) {
		int saisie = 0;
		boolean continuerBoucle = true;
		
		do {
			try {
				System.out.printf("%s ", prompt);
				saisie = s.nextInt();
				
				continuerBoucle = saisie < min || saisie > max;
			} catch(InputMismatchException e) {
				continuerBoucle = true;
				s = new Scanner(System.in);
			}
			
			if(continuerBoucle) {
				System.out.printf("%s ", error);
			}
		} while(continuerBoucle);
	
		return saisie;
	}
	
	public static int saisie(String prompt, int min, int max) {
		return Outils.saisie(prompt, min, max, "Saisie incorrecte.");
	}
}
