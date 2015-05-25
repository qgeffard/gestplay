package org.kgj.pds.playlist.persistance;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;
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

		logger.info("Try a database connection");
		tryDbConnection();
		logger.info("Connection to Database OK");
		// ...
		// tryWSquery();

		startRMIServer();
	}

	private static void startRMIServer() {
		logger.info("RMI server started");

//		if (System.getSecurityManager() == null) {
//			System.setSecurityManager(new RMISecurityManager());
//		}

		Registry registry = null;
		Task obj;
		try {
			obj = new Task();
			registry = LocateRegistry.createRegistry(1099);
			registry.bind("rmi://localhost/RmiServer", obj);
		} catch (RemoteException e) {
			logger.error(e.getMessage());
		} catch (AlreadyBoundException e) {
			logger.error(e.getMessage());
		}
		logger.info("PeerServer bound in registry");

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

}
