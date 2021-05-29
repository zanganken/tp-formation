package fr.eni.suiviDesRepas.dal;

public class DAOFactory {
	public static RepasDAO getRepasDAO() {
		return new RepasDAOJdbcImpl();
	}
}
