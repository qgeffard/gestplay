package org.kgj.pds.playlist.persistance.PlaylistPersistanceApplicationClient;

import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.apache.log4j.Logger;
import org.kgj.pds.playlist.Utils.QueryMarshaller;
import org.kgj.pds.playlist.persistance.IFTask;
import org.kgj.pds.playlist.persistance.messagingProtocol.Query;
import org.kgj.pds.playlist.persistance.messagingService.ClientAppMessagingServiceManager;

public class ClientRMI {
	protected static final Logger logger = Logger.getLogger(ClientRMI.class);
	private Query query;
	private IFTask taskRemote;
	
	public ClientRMI(Query query) {
		this.query = query;
		taskRemote = initRMIClient();
	}
	
	private IFTask initRMIClient() {
		Registry registry = null;
		try {
			registry = LocateRegistry.getRegistry("localhost", 1099) ;
		} catch (RemoteException e1) {
			logger.error(e1.getMessage());
		}
		
//		if (System.getSecurityManager() == null) {
//			System.setSecurityManager(new RMISecurityManager());
//		}

		try {
			return (IFTask) registry.lookup("rmi://localhost/RmiServer");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void start() {
		switch (query.getAction().getNameAction()) {
		case "login":
		case "read":
			try {
				ClientAppMessagingServiceManager.getInstance().send(taskRemote.sendGlobalPlaylistAndUser(QueryMarshaller.queryToString(query), query.getUserManager().getUser().getLogin()));
			} catch (RemoteException e) {
				logger.error(e.getMessage());
			}
			break;
		case "create":
			try {
				ClientAppMessagingServiceManager.getInstance().send(taskRemote.sendNewPlaylistSaved(QueryMarshaller.queryToString(query)));
			} catch (RemoteException e) {
				logger.error(e.getMessage());
			}
			break;
		case "update":
			try {
				ClientAppMessagingServiceManager.getInstance().send(taskRemote.sendModifiedPlaylist(QueryMarshaller.queryToString(query)));
			} catch (RemoteException e) {
				logger.error(e.getMessage());
			}
			break;

		case "delete":
			try {
				ClientAppMessagingServiceManager.getInstance().send(taskRemote.sendDeletedPlaylist(QueryMarshaller.queryToString(query)));
			} catch (RemoteException e) {
				logger.error(e.getMessage());
			}
			break;

		case "undo":
			try {
				ClientAppMessagingServiceManager.getInstance().send(taskRemote.undoUpdate(QueryMarshaller.queryToString(query), query.getUserManager().getUser().getLogin()));
			} catch (RemoteException e) {
				logger.error(e.getMessage());
			}
			break;
			
		default:
			break;
		}
	}

}
