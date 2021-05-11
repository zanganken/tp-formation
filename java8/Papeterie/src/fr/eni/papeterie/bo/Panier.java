package fr.eni.papeterie.bo;

import java.util.ArrayList;
import java.util.List;

public class Panier {
	private float montant = 0f;
	private List<Ligne> lignesPanier = new ArrayList<Ligne>();
	
	public Ligne getLigne(int index) {
		return lignesPanier.get(index);
	}
	
	public List<Ligne> getLignes() {
		return new ArrayList<Ligne>(lignesPanier);
	}
	
	public void addLigne(Article a, int qte) {
		Ligne l = new Ligne(a, qte);
		
		lignesPanier.add(l);
		this.montant += l.getMontant();
	}
	
	public void updateLigne(int index, int newQte) {
		Ligne l = lignesPanier.get(index);
		
		this.montant -= l.getMontant();
		
		l.setQte(newQte);
		
		this.montant += l.getMontant();
	}
	
	public void removeLigne(int index) {
		this.montant -= lignesPanier.get(index).getMontant();
		lignesPanier.remove(index);
	}
	
	public float getMontant() {
		return montant;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("Panier :\n\n");
		
		for(int i = 0; i < lignesPanier.size(); i++) {
			str.append("Ligne "+ i +" "+ lignesPanier.get(i) +"\n");
		}
		
		str.append(String.format("%nValeur du panier : %.2f€", getMontant()));
		
		return str.toString();
	}
}
