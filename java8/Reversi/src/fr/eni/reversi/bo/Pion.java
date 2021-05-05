package fr.eni.reversi.bo;

/**
 * @author Zanganken
 * 
 */
public enum Pion {
	LIBRE, BLANC, NOIR;
	
	private int nombre = 2;

	/**
	 * @return Le nombre de pions de la couleur de l'instance sur le plateau
	 */
	public int getNombre() {
		return nombre;
	}

	/**
	 * @param a : char (symbole)
	 * @param b : char (symbole)
	 * @param c : char (symbole)
	 * @return a si le pion est BLANC, b si le pion est NOIR ou c si la case est vide
	 */
	private char ternaireBNV(char a, char b, char c) {
		String name = this.name();
		
		return name == "BLANC" ? a : name == "NOIR" ? b : c;
	}
	/**
	 * @param a : String
	 * @param b : String
	 * @param c : String 
	 * @return a si le pion est BLANC, b si le pion est NOIR ou c si la case est vide
	 */
	private Pion ternaireBNV(Pion a, Pion b, Pion c) {
		return this == Pion.BLANC ? a : this == Pion.NOIR ? b : c;
	}
	
	public char getSymbole() {
		return ternaireBNV('o', '●', '·');
	}
	
	public Pion autrePion() {
		return ternaireBNV(Pion.NOIR, Pion.BLANC, Pion.LIBRE);
	}
	
	public void gagne(int nbPions) {
		this.nombre += nbPions;
	}
}
