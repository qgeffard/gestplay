package org.kgj.pds.playlist.metier.data;

import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.kgj.pds.playlist.metier.messagingProtocol.PlaylistType;

public class LocalStorage {
	private static final Logger logger = Logger.getLogger(LocalStorage.class);
	
	private Map<String, Map<Integer, String>> commandUndo;
	private Map<String, Map<Integer, String>> commandRedo;
	private Map<String, String> users;
	
	private Properties prop;
	private InputStream input;

	public LocalStorage() {
		commandUndo  = new ConcurrentHashMap<String, Map<Integer,String>>();
		commandRedo  = new ConcurrentHashMap<String, Map<Integer,String>>();
		users = new LinkedHashMap<String, String>();
		
		//load users
		prop  =  new Properties();
		try {
			input =  getClass().getClassLoader().getResourceAsStream("users.properties");
			prop.load(input);
			
			String currentUserLogin = "";
			for (String propertyName : prop.stringPropertyNames()) {
				if(propertyName.endsWith("login")){
					currentUserLogin = prop.getProperty(propertyName);
				} else {
					users.put(currentUserLogin, prop.getProperty(propertyName));
				}
			}

			logger.info(users);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
	}

	public Map<String, Map<Integer, String>> getData() {
		return commandUndo;
	}

	public void setData(Map<String, Map<Integer, String>> command) {
		this.commandUndo = command;
	}

	public void addQueryToCommandUndo(String userToken, String query) {
		Map<Integer, String> existingCommandUndo = getCommandUndoByUser(userToken);
		
		if (existingCommandUndo == null) {
			existingCommandUndo = new ConcurrentHashMap<Integer, String>();
			existingCommandUndo.put(new Integer(1), query);
		} else {
			Integer newID = Collections.max(existingCommandUndo.keySet())+1;
			existingCommandUndo.put(newID, query);
		}
		
		this.commandUndo.put(userToken, existingCommandUndo);
	}
	
	public void addQueryToCommandRedo(String userToken, String query) {
		Map<Integer, String> existingCommandRedo = getCommandRedoByUser(userToken);
		
		if (existingCommandRedo == null) {
			existingCommandRedo = new ConcurrentHashMap<Integer, String>();
			existingCommandRedo.put(new Integer(1), query);
		} else {
			Integer newID = Collections.max(existingCommandRedo.keySet())+1;
			existingCommandRedo.put(newID, query);
		}
		
		this.commandRedo.put(userToken, existingCommandRedo);
	}
	
	public void deleteTheMaxRedoCommand(String user){
		getCommandRedoByUser(user).remove(Collections.max(getCommandRedoByUser(user).keySet()));
	}

	public Map<Integer, String> getCommandUndoByUser(String userToken) {
		if (commandUndo.containsKey(userToken)) {
			return commandUndo.get(userToken);
		}
		return null;
	}
	
	public Map<Integer, String> getCommandRedoByUser(String userToken) {
		if (commandRedo.containsKey(userToken)) {
			return commandRedo.get(userToken);
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