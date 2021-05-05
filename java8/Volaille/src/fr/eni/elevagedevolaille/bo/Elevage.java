package fr.eni.elevagedevolaille.bo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * @author Zanganken
 *
 */
public class Elevage {
	private ArrayList<Volaille> volailles = new ArrayList<Volaille>();
	
	/**
	 * @param v : Volaille (Le ziozio à ajouter à l'élevage)
	 */
	public void ajouter(Volaille v) {
		volailles.add(v);
		
		// Tri des volailles en créant une instance de SortVolailles()
		Collections.sort(volailles, new SortVolailles());
	}
	
	/**
	 * @param id : int
	 * @param poids : double (en kg)
	 */
	public void changerPoids(int id, double poids) {
		for(Volaille v : volailles) {
			if(v.getId() == id) {
				v.changerPoids(poids);
			}
		}
	}
	
	/**
	 * @return Le prix total des volailles à abattre
	 */
	public double calculerPrixVolaillesAAbattre() {
		double total = 0;
		
		// Parcours l'ArrayList<Volaille> et ajoute le prix au total si la volaille est assez grosse
		for(Volaille v : volailles) {
			if(v.assezGrosse()) {
				total += v.prix();
			}
		}
		
		return total;
	}
	
	/**
	 * @return Le tableau des volailles à abattre
	 */
	public Volaille[] envoyerALAbattoir() {
		ArrayList<Volaille> aAbattre = new ArrayList<>();
		Iterator<Volaille> itr = volailles.iterator();
		
		// On parcours chaque itération de volailles que l'on assigne à v
		while(itr.hasNext()) {
			Volaille v = itr.next();
			
			// Si la volaille est assez grosse, on l'ajoute à la liste aAbattre et on la supprime de la liste volailles
			if(v.assezGrosse()) {
				aAbattre.add(v);
				itr.remove();
			}
		}
		
		// On convertit la liste en array pour respecter le type attendu dans MainVolaille
		return aAbattre.toArray(new Volaille[aAbattre.size()]);
	}
	
	/*public Volaille[] envoyerALAbattoir() {
		ArrayList<Volaille> aAbattre = new ArrayList<>();
		
		for(Volaille v : volailles) {
			if(v.assezGrosse()) {
				aAbattre.add(v);
			}
		}
		
		for(Volaille v : aAbattre) {
			volailles.remove(v);
		}
		
		return aAbattre.toArray(new Volaille[aAbattre.size()]);
	}*/
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		for(Volaille v : volailles) {
			str.append(String.format("%s%n", v));
		}
		
		return str.toString();
	}
}
