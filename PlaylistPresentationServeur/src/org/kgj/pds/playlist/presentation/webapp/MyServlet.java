package org.kgj.pds.playlist.presentation.webapp;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Result;

import org.apache.log4j.Logger;
import org.kgj.pds.playlist.presentation.messagingProtocol.Query;
import org.kgj.pds.playlist.presentation.messagingProtocol.Query.Action;
import org.kgj.pds.playlist.presentation.messagingProtocol.Query.Status;
import org.kgj.pds.playlist.presentation.messagingProtocol.Query.UserManager;
import org.kgj.pds.playlist.presentation.messagingProtocol.Query.UserManager.User;
import org.kgj.pds.playlist.presentation.messagingService.WebappMessagingServiceManager;

/**
 * Servlet implementation class myServlet
 */
@SuppressWarnings("unused")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = -871399416795687851L;
	private static final Logger logger = Logger.getLogger(MyServlet.class);
	private SecureRandom random = new SecureRandom();
	
	/* 1 = connected
	 * 2 = error message
	 * 3 = list of playlist
 */	
	private static String ses[] = new String[10];
	private static Map<String, String> responseManager;

	public void init(ServletConfig config) throws ServletException {
		responseManager = new ConcurrentHashMap<String, String>();
	}

	public String nextSessionId() {
		return new BigInteger(130, random).toString(32);
	}

	/**
	 * Default constructor.
	 */
	public MyServlet() {
		
	}
	
	public static String[] getSes() {
		return ses;
	}
	
	public static String getSes(int i) {
		return ses[i];
	}
	
	public static void setSes(String[] ses) {
		MyServlet.ses = ses;
	}
	
	public static void setSes(int i, String s) {
		MyServlet.ses[i] = s;
	}

	public static Map<String, String> getResponseManager() {
		return responseManager;
	}

	public void setResponseManager(Map<String, String> responseManager) {
		MyServlet.responseManager = responseManager;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
			
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String userAgent = request.getHeader("User-Agent");
	
		HttpSession session = request.getSession();
		session.setAttribute("id", login);
		
		String id = nextSessionId();
		Thread.currentThread().setName(id);
		String name = Thread.currentThread().getName();
		responseManager.put(id, name);

		Query query = new Query();
		Action action = new Action();
		action.setNameAction("login");

		User user = new User();
		user.setLogin(login);
		user.setPassword(password);

		UserManager userManager = new UserManager();
		userManager.setUser(user);

		
		// Add le status de la query
		// Si erreur, message et source (d'o� �a bug, on s'en fou)
		// 
		
		Status status = new Status();
		status.setProgress("progress");
		
		query.setAction(action);
		query.setUserManager(userManager);
		query.setQueryId(id);
		query.setStatus(status);
		
		JAXBContext jaxbContext;
		StringWriter str = new StringWriter() ;
		try {
			jaxbContext = JAXBContext.newInstance("org.kgj.pds.playlist.presentation.messagingProtocol");
			Marshaller mar = jaxbContext.createMarshaller();
			mar.marshal(query, str);
		} catch ( JAXBException e) {
			e.printStackTrace();
		}
		
		
		WebappMessagingServiceManager.getInstance().send(str.toString());;
		
			
		synchronized (Thread.currentThread()) {
	        try {
	        	Thread.currentThread().wait();
	        	if(ses[1].equals("-1")) {
	        		session.setAttribute("connected", ses[1]);
	        		session.setAttribute("erreur", ses[2]);
	        		response.sendRedirect("index.jsp");
	        	} else {
	        	session.setAttribute("connected", ses[1]);
	        	response.sendRedirect("welcome.jsp");
	        	}
	        } catch (Throwable e) {
	            e.printStackTrace();
	        }
	    }
	}
	

}
