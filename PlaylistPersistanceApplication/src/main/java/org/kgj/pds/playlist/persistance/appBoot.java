package org.kgj.pds.playlist.persistance;

import org.apache.log4j.Logger;
import org.kgj.pds.playlist.persistance.messagingService.ClientAppMessagingServiceManager;

/**
 * Class used to init all ClientHttp side
 * 
 * @author Doox
 *
 */
public class appBoot {
	
	static Logger logger = Logger.getLogger(appBoot.class);

	public static void main(String[] args) {
		logger.info("Starting app metier");
		startMessagingService();
		// ...
//		tryWSquery();
	}
	
	/**
	 * Demarre le service de messagerie grace a la methode getInstance pour
	 * creer le singleton
	 */
	private static void startMessagingService() {
		ClientAppMessagingServiceManager.getInstance();
	}

}
