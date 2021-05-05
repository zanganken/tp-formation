package fr.eni.maximot.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Manipulation de la classe FileInputStream
 * @author Zanganken
 *
 */
public class Maximot {
	public static void main(String[] args) {
		// On crée la liste dictionnaire
		List<String> dictionnaire = new ArrayList<String>();

		// On lit le fichier dictionnaire.txt
		try(FileInputStream fichier = new FileInputStream("./dictionnaire.txt");
				Scanner s = new Scanner(fichier)) {
			while(s.hasNextLine()) {
				// Pour chaque ligne, on ajoute la valeur à la liste dictionnaire
				dictionnaire.add(s.nextLine());
			}

			String mot = dictionnaire.get(getRandomInt(dictionnaire.size()));
			List<Character> tirage = melanger(mot);

			System.out.printf("Voici le tirage : %s%n", tirage);
			System.out.print("Trouver le mot le plus long : ");

			try(Scanner sc = new Scanner(System.in)) {
				String saisie = sc.nextLine();

				if(bonnesLettres(saisie.toUpperCase(), tirage)) {
					if(dansLeDico(saisie, dictionnaire)) {
						if(saisie.length() == mot.length()) {
							System.out.println("Bravo, c'est gagné !");
						} else {
							System.out.printf("Bien joué, mais le mot à trouver était : %s%n", mot);
							System.out.printf("Score : %.2f/10", (float)(saisie.length()) / mot.length() * 10);
						}
					} else {
						System.out.println("Désolé mais ce mot n'existe pas dans le dictionnaire");
						System.out.printf("Le mot à trouver était : %s%n", mot);
					}
				} else {
					System.out.println("Les lettres utilisées n'ont pas été respectées");
					System.out.printf("Le mot à trouver était : %s%n", mot);
				}
			}
		} catch(IOException e) {
			System.err.println("Lecture impossible");
		}
	}

	// Renvoit un nombre aléatoire entre 0 et max
	private static int getRandomInt(int max) {
		Random r = new Random();
		return r.nextInt(max + 1);
	}

	private static List<Character> melanger(String s) {
		// On récupère une liste de caractères avec la fonction charToList()
		List<Character> charList = charToList(s.toUpperCase().toCharArray());

		// Collections.shuffle(list) mélange la liste.
		Collections.shuffle(charList);

		return charList;
	}

	// Transforme un tableau char[] en List<Character>
	private static List<Character> charToList(char[] chars) {
		// On crée une liste vide
		List<Character> charList = new ArrayList<Character>();

		for(char c : chars) {
			// on ajoute chaque caractère à la liste
			charList.add(c);
		}

		return charList;
	}

	private static boolean bonnesLettres(String mot, List<Character> tirage) {
		while(mot.length() > 0) {
			// On récupère l'index sur tirage du premier caractère du mot
			int charIndex = tirage.indexOf(mot.charAt(0));

			// Si l'index est supérieur à -1, le caractère existe dans le tirage
			if(charIndex > -1) {
				// On retire la première lettre du mot + la lettre du tirage pour continuer la boucle
				mot = mot.substring(1);
				tirage.remove(charIndex);
			} else {
				return false;
			}
		}

		return true;
	}

	private static boolean dansLeDico(String mot, List<String> dictionnaire) {
		if(dictionnaire.indexOf(mot.toLowerCase()) > -1)
			return true;
		else
			return false;
	}
}
