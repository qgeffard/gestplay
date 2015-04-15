package org.kgj.pds.playlist.metier.serveurHttp;

import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.kgj.pds.playlist.metier.messagingProtocol.PlaylistType;

public class LocalStorage {
	private static final Logger logger = Logger.getLogger(LocalStorage.class);
	
	
	private Map<String, Map<Integer, PlaylistType>> data;
	private Map<String, String> users;
	
	private Properties prop;
	private InputStream input;

	public LocalStorage() {
		data  = new ConcurrentHashMap<String, Map<Integer,PlaylistType>>();
		users = new HashMap<String, String>();
		
		//load users
		prop  =  new Properties();
		try {
			input =  getClass().getClassLoader().getResourceAsStream("users.properties");
			prop.load(input);
			
			for (int i = 0; i < prop.keySet().size(); i+=2) {
				users.put(prop.getProperty(prop.keySet().toArray()[i].toString()), prop.getProperty(prop.keySet().toArray()[i+1].toString()));
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
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

	public Map<String, String> getUsers() {
		return users;
	}

	public void setUsers(Map<String, String> users) {
		this.users = users;
	}
	
	

}