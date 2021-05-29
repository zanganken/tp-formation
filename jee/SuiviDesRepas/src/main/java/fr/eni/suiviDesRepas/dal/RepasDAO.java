package fr.eni.suiviDesRepas.dal;

import java.util.Map;

import fr.eni.suiviDesRepas.bo.Repas;

public interface RepasDAO {
	Integer insert(Repas r) throws DALException;
	Map<Integer, Repas> selectAll() throws DALException;
}
