package org.kgj.pds.playlist.persistance.PlaylistPersistanceApplicationClient;

import org.apache.log4j.Logger;
import org.kgj.pds.playlist.persistance.messagingService.ClientAppMessagingServiceManager;

/**
 * Hello world!
 *
 */
public class App {
	protected static final Logger logger = Logger.getLogger(App.class);

	public static void main(String[] args) {
		logger.info("Starting the Client RMI app..");
		ClientAppMessagingServiceManager.getInstance();
		logger.info("The Client RMI app started");
	}
}
