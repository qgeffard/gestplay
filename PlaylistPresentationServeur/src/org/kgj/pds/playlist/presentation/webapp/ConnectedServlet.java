package org.kgj.pds.playlist.presentation.webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import org.apache.log4j.Logger;
import org.kgj.pds.playlist.Utils.QueryManager;
import org.kgj.pds.playlist.presentation.messagingProtocol.PlaylistType;
import org.kgj.pds.playlist.presentation.messagingProtocol.Query;
import org.kgj.pds.playlist.presentation.messagingProtocol.Query.Action;
import org.kgj.pds.playlist.presentation.messagingProtocol.Query.Status;
import org.kgj.pds.playlist.presentation.messagingProtocol.Query.UserManager;
import org.kgj.pds.playlist.presentation.messagingProtocol.Query.UserManager.User;
import org.kgj.pds.playlist.presentation.messagingProtocol.TrackListType;
import org.kgj.pds.playlist.presentation.messagingProtocol.TrackType;
import org.kgj.pds.playlist.presentation.messagingService.WebappMessagingServiceManager;

/**
 * Servlet implementation class ConnectedServlet
 */
@WebServlet("/ConnectedServlet")
@SuppressWarnings("unused")
public class ConnectedServlet extends HttpServlet {
	private static final Logger logger = Logger.getLogger(ConnectedServlet.class);
	private static final long serialVersionUID = 1L;
	private SecureRandom random = new SecureRandom();
	private static Map<String, String> responseManager;
	private static Object ses[] = new Object[10];

	public void init(ServletConfig config) throws ServletException {
		responseManager = MyServlet.getResponseManager();
		ses = MyServlet.getSes();
	}

	public String nextSessionId() {
		return new BigInteger(130, random).toString(32);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConnectedServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		// On fait un switch sur l'action qui est défini dans le formulaire

		HttpSession session = request.getSession();
		Query query = new Query();
		UserManager userManager = new UserManager();
		Status status = new Status();
		List<PlaylistType> listPlaylist = new ArrayList<PlaylistType>();
		List<TrackType> track = new ArrayList<TrackType>();
		TrackListType trackList = new TrackListType();
		PlaylistType playlist = new PlaylistType();

		User user = new User();
		user.setLogin(session.getAttribute("user").toString());
		user.setPassword(session.getAttribute("pass").toString());
		// userManager.setConnectedToken(session.getAttribute("token").toString());
//		QueryManager.setUserManagerConnected(query, session.getAttribute("token").toString()); //TODO : ramener le token de session 
		QueryManager.setUserManagerConnected(query, "aaa");
		userManager.setUser(user);

		listPlaylist.add(playlist); // On set la playlist à la liste de playlist
		playlist.setTrackList(trackList); // On set la liste de track à la
											// playlist
		playlist.setTitle(request.getParameter("name"));
		playlist.setCreator(session.getAttribute("user").toString());
		System.out.println("PRES : Requête en cours d'envoi !");

		String id = nextSessionId();
		Thread.currentThread().setName(id);
		String name = Thread.currentThread().getName();
		responseManager.put(id, name);

		switch (action) {
		case "update":
			playlist.setIdentifier(request.getParameter("identifier").toString());
			QueryManager.setActionUpdate(query);
			break;
		case "delete":
			playlist.setIdentifier(request.getParameter("identifier").toString());
			QueryManager.setActionDelete(query);
			break;
		case "create":
			QueryManager.setActionCreate(query);
			break;
		default:
			logger.error("No action specified");
			break;
		}

		// On se sert désormais du connected token, et non plus du userManager
		// query.setUserManager(userManager);
		query.setQueryId(id);
		QueryManager.setStatusInProgress(query);
		query.getPlaylist().add(playlist);

		JAXBContext jaxbContext;
		StringWriter str = new StringWriter();
		try {
			jaxbContext = JAXBContext.newInstance("org.kgj.pds.playlist.presentation.messagingProtocol");
			Marshaller mar = jaxbContext.createMarshaller();
			mar.marshal(query, str);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		WebappMessagingServiceManager.getInstance().send(str.toString());

		
		PrintWriter out = response.getWriter();

		synchronized (Thread.currentThread()) {
			try {
				Thread.currentThread().wait();
				System.out.println("PRES : Transmission Process - Servlet : OK.");
				int text = 0;

				if (ses[1].equals("0")) {
					List<PlaylistType> pT = (List<PlaylistType>) request.getSession().getAttribute("playlist");

					if (action.equals("update")) { // Quand l'utilisateur sauvegarde la liste des tracks
						System.out.println("PRES : "+action+" : "+pT.get(0).getIdentifier()+" : Transmission Vue");
						for (int i = 0; i < pT.size(); i++) {
							if (pT.get(i).getIdentifier().equals(playlist.getIdentifier())) {
								pT.get(i).setTitle(request.getParameter("name"));
								text = i;
							}
						}
					} else if (action.equals("delete")) { // Quand l'utilisateur supprime une playlist
						System.out.println("PRES : "+action+" : "+pT.get(0).getIdentifier()+" : Transmission Vue");
						for (int i = 0; i < pT.size(); i++) {
							if (pT.get(i).getIdentifier().equals(playlist.getIdentifier())) {
								pT.remove(i);
								text = i;
							}
						}

					} else if (action.equals("create")) { // Se fait lors de l'ajout d'une playlist
						playlist.setIdentifier(MyServlet.getSes(10));
						pT.add(playlist);
						System.out.println("PRES : "+action+" : "+pT.get(0).getIdentifier()+" : Transmission Vue");
						text = pT.size();
					}
					
					// On modifie les variables de sessions correspondantes.
					MyServlet.setSes(3, pT);
					request.getSession().setAttribute("playlist", ses[3]);
					System.out.println("PRES : Identifier : " + text);
					
					response.setContentType("text/html;charset=UTF-8"); // For world domination					
					response.getWriter().write(text); // Write response body.
					
				} else {
					session.setAttribute("erreur", ses[2].toString());
					response.getWriter().write("Error");
				}
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}

	}

	void savePlaylists(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	void savePlaylist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
