package org.kgj.pds.playlist.persistance;

import org.kgj.pds.playlist.persistance.entity.TrackEntity;
import org.kgj.pds.playlist.persistance.messagingProtocol.PlaylistType;
import org.kgj.pds.playlist.persistance.messagingProtocol.Query;
import org.kgj.pds.playlist.persistance.messagingProtocol.TrackListType;
import org.kgj.pds.playlist.persistance.messagingProtocol.TrackType;
import org.kgj.pds.playlist.persistance.messagingService.ClientAppMessagingServiceManager;
import org.kgj.pds.playlist.persistance.model.PlaylistDAO;
import org.kgj.pds.playlist.persistance.model.TrackDAO;
import org.kgj.pds.playlist.persistance.model.UsersDAO;

public class Task {
	private Query query;
	private PlaylistDAO playlistDao;
	private TrackDAO trackDao;
	private UsersDAO usersDao;

	public Task() {
		playlistDao = new PlaylistDAO(); 
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

		default:
			break;
		}
	}

	private void sendTrackById(int id) {//INUTILLOL
		TrackEntity userTrack = trackDao.read(id);
		PlaylistType playlist = new PlaylistType();
		TrackListType trackListType = new TrackListType();
		TrackType trac = new TrackType();
		trac = trackDao.convertToTrackType(userTrack);
		trackListType.getTrack().add(trackDao.convertToTrackType(userTrack));
		playlist.setTrackList(trackListType);
		query.getPlaylist().add(playlist);
		ClientAppMessagingServiceManager clAppMessServ = ClientAppMessagingServiceManager.getInstance();
		clAppMessServ.send(clAppMessServ.queryToString(query));	
	}

	private void sendGlobalPlaylistAndUser(String login){
	}
}
