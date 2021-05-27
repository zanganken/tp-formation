package fr.eni.TPRechercherNombre.ihm;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletRechercherNombre extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int nombreAleatoire;
	
	public ServletRechercherNombre() {
		super();
		
		Random r = new Random();
		this.nombreAleatoire = r.nextInt(10 + 1);
		
		System.out.println(this.nombreAleatoire);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String protocole = request.getScheme();
		String nomServeur = request.getServerName();
		int port = request.getServerPort();
		String nomApplication = request.getContextPath();
		
		String baseUrl = String.format("%s://%s:%d%s",
				protocole, nomServeur, port, nomApplication);
		
		try {
			int n = Integer.parseInt(request.getParameter("nombre"));
			
			if(n == this.nombreAleatoire) {
				response.sendRedirect(baseUrl + "/gagne.html");
				return;
			}
		} catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(baseUrl + "/perdu.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
