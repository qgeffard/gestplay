package org.kgj.pds.playlist.metier.serveurHttp;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.kgj.pds.playlist.metier.messagingProtocol.PlaylistType;

public class LocalStorage {
	private Map<String, Map<Integer, PlaylistType>> data;

	public LocalStorage(Map<String, Map<Integer, PlaylistType>> data) {
		super();
		this.data = data;
	}

	public LocalStorage() {
		data = new ConcurrentHashMap<String, Map<Integer,PlaylistType>>();
	}

	public Map<String, Map<Integer, PlaylistType>> getData() {
		return data;
	}

	public void setData(Map<String, Map<Integer, PlaylistType>> data) {
		this.data = data;
	}

	public void addPlaylistToUser(String userToken, PlaylistType newPlaylist) {
		Map<Integer, PlaylistType> existingPlaylist = getPlaylistByUser(userToken);
		
		if (existingPlaylist == null) {
			existingPlaylist = new ConcurrentHashMap<Integer, PlaylistType>();
			existingPlaylist.put(new Integer(1), newPlaylist);
		} else {
			Integer newID = Collections.max(existingPlaylist.keySet())+1;
			existingPlaylist.put(newID, newPlaylist);
		}
		
		this.data.put(userToken, existingPlaylist);
	}

	public Map<Integer, PlaylistType> getPlaylistByUser(String userToken) {
		if (data.containsKey(userToken)) {
			return data.get(userToken);
		}
		return null;
	}

}