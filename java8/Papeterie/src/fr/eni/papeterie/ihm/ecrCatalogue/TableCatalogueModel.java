package fr.eni.papeterie.ihm.ecrCatalogue;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.papeterie.bll.BLLException;
import fr.eni.papeterie.bll.CatalogueManager;
import fr.eni.papeterie.bo.Article;

public class TableCatalogueModel extends AbstractTableModel {
	private static final long serialVersionUID = 6062007283330303453L;
	
	private List<Article> articles;
	private final String[] entetes = {"", "Référence", "Marque", "Désignation", "Prix unit", "Stock"};
	
	public TableCatalogueModel() {
		super();
		
		try {
			articles = CatalogueManager.getInstance().getCatalogue();
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getColumnCount() {
		return entetes.length;
	}

	@Override
	public int getRowCount() {
		return articles.size();
	}
	
	@Override
	public String getColumnName(int colIndex) {
		return entetes[colIndex];
	}

	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		switch (colIndex) {
		case 0:
			return articles.get(rowIndex).getClass().getSimpleName();
		case 1:
			return articles.get(rowIndex).getReference();
		case 2:
			return articles.get(rowIndex).getMarque();
		case 3:
			return articles.get(rowIndex).getDesignation();
		case 4:
			return articles.get(rowIndex).getPrixUnitaire();
		case 5:
			return articles.get(rowIndex).getQteStock();
		default:
			return null;
		}
	}
}
