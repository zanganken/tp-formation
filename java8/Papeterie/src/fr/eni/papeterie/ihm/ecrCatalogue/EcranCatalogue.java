package fr.eni.papeterie.ihm.ecrCatalogue;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class EcranCatalogue extends JFrame {
	private static final long serialVersionUID = -2844682068282758656L;
	
	public EcranCatalogue() {
		setTitle("Catalogue articles");
		setLocationRelativeTo(null);
		setSize(new Dimension(400, 400));
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initIHM();
	}

	private void initIHM() {
		JPanel mainContent = new JPanel();
		mainContent.setOpaque(true);
		
		mainContent.setLayout(new GridLayout(1, 0));
		TableCatalogue tblCatalogue = new TableCatalogue();
		
		JScrollPane scrollPane = new JScrollPane(tblCatalogue);
		
		mainContent.add(scrollPane);
		
		setContentPane(mainContent);
	}
}
