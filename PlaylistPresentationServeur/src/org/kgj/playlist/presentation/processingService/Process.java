package org.kgj.playlist.presentation.processingService;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.kgj.pds.playlist.presentation.messagingProtocol.Query;
import org.kgj.pds.playlist.presentation.webapp.MyServlet;

public class Process {
	Query query;
	private static Map<String, String> responseManager;

	public Process(Query q) {
		this.query = q;	
		responseManager = new ConcurrentHashMap<String, String>();
		responseManager = MyServlet.getResponseManager();
		start();
	}
	
	
	private void start() {
		switch (this.query.getAction().getNameAction()) {
		
		case "login":
			login();
			break;
		
		case "create":
			create();
			break;
		
		case "modify":
			modify();
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
	private void login() {
		
		if (this.query.getStatus().getSucced() != null) {
			MyServlet.setSes(1,"0");
		} else {
			MyServlet.setSes(1,"-1");
			MyServlet.setSes(2,this.query.getStatus().getError().getMessage());
		}
		
		String queryId = query.getQueryId();
		String rmKey = "";
		
		Set cles = responseManager.keySet();
		Iterator it = cles.iterator();
		while (it.hasNext()){
		   Object cle = it.next();
		   Object valeur = responseManager.get(cle); 
		   rmKey = cle.toString();
		   
		}
		String name = MyServlet.getResponseManager().get(query.getQueryId());
		
		
		int count = Thread.activeCount();
	     
	    
	     Thread th[] = new Thread[count];
	     // returns the number of threads put into the array 
	     Thread.enumerate(th);
	    
	     // prints active threads
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
	
	/* Process the create case
	 * When the user want to create a new playlist
	 * if the create success, don't change the display. (Already displayed in JS)
	 * if the create fail, alert the user and delete it in the view
	 */
	private void create() {
		
	}
	
	/* Process the modify playlist case
	 * In fact, modify is a delete for the track but not only (so do the number of track)
	 * also the modification of the name and other basic informations
	 * All informations about the playlist will be reloaded when we made a change
	 */
	private void modify() {
		
	}
	
	/* Process the delete playlist case
	 * Simply delete a playlist from the user account
	 * The playlist will be deleted with JS until the answer come back
	 * If the answer is false, then the playlist will be displayed back and the user alerted
	 */
	private void delete() {
		
	}

}
