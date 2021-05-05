package fr.eni.location.bo;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Test sur les relations unidirectionnelles et bidirectionnelles
 * @author Zanganken
 *
 */
public abstract class Cycle {
	protected String marque;
	protected String modele;
	protected LocalDate dateAchat;
	
	private static ArrayList<Cycle> cycles = new ArrayList<Cycle>();
	
	/**
	 * @param marque : String
	 * @param modele : String
	 * @param dateAchat : LocalDate
	 */
	protected Cycle(String marque, String modele, LocalDate dateAchat) {
		this.marque = marque;
		this.modele = modele;
		this.dateAchat = dateAchat;
		
		cycles.add(this);
	}
	
	public static String getCycles() {
		StringBuilder str = new StringBuilder();
		
		for(Cycle c : Cycle.cycles) {
			str.append(c)
				.append(String.format(" (%.2f€/heure)%n", c.getTarif()));
		}
		
		return str.toString();
	}
	
	public abstract double getTarif();
	
	@Override
	public String toString() {
		int age = dateAchat.until(LocalDate.now()).getYears();
		
		return String.format("%s %s %s (%d an%s)",
				getClass().getSimpleName(),
				marque,
				modele,
				age,
				age > 1 ? "s" : "");
	}
}
