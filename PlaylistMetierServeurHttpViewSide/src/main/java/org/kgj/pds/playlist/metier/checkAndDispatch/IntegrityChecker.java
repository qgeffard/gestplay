package org.kgj.pds.playlist.metier.checkAndDispatch;

import javax.jms.JMSException;
import javax.jms.Message;

import org.kgj.pds.playlist.metier.messagingProtocol.Query;

/**
 * Classe use to make the first check of integrity before dispatch (back to view or forward to webapp)
 * @author geffardq
 *
 */
public class IntegrityChecker {
	private DispatcherViewSide dispatcher;
	
	public IntegrityChecker(){
		dispatcher = new DispatcherViewSide();
	}
	
	public void entryPointCheckIntegrity(Query query, Message message) throws JMSException{
		boolean forward = true;
		switch (query.getAction().getNameAction()) {
		case "create":
			forward = checkExistingPlaylist(query);
			break;
		case "delete":
			forward = checkExistingPlaylist(query);
			break;
		case "update":
			forward = checkExistingPlaylist(query);
			break;
		case "login":
			forward = checkExistingLogin(query);
			break;

		default:
			break;
		}
		
		if (forward) 
			dispatcher.sendToPS(query, message);
		else
			dispatcher.sendToView(query, message);
		
	}

	private boolean checkExistingPlaylist(Query query) {
		return (query.getPlaylist().size() != 0) ? true : false;
	}

	private boolean checkExistingLogin(Query query) {
		return (query.getUserManager().getUser() != null) ? true : false;
	}
}
