package org.kgj.playlist.presentation.processingService;

import org.kgj.pds.playlist.presentation.messagingProtocol.Query;

@SuppressWarnings("unused")
public class Process {
	Query query;

	Process(Query q) {
		this.query = q;		
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
			System.out.println("Succes");
		} else {
			System.out.println(this.query.getStatus().getError().getMessage());
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
