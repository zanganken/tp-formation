package fr.eni.papeterie.dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import fr.eni.papeterie.dal.Settings;

public class JdbcTools {
	private static String dbUrl, dbUser, dbPassword;
	
	static {
		try {
			// Chargement du driver
			Class.forName(Settings.getProperty("jdbcdriver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		dbUrl = Settings.getProperty("url");
		dbUser = Settings.getProperty("user");
		dbPassword = Settings.getProperty("password");
	}
	
	public static Connection getConnection() throws SQLException {
		// Création de la connexion à la base de données
		Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		
		return con;
	}
}
