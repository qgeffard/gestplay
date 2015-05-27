package org.kgj.pds.playlist.persistance;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.kgj.pds.playlist.persistance.messagingProtocol.Query;

public interface IFTask extends Remote{

	public String sendDeletedPlaylist(String query) throws RemoteException;

	public String sendModifiedPlaylist(String query) throws RemoteException;

	// Create the playlist and return it with the new identifier
	public String sendNewPlaylistSaved(String query) throws RemoteException;

	// on read or login return all playlist of a specific user
	public String sendGlobalPlaylistAndUser(String query, String login) throws RemoteException;
	
	public String undoUpdate(String query, String login) throws RemoteException;
}
