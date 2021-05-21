package fr.eni.papeterie.dal;

import java.util.List;

import fr.eni.papeterie.bo.Article;

public interface ArticleDAO extends DAO<Article> {
	List<Article> selectByMarque(String marque) throws DALException;
	List<Article> selectByMotCle(String keyword) throws DALException;
}
