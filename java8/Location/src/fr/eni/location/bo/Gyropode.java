package fr.eni.location.bo;

import java.time.LocalDate;

public class Gyropode extends CycleElectrique {
	private int tailleMinCm;
	protected static double tarif = 29.9;

	/**
	 * @param marque : String
	 * @param modele : String
	 * @param dateAchat : LocalDate
	 * @param autonomieKm : int
	 * @param tailleMinCm : int
	 */
	public Gyropode(LocalDate dateAchat, String marque, String modele, int autonomieKm, int tailleMinCm) {
		super(dateAchat, marque, modele, autonomieKm);
		this.tailleMinCm = tailleMinCm;
	}
	
	@Override
	public double getTarif() {
		return Gyropode.tarif;
	}

	@Override
	public String toString() {
		return String.format("%s [%dm%d min]",
				super.toString(),
				tailleMinCm / 100,
				tailleMinCm % 100);
	}
}
