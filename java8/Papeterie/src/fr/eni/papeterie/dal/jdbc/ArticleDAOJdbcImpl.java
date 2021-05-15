package fr.eni.papeterie.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.ArticleDAO;
import fr.eni.papeterie.dal.DALException;

/**
 * @author Zanganken
 */
public class ArticleDAOJdbcImpl implements ArticleDAO {
	private Connection con = null;
	private PreparedStatement rqt = null;
	private ResultSet rs = null;
	
	/**
	 * Ajoute une instance d'article dans la table "articles"
	 * @param a : Article
	 * @throws DALException 
	 */
	public void insert(Article a) throws DALException {
		try {
			this.con = JdbcTools.getConnection();
			
			/*
			 * Requête préparée pour l'initialisation de rqt
			 * On insère la couleur ou le grammage en fonction de l'instance de l'article "a"
			 */
			final String sqlInsert = "INSERT INTO articles (marque, reference, designation, prixUnitaire, qteStock, "+
					(a instanceof Stylo ? "couleur" : "grammage") + // opérateur ternaire
					", type) VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			this.rqt = con.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			
			rqt.setString(1, a.getMarque());
			rqt.setString(2, a.getReference());
			rqt.setString(3, a.getDesignation());
			rqt.setFloat(4, a.getPrixUnitaire());
			rqt.setInt(5, a.getQteStock());
			rqt.setString(7, a.getClass().getSimpleName());
			
			// Obligation de caster "a" pour utiliser les méthodes liées à son instance
			if(a instanceof Stylo) {
				rqt.setString(6, ((Stylo) a).getCouleur());
			} else if(a instanceof Ramette) {
				rqt.setInt(6, ((Ramette) a).getGrammage());
			}
			
			if(rqt.executeUpdate() > 0) {
				// On récupère la clé primaire générée
				this.rs = rqt.getGeneratedKeys();
				
				// Si le résultat existe et qu'il n'est pas null
				if(rs.next() && !rs.wasNull()) {
					a.setIdArticle(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			throw new DALException("Échec de la méthode insert - "+ a, e);
		} finally {
			this.close();
		}
	}
	
	/**
	 * @param idArticle : Integer
	 * @return L'article ayant pour id "idArticle"
	 * @throws DALException 
	 */
	public Article selectById(Integer idArticle) throws DALException {
		// On initialise l'article à retourner (la valeur reste null si aucun résultat n'est trouvé)
		Article articleARetourner = null;
		
		try {
			this.con = JdbcTools.getConnection();
			
			this.rqt = con.prepareStatement("SELECT * FROM articles WHERE idArticle = ?");
			rqt.setInt(1, idArticle);
			
			this.rs = rqt.executeQuery();
			
			if(rs.next() && !rs.wasNull()) {
				// On génère l'article à partir du résultat
				articleARetourner = ArticleDAOJdbcImpl.generateArticle(rs);
			}
		} catch (SQLException e) {
			throw new DALException("Échec de la méthode selectById - idArticle = "+ idArticle, e);
		} finally {
			this.close();
		}
		
		return articleARetourner;
	}
	
	public List<Article> selectAll() throws DALException {
		List<Article> articles = new ArrayList<Article>();
		
		try {
			this.con = JdbcTools.getConnection();
			
			this.rqt = con.prepareStatement("SELECT * FROM articles");
			
			this.rs = rqt.executeQuery();
			
			while(rs.next() && !rs.wasNull()) {
				// On génère l'article à partir du résultat que l'on ajoute à la liste
				articles.add(ArticleDAOJdbcImpl.generateArticle(rs));
			}
		} catch (SQLException e) {
			throw new DALException("Échec de la méthode selectAll", e);
		} finally {
			this.close();
		}
		
		return articles;
	}
	
	public void update(Article a) throws DALException {
		try {
			this.con = JdbcTools.getConnection();
			
			/*
			 * Requête préparée pour l'initialisation de rqt
			 * On modifie la couleur ou le grammage en fonction de l'instance de l'article "a"
			 */
			final String sqlUpdate = "UPDATE articles SET marque=?, reference=?, designation=?, prixUnitaire=?, qteStock=?, "+
					(a instanceof Stylo ? "couleur" : "grammage") + // opérateur ternaire
					"=? WHERE idArticle=?";
			
			this.rqt = con.prepareStatement(sqlUpdate);
			
			rqt.setString(1, a.getMarque());
			rqt.setString(2, a.getReference());
			rqt.setString(3, a.getDesignation());
			rqt.setFloat(4, a.getPrixUnitaire());
			rqt.setInt(5, a.getQteStock());
			rqt.setInt(7, a.getIdArticle());
			
			// Obligation de caster "a" pour utiliser les méthodes liées à son instance
			if(a instanceof Stylo) {
				rqt.setString(6, ((Stylo) a).getCouleur());
			} else if(a instanceof Ramette) {
				rqt.setInt(6, ((Ramette) a).getGrammage());
			}
			
			rqt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Échec de la méthode update - "+ a, e);
		} finally {
			this.close();
		}
	}
	
	/**
	 * @param idArticle : Integer (id de l'article à supprimer de la table)
	 * @throws DALException 
	 */
	public void delete(Integer idArticle) throws DALException {
		try {
			this.con = JdbcTools.getConnection();
			
			this.rqt = con.prepareStatement("DELETE FROM articles WHERE idArticle=?");
			rqt.setInt(1, idArticle);
			
			rqt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Échec de la méthode delete - idArticle = "+ idArticle, e);
		} finally {
			this.close();
		}
	}
	
	/**
	 * Fermeture des connexions, requêtes et résultats si différents de null
	 * @throws DALException 
	 */
	public void close() throws DALException {
		try {
			if(con != null) {
				con.close();
				this.con = null;
			}
			if(rqt != null) {
				rqt.close();
				this.rqt = null;
			}
			if(rs != null) {
				rs.close();
				this.rs = null;
			}
		} catch (SQLException e) {
			throw new DALException("Echec de la fermeture des instances - ", e);
		}
	}
	
	public static Article generateArticle(ResultSet rs) throws SQLException {
		Article articleARetourner;
		
		int id = rs.getInt("idArticle");
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
		
		return articleARetourner;
	}
}
