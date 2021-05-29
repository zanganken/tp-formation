package fr.eni.suiviDesRepas.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import fr.eni.suiviDesRepas.bo.Repas;

public class RepasDAOJdbcImpl implements RepasDAO {
	private final static String insertRepasSQL = "INSERT INTO repas (datetime) VALUES (?)";
	private final static String insertAlimentSQL = "INSERT INTO aliments (nom, idRepas) VALUES (?, ?)";
	private final static String selectAllSQL = "SELECT * FROM repas r LEFT JOIN aliments a ON r.id = a.idRepas ORDER BY datetime DESC";

	@Override
	public void insert(Repas t) throws DALException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Integer idRepas = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			stmt = cnx.prepareStatement(insertRepasSQL, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, t.getDateTime().toString());
			
			if(stmt.executeUpdate() > 0) {
				rs = stmt.getGeneratedKeys();
				
				if(rs.next()) {
					idRepas = rs.getInt(1);
					
					rs.close();
				}
			}
			
			if(idRepas != null) {
				for(String aliment : t.getAliments()) {
					stmt = cnx.prepareStatement(insertAlimentSQL);
					
					stmt.setString(1, aliment);
					stmt.setInt(2, idRepas);
					
					stmt.executeUpdate();
				}
			}
			
			stmt.close();
		} catch (SQLException e) {
			throw new DALException("Échec de la méthode insert - "+ t, e);
		}
	}

	@Override
	public Map<Integer, Repas> selectAll() throws DALException {
		Map<Integer, Repas> mapRepas = new LinkedHashMap<Integer, Repas>();
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(selectAllSQL);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String aliment = rs.getString("nom");
				
				if(mapRepas.containsKey(id)) {
					mapRepas.get(id).addAliment(aliment);
				} else {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					
					LocalDateTime dateTime = LocalDateTime.parse(rs.getString("datetime"), formatter);
					List<String> aliments = new ArrayList<String>(1);
					aliments.add(aliment);
					
					mapRepas.put(id, new Repas(dateTime, aliments));
				}
			}
		} catch (SQLException e) {
			throw new DALException("Échec de la méthode selectAll - ", e);
		}
		
		return mapRepas;
	}
}
