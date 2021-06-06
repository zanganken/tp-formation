package fr.eni.listesCourses.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.listesCourses.bo.Article;
import fr.eni.listesCourses.bo.ListeCourses;

public class ListeCoursesDAOJdbcImpl implements ListeCoursesDAO {
	private final static String INSERT_LISTE_SQL = "INSERT INTO listes(nom) VALUES(?)";
	private final static String INSERT_ARTICLE_SQL = "INSERT INTO articles(nom, id_liste, qte) VALUES(?, ?, ?)";
	private final static String DELETE_LISTE_SQL = "DELETE FROM listes WHERE id = ?";
	private final static String DELETE_ARTICLE_SQL = "DELETE FROM articles WHERE nom = ? AND id_liste = ?";
	private final static String SELECT_LISTE_SQL = "SELECT * FROM listes l LEFT JOIN articles a ON l.id = a.id_liste WHERE l.id = ? ORDER BY a.nom ASC";
	private final static String SELECTALL_LISTE_SQL = "SELECT * FROM listes l LEFT JOIN articles a ON l.id = a.id_liste ORDER BY l.id DESC, a.nom ASC";

	@Override
	public ListeCourses insert(Article article, ListeCourses lc) throws DALException {
		PreparedStatement stmt;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			if(lc.getId() == null || selectById(lc.getId()) == null) {
				stmt = cnx.prepareStatement(INSERT_LISTE_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
				
				stmt.setString(1, lc.getNom());
				stmt.executeUpdate();
				
				ResultSet rs = stmt.getGeneratedKeys();
				
				if(rs.next()) {
					// On attribut l'id généré à l'instance
					lc.setId(rs.getInt(1));
				}
				
				rs.close();
			}
			
			stmt = cnx.prepareStatement(INSERT_ARTICLE_SQL);
			
			stmt.setString(1, article.getNom());
			stmt.setInt(2, lc.getId());
			stmt.setInt(3, article.getQte());
			stmt.executeUpdate();
			
			lc.addArticle(article);
			
			stmt.close();
		} catch (SQLException e) {
			throw new DALException("Échec de l'insertion de l'article "+ article +" dans la liste "+ lc.getNom() +" -", e);
		}
		
		return lc;
	}

	@Override
	public void delete(ListeCourses lc) throws DALException {
		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement stmt = cnx.prepareStatement(DELETE_LISTE_SQL)) {
			
			stmt.setInt(1, lc.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Échec de la supression de la liste "+ lc.getNom() +" -", e);
		}
	}
	
	@Override
	public void delete(String article, ListeCourses lc) throws DALException {
		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement stmt = cnx.prepareStatement(DELETE_ARTICLE_SQL)) {

			stmt.setString(1, article);
			stmt.setInt(2, lc.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Échec de la supression de l'article "+ article +" de la liste "+ lc.getNom() +" -", e);
		}
	}

	@Override
	public ListeCourses selectById(Integer id) throws DALException {
		ListeCourses lc = null;
		
		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement stmt = cnx.prepareStatement(SELECT_LISTE_SQL)) {

			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				if(lc == null) {
					String nom = rs.getString("l.nom");
					
					lc = new ListeCourses(id, nom, new ArrayList<Article>());
				}
				
				if(rs.getString("a.nom") != null) lc.addArticle(new Article(rs.getString("a.nom"), rs.getInt("a.qte")));
			}
			
			rs.close();
		} catch (SQLException e) {
			throw new DALException("Échec de la récupération de la liste -", e);
		}
		
		return lc;
	}

	@Override
	public List<ListeCourses> selectAll() throws DALException {
		List<ListeCourses> listesCourses = new ArrayList<ListeCourses>();
		
		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement stmt = cnx.prepareStatement(SELECTALL_LISTE_SQL)) {

			ResultSet rs = stmt.executeQuery();
			
			Integer currentId = null;
			
			while(rs.next()) {
				Integer id = rs.getInt("id");
				Article article = new Article(rs.getString("a.nom"), rs.getInt("a.qte"));
				
				if(id == currentId) {
					ListeCourses currentLC = listesCourses.get(listesCourses.size() - 1);
					
					currentLC.addArticle(article);
				} else {
					currentId = id.intValue();
					
					String nom = rs.getString("l.nom");
					listesCourses.add(new ListeCourses(id, nom, article));
				}
			}
			
			rs.close();
		} catch (SQLException e) {
			throw new DALException("Échec de la récupération des listes -", e);
		}
		
		return listesCourses;
	}
}
