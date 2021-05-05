package fr.eni.quelMedecin.bo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RendezVous {
	private Creneau creneau;
	private Patient patient;
	private LocalDate date;
	/**
	 * @param creneau : Creneau
	 * @param patient : Patient
	 * @param date : LocalDate
	 */
	public RendezVous(Creneau creneau, Patient patient, LocalDate date) {
		this.creneau = creneau;
		this.patient = patient;
		this.date = date;
	}
	
	/**
	 * @return la date au format String (exemple: "21 novembre 1992")
	 */
	public String getDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
		return date.format(formatter);
	}
	
	@Override
	public String toString() {
		return String.format("Rendez-vous du %s %s%navec le Dr %s%npour %s",
				getDate(),
				creneau,
				creneau.getMedecin().getNom(),
				patient);
	}
	
	public void afficher() {
		System.out.println(this);
	}
}
