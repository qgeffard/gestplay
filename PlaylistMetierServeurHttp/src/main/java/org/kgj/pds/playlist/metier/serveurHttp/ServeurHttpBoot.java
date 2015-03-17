package org.kgj.pds.playlist.metier.serveurHttp;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServeurHttpBoot implements ServletContextListener {
	ServletContext context;

	public void contextInitialized(ServletContextEvent contextEvent) {
		// ... some init work
	
	}

	public void contextDestroyed(ServletContextEvent contextEvent) {
		// ... some destroy work
	}
}