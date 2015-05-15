package org.kgj.pds.playlist.presentation.webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
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
import org.kgj.pds.playlist.presentation.messagingProtocol.PlaylistType;
import org.kgj.pds.playlist.presentation.messagingProtocol.Query;
import org.kgj.pds.playlist.presentation.messagingProtocol.TrackListType;
import org.kgj.pds.playlist.presentation.messagingProtocol.TrackType;
import org.kgj.pds.playlist.presentation.messagingProtocol.Query.Action;
import org.kgj.pds.playlist.presentation.messagingProtocol.Query.Status;
import org.kgj.pds.playlist.presentation.messagingProtocol.Query.UserManager;
import org.kgj.pds.playlist.presentation.messagingProtocol.Query.UserManager.User;
import org.kgj.pds.playlist.presentation.messagingService.WebappMessagingServiceManager;

/**
 * Servlet implementation class ConnectedServlet
 */
@WebServlet("/ConnectedServlet")
@SuppressWarnings("unused")
public class ConnectedServlet extends HttpServlet {
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
		// On fait un switch sur l'action qui est défini dans le formulaire

		HttpSession session = request.getSession();
		Query query = new Query();
		Action act = new Action();	
		UserManager userManager = new UserManager();
		Status status = new Status();
		List<PlaylistType> listPlaylist = new ArrayList<PlaylistType>();
		List<TrackType> track = new ArrayList<TrackType>();
		TrackListType trackList = new TrackListType();
		PlaylistType playlist = new PlaylistType();
		
		User user = new User();
		user.setLogin(session.getAttribute("user").toString());
		user.setPassword(session.getAttribute("pass").toString());
		
		userManager.setUser(user);

		listPlaylist.add(playlist);  		// On set la playlist à la liste de playlist
		playlist.setTrackList(trackList);  	// On set la liste de track à la playlist
		playlist.setTitle(request.getParameter("name"));
		playlist.setCreator(session.getAttribute("user").toString());
		System.out.println("Requête en cours d'envoi !");
		status.setProgress("In progress");
		
		String id = nextSessionId();
		Thread.currentThread().setName(id);
		String name = Thread.currentThread().getName();
		responseManager.put(id, name);
		
		
		if(action.equals("update")) {  // Quand l'utilisateur sauvegarde la liste des tracks
			System.out.println("update");
			act.setNameAction("update");
			playlist.setIdentifier(request.getParameter("identifier").toString());
		} else if (action.equals("delete")) {  // Quand l'utilisateur supprime une playlist
			System.out.println("delete");
			act.setNameAction("delete");
			playlist.setIdentifier(request.getParameter("identifier").toString());
		} else if (action.equals("create")) {  // Se fait lors de l'ajout d'une playlist
			System.out.println("create");
			act.setNameAction("create");
		}

		
		query.setAction(act);
		query.setUserManager(userManager);
		query.setQueryId(id);
		query.setStatus(status);
		query.getPlaylist().add(playlist);
		
		JAXBContext jaxbContext;
		StringWriter str = new StringWriter() ;
		try {
			jaxbContext = JAXBContext.newInstance("org.kgj.pds.playlist.presentation.messagingProtocol");
			Marshaller mar = jaxbContext.createMarshaller();
			mar.marshal(query, str);
		} catch ( JAXBException e) {
			e.printStackTrace();
		}
		WebappMessagingServiceManager.getInstance().send(str.toString());
		
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
		synchronized (Thread.currentThread()) {
	        try {
	        	Thread.currentThread().wait();
	        	System.out.println("J'ai été notify");
	        	response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
	            response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
	            String text = "";
	            
	            if(ses[1].equals("0")) {
	        		
	            
	        	if(action == "update") {  // Quand l'utilisateur sauvegarde la liste des tracks
	        		text = ses[10].toString();
	    		} else if (action == "delete") {  // Quand l'utilisateur supprime une playlist
	    			text = ses[10].toString();
	    		} else if (action == "create") {  // Se fait lors de l'ajout d'une playlist
	    			 text = ses[10].toString();
	    		}	
	        	response.getWriter().write(text);       // Write response body.
	            } else {
	            	session.setAttribute("erreur", ses[2].toString());
	            	response.getWriter().write("Error"); 
	            }
	        }
			catch (Throwable e) {
	            e.printStackTrace();
	        }
	    }
		
	}
	
	void savePlaylists(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	void savePlaylist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
