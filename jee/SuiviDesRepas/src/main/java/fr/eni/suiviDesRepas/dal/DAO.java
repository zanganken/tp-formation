package fr.eni.suiviDesRepas.dal;

import java.util.Map;

public interface DAO<K, T> {
	void insert(T t) throws DALException;
	//T selectById(Integer id) throws DALException;
	Map<K, T> selectAll() throws DALException;
	//void update(T t) throws DALException;
	//void delete(Integer id) throws DALException;
}
