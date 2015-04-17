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
		//Check some point...
		
		if (forward) 
			dispatcher.sendToPS(query, message);
		else
			dispatcher.sendToView(query, message);
		
	}
}
