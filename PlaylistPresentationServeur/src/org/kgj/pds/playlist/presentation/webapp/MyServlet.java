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
	private Map<String, Thread> responseManager;
	
	public void init(ServletConfig config) throws ServletException {
		responseManager = new ConcurrentHashMap<String, Thread>();
	}

	public String nextSessionId() {
		return new BigInteger(130, random).toString(32);
	}

	/**
	 * Default constructor.
	 */
	public MyServlet() {
		
	}

	public Map<String, Thread> getResponseChief() {
		return responseManager;
	}

	public void setResponseChief(Map<String, Thread> responseChief) {
		this.responseManager = responseChief;
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
		
			
		String login = request.getParameter("inputLogin");
		String password = request.getParameter("inputPassword");
		String userAgent = request.getHeader("User-Agent");
		
		String id = nextSessionId();
		Thread thread = Thread.currentThread();
		
		responseManager.put(id, thread);

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
		query.setQueryId(nextSessionId());
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
		
		System.out.println(str.toString());
		WebappMessagingServiceManager.getInstance().send(str.toString());;
		
	
		try {
			thread.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
;		
	}
	

}
