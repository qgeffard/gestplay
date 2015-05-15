package org.kgj.pds.playlist.persistance;

import java.util.List;

import org.apache.log4j.Logger;
import org.kgj.pds.playlist.persistance.entity.PlaylistEntity;
import org.kgj.pds.playlist.persistance.entity.TrackEntity;
import org.kgj.pds.playlist.persistance.messagingProtocol.PlaylistType;
import org.kgj.pds.playlist.persistance.messagingProtocol.Query;
import org.kgj.pds.playlist.persistance.messagingProtocol.TrackListType;
import org.kgj.pds.playlist.persistance.messagingProtocol.TrackType;
import org.kgj.pds.playlist.persistance.messagingService.ClientAppMessagingServiceManager;
import org.kgj.pds.playlist.persistance.model.PlaylistDAO;
import org.kgj.pds.playlist.persistance.model.QueryDAO;
import org.kgj.pds.playlist.persistance.model.TrackDAO;
import org.kgj.pds.playlist.persistance.model.UsersDAO;

public class Task {
	private Query query;
	private QueryDAO queryDao;
	private TrackDAO trackDao;
	private UsersDAO usersDao;
	protected static final Logger logger = Logger.getLogger(Task.class);

	public Task() {
		queryDao = new QueryDAO(); 
		trackDao = new TrackDAO();
		usersDao = new UsersDAO();
	}

	public Task(Query query) {
		this();
		this.query = query;
	}

	public void start() {
		switch (query.getAction().getNameAction()) {
		case "login":
		case "read":
			sendGlobalPlaylistAndUser(query.getUserManager().getUser().getLogin());
			break;
			
		case "create":
			sendNewPlaylistSaved();
			break;
			
		case "update":
			sendModifiedPlaylist();
			break;
		
		case "delete":
			sendDeletedPlaylist();
			break;

		default:
			break;
		}
	}
	
	private void sendDeletedPlaylist() {
		if(queryDao.delete(query)){
			
		}
	}

	private void sendModifiedPlaylist() {
		// TODO Auto-generated method stub
		
	}

	//Create the playlist and return it with the new identifier
	private void sendNewPlaylistSaved() {
		query.getPlaylist().get(0).setIdentifier(Integer.toString(queryDao.create(query)));
		ClientAppMessagingServiceManager clAppMessServ = ClientAppMessagingServiceManager.getInstance();
		logger.info("Message sending :" + clAppMessServ.queryToString(query));
		clAppMessServ.send(clAppMessServ.queryToString(query));	
	}
	
	//on read or login return all playlist of a specific user
	private void sendGlobalPlaylistAndUser(String login){
		Query querydb = queryDao.readByUser(login);
		query.getPlaylist().addAll(querydb.getPlaylist());
		ClientAppMessagingServiceManager clAppMessServ = ClientAppMessagingServiceManager.getInstance();
		logger.info("Message sending :" + clAppMessServ.queryToString(query));
		clAppMessServ.send(clAppMessServ.queryToString(query));	
	}
}
