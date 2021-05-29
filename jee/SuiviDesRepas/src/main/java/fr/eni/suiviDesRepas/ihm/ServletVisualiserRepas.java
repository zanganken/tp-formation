package fr.eni.suiviDesRepas.ihm;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.suiviDesRepas.bll.RepasManager;

/**
 * Servlet implementation class ServletVisualiserRepas
 */
@WebServlet("/view")
public class ServletVisualiserRepas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RepasManager rMgr = new RepasManager();
		
		request.setAttribute("repasMap", rMgr.getAllRepas());
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/view.jsp");
		rd.forward(request, response);
	}

}
