package fr.eni.location.bo;

import java.time.LocalDate;

public class Gyroroue extends CycleElectrique {
	protected static double tarif = 18.9;
	
	public Gyroroue(LocalDate dateAchat, String marque, String modele, int autonomieKm) {
		super(dateAchat, marque, modele, autonomieKm);
	}
	
	@Override
	public double getTarif() {
		return Gyroroue.tarif;
	}
}
