package org.kgj.pds.playlist.metier.serveurHttp;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;

public class ServeurHttpBoot implements ServletContextListener {
	ServletContext context;
	private static final Logger logger = Logger.getLogger(ServeurHttpBoot.class);

	public void contextInitialized(ServletContextEvent contextEvent) {
		// ... some init work
		logger.info("Web app is starting");
	
	}

	public void contextDestroyed(ServletContextEvent contextEvent) {
		// ... some destroy work
		logger.info("Web app stoping");
	}
}