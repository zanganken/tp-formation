package fr.eni.papeterie.ihm;

import fr.eni.papeterie.ihm.ecrCatalogue.EcranCatalogue;

public class CatalogueController {
	private static CatalogueController instance;
	private EcranCatalogue ecrCatalogue;
	
	private CatalogueController() {}
	
	public static CatalogueController getInstance() {
		if (instance == null) {
			instance = new CatalogueController();
		}

		return instance;
	}
	
	public void startApp() {
		ecrCatalogue = new EcranCatalogue();
		
		ecrCatalogue.setVisible(true);
	}
}
