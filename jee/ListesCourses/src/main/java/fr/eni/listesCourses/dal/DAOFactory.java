package fr.eni.listesCourses.dal;

public class DAOFactory {
	public static ListeCoursesDAO getListeCoursesDAO() {
		return new ListeCoursesDAOJdbcImpl();
	}
}
