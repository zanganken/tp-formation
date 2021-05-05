package fr.eni.reversi.bo;

/**
 * @author Zanganken
 * 
 */
public class Coordonnees {
	private int x;
	private int y;
	private int pionsGagnes = 0;

	public Coordonnees(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Coordonnees(int x, int y, int pionsGagnes) {
		this.x = x;
		this.y = y;
		this.pionsGagnes = pionsGagnes;
	}
	
	public int x() {
		return x;
	}
	
	public int y() {
		return y;
	}
	
	public int getPionsGagnes() {
		return pionsGagnes;
	}

	@Override
	public String toString() {
		return String.format("(x: %d, y: %d)", x(), y());
	}
}
