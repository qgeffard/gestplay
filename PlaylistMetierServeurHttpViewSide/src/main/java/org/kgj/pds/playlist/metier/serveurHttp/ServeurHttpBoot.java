package org.kgj.pds.playlist.metier.serveurHttp;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.kgj.pds.playlist.metier.messagingService.ServeurHttpMessagingServiceManager;


public class ServeurHttpBoot implements ServletContextListener {
	ServletContext context;
	private static final Logger logger = Logger.getLogger(ServeurHttpBoot.class);

	public void contextInitialized(ServletContextEvent contextEvent) {
		// ... some init work
		logger.info("Web app is starting");
		startMessagingService();
	}
	
	/**
	 * Demarre le service de messagerie grace a la methode getInstance pour
	 * creer le singleton
	 */
	private static void startMessagingService() {
		ServeurHttpMessagingServiceManager.getInstance();
	}

	public void contextDestroyed(ServletContextEvent contextEvent) {
		// ... some destroy work
		logger.info("Web app stoping");
	}
}