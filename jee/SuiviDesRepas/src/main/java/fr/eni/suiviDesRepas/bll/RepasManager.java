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
	
	public Repas add(Map<String, String[]> params) {
		Repas repas = null;
		
		if(params.containsKey("date") && params.containsKey("heure") && params.containsKey("repas")) {
			String dateStr = params.get("date")[0];
			String heureStr = params.get("heure")[0];
			String repasStr = params.get("repas")[0];
			
			try {
				LocalDateTime dateTime = LocalDateTime.of(LocalDate.parse(dateStr), LocalTime.parse(heureStr));
				
				List<String> aliments = new ArrayList<String>();
				String[] alimentsArr = repasStr.split(",");
				
				for(String aliment : alimentsArr) {
					aliments.add(aliment.trim());
				}
				
				repas = new Repas(dateTime, aliments);
				
				repasDAO.insert(repas);
			} catch(DateTimeParseException | DALException e) {
				e.printStackTrace();
			}
		}
		
		return repas;
	}

	public Map<Integer, Repas> getAllRepas() {
		Map<Integer, Repas> repas = new Hashtable<Integer, Repas>();
		
		try {
			repas = repasDAO.selectAll();
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		return repas;
	}
}
