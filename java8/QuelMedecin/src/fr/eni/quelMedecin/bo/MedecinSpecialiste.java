package fr.eni.quelMedecin.bo;

public class MedecinSpecialiste extends MedecinGeneraliste {
	private String specialite;
	private int tarif;
	/**
	 * @param nom
	 * @param prenom
	 * @param numeroDeTelephone
	 * @param adresse
	 * @param specialite
	 * @param tarif
	 */
	public MedecinSpecialiste(String nom, String prenom, String numeroDeTelephone, Adresse adresse, String specialite,
			int tarif) {
		super(nom, prenom, numeroDeTelephone, adresse);
		this.specialite = specialite;
		this.tarif = tarif;
	}
	
	@Override
	public String toString() {
		return String.format("%s%nTéléphone : %s%nSpécialité : %s%nTarif : %d€%nAdresse :%n%s%nCréneaux :%s",
				getNom(),
				getNumeroDeTelephone(),
				specialite,
				tarif,
				adresse,
				getCreneaux());
	}
}
