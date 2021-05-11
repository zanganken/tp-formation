package fr.eni.papeterie.bo;

public abstract class Article {
	private Integer idArticle;
	private String reference, marque, designation;
	private float prixUnitaire;
	private int qteStock;

	public Article(Integer idArticle, String marque, String reference, String designation, float prixUnitaire,
			int qteStock) {
		this.idArticle = idArticle;
		this.reference = reference;
		this.marque = marque;
		this.designation = designation;
		this.prixUnitaire = prixUnitaire;
		this.qteStock = qteStock;
	}
	
	public Article(String marque, String reference, String designation, float prixUnitaire, int qteStock) {
		this.marque = marque;
		this.reference = reference;
		this.designation = designation;
		this.prixUnitaire = prixUnitaire;
		this.qteStock = qteStock;
	}

	public Integer getIdArticle() {
		return idArticle;
	}

	public String getReference() {
		return reference;
	}

	public String getMarque() {
		return marque;
	}

	public String getDesignation() {
		return designation;
	}

	public float getPrixUnitaire() {
		return prixUnitaire;
	}

	public int getQteStock() {
		return qteStock;
	}

	@Override
	public String toString() {
		return "Article [idArticle=" + idArticle + ", reference=" + reference + ", marque=" + marque + ", designation="
				+ designation + ", prixUnitaire=" + prixUnitaire + ", qteStock=" + qteStock + "]";
	}
}
