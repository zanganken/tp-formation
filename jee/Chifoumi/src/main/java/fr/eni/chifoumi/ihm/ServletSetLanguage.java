package fr.eni.chifoumi.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletSetLanguage
 */
@WebServlet("/setLanguage")
public class ServletSetLanguage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie cookie = new Cookie("lang", request.getParameter("lang"));
		
		if(request.getParameter("lang").equals("default")) {
			cookie.setMaxAge(0);
		}
		
		response.addCookie(cookie);
		response.sendRedirect(request.getContextPath() + "/");
	}

}
