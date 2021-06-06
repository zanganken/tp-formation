package fr.eni.listesCourses.ihm;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import fr.eni.listesCourses.bll.BLLException;
import fr.eni.listesCourses.bll.ListeCoursesManager;
import fr.eni.listesCourses.bo.Article;
import fr.eni.listesCourses.bo.ListeCourses;
import fr.eni.listesCourses.dal.DALException;

@Path("/listesCourses")
public class GestionListesCourses {
	private static List<ListeCourses> listesCourses = new ArrayList<ListeCourses>();
	
	static {
		ListeCoursesManager lcmgr = ListeCoursesManager.getInstance();
		
		try {
			listesCourses = lcmgr.getAll();
		} catch (DALException e) {
			e.printStackTrace();
		}
	}
	
// METHODE GET :
	/**
	 * @return toutes les listes de courses
	 */
	@GET
	public List<ListeCourses> getListesCourses() {
		ListeCoursesManager lcmgr = ListeCoursesManager.getInstance();
		
		try {
			listesCourses = lcmgr.getAll();
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		return listesCourses;
	}
	
	/**
	 * @param id : int (id de la liste)
	 * @return la liste de courses dont l'id est renseigné
	 */
	@GET
	@Path("/{id: \\d+}")
	public ListeCourses getListeCourses(@PathParam("id") int id) {
		ListeCoursesManager lcmgr = ListeCoursesManager.getInstance();
		
		try {
			return lcmgr.getOne(id);
		} catch (DALException e) {
			e.printStackTrace();
			return null;
		}
	}

// METHODE POST :
	/**
	 * Insertion d'une nouvelle liste de courses
	 * @param nom : String (nom de la liste)
	 * @param article : String (premier article à insérer)
	 * @return la liste créée
	 */
	@POST
	public ListeCourses createListeCourses(
			@FormParam("nom") String nom,
			@FormParam("article") String article,
			@FormParam("qte") Integer qte
	) {
		if(nom.isEmpty() || article.isEmpty()) return null;
		
		ListeCoursesManager lcmgr = ListeCoursesManager.getInstance();
		ListeCourses lc = new ListeCourses(nom);
		
		try {
			Article a = new Article(article.trim(), qte);
			
			lc = lcmgr.addArticle(a, lc);
			listesCourses.add(lc);
		} catch (DALException | BLLException e) {
			e.printStackTrace();
			lc = null;
		}
		
		return lc;
	}
	
	/**
	 * Insertion d'un article dans la liste /id
	 * @param id : int (id de la liste)
	 * @param article : String (article à insérer)
	 * @return l'article si l'insertion a été effectuée avec succès
	 */
	@POST
	@Path("/{id: \\d+}")
	public Article insertArticleIntoListeCourses(
			@PathParam("id") Integer id,
			@FormParam("article") String article,
			@FormParam("qte") Integer qte
	) {
		if(article.isEmpty() && qte <= 0) return null;
		
		for(ListeCourses lc : listesCourses) {
			if(lc.getId() == id) {
				ListeCoursesManager lcmgr = ListeCoursesManager.getInstance();
				
				try {
					Article a = new Article(article.trim(), qte);
					
					lcmgr.addArticle(a, lc);
					
					lc.addArticle(a);
					return a;
				} catch (DALException | BLLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}

// METHODE DELETE :
	/**
	 * Supprimer la liste /id
	 * @param id : int (id de la liste)
	 * @return vrai si la suppression a été effectuée avec succès/faux dans le cas contraire
	 */
	@DELETE
	@Path("/{id: \\d+}")
	public boolean deleteListeCourses(@PathParam("id") int id) {
		for(ListeCourses lc : listesCourses) {
			if(lc.getId() == id) {
				ListeCoursesManager lcmgr = ListeCoursesManager.getInstance();
				
				try {
					lcmgr.removeListeCourses(lc);
					return listesCourses.remove(lc);
				} catch (DALException | BLLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Supprimer l'article passé en paramètre de la liste /id
	 * @param idLC : int (id de la liste)
	 * @param article : String (article à supprimer)
	 * @return vrai si la suppression a été effectuée avec succès/faux dans le cas contraire
	 */
	@DELETE
	@Path("/{idLC: \\d+}/article")
	public boolean deleteArticleFromListeCourses(@PathParam("idLC") int idLC, @FormParam("article") String article) {
		for(ListeCourses lc : listesCourses) {
			if(lc.getId() == idLC) {
				ListeCoursesManager lcmgr = ListeCoursesManager.getInstance();
				
				try {
					List<Article> articles = lc.getArticles();
					
					for(int i = 0; i < articles.size(); i++) {
						if(articles.get(i).getNom().equals(article)) {
							lcmgr.removeArticle(article, lc);
							
							return lc.removeArticle(i);
						}
					}
				} catch (DALException | BLLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return false;
	}
}
