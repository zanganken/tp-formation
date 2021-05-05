package fr.eni.reversi.bo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Permet de créer une instance de plateau de jeu de Reversi
 * @author Zanganken
 * 
 */
public class PlateauDeReversi {
	public Pion[][] grille = new Pion[8][8];
	
	/**
	 * Initialisation de la grille de jeu.
	 */
	public PlateauDeReversi() {
		Pion pion;
		
		for(int y = 0; y < grille.length; y++) {
			for(int x = 0; x < grille[y].length; x++) {
				if((y == 3 && x == 3) || (y == 4 && x == 4)) {
					pion = Pion.BLANC;
				} else if((y == 4 && x == 3) || (y == 3 && x == 4)) {
					pion = Pion.NOIR;
				} else {
					pion = Pion.LIBRE;
				}
				
				grille[y][x] = pion;
			}
		}
	}
	
	/**
	 * @param p : Pion
	 * @param x : int (abscisse sur la grille)
	 * @param y : int (ordonnée sur la grille)
	 * @param poser : boolean (Si vrai, tranforme le test en pose)
	 * @return Le nombre de pions qui changeraient de couleur si le joueur choisissait cette case
	 */
	public int tester(Pion p, int x, int y, boolean poser) {
		ArrayList<Coordonnees> total = new ArrayList<Coordonnees>();
		
		if(grille[y][x] == Pion.LIBRE) {
			checkUp(p, x, y).stream().forEachOrdered(total::add);
			checkDown(p, x, y).stream().forEachOrdered(total::add);
			checkRight(p, x, y).stream().forEachOrdered(total::add);
			checkLeft(p, x, y).stream().forEachOrdered(total::add);
			checkUpLeft(p, x, y).stream().forEachOrdered(total::add);
			checkUpRight(p, x, y).stream().forEachOrdered(total::add);
			checkDownRight(p, x, y).stream().forEachOrdered(total::add);
			checkDownLeft(p, x, y).stream().forEachOrdered(total::add);

			if(poser && total.size() > 0) {
				grille[y][x] = p;
				
				for(Coordonnees c : total) {
					grille[c.y()][c.x()] = p;
				}
				
				p.gagne(total.size() + 1);
				p.autrePion().gagne(- total.size());
				
				this.afficher();
				
				System.out.printf("%s joue en (%d, %d)%n%n", p, x+1, y+1);
			}
		}
		
		return total.size();
	}
	
	/**
	 * Parcours la grille et vérifie si le pion peut être joué.
	 * @param p : Pion
	 * @return La liste des coordonnées de placements possibles ainsi que le nombre de pions gagnés par placement
	 */
	public ArrayList<Coordonnees> peutJouer(Pion p) {
		ArrayList<Coordonnees> cList = new ArrayList<Coordonnees>();
		int pionsGagnes;
		
		for(int y = 0; y < grille.length; y++) {
			for(int x = 0; x < grille[y].length; x++) {
				pionsGagnes = this.tester(p, x, y, false);
				if(pionsGagnes > 0) {
					cList.add(new Coordonnees(x, y, pionsGagnes));
				}
			}
		}
		
		return cList;
	}
	
	/**
	 * @param p : Pion
	 * @param x : int (abscisse de la pose sur la grille)
	 * @param y : int (ordonnée de la pose sur la grille)
	 * @return Si la pose est effectuée, retourne vrai
	 */
	public boolean poser(Pion p, int x, int y) {
		return tester(p, x, y, true) > 0;
	}
	
