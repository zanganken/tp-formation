package fr.eni.papeterie.ihm;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelButtons extends JPanel {
	private static final long serialVersionUID = 1L;

	private InterfacePanelButtonsObserver observer;
	private JButton btnPrev, btnNew, btnSave, btnDel, btnNext;
	
	public PanelButtons() {
		setLayout(new FlowLayout());
		
		add(getBtnPrev());
		add(getBtnNew());
		add(getBtnSave());
		add(getBtnDel());
		add(getBtnNext());
		
		observer = null;
	}
	
	public void addObserver(InterfacePanelButtonsObserver o) {
		observer = o;
	}

	public JButton getBtnPrev() {
		if (btnPrev == null) {
			Icon icon = new ImageIcon("./ressources/Back24.gif");
			btnPrev = new JButton(icon);
			
			btnPrev.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(observer != null) {
						observer.precedent();
					}
				}
			});
		}

		return btnPrev;
	}

	public JButton getBtnNew() {
		if (btnNew == null) {
			Icon icon = new ImageIcon("./ressources/New24.gif");
			btnNew = new JButton(icon);
			
			btnNew.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(observer != null) {
						observer.nouveau();
					}
				}
			});
		}

		return btnNew;
	}

	public JButton getBtnSave() {
		if (btnSave == null) {
			Icon icon = new ImageIcon("./ressources/Save24.gif");
			btnSave = new JButton(icon);
			
			btnSave.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(observer != null) {
						observer.enregistrer();
					}
				}
			});
		}

		return btnSave;
	}

	public JButton getBtnDel() {
		if (btnDel == null) {
			Icon icon = new ImageIcon("./ressources/Delete24.gif");
			btnDel = new JButton(icon);
			
			btnDel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(observer != null) {
						observer.supprimer();
					}
				}
			});
		}

		return btnDel;
	}

	public JButton getBtnNext() {
		if (btnNext == null) {
			Icon icon = new ImageIcon("./ressources/Forward24.gif");
			btnNext = new JButton(icon);
			
			btnNext.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(observer != null) {
						observer.suivant();
					}
				}
			});
		}

		return btnNext;
	}
	
	
}
