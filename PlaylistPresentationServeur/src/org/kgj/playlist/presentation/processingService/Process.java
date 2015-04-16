package org.kgj.playlist.presentation.processingService;

@SuppressWarnings("unused")
public class Process {
	String idQuery;
	String action;

	Process(String id, String act) {
		this.idQuery = id;
		this.action = act;
		
		
	}
	
	
	private void start() {
		switch (this.action) {
		
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