package fr.eni.papeterie.ihm.ecrCatalogue;

import javax.swing.JTable;

public class TableCatalogue extends JTable {
	private static final long serialVersionUID = 6685289445443590380L;

	public TableCatalogue() {
		super(new TableCatalogueModel());
		
		this.getColumnModel().getColumn(0).setCellRenderer(new TypeCellRenderer());
	}
}