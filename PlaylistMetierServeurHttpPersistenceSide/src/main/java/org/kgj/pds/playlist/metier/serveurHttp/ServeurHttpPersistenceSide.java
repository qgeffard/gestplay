package org.kgj.pds.playlist.metier.serveurHttp;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.kgj.pds.playlist.metier.data.LocalStorage;
import org.kgj.pds.playlist.metier.messagingProtocol.Query;
import org.kgj.pds.playlist.metier.messagingService.ServeurHttpPersistenceSideMessagingServiceManager;
import org.kgj.playlist.metier.checkAndDispatch.CheckerPersistenceSide;
import org.kgj.playlist.metier.checkAndDispatch.DispatcherPersistenceSide;

/**
 * Servlet implementation class ServeurHttp
 */
public class ServeurHttpPersistenceSide extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ServeurHttpPersistenceSide.class);
	public static LocalStorage localStorage = new LocalStorage();
	private DispatcherPersistenceSide dispatcher;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		dispatcher = new DispatcherPersistenceSide();
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("request received from VS");
		logger.info("Param : " + request.getParameter("action"));
		logger.info("Param : " + request.getParameter("query"));
		Query query = ServeurHttpPersistenceSideMessagingServiceManager
				.getInstance().stringToQuery(request.getParameter("query"));
		
		CheckerPersistenceSide checker = new CheckerPersistenceSide();
		checker.check(query);
		dispatcher.dispatch(query);
		
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
