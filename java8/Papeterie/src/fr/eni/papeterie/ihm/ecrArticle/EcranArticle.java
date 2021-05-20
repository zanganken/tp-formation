package fr.eni.papeterie.ihm.ecrArticle;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.ihm.ArticleController;
import fr.eni.papeterie.ihm.InterfacePanelButtonsObserver;
import fr.eni.papeterie.ihm.PanelButtons;

public class EcranArticle extends JFrame {
	private static final long serialVersionUID = -5999130905071642295L;
	
	private JTextField txtReference, txtDesignation, txtMarque, txtQteStock, txtPrix;
	private JLabel lblReference, lblDesignation, lblMarque, lblQteStock, lblPrix,
		lblType, lblGrammage, lblCouleur;
	private JRadioButton radioRamette, radioStylo, radio80g, radio100g;
	private JComboBox<String> listCouleurs;
	private JPanel radioTypePanel, radioGrammagePanel;
	private PanelButtons btnsPanel;
	
	private Integer currentId;
	private static final String titre = "Papeterie - ";
	
	public EcranArticle() {
		setTitle(titre);
		setLocationRelativeTo(null);
		setSize(new Dimension(500, 400));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		initIHM();
	}
	
	private void initIHM() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(5, 5, 5, 5);
		
		// colonne 1
		gbc.gridx = 0;
		
		gbc.gridy = 0;
		panel.add(getLblReference(), gbc);
		gbc.gridy = 1;
		panel.add(getLblDesignation(), gbc);
		gbc.gridy = 2;
		panel.add(getLblMarque(), gbc);
		gbc.gridy = 3;
		panel.add(getLblQteStock(), gbc);
		gbc.gridy = 4;
		panel.add(getLblPrix(), gbc);
		gbc.gridy = 5;
		panel.add(getLblType(), gbc);
		gbc.gridy = 6;
		panel.add(getLblGrammage(), gbc);
		gbc.gridy = 7;
		panel.add(getLblCouleur(), gbc);
		gbc.gridy = 8;
		gbc.gridwidth = 2;
		panel.add(getBtnsPanel(), gbc);
		
		// colonne 2
		gbc.gridx = 1;
		gbc.gridwidth = 1;
		
		gbc.gridy = 0;
		panel.add(getTxtReference(), gbc);
		gbc.gridy = 1;
		panel.add(getTxtDesignation(), gbc);
		gbc.gridy = 2;
		panel.add(getTxtMarque(), gbc);
		gbc.gridy = 3;
		panel.add(getTxtQteStock(), gbc);
		gbc.gridy = 4;
		panel.add(getTxtPrix(), gbc);
		
		// Groupe radio Type
		gbc.gridy = 5;
		panel.add(getRadioTypePanel(), gbc);
		
		// Groupe radio Grammage
		gbc.gridy = 6;
		panel.add(getRadioGrammagePanel(), gbc);
		
