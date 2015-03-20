package org.kgj.pds.playlist.metier;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

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
		startMessagingService();
		// ...
		tryWSquery();
	}

	private static void tryWSquery() {
		// TODO Auto-generated method stub
		logger.info("LOL");
		try {
			URLConnection connection = new URL("http://localhost:8080/PlaylistMetierServeurHttp/serveurHttp").openConnection();
			connection.setRequestProperty("Accept-Charset", "utf-8");
			InputStream response = connection.getInputStream();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Demarre le service de messagerie grace a la methode getInstance pour
	 * creer le singleton
	 */
	private static void startMessagingService() {
		ClientHttpMessagingServiceManager.getInstance();
	}

}
