package org.kgj.pds.playlist.presentation.webapp;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Result;

import org.kgj.pds.playlist.presentation.messagingProtocol.Query;
import org.kgj.pds.playlist.presentation.messagingProtocol.Query.Action;
import org.kgj.pds.playlist.presentation.messagingProtocol.Query.UserManager;
import org.kgj.pds.playlist.presentation.messagingProtocol.Query.UserManager.User;
import org.kgj.pds.playlist.presentation.messagingService.WebAppMessagingServiceManager;

/**
 * Servlet implementation class myServlet
 */
@SuppressWarnings("restriction")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SecureRandom random = new SecureRandom();

	public String nextSessionId() {
		return new BigInteger(130, random).toString(32);
	}

	/**
	 * Default constructor.
	 */
	public MyServlet() {
		// TODO Auto-generated constructor stub
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

		String login = request.getParameter("inputLogin");
		String password = request.getParameter("inputPassword");


		Query q = new Query();
		Action a = new Action();
		a.setNameAction("login");

		User u = new User();
		u.setLogin(login);
		u.setPassword(password);

		UserManager uM = new UserManager();
		uM.setUser(u);

		q.setAction(a);
		q.setUserManager(uM);
		q.setQueryId(nextSessionId());
		
		WebAppMessagingServiceManager.getInstance();
		
		JAXBContext jaxbContext;
		Result str = null;
		try {
			jaxbContext = JAXBContext.newInstance("org.kgj.pds.playlist.presentation.messagingProtocol");
			Marshaller mar = jaxbContext.createMarshaller();
			mar.marshal(q, str);
		} catch ( JAXBException e) {
			e.printStackTrace();
		}
		
		System.out.println(str.toString());
//		
//
		
//		WebAppMessagingServiceManager.getInstance().send("");
		// If the user authanticate
		// request.getSession().setAttribute("user", user); user = un
		// "tableau"

	}

}
