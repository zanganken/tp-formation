package fr.eni.listesCourses.bo;

public class Article {
	private String nom;
	private Integer qte;
	
	public Article() { }
	public Article(String nom, Integer qte) {
		this.nom = nom;
		this.qte = qte;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public Integer getQte() {
		return qte;
	}
	public void setQte(Integer qte) {
		this.qte = qte;
	}
}
