package fr.eni.suiviDesRepas.bll;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import fr.eni.suiviDesRepas.bo.Repas;
import fr.eni.suiviDesRepas.dal.DALException;
import fr.eni.suiviDesRepas.dal.DAOFactory;
import fr.eni.suiviDesRepas.dal.RepasDAO;

public class RepasManager {
	private RepasDAO repasDAO;
	
	public RepasManager() {
		repasDAO = DAOFactory.getRepasDAO();
	}
	
	public Integer add(Map<String, String[]> params) throws DALException, BLLException {
		Integer idRepas = null;
		
		if(params.containsKey("date") && params.containsKey("heure") && params.containsKey("repas")) {
			String dateStr = params.get("date")[0];
			String heureStr = params.get("heure")[0];
			String repasStr = params.get("repas")[0];
			
			try {
				LocalDateTime dateTime = LocalDateTime.of(LocalDate.parse(dateStr), LocalTime.parse(heureStr));
				
				if(repasStr.trim().length() == 0) {
					throw new BLLException("Il est nécessaire d'indiquer ce que vous avez mangé -");
				}
				
				List<String> aliments = new ArrayList<String>();
				String[] alimentsArr = repasStr.split(",");
				
				for(String aliment : alimentsArr) {
					aliments.add(aliment.trim());
				}
				
				Repas r = new Repas(dateTime, aliments);
				
				idRepas = repasDAO.insert(r);
			} catch(DateTimeParseException e) {
				throw new BLLException("La date ou l'heure renseignée n'est pas valide -", e);
			}
		}
		
		return idRepas;
	}

	public Map<Integer, Repas> getAllRepas() throws DALException {
		Map<Integer, Repas> repas = new Hashtable<Integer, Repas>();
		
		repas = repasDAO.selectAll();
		
		return repas;
	}
}
