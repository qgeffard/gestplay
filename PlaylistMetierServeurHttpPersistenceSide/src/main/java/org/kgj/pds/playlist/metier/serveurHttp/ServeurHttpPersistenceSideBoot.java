package org.kgj.pds.playlist.metier.serveurHttp;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.kgj.pds.playlist.metier.messagingService.ServeurHttpMessagingServiceManager;

public class ServeurHttpPersistenceSideBoot implements ServletContextListener {
	ServletContext context;
	private static final Logger logger = Logger.getLogger(ServeurHttpPersistenceSideBoot.class);

	public void contextInitialized(ServletContextEvent contextEvent) {
		// ... some init work
		logger.info("Web app is starting");
		ServeurHttpMessagingServiceManager.getInstance();
	}

	public void contextDestroyed(ServletContextEvent contextEvent) {
		// ... some destroy work
		logger.info("Web app stoping");
	}
}