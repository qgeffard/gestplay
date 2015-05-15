package org.kgj.pds.playlist.persistance;

import org.apache.log4j.Logger;
import org.kgj.pds.playlist.persistance.entity.PlaylistEntity;
import org.kgj.pds.playlist.persistance.entity.TrackEntity;
import org.kgj.pds.playlist.persistance.messagingProtocol.PlaylistType;
import org.kgj.pds.playlist.persistance.messagingProtocol.Query;
import org.kgj.pds.playlist.persistance.messagingProtocol.TrackListType;
import org.kgj.pds.playlist.persistance.messagingProtocol.TrackType;
import org.kgj.pds.playlist.persistance.messagingService.ClientAppMessagingServiceManager;
import org.kgj.pds.playlist.persistance.model.QueryDAO;

public class Task {
	private Query query;
	private QueryDAO queryDAO;
	private String messageContent;
	protected static final Logger logger = Logger.getLogger(Task.class);

	public Task() {
	}

	public Task(Query query, String messageContent) {
		this();
		this.query = query;
		this.messageContent = messageContent;
	}

	public void start() {
		switch (query.getAction().getNameAction()) {
		case "login":
		case "read":
			sendGlobalPlaylistAndUser(query.getUserManager().getUser().getLogin());
			break;
		case "create" :
			createPlaylist(query.getPlaylist());
		break;
			
		default:
			break;
		}
	}
	
	private void sendGlobalPlaylistAndUser(String login){
		Query queryDb = queryDAO.readByUser(login);
		query.getPlaylist();
		ClientAppMessagingServiceManager clAppMessServ = ClientAppMessagingServiceManager.getInstance();
//		logger.info("Message sending :" + clAppMessServ.queryToString(query));
//		clAppMessServ.send(clAppMessServ.queryToString(query));
	}
	
	private void createPlaylist(PlaylistType playlistType) {
		PlaylistEntity playlistEntity = new PlaylistEntity();
		playlistEntity.setCreator(playlistType.getCreator());
		playlistEntity.setTitle(playlistType.getTitle());
//		if (playlistDao.create(playlistEntity)){
//			ClientAppMessagingServiceManager clAppMessServ = ClientAppMessagingServiceManager.getInstance();
//			logger.info("Message sending :" + clAppMessServ.queryToString(query));
//			clAppMessServ.send(clAppMessServ.queryToString(query));	
//		}
	}
}
