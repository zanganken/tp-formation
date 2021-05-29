package fr.eni.suiviDesRepas.ihm;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.suiviDesRepas.bll.BLLException;
import fr.eni.suiviDesRepas.bll.RepasManager;
import fr.eni.suiviDesRepas.dal.DALException;

/**
 * Servlet implementation class ServletAjoutRepas
 */
@WebServlet("/add")
public class ServletAjoutRepas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/add.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Map<String, String[]> params = request.getParameterMap();
		
		RepasManager rMgr = new RepasManager();
		Integer idRepas;
		
		try {
			idRepas = rMgr.add(params);
			response.sendRedirect(request.getContextPath() + "/view" + (idRepas != null ? "?id="+idRepas+"#"+idRepas : ""));
		} catch (DALException | BLLException e) {
			request.setAttribute("error", e.getMessage());

			request.setAttribute("date", params.get("date")[0]);
			request.setAttribute("heure", params.get("heure")[0]);
			request.setAttribute("repas", params.get("repas")[0]);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/add.jsp");
			rd.forward(request, response);
		}
	}

}
