package fr.eni.preferencesDUsage.ihm;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletSetColor
 */
@WebServlet("/index")
public class ServletSetColor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private void checkInit(HttpServletRequest request) {
		if(getServletContext().getAttribute("colors") == null) {
			Map<String, String> colors = new LinkedHashMap<>();

			colors.put("noir", "text-dark");
			colors.put("bleu", "text-primary");
			colors.put("gris", "text-secondary");
			colors.put("vert", "text-success");
			colors.put("rouge", "text-danger");
			colors.put("jaune", "text-warning bg-dark");
			colors.put("cyan", "text-info bg-dark");
			colors.put("blanc", "text-white bg-dark");
			
			getServletContext().setAttribute("colors", colors);
		}
		
		if(request.getSession().getAttribute("color") == null) {
			request.getSession().setAttribute("color", "text-dark");
		}
	}

	private void count(HttpServletRequest request) {
		if(request.getSession().getAttribute("counter") == null) {
			request.getSession().setAttribute("counter", 0);
		}
		
		request.getSession().setAttribute("counter", (int) request.getSession().getAttribute("counter") + 1);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		checkInit(request);
		count(request);
		
		RequestDispatcher rs = request.getRequestDispatcher("WEB-INF/pages/index.jsp");
		rs.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		checkInit(request);
		
		String color = request.getParameter("color");
		
		@SuppressWarnings("unchecked")
		Map<String, String> colors = (Map<String, String>) getServletContext().getAttribute("colors");
		
		if(colors.containsKey(color)) {
			request.getSession().setAttribute("color", colors.get(color));
		}
		
		doGet(request, response);
	}
}
