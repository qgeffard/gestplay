package org.kgj.pds.playlist.metier.integrityAndDispatcher;

import org.kgj.pds.playlist.metier.generated.Query;

/**
 * Classe use to make the first check of integrity before dispatch (back to view or forward to webapp)
 * @author geffardq
 *
 */
public class IntegrityChecker {
	private Dispatcher dispatcher;
	
	public IntegrityChecker(){
		dispatcher = new Dispatcher();
	}
	
	public void entryPointCheckIntegrity(Query query){
		boolean forward = true;
		//Check some point...
		
		if (forward) 
			dispatcher.sendToWS(query);
		else
			dispatcher.sendToView(query);
		
	}
}
