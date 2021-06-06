package fr.eni.listesCourses.dal;

import java.util.List;

import fr.eni.listesCourses.bo.Article;
import fr.eni.listesCourses.bo.ListeCourses;


public interface ListeCoursesDAO {
	ListeCourses insert(Article article, ListeCourses lc) throws DALException;
	
	void delete(ListeCourses lc) throws DALException;
	void delete(String article, ListeCourses lc) throws DALException;
	
	ListeCourses selectById(Integer id) throws DALException;
	
	List<ListeCourses> selectAll() throws DALException;
}
