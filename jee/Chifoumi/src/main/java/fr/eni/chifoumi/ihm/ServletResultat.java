package fr.eni.chifoumi.ihm;

import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletResultat
 */
@WebServlet("/resultat")
public class ServletResultat extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] choix = {"rock", "paper", "scissors"};
		
		int cu = Integer.parseInt(request.getParameter("choix"));
		int cs = new Random().nextInt(3); // renvoit un chiffre entre 0 et 2
		String winStr;
		
		if(cu == cs) {
			winStr = "draw";
		} else if((cu + 1) == cs || (cu == (choix.length - 1) && cs == 0)) {
			winStr = "server_wins";
		} else {
			winStr = "you_win";
		}
		
		request.setAttribute("choixUtilisateur", choix[cu]);
		request.setAttribute("choixServeur", choix[cs]);
		request.setAttribute("resultat", winStr);
		
		RequestDispatcher rd = this.getServletContext().getNamedDispatcher("resultatJSP");
		rd.forward(request, response);
	}

}
