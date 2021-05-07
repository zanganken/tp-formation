package fr.eni.maximot3.bo;

import java.util.Scanner;

/**
 * Classe qui permet de jouer
 * @author Zanganken
 *
 */
public class Maximot {
	private Tirage tirage = new Tirage();
	private double score = 0;
	
	public void jouer() {
		System.out.printf("Voici le tirage : %s%n", tirage);
		System.out.print("A vous de trouver le mot correspondant : ");
		
		try(Scanner s = new Scanner(System.in)) {
			String saisie = s.nextLine();
			
			calculScore(saisie);
			
			if(score > 0) {
				/*
				 * Si le score est un nombre entier, on n'affiche pas les décimales
				 */
				System.out.printf("%s, votre score est de "+ (score % 1 == 0 ? "%.0f" : "%.2f") +"/10%n%n",
						score == 10 ? "Excellent" : "Bravo",
						score);
			}
			
			tirage.afficherMotsPossibles();
		}
	}
	
	/**
	 * Calcul du score sur 10 par rapport au nombre de lettres utilisées vis à vis du nombre de lettres attendues
	 * @param mot : String
	 */
	public void calculScore(String mot) {
		/*
		 * On utilise l'opérateur logique & au lieu de && pour afficher
		 * le message correspondant à chacune des méthodes appelées
		 */
		if(tirage.verifLettres(mot, true) & Dictionnaire.verifier(mot)) {
			this.score = (float) mot.length() / tirage.getTirage().size() * 10;
		}
	}
}
