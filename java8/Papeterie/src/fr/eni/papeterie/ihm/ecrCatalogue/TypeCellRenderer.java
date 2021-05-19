package fr.eni.papeterie.ihm.ecrCatalogue;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class TypeCellRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 2646478558379654371L;
	
	private Icon styloIcon;
	private Icon rametteIcon;
	
	public TypeCellRenderer() {
		super();

		styloIcon = new ImageIcon("./ressources/pencil.gif");
		rametteIcon = new ImageIcon("./ressources/ramette.gif");
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		setText("");
		
		if(value.equals("Stylo")) {
			setIcon(styloIcon);
		} else {
			setIcon(rametteIcon);
		}
		
		setHorizontalAlignment(SwingConstants.CENTER);
		
		return this;
	}
}