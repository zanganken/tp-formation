package fr.eni.maximot2.bo;

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
			
			if(score < 10) {
				if(score > 0) {
					System.out.printf("Bien joué, mais le mot à trouver était %s%nScore : %.2f/10",
							tirage.getMot(),
							score);
				} else {
					System.out.printf("Le mot à trouver était : %s%n", tirage.getMot());
				}
			} else {
				System.out.println("Bravo, c'est gagné !");
			}
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
		if(tirage.verifLettres(mot) & Dictionnaire.verifier(mot)) {
			this.score = (float) mot.length() / tirage.getMot().length() * 10;
		}
	}
}