	/**
	 * La méthode qui permet de lancer la partie
	 * @param robotJ1 : boolean (Si vrai, le joueur 1 sera un robot)
	 * @param robotJ2 : boolean (Si vrai, le joueur 2 sera un robot)
	 */
	public void jouer(boolean robotJ1, boolean robotJ2) {
		/*
		 * Les pions noirs commencent toujours au Reversi
		 */
		Pion joueurActuel = Pion.NOIR;
		ArrayList<Coordonnees> coupsPossibles;
		ArrayList<Coordonnees> maxList = new ArrayList<Coordonnees>();
		
		int x = 0;
		int y = 0;
		boolean reboucle;
		int nePeutPasJouer = 0;
		
		Scanner s = new Scanner(System.in);
		
		do {
			System.out.println("Au tour de "+ joueurActuel);
			
			/*
			 * Retourne une liste des coups possibles par le joueur actuel
			 */
			coupsPossibles = peutJouer(joueurActuel);
			
			/*
			 * Si la liste n'est pas vide
			 */
			if(coupsPossibles.size() > 0) {
				// Si le joueur actuel est une intelligence artificielle
				if((robotJ1 && joueurActuel == Pion.NOIR) || (robotJ2 && joueurActuel == Pion.BLANC)) {
					/* ------------------------------------------
					 * CODE DE L'INTELLIGENCE ARTIFICIELLE SIMPLE
					 * ------------------------------------------
					 */
					
					/*
					 * On récupère le nombre maximum de pions qui peuvent être gagnés
					 */
					int max = coupsPossibles.stream()
							.max(Comparator.comparingInt(Coordonnees::getPionsGagnes))
							.get().getPionsGagnes();
					
					/*
					 * On ajoute à maxList chacune des coordonnées possibles pour le nombre maximum de pions gagnés
					 */
					coupsPossibles.stream()
							.filter(c -> c.getPionsGagnes() == max)
							.forEach(maxList::add);
					
					/*
					 * On mélange la liste
					 */
					Collections.shuffle(maxList);
					
					/*
					 * On pose la première entrée de la liste
					 */
					poser(joueurActuel, maxList.get(0).x(), maxList.get(0).y());
					
					maxList = new ArrayList<Coordonnees>();
				} else {
					do {
						do {
							try {
								System.out.print("Quelle colonne ? ");
								x = s.nextInt() - 1;
								
								reboucle = x < 0 || x >= grille[0].length;
							} catch(InputMismatchException e) {
								reboucle = true;
								s = new Scanner(System.in);
							}
							
							if(reboucle) {
								System.out.print("Cette colonne n'existe pas... ");
							}
						} while(reboucle);
						
						do {
							try {
								System.out.print("Quelle ligne ? ");
								y = s.nextInt() - 1;
								
								reboucle = y < 0 || y >= grille.length;
							} catch(InputMismatchException e) {
								reboucle = true;
								s = new Scanner(System.in);
							}
							
							if(reboucle) {
								System.out.print("Cette ligne n'existe pas... ");
							}
						} while(reboucle);
						
						reboucle = !poser(joueurActuel, x, y);
						
						if(reboucle) {
							System.out.printf("%s ne peut pas jouer en (%d, %d). Choisir à nouveau :%n", joueurActuel, x+1, y+1);
						}
					} while(reboucle);
				}
				
				nePeutPasJouer = 0;
			} else {
				/*
				 * S'il n'y a pas de coup possible pour le joueur actuel, on incrémente nePeutPasJouer,
				 * si cette variable est supérieur à 1, plus personne ne peut jouer et on déclare le vainqueur
				 */
				nePeutPasJouer++;
				System.out.println("Mais il n'est pas en mesure de joueur...");
			}
			
			/*
			 * Fin de la boucle, on change de joueur
			 */
			joueurActuel = joueurActuel.autrePion();
		} while(nePeutPasJouer < 2);
		
		Pion gagnant = Pion.BLANC.getNombre() > Pion.NOIR.getNombre() ?
				Pion.BLANC : Pion.NOIR.getNombre() > Pion.BLANC.getNombre() ?
						Pion.NOIR : Pion.LIBRE;
		
		if(gagnant != Pion.LIBRE) {
			System.out.printf("%n%s à gagné avec %d pions sur le plateau !",
				gagnant, gagnant.getNombre());
		} else {
			System.out.printf("%nEgalité totale !");
		}
		
		s.close();
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();

		str.append(String.format("%d %s%n",
				Pion.NOIR.getNombre(),
				Pion.NOIR.getSymbole()));
		
		str.append(String.format("%d %s%n",
				Pion.BLANC.getNombre(),
				Pion.BLANC.getSymbole()));
		
		str.append("  1 2 3 4 5 6 7 8 \n");
		
		for(int y = 0; y < grille.length; y++) {
			str.append((y + 1) + " ");
			
			for(int x = 0; x < grille[y].length; x++) {
				str.append(grille[y][x].getSymbole() + " ");
			}
			
			str.append("\n");
		}
		
		return str.toString();
	}
	
