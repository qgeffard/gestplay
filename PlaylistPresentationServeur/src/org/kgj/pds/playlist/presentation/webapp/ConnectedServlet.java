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

		listPlaylist.add(playlist);  		// On set la playlist à la liste de playlist
		playlist.setTrackList(trackList);  	// On set la liste de track à la playlist
		
		
		userManager = (UserManager) session.getAttribute("user");
		status.setProgress("In progress");
		
		String id = nextSessionId();
		Thread.currentThread().setName(id);
		String name = Thread.currentThread().getName();
		responseManager.put(id, name);

		if(action == "savePlaylists") {  // Quand l'utilisateur sauvegarde la liste des tracks
			act.setNameAction("update");
		} else if (action == "deletePlaylist") {  // Quand l'utilisateur supprime une playlist
			act.setNameAction("delete");
		} else if (action == "createPlaylist") {  // Se fait lors de l'ajout d'une playlist
			act.setNameAction("create");
			userManager = (UserManager) session.getAttribute("user");
			
		}
		
		query.setAction(act);
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
		WebappMessagingServiceManager.getInstance().send(str.toString());
		
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
		synchronized (Thread.currentThread()) {
	        try {
	        	Thread.currentThread().wait();
	        	System.out.println("J'ai été notify");
	        	if(action == "savePlaylists") {  // Quand l'utilisateur sauvegarde la liste des tracks
	        		 out.println();
	    		} else if (action == "deletePlaylist") {  // Quand l'utilisateur supprime une playlist
	    			 out.println();
	    		} else if (action == "createPlaylist") {  // Se fait lors de l'ajout d'une playlist
	    			 out.println();
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
