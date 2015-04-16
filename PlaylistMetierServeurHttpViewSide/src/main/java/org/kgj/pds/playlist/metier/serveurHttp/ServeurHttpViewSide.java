package org.kgj.pds.playlist.metier.serveurHttp;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.kgj.pds.playlist.metier.checkAndDispatch.Dispatcher;
import org.kgj.pds.playlist.metier.messagingService.ServeurHttpMessagingServiceManager;

/**
 * Servlet implementation class ServeurHttp
 */
public class ServeurHttpViewSide extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ServeurHttpViewSide.class);
	private Dispatcher dispatcher;
	
    /**
     * Default constructor. 
     */
    public ServeurHttpViewSide() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		dispatcher = new Dispatcher();
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("request received from PS");
		
		ServeurHttpMessagingServiceManager.getInstance().send(request.getParameter("query"));
		logger.info(request.getParameter("query"));
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
		// TODO Auto-generated method stub
	}

}