	public void afficher() {
		System.out.println(this);
	}
	
// METHODES POUR PARCOURIR LA GRILLE
	/**
	 * Parcours la grille vers le haut par rapport à la case de coordonnées (x, y)
	 * @param p : Pion
	 * @param x : int (abscisse case de référence)
	 * @param y : int (ordonnée case de référence)
	 * @return La liste des coordonnées des pions gagnés
	 */
	private ArrayList<Coordonnees> checkUp(Pion p, int x, int y) {
		ArrayList<Coordonnees> test = new ArrayList<Coordonnees>();
		Pion pion;
		int coordY;
		
		for(int cases = 1; (y - cases) >= 0; cases++) {
			coordY = y - cases;
			pion = grille[coordY][x];
			
			if(pion == Pion.LIBRE) {
				break;
			} else if(pion == p) {
				return test;
			}
			
			test.add(new Coordonnees(x, coordY));
		}
		
		return new ArrayList<Coordonnees>();
	}
	/**
	 * Parcours la grille vers le bas par rapport à la case de coordonnées (x, y)
	 * @param p : Pion
	 * @param x : int (abscisse case de référence)
	 * @param y : int (ordonnée case de référence)
	 * @return La liste des coordonnées des pions gagnés
	 */
	private ArrayList<Coordonnees> checkDown(Pion p, int x, int y) {
		ArrayList<Coordonnees> test = new ArrayList<Coordonnees>();
		Pion pion;
		int coordY;
		
		for(int cases = 1; (y + cases) < grille.length; cases++) {
			coordY = y + cases;
			pion = grille[coordY][x];

			if(pion == Pion.LIBRE) {
				break;
			} else if(pion == p) {
				return test;
			}
			
			test.add(new Coordonnees(x, coordY));
		}
		
		return new ArrayList<Coordonnees>();
	}
	/**
	 * Parcours la grille vers la droite par rapport à la case de coordonnées (x, y)
	 * @param p : Pion
	 * @param x : int (abscisse case de référence)
	 * @param y : int (ordonnée case de référence)
	 * @return La liste des coordonnées des pions gagnés
	 */
	private ArrayList<Coordonnees> checkRight(Pion p, int x, int y) {
		ArrayList<Coordonnees> test = new ArrayList<Coordonnees>();
		Pion pion;
		int coordX;
		
		for(int cases = 1; (x + cases) < grille[y].length; cases++) {
			coordX = x + cases;
			pion = grille[y][coordX];
			
			if(pion == Pion.LIBRE) {
				break;
			} else if(pion == p) {
				return test;
			}
			
			test.add(new Coordonnees(coordX, y));
		}
		
		return new ArrayList<Coordonnees>();
	}
	/**
	 * Parcours la grille vers la gauche par rapport à la case de coordonnées (x, y)
	 * @param p : Pion
	 * @param x : int (abscisse case de référence)
	 * @param y : int (ordonnée case de référence)
	 * @return La liste des coordonnées des pions gagnés
	 */
	private ArrayList<Coordonnees> checkLeft(Pion p, int x, int y) {
		ArrayList<Coordonnees> test = new ArrayList<Coordonnees>();
		Pion pion;
		int coordX;
		
		for(int cases = 1; (x - cases) >= 0; cases++) {
			coordX = x - cases;
			pion = grille[y][coordX];
			
			if(pion == Pion.LIBRE) {
				break;
			} else if(pion == p) {
				return test;
			}
			
			test.add(new Coordonnees(coordX, y));
		}
		
		return new ArrayList<Coordonnees>();
	}
	/**
	 * Parcours la grille en diagonale vers le haut-gauche par rapport à la case de coordonnées (x, y)
	 * @param p : Pion
	 * @param x : int (abscisse case de référence)
	 * @param y : int (ordonnée case de référence)
	 * @return La liste des coordonnées des pions gagnés
	 */
	private ArrayList<Coordonnees> checkUpLeft(Pion p, int x, int y) {
		ArrayList<Coordonnees> test = new ArrayList<Coordonnees>();
		Pion pion;
		int coordY, coordX;
		
		for(int cases = 1; (y - cases) >= 0 && (x - cases) >= 0; cases++) {
			coordY = y - cases;
			coordX = x - cases;
			pion = grille[coordY][coordX];
			
			if(pion == Pion.LIBRE) {
				break;
			} else if(pion == p) {
				return test;
			}
			
			test.add(new Coordonnees(coordX, coordY));
		}
		
		return new ArrayList<Coordonnees>();
	}
	/**
	 * Parcours la grille en diagonale vers le haut-droite par rapport à la case de coordonnées (x, y)
	 * @param p : Pion
	 * @param x : int (abscisse case de référence)
	 * @param y : int (ordonnée case de référence)
	 * @return La liste des coordonnées des pions gagnés
	 */
	private ArrayList<Coordonnees> checkUpRight(Pion p, int x, int y) {
		ArrayList<Coordonnees> test = new ArrayList<Coordonnees>();
		Pion pion;
		int coordY, coordX;
		
		for(int cases = 1; (y - cases) >= 0 && (x + cases) < grille[y].length; cases++) {
			coordY = y - cases;
			coordX = x + cases;
			pion = grille[coordY][coordX];
			
			if(pion == Pion.LIBRE) {
				break;
			} else if(pion == p) {
				return test;
			}
			
			test.add(new Coordonnees(coordX, coordY));
		}
		
		return new ArrayList<Coordonnees>();
	}
	/**
	 * Parcours la grille en diagonale vers le bas-droite par rapport à la case de coordonnées (x, y)
	 * @param p : Pion
	 * @param x : int (abscisse case de référence)
	 * @param y : int (ordonnée case de référence)
	 * @return La liste des coordonnées des pions gagnés
	 */
	private ArrayList<Coordonnees> checkDownRight(Pion p, int x, int y) {
		ArrayList<Coordonnees> test = new ArrayList<Coordonnees>();
		Pion pion;
		int coordY, coordX;
		
		for(int cases = 1; (y + cases) < grille.length && (x + cases) < grille[y].length; cases++) {
			coordY = y + cases;
			coordX = x + cases;
			pion = grille[coordY][coordX];
			
			if(pion == Pion.LIBRE) {
				break;
			} else if(pion == p) {
				return test;
			}
			
			test.add(new Coordonnees(coordX, coordY));
		}
		
		return new ArrayList<Coordonnees>();
	}
	/**
	 * Parcours la grille en diagonale vers le bas-gauche par rapport à la case de coordonnées (x, y)
	 * @param p : Pion
	 * @param x : int (abscisse case de référence)
	 * @param y : int (ordonnée case de référence)
	 * @return La liste des coordonnées des pions gagnés
	 */
	private ArrayList<Coordonnees> checkDownLeft(Pion p, int x, int y) {
		ArrayList<Coordonnees> test = new ArrayList<Coordonnees>();
		Pion pion;
		int coordY, coordX;
		
		for(int cases = 1; (y + cases) < grille.length && (x - cases) >= 0; cases++) {
			coordY = y + cases;
			coordX = x - cases;
			pion = grille[coordY][coordX];
			
			if(pion == Pion.LIBRE) {
				break;
			} else if(pion == p) {
				return test;
			}
			
			test.add(new Coordonnees(coordX, coordY));
		}
		
		return new ArrayList<Coordonnees>();
	}
}
