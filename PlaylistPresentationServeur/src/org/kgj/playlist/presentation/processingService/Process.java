package org.kgj.playlist.presentation.processingService;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.kgj.pds.playlist.presentation.messagingProtocol.PlaylistType;
import org.kgj.pds.playlist.presentation.messagingProtocol.Query;
import org.kgj.pds.playlist.presentation.webapp.MyServlet;

import com.sun.istack.internal.logging.Logger;

public class Process {
	Query query;
	private static Map<String, String> responseManager;

	public Process(Query q) {
		this.query = q;	
		responseManager = MyServlet.getResponseManager();
		start();
	}
	
	
	private void start() {		
		System.out.println("PRES : Requête reçu - Dispatching...");
		switch (this.query.getAction().getNameAction()) {
		
		case "login":
			System.out.println("PRES : Identifier toString : "+this.query.getPlaylist().get(0).getIdentifier().toString());
			login();
			break;
			
		case "test":
			System.out.println("PRES : Identifier toString : "+this.query.getPlaylist().get(0).getIdentifier().toString());
			login();
			break;
		
		case "create":
			System.out.println("PRES : Identifier toString : "+this.query.getPlaylist().get(0).getIdentifier().toString());
			create();
			break;
		
		case "update":
			update();
			break;
		
		case "delete":
			delete();
			break;			
	}
	}
	
	
	/* Process the login case
	 * retry the ID of the query to correspond to the concurrent Map
	 * Then, notify the thread to log the user
	 * If the login fail,  switch back the user to the login page
	 */
	@SuppressWarnings("unused")
	private void login() {
		System.out.println("PRES : Requête reçu : Login");
		if (this.query.getStatus().getSucced() != null) {
			MyServlet.setSes(1,"0");
			MyServlet.setSes(3,(List<PlaylistType>) this.query.getPlaylist());
			MyServlet.sesSes(4,this.query.getUserManager());
		} else {
			MyServlet.setSes(1,"-1");
			MyServlet.setSes(2,this.query.getStatus().getError().getMessage());
		}
		notifyThread();
	    
	}
	
	/* Process the create case
	 * When the user want to create a new playlist
	 * if the create success, don't change the display. (Already displayed in JS)
	 * if the create fail, alert the user and delete it in the view
	 */
	private void create() {
		System.out.println("PRES : Requête reçu : Create.");
		if (this.query.getStatus().getSucced() != null) {
			MyServlet.setSes(1,"0");
			MyServlet.sesSes(10,this.query.getPlaylist().get(0).getIdentifier().toString());
		} else {
			MyServlet.setSes(1,"-1");
			MyServlet.setSes(2,this.query.getStatus().getError().getMessage());
		}
		notifyThread();
	}
	
	/* Process the modify playlist case
	 * In fact, modify is a delete for the track but not only (so do the number of track)
	 * also the modification of the name and other basic informations
	 * All informations about the playlist will be reloaded when we made a change
	 */
	private void update() {
		System.out.println("PRES : Requête reçu : Update.");
		if (this.query.getStatus().getSucced() != null) {
			MyServlet.setSes(1,"0");
			MyServlet.sesSes(10,this.query.getPlaylist().get(0).getIdentifier().toString());
		} else {
			MyServlet.setSes(1,"-1");
			MyServlet.setSes(2,this.query.getStatus().getError().getMessage());
		}
		notifyThread();
	}
	
	/* Process the delete playlist case
	 * Simply delete a playlist from the user account
	 * The playlist will be deleted with JS until the answer come back
	 * If the answer is false, then the playlist will be displayed back and the user alerted
	 */
	private void delete() {
		System.out.println("PRES : Requête reçu : Delete.");
		if (this.query.getStatus().getSucced() != null) {
			MyServlet.setSes(1,"0");
		//	MyServlet.sesSes(10,this.query.getPlaylist().get(0).getIdentifier().toString());
		} else {
			MyServlet.setSes(1,"-1");
			MyServlet.setSes(2,this.query.getStatus().getError().getMessage());
		}
		notifyThread();
	}
	
	private void notifyThread() {
		String name = MyServlet.getResponseManager().get(query.getQueryId());
		int count = Thread.activeCount();
	     Thread th[] = new Thread[count];
	     // returns the number of threads put into the array 
	     Thread.enumerate(th);
	    
	     // notify the thread that get answer
	     for (int i = 0; i < count; i++) {
	    	  if(name.equals(th[i].getName())) {
	    		  synchronized (th[i]) {
	    	      try {
	    	    	  th[i].notify();
	    	      	  } catch (Throwable e) {
	    	            e.printStackTrace();
	    	        }
	    	    }
	        	return;	        	
	        	}
	        
	     }
	}

}
