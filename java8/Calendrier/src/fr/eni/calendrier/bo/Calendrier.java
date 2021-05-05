package fr.eni.calendrier.bo;

import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Utilisation de la classe GregorianCalendar pour afficher un calendrier
 * @author Zanganken
 *
 */
public class Calendrier {
// VARIABLES
	private GregorianCalendar date;

// CONSTRUCTEURS (surcharge ddes méthodes de construction)
	// Initialise la variable privée date
	public Calendrier(GregorianCalendar pDate) {
		this.date = pDate;
		
		// ça n'a pas trop d'importance mais j'initialise le jour à 1
		this.date.set(GregorianCalendar.DAY_OF_MONTH, 1);
	}
	
	public Calendrier(int y, int m) {
		// (m - 1) pour le mois afin que l'on puisse écrire nos dates comme de vrais humains
		this(new GregorianCalendar(y, m - 1, 1));
	}
	
	public Calendrier(int m) {
		this(new GregorianCalendar().get(GregorianCalendar.YEAR), m);
	}
	
	public Calendrier() {
		// Date du jour
		this(new GregorianCalendar());
	}

// METHODES
	// Renvoit le mois + l'année en texte
	private String afficherMoisAnnee() {
		String str = String.format("* %s %s *",
				date.getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG_FORMAT, Locale.FRANCE),
				date.get(GregorianCalendar.YEAR));
		
		return str;
	}
	
	// Renvoit simplement les jours de la semaine (inutile)
	private String afficherWeekDays() {
		String str = "L  Ma Me J  V  S  D";
		
		return str;
	}
	
	// Renvoit les jours du mois dans le calendrier
	private String afficherJours() {
		StringBuilder str = new StringBuilder();
		
		for(int i = 1; i <= this.date.getActualMaximum(GregorianCalendar.DAY_OF_MONTH); i++) {
			// On crée la variable jour qui permet de faire les opérations dans la boucle
			GregorianCalendar jour = new GregorianCalendar(
					this.date.get(GregorianCalendar.YEAR),
					this.date.get(GregorianCalendar.MONTH),
					i);
			// On calcule la position du jour dans la semaine au format Français (0: lundi, ..., 6: dimanche)
			int positionJour = (jour.get(GregorianCalendar.DAY_OF_WEEK) + 5) % 7;
			
			// Permet de positionner le premier jour du mois dans le calendrier
			if(i == 1) {
				for(int j = 0; j < positionJour; j++) {
					str.append("   ");
				}
			}
			
			str.append(String.format("%02d", i)).append(" ");
			
			if(positionJour == 6) str.append("\n");
		}
		
		return str.toString();
	}
	
	// La méthode d'affichage du calendrier
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append(this.afficherMoisAnnee())
			.append("\n")
			.append(this.afficherWeekDays())
			.append("\n")
			.append(this.afficherJours());
				
		return str.toString();
	}
}
