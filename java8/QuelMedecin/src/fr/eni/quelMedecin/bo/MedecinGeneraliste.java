package fr.eni.quelMedecin.bo;

import java.util.ArrayList;
import java.util.List;

public class MedecinGeneraliste extends Personne {
	protected List<Creneau> creneaux = new ArrayList<Creneau>(15);
	private static int tarif = 25;

	/**
	 * @param nom : String
	 * @param prenom : String
	 * @param numeroDeTelephone : String
	 * @param adresse : Adresse
	 */
	public MedecinGeneraliste(String nom, String prenom, String numeroDeTelephone, Adresse adresse) {
		super(nom, prenom, numeroDeTelephone, adresse);
	}
	/**
	 * @return Le tarif
	 */
	public static int getTarif() {
		return tarif;
	}
	/**
	 * @param tarif Le tarif à attribuer au médecin
	 */
	public static void setTarif(int tarif) {
		MedecinGeneraliste.tarif = tarif;
	}
	/**
	 * @param c Le créneau à ajouter à la liste "creneaux"
	 */
	void ajouterCreneau(Creneau c) {
		creneaux.add(c);
	}

	/**
	 * @return La liste des créneaux
	 */
	public String getCreneaux() {
		StringBuilder str = new StringBuilder();
		
		for(Creneau c : creneaux) {
			
			str.append("\n")
				.append(c);
		}
		
		return str.toString();
	}
	
	@Override
	public String toString() {
		return String.format("%s%nTéléphone : %s%nTarif : %d€%nAdresse :%n%s%nCréneaux :%s",
				getNom(),
				getNumeroDeTelephone(),
				MedecinGeneraliste.getTarif(),
				adresse,
				getCreneaux());
	}
	
	/*
	 * Affiche les informations du médecin
	 
	public void afficher() {
		System.out.println(this);
	}*/
}
