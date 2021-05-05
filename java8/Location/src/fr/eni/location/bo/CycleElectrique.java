package fr.eni.location.bo;

import java.time.LocalDate;

public abstract class CycleElectrique extends Cycle {
	private int autonomieKm;

	/**
	 * @param marque : String
	 * @param modele : String
	 * @param dateAchat : LocalDate
	 * @param autonomieKm : int
	 */
	public CycleElectrique(LocalDate dateAchat, String marque, String modele, int autonomieKm) {
		super(marque, modele, dateAchat);
		this.autonomieKm = autonomieKm;
	}
	
	@Override
	public String toString() {
		return String.format("%s %d km d'autonomie",
				super.toString(),
				this.autonomieKm);
	}
}
