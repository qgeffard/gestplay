package org.kgj.pds.playlist.presentation.webapp;

import java.io.IOException;
import java.math.BigInteger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.security.SecureRandom;

import org.kgj.pds.playlist.presentation.messagingProtocol.Query;
import org.kgj.pds.playlist.presentation.messagingProtocol.Query.UserManager.User;
import org.kgj.pds.playlist.presentation.messagingProtocol.Query.*;
import org.kgj.pds.playlist.presentation.messagingService.WebAppMessagingServiceManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;

import javax.xml.bind.*;
import javax.xml.transform.Result;

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

		System.out.println("lol");
		String login = request.getParameter("inputLogin");
		String password = request.getParameter("inputPassword");

		/* On créer la Query correspondant à la demande d'authentification */
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

		WebAppMessagingServiceManager.getInstance().send("");
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
		// If the user authanticate
		// request.getSession().setAttribute("user", user); où user = un
		// "tableau"

	}

}