		// ---
		gbc.gridy = 7;
		panel.add(getListCouleurs(), gbc);
		
		
		// lier le panel à l'écran
		this.setContentPane(panel);
	}

	public JTextField getTxtReference() {
		if (txtReference == null) {
			txtReference = new JTextField(30);
			
		}

		return txtReference;
	}

	public JTextField getTxtDesignation() {
		if (txtDesignation == null) {
			txtDesignation = new JTextField(30);
			
		}

		return txtDesignation;
	}

	public JTextField getTxtMarque() {
		if (txtMarque == null) {
			txtMarque = new JTextField(30);
			
		}

		return txtMarque;
	}

	public JTextField getTxtQteStock() {
		if (txtQteStock == null) {
			txtQteStock = new JTextField(30);
			
		}

		return txtQteStock;
	}

	public JTextField getTxtPrix() {
		if (txtPrix == null) {
			txtPrix = new JTextField(30);
			
		}

		return txtPrix;
	}

	public JLabel getLblReference() {
		if (lblReference == null) {
			lblReference = new JLabel("Référence");
			
		}

		return lblReference;
	}

	public JLabel getLblDesignation() {
		if (lblDesignation == null) {
			lblDesignation = new JLabel("Désignation");
			
		}

		return lblDesignation;
	}

	public JLabel getLblMarque() {
		if (lblMarque == null) {
			lblMarque = new JLabel("Marque");
			
		}

		return lblMarque;
	}

	public JLabel getLblQteStock() {
		if (lblQteStock == null) {
			lblQteStock = new JLabel("Stock");
			
		}

		return lblQteStock;
	}

	public JLabel getLblPrix() {
		if (lblPrix == null) {
			lblPrix = new JLabel("Prix");
			
		}

		return lblPrix;
	}

	public JLabel getLblType() {
		if (lblType == null) {
			lblType = new JLabel("Type");
			
		}

		return lblType;
	}

	public JLabel getLblGrammage() {
		if (lblGrammage == null) {
			lblGrammage = new JLabel("Grammage");
			
		}

		return lblGrammage;
	}

	public JLabel getLblCouleur() {
		if (lblCouleur == null) {
			lblCouleur = new JLabel("Couleur");
			
		}

		return lblCouleur;
	}

	public JRadioButton getRadioRamette() {
		if (radioRamette == null) {
			radioRamette = new JRadioButton("Ramette");
			radioRamette.setSelected(true);
		}

		return radioRamette;
	}

	public JRadioButton getRadioStylo() {
		if (radioStylo == null) {
			radioStylo = new JRadioButton("Stylo");
			
		}

		return radioStylo;
	}

	public JRadioButton getRadio80g() {
		if (radio80g == null) {
			radio80g = new JRadioButton("80 grammes");
			radio80g.setSelected(true);
		}

		return radio80g;
	}

	public JRadioButton getRadio100g() {
		if (radio100g == null) {
			radio100g = new JRadioButton("100 grammes");
			
		}

		return radio100g;
	}

	public JComboBox<String> getListCouleurs() {
		if (listCouleurs == null) {
			String[] couleurs = {"bleu", "rouge", "noir", "vert"};
			
			listCouleurs = new JComboBox<String>(couleurs);
		}

		return listCouleurs;
	}

	public PanelButtons getBtnsPanel() {
		if (btnsPanel == null) {
			btnsPanel = new PanelButtons();
			btnsPanel.addObserver(new InterfacePanelButtonsObserver() {
				@Override
				public void supprimer() {
					ArticleController.getInstance().supprimer();
				}
				
				@Override
				public void suivant() {
					ArticleController.getInstance().suivant();
				}
				
				@Override
				public void precedent() {
					ArticleController.getInstance().precedent();
				}
				
				@Override
				public void nouveau() {
					ArticleController.getInstance().nouveau();
				}
				
				@Override
				public void enregistrer() {
					ArticleController.getInstance().enregistrer();
				}
			});
		}

		return btnsPanel;
	}

	public JPanel getRadioTypePanel() {
		if (radioTypePanel == null) {
			radioTypePanel = new JPanel();
			radioTypePanel.setLayout(new BoxLayout(radioTypePanel, BoxLayout.Y_AXIS));
			
			radioTypePanel.add(getRadioRamette());
			radioTypePanel.add(getRadioStylo());

			ButtonGroup bg = new ButtonGroup();
			bg.add(getRadioRamette());
			bg.add(getRadioStylo());
		}

		return radioTypePanel;
	}

	public JPanel getRadioGrammagePanel() {
		if (radioGrammagePanel == null) {
			radioGrammagePanel = new JPanel();
			radioGrammagePanel.setLayout(new BoxLayout(radioGrammagePanel, BoxLayout.Y_AXIS));
			
			radioGrammagePanel.add(getRadio80g());
			radioGrammagePanel.add(getRadio100g());
			
			ButtonGroup bg = new ButtonGroup();
			bg.add(getRadio80g());
			bg.add(getRadio100g());
		}

		return radioGrammagePanel;
	}

	public void afficherArticle(Article a) {
		currentId = a.getIdArticle();
		setTitle(titre + (currentId == null ? "Nouvel article" : "Article n° " + currentId));
		
		txtReference.setText(a.getReference());
		txtDesignation.setText(a.getDesignation());
		txtMarque.setText(a.getMarque());
		txtQteStock.setText(a.getQteStock() + "");
		txtPrix.setText(a.getPrixUnitaire() + "");
		
		if(a instanceof Ramette) {
			getRadio80g().setEnabled(true);
			getRadio100g().setEnabled(true);
			
			getRadioRamette().setSelected(true);
			getRadio80g().setSelected(((Ramette) a).getGrammage() == 80);
			getRadio100g().setSelected(((Ramette) a).getGrammage() == 100);
			getListCouleurs().setSelectedItem(null);
			
			getListCouleurs().setEnabled(currentId == null);
		} else {
			getRadioStylo().setSelected(true);
			getListCouleurs().setEnabled(true);
			
			getListCouleurs().setSelectedItem(((Stylo) a).getCouleur());
			
			getRadio80g().setEnabled(currentId == null);
			getRadio100g().setEnabled(currentId == null);
		}
		
		getRadioRamette().setEnabled(currentId == null);
		getRadioStylo().setEnabled(currentId == null);
	}

	public void afficherNouveau() {
		// Par défaut un article est une rammette
		Ramette a = new Ramette(null, "", "", "", 0.0f, 0, 0);

		afficherArticle(a);
	}
	
	public Article getArticle() {
		Article a;
		
		String refArt = getTxtReference().getText();
		String desArt = getTxtDesignation().getText();
		String marqueArt = getTxtMarque().getText();
		int stockArt = Integer.parseInt(getTxtQteStock().getText());
		float prixArt = Float.parseFloat(getTxtPrix().getText());
		
		if(getRadioStylo().isSelected()) {
			String couleurArt = getListCouleurs().getSelectedItem().toString();
			a = new Stylo(currentId, marqueArt, refArt, desArt, prixArt, stockArt, couleurArt);
		} else {
			int grammageArt = getRadio80g().isSelected() ? 80 : 100;
			a = new Ramette(currentId, marqueArt, refArt, desArt, prixArt, stockArt, grammageArt);
		}
		
		return a;
	}
}
