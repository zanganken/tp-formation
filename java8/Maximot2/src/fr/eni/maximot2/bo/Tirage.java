package fr.eni.maximot2.bo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe qui manipule le tirage
 * @author Zanganken
 *
 */
public class Tirage {
	private String mot;
	private List<Character> tirage;
	
	/**
	 * Le constructeur récupère un mot au hasard dans le dictionnaire et le mélange pour créer le tirage
	 */
	public Tirage() {
		this.mot = Dictionnaire.getRandom();
		this.tirage = melanger();
	}
	
	/**
	 * @return Le mot tiré mélangé
	 */
	private List<Character> melanger() {
		List<Character> resultat = new ArrayList<Character>();
		char[] motArray = mot.toUpperCase().toCharArray();
		
		for(char c : motArray) {
			resultat.add(c);
		}
		
		Collections.shuffle(resultat);
		
		return resultat;
	}
	
	/**
	 * @return le mot tiré aléatoirement à la création de l'instance
	 */
	public String getMot() {
		return mot;
	}
	
	/**
	 * @param mot : String
	 * @return Vrai si les lettres utilisées dans le mot en paramètre correspondent bien à celles du tirage
	 */
	public boolean verifLettres(String mot) {
		char[] motArray = mot.toUpperCase().toCharArray();
		int index;
		
		for(char c : motArray) {
			index = tirage.indexOf(c);
			
			if(index > -1) {
				tirage.remove(index);
			} else {
				System.out.println("Tirage non respecté");
				return false;
			}
		}
		
		return true;
	}

	@Override
	public String toString() {
		return tirage.toString();
	}
}
