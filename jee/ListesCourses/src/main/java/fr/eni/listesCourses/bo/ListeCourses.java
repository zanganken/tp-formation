package fr.eni.listesCourses.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListeCourses implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nom;
	private List<Article> articles;
	
	public ListeCourses() {
		this.articles = new ArrayList<Article>();
	}
	public ListeCourses(String nom) {
		this();
		this.nom = nom;
	}
	public ListeCourses(String nom, Article article) {
		this(nom);
		this.articles.add(article);
	}
	public ListeCourses(Integer id, String nom, Article article) {
		this(nom, article);
		this.id = id;
	}
	public ListeCourses(Integer id, String nom, List<Article> articles) {
		this.id = id;
		this.nom = nom;
		this.articles = articles;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	
	public void addArticle(Article article) {
		articles.add(article);
	}
	public boolean removeArticle(Article article) {
		return articles.remove(article);
	}
	public boolean removeArticle(int idx) {
		return articles.remove(idx) != null;
	}
}
