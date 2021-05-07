package fr.eni.maximot2.bo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Classe générique qui génère et manipule le dictionnaire
 * @author Zanganken
 *
 */
public class Dictionnaire {
	/**
	 * Constante listeMots qui contient les mots récupérés avec la méthode alimenter()
	 */
	private final static List<String> listeMots = Dictionnaire.alimenter();
	
	/**
	 * @return Une liste générée à partir du fichier "dictionnaire.txt"
	 */
	private static List<String> alimenter() {
		List<String> resultat = new ArrayList<String>();
		
		try(FileInputStream fichier = new FileInputStream("./dictionnaire.txt"); Scanner s = new Scanner(fichier)) {
			while(s.hasNextLine()) {
				/*
				 * Pour chaque ligne du fichier, on ajoute la valeur à la liste à retourner
				 */
				resultat.add(s.nextLine().toUpperCase());
			}
		} catch(IOException e) {
			System.err.println("Le dictionnaire n'a pas pu être initialisé.");
		}
		
		return resultat;
	}
	
	/**
	 * @return Un mot au hasard
	 */
	public static String getRandom() {
		Random r = new Random();
		return listeMots.get(r.nextInt(listeMots.size()));
	}
	
	/**
	 * @param mot : String
	 * @return Vrai si le mot en paramètre existe dans le dictionnaire
	 */
	public static boolean verifier(String mot) {
		if(listeMots.indexOf(mot.toUpperCase()) > -1) {
			return true;
		}
		
		System.out.println("Ce mot n'existe pas dans le dictionnaire");
		return false;
	}
}
