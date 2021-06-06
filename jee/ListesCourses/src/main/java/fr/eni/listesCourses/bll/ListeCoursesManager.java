package fr.eni.listesCourses.bll;

import java.util.List;

import fr.eni.listesCourses.bo.Article;
import fr.eni.listesCourses.bo.ListeCourses;
import fr.eni.listesCourses.dal.DALException;
import fr.eni.listesCourses.dal.DAOFactory;
import fr.eni.listesCourses.dal.ListeCoursesDAO;

public class ListeCoursesManager {
	private static ListeCoursesManager lcMgr;
	
	private ListeCoursesDAO listeCoursesDAO;
	
	private ListeCoursesManager() {
		listeCoursesDAO = DAOFactory.getListeCoursesDAO();
	}
	
	public static ListeCoursesManager getInstance() {
		if (lcMgr == null) {
			lcMgr = new ListeCoursesManager();
		}

		return lcMgr;
	}

	public ListeCourses getOne(Integer id) throws DALException {
		return listeCoursesDAO.selectById(id);
	}
	
	public List<ListeCourses> getAll() throws DALException {
		return listeCoursesDAO.selectAll();
	}
	
	public ListeCourses addArticle(Article article, ListeCourses lc) throws DALException, BLLException {
		if(article == null || article.getNom().isEmpty() || article.getQte() <= 0) {
			throw new BLLException("L'article à ajouter ne peut pas être vide -");
		}
		if(lc == null || lc.getNom().isEmpty()) {
			throw new BLLException("La liste doit forcément avoir un nom -");
		}
		
		return listeCoursesDAO.insert(article, lc);
	}

	public void removeListeCourses(ListeCourses lc) throws DALException, BLLException {
		if(lc == null || lc.getId() == null) {
			throw new BLLException("La liste ciblée est incorrecte -");
		}
		
		listeCoursesDAO.delete(lc);
	}
	
	public void removeArticle(String article, ListeCourses lc) throws DALException, BLLException {
		if(article == null || article.isEmpty()) {
			throw new BLLException("L'article à supprimer ne peut pas être vide -");
		}
		if(lc == null || lc.getId() == null) {
			throw new BLLException("La liste ciblée est incorrecte -");
		}
		
		listeCoursesDAO.delete(article, lc);
	}
}
