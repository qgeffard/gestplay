package org.kgj.pds.playlist.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.kgj.pds.playlist.persistance.entity.PlaylistEntity;
import org.kgj.pds.playlist.persistance.messagingService.ClientAppMessagingServiceManager;
import org.kgj.pds.playlist.persistance.model.QueryDAO;

/**
 * Class used to init all ClientHttp side
 * 
 * @author Doox
 *
 */
public class AppBoot {

	static Logger logger = Logger.getLogger(AppBoot.class);

	public static void main(String[] args) {
		logger.info("Starting app metier");
		startMessagingService();
		logger.info("Try a database connection");
		tryDbConnection();
		logger.info("Connection to Database OK");
		// ...
		// tryWSquery();
	}

	private static void tryDbConnection() {
		String urlDatabase = QueryDAO.props.getProperty("urlDatabase");
		String login = QueryDAO.props.getProperty("login");
		String password = QueryDAO.props.getProperty("password");

		try {
			Connection connection = DriverManager.getConnection(urlDatabase,
					login, password);
			connection.close();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * Demarre le service de messagerie grace a la methode getInstance pour
	 * creer le singleton
	 */
	private static void startMessagingService() {
		ClientAppMessagingServiceManager.getInstance();
	}

}
