/**
 * 
 */
package fr.eni.papeterie.dal.jdbc;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.Driver;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;


/**
 * Implémentation de l'interface Closeable juste pour être stylé de ouf :^)
 * @author Zanganken
 */
public class ArticleDAOJdbcImpl implements Closeable {
	private Connection connection = null;
	
	/**
	 * Instanciation de la connexion à la BDD lors de l'initialisation
	 */
	public ArticleDAOJdbcImpl() {
		try {
			DriverManager.registerDriver(new Driver());
			
			// URL de connexion à MYSQL
			String urlConnection = "jdbc:mysql://127.0.0.1:3306/papeterie_db";
			
			this.connection = DriverManager.getConnection(
					urlConnection,
					DBUser.name, // String username à remplacer par l'identifiant de la base de données
					DBUser.password); // String password à remplacer par le mot de passe de l'utilisateur
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	/**
	 * Ajoute une instance d'article dans la table "articles"
	 * @param a : Article
	 */
	public void insert(Article a) {
		/*
		 * Requête préparée pour l'initialisation de stmt
		 * On insère la couleur ou le grammage en fonction de l'instance de l'article "a"
		 */
		String sql = "INSERT INTO articles (marque, reference, designation, prixUnitaire, qteStock, "+
				(a instanceof Stylo ? "couleur" : "grammage") + // opérateur ternaire
				", type) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		// La déclaration de stmt dans le try gère stmt.close() automatiquement
		try(PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, a.getMarque());
			stmt.setString(2, a.getReference());
			stmt.setString(3, a.getDesignation());
			stmt.setFloat(4, a.getPrixUnitaire());
			stmt.setInt(5, a.getQteStock());
			stmt.setString(7, a.getClass().getSimpleName());
			
			// Obligation de caster "a" pour utiliser les méthodes liées à son instance
			if(a instanceof Stylo) {
				stmt.setString(6, ((Stylo) a).getCouleur());
			} else if(a instanceof Ramette) {
				stmt.setInt(6, ((Ramette) a).getGrammage());
			}
			
			if(stmt.executeUpdate() > 0) {
				try(Statement stmt2 = connection.createStatement()) {
					// On récupère l'id de la dernière ligne insérée dans la base de données papeterie_db
					ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
					
					// On parcours les résultats non nuls (en l'occurence, il n'y a qu'un seul résultat)
					while(rs.next() && !rs.wasNull()) {
						a.setIdArticle(rs.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param idArticle : Integer
	 * @return L'article ayant pour id "idArticle"
	 */
	public Article selectById(Integer idArticle) {
		// On initialise l'article à retourner (la valeur reste null si aucun résultat n'est trouvé)
		Article articleARetourner = null;
		
		// La déclaration de stmt dans le try gère stmt.close() automatiquement
		try(PreparedStatement stmt = connection.prepareStatement("SELECT * FROM articles WHERE idArticle = ?")) {
			stmt.setInt(1, idArticle);
			
			ResultSet rs = stmt.executeQuery();
			
			// On parcours les résultats non nuls (en l'occurence, il n'y a qu'un seul résultat)
			while(rs.next() && !rs.wasNull()) {
				Integer id = rs.getInt("idArticle");
				String marque = rs.getString("marque");
				String ref = rs.getString("reference");
				String designation = rs.getString("designation");
				float pu = rs.getFloat("prixUnitaire");
				int qte = rs.getInt("qteStock");
				String type = rs.getString("type");
				
				// On génère une instance de Stylo ou de Ramette en fonction du type indiqué dans la table
				if(type.equals("Stylo")) {
					String couleur = rs.getString("couleur");
					
					articleARetourner = new Stylo(id, marque, ref, designation, pu, qte, couleur);
				} else {
					int grammage = rs.getInt("grammage");
					
					articleARetourner = new Ramette(id, marque, ref, designation, pu, qte, grammage);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return articleARetourner;
	}
	
	public List<Article> selectAll() {
		List<Article> articles = new ArrayList<Article>();
		
		// La déclaration de stmt dans le try gère stmt.close() automatiquement
		try(Statement stmt = connection.createStatement()) {
			/*
			 * On récupère uniquement les idArticle que l'on va
			 * exploiter avec la méthode selectById créée juste avant
			 */
			ResultSet rs = stmt.executeQuery("SELECT idArticle FROM articles");
			
			// On parcours les résultats non nuls
			while(rs.next() && !rs.wasNull()) {
				// On ajoute à la liste "articles" l'article correspondant à l'idArticle de la base de données
				articles.add(this.selectById(rs.getInt("idArticle")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return articles;
	}
	
	public void update(Article a) {
		/*
		 * Requête préparée pour l'initialisation de stmt
		 * On modifie la couleur ou le grammage en fonction de l'instance de l'article "a"
		 */
		String sql = "UPDATE articles SET marque=?, reference=?, designation=?, prixUnitaire=?, qteStock=?, "+
				(a instanceof Stylo ? "couleur" : "grammage") + // opérateur ternaire
				"=? WHERE idArticle=?";
		
		// La déclaration de stmt dans le try gère stmt.close() automatiquement
		try(PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, a.getMarque());
			stmt.setString(2, a.getReference());
			stmt.setString(3, a.getDesignation());
			stmt.setFloat(4, a.getPrixUnitaire());
			stmt.setInt(5, a.getQteStock());
			stmt.setInt(7, a.getIdArticle());
			
			// Obligation de caster "a" pour utiliser les méthodes liées à son instance
			if(a instanceof Stylo) {
				stmt.setString(6, ((Stylo) a).getCouleur());
			} else if(a instanceof Ramette) {
				stmt.setInt(6, ((Ramette) a).getGrammage());
			}
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param idArticle : Integer (id de l'article à supprimer de la table)
	 */
	public void delete(Integer idArticle) {
		try(PreparedStatement stmt = connection.prepareStatement("DELETE FROM articles WHERE idArticle=?")) {
			stmt.setInt(1, idArticle);
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Fermeture de la connexion si elle existe
	 */
	public void close() {
		if(connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
				System.err.println(e);
			}
		}
	}
}
