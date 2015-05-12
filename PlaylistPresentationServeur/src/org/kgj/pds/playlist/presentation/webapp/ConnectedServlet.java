package org.kgj.pds.playlist.presentation.webapp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConnectedServlet
 */
@WebServlet("/ConnectedServlet")
public class ConnectedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnectedServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		// On fait un switch sur l'action qui est d�fini dans le formulaire
		
		if(action == "savePlaylists") {
			response.sendRedirect("welcome.jsp");
		}
		response.sendRedirect("index.jsp");
	}
	
	void savePlaylists(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	void savePlaylist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
