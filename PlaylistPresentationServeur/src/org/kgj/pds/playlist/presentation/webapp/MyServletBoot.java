package org.kgj.pds.playlist.presentation.webapp;

import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.kgj.pds.playlist.presentation.messagingService.WebappMessagingServiceManager;


public class MyServletBoot implements javax.servlet.ServletContextListener {
	private static final Logger logger = Logger.getLogger(MyServletBoot.class);
	

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		logger.info("contextDestroyed");
	}

	public void contextInitialized(ServletContextEvent arg0) {
		logger.info("Starting...");
//		new WebAppMessagingServiceManager("tcp://localhost:61616", "consumerFromView", "producerToView");
//		new WebAppMessagingServiceManager("tcp://localhost:61616", "consumerFromView", "producerToView");
		WebappMessagingServiceManager.getInstance();

	}
}
