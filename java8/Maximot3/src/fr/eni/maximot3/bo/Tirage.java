package fr.eni.maximot3.bo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Classe qui manipule le tirage
 * @author Zanganken
 *
 */
public class Tirage {
	private List<Character> tirage;
	private Map<Integer, List<String>> motsPossibles;
	
	/**
	 * Le constructeur récupère un mot au hasard dans le dictionnaire et le mélange pour créer le tirage
	 */
	public Tirage() {
		this.tirage = melanger(Dictionnaire.getRandom());
		
		this.motsPossibles = fetchMotsPossibles();
	}

	/**
	 * @return Le mot tiré mélangé
	 */
	private List<Character> melanger(String mot) {
		List<Character> resultat = new ArrayList<Character>();
		char[] motArray = mot.toUpperCase().toCharArray();
		
		for(char c : motArray) {
			resultat.add(c);
		}
		
		Collections.shuffle(resultat);
		
		return resultat;
	}
	
	/**
	 * @return Un Map de tous les mots possibles pour ce tirage avec pour clés la longueur des mots
	 */
	private Map<Integer, List<String>> fetchMotsPossibles() {
		Map<Integer, List<String>> resultat = new TreeMap<Integer, List<String>>();
		
		for(String m : Dictionnaire.getList()) {
			if(verifLettres(m)) {
				int key = m.length();
				
				/*
				 * Si la clé n'existe pas, on crée une nouvelle liste pour cette clé
				 */
				if(!resultat.containsKey(key)) {
					resultat.put(key, new ArrayList<String>());
				}
				
				/*
				 * On ajoute la valeur à la liste d'indice key
				 */
				resultat.get(key).add(m);
			}
		}
		
		System.out.println(resultat);

		return resultat;
	}
	
	/**
	 * Affiche la liste des mots possibles par nombre de caractères
	 */
	public void afficherMotsPossibles() {
		/*
		 * On parcours le Map (k: clé, v: motsPossibles.get(k))
		 */
		motsPossibles.forEach((k, v) -> {
			StringBuilder str = new StringBuilder();
			
			str.append(String.format("Mot%s de %d caractères :%n",
					v.size() > 1 ? "s" : "",
					k));
			
			for(String m : v) {
				str.append(String.format("- %s%n", m));
			}
			
			System.out.print(str);
		});
	}

	/**
	 * @param mot : String
	 * @param afficher : boolean (affiche un message si vrai)
	 * @return Vrai si les lettres utilisées dans le mot en paramètre correspondent bien à celles du tirage
	 */
	public boolean verifLettres(String mot, boolean afficher) {
		char[] motArray = mot.toUpperCase().toCharArray();
		int index;
		List<Character> test = new ArrayList<Character>(tirage);
		
		for(char c : motArray) {
			index = test.indexOf(c);
			
			if(index > -1) {
				test.remove(index);
			} else {
				if(afficher) {
					System.out.println("Tirage non respecté");
				}
				
				return false;
			}
		}
		
		return true;
	}
	
	public boolean verifLettres(String mot) {
		return verifLettres(mot, false);
	}
	
	public List<Character> getTirage() {
		return tirage;
	}

	@Override
	public String toString() {
		return tirage.toString();
	}
}
