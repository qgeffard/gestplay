package org.kgj.pds.playlist.metier.httpClient;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.kgj.pds.playlist.metier.messagingService.ClientHttpMessagingServiceManager;

/**
 * Class used to init all ClientHttp side
 * 
 * @author Doox
 *
 */
public class ClientHttpBoot {
	
	static Logger logger = Logger.getLogger(ClientHttpBoot.class);

	public static void main(String[] args) {
		startLoggerService();
		startMessagingService();
		// ...
	}

	/**
	 * Demarre le service de logging
	 */
	private static void startLoggerService() {
		BasicConfigurator.configure();
		logger.debug("Hello World!");
		logger.info("Info");
		logger.warn("warning!");
		logger.error("error");

	}

	/**
	 * Demarre le service de messagerie grace a la methode getInstance pour
	 * creer le singleton
	 */
	private static void startMessagingService() {
		ClientHttpMessagingServiceManager.getInstance();
	}

}
