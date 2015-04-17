package org.kgj.pds.playlist.persistance;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.BasicConfigurator;
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
