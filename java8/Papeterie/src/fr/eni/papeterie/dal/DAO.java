package fr.eni.papeterie.dal;

import java.util.List;

public interface DAO<T> {
	public void insert(T t) throws DALException;
	public T selectById(Integer id) throws DALException;
	public List<T> selectAll() throws DALException;
	public void update(T t) throws DALException;
	public void delete(Integer id) throws DALException;
	public void close() throws DALException;
}
