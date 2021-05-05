package fr.eni.location.bo;

import java.time.LocalDate;

public class Velo extends Cycle {
	private int nbVitesses;
	protected static double tarif = 4.9;

	/**
	 * @param marque : String
	 * @param modele : String
	 * @param dateAchat : LocalDate
	 * @param nbVitesses : int
	 */
	public Velo(LocalDate dateAchat, String marque, String modele, int nbVitesses) {
		super(marque, modele, dateAchat);
		this.nbVitesses = nbVitesses;
	}
	
	@Override
	public double getTarif() {
		return Velo.tarif;
	}
	
	@Override
	public String toString() {
		return String.format("%s %d vitesses",
				super.toString(),
				this.nbVitesses);
	}
}
