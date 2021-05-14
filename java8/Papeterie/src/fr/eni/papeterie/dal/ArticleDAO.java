package fr.eni.papeterie.dal;

import java.util.List;

import fr.eni.papeterie.bo.Article;

public interface ArticleDAO {
	public void insert(Article a) throws DALException;
	public Article selectById(Integer idArticle) throws DALException;
	public List<Article> selectAll() throws DALException;
	public void update(Article a) throws DALException;
	public void delete(Integer idArticle) throws DALException;
	public void close() throws DALException;
}
