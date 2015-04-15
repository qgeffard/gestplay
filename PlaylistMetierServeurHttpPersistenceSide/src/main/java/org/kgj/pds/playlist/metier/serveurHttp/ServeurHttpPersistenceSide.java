package org.kgj.pds.playlist.metier.serveurHttp;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.kgj.pds.playlist.metier.messagingProtocol.Query;
import org.kgj.pds.playlist.metier.messagingService.ServeurHttpPersistenceSideMessagingServiceManager;
import org.kgj.playlist.metier.checker.LoginChecker;

/**
 * Servlet implementation class ServeurHttp
 */
public class ServeurHttpPersistenceSide extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(ServeurHttpPersistenceSide.class);
	private LocalStorage localstorage;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {

		// TODO Auto-generated method stub
		localstorage = new LocalStorage();
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("request received");
		logger.info("Param : " + request.getParameter("action"));
		logger.info("Param : " + request.getParameter("query"));
		Query query = ServeurHttpPersistenceSideMessagingServiceManager
				.getInstance().decryptQuery(request.getParameter("query"));

		switch (request.getParameter("action")) {
		case "login":
			LoginChecker loginChecker = new LoginChecker(localstorage);
			boolean allowed = loginChecker.validLogin(query.getUserManager().getUser());
			//todo send to persist or return to view depend on 
			break;
		default:
			break;
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
