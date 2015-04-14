package org.kgj.pds.playlist.presentation.webapp;

import javax.servlet.ServletContextEvent;

import org.kgj.pds.playlist.presentation.messagingService.WebAppMessagingServiceManager;


public class MyServletBoot implements javax.servlet.ServletContextListener {
	
	

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("lol destroyed");

	}

	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Starting...");
//		new WebAppMessagingServiceManager("tcp://localhost:61616", "consumerFromView", "producerToView");
		WebAppMessagingServiceManager.getInstance();

	}
}
