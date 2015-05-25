package org.kgj.pds.playlist.persistance;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.apache.log4j.Logger;
import org.kgj.pds.playlist.Utils.QueryManager;
import org.kgj.pds.playlist.Utils.QueryMarshaller;
import org.kgj.pds.playlist.Utils.Source;
import org.kgj.pds.playlist.persistance.messagingProtocol.Query;
import org.kgj.pds.playlist.persistance.model.QueryDAO;

public class Task extends UnicastRemoteObject implements IFTask{
	public QueryDAO queryDao;

	protected static final Logger logger = Logger.getLogger(Task.class);

	public Task() throws RemoteException {
		queryDao = new QueryDAO();
	}

	public Task(Query query) throws RemoteException {
		this();
	}

	public String sendDeletedPlaylist(String queryStr) throws RemoteException{
		Query query = QueryMarshaller.stringToQuery(queryStr);
		if (queryDao.delete(query)) {
			QueryManager.setStatusSucced(query);
			QueryManager.flushPlaylist(query);
		} else {
			logger.error("Erreur delete");
			QueryManager.setStatusError(query, Source.PERSISTANCE.getName(), "Erreur delete");
		}
		return QueryMarshaller.queryToString(query);
	}

	public String sendModifiedPlaylist(String queryStr) throws RemoteException{
		Query query = QueryMarshaller.stringToQuery(queryStr);
		if (queryDao.updatePlaylist(query)) {
			QueryManager.setStatusSucced(query);
		} else {
			logger.error("Erreur update");
			QueryManager.setStatusError(query, Source.PERSISTANCE.getName(), "Erreur d'update");
		}
		return QueryMarshaller.queryToString(query);
	}

	// Create the playlist and return it with the new identifier
	public String sendNewPlaylistSaved(String queryStr) throws RemoteException{
		Query query = QueryMarshaller.stringToQuery(queryStr);
		query.getPlaylist().get(0).setIdentifier(Integer.toString(queryDao.create(query)));
		QueryManager.setStatusSucced(query);
		return QueryMarshaller.queryToString(query);
	}

	// on read or login return all playlist of a specific user
	public String sendGlobalPlaylistAndUser(String queryStr, String login) throws RemoteException{
		Query query = QueryMarshaller.stringToQuery(queryStr);
		Query querydb = queryDao.readByUser(login);
		query.getPlaylist().addAll(querydb.getPlaylist());
		QueryManager.setStatusSucced(query);
		return QueryMarshaller.queryToString(query);
	}
}
