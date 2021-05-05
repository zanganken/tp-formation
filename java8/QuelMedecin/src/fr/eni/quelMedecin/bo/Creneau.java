package fr.eni.quelMedecin.bo;

import java.time.LocalTime;

public class Creneau {
	private LocalTime debut;
	private int duree;
	private MedecinGeneraliste medecin;
	/**
	 * @param debut : LocalTime
	 * @param duree : int (en minutes)
	 * @param medecin : MedecinGeneraliste
	 */
	public Creneau(LocalTime debut, int duree, MedecinGeneraliste medecin) {
		this.debut = debut;
		this.duree = duree;
		this.medecin = medecin;
		
		medecin.ajouterCreneau(this);
	}
	
	public MedecinGeneraliste getMedecin() {
		return medecin;
	}
	
	public String toString() {
		return String.format("%s - %s (%d minutes)",
				debut,
				debut.plusMinutes(duree),
				duree);
	}
	
	public void afficher() {
		System.out.println(this);
	}
}
