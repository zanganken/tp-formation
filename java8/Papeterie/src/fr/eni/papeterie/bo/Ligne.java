package fr.eni.papeterie.bo;

public class Ligne {
	private int qte;
	private Article article;

	public Ligne(Article article, int qte) {
		this.article = article;
		this.qte = qte;
	}
	
	public int getQte(int i) {
		return qte;
	}
	public void setQte(int qte) {
		this.qte = qte;
	}

	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	
	public float getMontant() {
		return article.getPrixUnitaire() * qte;
	}

	@Override
	public String toString() {
		return String.format("Ligne [qte=%d, montant=%.2f, article=%s]",
				qte, getMontant(), article);
	}
}
