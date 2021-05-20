package fr.eni.papeterie.ihm;

import java.util.List;

import fr.eni.papeterie.bll.BLLException;
import fr.eni.papeterie.bll.CatalogueManager;
import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.ihm.ecrArticle.EcranArticle;

public class ArticleController {
	private static ArticleController instance;
	
	private EcranArticle ecrArticle;
	private CatalogueManager mger;
	private List<Article> catalogue;
	private CatalogueController catalogueCtrl;
	
	private int indexCatalogue;
	
	private ArticleController() {
		mger = CatalogueManager.getInstance();
		catalogueCtrl = CatalogueController.getInstance();
		
		try {
			catalogue = mger.getCatalogue();
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized ArticleController getInstance() {
		if (instance == null) {
			instance = new ArticleController();
		}

		return instance;
	}
	
	public void startApp() {
		ecrArticle = new EcranArticle();
		
		afficherPremierArticle();
		
		ecrArticle.setVisible(true);
	}
	
	public void afficherPremierArticle() {
		if(catalogue.size() > 0) {
			indexCatalogue = 0;
			ecrArticle.afficherArticle(catalogue.get(indexCatalogue));
		} else {
			indexCatalogue = 0;
			ecrArticle.afficherNouveau();
		}
	}
	
	public void precedent() {
		if(indexCatalogue > 0) {
			indexCatalogue--;
		} else {
			indexCatalogue = catalogue.size() - 1;
		}
		
		if(catalogue.size() > 0) {
			ecrArticle.afficherArticle(catalogue.get(indexCatalogue));
		}
	}
	
	public void suivant() {
		if(indexCatalogue < catalogue.size() - 1) {
			indexCatalogue++;
		} else {
			indexCatalogue = 0;
		}
		
		if(catalogue.size() > 0) {
			ecrArticle.afficherArticle(catalogue.get(indexCatalogue));
		}
	}
	
	public void nouveau() {
		indexCatalogue = catalogue.size();
		ecrArticle.afficherNouveau();
	}
	
	public void supprimer() {
		if(catalogue.size() > 0) {
			try {
				mger.removeArticle(catalogue.get(indexCatalogue));
				catalogue.remove(indexCatalogue);
				catalogueCtrl.supprimer(indexCatalogue);
			} catch (BLLException e) {
				e.printStackTrace();
			}
			
			if(indexCatalogue < catalogue.size()) {
				ecrArticle.afficherArticle(catalogue.get(indexCatalogue));
			} else if(indexCatalogue > 0) {
				indexCatalogue--;
				ecrArticle.afficherArticle(catalogue.get(indexCatalogue));
			} else {
				indexCatalogue--;
				ecrArticle.afficherNouveau();
			}
		}
	}
	
	public void enregistrer() {
		Article a = ecrArticle.getArticle();
		
		try {
			if(a.getIdArticle() == null) {
				mger.addArticle(a);
				catalogue.add(a);
				catalogueCtrl.ajouter(a);
				
				ecrArticle.afficherArticle(a);
			} else {
				mger.updateArticle(a);
				catalogue.set(indexCatalogue, a);
				catalogueCtrl.modifier(indexCatalogue, a);
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}
}
