package fr.eni.papeterie.bll;

import java.util.List;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.ArticleDAO;
import fr.eni.papeterie.dal.DALException;
import fr.eni.papeterie.dal.DAOFactory;

public class CatalogueManager {
	private static CatalogueManager instance;
	private ArticleDAO daoArticle;
	
	private CatalogueManager() {
		this.daoArticle = DAOFactory.getArticleDAO();
	};
	
	public static CatalogueManager getInstance() {
		if(CatalogueManager.instance == null) {
			CatalogueManager.instance = new CatalogueManager();
		}
		
		return CatalogueManager.instance;
	}

	public void addArticle(Article a) throws BLLException {
		try {
			validerArticle(a);
			daoArticle.insert(a);
		} catch (DALException e) {
			throw new BLLException("Echec de l'ajout de l'article - "+ a, e);
		}
	}

	public List<Article> getCatalogue() throws BLLException {
		try {
			return daoArticle.selectAll();
		} catch (DALException e) {
			throw new BLLException("Echec de la récupération du catalogue - ", e);
		}
	}

	public void updateArticle(Article a) throws BLLException {
		try {
			validerArticle(a);
			daoArticle.update(a);
		} catch (DALException e) {
			throw new BLLException("Echec de la mise à jour de l'article n° "+ a.getIdArticle() +" - ", e);
		}
	}

	public void removeArticle(Article a) throws BLLException {
		try {
			daoArticle.delete(a.getIdArticle());
		} catch (DALException e) {
			throw new BLLException("Echec de la suppression de l'article n° "+ a.getIdArticle() +" - ", e);
		}
	}
	
	public Article getArticle(Integer id) throws BLLException {
		try {
			return daoArticle.selectById(id);
		} catch (DALException e) {
			throw new BLLException("Echec de la récupération de l'article n° "+ id +" - ", e);
		}
	}
	
	public void validerArticle(Article a) throws BLLException {
		boolean isValid = true;
		StringBuffer sb = new StringBuffer();
		
		if(a == null) {
			throw new BLLException("L'article vaut null");
		}
		//Les attributs des articles sont obligatoires
		if(a.getReference() == null || a.getReference().trim().length() == 0) {
			sb.append("La reference article est obligatoire.\n");
			isValid = false;
		}
		if(a.getMarque() == null || a.getMarque().trim().length() == 0) {
			sb.append("La marque est obligatoire.\n");
			isValid = false;
		}
		if(a.getDesignation() == null || a.getDesignation().trim().length() == 0){
			sb.append("La designation  est obligatoire.\n");
			isValid = false;
		}
		if(a instanceof Ramette && ((Ramette) a).getGrammage () <= 0) {
			sb.append("Le grammage doit avoir une valeur positive.\n");
			isValid = false;
		}
		if(a instanceof Stylo) {
			Stylo s = (Stylo) a;
			
			if(s.getCouleur() == null || s.getCouleur().trim().length() == 0) {
				sb.append("La couleur pour un stylo  est obligatoire.\n");
				isValid = false;
			}
		}
		
		if(!isValid){
			throw new BLLException(sb.toString());
		}
	}
}
