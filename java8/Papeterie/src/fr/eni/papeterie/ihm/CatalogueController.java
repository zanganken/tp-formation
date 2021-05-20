package fr.eni.papeterie.ihm;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.ihm.ecrCatalogue.EcranCatalogue;
import fr.eni.papeterie.ihm.ecrCatalogue.TableCatalogueModel;

public class CatalogueController {
	private static CatalogueController instance;
	private EcranCatalogue ecrCatalogue;
	private TableCatalogueModel tblModel;
	
	private CatalogueController() {}
	
	public static CatalogueController getInstance() {
		if (instance == null) {
			instance = new CatalogueController();
		}

		return instance;
	}
	
	public void startApp() {
		ecrCatalogue = new EcranCatalogue();
		tblModel = (TableCatalogueModel) ecrCatalogue.getTblCatalogue().getModel();
		
		ecrCatalogue.setVisible(true);
	}
	
	public void updateTable() {
		ecrCatalogue.initIHM();
		ecrCatalogue.setVisible(true);
	}
	
	public void ajouter(Article a) {
		tblModel.ajouter(a);
		updateTable();
	}
	
	public void modifier(int indexCatalogue, Article a) {
		tblModel.modifier(indexCatalogue, a);
		updateTable();
	}

	public void supprimer(int indexCatalogue) {
		tblModel.supprimer(indexCatalogue);
		updateTable();
	}
}
